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

package pt.ist.processpedia.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.client.place.SettingsPlace;
import pt.ist.processpedia.client.view.home.content.settings.SettingsView;
import pt.ist.processpedia.client.view.home.content.splash.LoadingMessageView;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetUserSettingsActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.UpdateUserSettingsActionDto;
import pt.ist.processpedia.shared.dto.response.GetUserSettingsResponseDto;
import pt.ist.processpedia.shared.dto.response.UpdateUserSettingsResponseDto;
import pt.ist.processpedia.shared.exception.PasswordsDoNotMatchException;

public class SettingsActivity extends ProcesspediaActivity<SettingsPlace> implements SettingsView.Presenter {

  public SettingsActivity(SettingsPlace place, BrowserFactory browserFactory) {
    super(place, browserFactory);
  }

  public void start(final AcceptsOneWidget containerWidget, EventBus eventBus) {
    Messages messages = getBrowserFactory().getMessages();
    LoadingMessageView loadingMessageView = getBrowserFactory().getLoadingMessageView();
    loadingMessageView.setLoadingMessage(messages.loadingUserSettings());
    containerWidget.setWidget(loadingMessageView);
    getBrowserFactory().getDataSwitch().getUserSettings(new GetUserSettingsActionDto(getActorOid()), new AsyncCallback<GetUserSettingsResponseDto>() {
      public void onFailure(Throwable throwable) {
        handleException(throwable);
      }
      public void onSuccess(GetUserSettingsResponseDto getUserSettingsResponseDto) {
        loadSettings(containerWidget, getUserSettingsResponseDto);
      }
    });
  }

  private void loadSettings(AcceptsOneWidget containerWidget, GetUserSettingsResponseDto getUserSettingsResponseDto) {
    SettingsView settingsView = getBrowserFactory().getSettingsView();
    settingsView.setPresenter(this);
    settingsView.prepareView();
    settingsView.setUserName(getUserSettingsResponseDto.getName());
    settingsView.setUserEmail(getUserSettingsResponseDto.getEmail());
    containerWidget.setWidget(settingsView);
  }

  public void onSaveSettingsAction() {
    final Messages messages = getBrowserFactory().getMessages();
    SettingsView settingsView = getBrowserFactory().getSettingsView();
    String newPassword = settingsView.getNewPassword();
    String confirmNewPassword = settingsView.getConfirmedNewPassword();
    if(newPassword.equals(confirmNewPassword)) {
      getBrowserFactory().getDataSwitch().updateUserSettings(new UpdateUserSettingsActionDto(getActorOid(), settingsView.getUserName(), settingsView.getUserEmail(), settingsView.getNewPassword(), settingsView.getCurrentPassword()), new AsyncCallback<UpdateUserSettingsResponseDto>() {
        public void onFailure(Throwable throwable) {
          handleException(throwable);
        }

        public void onSuccess(UpdateUserSettingsResponseDto updateUserSettingsResponseDto) {
          Window.alert(messages.changesSavedSuccessfully());
        }
      });
    } else {
      handleException(new PasswordsDoNotMatchException());
      return;
    }
  }

  public void onCancelChangesAction() {
    //TODO: change view when the user cancels
  }
}
