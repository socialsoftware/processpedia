package pt.ist.processpedia.server.connector.organization;

import pt.ist.processpedia.server.connector.ProcesspediaConnector;
import pt.ist.processpedia.server.organization.OrganizationalUnit;

public interface OrganizationConnector extends ProcesspediaConnector {
  
  OrganizationalUnit loadOrganizationData();
  
}
