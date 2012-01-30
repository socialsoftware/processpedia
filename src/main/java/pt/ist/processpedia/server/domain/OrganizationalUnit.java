package pt.ist.processpedia.server.domain;

import pt.ist.processpedia.shared.exception.ProcesspediaException;

public class OrganizationalUnit extends OrganizationalUnit_Base {

  public OrganizationalUnit(String name, String avatarUrl) throws ProcesspediaException {
    init(name, avatarUrl);
  }
}
