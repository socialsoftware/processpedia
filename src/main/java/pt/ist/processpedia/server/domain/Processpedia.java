package pt.ist.processpedia.server.domain;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.processpedia.server.util.EmailClient;
import pt.ist.processpedia.server.util.MD5;
import pt.ist.processpedia.server.util.Urn;
import pt.ist.processpedia.shared.exception.*;
import pt.ist.processpedia.shared.exception.email.EmailInvalidException;
import pt.ist.processpedia.shared.exception.password.PasswordTooShortException;
import pt.ist.processpedia.shared.exception.process.ProcessDescriptionTooLongException;
import pt.ist.processpedia.shared.exception.process.ProcessTitleTooLongException;
import pt.ist.processpedia.shared.exception.queue.QueueTitleAlreadyInUseException;
import pt.ist.processpedia.shared.exception.queue.QueueTitleTooLongException;
import pt.ist.processpedia.shared.exception.request.RequestTitleTooLongException;
import pt.ist.processpedia.shared.exception.user.UserAlreadyActiveException;
import pt.ist.processpedia.shared.exception.user.UserEmailAlreadyInUseException;
import pt.ist.processpedia.shared.exception.user.UserInactiveException;
import pt.ist.processpedia.shared.validation.InputValidator;

import java.util.Set;

public class Processpedia extends Processpedia_Base {

  public Processpedia() {}

  /**
   * Creates a new user.
   * @param name the name of the user
   * @param email the email of the user
   * @param password the user's password
   * @return a new user if the email is not yet registered, null if the email already exists
   * @throws pt.ist.processpedia.shared.exception.UserNameInvalidException when the provided name does not contain at least two names separated by a space.
   * @throws pt.ist.processpedia.shared.exception.email.EmailInvalidException when the provided email is not well-formed
   * @throws pt.ist.processpedia.shared.exception.password.PasswordTooShortException when the provided password is too short
   * @throws UserEmailAlreadyInUseException when the provided email is already associated to another user
   */
  public User createUser(String name, String email, String password) throws ProcesspediaException {
    InputValidator.validateUserName(name);
    InputValidator.validateEmail(email);
    InputValidator.validateUserPassword(password);
    for(User existingUser : getUserSet()) {
      if(existingUser.getEmail().equals(email)) {
        throw new UserEmailAlreadyInUseException(email);
      }
    }
    User user = new User(name, email, password);
    addUser(user);
    HumanQueue userQueue = new HumanQueue(name);
    user.setPersonalQueue(userQueue);
    addQueue(userQueue);
    EmailClient.sendActivationEmail(user.getName(), user.getEmail(), user.getActivationKey());
    return user;
  }

  /**
   * Activates an account that is not active.
   * @param activationKey the account key to be activated
   * @return the user for which the account was active
   * @throws UserAlreadyActiveException if the user is already active
   * @throws WrongActivationKeyException if the account key does not match any account
   */
  public User activateAccount(String activationKey) throws UserAlreadyActiveException, WrongActivationKeyException {
    for(User user : getUserSet()) {
      if(user.getActivationKey().equals(activationKey)) {
        if(user.getActive()) {
          throw new UserAlreadyActiveException();
        } else {
          user.setActive(true);
          return user;
        }
      }
    }
    throw new WrongActivationKeyException(activationKey);
  }


  /**
   * Creates a new process.
   * @param initiator the initiator of the process
   * @param processTitle the title of the process
   * @param processDescription the description of the process
   * @param requestTitle the title of the request
   * @param requestDescription the description of the request
   * @param expectsAnswer true if it is a callback request, false if it is a forward request
   * @param publishQueueSet the set of queues where the request is to be published
   * @param inputDataObjectSet the sub-set of data objects accessible to the executor
   * @return the newly created process
   */
  public Process createProcess(User initiator, String processTitle, String processDescription, String requestTitle,
                               String requestDescription, Boolean expectsAnswer, Set<Queue> publishQueueSet,
                               Set<DataObject> inputDataObjectSet) throws ProcessTitleTooLongException,
      ProcessDescriptionTooLongException,
      RequestTitleTooLongException {
    Process newProcess = new Process(initiator, processTitle, processDescription);
    addProcess(newProcess);
    newProcess.createRequest(initiator, requestTitle, requestDescription, expectsAnswer, publishQueueSet, inputDataObjectSet);
    return newProcess;
  }

  /**
   * Creates a new human queue.
   * @param title the title of the human queue
   * @return the newly created human queue
   * @throws QueueTitleAlreadyInUseException when the title provided is already associated to another queue
   * @throws QueueTitleTooLongException
   */
  public HumanQueue createHumanQueue(String title) throws ProcesspediaException {
    InputValidator.validateQueueTitle(title);
    for(Queue existingQueue : getQueueSet()) {
      if(existingQueue.getTitle().equals(title)) {
        throw new QueueTitleAlreadyInUseException(title);
      }
    }
    HumanQueue newQueue = new HumanQueue(title);
    addQueue(newQueue);
    return newQueue;
  }

  public Tag getTag(String tagValue) {
    for(Process process : getProcessSet()) {
      for(Request request : process.getRequestSet()) {
        if(request.getSubject().getValue().equals(tagValue)) {
          return request.getSubject();
        }
      }
    }
    Tag tag = new Tag(tagValue);
    return tag;
  }

  /**
   * Attempts to login a user with the provided credentials (email and password).
   * @param email the email to be verified
   * @param password the password to be verified
   * @return the corresponding user when its credentials match and the user is in the active state
   * @throws pt.ist.processpedia.shared.exception.email.EmailInvalidException when the email provided is not valid
   * @throws pt.ist.processpedia.shared.exception.password.PasswordTooShortException when the passwordHash provided is not valid
   * @throws pt.ist.processpedia.shared.exception.user.UserInactiveException when the credentials are valid but the user is not in the active state
   * @throws WrongCredentialsException when the provided credentials are wrong or do not match any user
   */
  public User loginUser(String email, String password) throws ProcesspediaException {
    InputValidator.validateEmail(email);
    InputValidator.validateUserPassword(password);
    for(User user : getUserSet()) {
      if(user.getEmail().equals(email)) {
        String passwordHash = MD5.hash(password+user.getSalt());
        if(user.getPasswordHash().equals(passwordHash)) {
          if(user.getActive()) {
            return user;
          } else {
            throw new UserInactiveException(Urn.getUrn(user));
          }
        } else {
          throw new WrongCredentialsException(email);
        }
      }
    }
    throw new WrongCredentialsException(email);
  }

  public static Processpedia getInstance() {
    return FenixFramework.getRoot();
  }
}
