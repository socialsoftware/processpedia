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

package pt.ist.processpedia.client.view.home.content.process;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;

public class ProcessViewImpl extends Composite implements ProcessView {

  @UiField
  HasText processTitleContainer, processDescriptionContainer;

  private Presenter presenter;

  public void setProcessTitle(String processTitle) {
    processTitleContainer.setText(processTitle);
  }

  public void setProcessDescription(String processDescription) {
    processDescriptionContainer.setText(processDescription);
  }

  public void prepareView() {

  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public HasWidgets getRequestsContainer() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
