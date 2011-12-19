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

public class RequestDetailedViewImpl extends Composite implements RequestDetailedView {

  interface RequestDetailedViewImplUiBinder extends UiBinder<Widget,RequestDetailedViewImpl> {}
  private static RequestDetailedViewImplUiBinder uiBinder = GWT.create(RequestDetailedViewImplUiBinder.class);
  
  @UiField
  AcceptsOneWidget headerContainer;

  @UiField
  AcceptsOneWidget leftColumnContainer;

  @UiField
  AcceptsOneWidget middleColumnContainer;
  
  @UiField
  AcceptsOneWidget rightColumnContainer;

  private Presenter presenter;

  public RequestDetailedViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public void prepareView() {

  }

    @Override
    public AcceptsOneWidget getHeaderContainer() {
        return headerContainer;
    }
  
    @Override
    public AcceptsOneWidget getLeftColumnContainer() {
        return leftColumnContainer;
    }
    
    @Override
    public AcceptsOneWidget getMiddleColumnContainer() {
	return middleColumnContainer;
    }
    
    @Override
    public AcceptsOneWidget getRightColumnContainer() {
        return rightColumnContainer;
    }
}
