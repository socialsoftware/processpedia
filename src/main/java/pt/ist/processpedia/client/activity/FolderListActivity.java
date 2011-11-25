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
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.place.FolderPlace;
import pt.ist.processpedia.client.view.home.sidebar.FolderListView;
import pt.ist.processpedia.client.view.home.sidebar.LoadingFoldersView;
import pt.ist.processpedia.client.view.home.sidebar.folder.FolderView;
import pt.ist.processpedia.client.view.home.sidebar.folder.FolderViewImpl;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetFolderListActionDto;
import pt.ist.processpedia.shared.dto.response.GetFolderListResponseDto;
import pt.ist.processpedia.shared.dto.util.FolderDto;

import java.util.List;

public class FolderListActivity extends ProcesspediaActivity<Place> implements FolderListView.Presenter, FolderView.Presenter, LoadingFoldersView.Presenter {

  public FolderListActivity(Place place, BrowserFactory browserFactory) {
    super(place, browserFactory);
  }

  public void start(final AcceptsOneWidget containerWidget, EventBus eventBus) {
    LoadingFoldersView loadingFoldersView = getBrowserFactory().getLoadingFoldersView();
    loadingFoldersView.setPresenter(this);
    loadingFoldersView.prepareView();
    containerWidget.setWidget(loadingFoldersView);
    getBrowserFactory().getDataSwitch().getFolderList(new GetFolderListActionDto(getActorOid()), new AsyncCallback<GetFolderListResponseDto>() {
      public void onFailure(Throwable throwable) {
        handleException(throwable);
      }
      public void onSuccess(GetFolderListResponseDto getFolderListResponseDto) {
        displayFolderList(containerWidget, getFolderListResponseDto.getFolderDtoList());
      }
    });
  }

  private void displayFolderList(AcceptsOneWidget containerWidget, List<FolderDto> folderDtoList) {
    FolderListView folderListView = getBrowserFactory().getFolderListView();
    folderListView.setPresenter(this);
    folderListView.prepareView();
    folderListView.clear();
    for(FolderDto folderDto : folderDtoList) {
      FolderView folderView = new FolderViewImpl();
      folderView.setPresenter(this);
      folderView.setFolderType(folderDto.getType());
      folderView.prepareView();
      folderView.setFolderItemCount(folderDto.getCount());
      folderListView.addFolderView(folderView);
    }
    containerWidget.setWidget(folderListView);
  }

  public void onFolderAction(FolderView folderView) {
    goTo(new FolderPlace(folderView.getFolderType().name().toLowerCase()));
  }
}
