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

package pt.ist.processpedia.client.view.home.sidebar.folder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.shared.dto.util.FolderDto;

public class FolderViewImpl extends Composite implements FolderView {

  interface FolderViewImplUiBinder extends UiBinder<Widget,FolderViewImpl> {}
  private static FolderViewImplUiBinder uiBinder = GWT.create(FolderViewImplUiBinder.class);

  private Presenter presenter;

  private FolderDto.FolderType folderType;

  @UiField
  FocusPanel wrapper;
  
  @UiField
  Image folderIcon;

  @UiField
  Button folderAction;

  @UiField
  Label folderCount;

  public FolderViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
    wrapper.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent clickEvent) {
        onFolderAction(clickEvent);
      }
    });
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public void prepareView() {
    String iconUrl = "";
    String label = "";
    Messages messages = presenter.getBrowserFactory().getMessages();
    switch(folderType) {
      case INBOX:
        iconUrl = "themes/default/images/folder-inbox.png";
        label = messages.inboxFolder();
        break;
      case HANDLED:
        iconUrl = "themes/default/images/folder-handled.png";
        label = messages.handledFolder();
        break;
      case HANDLING:
        iconUrl = "themes/default/images/folder-executing.png";
        label = messages.handlingFolder();
        break;
      case PENDING:
        iconUrl = "themes/default/images/folder-pending.png";
        label = messages.pendingFolder();
        break;
    }
    setFolderIconUrl(iconUrl);
    setFolderLabel(label);
  }

  public void setFolderType(FolderDto.FolderType folderType) {
    this.folderType = folderType;
  }

  public FolderDto.FolderType getFolderType() {
    return this.folderType;
  }

  public void setFolderIconUrl(String iconUrl) {
    folderIcon.setUrl(iconUrl);
  }

  public void setFolderLabel(String folderLabel) {
    folderAction.setText(folderLabel);
  }

  public void setFolderItemCount(int count) {
    if(count > 0) {
      folderCount.setStyleName("folderCount", true);
      folderCount.setText(new Integer(count).toString());
    } else {
      folderCount.setText("");
      folderCount.setStyleName("folderCountHidden");
    }
  }

  @UiHandler("folderAction")
  public void onFolderAction(ClickEvent clickEvent) {
    presenter.onFolderAction(this);
  }
}
