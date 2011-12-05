package pt.ist.processpedia.server.domain;

import org.joda.time.DateTime;
import pt.ist.processpedia.server.util.MD5;
import pt.ist.processpedia.shared.exception.*;
import pt.ist.processpedia.shared.exception.password.PasswordTooShortException;
import pt.ist.processpedia.shared.exception.user.UserAlreadyActiveException;
import pt.ist.processpedia.shared.validation.InputValidator;

import java.util.Random;

public class User extends User_Base {

  private static final String DEFAULT_AVATAR_URL = "avatar.jpg";

  /**
   * Creates a new user.
   * @param name the name of the user
   * @param email the email of the user
   * @param password the user's password
   * @throws pt.ist.processpedia.shared.exception.UserNameInvalidException
   * @throws pt.ist.processpedia.shared.exception.email.EmailInvalidException
   * @throws pt.ist.processpedia.shared.exception.password.PasswordTooShortException
   */
  public User(String name, String email, String password) throws ProcesspediaException {
    updateName(name);
    updateEmail(email);
    setSalt(generateSalt());
    updatePassword(password);
    setActive(true);
    setAvatarUrl(DEFAULT_AVATAR_URL);
    setActivationKey(generateNewActivationKey());
    setCreationTimestamp(new DateTime());
  }

  /**
   * Checks if the credentials match a given user.
   * @param email the email address to be verified
   * @param password the password to be verified
   * @return true if the provided credentials match the stored ones, false otherwise
   * @throws pt.ist.processpedia.shared.exception.email.EmailInvalidException
   * @throws pt.ist.processpedia.shared.exception.password.PasswordTooShortException
   */
  public boolean matchCredentials(String email, String password) throws ProcesspediaException {
    InputValidator.validateEmail(email);
    InputValidator.validateUserPassword(password);
    String passwordHash = calculatePasswordHash(password);
    return email.equals(getEmail()) && passwordHash.equals(getPasswordHash());
  }

  /**
   * Calculate the hash of a given password considering the user's salt.
   * @param password the provided password to be salted and hashed
   * @return the hash of the provided password, previously salted with the user's salt
   * @throws pt.ist.processpedia.shared.exception.password.PasswordTooShortException
   */
  private String calculatePasswordHash(String password) throws PasswordTooShortException {
    return MD5.hash(password+getSalt());
  }

  /**
   * Generates a new salt.
   * @return the generated salt
   */
  private String generateSalt() {
    Random randomNumber = new Random();
    DateTime now = new DateTime();
    return MD5.hash(now.toString()+randomNumber);
  }

  /**
   * Generates a new activation key.
   * @return the newly generated activation key
   */
  private String generateNewActivationKey() {
    DateTime now = new DateTime();
    return MD5.hash(getName() + getEmail() + now.toString() + getPasswordHash());
  }

  private void setPassword(String password) {
    setPasswordHash(MD5.hash(password + getSalt()));
  }

  public void updateSettings(String newName, String newEmail, String newPassword) throws ProcesspediaException {
    updateName(newName);
    updateEmail(newEmail);
    if(InputValidator.isValidPassword(newPassword)) {
      updatePassword(newPassword);
    }
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
   * @throws WrongActivationKeyException when the provided activation key differs from the stored one
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
        throw new WrongActivationKeyException(activationKeyProvided);
      }
    }
  }

  /**
   * Sets a valid name.
   * @param name the name to be validated and updated
   * @throws pt.ist.processpedia.shared.exception.UserNameInvalidException
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

  /**
   * Sets a valid password.
   * @param password the password to be validated and updated
   * @throws pt.ist.processpedia.shared.exception.password.PasswordTooShortException
   */
  public void updatePassword(String password) throws PasswordTooShortException {
    InputValidator.validateUserPassword(password);
    setPassword(password);
  }

  public boolean hasUsername(String username) throws ProcesspediaException {
    InputValidator.validateUsername(username);
    return getUsername().equals(username);
  }
}
