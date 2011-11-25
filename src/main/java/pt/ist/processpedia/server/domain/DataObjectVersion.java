package pt.ist.processpedia.server.domain;

import org.joda.time.DateTime;

public abstract class DataObjectVersion extends DataObjectVersion_Base {

  protected void init(String label) {
    setLabel(label);
    setCreationTimestamp(new DateTime());
  }
}
