package pt.ist.processpedia.shared.dto.domain;

public abstract class OperatingPartyDtoImpl extends PartyDtoImpl implements OperatingPartyDto {

  private static final long serialVersionUID = 1L;

  public OperatingPartyDtoImpl() {}
  
  public OperatingPartyDtoImpl(long oid, String name, String avatarUrl) {
    super(oid, name, avatarUrl);
  }
}
