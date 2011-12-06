package pt.ist.processpedia.server.recommendation;

public class RequestRecommendation {

  private String requestTitle;
  private Double support;
  
  public RequestRecommendation(String requestTitle, Double support) {
    setRequestTitle(requestTitle);
    setSupport(support);
  }
  
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
