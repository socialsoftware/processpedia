package pt.ist.processpedia.shared.dto.domain;

public class UserDtoImpl extends DomainObjectDtoImpl implements UserDto {

  private String name;

  public UserDtoImpl() {}

  public UserDtoImpl(Long oid, String name) {
    super(oid);
    setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
