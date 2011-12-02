package pt.ist.processpedia.shared.dto.domain;

public class QueueDto extends DomainObjectDtoImpl {

  private String title;

  public QueueDto() {}

  public QueueDto(long oid, String title) {
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
