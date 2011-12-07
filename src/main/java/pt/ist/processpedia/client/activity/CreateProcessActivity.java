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
import pt.ist.processpedia.client.notification.ProcesspediaNotification;
import pt.ist.processpedia.client.notification.ProcesspediaNotificationImpl;
import pt.ist.processpedia.client.place.CreateProcessPlace;
import pt.ist.processpedia.client.place.FolderPlace;
import pt.ist.processpedia.client.view.home.content.process.CreateProcessView;
import pt.ist.processpedia.shared.dto.action.authenticaded.CreateProcessActionDto;
import pt.ist.processpedia.shared.dto.response.CreateProcessResponseDto;
import pt.ist.processpedia.shared.dto.util.FolderDto;
import pt.ist.processpedia.shared.validation.InputValidator;

public class CreateProcessActivity extends ProcesspediaActivity<CreateProcessPlace> implements CreateProcessView.Presenter {

  public CreateProcessActivity(CreateProcessPlace place, BrowserFactory browserFactory) {
    super(place, browserFactory);
  }

  public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
    CreateProcessView createProcessView = getBrowserFactory().getCreateProcessView();
    createProcessView.setPresenter(this);
    createProcessView.prepareView();
    containerWidget.setWidget(createProcessView);

  }

  private void onCreateProcessResponse(CreateProcessResponseDto createProcessResponseDto) {
    goTo(new FolderPlace(FolderDto.FolderType.PENDING.toString().toLowerCase()));
  }

  public void onCancelAction() {
    History.back();
  }

  @Override
  public void onCreateProcessAction() {
    CreateProcessView createProcessView = getBrowserFactory().getCreateProcessView();
    String processTitle = createProcessView.getProcessTitle();
    String processDescription = createProcessView.getProcessDescription();
    try {
      InputValidator.validateProcessTitle(processTitle);
      InputValidator.validateProcessDescription(processDescription);
    } catch (Throwable e) {
      handleException(e);
      return;
    }
    CreateProcessActionDto createProcessActionDto = new CreateProcessActionDto(getActorOid(), processTitle, processDescription);
    getBrowserFactory().getDataSwitch().createProcess(createProcessActionDto, new AsyncCallback<CreateProcessResponseDto>() {
      @Override
      public void onFailure(Throwable throwable) {
        handleException(throwable);
      }
      @Override
      public void onSuccess(CreateProcessResponseDto createProcessResponseDto) {
        Messages messages = getBrowserFactory().getMessages();
        ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.SUCCESS, messages.process(), messages.processCreatedSuccessfully());
        notification.show();
        onCreateProcessResponse(createProcessResponseDto);
      }
    });
  }
}