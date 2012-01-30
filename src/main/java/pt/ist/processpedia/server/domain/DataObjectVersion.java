package pt.ist.processpedia.server.domain;

import org.joda.time.DateTime;

import pt.ist.processpedia.shared.dto.domain.DataObjectType;

public class DataObjectVersion extends DataObjectVersion_Base {

  public DataObjectVersion(DataObjectType dataObjectType, String label, String externalizedValue) {
    setType(dataObjectType);
    setLabelTag(Processpedia.getInstance().getTagManager().getTagForKeyword(label));
    setCreationTimestamp(new DateTime());
  }
}
