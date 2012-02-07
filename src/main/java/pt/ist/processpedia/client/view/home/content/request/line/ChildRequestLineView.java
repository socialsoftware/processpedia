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

import pt.ist.processpedia.client.view.ProcesspediaView;
import pt.ist.processpedia.client.view.home.content.request.RequestDetailedView;
import java.util.Date;

public interface ChildRequestLineView extends ProcesspediaView {

  void setPresenter(RequestDetailedView.Presenter presenter);

  void setOriginalInitiatorAvatar(String avatarUrl);
  void setInitiatorAvatar(String avatarUrl);
  void setSubject(String subject);
  void setOriginalInitiatorName(String name);
  void setInitiatorName(String name);
  void setDate(Date date);
  void setRequestStateImage(String requestStateImage);

}
