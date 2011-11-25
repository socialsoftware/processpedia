package pt.ist.processpedia.server.domain;

import java.util.Set;

public class DataObject extends DataObject_Base {

  public DataObject(String label, Set<DataObjectVersion> childDataObjectVersionSet) {
    createNewComposedDataObjectVersion(label, childDataObjectVersionSet);
  }

  public DataObject(AtomicDataObjectVersion.DataObjectType type, String label, String externalizedValue) {
    createNewAtomicDataObjectVersion(type, label, externalizedValue);
  }

  public ComposedDataObjectVersion createNewComposedDataObjectVersion(String label, Set<DataObjectVersion> childDataObjectVersionSet) {
    ComposedDataObjectVersion newVersion = new ComposedDataObjectVersion(label);
    for(DataObjectVersion childDataObjectVersion : childDataObjectVersionSet) {
      newVersion.addChildDataObjectVersion(childDataObjectVersion);
    }
    newVersion.setPreviousVersion(getLatestVersion());
    addVersion(newVersion);
    setLatestVersion(newVersion);
    return newVersion;
  }

  public DataObjectVersion createNewAtomicDataObjectVersion(AtomicDataObjectVersion.DataObjectType type, String label, String externalizedValue) {
    AtomicDataObjectVersion newVersion = new AtomicDataObjectVersion(type, label, externalizedValue);
    newVersion.setPreviousVersion(getLatestVersion());
    addVersion(newVersion);
    setLatestVersion(newVersion);
    return newVersion;
  }

}
