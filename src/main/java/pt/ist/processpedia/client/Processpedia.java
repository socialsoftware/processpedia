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

package pt.ist.processpedia.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import pt.ist.processpedia.client.ui.SearchPopup;
import pt.ist.processpedia.client.util.KeyCodes;

public class Processpedia implements EntryPoint, Event.NativePreviewHandler {

  public void onModuleLoad() {
    BrowserFactory browserFactory = BrowserFactoryImpl.getInstance();
    RootLayoutPanel.get().add(browserFactory.getAppContainer());
    browserFactory.getPlaceHistoryHandler().handleCurrentHistory();
    Event.addNativePreviewHandler(this);
  }

  public void onPreviewNativeEvent(Event.NativePreviewEvent event) {
    int pressedKey = event.getNativeEvent().getKeyCode();
    boolean ctrl = event.getNativeEvent().getCtrlKey();
    if(ctrl && (pressedKey == KeyCodes.KEY_SPACE)) {
      event.getNativeEvent().preventDefault();
      event.consume();
      SearchPopup searchPopup = SearchPopup.getInstance();
      searchPopup.open();
    }
  }

  public static void showErrorMessage(String errorMsg) {
    Window.alert(errorMsg);
  }

  public static void showNotification(String notificationMsg) {
    Window.alert(notificationMsg);
  }

}
