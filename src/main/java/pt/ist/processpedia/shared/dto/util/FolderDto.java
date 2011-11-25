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

package pt.ist.processpedia.shared.dto.util;

import pt.ist.processpedia.shared.dto.Dto;

public class FolderDto implements Dto {

  public static enum FolderType { INBOX, HANDLING, PENDING, HANDLED }

  private FolderType type;
  private int count;

  public FolderDto() {}

  public FolderDto(FolderType type, int count) {
    setType(type);
    setCount(count);
  }

  public FolderType getType() {
    return type;
  }

  public void setType(FolderType type) {
    this.type = type;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
