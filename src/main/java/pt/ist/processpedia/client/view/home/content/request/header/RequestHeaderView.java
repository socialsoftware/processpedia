package pt.ist.processpedia.client.view.home.content.request.header;

import pt.ist.processpedia.client.view.ProcesspediaView;

public interface RequestHeaderView extends ProcesspediaView {

  interface Presenter extends ProcesspediaView.ProcesspediaPresenter {
    void onSelectRequestInitiatorAction();
    void onSelectRequestSenderAction();
  }
  
  public void setPresenter(Presenter presenter);
  
  public void setSubject(String subject);
    
  public void setInitiatorName(String initiatorName);

  public void setSenderName(String senderName);

}
