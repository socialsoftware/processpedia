package pt.ist.processpedia.shared.dto.recommendation;

public class RequestRecommendationDtoImpl implements RequestRecommendationDto {
  
  private String requestTitle;
  private Double support;
  
  public RequestRecommendationDtoImpl() {}
  
  public RequestRecommendationDtoImpl(String requestTitle, Double support) {
    setRequestTitle(requestTitle);
    setSupport(support);
  }
  
  @Override
  public String getRequestTitle() {
    return requestTitle;
  }
  
  public void setRequestTitle(String requestTitle) {
    this.requestTitle = requestTitle;
  }
  
  public Double getSupport() {
    return support;
  }
  
  public void setSupport(Double support) {
    this.support = support;
  }
}
