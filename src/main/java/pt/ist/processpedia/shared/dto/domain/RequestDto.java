package pt.ist.processpedia.shared.dto.domain;

import com.google.gwt.view.client.ProvidesKey;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;

public class RequestDto extends DomainObjectDtoImpl {

  public static final ProvidesKey<RequestDto> KEY_PROVIDER = new ProvidesKey<RequestDto>() {
    public Object getKey(RequestDto requestDto) {
      return requestDto == null ? null : requestDto.getOid();
    }
  };

  private String title;
  private UserDtoImpl initiatorDto;
  private UserDtoImpl executorDto;
  
  private Set<QueueDto> publishedQueueDtoSet;

  private ProcessDto processDto;

  private Date creationTimestamp;
  private Date lastUpdateTimestamp;
  
  public RequestDto() {}

  public RequestDto(long oid, String title, UserDtoImpl initiatorDto, UserDtoImpl executorDto, Set<QueueDto> publishedQueueDtoSet, Date creationTimestamp, Date lastUpdateTimestamp, ProcessDto processDto) {
    super(oid);
    setTitle(title);
    setInitiatorDto(initiatorDto);
    setExecutorDto(executorDto);
    setLastUpdateTimestamp(lastUpdateTimestamp);
    setProcessDto(processDto);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public UserDtoImpl getInitiatorDto() {
    return initiatorDto;
  }

  public void setInitiatorDto(UserDtoImpl initiatorDto) {
    this.initiatorDto = initiatorDto;
  }

  public UserDtoImpl getExecutorDto() {
    return executorDto;
  }

  public void setExecutorDto(UserDtoImpl executorDto) {
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

  public static class CompareRequestTitleName implements Comparator<RequestDto> {
    public int compare(RequestDto a, RequestDto b) {
      if(a == null || a.getTitle()==null)
        return -1;
      if(b == null || b.getTitle()==null)
        return 1;
      else return a.getTitle().compareTo(b.getTitle());
    }
  }

  public static class CompareProcessTitleName implements Comparator<RequestDto> {
    public int compare(RequestDto a, RequestDto b) {
      if(a == null || a.getProcessDto() == null || a.getProcessDto().getTitle() == null)
        return -1;
      if(b == null || b.getProcessDto() == null || a.getProcessDto().getTitle() == null)
        return 1;
      else return a.getProcessDto().getTitle().compareTo(b.getProcessDto().getTitle());
    }
  }

  public static class CompareLastUpdateTimestamp implements Comparator<RequestDto> {
    public int compare(RequestDto a, RequestDto b) {
      if(a == null || a.getLastUpdateTimestamp() == null)
        return -1;
      if(b == null || b.getLastUpdateTimestamp() == null)
        return 1;
      else return a.getLastUpdateTimestamp().compareTo(b.getLastUpdateTimestamp());
    }
  }

  public static class CompareSenderName implements Comparator<RequestDto> {
    public int compare(RequestDto a, RequestDto b) {
      if(a == null || a.getInitiatorDto() == null || a.getInitiatorDto().getName() == null)
        return -1;
      if(b == null || b.getInitiatorDto() == null || a.getInitiatorDto().getName() == null)
        return 1;
      else return a.getInitiatorDto().getName().compareTo(b.getInitiatorDto().getName());
    }
  }


}
