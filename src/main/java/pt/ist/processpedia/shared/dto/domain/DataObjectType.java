package pt.ist.processpedia.shared.dto.domain;

public enum DataObjectType {

  COMPOSED("composed"),
  STRING("string"),
  FILE("file"),
  DATE("date"),
  DATETIME("datetime"),
  MONEY("money"),
  NUMBER("number");
  
  private final String type;
  
  private DataObjectType(String type) {
    this.type = type;
  }
  
  @Override
  public String toString() {
    return this.type;
  }
}
