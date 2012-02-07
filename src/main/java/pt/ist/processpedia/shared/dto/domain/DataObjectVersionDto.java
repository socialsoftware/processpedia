package pt.ist.processpedia.shared.dto.domain;

import pt.ist.processpedia.shared.domain.DataObjectType;

public interface DataObjectVersionDto extends DomainObjectDto {

  public DataObjectType getType();
  public String getLabel();
  public String getExternalizedValue();
  
}
