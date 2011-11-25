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

package pt.ist.processpedia.client.activity.manager;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.web.bindery.event.shared.EventBus;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.activity.AccountActivationActivity;
import pt.ist.processpedia.client.place.AccountActivationPlace;
import pt.ist.processpedia.client.place.HomePlace;
import pt.ist.processpedia.client.place.LoginPlace;
import pt.ist.processpedia.client.place.SignupPlace;

public class SidebarActivityManager extends ActivityManager {

  public SidebarActivityManager(BrowserFactory browserFactory) {
    super(browserFactory.getSidebarActivityMapper(), browserFactory.getEventBus());
    setDisplay(browserFactory.getHomeView().getSidebarContainer());
  }

  @Override
  public void onPlaceChange(PlaceChangeEvent event) {
    Place newPlace = event.getNewPlace();
    if(!(newPlace instanceof LoginPlace) &&
       !(newPlace instanceof SignupPlace) &&
       !(newPlace instanceof AccountActivationPlace)) {
      super.onPlaceChange(event);
    }
  }

}
