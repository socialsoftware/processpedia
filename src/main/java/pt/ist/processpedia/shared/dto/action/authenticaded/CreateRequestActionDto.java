/**
 * Copyright 2011 ESW Software Engineering Group
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/

package pt.ist.processpedia.shared.dto.action.authenticaded;

import com.google.gwt.user.client.rpc.IsSerializable;
import pt.ist.processpedia.shared.dto.domain.DataObjectVersionDto;
import pt.ist.processpedia.shared.dto.domain.QueueDto;
import java.util.Set;

public class CreateRequestActionDto extends AuthenticatedActionDto implements IsSerializable {

  private static final long serialVersionUID = 1L;
  
  private long parentRequestOid;
  private String subject;
  private String description;
  private Boolean isResponseExpected;
  private Set<DataObjectVersionDto> inputDataObjectVersionSet;
  private Set<QueueDto> queueSet;

  public CreateRequestActionDto() {}

  public CreateRequestActionDto(String actorOid, long parentRequestOid, String subject, String description, Boolean isResponseExpected, Set<QueueDto> queueSet, Set<DataObjectVersionDto> inputDataObjectVersionSet) {
    super(actorOid);
    setParentRequestOid(parentRequestOid);
    setSubject(subject);
    setDescription(description);
    setResponseExpected(isResponseExpected);
    setQueueSet(queueSet);
    setInputDataObjectVersionSet(inputDataObjectVersionSet);
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getResponseExpected() {
    return isResponseExpected;
  }

  public void setResponseExpected(Boolean isResponseExpected) {
    this.isResponseExpected = isResponseExpected;
  }

  public Set<QueueDto> getQueueSet() {
    return queueSet;
  }

  public void setQueueSet(Set<QueueDto> queueSet) {
    this.queueSet = queueSet;
  }

  public Set<DataObjectVersionDto> getInputDataObjectVersionSet() {
    return inputDataObjectVersionSet;
  }

  public void setInputDataObjectVersionSet(Set<DataObjectVersionDto> inputDataObjectVersionSet) {
    this.inputDataObjectVersionSet = inputDataObjectVersionSet;
  }
}