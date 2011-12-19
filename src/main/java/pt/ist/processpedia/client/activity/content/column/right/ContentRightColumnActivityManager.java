package pt.ist.processpedia.client.activity.content.column.right;

import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.place.RequestPlace;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;

public class ContentRightColumnActivityManager extends ActivityManager {

    public ContentRightColumnActivityManager(BrowserFactory browserFactory) {
	super(new ContentRightColumnActivityMapper(browserFactory), browserFactory.getEventBus());
	setDisplay(browserFactory.getRequestDetailedView().getRightColumnContainer());
    }

    @Override
    public void onPlaceChange(PlaceChangeEvent event) {
      Place newPlace = event.getNewPlace();
      if(newPlace instanceof RequestPlace) {
        super.onPlaceChange(event);
      }
    }
}
