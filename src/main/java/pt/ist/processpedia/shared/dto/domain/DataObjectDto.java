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

public class DataObjectDto extends DomainObjectDtoImpl {

  private DataObjectVersionDto dataObjectVersionDto;

  public DataObjectDto() {}

  public DataObjectDto(long oid, DataObjectVersionDto dataObjectVersionDto) {
    super(oid);
    setDataObjectVersionDto(dataObjectVersionDto);
  }

  public DataObjectVersionDto getDataObjectVersionDto() {
    return dataObjectVersionDto;
  }

  public void setDataObjectVersionDto(DataObjectVersionDto dataObjectVersionDto) {
    this.dataObjectVersionDto = dataObjectVersionDto;
  }
}
