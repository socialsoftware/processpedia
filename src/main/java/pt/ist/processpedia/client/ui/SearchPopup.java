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

package pt.ist.processpedia.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class SearchPopup extends PopupPanel {

  interface SearchPopupUiBinder extends UiBinder<Widget, SearchPopup> {}

  private static SearchPopupUiBinder uiBinder = GWT.create(SearchPopupUiBinder.class);

  private static final int KEY_SPACE = 32;

  @UiField
  TextBox searchBox;

  public final static SearchPopup INSTANCE = new SearchPopup();

  private SearchPopup() {
    setAutoHideEnabled(true);
    setStyleName("search-popup");
    setWidget(uiBinder.createAndBindUi(this));
  }

  @UiHandler("searchBox")
  public void onSearchBoxKeyDownEvent(KeyDownEvent keyDownEvent) {
    if(keyDownEvent.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
      String query = URL.encodeQueryString(searchBox.getText());
      //BrowserFactoryImpl.getInstance().getPlaceController().goTo(new SearchPlace(query));
      hide();
    } else if(keyDownEvent.getNativeKeyCode() == KeyCodes.KEY_ESCAPE) {
      hide();
    }
  }

  @Override
  public void hide() {
    searchBox.setText("");
    super.hide();
  }

  public void open() {
    super.center();
    super.show();
    searchBox.setFocus(true);
  }

  public static SearchPopup getInstance() {
    return INSTANCE;
  }

}
