package pt.ist.processpedia.client.view.home.content.request.recommendation;

import pt.ist.processpedia.client.view.home.content.request.CreateRequestView;
import pt.ist.processpedia.shared.dto.recommendation.RequestRecommendationDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class RequestRecommendationViewImpl extends Composite implements RequestRecommendationView {
  
  interface RequestRecommendationViewImplUiBinder extends UiBinder<Widget,RequestRecommendationViewImpl> {}
  private static RequestRecommendationViewImplUiBinder uiBinder = GWT.create(RequestRecommendationViewImplUiBinder.class);
  
  private CreateRequestView.Presenter presenter;
  
  @UiField
  HasText requestTitleContainer;
  
  private RequestRecommendationDto requestRecommendationDto;
  
  public RequestRecommendationViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  @Override
  public void setPresenter(CreateRequestView.Presenter presenter) {
    this.presenter = presenter;
  }
  
  @Override
  public void prepareView() {
    requestTitleContainer.setText("");
  }
  
  public void setRequestRecommendationDto(RequestRecommendationDto requestRecommendationDto) {
    this.requestRecommendationDto = requestRecommendationDto;
    setRequestTitle(requestRecommendationDto.getRequestTitle());
  }
  
  @UiHandler("container")
  public void onSelectRequestRecommendation(ClickEvent clickEvent) {
    presenter.onSelectRequestRecommendationAction(requestRecommendationDto);
  }

  @Override
  public void setRequestTitle(String requestTitle) {
    requestTitleContainer.setText(requestTitle);
  }



}