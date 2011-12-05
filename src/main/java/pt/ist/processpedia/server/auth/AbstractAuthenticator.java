package pt.ist.processpedia.server.auth;

import javax.servlet.http.HttpSession;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.processpedia.server.domain.Processpedia;
import pt.ist.processpedia.server.domain.User;
import pt.ist.processpedia.shared.exception.ProcesspediaException;

public abstract class AbstractAuthenticator implements Authenticator {
  
  @Override
  public final User login(HttpSession httpSession, Credential credential) throws ProcesspediaException {
    credential.validate();
    Processpedia processpedia = (Processpedia)FenixFramework.getRoot();
    
    User user = getUser(processpedia, credential);
    httpSession.setAttribute("actorOid", user.getOid());
    return user;
  }
}
