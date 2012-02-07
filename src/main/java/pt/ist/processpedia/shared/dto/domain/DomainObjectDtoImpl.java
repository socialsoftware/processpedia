package pt.ist.processpedia.shared.dto.domain;

public abstract class DomainObjectDtoImpl implements DomainObjectDto {

  private static final long serialVersionUID = 1L;

  private Long oid;

  public DomainObjectDtoImpl() {}

  public DomainObjectDtoImpl(long oid) {
    setOid(oid);
  }

  public Long getOid() {
    return oid;
  }

  public void setOid(Long oid) {
    this.oid = oid;
  }
}
