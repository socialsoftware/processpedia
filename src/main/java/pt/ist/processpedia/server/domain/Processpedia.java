package pt.ist.processpedia.server.domain;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.processpedia.server.domain.credential.CasCredentialInfo;
import pt.ist.processpedia.server.domain.credential.PasswordCredentialInfo;
import pt.ist.processpedia.server.organization.OrganizationalUnit;
import pt.ist.processpedia.server.organization.Party;
import pt.ist.processpedia.server.organization.Person;
import pt.ist.processpedia.server.util.Urn;
import pt.ist.processpedia.shared.exception.*;
import pt.ist.processpedia.shared.exception.authentication.NetIdAlreadyInUserException;
import pt.ist.processpedia.shared.exception.authentication.WrongCredentialTypeException;
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
  
  public Processpedia() {
    try {
      createUserWithPasswordCredentialInfo("David Martinho", "d@g.com", "avatar", "xpto");
    } catch (ProcesspediaException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public User createUserWithCasCredentialInfo(String name, String email, String avatarUrl, String netId) throws ProcesspediaException {
    InputValidator.validateUserName(name);
    InputValidator.validateEmail(email);
    
    for(User existingUser : getUserSet()) {
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
    User user = new User(name, email, avatarUrl);
    addUser(user);
    HumanQueue personalQueue = new HumanQueue(name);
    user.setPersonalQueue(personalQueue);
    personalQueue.setProcesspedia(this);
    user.setCredentialInfo(new CasCredentialInfo(netId));
    return user;
  }

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
  public User createUserWithPasswordCredentialInfo(String name, String email, String avatarUrl, String password) throws ProcesspediaException {
    InputValidator.validateUserName(name);
    InputValidator.validateEmail(email);
    InputValidator.validateUserPassword(password);
    for(User existingUser : getUserSet()) {
      if(existingUser.getEmail().equals(email)) {
        throw new UserEmailAlreadyInUseException(email);
      }
    }
    User user = new User(name, email, avatarUrl);
    addUser(user);
    HumanQueue personalQueue = new HumanQueue(name);
    user.setPersonalQueue(personalQueue);
    personalQueue.setProcesspedia(this);
    user.setCredentialInfo(new PasswordCredentialInfo(password));
    //EmailClient.sendActivationEmail(user.getName(), user.getEmail(), user.getActivationKey());
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
   * Attempts to login a user given a CAS netId.
   * @param netId the netId to be verified
   * @return
   * @throws WrongCredentialsException
   */
  public User loginUserWithCasTicket(String ticket) throws WrongCredentialsException {
    //TODO: get netId using the given ticket
    String netId = "";
    for(User user : getUserSet()) {
      if(user.getCredentialInfo() instanceof CasCredentialInfo) {
        CasCredentialInfo credentialInfo = (CasCredentialInfo)user.getCredentialInfo();
        if(credentialInfo.matchNetId(netId)) {
          return user;
        }
      }
    }
    throw new WrongCredentialsException(netId);
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
  public User loginUserWithEmailAndPassword(String email, String password) throws ProcesspediaException {
    InputValidator.validateEmail(email);
    InputValidator.validateUserPassword(password);
    for(User user : getUserSet()) {
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
            throw new WrongCredentialsException(email);
          }
        } else {
          throw new WrongCredentialTypeException();
        }
      }
    }
    throw new WrongCredentialsException(email);
  }

  public static Processpedia getInstance() {
    return FenixFramework.getRoot();
  }

  public void installOrganizationalUnit(OrganizationalUnit rootOrganizationalUnit) {
    HumanQueue humanQueue = new HumanQueue(rootOrganizationalUnit.getDesignation());
    int error = 0;
    int success = 0;
    for(Party party : rootOrganizationalUnit.getChildPartySet()) {
      if(party.isPerson()) {
        Person person = (Person)party;
        try {
          createUserWithCasCredentialInfo(person.getName(), person.getEmail(), person.getAvatarUrl(), person.getNetId());
          success++;
        } catch(ProcesspediaException e) {
          error++;
        }
      }
    }
    
  }
  
  public void updateOrganizationalUnit(OrganizationalUnit rootOrganization) {
    
  }
}
