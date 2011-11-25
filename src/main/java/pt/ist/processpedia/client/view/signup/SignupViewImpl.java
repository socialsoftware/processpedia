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

package pt.ist.processpedia.client.view.signup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import pt.ist.processpedia.client.Messages;

public class SignupViewImpl extends Composite implements SignupView {

  interface SignupViewImplUiBinder extends UiBinder<Widget, SignupViewImpl> {}
  private static SignupViewImplUiBinder uiBinder = GWT.create(SignupViewImplUiBinder.class);

  private Presenter presenter;

  @UiField HasText signupTitleContainer;

  @UiField TextBox emailContainer;

  @UiField TextBox nameContainer;

  @UiField PasswordTextBox passwordContainer;

  @UiField Button signupAction;


  public SignupViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void prepareView() {
    clearEmailContainer();
    clearNameContainer();
    clearPasswordContainer();
    emailContainer.getElement().setPropertyBoolean("spellcheck", false);
    nameContainer.getElement().setPropertyBoolean("spellcheck", false);
    passwordContainer.getElement().setPropertyBoolean("spellcheck", false);
    Messages messages = presenter.getBrowserFactory().getMessages();
    setSignupTitleText(messages.signupTitle());
    setNamePlaceholderText(messages.enterYourName());
    setEmailPlaceholderText(messages.enterYourEmail());
    setPasswordPlaceholderText(messages.enterYourPassword());
    setSignupButtonText(messages.signup());
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public void setSignupTitleText(String signupTitleText) {
    signupTitleContainer.setText(signupTitleText);
  }

  public void setEmailPlaceholderText(String emailPlaceholderText) {
    emailContainer.getElement().setPropertyString("placeholder", emailPlaceholderText);
  }

  public void setNamePlaceholderText(String namePlaceholderText) {
    nameContainer.getElement().setPropertyString("placeholder", namePlaceholderText);
  }

  public void setPasswordPlaceholderText(String passwordPlaceholderText) {
    passwordContainer.getElement().setPropertyString("placeholder", passwordPlaceholderText);
  }

  public void setSignupButtonText(String signupButtonText) {
    signupAction.setText(signupButtonText);
  }

  public String getName() {
    return nameContainer.getText();
  }

  public String getEmail() {
    return emailContainer.getText();
  }

  public String getPassword() {
    return passwordContainer.getText();
  }

  public void clearNameContainer() {
    nameContainer.setText("");
  }

  public void clearEmailContainer() {
    emailContainer.setText("");
  }

  public void clearPasswordContainer() {
    passwordContainer.setText("");
  }

  @UiHandler("signupAction")
  public void onSignupAction(ClickEvent clickEvent) {
    presenter.onSignupAction();
  }

}
