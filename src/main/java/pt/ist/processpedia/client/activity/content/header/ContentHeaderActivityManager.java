package pt.ist.processpedia.client.activity.content.header;

import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.place.RequestPlace;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.web.bindery.event.shared.EventBus;

public class ContentHeaderActivityManager extends ActivityManager {

    public ContentHeaderActivityManager(BrowserFactory browserFactory) {
	super(new ContentHeaderActivityMapper(browserFactory), browserFactory.getEventBus());
	setDisplay(browserFactory.getRequestDetailedView().getHeaderContainer());
    }

    @Override
    public void onPlaceChange(PlaceChangeEvent event) {
	Place newPlace = event.getNewPlace();
	if(newPlace instanceof RequestPlace) {
	    super.onPlaceChange(event);
	}
    }
}
