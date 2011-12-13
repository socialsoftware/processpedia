package pt.ist.processpedia.shared.dto.domain;

import java.util.Date;
import java.util.Set;

public class RequestDtoImpl extends DomainObjectDtoImpl implements RequestDto {

  private String title;
  private UserDto initiatorDto;
  private UserDto senderDto;
  private UserDto executorDto;

  private Set<QueueDto> publishedQueueDtoSet;

  private ProcessDto processDto;

  private Date creationTimestamp;
  private Date lastUpdateTimestamp;

  public RequestDtoImpl() {}

  public RequestDtoImpl(long oid, String title, UserDtoImpl initiatorDto, UserDtoImpl executorDto, Set<QueueDto> publishedQueueDtoSet, Date creationTimestamp, Date lastUpdateTimestamp, ProcessDto processDto) {
    super(oid);
    setTitle(title);
    setInitiatorDto(initiatorDto);
    setExecutorDto(executorDto);
    setPublishedQueueDtoSet(publishedQueueDtoSet);
    setCreationTimestamp(creationTimestamp);
    setLastUpdateTimestamp(lastUpdateTimestamp);
    setProcessDto(processDto);
  }

  public String getSubject() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public UserDto getInitiatorDto() {
    return initiatorDto;
  }

  public void setInitiatorDto(UserDto initiatorDto) {
    this.initiatorDto = initiatorDto;
  }
  
  public UserDto getSenderDto() {
    return senderDto;
  }
  
  public void setSenderDto(UserDto senderDto) {
    this.senderDto = senderDto;
  }

  public UserDto getExecutorDto() {
    return executorDto;
  }

  public void setExecutorDto(UserDto executorDto) {
    this.executorDto = executorDto;
  }

  public Set<QueueDto> getPublishedQueueDtoSet() {
    return publishedQueueDtoSet;
  }

  public void setPublishedQueueDtoSet(Set<QueueDto> publishedQueueDtoSet) {
    this.publishedQueueDtoSet = publishedQueueDtoSet;
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

  public ProcessDto getProcessDto() {
    return processDto;
  }

  public void setProcessDto(ProcessDto processDto) {
    this.processDto = processDto;
  }
}
