package pt.ist.processpedia.client.notification;

public interface ProcesspediaNotification {

  public enum Type { INFO, WARNING, ERROR, SUCCESS }
  
  String getTitle();
  String getText();
  int getTimeout();
  
  void show();
}
