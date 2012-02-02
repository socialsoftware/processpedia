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
import pt.ist.processpedia.shared.dto.domain.DataObjectDtoImpl;
import pt.ist.processpedia.shared.dto.domain.DataObjectVersionDtoImpl;
import pt.ist.processpedia.shared.dto.domain.QueueDtoImpl;
import java.util.Set;

public class CreateRequestActionDto extends AuthenticatedActionDto implements IsSerializable {

  private long parentRequestOid;
  private String title;
  private String description;
  private Boolean isResponseExpected;
  private Set<DataObjectVersionDtoImpl> inputDataObjectVersionDtoSet;
  private Set<QueueDtoImpl> queueDtoSet;

  public CreateRequestActionDto() {}

  public CreateRequestActionDto(String actorOid, long parentRequestOid, String title, String description, Boolean isResponseExpected, Set<QueueDtoImpl> queueDtoSet, Set<DataObjectDtoImpl> inputDataObjectDtoSet) {
    super(actorOid);
    setParentRequestOid(parentRequestOid);
    setTitle(title);
    setDescription(description);
    setResponseExpected(isResponseExpected);
    setQueueDtoSet(queueDtoSet);
    setInputDataObjectVersionDtoSet(inputDataObjectVersionDtoSet);
  }
  
  public long getParentRequestOid() {
    return parentRequestOid;
  }
  
  public void setParentRequestOid(long parentRequestOid) {
    this.parentRequestOid = parentRequestOid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public Set<QueueDtoImpl> getQueueDtoSet() {
    return queueDtoSet;
  }

  public void setQueueDtoSet(Set<QueueDtoImpl> queueDtoSet) {
    this.queueDtoSet = queueDtoSet;
  }

  public Set<DataObjectVersionDtoImpl> getInputDataObjectVersionDtoSet() {
    return inputDataObjectVersionDtoSet;
  }

  public void setInputDataObjectVersionDtoSet(Set<DataObjectVersionDtoImpl> inputDataObjectVersionDtoSet) {
    this.inputDataObjectVersionDtoSet = inputDataObjectVersionDtoSet;
  }
}