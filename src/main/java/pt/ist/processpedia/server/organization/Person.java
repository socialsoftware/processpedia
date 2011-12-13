package pt.ist.processpedia.server.organization;

public class Person extends Party {
  
  private String name;
  private String email;
  private String avatarUrl;
  private String netId;
  
  public Person(String name, String email, String avatarUrl, String netId) {
    setName(name);
    setEmail(email);
    setAvatarUrl(avatarUrl);
    setNetId(netId);
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getAvatarUrl() {
    return avatarUrl;
  }
  
  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public boolean isPerson() {
    return true;
  }
  
  public String toString() {
    return getName();
  }
  
  public String getNetId() {
    return netId;
  }
  
  public void setNetId(String netId) {
    this.netId = netId;
  }
}
