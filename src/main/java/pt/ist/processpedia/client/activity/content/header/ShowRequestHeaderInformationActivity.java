package pt.ist.processpedia.client.activity.content.header;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.activity.ProcesspediaActivity;
import pt.ist.processpedia.client.place.RequestPlace;
import pt.ist.processpedia.client.view.home.content.request.header.RequestHeaderView;
import pt.ist.processpedia.shared.dto.action.authenticaded.GetRequestActionDto;
import pt.ist.processpedia.shared.dto.domain.RequestDetailedDto;
import pt.ist.processpedia.shared.dto.response.GetRequestResponseDto;

public class ShowRequestHeaderInformationActivity extends ProcesspediaActivity<RequestPlace> implements RequestHeaderView.Presenter {
  
  public ShowRequestHeaderInformationActivity(RequestPlace place, BrowserFactory browserFactory) {
    super(place, browserFactory);
  }

  @Override
  public void start(final AcceptsOneWidget containerWidget, EventBus eventBus) {
    Long requestOid = getPlace().getRequestOid();
    GetRequestActionDto getRequestActionDto = new GetRequestActionDto(getActorOid(), requestOid);
    this.getBrowserFactory().getDataSwitch().getRequest(getRequestActionDto, new AsyncCallback<GetRequestResponseDto>() {
      public void onFailure(Throwable throwable) {
        handleException(throwable);
      }
      public void onSuccess(GetRequestResponseDto getRequestResponseDto) {
        onGetRequestResponseDto(containerWidget, getRequestResponseDto);
      }
    });
  }
  
  private void onGetRequestResponseDto(AcceptsOneWidget containerWidget, GetRequestResponseDto getRequestResponseDto) {
    RequestDetailedDto requestDetailedDto = getRequestResponseDto.getRequestDetailedDto();
    RequestHeaderView requestHeaderView = getBrowserFactory().getRequestHeaderView();
    requestHeaderView.setPresenter(this);
    requestHeaderView.prepareView();
    requestHeaderView.setSubject(requestDetailedDto.getSubject());
    requestHeaderView.setProcessTitle(requestDetailedDto.getProcessDetailedDto().getTitle());
    requestHeaderView.setInitiatorName(requestDetailedDto.getInitiatorDetailedDto().getName());
    requestHeaderView.setSenderName(requestDetailedDto.getInitiatorDetailedDto().getName());
    containerWidget.setWidget(requestHeaderView);
  }

  @Override
  public void onSelectRequestInitiatorAction() {
    //TODO: GOTO USER PROFILE PAGE
  }

  @Override
  public void onSelectRequestSenderAction() {
    //TODO: GOTO USER PROFILE PAGE
  }
  
}
