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

import java.util.Set;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.client.place.CreateRequestPlace;
import pt.ist.processpedia.client.view.home.content.request.CreateRequestView;
import pt.ist.processpedia.client.view.home.content.splash.LoadingMessageView;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetQueueSetActionDto;
import pt.ist.processpedia.shared.dto.domain.QueueDto;
import pt.ist.processpedia.shared.dto.response.GetQueueSetResponseDto;

public class CreateRequestActivity extends ProcesspediaActivity<CreateRequestPlace> implements CreateRequestView.Presenter {

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
    CreateRequestView createRequestView = getBrowserFactory().getCreateRequestView();
    createRequestView.setPresenter(this);
    createRequestView.prepareView();
    containerWidget.setWidget(createRequestView);
  }

  private void resetOracle(Set<QueueDto> queueDtoSet) {
    CreateRequestView createRequestView = getBrowserFactory().getCreateRequestView();
    createRequestView.getOracle().clear();
    for(QueueDto queueDto : queueDtoSet) {
      createRequestView.getOracle().add(queueDto.getTitle());
    }
  }
  
  
  public void onPublishRequestAction() {

  }
  
  /*
  public void onPublishRequestAction() {
    CreateRequestView createRequestView = getBrowserFactory().getCreateRequestView();
    String requestTitle = createRequestView.getRequestTitle();
    String requestDescription = createRequestView.getRequestDescription();
    Boolean isResponseExpected = createRequestView.getIsResponseExpected();


    Set<QueueDto> publishQueueDtoSet = new HashSet<QueueDto>();
    for(QueueDto queueDto : queueDtoSet) {
      if(createRequestView.getTo().contains(queueDto.getTitle())) {
        publishQueueDtoSet.add(queueDto);
      }
    }
    Set<DataObjectDto> inputDataObjectDtoSet = new HashSet<DataObjectDto>();
    //TODO: LOAD ALL DATA OBJECTS INTO THE INPUT DATA OBJECT DTO SET

    CreateRequestActionDto createRequestActionDto = new CreateRequestActionDto(getActorOid(), requestTitle, requestDescription, isResponseExpected, publishQueueDtoSet, inputDataObjectDtoSet);
    CreateProcessActionDto createProcessActionDto = new CreateProcessActionDto(getActorOid(), processTitle, processDescription, createRequestActionDto);
    getBrowserFactory().getDataSwitch().createProcess(createProcessActionDto, new AsyncCallback<CreateProcessResponseDto>() {
      public void onFailure(Throwable throwable) {
        handleException(throwable);
      }
      public void onSuccess(CreateProcessResponseDto createProcessResponseDto) {
        Messages messages = getBrowserFactory().getMessages();
        Processpedia.showNotification(messages.requestSentSuccessfully());
        onCreateProcessResponse(createProcessResponseDto);
      }
    });
  }*/

  public void onCancelAction() {
    History.back();
  }
}
