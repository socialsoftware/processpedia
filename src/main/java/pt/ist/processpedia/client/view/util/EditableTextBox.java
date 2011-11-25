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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.*;
import pt.ist.processpedia.client.util.KeyCodes;

public class EditableTextBox extends Composite {

  private FlowPanel container;
  private Label label;
  private TextBox textBox;
  private Image editIcon;

  public EditableTextBox(String value) {
    editIcon = new Image("themes/default/images/edit-icon.png");
    editIcon.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent clickEvent) {
        setEditable(true);
      }
    });
    label = new Label(value);
    textBox = new TextBox();
    textBox.addKeyDownHandler(new KeyDownHandler() {
      public void onKeyDown(KeyDownEvent event) {
        if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          setEditable(false);
        }
      }
    });
    textBox.setValue(value);
    container = new FlowPanel();
    container.add(label);
    container.add(editIcon);
    initWidget(container);
  }
  

  public void setEditable(Boolean editable) {
    if(editable) {
      container.clear();
      container.add(textBox);
    } else {
      label.setText(textBox.getText());
      container.clear();
      container.add(label);
      container.add(editIcon);
    }
  }
}
