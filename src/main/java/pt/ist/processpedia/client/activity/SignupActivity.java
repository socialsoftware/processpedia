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
import pt.ist.processpedia.client.place.LoginPlace;
import pt.ist.processpedia.client.place.SignupPlace;
import pt.ist.processpedia.client.view.signup.SignupView;
import pt.ist.processpedia.shared.dto.action.SignupUserActionDto;
import pt.ist.processpedia.shared.dto.response.SignupUserResponseDto;
import pt.ist.processpedia.shared.validation.InputValidator;

public class SignupActivity extends ProcesspediaActivity<SignupPlace> implements SignupView.Presenter {

  public SignupActivity(SignupPlace place, BrowserFactory browserFactory) {
    super(place, browserFactory);
  }

  public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
    SignupView signupView = getBrowserFactory().getSignupView();
    signupView.setPresenter(this);
    signupView.prepareView();
    containerWidget.setWidget(signupView.asWidget());
  }

  public void onSignupAction() {
    SignupView signupView = getBrowserFactory().getSignupView();
    String email = signupView.getEmail();
    String name = signupView.getName();
    String password = signupView.getPassword();
    try {
      InputValidator.validateUserName(name);
      InputValidator.validateEmail(email);
      InputValidator.validateUserPassword(password);
    } catch (Throwable throwable) {
      handleException(throwable);
    }
    SignupUserActionDto signupUserActionDto = new SignupUserActionDto(name, email, password);
    getBrowserFactory().getDataSwitch().signupUser(signupUserActionDto, new AsyncCallback<SignupUserResponseDto>() {
      public void onFailure(Throwable throwable) {
        GWT.log(throwable.toString());
        handleException(throwable);
      }
      public void onSuccess(SignupUserResponseDto signupUserResponseDto) {
        goTo(new LoginPlace());
      }
    });
    
  }

}
