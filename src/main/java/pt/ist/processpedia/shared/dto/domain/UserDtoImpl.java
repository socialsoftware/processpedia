package pt.ist.processpedia.shared.dto.domain;

public class UserDtoImpl extends OperatingPartyDtoImpl implements UserDto {

  private static final long serialVersionUID = 1L;
  
  public UserDtoImpl() {}

  public UserDtoImpl(Long oid, String name, String avatarUrl) {
    super(oid, name, avatarUrl);
  }
}
