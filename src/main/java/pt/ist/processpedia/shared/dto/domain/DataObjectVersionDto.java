package pt.ist.processpedia.shared.dto.domain;

import pt.ist.processpedia.shared.domain.DataObjectType;

public interface DataObjectVersionDto extends DomainObjectDto {

  public DataObjectVersionDto withType(DataObjectType type);
  public DataObjectVersionDto withLabel(String label);
  public DataObjectVersionDto withExternalizedValue(String externalizedValue);

  public DataObjectType getType();
  public String getLabel();
  public String getExternalizedValue();
  
}
