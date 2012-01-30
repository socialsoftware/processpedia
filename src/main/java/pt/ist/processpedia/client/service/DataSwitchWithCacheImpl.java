package pt.ist.processpedia.client.service;

import pt.ist.processpedia.client.cache.ProcesspediaCacheService;
import pt.ist.processpedia.client.cache.ProcesspediaCacheServiceImpl;
import pt.ist.processpedia.shared.dto.action.ActivateAccountActionDto;
import pt.ist.processpedia.shared.dto.action.LoginUserActionDto;
import pt.ist.processpedia.shared.dto.action.SignupUserActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.CreateProcessActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.CreateRequestActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetFolderRequestSetActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetFolderListActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetQueueSetActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetRequestActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetRequestRecommendationAction;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetUserSettingsActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.SaveDraftRequestActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.SearchActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.UpdateUserSettingsActionDto;
import pt.ist.processpedia.shared.dto.response.ActivateAccountResponseDto;
import pt.ist.processpedia.shared.dto.response.CreateProcessResponseDto;
import pt.ist.processpedia.shared.dto.response.CreateRequestResponseDto;
import pt.ist.processpedia.shared.dto.response.GetFolderRequestSetResponseDto;
import pt.ist.processpedia.shared.dto.response.GetFolderListResponseDto;
import pt.ist.processpedia.shared.dto.response.GetQueueSetResponseDto;
import pt.ist.processpedia.shared.dto.response.GetRequestRecommendationResponseDto;
import pt.ist.processpedia.shared.dto.response.GetRequestResponseDto;
import pt.ist.processpedia.shared.dto.response.GetUserSettingsResponseDto;
import pt.ist.processpedia.shared.dto.response.LoginUserResponseDto;
import pt.ist.processpedia.shared.dto.response.SearchResponseDto;
import pt.ist.processpedia.shared.dto.response.SignupUserResponseDto;
import pt.ist.processpedia.shared.dto.response.UpdateUserSettingsResponseDto;
import pt.ist.processpedia.shared.service.ProcesspediaService;
import pt.ist.processpedia.shared.service.ProcesspediaServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class DataSwitchWithCacheImpl implements DataSwitch {

  private ProcesspediaServiceAsync realService = (ProcesspediaServiceAsync)GWT.create(ProcesspediaService.class);
  private ProcesspediaCacheService cacheService = ProcesspediaCacheServiceImpl.getInstance();

  public void loginUser(final LoginUserActionDto loginUserActionDto, final AsyncCallback<LoginUserResponseDto> callback) {
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

  public void getFolderList(final GetFolderListActionDto getFolderListActionDto, final AsyncCallback<GetFolderListResponseDto> callback) {
    realService.getFolderList(getFolderListActionDto, callback);
  }

  public void getFolderContents(GetFolderRequestSetActionDto getFolderContentsActionDto, AsyncCallback<GetFolderRequestSetResponseDto> callback) {
    realService.getFolderContents(getFolderContentsActionDto, callback);
  }

  public void getRequest(GetRequestActionDto getRequestActionDto, AsyncCallback<GetRequestResponseDto> callback) {
    realService.getRequest(getRequestActionDto, callback);
  }

  public void getQueueSet(GetQueueSetActionDto getQueueSetActionDto, AsyncCallback<GetQueueSetResponseDto> callback) {
    realService.getQueueSet(getQueueSetActionDto, callback);
  }

  public void search(SearchActionDto searchActionDto, AsyncCallback<SearchResponseDto> callback) {
    cacheService.search(searchActionDto, callback);
  }

  public void getRequestRecommendation(GetRequestRecommendationAction getRequestRecommendationAction, AsyncCallback<GetRequestRecommendationResponseDto> callback) {
    realService.getRequestRecommendation(getRequestRecommendationAction, callback);
  }

  public void createRequest(CreateRequestActionDto createRequestActionDto, AsyncCallback<CreateRequestResponseDto> callback) {
    realService.createRequest(createRequestActionDto, callback);
  }

  public void saveDraftRequest(SaveDraftRequestActionDto saveDraftRequestActionDto, AsyncCallback<SaveDraftRequestActionDto> callback) {
    realService.saveDraftRequest(saveDraftRequestActionDto, callback);
  }
}
