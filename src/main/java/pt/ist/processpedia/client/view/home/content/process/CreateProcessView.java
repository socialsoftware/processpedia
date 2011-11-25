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

import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import pt.ist.processpedia.client.view.ProcesspediaView;

public interface CreateProcessView extends ProcesspediaView {

  interface Presenter extends ProcesspediaPresenter {
    void onNextAction();
    void onCancelAction();
  }

  void setPresenter(Presenter presenter);

  void setCreateProcessTitle(String createProcessTitle);
  void setProcessTitleLabel(String processTitle);
  void setProcessDescriptionLabel(String processDescription);

  void setNextButtonText(String nextButtonText);
  void setCancelButtonText(String cancelButtonText);

  String getProcessTitle();
  String getProcessDescription();

}
