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
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.view.ProcesspediaView;
import pt.ist.processpedia.client.view.home.content.splash.NoFolderSelectedView;

public class ShowNoFolderSelectedViewActivity extends ProcesspediaActivity<Place> implements ProcesspediaView.ProcesspediaPresenter {

  public ShowNoFolderSelectedViewActivity(Place place, BrowserFactory browserFactory) {
    super(place, browserFactory);
  }

  public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
    NoFolderSelectedView noFolderSelectedView = getBrowserFactory().getNoFolderSelectedView();
    noFolderSelectedView.setPresenter(this);
    noFolderSelectedView.prepareView();
    containerWidget.setWidget(noFolderSelectedView);
  }
}
