package pt.ist.processpedia.server.domain;

import java.util.HashSet;
import java.util.Set;
import pt.ist.processpedia.server.domain.Request.RequestState;
import pt.ist.processpedia.shared.FolderType;
import pt.ist.processpedia.shared.exception.ProcesspediaException;

public abstract class OperatingParty extends OperatingParty_Base {

  public void init(String name, String avatarUrl) throws ProcesspediaException {
    super.init(name, avatarUrl);
    Processpedia processpedia = Processpedia.getInstance();
    PrivateQueue privateQueue = processpedia.createPrivateQueue(name);
    setPrivateQueue(privateQueue);
  }
  
  private Set<Request> getInboxRequestSet() {
    Set<Request> inboxRequestSet = new HashSet<Request>();
    for(RolePlay rolePlay : getRolePlay()) {
      RoleAssociation roleAssociation = rolePlay.getRoleAssociation();
      Role role = roleAssociation.getRole();
      AccountabilityType accountabilityType = roleAssociation.getAccountabilityType();
      for(RolePlayQueue rolePlayQueue : role.getRolePlayQueueSet()) {
        if(rolePlayQueue.hasAccountabilityType(accountabilityType)) {
          inboxRequestSet.addAll(rolePlayQueue.getNonExecutingPublishedRequestSet());
        }
      }
    }
    inboxRequestSet.addAll(getPrivateQueue().getNonExecutingPublishedRequestSet());
    return inboxRequestSet;
  }
  
  private Set<Request> getDraftRequestSet() {
    Set<Request> draftRequestSet = new HashSet<Request>();
    for(Request initiatedRequest : this.getInitiatedRequestSet()) {
      if(initiatedRequest.getState().equals(RequestState.DRAFT)) {
        draftRequestSet.add(initiatedRequest);
      }
    }
    return draftRequestSet;
  }
  
  private Set<Request> getHandlingRequestSet() {
    Set<Request> handlingRequestSet = new HashSet<Request>();
    for(Request request : this.getExecutingRequestSet()) {
      if(request.getState().equals(RequestState.HANDLING)) {
        handlingRequestSet.add(request);
      }
    }
    return handlingRequestSet;
  }
  
  private Set<Request> getPendingRequestSet() {
    Set<Request> handledRequestSet = new HashSet<Request>();
    for(Request request : this.getExecutingRequestSet()) {
      if(request.getState().equals(RequestState.PENDING)) {
        handledRequestSet.add(request);
      }
    }
    return handledRequestSet;
  }
  
  private Set<Request> getHandledRequestSet() {
    Set<Request> handledRequestSet = new HashSet<Request>();
    for(Request request : this.getExecutingRequestSet()) {
      if(request.getState().equals(RequestState.HANDLED)) {
        handledRequestSet.add(request);
      }
    }
    return handledRequestSet;
  }
  
  public Set<Request> getFolderRequestSet(FolderType folderType) {
    switch(folderType) {
      case INBOX: return getInboxRequestSet();
      case DRAFT: return getDraftRequestSet();
      case HANDLING: return getHandlingRequestSet();
      case PENDING: return getPendingRequestSet();
      case HANDLED: return getHandledRequestSet();
      default: return new HashSet<Request>();
    }
  }
  
}
