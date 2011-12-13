package pt.ist.processpedia.client.auth;

public class EmailPasswordAuthenticationHandler implements AuthenticationHandler {
  
  @Override
  public boolean hasValidAuthentication() {
    return true;
  }

}
