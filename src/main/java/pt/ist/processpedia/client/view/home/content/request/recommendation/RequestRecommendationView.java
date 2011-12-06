package pt.ist.processpedia.client.view.home.content.request.recommendation;

import pt.ist.processpedia.client.view.ProcesspediaView;
import pt.ist.processpedia.client.view.home.content.request.CreateRequestView;
import pt.ist.processpedia.shared.dto.recommendation.RequestRecommendationDto;

public interface RequestRecommendationView extends ProcesspediaView {
  
  void setPresenter(CreateRequestView.Presenter presenter);
  
  void setRequestRecommendationDto(RequestRecommendationDto requestRecommendationDto);
  void setRequestTitle(String requestTitle);
  
}
