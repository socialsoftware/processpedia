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
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasWidgets;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.place.ProcessPlace;
import pt.ist.processpedia.client.view.home.content.process.ProcessView;
import pt.ist.processpedia.client.view.home.content.request.RequestDetailedView;
import pt.ist.processpedia.client.view.home.content.request.line.ChildRequestLineView;
import pt.ist.processpedia.shared.dto.domain.ProcessWithRootRequestsDto;
import pt.ist.processpedia.shared.dto.domain.RequestDetailedDtoImpl;

public class ShowProcessActivity extends ProcesspediaActivity<ProcessPlace> implements ProcessView.Presenter, RequestDetailedView.Presenter {

  public ShowProcessActivity(ProcessPlace place, BrowserFactory browserFactory) {
    super(place, browserFactory);
  }

  public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

  }

  public void displayProcess(AcceptsOneWidget containerWidget, ProcessWithRootRequestsDto processWithRootRequestsDto) {
    ProcessView processView = getBrowserFactory().getProcessView();
    processView.setPresenter(this);
    processView.prepareView();
    processView.setProcessTitle(processWithRootRequestsDto.getTitle());
    processView.setProcessDescription(processWithRootRequestsDto.getDescription());

    HasWidgets requestsContainer = processView.getRequestsContainer();
    requestsContainer.clear();
    for(RequestDetailedDtoImpl requestDetailedDto : processWithRootRequestsDto.getRootRequestSet()) {
      displayRequestLine(requestsContainer, requestDetailedDto);
    }
    containerWidget.setWidget(processView);
  }


  public void displayRequestLine(HasWidgets widgetsContainer, RequestDetailedDtoImpl requestDetailedDto) {
    ChildRequestLineView childRequestLineView = getBrowserFactory().getChildRequestLineView();
    childRequestLineView.setPresenter(this);
    childRequestLineView.prepareView();
    childRequestLineView.setRequestDetailedDto(requestDetailedDto);
    childRequestLineView.setInitiatorName(requestDetailedDto.getInitiatorDto().getName());
    childRequestLineView.setRequestTitle(requestDetailedDto.getTitle());
    childRequestLineView.setDate(requestDetailedDto.getLastUpdateTimestamp());

    widgetsContainer.add(childRequestLineView.asWidget());
  }

  public void onRequestSelectAction(RequestDetailedDtoImpl requestDetailedDto) {
    displayRequest(requestDetailedDto);
  }

  private void displayRequest(RequestDetailedDtoImpl requestDetailedDto) {
    
  }

  public void onViewRequestAction() {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
