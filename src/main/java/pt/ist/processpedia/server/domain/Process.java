package pt.ist.processpedia.server.domain;

import org.joda.time.DateTime;
import java.util.Set;

public class Process extends Process_Base {

  public Process(User initiator, String title, String description) {
    setInitiator(initiator);
    setTitle(title);
    setDescription(description);
    setCreationTimestamp(new DateTime());
  }

  /**
   * Creates a new request within the process
   * @param initiator the user initiating the request
   * @param requestTitle the title of the request to be created
   * @param expectsAnswer if a response is expected
   * @param publishedQueueSet the set of queues where the new request is to be published
   * @param inputDataObjectSet the set of data objects available to the executor
   * @return the created request
   */
  public Request createRequest(User initiator, String requestTitle, String requestDescription, Boolean expectsAnswer, Set<Queue> publishedQueueSet, Set<DataObject> inputDataObjectSet) {
    Request request = new Request(initiator, requestTitle, requestDescription, expectsAnswer, publishedQueueSet, inputDataObjectSet);
    request.setProcess(this);
    return request;
  }

}
