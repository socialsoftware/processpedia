package pt.ist.processpedia.shared.dto.domain;

import java.util.Date;
import java.util.Set;

public interface RequestDto extends DomainObjectDto {

  public RequestDto withProcess(ProcessDto process);

  public RequestDto withSubject(String subject);
  public RequestDto withInitialComment(CommentDto comment);

  public RequestDto withInitiator(OperatingPartyDto operatingParty);
  public RequestDto withSender(OperatingPartyDto operatingParty);
  public RequestDto withExecutor(OperatingPartyDto operatingParty);

  public RequestDto withPublishedQueueSet(Set<QueueDto> publishedQueueSet);
  public RequestDto withDataObjectVersionSet(Set<DataObjectVersionDtoImpl> dataObjectVersionDto);

  public RequestDto withCreationTimestamp(Date creationTimestamp);
  public RequestDto withLastUpdateTimestamp(Date lastUpdateTimestamp);

  public ProcessDto getProcess();

  public String getSubject();
  public CommentDto getInitialComment();

  public OperatingPartyDto getInitiator();
  public OperatingPartyDto getSender();
  public OperatingPartyDto getExecutor();

  public Set<QueueDto> getPublishedQueueSet();
  public Set<DataObjectVersionDtoImpl> getDataObjectVersionSet();

  public Date getCreationTimestamp();
  public Date getLastUpdateTimestamp();
}
