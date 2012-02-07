package pt.ist.processpedia.client.view.home.content.request.list;

import java.util.Comparator;
import pt.ist.processpedia.client.BrowserFactoryImpl;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.shared.dto.domain.QueueDtoImpl;
import pt.ist.processpedia.shared.dto.domain.RequestDto;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;

public class RequestListColumn {

  public static abstract class RequestColumn<T,C> extends Column<T,C> implements Comparator<RequestDto> {
    public RequestColumn(Cell<C> cell) {
      super(cell);
    }
    public Comparator<RequestDto> asComparator() {
      return (Comparator<RequestDto>)this;
    }
    public abstract String getHeader();
  }
  
  private static final Messages messages = BrowserFactoryImpl.getInstance().getMessages();
  
  public static final RequestColumn<RequestDto,String> PROCESS_OID_COLUMN = new RequestColumn<RequestDto, String>(new TextCell()) {
    public String getValue(RequestDto requestDto) {
      return requestDto.getProcess().getOid().toString();
    }
    public int compare(RequestDto a, RequestDto b) {
      if(a == null || a.getProcess() == null)
        return -1;
      if(b == null || b.getProcess() == null)
        return 1;
      else return a.getProcess().getOid().compareTo(b.getProcess().getOid());
    }
    @Override
    public String getHeader() {
      return messages.process();
    }
  };
  
  public static final RequestColumn<RequestDto,String> PROCESS_TITLE_COLUMN = new RequestColumn<RequestDto, String>(new TextCell()) {
    public String getValue(RequestDto requestDto) {
      return requestDto.getProcess().getTitle();
    }
    public int compare(RequestDto a, RequestDto b) {
      if(a == null || a.getProcess() == null)
        return -1;
      if(b == null || b.getProcess() == null)
        return 1;
      else return a.getProcess().getTitle().compareTo(b.getProcess().getTitle());
    }
    @Override
    public String getHeader() {
      return messages.processTitle();
    }
  };

  public static final RequestColumn<RequestDto,String> REQUEST_OID_COLUMN = new RequestColumn<RequestDto, String>(new TextCell()) {
    public String getValue(RequestDto requestDto) {
      return requestDto.getOid().toString();
    }
    public int compare(RequestDto a, RequestDto b) {
      if(a == null)
        return -1;
      if(b == null)
        return 1;
      else return a.getOid().compareTo(b.getOid());
    }
    @Override
    public String getHeader() {
      return messages.request();
    }
  };

  public static final RequestColumn<RequestDto,String> SENDER_NAME_COLUMN = new RequestColumn<RequestDto, String>(new TextCell()) {
    public String getValue(RequestDto requestDto) {
      return requestDto.getInitiator().getName();
    }
    public int compare(RequestDto a, RequestDto b) {
      if(a == null || a.getInitiator() == null)
        return -1;
      if(b == null || b.getInitiator() == null)
        return 1;
      else return a.getInitiator().getName().compareTo(b.getInitiator().getName());
    }
    @Override
    public String getHeader() {
      return messages.from();
    }
  };
  
  public static final RequestColumn<RequestDto,String> CREATION_TIMESTAMP_COLUMN = new RequestColumn<RequestDto, String>(new TextCell()) {
    public String getValue(RequestDto requestDto) {
      return requestDto.getCreationTimestamp().toString();
    }
    public int compare(RequestDto a, RequestDto b) {
      if(a == null || a.getCreationTimestamp() == null)
        return -1;
      if(b == null || b.getCreationTimestamp() == null)
        return 1;
      else return a.getCreationTimestamp().compareTo(b.getCreationTimestamp());
    }
    @Override
    public String getHeader() {
      return messages.lastUpdate();
    }
  };
  
  public static final RequestColumn<RequestDto,String> LAST_UPDATE_TIMESTAMP_COLUMN = new RequestColumn<RequestDto, String>(new TextCell()) {
    public String getValue(RequestDto requestDto) {
      return requestDto.getLastUpdateTimestamp().toString();
    }
    public int compare(RequestDto a, RequestDto b) {
      if(a == null || a.getLastUpdateTimestamp() == null)
        return -1;
      if(b == null || b.getLastUpdateTimestamp() == null)
        return 1;
      else return a.getLastUpdateTimestamp().compareTo(b.getLastUpdateTimestamp());
    }
    @Override
    public String getHeader() {
      return messages.lastUpdate();
    }
  };
  
  public static final RequestColumn<RequestDto,String> SUBJECT_COLUMN = new RequestColumn<RequestDto, String>(new TextCell()) {
    public String getValue(RequestDto requestDto) {
      return requestDto.getSubject();
    }
    public int compare(RequestDto a, RequestDto b) {
      if(a == null || a.getSubject() == null)
        return -1;
      if(b == null || b.getSubject() == null)
        return 1;
      else return a.getSubject().compareTo(b.getSubject());
    }
    @Override
    public String getHeader() {
      return messages.requestTitle();
    }
  };
  
  public static final RequestColumn<RequestDto,String> NUMBER_PUBLISHED_QUEUE_COLUMN = new RequestColumn<RequestDto, String>(new TextCell()) {
    public String getValue(RequestDto requestDto) {
      QueueDtoImpl queueDto = (QueueDtoImpl)requestDto.getPublishedQueueSet().toArray()[0];
      return queueDto.getTitle();
    }
    public int compare(RequestDto a, RequestDto b) {
      if(a == null || a.getPublishedQueueSet() == null)
        return -1;
      if(b == null || b.getPublishedQueueSet() == null)
        return 1;
      else return new Integer(a.getPublishedQueueSet().size()).compareTo(new Integer(b.getPublishedQueueSet().size()));
    }
    @Override
    public String getHeader() {
      return "Num of Queues";
    }
  };
  
}
