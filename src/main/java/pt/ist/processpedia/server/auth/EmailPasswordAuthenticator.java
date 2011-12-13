package pt.ist.processpedia.server.auth;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.processpedia.server.domain.Processpedia;
import pt.ist.processpedia.server.domain.User;
import pt.ist.processpedia.shared.dto.auth.CredentialDto;
import pt.ist.processpedia.shared.dto.auth.EmailPasswordCredentialDto;
import pt.ist.processpedia.shared.exception.ProcesspediaException;
import pt.ist.processpedia.shared.exception.WrongCredentialsException;

public class EmailPasswordAuthenticator extends AbstractAuthenticator {

  public class EmailPasswordCredential implements Credential {

    private String email;
    private String password;
    
    public EmailPasswordCredential(String email, String password) {
      this.email = email;
      this.password = password;
    }
    
    public String getEmail() {
      return email;
    }
    
    @Override
    public void validate() throws ProcesspediaException {
      Processpedia processpedia = (Processpedia)FenixFramework.getRoot();
      processpedia.loginUserWithEmailAndPassword(email, password);
    }
  }
  
  @Override
  public Credential getCredential(CredentialDto credentialDto) {
    EmailPasswordCredentialDto emailPasswordCredentialDto = (EmailPasswordCredentialDto)credentialDto;
    return new EmailPasswordCredential(emailPasswordCredentialDto.getEmail(), emailPasswordCredentialDto.getPassword());
  }

  @Override
  public User getUser(Processpedia processpedia, Credential credential) throws WrongCredentialsException {
    String email = ((EmailPasswordCredential)credential).getEmail();
    for(User user : processpedia.getUserSet()) {
      if(user.getEmail().equals(email)) {
        return user;
      }
    }
    throw new WrongCredentialsException();
  }

}
