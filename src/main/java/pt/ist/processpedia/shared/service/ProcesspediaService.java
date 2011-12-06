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

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import pt.ist.processpedia.shared.dto.action.*;
import pt.ist.processpedia.shared.dto.action.authenticaded.*;
import pt.ist.processpedia.shared.dto.response.*;
import pt.ist.processpedia.shared.exception.ProcesspediaException;

@RemoteServiceRelativePath("processpedia")
public interface ProcesspediaService extends RemoteService {

  LoginUserResponseDto loginUser(LoginUserActionDto loginUserActionDto) throws ProcesspediaException;

  SignupUserResponseDto signupUser(SignupUserActionDto signupUserActionDto) throws ProcesspediaException;

  GetUserSettingsResponseDto getUserSettings(GetUserSettingsActionDto getUserSettingsActionDto) throws ProcesspediaException;

  UpdateUserSettingsResponseDto updateUserSettings(UpdateUserSettingsActionDto updateUserSettingsActionDto) throws ProcesspediaException;

  CreateProcessResponseDto createProcess(CreateProcessActionDto createProcessActionDto) throws ProcesspediaException;

  ActivateAccountResponseDto activateAccount(ActivateAccountActionDto activateAccountActionDto) throws ProcesspediaException;

  GetFolderListResponseDto getFolderList(GetFolderListActionDto getFolderListActionDto) throws ProcesspediaException;

  GetFolderContentsResponseDto getFolderContents(GetFolderContentsActionDto getFolderContentsActionDto) throws ProcesspediaException;

  GetRequestResponseDto getRequest(GetRequestActionDto getRequestActionDto) throws ProcesspediaException;

  GetQueueSetResponseDto getQueueSet(GetQueueSetActionDto getQueueSetActionDto) throws ProcesspediaException;

  SearchResponseDto search(SearchActionDto searchActionDto) throws ProcesspediaException;

  GetRequestRecommendationResponseDto getRequestRecommendation(GetRequestRecommendationAction getRequestRecommendationAction) throws ProcesspediaException;
  
  CreateRequestResponseDto createRequest(CreateRequestActionDto createRequestActionDto) throws ProcesspediaException;
  
}

