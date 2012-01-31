package pt.ist.processpedia.server.domain;

import org.joda.time.DateTime;

public class RolePlay extends RolePlay_Base {

  public RolePlay(Party party, RoleAssociation roleAssociation, Accountability accountability) {
    setParty(party);
    setRoleAssociation(roleAssociation);
    setAccountability(accountability);
  }
  
  /**
   * Verifies if a role play is valid on a particular timestamp. 
   * @param timestamp the timestamp in which the validity is to be verified
   * @return true if the role play is active on the given timestamp, false if not
   */
  public boolean isValidOn(DateTime timestamp) {
    if(getEffectivePeriod() == null) {
      return getAccountability().isValidOn(timestamp);
    } else {
      return getEffectivePeriod().isValidOn(timestamp);
    }
  }
  
  /**
   * Terminates the role play, even when the accountability is still valid (e.g. drop-outs, etc...)
   * @param endTime the timestamp until the 
   */
  public void terminate(DateTime endTime) {
    DateTime startTime = getAccountability().getEffectivePeriod().getStartTime();
    EffectivePeriod effectivePeriod = new EffectivePeriod(startTime);
    effectivePeriod.terminate(endTime);
    setEffectivePeriod(effectivePeriod);
  }
}
