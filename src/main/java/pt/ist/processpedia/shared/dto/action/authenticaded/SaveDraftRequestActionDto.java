package pt.ist.processpedia.shared.dto.action.authenticaded;

import java.util.Set;
import pt.ist.processpedia.shared.dto.domain.DataObjectDtoImpl;
import pt.ist.processpedia.shared.dto.domain.QueueDtoImpl;

public class SaveDraftRequestActionDto extends AuthenticatedActionDto {

  private long parentRequestOid;
  private String subject;
  private String message;
  private Boolean isResponseExpected;
  private Set<DataObjectDtoImpl> inputDataObjectDtoSet;
  private Set<QueueDtoImpl> queueDtoSet;
  
  public SaveDraftRequestActionDto() {}
  
  public SaveDraftRequestActionDto(String actorOid, long parentRequestOid, String subject, String message, boolean isResponseExpected, Set<QueueDtoImpl> queueDtoSet, Set<DataObjectDtoImpl> inputDataObjectDtoSet) {
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
  
  public Set<QueueDtoImpl> getQueueDtoSet() {
    return queueDtoSet;
  }
  
  public void setQueueDtoSet(Set<QueueDtoImpl> queueDtoSet) {
    this.queueDtoSet = queueDtoSet;
  }
  
  public Set<DataObjectDtoImpl> getInputDataObjectDtoSet() {
    return inputDataObjectDtoSet;
  }
  
  public void setInputDataObjectDtoSet(Set<DataObjectDtoImpl> inputDataObjectDtoSet) {
    this.inputDataObjectDtoSet = inputDataObjectDtoSet;
  }
  
}
