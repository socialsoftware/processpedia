package pt.ist.processpedia.shared.dto.auth;

public class CASCredentialDto implements CredentialDto {
  
  private String ticket;
  
  public CASCredentialDto() {}

  public CASCredentialDto(String ticket) {
    setTicket(ticket);
  }
  
  public String getTicket() {
    return ticket;
  }
  
  public void setTicket(String ticket) {
    this.ticket = ticket;
  }
}
