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

package pt.ist.processpedia.client.view.home.content.settings;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import pt.ist.processpedia.client.Messages;

public class SettingsViewImpl extends Composite implements SettingsView {

  interface SettingsViewImplUiBinder extends UiBinder<Widget,SettingsViewImpl> {}
  private static SettingsViewImplUiBinder uiBinder = GWT.create(SettingsViewImplUiBinder.class);


  @UiField
  HasText settingsTitleContainer,
          userNameLabelContainer,
          userEmailLabelContainer,
          userCurrentPasswordLabelContainer,
          userNewPasswordLabelContainer,
          userConfirmNewPasswordLabelContainer,
          makeSettingsChangesConditionMessageContainer;

  @UiField
  HasText userNameContainer,
          userEmailContainer,
          userCurrentPasswordContainer,
          userNewPasswordContainer,
          userConfirmNewPasswordContainer;

  @UiField
  Button saveSettingsAction, cancelChangesAction;

  private Presenter presenter;

  public SettingsViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void prepareView() {
    Messages messages = presenter.getBrowserFactory().getMessages();
    setSettingsTitle(messages.settings());
    setUserNameLabel(messages.userNameLabel());
    setUserEmailLabel(messages.userEmailLabel());
    setUserCurrentPasswordLabel(messages.userCurrentPasswordLabel());
    userCurrentPasswordContainer.setText("");
    setUserNewPasswordLabel(messages.userNewPasswordLabel());
    userNewPasswordContainer.setText("");
    setUserConfirmNewPasswordLabel(messages.userConfirmNewPasswordLabel());
    userConfirmNewPasswordContainer.setText("");
    setMakeSettingsChangesConditionMessage(messages.makeSettingsChangesConditionMessage());
    setSaveChangesButtonText(messages.saveChanges());
    setCancelChangesButtonText(messages.cancel());
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public void setSettingsTitle(String settingsTitle) {
    settingsTitleContainer.setText(settingsTitle);
  }

  public void setUserNameLabel(String userNameLabel) {
    userNameLabelContainer.setText(userNameLabel);
  }

  public void setUserName(String userName) {
    userNameContainer.setText(userName);
  }

  public void setUserEmailLabel(String userEmailLabel) {
    userEmailLabelContainer.setText(userEmailLabel);
  }

  public void setUserEmail(String userEmail) {
    userEmailContainer.setText(userEmail);
  }

  public void setUserCurrentPasswordLabel(String userCurrentPasswordLabel) {
    userCurrentPasswordLabelContainer.setText(userCurrentPasswordLabel);
  }
  
  public void setUserNewPasswordLabel(String userNewPasswordLabel) {
    userNewPasswordLabelContainer.setText(userNewPasswordLabel);
  }

  public void setUserConfirmNewPasswordLabel(String userConfirmNewPasswordLabel) {
    userConfirmNewPasswordLabelContainer.setText(userConfirmNewPasswordLabel);
  }

  public void setMakeSettingsChangesConditionMessage(String makeSettingsChangesConditionMessage) {
    makeSettingsChangesConditionMessageContainer.setText(makeSettingsChangesConditionMessage);
  }

  @UiHandler("saveSettingsAction")
  public void onSaveSettingsAction(ClickEvent clickEvent) {
    presenter.onSaveSettingsAction();
  }

  @UiHandler("cancelChangesAction")
  public void onCancelChangesAction(ClickEvent clickEvent) {
    presenter.onCancelChangesAction();
  }

  public void setSaveChangesButtonText(String saveChangesButtonText) {
    saveSettingsAction.setText(saveChangesButtonText);
  }

  public void setCancelChangesButtonText(String cancelChangesButtonText) {
    cancelChangesAction.setText(cancelChangesButtonText);
  }

  public String getUserName() {
    return userNameContainer.getText();
  }

  public String getUserEmail() {
    return userEmailContainer.getText();
  }

  public String getCurrentPassword() {
    return userCurrentPasswordContainer.getText();
  }

  public String getNewPassword() {
    return userNewPasswordContainer.getText();
  }

  public String getConfirmedNewPassword() {
    return userConfirmNewPasswordContainer.getText();
  }
}
