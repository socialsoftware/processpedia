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

package pt.ist.processpedia.client.view.home.content.splash;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

public abstract class SplashMessageViewImpl extends Composite implements SplashMessageView {

  interface SplashMessageViewImplUiBinder extends UiBinder<Widget,SplashMessageViewImpl> {}
  private static SplashMessageViewImplUiBinder uiBinder = GWT.create(SplashMessageViewImplUiBinder.class);

  private ProcesspediaPresenter presenter;

  @UiField
  Image imageContainer;

  @UiField
  HasText messageContainer;

  public SplashMessageViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setPresenter(ProcesspediaPresenter presenter) {
    this.presenter = presenter;
  }

  public ProcesspediaPresenter getPresenter() {
    return presenter;
  }

  public void setSplashImage(String imageUrl) {
    imageContainer.setUrl(imageUrl);
  }

  public void setMessage(String message) {
    messageContainer.setText(message);
  }
}
