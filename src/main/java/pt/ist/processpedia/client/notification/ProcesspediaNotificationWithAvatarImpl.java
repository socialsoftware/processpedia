package pt.ist.processpedia.client.notification;

public class ProcesspediaNotificationWithAvatarImpl extends ProcesspediaNotificationImpl implements ProcesspediaNotification {

  private String avatarUrl;
  
  public ProcesspediaNotificationWithAvatarImpl(String title, String text, int timeout, String avatarUrl) {
    super(title, text, timeout);
    setAvatarUrl(avatarUrl);
  }
  
  public ProcesspediaNotificationWithAvatarImpl(String title, String text, String avatarUrl) {
    super(title, text);
    setAvatarUrl(avatarUrl);
  }
  
  public String getAvatarUrl() {
    return avatarUrl;
  }
  
  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }
  
  private static native void renderNotificationWithAvatar(String notificationTitle, String notificationText, int timeout, String avatarUrl) /*-{
    $wnd.jQuery.gritter.add({
      title: notificationTitle,
      text: notificationText,
      image: avatarUrl
      time: timeout
    });
  }-*/;
  
  @Override
  public void show() {
    renderNotificationWithAvatar(getTitle(), getText(), getTimeout(), getAvatarUrl());
  }

}
