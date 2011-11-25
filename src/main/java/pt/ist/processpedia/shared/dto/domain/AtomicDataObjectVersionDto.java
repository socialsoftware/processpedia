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

package pt.ist.processpedia.shared.dto.domain;

public class AtomicDataObjectVersionDto extends DataObjectVersionDto {

  public enum DataObjectTypeDto { STRING, FILE }

  private DataObjectTypeDto dataObjectTypeDto;
  private String label;
  private String value;

  public AtomicDataObjectVersionDto() {}

  public AtomicDataObjectVersionDto(long oid, DataObjectTypeDto dataObjectTypeDto, String label, String value) {
    super(oid, label);
  }

  public DataObjectTypeDto getDataObjectTypeDto() {
    return dataObjectTypeDto;
  }

  public void setDataObjectTypeDto(DataObjectTypeDto dataObjectTypeDto) {
    this.dataObjectTypeDto = dataObjectTypeDto;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
