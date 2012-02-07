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

import com.google.gwt.user.client.ui.HasWidgets;
import pt.ist.processpedia.client.view.ProcesspediaView;
import pt.ist.processpedia.shared.dto.domain.RequestDto;

public interface ProcessView extends ProcesspediaView {

  interface Presenter extends ProcesspediaView.ProcesspediaPresenter {
    void onRequestSelectAction(RequestDto request);
  }

  void setPresenter(Presenter presenter);

  void setProcessTitle(String processTitle);
  void setProcessDescription(String processDescription);

  HasWidgets getRequestsContainer();

}
