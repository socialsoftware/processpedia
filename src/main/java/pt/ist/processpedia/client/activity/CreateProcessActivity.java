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
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.client.Processpedia;
import pt.ist.processpedia.client.place.CreateProcessPlace;
import pt.ist.processpedia.client.place.FolderPlace;
import pt.ist.processpedia.client.view.home.content.process.CreateProcessView;
import pt.ist.processpedia.client.view.home.content.request.CreateRequestView;
import pt.ist.processpedia.shared.dto.action.authenticaded.CreateProcessActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.CreateRequestActionDto;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetQueueSetActionDto;
import pt.ist.processpedia.shared.dto.domain.DataObjectDto;
import pt.ist.processpedia.shared.dto.domain.QueueDto;
import pt.ist.processpedia.shared.dto.response.CreateProcessResponseDto;
import pt.ist.processpedia.shared.dto.response.GetQueueSetResponseDto;
import pt.ist.processpedia.shared.dto.util.FolderDto;
import pt.ist.processpedia.shared.validation.InputValidator;

import java.util.HashSet;
import java.util.Set;

public class CreateProcessActivity extends ProcesspediaActivity<CreateProcessPlace> implements CreateProcessView.Presenter, CreateRequestView.Presenter {

  private Set<QueueDto> queueDtoSet;

  private String processTitle;
  private String processDescription;

  private AcceptsOneWidget containerWidget;

  public CreateProcessActivity(CreateProcessPlace place, BrowserFactory browserFactory) {
    super(place, browserFactory);
  }

  public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
    this.containerWidget = containerWidget;
    CreateProcessView createProcessView = getBrowserFactory().getCreateProcessView();
    createProcessView.setPresenter(this);
    createProcessView.prepareView();
    containerWidget.setWidget(createProcessView);
    getBrowserFactory().getDataSwitch().getQueueSet(new GetQueueSetActionDto(getActorOid()), new AsyncCallback<GetQueueSetResponseDto>() {
      public void onFailure(Throwable throwable) {
        handleException(throwable);
      }

      public void onSuccess(GetQueueSetResponseDto getQueueSetResponseDto) {
        resetOracle(getQueueSetResponseDto.getQueueDtoSet());
      }
    });
  }

  public void onNextAction() {
    CreateProcessView createProcessView = getBrowserFactory().getCreateProcessView();
    processTitle = createProcessView.getProcessTitle();
    processDescription = createProcessView.getProcessDescription();
    try {
      InputValidator.validateProcessTitle(processTitle);
      InputValidator.validateProcessDescription(processDescription);
    } catch (Throwable e) {
      handleException(e);
      return;
    }
    CreateRequestView createRequestView = getBrowserFactory().getCreateRequestView();
    createRequestView.setPresenter(this);
    createRequestView.prepareView();
    containerWidget.setWidget(createRequestView);
  }


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
  }

  private void onCreateProcessResponse(CreateProcessResponseDto createProcessResponseDto) {
    goTo(new FolderPlace(FolderDto.FolderType.PENDING.toString().toLowerCase()));
  }

  public void onCancelAction() {
    History.back();
  }

  private void resetOracle(Set<QueueDto> queueDtoSet) {
    this.queueDtoSet = queueDtoSet;
    CreateRequestView createRequestView = getBrowserFactory().getCreateRequestView();
    createRequestView.getOracle().clear();
    for(QueueDto queueDto : queueDtoSet) {
      createRequestView.getOracle().add(queueDto.getTitle());
    }
  }
}