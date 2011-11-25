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
import com.google.gwt.user.client.ui.Label;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.client.place.RequestPlace;
import pt.ist.processpedia.client.place.SearchPlace;
import pt.ist.processpedia.client.view.home.content.request.RequestListView;
import pt.ist.processpedia.client.view.home.content.splash.LoadingMessageView;
import pt.ist.processpedia.shared.dto.action.authenticaded.SearchActionDto;
import pt.ist.processpedia.shared.dto.domain.RequestDto;
import pt.ist.processpedia.shared.dto.response.SearchResponseDto;

import java.util.Set;

public class SearchActivity extends ProcesspediaActivity<SearchPlace> implements RequestListView.Presenter {

  public SearchActivity(SearchPlace place, BrowserFactory browserFactory) {
    super(place, browserFactory);
  }

  public void start(final AcceptsOneWidget containerWidget, EventBus eventBus) {
    Messages messages = getBrowserFactory().getMessages();
    LoadingMessageView loadingMessageView = getBrowserFactory().getLoadingMessageView();
    loadingMessageView.setLoadingMessage(messages.searching());
    containerWidget.setWidget(loadingMessageView);

    String criteria = getPlace().getCriteria();

    getBrowserFactory().getDataSwitch().search(new SearchActionDto(getActorOid(), criteria), new AsyncCallback<SearchResponseDto>() {
      public void onFailure(Throwable throwable) {
        handleException(throwable);
      }
      public void onSuccess(SearchResponseDto searchResponseDto) {
        displaySearchResults(containerWidget, searchResponseDto.getRequestDtoSet());
      }
    });
  }

  private void displaySearchResults(AcceptsOneWidget containerWidget, Set<RequestDto> requestDtoSet) {
    RequestListView requestListView = getBrowserFactory().getProcessListView();
    requestListView.setPresenter(this);
    requestListView.prepareView();
    requestListView.displayRequestSet(requestDtoSet);
    containerWidget.setWidget(requestListView);
  }

  public void onRequestSelection(RequestDto requestDto) {
    goTo(new RequestPlace(requestDto.getOid()));
  }
}
