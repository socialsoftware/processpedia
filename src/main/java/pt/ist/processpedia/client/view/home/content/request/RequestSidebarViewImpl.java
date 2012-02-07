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

package pt.ist.processpedia.client.view.home.content.request;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.client.view.home.content.request.line.ChildRequestLineView;
import pt.ist.processpedia.client.view.home.content.request.line.ParentRequestLineViewImpl;
import pt.ist.processpedia.shared.dto.domain.OperatingPartyDto;
import pt.ist.processpedia.shared.dto.domain.RequestDto;

public class RequestSidebarViewImpl extends Composite implements RequestSidebarView {

  interface RequestSidebarViewImplUiBinder extends UiBinder<Widget,RequestSidebarViewImpl> {}
  private static RequestSidebarViewImplUiBinder uiBinder = GWT.create(RequestSidebarViewImplUiBinder.class);

  private RequestDetailedView.Presenter presenter;

  @UiField
  ParentRequestLineViewImpl parentRequestLineView;
  
  @UiField
  Button createNewRequestAction;
  
  public RequestSidebarViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setPresenter(RequestDetailedView.Presenter presenter) {
    this.presenter = presenter;
  }

  public void clear() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public void setParentRequestLine(RequestDto request) {
    OperatingPartyDto originalInitiator = request.getOriginalInitiator();
    OperatingPartyDto initiator = request.getInitiator();
    OperatingPartyDto executor = request.getExecutor();
    parentRequestLineView.setPresenter(presenter);
    parentRequestLineView.prepareView();
    parentRequestLineView.setOriginalInitiatorName(originalInitiator.getName());
    parentRequestLineView.setOriginalInitiatorAvatar(originalInitiator.getName());
    parentRequestLineView.setInitiatorAvatar(originalInitiator.getAvatarUrl());
    parentRequestLineView.setSubject(request.getSubject());
    parentRequestLineView.setCreationDate(request.getCreationTimestamp());
  }

  public void addChildRequestLine(ChildRequestLineView childRequestLineView) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public void prepareView() {
    Messages messages = presenter.getBrowserFactory().getMessages();
    
    setCreateNewRequestButtonText(messages.createNewRequestAction());
  }
  
  public void setCreateNewRequestButtonText(String createNewRequestButtonText) {
    createNewRequestAction.setText(createNewRequestButtonText);
  }

  @UiHandler("createNewRequestAction")
  public void onCreateNewRequestAction(ClickEvent clickEvent) {
    presenter.onCreateNewRequestAction();
  }
  
}
