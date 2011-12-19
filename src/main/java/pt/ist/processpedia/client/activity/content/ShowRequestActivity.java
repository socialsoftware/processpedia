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

package pt.ist.processpedia.client.activity.content;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.client.activity.ProcesspediaActivity;
import pt.ist.processpedia.client.place.CreateRequestPlace;
import pt.ist.processpedia.client.place.RequestPlace;
import pt.ist.processpedia.client.view.home.content.request.RequestDetailedView;
import pt.ist.processpedia.client.view.home.content.splash.LoadingMessageView;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetRequestActionDto;
import pt.ist.processpedia.shared.dto.domain.RequestDetailedDto;
import pt.ist.processpedia.shared.dto.response.GetRequestResponseDto;

public class ShowRequestActivity extends ProcesspediaActivity<RequestPlace> implements RequestDetailedView.Presenter {

  private RequestDetailedDto requestDetailedDto;
  
  public ShowRequestActivity(RequestPlace place, BrowserFactory browserFactory) {
    super(place, browserFactory);
  }

  public void start(final AcceptsOneWidget containerWidget, EventBus eventBus) {
    Messages messages = getBrowserFactory().getMessages();
    LoadingMessageView loadingMessageView = getBrowserFactory().getLoadingMessageView();
    loadingMessageView.setLoadingMessage(messages.loadingRequest());
    containerWidget.setWidget(loadingMessageView);

    long requestOid = getPlace().getRequestOid();
    getBrowserFactory().getDataSwitch().getRequest(new GetRequestActionDto(getActorOid(), requestOid), new AsyncCallback<GetRequestResponseDto>() {
      public void onFailure(Throwable throwable) {
        handleException(throwable);
      }
      public void onSuccess(GetRequestResponseDto getRequestResponseDto) {
        displayRequestDetailedView(containerWidget, getRequestResponseDto.getRequestDetailedDto());
      }
    });
  }

  private void displayRequestDetailedView(AcceptsOneWidget containerWidget, RequestDetailedDto requestDetailedDto) {
    this.requestDetailedDto = requestDetailedDto;
    
    RequestDetailedView requestDetailedView = getBrowserFactory().getRequestDetailedView();
    requestDetailedView.setPresenter(this);
    requestDetailedView.prepareView();

    containerWidget.setWidget(requestDetailedView);
  }

  @Override
  public void onCreateNewRequestAction() {
    goTo(new CreateRequestPlace(requestDetailedDto.getOid()));
  }


}
