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

package pt.ist.processpedia.client.view.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;

import java.util.Set;

public class Token extends Composite {

  interface TokenUiBinder extends UiBinder<Widget,Token> {}
  private static TokenUiBinder uiBinder = GWT.create(TokenUiBinder.class);

  private TokenTextBox tokenTextBox;

  @UiField
  HasText textContainer;

  public Token(TokenTextBox tokenTextBox, String text) {
    this.tokenTextBox = tokenTextBox;
    initWidget(uiBinder.createAndBindUi(this));
    textContainer.setText(text);
  }

  @UiHandler("removeAction")
  public void onRemoveActionClick(ClickEvent event) {
    tokenTextBox.removeToken(this);
  }

  public void setText(String text) {
    textContainer.setText(text);
  }

  public String getText() {
    return textContainer.getText();
  }


}
