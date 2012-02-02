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

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import pt.ist.processpedia.client.util.KeyCodes;
import pt.ist.processpedia.shared.domain.DataObjectType;
import pt.ist.processpedia.shared.dto.domain.DataObjectVersionDtoImpl;

import java.util.HashSet;
import java.util.Set;

public class DataObjectCreationBox extends Composite {

  private FlowPanel container;
  private ListBox dataObjectTypeListBox;
  private TextBox labelTextBox;

  private Set<DataObjectCreationHandler> dataObjectCreationHandlerSet;

  public DataObjectCreationBox() {
    dataObjectCreationHandlerSet = new HashSet<DataObjectCreationHandler>();
    dataObjectTypeListBox = new ListBox(false);
    for(DataObjectType dataObjectTypeDto : DataObjectType.values()) {
      dataObjectTypeListBox.addItem(dataObjectTypeDto.toString());
    }
    labelTextBox = new TextBox();
    labelTextBox.addKeyDownHandler(new KeyDownHandler() {
      public void onKeyDown(KeyDownEvent event) {
        if(event.getNativeKeyCode()==KeyCodes.KEY_ENTER) {
          notifySubscribers();
        }
      }
    });
    labelTextBox.getElement().setPropertyString("placeholder","new data object label");
    container = new FlowPanel();
    container.setWidth("100%");
    container.add(dataObjectTypeListBox);
    container.add(labelTextBox);
    initWidget(container);
  }

  private void notifySubscribers() {
    for(DataObjectCreationHandler dataObjectCreationHandler : dataObjectCreationHandlerSet) {
      dataObjectCreationHandler.onDataObjectCreation(new DataObjectVersionDtoImpl(1L, DataObjectType.FILE, labelTextBox.getText(), ""));
    }
    labelTextBox.setText("");
  }

  public interface DataObjectCreationHandler {
    public void onDataObjectCreation(DataObjectVersionDtoImpl atomicDataObjectVersionDto);
  }

  public void addDataObjectCreationHandler(DataObjectCreationHandler dataObjectCreationHandler) {
    dataObjectCreationHandlerSet.add(dataObjectCreationHandler);
  }

}
