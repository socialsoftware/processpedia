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

import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import pt.ist.processpedia.client.view.ProcesspediaView;

public interface CreateRequestView extends ProcesspediaView {

  interface Presenter extends ProcesspediaPresenter {
    void onPublishRequestAction();
    void onCancelAction();
  }

  void setPresenter(Presenter presenter);

  void setCreateRequestTitle(String createRequestTitle);
  void setToLabel(String toLabel);
  void setRequestTitleLabel(String requestTitleLabel);
  void setRequestDescriptionLabel(String requestDescriptionLabel);
  void setExpectsAnswerLabel(String expectsAnswerLabel);
  void setRequestDataObjectLabel(String requestDataObjectLabel);

  void setPublishRequestButtonText(String publishRequestButtonText);
  void setCancelButtonText(String cancelButtonText);

  String getTo();
  String getRequestTitle();
  String getRequestDescription();
  Boolean getIsResponseExpected();

  MultiWordSuggestOracle getOracle();

}
