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

public class CreateProcessActionDto extends AuthenticatedActionDto implements IsSerializable {

  private String processTitle;
  private String processDescription;
  private CreateRequestActionDto createRequestActionDto;

  public CreateProcessActionDto() {}

  public CreateProcessActionDto(String actorId, String processTitle, String processDescription, CreateRequestActionDto createRequestActionDto) {
    super(actorId);
    setProcessTitle(processTitle);
    setProcessDescription(processDescription);
    setCreateRequestActionDto(createRequestActionDto);
  }

  public String getProcessTitle() {
    return processTitle;
  }

  public void setProcessTitle(String processTitle) {
    this.processTitle = processTitle;
  }

  public String getProcessDescription() {
    return processDescription;
  }

  public void setProcessDescription(String processDescription) {
    this.processDescription = processDescription;
  }

  public CreateRequestActionDto getCreateRequestActionDto() {
    return createRequestActionDto;
  }

  public void setCreateRequestActionDto(CreateRequestActionDto createRequestActionDto) {
    this.createRequestActionDto = createRequestActionDto;
  }
}