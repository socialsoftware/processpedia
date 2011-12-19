package pt.ist.processpedia.client.activity.content.column.middle;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Label;

import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.activity.ProcesspediaActivity;
import pt.ist.processpedia.client.place.RequestPlace;

public class ShowRequestInteractionsActivity extends ProcesspediaActivity<RequestPlace> {

    public ShowRequestInteractionsActivity(RequestPlace place, BrowserFactory browserFactory) {
	super(place, browserFactory);
    }

    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	containerWidget.setWidget(new Label("This is the interactions of the request"));
    }
    
}
