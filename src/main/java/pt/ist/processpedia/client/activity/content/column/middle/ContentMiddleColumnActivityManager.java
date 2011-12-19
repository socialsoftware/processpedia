package pt.ist.processpedia.client.activity.content.column.middle;

import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.place.RequestPlace;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;

public class ContentMiddleColumnActivityManager extends ActivityManager {

    public ContentMiddleColumnActivityManager(BrowserFactory browserFactory) {
	super(new ContentMiddleColumnActivityMapper(browserFactory), browserFactory.getEventBus());
	setDisplay(browserFactory.getRequestDetailedView().getMiddleColumnContainer());
    }
    
    @Override
    public void onPlaceChange(PlaceChangeEvent event) {
	Place newPlace = event.getNewPlace();
	if(newPlace instanceof RequestPlace) {
	    super.onPlaceChange(event);
    	}
    }
}
