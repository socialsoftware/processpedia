package pt.ist.processpedia.client.activity.content.column.right;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Label;

import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.activity.ProcesspediaActivity;
import pt.ist.processpedia.client.place.RequestPlace;

public class ShowRequestDataActivity extends ProcesspediaActivity<RequestPlace> {

    public ShowRequestDataActivity(RequestPlace place, BrowserFactory browserFactory) {
	super(place, browserFactory);
    }
    
    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
	containerWidget.setWidget(new Label("Here comes the request data"));
    }

}
