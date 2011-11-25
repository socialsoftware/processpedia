package pt.ist.processpedia.server.domain;

public class AtomicDataObjectVersion extends AtomicDataObjectVersion_Base {

  public enum DataObjectType { STRING, FILE }

  public AtomicDataObjectVersion(DataObjectType type, String label, String externalizedValue) {
    init(label);
    setType(type);
    setExternalizedValue(externalizedValue);
  }
}
