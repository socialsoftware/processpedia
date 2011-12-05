package pt.ist.processpedia.server.auth;

import javax.servlet.http.HttpSession;

import pt.ist.processpedia.server.domain.Processpedia;
import pt.ist.processpedia.server.domain.User;
import pt.ist.processpedia.shared.dto.auth.CredentialDto;
import pt.ist.processpedia.shared.exception.ProcesspediaException;

public interface Authenticator {

  public interface Credential {
    void validate() throws ProcesspediaException;
  }

  Credential getCredential(CredentialDto credentialDto);
  User login(HttpSession httpSession, Credential credential) throws ProcesspediaException;
  
  User getUser(Processpedia processpedia, Credential credential) throws ProcesspediaException;
  
}
