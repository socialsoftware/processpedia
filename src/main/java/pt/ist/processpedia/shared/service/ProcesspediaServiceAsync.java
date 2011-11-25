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

package pt.ist.processpedia.shared.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import pt.ist.processpedia.shared.dto.action.*;
import pt.ist.processpedia.shared.dto.action.authenticaded.*;
import pt.ist.processpedia.shared.dto.response.*;

public interface ProcesspediaServiceAsync {

  void loginUser(LoginUserActionDto loginUserActionDto, AsyncCallback<LoginUserResponseDto> callback);

  void signupUser(SignupUserActionDto signupUserActionDto, AsyncCallback<SignupUserResponseDto> callback);

  void getUserSettings(GetUserSettingsActionDto getUserSettingsActionDto, AsyncCallback<GetUserSettingsResponseDto> callback);

  void updateUserSettings(UpdateUserSettingsActionDto updateUserSettingsActionDto, AsyncCallback<UpdateUserSettingsResponseDto> callback);

  void createProcess(CreateProcessActionDto createProcessActionDto, AsyncCallback<CreateProcessResponseDto> callback);

  void activateAccount(ActivateAccountActionDto activateAccountActionDto, AsyncCallback<ActivateAccountResponseDto> callback);

  void getFolderList(GetFolderListActionDto getFolderListActionDto, AsyncCallback<GetFolderListResponseDto> callback);

  void getFolderContents(GetFolderContentsActionDto getFolderContentsActionDto, AsyncCallback<GetFolderContentsResponseDto> callback);

  void getRequest(GetRequestActionDto getRequestActionDto, AsyncCallback<GetRequestResponseDto> callback);

  void getQueueSet(GetQueueSetActionDto getQueueSetActionDto, AsyncCallback<GetQueueSetResponseDto> callback);

  void search(SearchActionDto searchActionDto, AsyncCallback<SearchResponseDto> callback);
}
