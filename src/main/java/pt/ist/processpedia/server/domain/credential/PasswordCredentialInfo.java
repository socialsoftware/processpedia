package pt.ist.processpedia.server.domain.credential;

import java.util.Random;

import org.joda.time.DateTime;

import pt.ist.processpedia.server.util.MD5;
import pt.ist.processpedia.shared.exception.ProcesspediaException;
import pt.ist.processpedia.shared.validation.InputValidator;

public class PasswordCredentialInfo extends PasswordCredentialInfo_Base {

  public PasswordCredentialInfo(String password) {
    setSalt(generateSalt());
    setPasswordHash(calculatePasswordHash(password));
  }

  /**
   * Checks if the password credential info matches.
   * @param password the password to be verified
   * @return true if the provided password match the stored one, false otherwise
   */
  public boolean matchPassword(String password) throws ProcesspediaException {
    InputValidator.validateUserPassword(password);
    String passwordHash = calculatePasswordHash(password);
    return passwordHash.equals(getPasswordHash());
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
   * Calculate the hash of a given password considering the user's salt.
   * @param password the provided password to be salted and hashed
   * @return the hash of the provided password, previously salted with the user's salt
   */
  private String calculatePasswordHash(String password) {
    return MD5.hash(password+getSalt());
  }

  public void updatePassword(String newPassword) {
    setPasswordHash(calculatePasswordHash(newPassword));    
  }

}
