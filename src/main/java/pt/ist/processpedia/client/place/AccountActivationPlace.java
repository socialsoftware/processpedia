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

public class AccountActivationPlace extends Place {

  private String activationKey;

  public AccountActivationPlace(String token) {
    this.activationKey = token;
  }

  public String getActivationKey() {
    return activationKey;
  }

  @Prefix("activateAccount")
  public static class Tokenizer implements PlaceTokenizer<AccountActivationPlace> {

    public AccountActivationPlace getPlace(String criteria) {
      return new AccountActivationPlace(criteria);
    }

    public String getToken(AccountActivationPlace place) {
      return place.getActivationKey();
    }
  }
}
