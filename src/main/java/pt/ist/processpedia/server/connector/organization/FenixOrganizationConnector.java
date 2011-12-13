package pt.ist.processpedia.server.connector.organization;

import pt.ist.processpedia.server.organization.OrganizationalUnit;

public class FenixOrganizationConnector implements OrganizationConnector {

  @Override
  public OrganizationalUnit loadOrganizationData() {
    return new OrganizationalUnit("IST");
  }


}
