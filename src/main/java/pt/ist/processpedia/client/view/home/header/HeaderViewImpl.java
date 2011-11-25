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

package pt.ist.processpedia.client.view.home.header;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.http.client.URL;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.client.place.SearchPlace;

public class HeaderViewImpl extends Composite implements HeaderView {

  interface HeaderViewImplUiBinder extends UiBinder<Widget, HeaderViewImpl> {}
  private static HeaderViewImplUiBinder uiBinder = GWT.create(HeaderViewImplUiBinder.class);

  private Presenter presenter;

  @UiField
  HasText applicationNameContainer;

  @UiField
  Button createProcessAction;

  @UiField
  Anchor settingsAction, logOutAction;

  @UiField TextBox searchTextContainer;

  public HeaderViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void prepareView() {
    Messages messages = presenter.getBrowserFactory().getMessages();
    setApplicationName(messages.applicationName());
    setSettingsActionText(messages.settings());
    setLogOutActionText(messages.logout());
    setCreateRequestTitleText(messages.createRequest());
    setSearchPlaceholderText(messages.search());
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public void setApplicationName(String applicationName) {
    applicationNameContainer.setText(applicationName);
  }

  @UiHandler("createProcessAction")
  public void onCreateProcessAction(ClickEvent event) {
    presenter.onCreateProcessAction();
  }

  @UiHandler("searchTextContainer")
  public void onSearchCriteriaDefinition(KeyDownEvent event) {
    if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
      if(!searchTextContainer.getText().equals("")) {
        presenter.goTo(new SearchPlace(searchTextContainer.getText()));
      } else {
        searchTextContainer.setFocus(false);
      }
    }
  }

  public void setCreateRequestTitleText(String createRequestTitleText) {
    createProcessAction.setTitle(createRequestTitleText);
  }

  public void setSettingsActionText(String settingsActionText) {
    settingsAction.setText(settingsActionText);
  }

  public void setLogOutActionText(String logOutActionText) {
    logOutAction.setText(logOutActionText);
  }

  public void setSearchPlaceholderText(String searchPlaceholderText) {
    searchTextContainer.getElement().setPropertyString("placeholder", searchPlaceholderText);
  }

  @UiHandler("settingsAction")
  public void onSettingsAction(ClickEvent event) {
    presenter.onSettingsAction();
  }

  @UiHandler("logOutAction")
  public void onLogOutAction(ClickEvent event) {
    presenter.onLogOutAction();
  }

}
