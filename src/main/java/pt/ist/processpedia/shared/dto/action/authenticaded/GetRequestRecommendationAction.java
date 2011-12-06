package pt.ist.processpedia.shared.dto.action.authenticaded;

public class GetRequestRecommendationAction extends AuthenticatedActionDto {
  
  private long parentRequestOid;
  
  public GetRequestRecommendationAction() {}
  
  public GetRequestRecommendationAction(long parentRequestOid) {
    setParentRequestOid(parentRequestOid);
  }
  
  public long getParentRequestOid() {
    return parentRequestOid;
  }
  
  public void setParentRequestOid(long parentRequestOid) {
    this.parentRequestOid = parentRequestOid;
  }
}
