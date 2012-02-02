package pt.ist.processpedia.shared.dto.recommendation;

import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

public class RequestRecommendationDtoImpl implements RequestRecommendationDto, Suggestion {

  private static final long serialVersionUID = 1L;

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

  @Override
  public String getDisplayString() {
    return requestTitle;
  }

  @Override
  public String getReplacementString() {
    return requestTitle;
  }
}
