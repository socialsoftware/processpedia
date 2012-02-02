package pt.ist.processpedia.server.domain;

import pt.ist.processpedia.shared.domain.DataObjectType;

public class DataObject extends DataObject_Base {

  public DataObject(DataObjectType type, String label) {
    this(type, label, null);
  }

  public DataObject(DataObjectType type, String label, String externalizedValue) {
    createNewDataObjectVersion(type, label, externalizedValue);
  }

  public DataObjectVersion createNewDataObjectVersion(DataObjectType type, String label, String externalizedValue) {
    DataObjectVersion newVersion = new DataObjectVersion(type, label, externalizedValue);
    newVersion.setPreviousVersion(getLastVersion());
    addVersion(newVersion);
    setLastVersion(newVersion);
    return newVersion;
  }
}
