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

package pt.ist.processpedia.client.view.home.content.request.line;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.ocpsoft.pretty.time.PrettyTime;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.client.view.home.content.request.RequestDetailedView;

import java.util.Date;

public class ParentRequestLineViewImpl extends Composite implements ParentRequestLineView {

  interface ParentRequestLineViewImplUiBinder extends UiBinder<Widget, ParentRequestLineViewImpl> {}
  private static ParentRequestLineViewImplUiBinder uiBinder = GWT.create(ParentRequestLineViewImplUiBinder.class);

  @UiField
  HasText fromLabelContainer;

  @UiField
  Image initiatorAvatar;

  @UiField
  HasText initiatorNameContainer, requestTitleContainer, dateContainer;

  private RequestDetailedView.Presenter presenter;

  public ParentRequestLineViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setPresenter(RequestDetailedView.Presenter presenter) {
    this.presenter = presenter;
  }

  public void prepareView() {
    Messages messages = presenter.getBrowserFactory().getMessages();
    setFromLabel(messages.from()+": ");
  }

  public void setInitiatorAvatar(String avatarUrl) {
    //TODO: USE ACTUAL avatarURL
    initiatorAvatar.setUrl("themes/default/images/default-avatar.png");
  }

  public void setFromLabel(String fromLabel) {
    fromLabelContainer.setText(fromLabel);
  }

  public void setInitiatorName(String initiatorName) {
    initiatorNameContainer.setText(initiatorName);
  }

  public void setRequestTitle(String requestTitle) {
    requestTitleContainer.setText(requestTitle);
  }

  public void setCreationDate(Date date) {
    dateContainer.setText("date goes here");
  }
}
