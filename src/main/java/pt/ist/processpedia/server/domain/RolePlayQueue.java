package pt.ist.processpedia.server.domain;

public class RolePlayQueue extends RolePlayQueue_Base {

  public boolean hasAccountabilityType(AccountabilityType accountabilityType) {
    return accountabilityType != null && accountabilityType.equals(getAccountabilityType());
  }
}
