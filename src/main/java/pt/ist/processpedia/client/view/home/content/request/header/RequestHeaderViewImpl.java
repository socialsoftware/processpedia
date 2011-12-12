package pt.ist.processpedia.client.view.home.content.request.header;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class RequestHeaderViewImpl extends Composite implements RequestHeaderView {

  interface RequestHeaderViewImplUiBinder extends UiBinder<Widget,RequestHeaderViewImpl> {}
  private static RequestHeaderViewImplUiBinder uiBinder = GWT.create(RequestHeaderViewImplUiBinder.class);
  
  private ProcesspediaPresenter presenter;

  @UiField
  HasText subjectContainer;
  
  @UiField
  HasText processTitleContainer;
  
  @UiField
  HasText initiatorNameContainer, senderNameContainer;

  public RequestHeaderViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  @Override
  public void prepareView() {
    
  }
}
