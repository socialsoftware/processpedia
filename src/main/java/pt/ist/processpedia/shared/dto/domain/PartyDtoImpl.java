package pt.ist.processpedia.shared.dto.domain;

public abstract class PartyDtoImpl extends DomainObjectDtoImpl implements PartyDto {

  private static final long serialVersionUID = 1L;

  private String name;
  private String avatarUrl;

  public PartyDtoImpl() {}

  public PartyDtoImpl(long oid, String name, String avatarUrl) {
    super(oid);
    setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }
}
