package pt.ist.processpedia.client.view.home.content.request.recommendation;

import java.util.Set;

import pt.ist.processpedia.client.view.ProcesspediaView;
import pt.ist.processpedia.client.view.home.content.request.CreateRequestView;
import pt.ist.processpedia.shared.dto.recommendation.RequestRecommendationDto;

public interface RequestRecommendationPanelView extends ProcesspediaView {

  void setPresenter(CreateRequestView.Presenter presenter);
  
  void displayRequestRecommendationSet(Set<RequestRecommendationDto> requestRecommendationDtoSet);

}
