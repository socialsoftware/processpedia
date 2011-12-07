package pt.ist.processpedia.client.notification;

public class ProcesspediaNotificationImpl implements ProcesspediaNotification {

  private static int DEFAULT_TIMEOUT = 3000;
  
  private String title;
  private String text;
  private int timeout;
  
  public ProcesspediaNotificationImpl(String title, String text) {
    this(title, text, DEFAULT_TIMEOUT);
  }
  
  public ProcesspediaNotificationImpl(String title, String text, int timeout) {
    setTitle(title);
    setText(text);
    setTimeout(timeout);
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
  
  private static native void renderNotification(String notificationTitle, String notificationText, int timeout) /*-{
    $wnd.jQuery.gritter.add({
      title: notificationTitle,
      text: notificationText,
      time: timeout
    });
  }-*/;

  @Override
  public void show() {
    renderNotification(title, text, timeout);
  }

}
