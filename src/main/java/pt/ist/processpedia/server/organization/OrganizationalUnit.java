package pt.ist.processpedia.server.organization;

import java.util.HashSet;
import java.util.Set;

public class OrganizationalUnit extends Party {

  private String designation;
  private Set<Party> childPartySet;
  
  public OrganizationalUnit(String designation) {
    this.designation = designation;
    childPartySet = new HashSet<Party>();
  }
  
  public String getDesignation() {
    return designation;
  }
  
  public void setDesignation(String designation) {
    this.designation = designation;
  }
  
  public void addChildParty(Party childParty) {
    if(!childPartySet.contains(childParty)) {
      childPartySet.add(childParty);
      childParty.addParentParty(this);
    }
  }
  
  public Set<Party> getChildPartySet() {
    return this.childPartySet;
  }
  
  public String toString() {
    String result = "\n"+getDesignation()+"\n";
    for(Party childParty : childPartySet) {
      if(childParty.isPerson()) {
        result += "--"+((Person)childParty).getName()+", ";
      } else {
        result += childParty;
      }
    }
    return result;
  }

  public boolean isPerson() {
    return false;
  }

}