package pt.ist.processpedia.shared.dto.domain;

import java.util.Date;
import java.util.Set;

public interface RequestDto extends DomainObjectDto {

  public ProcessDto getProcessDto();
  
  public String getSubject();
  public UserDto getInitiatorDto();
  public UserDto getSenderDto();
  public UserDto getExecutorDto();
  
  public Set<QueueDto> getPublishedQueueDtoSet();
  public Date getCreationTimestamp();
  public Date getLastUpdateTimestamp();
  
}
