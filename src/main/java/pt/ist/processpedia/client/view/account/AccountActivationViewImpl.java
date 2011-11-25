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

package pt.ist.processpedia.client.view.account;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import pt.ist.processpedia.client.Messages;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;

public class AccountActivationViewImpl extends Composite implements AccountActivationView {

  interface AccountActivationViewImplUiBinder extends UiBinder<Widget,AccountActivationViewImpl> {}
  private static AccountActivationViewImplUiBinder uiBinder = GWT.create(AccountActivationViewImplUiBinder.class);

  private Presenter presenter;

  @UiField
  HasText activationTitleContainer, activationMessageContainer;

  @UiField
  Button goToLoginPageAction;

  public AccountActivationViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public void prepareView() {
    Messages messages = presenter.getBrowserFactory().getMessages();

    activationTitleContainer.setText(messages.activationTitle());
    goToLoginPageAction.setText(messages.goToLoginPage());

  }

  @UiHandler("goToLoginPageAction")
  public void onGoToLoginPageAction(ClickEvent clickEvent) {
    
  }
}
