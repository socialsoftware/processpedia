package pt.ist.processpedia.client.view.home.content.request.recommendation;

import java.util.Set;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.client.view.home.content.request.CreateRequestView;
import pt.ist.processpedia.shared.dto.recommendation.RequestRecommendationDto;

public class RequestRecommendationPanelViewImpl extends Composite implements RequestRecommendationPanelView {

  interface RequestRecommendationPanelViewImplUiBinder extends UiBinder<Widget,RequestRecommendationPanelViewImpl> {}
  private static RequestRecommendationPanelViewImplUiBinder uiBinder = GWT.create(RequestRecommendationPanelViewImplUiBinder.class);
  
  @UiField
  VerticalPanel container;
  
  @UiField
  HasText recommendationsTitleContainer;
  
  private CreateRequestView.Presenter presenter;
  
  public RequestRecommendationPanelViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  @Override
  public void prepareView() {
    Messages messages = presenter.getBrowserFactory().getMessages();
    recommendationsTitleContainer.setText(messages.recommendationsTitle());
    container.clear();
  }

  @Override
  public void setPresenter(CreateRequestView.Presenter presenter) {
    this.presenter = presenter;
  }

  public void displayRequestRecommendationSet(Set<RequestRecommendationDto> requestRecommendatioDtoSet) {
    for(RequestRecommendationDto requestRecommendationDto : requestRecommendatioDtoSet) {
      RequestRecommendationView requestRecommendationView = new RequestRecommendationViewImpl();
      requestRecommendationView.setPresenter(this.presenter);
      requestRecommendationView.setRequestRecommendationDto(requestRecommendationDto);
      container.add(requestRecommendationView.asWidget());
    }
  }

}
