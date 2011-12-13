package pt.ist.processpedia.server.organization;

import java.util.HashSet;
import java.util.Set;

public abstract class Party {

  private Set<Party> parentPartySet;
  
  public Party() {
    parentPartySet = new HashSet<Party>();
  }

  public Set<Party> getParentPartySet() {
    return parentPartySet;
  }
  
  public boolean hasAnyParentParty() {
    return parentPartySet.size() != 0;
  }
  
  public void addParentParty(Party parentParty) {
    if(!parentPartySet.contains(parentParty)) {
      parentPartySet.add(parentParty);
    }
  }
  
  public abstract boolean isPerson();
}
