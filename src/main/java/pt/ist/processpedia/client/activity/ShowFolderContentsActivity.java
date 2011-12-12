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

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.client.place.FolderPlace;
import pt.ist.processpedia.client.place.RequestPlace;
import pt.ist.processpedia.client.view.home.content.request.RequestListView;
import pt.ist.processpedia.client.view.home.content.splash.LoadingMessageView;
import pt.ist.processpedia.client.view.home.content.splash.NoRequestsFoundView;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetFolderContentsActionDto;
import pt.ist.processpedia.shared.dto.domain.RequestDtoImpl;
import pt.ist.processpedia.shared.dto.response.GetFolderContentsResponseDto;
import java.util.*;

public class ShowFolderContentsActivity extends ProcesspediaActivity<FolderPlace> implements RequestListView.Presenter {

  public ShowFolderContentsActivity(FolderPlace place, BrowserFactory browserFactory) {
    super(place, browserFactory);
  }

  public void start(final AcceptsOneWidget containerWidget, EventBus eventBus) {
    Messages messages = getBrowserFactory().getMessages();
    LoadingMessageView loadingMessageView = getBrowserFactory().getLoadingMessageView();
    loadingMessageView.setLoadingMessage(messages.loadingFolderContents());
    containerWidget.setWidget(loadingMessageView);

    String folderTitle = getPlace().getFolderTitle();

    getBrowserFactory().getDataSwitch().getFolderContents(new GetFolderContentsActionDto(getActorOid(), folderTitle), new AsyncCallback<GetFolderContentsResponseDto>() {
      public void onFailure(Throwable throwable) {
        handleException(throwable);
      }
      public void onSuccess(GetFolderContentsResponseDto getFolderContentsResponseDto) {
        displayRequestSet(containerWidget, getFolderContentsResponseDto.getRequestDtoSet());
      }
    });
  }

  private void displayRequestSet(AcceptsOneWidget containerWidget, Set<RequestDtoImpl> requestDtoSet) {
    if(requestDtoSet.size() > 0) {
      RequestListView requestListView = getBrowserFactory().getProcessListView();
      requestListView.setPresenter(this);
      requestListView.prepareView();
      requestListView.displayRequestSet(requestDtoSet);
      containerWidget.setWidget(requestListView);
    } else {
      NoRequestsFoundView noRequestsFoundView = getBrowserFactory().getNoRequestsFoundView();
      noRequestsFoundView.setPresenter(this);
      noRequestsFoundView.prepareView();
      containerWidget.setWidget(noRequestsFoundView);
    }
  }

  public void onRequestSelection(RequestDtoImpl requestDto) {
    goTo(new RequestPlace(requestDto.getOid()));
  }
}
