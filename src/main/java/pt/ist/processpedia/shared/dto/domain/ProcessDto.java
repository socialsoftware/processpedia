package pt.ist.processpedia.shared.dto.domain;

public class ProcessDto extends DomainObjectDto {

  private String title;

  public ProcessDto() {}

  public ProcessDto(long oid, String title) {
    super(oid);
    setTitle(title);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
