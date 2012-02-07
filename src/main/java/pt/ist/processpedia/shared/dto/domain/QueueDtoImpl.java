package pt.ist.processpedia.shared.dto.domain;

public class QueueDtoImpl extends DomainObjectDtoImpl implements QueueDto {

  private static final long serialVersionUID = 1L;

  private String title;

  public QueueDtoImpl() {}

  public QueueDtoImpl(long oid, String title) {
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
