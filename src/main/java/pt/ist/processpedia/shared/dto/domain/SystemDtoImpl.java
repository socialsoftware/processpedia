package pt.ist.processpedia.shared.dto.domain;

public class SystemDtoImpl extends OperatingPartyDtoImpl implements SystemDto {

  private static final long serialVersionUID = 1L;
  
  public SystemDtoImpl() {}
  
  public SystemDtoImpl(long oid, String name, String avatarUrl) {
    super(oid, name, avatarUrl);
  }
}
