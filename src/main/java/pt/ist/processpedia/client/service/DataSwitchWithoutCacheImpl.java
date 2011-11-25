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

package pt.ist.processpedia.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import pt.ist.processpedia.shared.dto.action.*;
import pt.ist.processpedia.shared.dto.action.authenticaded.*;
import pt.ist.processpedia.shared.dto.response.*;
import pt.ist.processpedia.shared.service.ProcesspediaService;
import pt.ist.processpedia.shared.service.ProcesspediaServiceAsync;

public class DataSwitchWithoutCacheImpl implements DataSwitch {

  private ProcesspediaServiceAsync realService = (ProcesspediaServiceAsync)GWT.create(ProcesspediaService.class);

  public void loginUser(LoginUserActionDto loginUserActionDto, AsyncCallback<LoginUserResponseDto> callback) {
    realService.loginUser(loginUserActionDto, callback);
  }

  public void signupUser(SignupUserActionDto signupUserActionDto, AsyncCallback<SignupUserResponseDto> callback) {
    realService.signupUser(signupUserActionDto, callback);
  }

  public void getUserSettings(GetUserSettingsActionDto getUserSettingsActionDto, AsyncCallback<GetUserSettingsResponseDto> callback) {
    realService.getUserSettings(getUserSettingsActionDto, callback);
  }

  public void updateUserSettings(UpdateUserSettingsActionDto updateUserSettingsActionDto, AsyncCallback<UpdateUserSettingsResponseDto> callback) {
    realService.updateUserSettings(updateUserSettingsActionDto, callback);
  }

  public void createProcess(CreateProcessActionDto createProcessActionDto, AsyncCallback<CreateProcessResponseDto> callback) {
    realService.createProcess(createProcessActionDto, callback);
  }

  public void activateAccount(ActivateAccountActionDto activateAccountActionDto, AsyncCallback<ActivateAccountResponseDto> callback) {
    realService.activateAccount(activateAccountActionDto, callback);
  }

  public void getFolderList(GetFolderListActionDto getFolderListActionDto, AsyncCallback<GetFolderListResponseDto> callback) {
    realService.getFolderList(getFolderListActionDto, callback);
  }

  public void getFolderContents(GetFolderContentsActionDto getFolderContentsActionDto, AsyncCallback<GetFolderContentsResponseDto> callback) {
    realService.getFolderContents(getFolderContentsActionDto, callback);
  }

  public void getRequest(GetRequestActionDto getRequestActionDto, AsyncCallback<GetRequestResponseDto> callback) {
    realService.getRequest(getRequestActionDto, callback);
  }

  public void getQueueSet(GetQueueSetActionDto getQueueSetActionDto, AsyncCallback<GetQueueSetResponseDto> callback) {
    realService.getQueueSet(getQueueSetActionDto, callback);
  }

  public void search(SearchActionDto searchActionDto, AsyncCallback<SearchResponseDto> callback) {
    realService.search(searchActionDto, callback);
  }
}
