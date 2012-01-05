package pt.ist.processpedia.client.cache;

import pt.ist.processpedia.client.storage.BrowserStorage;
import pt.ist.processpedia.client.storage.BrowserStorageImpl;
import pt.ist.processpedia.shared.dto.action.ActivateAccountActionDto;
import pt.ist.processpedia.shared.dto.action.LoginUserActionDto;
import pt.ist.processpedia.shared.dto.action.SignupUserActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.CreateProcessActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.CreateRequestActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetFolderContentsActionDto;
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
import pt.ist.processpedia.shared.dto.response.GetFolderContentsResponseDto;
import pt.ist.processpedia.shared.dto.response.GetFolderListResponseDto;
import pt.ist.processpedia.shared.dto.response.GetQueueSetResponseDto;
import pt.ist.processpedia.shared.dto.response.GetRequestRecommendationResponseDto;
import pt.ist.processpedia.shared.dto.response.GetRequestResponseDto;
import pt.ist.processpedia.shared.dto.response.GetUserSettingsResponseDto;
import pt.ist.processpedia.shared.dto.response.LoginUserResponseDto;
import pt.ist.processpedia.shared.dto.response.SearchResponseDto;
import pt.ist.processpedia.shared.dto.response.SignupUserResponseDto;
import pt.ist.processpedia.shared.dto.response.UpdateUserSettingsResponseDto;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ProcesspediaCacheServiceImpl implements ProcesspediaCacheService {

  private static final ProcesspediaCacheService INSTANCE = new ProcesspediaCacheServiceImpl();
  
  private static final BrowserStorage browserStorage = BrowserStorageImpl.getInstance();
  
  @Override
  public void loginUser(LoginUserActionDto loginUserActionDto, AsyncCallback<LoginUserResponseDto> callback) {
    callback.onFailure(new Exception());
  }

  @Override
  public void signupUser(SignupUserActionDto signupUserActionDto, AsyncCallback<SignupUserResponseDto> callback) {
    callback.onFailure(new Exception());
  }

  @Override
  public void getUserSettings(GetUserSettingsActionDto getUserSettingsActionDto, AsyncCallback<GetUserSettingsResponseDto> callback) {
    callback.onFailure(new Exception());
  }

  @Override
  public void updateUserSettings(UpdateUserSettingsActionDto updateUserSettingsActionDto, AsyncCallback<UpdateUserSettingsResponseDto> callback) {
    callback.onFailure(new Exception());
  }

  @Override
  public void createProcess(CreateProcessActionDto createProcessActionDto, AsyncCallback<CreateProcessResponseDto> callback) {
    callback.onFailure(new Exception());
  }

  public void activateAccount(ActivateAccountActionDto activateAccountActionDto, AsyncCallback<ActivateAccountResponseDto> callback) {
    callback.onFailure(new Exception());
  }

  public void getFolderList(GetFolderListActionDto getFolderListActionDto, AsyncCallback<GetFolderListResponseDto> callback) {
    callback.onFailure(new Exception());
  }

  public void getFolderContents(GetFolderContentsActionDto getFolderContentsActionDto, AsyncCallback<GetFolderContentsResponseDto> callback) {
    callback.onFailure(new Exception());
  }

  public void getRequest(GetRequestActionDto getRequestActionDto, AsyncCallback<GetRequestResponseDto> callback) {
    callback.onFailure(new Exception());
  }

  public void getQueueSet(GetQueueSetActionDto getQueueSetActionDto, AsyncCallback<GetQueueSetResponseDto> callback) {
    callback.onFailure(new Exception());
  }

  public void search(SearchActionDto searchActionDto, AsyncCallback<SearchResponseDto> callback) {
    callback.onFailure(new Exception());
  }

  public void getRequestRecommendation(GetRequestRecommendationAction getRequestRecommendationAction, AsyncCallback<GetRequestRecommendationResponseDto> callback) {
    callback.onFailure(new Exception());
  }

  public void createRequest(CreateRequestActionDto createRequestActionDto, AsyncCallback<CreateRequestResponseDto> callback) {
    callback.onFailure(new Exception());
  }

  public void saveDraftRequest(SaveDraftRequestActionDto saveDraftRequestActionDto, AsyncCallback<SaveDraftRequestActionDto> callback) {
    callback.onFailure(new Exception());
  }

  public void clear() {
    browserStorage.clear();
  }

  public static ProcesspediaCacheService getInstance() {
    return INSTANCE;
  }
}
