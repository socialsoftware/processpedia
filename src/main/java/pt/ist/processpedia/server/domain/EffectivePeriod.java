package pt.ist.processpedia.server.domain;

import org.joda.time.DateTime;

public class EffectivePeriod extends EffectivePeriod_Base {

  public EffectivePeriod(DateTime startTime) {
    setStartTime(startTime);
  }
  
  public void terminate(DateTime endTime) {
    setEndTime(endTime);
  }
  
  public boolean isActiveOn(DateTime timestamp) {
    return timestamp!=null && timestamp.isAfter(getStartTime()) && (getEndTime()==null || timestamp.isBefore(getEndTime()));
  } 
}
