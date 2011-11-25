package pt.ist.processpedia.server.domain;

public abstract class Queue extends Queue_Base {

  /**
   * Initializes a new queue with the specified title.
   * @param title the title of the queue
   */
  public void init(String title) {
    setTitle(title);
  }

}
