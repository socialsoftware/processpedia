package pt.ist.processpedia.shared.dto.domain;

import pt.ist.processpedia.shared.dto.Dto;

public abstract class DomainObjectDtoImpl implements Dto {

  private long oid;

  public DomainObjectDtoImpl() {}

  public DomainObjectDtoImpl(long oid) {
    setOid(oid);
  }

  public long getOid() {
    return oid;
  }

  public void setOid(long oid) {
    this.oid = oid;
  }
}
