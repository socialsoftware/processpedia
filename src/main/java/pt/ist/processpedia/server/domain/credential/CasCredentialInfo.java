package pt.ist.processpedia.server.domain.credential;

public class CasCredentialInfo extends CasCredentialInfo_Base {

  public CasCredentialInfo(String netId) {
    setNetId(netId);
  }

  public boolean matchNetId(String netId) {
    return getNetId().equals(netId);
  }
  
}
