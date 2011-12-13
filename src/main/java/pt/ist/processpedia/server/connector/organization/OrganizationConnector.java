package pt.ist.processpedia.server.connector.organization;

import java.util.Set;

import pt.ist.processpedia.server.connector.ProcesspediaConnector;
import pt.ist.processpedia.server.organization.OrganizationalUnit;
import pt.ist.processpedia.server.organization.Party;

public interface OrganizationConnector extends ProcesspediaConnector {
  
  OrganizationalUnit loadOrganizationData();
  
}
