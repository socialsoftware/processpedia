package pt.ist.processpedia.shared.dto.domain;

public class ProcessDtoImpl extends DomainObjectDtoImpl implements ProcessDto {

  private static final long serialVersionUID = 1L;

  private String title;
  private String description;

  public ProcessDtoImpl() {}
  
  public ProcessDto withTitle(String title) {
    setTitle(title);
    return this;
  }
  
  public ProcessDto withDescription(String description) {
    setDescription(description);
    return this;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
}
