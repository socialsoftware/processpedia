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

import pt.ist.processpedia.client.view.ProcesspediaView;

public interface SettingsView extends ProcesspediaView {

  interface Presenter extends ProcesspediaPresenter {
    void onSaveSettingsAction();
    void onCancelChangesAction();
  }

  void setPresenter(Presenter presenter);

  void setSettingsTitle(String settingsTitle);
  void setUserNameLabel(String userNameLabel);
  void setUserEmailLabel(String userEmailLabel);
  void setUserCurrentPasswordLabel(String userCurrentPasswordLabel);
  void setUserNewPasswordLabel(String userNewPasswordLabel);
  void setUserConfirmNewPasswordLabel(String userConfirmNewPasswordLabel);
  void setSaveChangesButtonText(String saveChangesButtonText);
  void setCancelChangesButtonText(String cancelChangesButtonText);


  void setUserName(String userName);
  void setUserEmail(String userEmail);

  String getUserName();
  String getUserEmail();
  String getNewPassword();
  String getConfirmedNewPassword();
  String getCurrentPassword();

}
