package pt.ist.processpedia.shared.dto.domain;

import java.util.Date;
import java.util.Set;

public interface RequestDto extends DomainObjectDto {

  public ProcessDto getProcess();

  public String getSubject();
  public CommentDto getInitialComment();

  public OperatingPartyDto getOriginalInitiator();
  public OperatingPartyDto getInitiator();
  public OperatingPartyDto getExecutor();

  public Set<QueueDto> getPublishedQueueSet();
  public Set<DataObjectVersionDtoImpl> getDataObjectVersionSet();

  public Date getCreationTimestamp();
  public Date getLastUpdateTimestamp();
}
