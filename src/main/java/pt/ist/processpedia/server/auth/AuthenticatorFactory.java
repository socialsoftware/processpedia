package pt.ist.processpedia.server.auth;

import pt.ist.processpedia.shared.dto.auth.CASCredentialDto;
import pt.ist.processpedia.shared.dto.auth.CredentialDto;
import pt.ist.processpedia.shared.dto.auth.EmailPasswordCredentialDto;
import pt.ist.processpedia.shared.exception.authentication.UnknownCredentialException;

public class AuthenticatorFactory {

  private static Authenticator EMAIL_PASSWORD_AUTHENTICATOR = new EmailPasswordAuthenticator();
  private static Authenticator CAS_AUTHENTICATOR = new CASAuthenticator();
  
  public static Authenticator getAuthenticatorFor(CredentialDto credentialDto) throws UnknownCredentialException {
    if(credentialDto instanceof CASCredentialDto) {
      return CAS_AUTHENTICATOR;
    } else if(credentialDto instanceof EmailPasswordCredentialDto) {
      return EMAIL_PASSWORD_AUTHENTICATOR;
    }
    throw new UnknownCredentialException();
  }
  
}
