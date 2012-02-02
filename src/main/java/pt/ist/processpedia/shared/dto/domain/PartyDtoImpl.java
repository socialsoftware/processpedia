package pt.ist.processpedia.shared.dto.domain;

public abstract class PartyDtoImpl extends DomainObjectDtoImpl implements PartyDto {

  private static final long serialVersionUID = 1L;

  private String name;
  private String avatarUrl;

  public PartyDtoImpl() {}

  public PartyDto withName(String name) {
    setName(name);
    return this;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public PartyDto withAvatarUrl(String avatarUrl) {
    setAvatarUrl(avatarUrl);
    return this;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }
}
