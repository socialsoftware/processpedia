package pt.ist.processpedia.server.domain;

import org.joda.time.DateTime;
import pt.ist.processpedia.server.util.MD5;
import pt.ist.processpedia.shared.exception.*;
import pt.ist.processpedia.shared.exception.activationkey.ActivationKeyIsWrongException;
import pt.ist.processpedia.shared.exception.user.UserAlreadyActiveException;
import pt.ist.processpedia.shared.validation.InputValidator;

public class User extends User_Base {

  /**
   * Creates a new user.
   * @param name the name of the user
   * @param email the email of the user
   * @param password the user's password
   * @throws pt.ist.processpedia.shared.exception.user.UserNameInvalidException
   * @throws pt.ist.processpedia.shared.exception.email.EmailInvalidException
   */
  public User(String name, String avatarUrl, String email) throws ProcesspediaException {
    init(name, avatarUrl);
    updateEmail(email);
    setActive(true);
    setActivationKey(generateNewActivationKey());
  }

  /**
   * Generates a new activation key.
   * @return the newly generated activation key
   */
  private String generateNewActivationKey() {
    DateTime now = new DateTime();
    return MD5.hash(getName() + getEmail() + now.toString());
  }

  public void updateSettings(String newName, String newEmail) throws ProcesspediaException {
    updateName(newName);
    updateEmail(newEmail);
  }

  /**
   * Checks if the user is active (this method is a wrapper for getActive() for aesthetics reasons only).
   * @return true if the user is active, false otherwise
   */
  private boolean userIsActive() {
    return getActive();
  }

  /**
   * Activates the user using an activation key.
   * @param activationKeyProvided the activation key provided by the user
   * @throws ActivationKeyIsWrongException when the provided activation key differs from the stored one
   * @throws UserAlreadyActiveException when the user is already active
   */
  public void activate(String activationKeyProvided) throws ProcesspediaException {
    InputValidator.validateActivationKey(activationKeyProvided);
    if(userIsActive()) {
      throw new UserAlreadyActiveException();
    } else {
      if(getActivationKey().equals(activationKeyProvided)) {
        setActive(true);
        setActivationKey(generateNewActivationKey());
      } else {
        throw new ActivationKeyIsWrongException(activationKeyProvided);
      }
    }
  }

  /**
   * Sets a valid name.
   * @param name the name to be validated and updated
   * @throws pt.ist.processpedia.shared.exception.user.UserNameInvalidException
   */
  public void updateName(String name) throws ProcesspediaException {
    InputValidator.validateUserName(name);
    setName(name);
  }

  /**
   * Sets a valid email.
   * @param email the email to be validated and updated
   * @throws pt.ist.processpedia.shared.exception.email.EmailInvalidException
   */
  public void updateEmail(String email) throws ProcesspediaException {
    InputValidator.validateEmail(email);
    setEmail(email);
  }

}
