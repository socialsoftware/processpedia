package pt.ist.processpedia.shared.dto.domain;

public class ProcessDtoImpl extends DomainObjectDtoImpl implements ProcessDto {

  private static final long serialVersionUID = 1L;

  private String title;
  private String description;

  public ProcessDtoImpl() {}
  
  public ProcessDtoImpl(long oid, String title, String description) {
    super(oid);
    setTitle(title);
    setDescription(description);
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

