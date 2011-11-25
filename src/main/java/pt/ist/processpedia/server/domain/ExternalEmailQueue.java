package pt.ist.processpedia.server.domain;

public class ExternalEmailQueue extends ExternalEmailQueue_Base {

  /**
   * Creates a queue that acts as a proxy to a particular email address.
   * @param email the external email address
   */
  public ExternalEmailQueue(String email) {
    init(email);
  }

}
