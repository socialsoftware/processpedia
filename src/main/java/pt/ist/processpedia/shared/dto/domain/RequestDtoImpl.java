package pt.ist.processpedia.shared.dto.domain;

import java.util.Date;
import java.util.Set;

public class RequestDtoImpl extends DomainObjectDtoImpl implements RequestDto {

  private static final long serialVersionUID = 1L;

  private String subject;
  private CommentDto initialComment;

  private OperatingPartyDto initiator;
  private OperatingPartyDto sender;
  private OperatingPartyDto executor;

  private ProcessDto process;

  private Date creationTimestamp;
  private Date lastUpdateTimestamp;

  private Set<QueueDto> publishedQueueSet;
  private Set<DataObjectVersionDtoImpl> dataObjectVersionSet;

  public RequestDtoImpl() {}
  
  public RequestDto withSubject(String subject) {
    setSubject(subject);
    return this;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String title) {
    this.subject = title;
  }

  public RequestDto withInitialComment(CommentDto initialComment) {
    setInitialComment(initialComment);
    return this;
  }

  public CommentDto getInitialComment() {
    return initialComment;
  }

  public void setInitialComment(CommentDto initialComment) {
    this.initialComment = initialComment;
  }

  public RequestDto withInitiator(OperatingPartyDto initiator) {
    setInitiator(initiator);
    return this;
  }

  public OperatingPartyDto getInitiator() {
    return initiator;
  }

  public void setInitiator(OperatingPartyDto initiator) {
    this.initiator = initiator;
  }
  
  public RequestDto withSender(OperatingPartyDto sender) {
    setSender(sender);
    return this;
  }
  
  public OperatingPartyDto getSender() {
    return sender;
  }
  
  public void setSender(OperatingPartyDto sender) {
    this.sender = sender;
  }
  
  public RequestDto withExecutor(OperatingPartyDto executor) {
    this.executor = executor;
    return this;
  }
  
  public OperatingPartyDto getExecutor() {
    return executor;
  }

  public void setExecutor(OperatingPartyDto executor) {
    this.executor = executor;
  }

  public RequestDto withCreationTimestamp(Date creationTimestamp) {
    setCreationTimestamp(creationTimestamp);
    return this;
  }
  
  public Date getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp(Date creationTimestamp) {
    this.creationTimestamp = creationTimestamp;
  }
  
  public RequestDto withLastUpdateTimestamp(Date lastUpdateTimestamp) {
    setLastUpdateTimestamp(lastUpdateTimestamp);
    return this;
  }

  public Date getLastUpdateTimestamp() {
    return lastUpdateTimestamp;
  }

  public void setLastUpdateTimestamp(Date lastUpdateTimestamp) {
    this.lastUpdateTimestamp = lastUpdateTimestamp;
  }
  
  public RequestDto withProcess(ProcessDto process) {
    setProcess(process);
    return this;
  }

  public ProcessDto getProcess() {
    return process;
  }

  public void setProcess(ProcessDto process) {
    this.process = process;
  }
  
  public RequestDto withPublishedQueueSet(Set<QueueDto> publishedQueueSet) {
    setPublishedQueueSet(publishedQueueSet);
    return this;
  }
  
  public Set<QueueDto> getPublishedQueueSet() {
    return publishedQueueSet;
  }

  public void setPublishedQueueSet(Set<QueueDto> publishedQueueSet) {
    this.publishedQueueSet = publishedQueueSet;
  }

  public RequestDto withDataObjectVersionSet(Set<DataObjectVersionDtoImpl> dataObjectVersionSet) {
    setDataObjectVersionSet(dataObjectVersionSet);
    return this;
  }

  public Set<DataObjectVersionDtoImpl> getDataObjectVersionSet() {
    return dataObjectVersionSet;
  }
  
  public void setDataObjectVersionSet(Set<DataObjectVersionDtoImpl> dataObjectVersionDto) {
    this.dataObjectVersionSet = dataObjectVersionDto;
  }
}
