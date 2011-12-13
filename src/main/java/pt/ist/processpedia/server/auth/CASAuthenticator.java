package pt.ist.processpedia.server.auth;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import pt.ist.processpedia.server.PropertiesManager;
import pt.ist.processpedia.server.domain.Processpedia;
import pt.ist.processpedia.server.domain.User;
import pt.ist.processpedia.shared.dto.auth.CASCredentialDto;
import pt.ist.processpedia.shared.dto.auth.CredentialDto;
import pt.ist.processpedia.shared.exception.ProcesspediaException;
import pt.ist.processpedia.shared.exception.WrongCredentialsException;

import edu.yale.its.tp.cas.client.ServiceTicketValidator;

public class CASAuthenticator extends AbstractAuthenticator {

  static String CAS_LOGIN_URL;
  static String APPLICATION_HOME_URL;
  
  static {
    CAS_LOGIN_URL = PropertiesManager.getProperty("cas.login.url");
    APPLICATION_HOME_URL = PropertiesManager.getProperty("application.home.url");
  }
  
  public class CASCredential implements Credential {

    private ServiceTicketValidator serviceTicketValidator;
    private String ticket;
    
    public CASCredential(ServiceTicketValidator serviceTicketValidator, String ticket) {
      this.ticket = ticket;
      this.serviceTicketValidator = serviceTicketValidator;
    }
    
    public String getUsername() {
      return serviceTicketValidator.getUser();
    }
    
    @Override
    public void validate() throws ProcesspediaException {
      serviceTicketValidator.setServiceTicket(ticket);
      
      try {
        serviceTicketValidator.validate();
      } catch (IOException e) {
        System.out.println("Validate error:" + e);
        e.printStackTrace();
      } catch (SAXException e) {
        System.out.println("SAXException:" + e);
        e.printStackTrace();
      } catch (ParserConfigurationException e) {
        System.out.println("ParserConfigurationException:" + e);
        e.printStackTrace();
      }
      if(!serviceTicketValidator.isAuthenticationSuccesful()) {
        throw new WrongCredentialsException();
      }
    }
    
  }
  
  public Credential getCredential(CredentialDto credentialDto) {
    CASCredentialDto casCredentialDto = (CASCredentialDto)credentialDto;
    ServiceTicketValidator serviceTicketValidator = new ServiceTicketValidator();
    serviceTicketValidator.setCasValidateUrl(CAS_LOGIN_URL);
    serviceTicketValidator.setService(APPLICATION_HOME_URL);
    return new CASCredential(serviceTicketValidator, casCredentialDto.getTicket());
  }

  @Override
  public User getUser(Processpedia processpedia, Credential credential) throws ProcesspediaException {
    String netId = ((CASCredential)credential).getUsername();
    return processpedia.loginUserWithCasTicket(netId);
  }
}
