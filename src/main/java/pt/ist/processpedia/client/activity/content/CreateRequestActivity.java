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

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.client.activity.ProcesspediaActivity;
import pt.ist.processpedia.client.notification.ProcesspediaNotification;
import pt.ist.processpedia.client.notification.ProcesspediaNotificationImpl;
import pt.ist.processpedia.client.place.CreateRequestPlace;
import pt.ist.processpedia.client.place.RequestPlace;
import pt.ist.processpedia.client.view.home.content.request.CreateRequestView;
import pt.ist.processpedia.shared.dto.action.authenticaded.CreateRequestActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetQueueSetActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetRequestRecommendationAction;
import pt.ist.processpedia.shared.dto.domain.DataObjectVersionDto;
import pt.ist.processpedia.shared.dto.domain.QueueDto;
import pt.ist.processpedia.shared.dto.recommendation.RequestRecommendationDto;
import pt.ist.processpedia.shared.dto.response.CreateRequestResponseDto;
import pt.ist.processpedia.shared.dto.response.GetQueueSetResponseDto;
import pt.ist.processpedia.shared.dto.response.GetRequestRecommendationResponseDto;

public class CreateRequestActivity extends ProcesspediaActivity<CreateRequestPlace> implements CreateRequestView.Presenter {

  private Set<QueueDto> queueSet;
  
  public CreateRequestActivity(CreateRequestPlace place, BrowserFactory browserFactory) {
    super(place, browserFactory);
  }

  public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
    getBrowserFactory().getDataSwitch().getQueueSet(new GetQueueSetActionDto(getActorOid()), new AsyncCallback<GetQueueSetResponseDto>() {
      public void onFailure(Throwable throwable) {
        handleException(throwable);
      }
      public void onSuccess(GetQueueSetResponseDto getQueueSetResponseDto) {
        resetOracle(getQueueSetResponseDto.getQueueDtoSet());
      }
    });
    String parentRequestOid = getPlace().getParentRequestId();
    getBrowserFactory().getDataSwitch().getRequestRecommendation(new GetRequestRecommendationAction(Long.parseLong(parentRequestOid)), new AsyncCallback<GetRequestRecommendationResponseDto>() {
      @Override
      public void onFailure(Throwable throwable) {
        handleException(throwable);
      }
      @Override
      public void onSuccess(GetRequestRecommendationResponseDto getRequestRecommendationResponse) {
        displayRequestRecommendations(getRequestRecommendationResponse.getRequestRecommendationSet());
      }    
    });
    CreateRequestView createRequestView = getBrowserFactory().getCreateRequestView();
    createRequestView.setPresenter(this);
    createRequestView.prepareView();
    createRequestView.addSelectionHandler(new SelectionHandler<Suggestion>() {

      @Override
      public void onSelection(SelectionEvent<Suggestion> selectionEvent) {
        onSelectRequestRecommendationAction((RequestRecommendationDto)selectionEvent.getSelectedItem());
      }
    });
    containerWidget.setWidget(createRequestView);
  }

  private void resetOracle(Set<QueueDto> queueDtoSet) {
    this.queueSet = queueDtoSet;
    CreateRequestView createRequestView = getBrowserFactory().getCreateRequestView();
    createRequestView.getOracle().clear();
    for(QueueDto queueDto : queueSet) {
      createRequestView.getOracle().add(queueDto.getTitle());
    }
  }
  
  private void displayRequestRecommendations(Set<RequestRecommendationDto> requestRecommendationDtoSet) {
    CreateRequestView createRequestView = getBrowserFactory().getCreateRequestView();
    createRequestView.setRequestRecommendationSet(requestRecommendationDtoSet);
  }
  
  
  public void onPublishRequestAction() {
    //TODO: VALIDATE ALL THE INPUT
    
    CreateRequestView createRequestView = getBrowserFactory().getCreateRequestView();
    String requestTitle = createRequestView.getRequestTitle();
    String requestDescription = createRequestView.getRequestDescription();
    Boolean isResponseExpected = createRequestView.getIsResponseExpected();

    Set<QueueDto> publishQueueSet = new HashSet<QueueDto>();
    for(QueueDto queueDto : queueSet) {
      if(createRequestView.getTo().contains(queueDto.getTitle())) {
        publishQueueSet.add(queueDto);
      }
    }
    Set<DataObjectVersionDto> inputDataObjectVersionSet = new HashSet<DataObjectVersionDto>();
    //TODO: LOAD ALL DATA OBJECTS INTO THE INPUT DATA OBJECT DTO SET

    long parentRequestOid = Long.parseLong(getPlace().getParentRequestId());
    CreateRequestActionDto createRequestActionDto = new CreateRequestActionDto(getActorOid(), parentRequestOid, requestTitle, requestDescription, isResponseExpected, publishQueueSet, inputDataObjectVersionSet);
    getBrowserFactory().getDataSwitch().createRequest(createRequestActionDto, new AsyncCallback<CreateRequestResponseDto>() {
      public void onFailure(Throwable throwable) {
        handleException(throwable);
      }
      public void onSuccess(CreateRequestResponseDto createRequestResponseDto) {
        Messages messages = getBrowserFactory().getMessages();
        ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.SUCCESS, messages.request(), messages.requestSentSuccessfully());
        notification.show();
        onCreateRequestResponse(createRequestResponseDto);
      }
    });
  }
  
  public void onCancelAction() {
    History.back();
  }

  @Override
  public void onSelectRequestRecommendationAction(RequestRecommendationDto requestRecommendationDto) {
    Window.alert("SUPPORT :"+requestRecommendationDto.getSupport());
  }
  
  public void onCreateRequestResponse(CreateRequestResponseDto createRequestResponseDto) {
    goTo(new RequestPlace(createRequestResponseDto.getRequestOid()));
  }
}
