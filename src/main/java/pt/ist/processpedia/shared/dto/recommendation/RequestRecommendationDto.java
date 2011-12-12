package pt.ist.processpedia.shared.dto.recommendation;

import pt.ist.processpedia.shared.dto.Dto;

public interface RequestRecommendationDto extends Dto {

  String getRequestTitle();
  
  Double getSupport();
  
}
