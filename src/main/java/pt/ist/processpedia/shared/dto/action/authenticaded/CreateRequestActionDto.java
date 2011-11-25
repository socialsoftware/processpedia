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
import pt.ist.processpedia.shared.dto.domain.DataObjectDto;
import pt.ist.processpedia.shared.dto.domain.QueueDto;
import java.util.Set;

public class CreateRequestActionDto extends AuthenticatedActionDto implements IsSerializable {

  private String title;
  private String description;
  private Boolean isResponseExpected;
  private Set<DataObjectDto> inputDataObjectDtoSet;
  private Set<QueueDto> queueDtoSet;

  public CreateRequestActionDto() {}

  public CreateRequestActionDto(String actorOid, String title, String description, Boolean isResponseExpected, Set<QueueDto> queueDtoSet, Set<DataObjectDto> inputDataObjectDtoSet) {
    super(actorOid);
    setTitle(title);
    setDescription(description);
    setResponseExpected(isResponseExpected);
    setQueueDtoSet(queueDtoSet);
    setInputDataObjectDtoSet(inputDataObjectDtoSet);
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