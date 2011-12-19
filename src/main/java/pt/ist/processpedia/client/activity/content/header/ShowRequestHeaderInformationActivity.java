package pt.ist.processpedia.client.activity.content.header;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Label;

import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.activity.ProcesspediaActivity;
import pt.ist.processpedia.client.place.RequestPlace;

public class ShowRequestHeaderInformationActivity extends ProcesspediaActivity<RequestPlace> {

    public ShowRequestHeaderInformationActivity(RequestPlace place, BrowserFactory browserFactory) {
	super(place, browserFactory);
    }

    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {

	containerWidget.setWidget(new Label("this is a header"));
    }
}
