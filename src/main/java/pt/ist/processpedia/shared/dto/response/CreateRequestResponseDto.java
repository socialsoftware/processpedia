package pt.ist.processpedia.shared.dto.response;

public class CreateRequestResponseDto extends ResponseDto {
  
  private long requestOid;
  
  public CreateRequestResponseDto() {}
  
  public CreateRequestResponseDto(long requestOid) {
    setRequestOid(requestOid);
  }
  
  public long getRequestOid() {
    return requestOid;
  }
  
  public void setRequestOid(long requestOid) {
    this.requestOid = requestOid;
  }
}
