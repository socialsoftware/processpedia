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

import pt.ist.processpedia.shared.domain.DataObjectType;

public class DataObjectVersionDtoImpl extends DomainObjectDtoImpl implements DataObjectVersionDto {
  
  private static final long serialVersionUID = 1L;
  
  private DataObjectType type;
  private String label;
  private String externalizedValue;

  public DataObjectVersionDtoImpl() {}
  
  public DataObjectVersionDto withType(DataObjectType type) {
    setType(type);
    return this;
  }
  
  public DataObjectType getType() {
    return type;
  }
  
  public void setType(DataObjectType type) {
    this.type = type;
  }
  
  public DataObjectVersionDto withLabel(String label) {
    setLabel(label);
    return this;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
  
  public DataObjectVersionDto withExternalizedValue(String externalizedValue) {
    setExternalizedValue(externalizedValue);
    return this;
  }
  
  public String getExternalizedValue() {
    return externalizedValue;
  }
  
  public void setExternalizedValue(String externalizedValue) {
    this.externalizedValue = externalizedValue;
  }
}
