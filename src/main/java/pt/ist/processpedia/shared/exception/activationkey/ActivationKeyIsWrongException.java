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

package pt.ist.processpedia.shared.exception.activationkey;

import pt.ist.processpedia.shared.exception.ProcesspediaException;

public class ActivationKeyIsWrongException extends ProcesspediaException {

  private static final long serialVersionUID = 1L;
  
  private String wrongActivationKey;

  public ActivationKeyIsWrongException() {}

  public ActivationKeyIsWrongException(String wrongActivationKey) {
    setWrongActivationKey(wrongActivationKey);
  }

  public String getWrongActivationKey() {
    return wrongActivationKey;
  }

  public void setWrongActivationKey(String wrongActivationKey) {
    this.wrongActivationKey = wrongActivationKey;
  }

}
