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
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class RequestViewImpl extends Composite implements RequestView {

  interface RequestViewImplUiBinder extends UiBinder<Widget,RequestViewImpl> {}
  private static RequestViewImplUiBinder uiBinder = GWT.create(RequestViewImplUiBinder.class);

  public RequestViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public AcceptsOneWidget getRequestInfoContainer() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  public void prepareView() {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
