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

package pt.ist.processpedia.client.activity.home;

import com.google.gwt.activity.shared.ActivityManager;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.activity.content.ContentActivityManager;
import pt.ist.processpedia.client.activity.header.HeaderActivityManager;
import pt.ist.processpedia.client.activity.sidebar.SidebarActivityManager;

public class ProcesspediaActivityManager extends ActivityManager {

  private HeaderActivityManager headerActivityManager;
  private SidebarActivityManager sidebarActivityManager;
  private ContentActivityManager contentActivityManager;
    
  public ProcesspediaActivityManager(BrowserFactory browserFactory) {
    super(new ProcesspediaActivityMapper(browserFactory), browserFactory.getEventBus());
    headerActivityManager = new HeaderActivityManager(browserFactory);
    sidebarActivityManager = new SidebarActivityManager(browserFactory);
    contentActivityManager = new ContentActivityManager(browserFactory);
    setDisplay(browserFactory.getProcesspediaContainer());
  }
  
  public HeaderActivityManager getHeaderActivityManager() {
    return headerActivityManager;
  }
  
  public SidebarActivityManager getSidebarActivityManager() {
    return sidebarActivityManager;
  }
  
  public ContentActivityManager getContentActivityManager() {
    return contentActivityManager;
  }

}
