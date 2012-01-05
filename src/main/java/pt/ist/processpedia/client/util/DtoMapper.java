package pt.ist.processpedia.client.util;

import pt.ist.processpedia.shared.dto.Dto;

public interface DtoMapper {

  public String externalize(Dto dto);
  
  public Dto internalize(String externalizedDto);
  
}
