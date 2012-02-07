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

public class DataObjectDtoImpl extends DomainObjectDtoImpl implements DataObjectDto {

  private static final long serialVersionUID = 1L;

  private DataObjectVersionDto dataObjectVersion;

  public DataObjectDtoImpl() {}

  public DataObjectDtoImpl(long oid, DataObjectVersionDto dataObjectVersion) {
    super(oid);
    setCurrentDataObjectVersion(dataObjectVersion);
  }

  public DataObjectVersionDto getDataObjectVersionDto() {
    return dataObjectVersion;
  }

  public void setCurrentDataObjectVersion(DataObjectVersionDto dataObjectVersion) {
    this.dataObjectVersion = dataObjectVersion;
  }
}
