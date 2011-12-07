package pt.ist.processpedia.client.notification;

public interface ProcesspediaNotification {

  String getTitle();
  String getText();
  int getTimeout();
  
  void show();
}
