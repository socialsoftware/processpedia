package pt.ist.processpedia.server.domain;

import org.joda.time.DateTime;
import pt.ist.processpedia.server.domain.Request.RequestState;
import java.util.Set;

public class Process extends Process_Base {

  public Process(Party creator, String title, String description) {
    setCreator(creator);
    setTitleTag(Processpedia.getInstance().getTagManager().getTagForKeyword(title));
    setDescription(description);
    setCreationTimestamp(new DateTime());
  }

  /**
   * Creates a new request within the process
   * @param initiator the user initiating the request
   * @param subject the subject of the request
   * @param message the message explaining the request
   * @param expectsAnswer if a response is expected
   * @param publishedQueueSet the set of queues where the new request is to be published
   * @param inputDataObjectSet the set of data objects available to the executor
   * @return the created request
   */
  public Request createRequest(User initiator, String subject, String message, Boolean expectsAnswer, Set<Queue> publishedQueueSet, Set<DataObjectVersion> inputDataObjectVersionSet) {
    Request request = new Request(initiator, subject, message, expectsAnswer, publishedQueueSet, inputDataObjectVersionSet);
    request.setProcess(this);
    request.setState(RequestState.HANDLING);
    return request;
  }

}
