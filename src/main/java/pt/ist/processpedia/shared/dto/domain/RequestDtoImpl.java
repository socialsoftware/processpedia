package pt.ist.processpedia.shared.dto.domain;

import java.util.Date;
import java.util.Set;

public class RequestDtoImpl extends DomainObjectDtoImpl implements RequestDto {

  private static final long serialVersionUID = 1L;

  private String subject;
  private CommentDto initialComment;

  private OperatingPartyDto originalInitiator;
  private OperatingPartyDto initiator;
  private OperatingPartyDto executor;

  private ProcessDto process;

  private Date creationTimestamp;
  private Date lastUpdateTimestamp;

  private Set<QueueDto> publishedQueueSet;
  private Set<DataObjectVersionDtoImpl> dataObjectVersionSet;

  public RequestDtoImpl() {}
  
  public RequestDtoImpl(long oid, OperatingPartyDto originalInitiator, OperatingPartyDto initiator) {
    super(oid);
    setSubject(subject);
    setOriginalInitiator(originalInitiator);
    setInitiator(initiator);
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String title) {
    this.subject = title;
  }

  public CommentDto getInitialComment() {
    return initialComment;
  }

  public void setInitialComment(CommentDto initialComment) {
    this.initialComment = initialComment;
  }

  public OperatingPartyDto getOriginalInitiator() {
    return originalInitiator;
  }

  public void setOriginalInitiator(OperatingPartyDto originalInitiator) {
    this.originalInitiator = originalInitiator;
  }
  
  public OperatingPartyDto getInitiator() {
    return initiator;
  }

  public void setInitiator(OperatingPartyDto initiator) {
    this.initiator = initiator;
  }
  
  public OperatingPartyDto getExecutor() {
    return executor;
  }

  public void setExecutor(OperatingPartyDto executor) {
    this.executor = executor;
  }
  
  public Date getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp(Date creationTimestamp) {
    this.creationTimestamp = creationTimestamp;
  }

  public Date getLastUpdateTimestamp() {
    return lastUpdateTimestamp;
  }

  public void setLastUpdateTimestamp(Date lastUpdateTimestamp) {
    this.lastUpdateTimestamp = lastUpdateTimestamp;
  }

  public ProcessDto getProcess() {
    return process;
  }

  public void setProcess(ProcessDto process) {
    this.process = process;
  }

  public Set<QueueDto> getPublishedQueueSet() {
    return publishedQueueSet;
  }

  public void setPublishedQueueSet(Set<QueueDto> publishedQueueSet) {
    this.publishedQueueSet = publishedQueueSet;
  }

  public Set<DataObjectVersionDtoImpl> getDataObjectVersionSet() {
    return dataObjectVersionSet;
  }
  
  public void setDataObjectVersionSet(Set<DataObjectVersionDtoImpl> dataObjectVersionDto) {
    this.dataObjectVersionSet = dataObjectVersionDto;
  }
}
