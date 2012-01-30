package pt.ist.processpedia.server.domain;

public class RoleAssociation extends RoleAssociation_Base {

  public RoleAssociation(Role role, AccountabilityType accountabilityType) {
    setRole(role);
    setAccountabilityType(accountabilityType);
  }
}
