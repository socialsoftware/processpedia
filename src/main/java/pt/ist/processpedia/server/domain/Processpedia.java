package pt.ist.processpedia.server.domain;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.processpedia.server.domain.credential.CasCredentialInfo;
import pt.ist.processpedia.server.domain.credential.PasswordCredentialInfo;
import pt.ist.processpedia.server.util.Urn;
import pt.ist.processpedia.shared.exception.*;
import pt.ist.processpedia.shared.exception.activationkey.ActivationKeyIsWrongException;
import pt.ist.processpedia.shared.exception.authentication.NetIdAlreadyInUserException;
import pt.ist.processpedia.shared.exception.authentication.WrongCredentialTypeException;
import pt.ist.processpedia.shared.exception.credential.CredentialInfoIsWrongException;
import pt.ist.processpedia.shared.exception.process.ProcessDescriptionTooLongException;
import pt.ist.processpedia.shared.exception.process.ProcessTitleTooLongException;
import pt.ist.processpedia.shared.exception.queue.QueueTitleAlreadyInUseException;
import pt.ist.processpedia.shared.exception.queue.QueueTitleTooLongException;
import pt.ist.processpedia.shared.exception.request.RequestTitleTooLongException;
import pt.ist.processpedia.shared.exception.user.UserAlreadyActiveException;
import pt.ist.processpedia.shared.exception.user.UserEmailAlreadyInUseException;
import pt.ist.processpedia.shared.exception.user.UserInactiveException;
import pt.ist.processpedia.shared.exception.user.UserNameInvalidException;
import pt.ist.processpedia.shared.validation.InputValidator;

import java.util.Set;

public class Processpedia extends Processpedia_Base {
  
  public User createUserWithCasCredentialInfo(String name, String email, String avatarUrl, String netId) throws ProcesspediaException {
    InputValidator.validateUserName(name);
    InputValidator.validateEmail(email);
    
    for(Party party : getPartySet()) {
      if(party instanceof User) {
        User existingUser = (User)party;
        if(existingUser.getEmail().equals(email)) {
          throw new UserEmailAlreadyInUseException(email);
        }
        if(existingUser.getCredentialInfo() instanceof CasCredentialInfo) {
          String existingNetId = ((CasCredentialInfo)existingUser.getCredentialInfo()).getNetId();
          if(existingNetId.equals(netId)) {
            throw new NetIdAlreadyInUserException(email);
          }
        }
      }
    }
    User user = new User(name, avatarUrl, email);
    user.setCredentialInfo(new CasCredentialInfo(netId));
    PrivateQueue privateQueue = new PrivateQueue(name);
    user.setPrivateQueue(privateQueue);
    addParty(user);
    addQueue(privateQueue);
    return user;
  }

  /**
   * Creates a new user with password credentials.
   * @param name the name of the user
   * @param avatarUrl the url of the user's avatar image
   * @param email the email of the user
   * @param password the user's password
   * @return a new user if the email is not yet registered, null if the email already exists
   * @throws UserNameInvalidException when the provided name does not contain at least two names separated by a space.
   * @throws EmailInvalidException when the provided email is not well-formed
   * @throws PasswordTooShortException when the provided password is too short
   * @throws UserEmailAlreadyInUseException when the provided email is already associated to another user
   */
  public User createUserWithPasswordCredentialInfo(String name, String avatarUrl, String email, String password) throws ProcesspediaException {
    InputValidator.validateUserName(name);
    InputValidator.validateEmail(email);
    InputValidator.validateUserPassword(password);
    for(Party party : getPartySet()) {
      if(party instanceof User) {
        User existingUser = (User)party;
        if(existingUser.getEmail().equals(email)) {
          throw new UserEmailAlreadyInUseException(email);
        }
      }
    }
    User user = new User(name, avatarUrl, email);
    user.setCredentialInfo(new PasswordCredentialInfo(password));
    addParty(user);
    //EmailClient.sendActivationEmail(user.getName(), user.getEmail(), user.getActivationKey());
    return user;
  }

  /**
   * Activates an account that is not active.
   * @param activationKey the account key to be activated
   * @return the user for which the account was active
   * @throws UserAlreadyActiveException if the user is already active
   * @throws ActivationKeyIsWrongException if the account key does not match any account
   */
  public User activateAccount(String activationKey) throws UserAlreadyActiveException, ActivationKeyIsWrongException {
    for(Party party : getPartySet()) {
      if(party instanceof User) {
        User user = (User)party;
        if(user.getActivationKey().equals(activationKey)) {
          if(user.getActive()) {
            throw new UserAlreadyActiveException();
          } else {
            user.setActive(true);
            return user;
          }
        }
      }
    }
    throw new ActivationKeyIsWrongException(activationKey);
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
   * @param inputDataObjectVersionSet the initial set of data object versions accessible to the creator of the process
   * @return the newly created process
   */
  public Process createProcess(User initiator, String processTitle, String processDescription, String requestTitle,
                               String requestDescription, Boolean expectsAnswer, Set<Queue> publishQueueSet,
                               Set<DataObjectVersion> inputDataObjectVersionSet) throws ProcessTitleTooLongException,
      ProcessDescriptionTooLongException,
      RequestTitleTooLongException {
    Process newProcess = new Process(initiator, processTitle, processDescription);
    addProcess(newProcess);
    Request initialRequest = newProcess.createRequest(initiator, requestTitle, requestDescription, expectsAnswer, publishQueueSet, inputDataObjectVersionSet);
    newProcess.setInitialRequest(initialRequest);
    return newProcess;
  }

  /**
   * Creates a new queue.
   * @param title the title of the queue
   * @return the newly created queue
   * @throws QueueTitleAlreadyInUseException when the title provided is already associated to another queue
   * @throws QueueTitleTooLongException when the title provided is too long
   */
  public PrivateQueue createPrivateQueue(String title) throws ProcesspediaException {
    InputValidator.validateQueueTitle(title);
    for(Queue existingQueue : getQueueSet()) {
      if(existingQueue.getTitle().equals(title)) {
        throw new QueueTitleAlreadyInUseException(title);
      }
    }
    PrivateQueue newQueue = new PrivateQueue(title);
    addQueue(newQueue);
    return newQueue;
  }
  
  /**
   * Attempts to login a user given a CAS netId.
   * @param netId the netId to be verified
   * @return
   * @throws CredentialInfoIsWrongException
   */
  public User loginUserWithCasTicket(String ticket) throws CredentialInfoIsWrongException {
    //TODO: get netId using the given ticket
    String netId = "";
    for(Party party : getPartySet()) {
      if(party instanceof User) {
        User user = (User)party;
        if(user.getCredentialInfo() instanceof CasCredentialInfo) {
          CasCredentialInfo credentialInfo = (CasCredentialInfo)user.getCredentialInfo();
          if(credentialInfo.matchNetId(netId)) {
            return user;
          }
        }
      }
    }
    throw new CredentialInfoIsWrongException(netId);
  }
  
  /**
   * Attempts to login a user with the provided credentials (email and password).
   * @param email the email to be verified
   * @param password the password to be verified
   * @return the corresponding user when its credentials match and the user is in the active state
   * @throws pt.ist.processpedia.shared.exception.email.EmailInvalidException when the email provided is not valid
   * @throws pt.ist.processpedia.shared.exception.credential.PasswordTooShortException when the passwordHash provided is not valid
   * @throws pt.ist.processpedia.shared.exception.user.UserInactiveException when the credentials are valid but the user is not in the active state
   * @throws CredentialInfoIsWrongException when the provided credentials are wrong or do not match any user
   */
  public User loginUserWithEmailAndPassword(String email, String password) throws ProcesspediaException {
    InputValidator.validateEmail(email);
    InputValidator.validateUserPassword(password);
    for(Party party : getPartySet()) {
      if(party instanceof User) {
        User user = (User)party;
        if(user.getEmail().equals(email)) {
          if(user.getCredentialInfo() instanceof PasswordCredentialInfo) {
            PasswordCredentialInfo credentialInfo = (PasswordCredentialInfo)user.getCredentialInfo();
            if(credentialInfo.matchPassword(password)) {
              if(user.getActive()) {
                return user;
              } else {
                throw new UserInactiveException(Urn.getUrn(user));
              }
            } else {
              throw new CredentialInfoIsWrongException(email);
            }
          } else {
            throw new WrongCredentialTypeException();
          }
        }
      }
    }
    throw new CredentialInfoIsWrongException(email);
  }

  public static Processpedia getInstance() {
    return FenixFramework.getRoot();
  }
}
