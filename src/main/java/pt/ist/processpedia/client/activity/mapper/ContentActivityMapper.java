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

package pt.ist.processpedia.client.activity.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.activity.*;

import pt.ist.processpedia.client.place.*;
import pt.ist.processpedia.client.view.home.content.splash.NoFolderSelectedView;

public class ContentActivityMapper implements ActivityMapper {

  private BrowserFactory browserFactory;

  public ContentActivityMapper(BrowserFactory browserFactory) {
    this.browserFactory = browserFactory;
  }

  public Activity getActivity(Place place) {
    GWT.log("ContentActivityMapper: " + place.toString());
    if(place instanceof SettingsPlace)
      return new SettingsActivity((SettingsPlace) place, browserFactory);
    if(place instanceof CreateProcessPlace)
      return new CreateProcessActivity((CreateProcessPlace) place, browserFactory);
    if(place instanceof CreateRequestPlace)
      return new CreateRequestActivity((CreateRequestPlace) place, browserFactory);
    if(place instanceof FolderPlace)
      return new ShowFolderContentsActivity((FolderPlace) place, browserFactory);
    if(place instanceof RequestPlace)
      return new ShowRequestActivity((RequestPlace) place, browserFactory);
    if(place instanceof SearchPlace)
      return new SearchActivity((SearchPlace) place, browserFactory);
    return new ShowNoFolderSelectedViewActivity(place, browserFactory);
  }
}
