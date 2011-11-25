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

package pt.ist.processpedia.client.view.home.content.request.line;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

import pt.ist.processpedia.client.view.home.content.request.RequestDetailedView;
import pt.ist.processpedia.shared.dto.domain.RequestDetailedDtoImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChildRequestLineViewImpl extends Composite implements ChildRequestLineView {

  interface ChildRequestLineViewImplUiBinder extends UiBinder<Widget,ChildRequestLineViewImpl> {}
  private static ChildRequestLineViewImplUiBinder uiBinder = GWT.create(ChildRequestLineViewImplUiBinder.class);

  @UiField
  HasText requestTitleContainer,
          initiatorNameContainer,
          dateContainer;

  @UiField
  Image initiatorImage, requestStateImage;

  private RequestDetailedView.Presenter presenter;

  public ChildRequestLineViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void prepareView() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  public void setInitiatorImage(String initiatorImageUrl) {
    initiatorImage.setUrl(initiatorImageUrl);
  }

  public void setRequestTitle(String requestTitle) {
    requestTitleContainer.setText(requestTitle);
  }

  public void setInitiatorName(String initiatorName) {
    initiatorNameContainer.setText(initiatorName);
  }

  public void setDate(Date date) {
    dateContainer.setText(date.toString());
  }

  public void setRequestStateImage(String requestStateImageUrl) {
    requestStateImage.setUrl(requestStateImageUrl);
  }

  public void setPresenter(RequestDetailedView.Presenter presenter) {
    this.presenter = presenter;
  }

  public void setRequestDetailedDto(RequestDetailedDtoImpl requestDetailedDto) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
