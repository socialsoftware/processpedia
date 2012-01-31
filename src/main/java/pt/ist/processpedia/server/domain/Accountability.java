package pt.ist.processpedia.server.domain;

import org.joda.time.DateTime;

public class Accountability extends Accountability_Base {

  public Accountability(AccountabilityType type, DateTime startTime) {
    setType(type);
    setEffectivePeriod(new EffectivePeriod(startTime));
  }

  public boolean isValidOn(DateTime timestamp) {
    return getEffectivePeriod().isValidOn(timestamp);
  }
}
