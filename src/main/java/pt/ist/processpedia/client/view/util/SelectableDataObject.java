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

import com.google.gwt.user.client.ui.*;

public class SelectableDataObject extends Composite {

  private FlowPanel container;
  private Tree dataObjectContainer;
  private CheckBox selectableDataObject;
  private EditableTextBox dataObjectValueTextBox;

  public SelectableDataObject(String label, String value) {
    container = new FlowPanel();
    selectableDataObject = new CheckBox(label);
    dataObjectValueTextBox = new EditableTextBox(value);
    TreeItem dataObjectItem = new TreeItem(selectableDataObject);
    dataObjectItem.addItem(dataObjectValueTextBox);

    dataObjectContainer = new Tree();
    dataObjectContainer.addItem(dataObjectItem);
    container.add(dataObjectContainer);
    initWidget(container);
  }

  public void setLabel(String label) {
    selectableDataObject.setText(label);
  }
}
