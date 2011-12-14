package pt.ist.processpedia.shared.dto.action.authenticaded;

import java.util.Set;
import pt.ist.processpedia.shared.dto.domain.DataObjectDto;
import pt.ist.processpedia.shared.dto.domain.QueueDto;

public class SaveDraftRequestActionDto extends AuthenticatedActionDto {

  private long parentRequestOid;
  private String subject;
  private String message;
  private Boolean isResponseExpected;
  private Set<DataObjectDto> inputDataObjectDtoSet;
  private Set<QueueDto> queueDtoSet;
  
  public SaveDraftRequestActionDto() {}
  
  public SaveDraftRequestActionDto(String actorOid, long parentRequestOid, String subject, String message, boolean isResponseExpected, Set<QueueDto> queueDtoSet, Set<DataObjectDto> inputDataObjectDtoSet) {
    super(actorOid);
    setParentRequestOid(parentRequestOid);
    setSubject(subject);
    setMessage(message);
    setIsResponseExpected(isResponseExpected);
    setQueueDtoSet(queueDtoSet);
    setInputDataObjectDtoSet(inputDataObjectDtoSet);
  }
  
  public long getParentRequestOid() {
    return parentRequestOid;
  }
  
  public void setParentRequestOid(long parentRequestOid) {
    this.parentRequestOid = parentRequestOid;
  }
  
  public String getSubject() {
    return subject;
  }
  
  public void setSubject(String subject) {
    this.subject = subject;
  }
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public Boolean getIsResponseExpected() {
    return isResponseExpected;
  }
  
  public void setIsResponseExpected(Boolean isResponseExpected) {
    this.isResponseExpected = isResponseExpected;
  }
  
  public Set<QueueDto> getQueueDtoSet() {
    return queueDtoSet;
  }
  
  public void setQueueDtoSet(Set<QueueDto> queueDtoSet) {
    this.queueDtoSet = queueDtoSet;
  }
  
  public Set<DataObjectDto> getInputDataObjectDtoSet() {
    return inputDataObjectDtoSet;
  }
  
  public void setInputDataObjectDtoSet(Set<DataObjectDto> inputDataObjectDtoSet) {
    this.inputDataObjectDtoSet = inputDataObjectDtoSet;
  }
  
}
