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

package pt.ist.processpedia.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class CreateRequestPlace extends Place {

  private String processId;
  private String parentRequestId;

  public CreateRequestPlace(String token) {
    String[] tokens = token.split(":");
    processId = tokens[0];
    parentRequestId = tokens[1];
  }

  public String getProcessId() {
    return processId;
  }

  public String getParentRequestId() {
    return parentRequestId;
  }

  @Prefix("createRequest")
  public static class Tokenizer implements PlaceTokenizer<CreateRequestPlace> {

    public CreateRequestPlace getPlace(String token) {
      return new CreateRequestPlace(token);
    }

    public String getToken(CreateRequestPlace place) {
      return place.getProcessId()+":"+place.getParentRequestId();
    }
  }
}
