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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.place.HomePlace;
import pt.ist.processpedia.client.place.LoginPlace;
import pt.ist.processpedia.client.place.SignupPlace;
import pt.ist.processpedia.client.view.login.LoginView;
import pt.ist.processpedia.shared.dto.action.LoginUserActionDto;
import pt.ist.processpedia.shared.dto.auth.EmailPasswordCredentialDto;
import pt.ist.processpedia.shared.dto.response.LoginUserResponseDto;
import pt.ist.processpedia.shared.validation.InputValidator;

public class LoginActivity extends ProcesspediaActivity<LoginPlace> implements LoginView.Presenter {

  public LoginActivity(LoginPlace place, BrowserFactory browserFactory) {
    super(place, browserFactory);
  }

  public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
    String actorId = getActorOid();
    if(actorId == null) {
      LoginView loginView = getBrowserFactory().getLoginView();
      loginView.setPresenter(this);
      loginView.prepareView();
      containerWidget.setWidget(loginView.asWidget());
    } else {
      goTo(new HomePlace());
    }
  }

  /**
   * Attempts to login the user using the provided set of credential data.
   */
  public void onLoginAction() {
    LoginView loginView = getBrowserFactory().getLoginView();
    String email = loginView.getEmail();
    String password = loginView.getPassword();
    try {
      InputValidator.validateEmail(email);
      InputValidator.validateUserPassword(password);
    } catch (Throwable throwable) {
      handleException(throwable);
      return;
    }
    EmailPasswordCredentialDto emailPasswordCredentialDto = new EmailPasswordCredentialDto(email, password);
    LoginUserActionDto loginUserActionDto = new LoginUserActionDto(emailPasswordCredentialDto);
    getBrowserFactory().getDataSwitch().loginUser(loginUserActionDto, new AsyncCallback<LoginUserResponseDto>() {
      public void onFailure(Throwable throwable) {
        GWT.log(throwable.toString());
        handleException(throwable);
      }
      public void onSuccess(LoginUserResponseDto loginUserResponseDto) {
        Long actorOid = loginUserResponseDto.getUserDto().getOid();
        String actorName = loginUserResponseDto.getUserDto().getName();
        saveActorOid(actorOid.toString());
        saveActorName(actorName);
        goTo(new HomePlace());
      }
    });
  }

  public void onSignupAction() {
    goTo(new SignupPlace());
  }
}
