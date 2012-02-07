package pt.ist.processpedia.shared.dto.util;

import java.util.Comparator;

import com.google.gwt.view.client.ProvidesKey;

import pt.ist.processpedia.shared.dto.domain.RequestDto;

public class RequestDataGridLine {
  
  public static final RequestIdComparator QUEUE_COMPARATOR = new RequestIdComparator();
  public static final SubjectComparator SUBJECT_COMPARATOR = new SubjectComparator();
  public static final ProcessTitleComparator PROCESS_TITLE_COMPARATOR = new ProcessTitleComparator();
  public static final LastUpdateTimestampComparator LAST_UPDATE_TIMESTAMP_COMPARATOR = new LastUpdateTimestampComparator();
  public static final SenderNameComparator SENDER_NAME_COMPARATOR = new SenderNameComparator();
  
  public static final ProvidesKey<RequestDto> KEY_PROVIDER = new ProvidesKey<RequestDto>() {
    public Object getKey(RequestDto requestDto) {
      return requestDto == null ? null : requestDto.getOid();
    }
  };
  

  private static class RequestIdComparator implements Comparator<RequestDto> {
    public int compare(RequestDto a, RequestDto b) {
      if(a == null)
        return -1;
      if(b == null)
        return 1;
      else return a.getOid().compareTo(b.getOid());
    }
  }
  
  private static class SubjectComparator implements Comparator<RequestDto> {
    public int compare(RequestDto a, RequestDto b) {
      if(a == null || a.getSubject()==null)
        return -1;
      if(b == null || b.getSubject()==null)
        return 1;
      else return a.getSubject().compareTo(b.getSubject());
    }
  }

  private static class ProcessTitleComparator implements Comparator<RequestDto> {
    public int compare(RequestDto a, RequestDto b) {
      if(a == null || a.getProcess() == null || a.getProcess().getTitle() == null)
        return -1;
      if(b == null || b.getProcess() == null || a.getProcess().getTitle() == null)
        return 1;
      else return a.getProcess().getTitle().compareTo(b.getProcess().getTitle());
    }
  }

  private static class LastUpdateTimestampComparator implements Comparator<RequestDto> {
    public int compare(RequestDto a, RequestDto b) {
      if(a == null || a.getLastUpdateTimestamp() == null)
        return -1;
      if(b == null || b.getLastUpdateTimestamp() == null)
        return 1;
      else return a.getLastUpdateTimestamp().compareTo(b.getLastUpdateTimestamp());
    }
  }

  private static class SenderNameComparator implements Comparator<RequestDto> {
    public int compare(RequestDto a, RequestDto b) {
      if(a == null || a.getOriginalInitiator() == null || a.getInitiator().getName() == null)
        return -1;
      if(b == null || b.getInitiator() == null || a.getInitiator().getName() == null)
        return 1;
      else return a.getInitiator().getName().compareTo(b.getInitiator().getName());
    }
  }
}
