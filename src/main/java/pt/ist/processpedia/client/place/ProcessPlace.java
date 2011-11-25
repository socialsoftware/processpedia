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

public class ProcessPlace extends Place {

  private Long processOid;

  public ProcessPlace(long processOid) {
    this.processOid = processOid;
  }

  public ProcessPlace(String token) {
    processOid = Long.parseLong(token);
  }

  public Long getProcessOid() {
    return processOid;
  }

  @Prefix("process")
  public static class Tokenizer implements PlaceTokenizer<ProcessPlace> {

    public ProcessPlace getPlace(String token) {
      return new ProcessPlace(token);
    }

    public String getToken(ProcessPlace place) {
      return place.getProcessOid().toString();
    }
  }
}
