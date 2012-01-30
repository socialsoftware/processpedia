package pt.ist.processpedia.shared;

public enum FolderType {

  INBOX("inbox"),
  DRAFT("draft"),
  HANDLING("handling"),
  PENDING("pending"),
  HANDLED("handled");
  
  private final String folder;
  
  private FolderType(String folder) {
    this.folder = folder;
  }
  
  @Override
  public String toString() {
    return this.folder;
  }
}
