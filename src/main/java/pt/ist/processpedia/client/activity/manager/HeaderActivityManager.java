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
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.place.AccountActivationPlace;
import pt.ist.processpedia.client.place.LoginPlace;
import pt.ist.processpedia.client.place.SignupPlace;

public class HeaderActivityManager extends ActivityManager {

  public HeaderActivityManager(BrowserFactory browserFactory) {
    super(browserFactory.getHeaderActivityMapper(), browserFactory.getEventBus());
    setDisplay(browserFactory.getHomeView().getHeaderContainer());
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