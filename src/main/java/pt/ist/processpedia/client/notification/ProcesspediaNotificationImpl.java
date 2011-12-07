package pt.ist.processpedia.client.notification;

public class ProcesspediaNotificationImpl implements ProcesspediaNotification {

  private static int DEFAULT_TIMEOUT = 3000;
  
  private Type type;
  private String title;
  private String text;
  private int timeout;
  
  public ProcesspediaNotificationImpl(Type type, String title, String text) {
    this(type, title, text, DEFAULT_TIMEOUT);
  }
  
  public ProcesspediaNotificationImpl(Type type, String title, String text, int timeout) {
    setType(type);
    setTitle(title);
    setText(text);
    setTimeout(timeout);
  }
  
  public Type getType() {
    return type;
  }
  
  public void setType(Type type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public String getText() {
    return text;
  }

  public int getTimeout() {
    return timeout;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public void setText(String text) {
    this.text = text;
  }
  
  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }
  
  private static native void renderNotification(String type, String title, String message, int timeout) /*-{
    $wnd.showMessage(type, title, message, timeout);
  }-*/;
  
  /*
  private static native void renderNotification(String notificationTitle, String notificationText, int timeout) /*-{
    $wnd.jQuery.gritter.add({
      title: notificationTitle,
      text: notificationText,
      time: timeout
    });
  }-*/

  @Override
  public void show() {
    renderNotification(type.toString().toLowerCase(), title, text, timeout);
  }

}
