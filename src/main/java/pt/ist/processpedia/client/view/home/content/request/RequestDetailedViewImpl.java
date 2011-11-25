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

package pt.ist.processpedia.client.view.home.content.request;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import pt.ist.processpedia.client.view.home.content.process.header.ProcessHeaderViewImpl;
import pt.ist.processpedia.client.view.home.content.request.line.ChildRequestLineView;
import pt.ist.processpedia.shared.dto.domain.*;

public class RequestDetailedViewImpl extends Composite implements RequestDetailedView {

  interface RequestDetailedViewImplUiBinder extends UiBinder<Widget,RequestDetailedViewImpl> {}
  private static RequestDetailedViewImplUiBinder uiBinder = GWT.create(RequestDetailedViewImplUiBinder.class);

  @UiField
  ProcessHeaderViewImpl processHeader;

  @UiField
  RequestSidebarViewImpl requestSidebar;

  private Presenter presenter;

  public RequestDetailedViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public void prepareView() {
    processHeader.prepareView();
    requestSidebar.prepareView();
  }

  public void setProcessTitle(String processTitle) {
    processHeader.setProcessTitle(processTitle);
  }

  public void setProcessDescription(String processDescription) {
    processHeader.setProcessDescription(processDescription);
  }

  public void addChildRequest(ChildRequestLineView childRequestLinewView) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public void clear() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public void setParentRequest(RequestDetailedDto parentRequestDetailedDto) {
    requestSidebar.setPresenter(presenter);
    requestSidebar.prepareView();
    requestSidebar.setParentRequestLine(parentRequestDetailedDto);
  }
}
