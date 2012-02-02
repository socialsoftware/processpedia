package pt.ist.processpedia.shared.dto.response;

import java.util.Set;
import pt.ist.processpedia.shared.dto.recommendation.RequestRecommendationDto;

public class GetRequestRecommendationResponseDto extends ResponseDto {

  private static final long serialVersionUID = 1L;

  private Set<RequestRecommendationDto> requestRecommendationDtoSet;
  
  public GetRequestRecommendationResponseDto() {}
  
  public GetRequestRecommendationResponseDto(Set<RequestRecommendationDto> requestRecommendationSet) {
    setRequestRecommendationDtoSet(requestRecommendationSet);
  }
  
  public Set<RequestRecommendationDto> getRequestRecommendationSet() {
    return requestRecommendationDtoSet;
  }
  
  public void setRequestRecommendationDtoSet(Set<RequestRecommendationDto> requestRecommendationDtoSet) {
    this.requestRecommendationDtoSet = requestRecommendationDtoSet;
  }
}
