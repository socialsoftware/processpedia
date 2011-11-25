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
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.place.CreateRequestPlace;
import pt.ist.processpedia.client.view.home.content.request.CreateRequestView;

public class CreateRequestActivity extends ProcesspediaActivity<CreateRequestPlace> implements CreateRequestView.Presenter {

  public CreateRequestActivity(CreateRequestPlace place, BrowserFactory browserFactory) {
    super(place, browserFactory);
  }

  public void start(AcceptsOneWidget acceptsOneWidget, EventBus eventBus) {
    //TODO: load the create
  }

  public void onPublishRequestAction() {

  }

  public void onCancelAction() {
    History.back();
  }
}
