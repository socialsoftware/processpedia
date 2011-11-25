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
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.place.CreateProcessPlace;
import pt.ist.processpedia.client.place.LoginPlace;
import pt.ist.processpedia.client.place.SettingsPlace;
import pt.ist.processpedia.client.view.home.header.HeaderView;

public class HeaderActivity extends ProcesspediaActivity<Place> implements HeaderView.Presenter {

  public HeaderActivity(Place place, BrowserFactory browserFactory) {
    super(place, browserFactory);
  }

  public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
    HeaderView headerView = getBrowserFactory().getHeaderView();
    headerView.setPresenter(this);
    headerView.prepareView();
    containerWidget.setWidget(headerView.asWidget());
  }

  public void onCreateProcessAction() {
    goTo(new CreateProcessPlace());
  }
  
  public void onSettingsAction() {
    goTo(new SettingsPlace());
  }

  public void onLogOutAction() {
    Storage sessionStorage = Storage.getSessionStorageIfSupported();
    sessionStorage.clear();
    goTo(new LoginPlace());
  }
}
