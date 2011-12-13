package pt.ist.processpedia.shared.exception.authentication;

import pt.ist.processpedia.shared.exception.ProcesspediaException;

public class NetIdAlreadyInUserException extends ProcesspediaException {

  private String netId;
  
  public NetIdAlreadyInUserException() {}
  
  public NetIdAlreadyInUserException(String netId) {
    this.netId = netId;
  }
  
  public String getNetId() {
    return netId;
  }
  
  public void setNetId(String netId) {
    this.netId = netId;
  }
  
}
