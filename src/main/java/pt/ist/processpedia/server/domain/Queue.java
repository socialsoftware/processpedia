package pt.ist.processpedia.server.domain;

import java.util.HashSet;
import java.util.Set;

public abstract class Queue extends Queue_Base {

  public void init(String title) {
    setTitle(title);
  }
  
  public Set<Request> getNonExecutingPublishedRequestSet() {
    Set<Request> nonExecutingPublishedRequestSet = new HashSet<Request>();
    for(Request publishedRequest : getRequestSet()) {
      if(!publishedRequest.hasExecutor()) {
        nonExecutingPublishedRequestSet.add(publishedRequest);
      }
    }
    return nonExecutingPublishedRequestSet;
  }
}
