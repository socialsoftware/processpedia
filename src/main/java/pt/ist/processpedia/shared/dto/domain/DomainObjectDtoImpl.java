package pt.ist.processpedia.shared.dto.domain;

import pt.ist.processpedia.shared.dto.Dto;

public abstract class DomainObjectDtoImpl implements Dto {

  private Long oid;

  public DomainObjectDtoImpl() {}

  public DomainObjectDtoImpl(Long oid) {
    setOid(oid);
  }

  public Long getOid() {
    return oid;
  }

  public void setOid(Long oid) {
    this.oid = oid;
  }
}
