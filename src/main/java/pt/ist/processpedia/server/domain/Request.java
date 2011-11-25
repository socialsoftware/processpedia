package pt.ist.processpedia.server.domain;

import org.joda.time.DateTime;
import pt.ist.processpedia.server.domain.AtomicDataObjectVersion.DataObjectType;
import java.util.Set;

public class Request extends Request_Base {

  public enum RequestState { PUBLISHED, HANDLING, PENDING, HANDLED, CANCELED }

  /**
   * Creates a new request.
   * @param initiator the initiator of the request
   * @param expectsAnswer true if the requests expects an answer, false if not
   * @param inputDataObjectSet the set of data objects available to the executor
   */
  public Request(User initiator, Boolean expectsAnswer, Set<DataObject> inputDataObjectSet) {
    setInitiator(initiator);
    setExpectsAnswer(expectsAnswer);
    DateTime now = new DateTime();
    setCreationTimestamp(now);
    setLastUpdateTimestamp(now);
    setState(RequestState.PUBLISHED);
    for(DataObject inputDataObject : inputDataObjectSet) {
      inputDataObject.setInputRequest(this);
    }
  }

  /**
   * Creates a new data object within the context of this request.
   * @param type the data type of the data object
   * @param label the label identifying the data object
   * @param externalizedValue the externalized value of the data object
   * @return the newly created data object
   */
  public DataObject createDataObject(DataObjectType type, String label, String externalizedValue) {
    DataObject newDataObject = new DataObject(type, label, externalizedValue);
    newDataObject.setProcess(getProcess());
    addCreatedDataObject(newDataObject);
    return newDataObject;
  }

  /**
   * Creates a new request within the context of the request.
   * @param initiator the user initiating the request
   * @param title the title of the request to be created
   * @param expectsAnswer if a response is expected
   * @param publishedQueueSet the set of queues where the new request is to be published
   * @param inputDataObjectSet the set of data objects available to the executor
   * @return the created request
   */
  public Request createRequest(User initiator, String title, Boolean expectsAnswer, Set<Queue> publishedQueueSet, Set<DataObject> inputDataObjectSet) {
    Request request = createRequest(initiator, title, expectsAnswer, publishedQueueSet, inputDataObjectSet);
    request.setParentRequest(this);
    return request;
  }

  @Override
  public RequestState getState() {
    Set<Request> childRequestSet = getContextRequestSet();
    if(childRequestSet.size() != 0) {
      for(Request childRequest : childRequestSet) {
        if(!childRequest.getState().equals(RequestState.HANDLED)) {
          return RequestState.PENDING;
        }
      }
    }
    return super.getState();
  }

  public String getTitle() {
    return getSubject().getValue();
  }

}
