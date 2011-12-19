package pt.ist.processpedia.client.activity.content.column.middle;

import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.place.RequestPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class ContentMiddleColumnActivityMapper implements ActivityMapper {

    private BrowserFactory browserFactory;
    
    public ContentMiddleColumnActivityMapper(BrowserFactory browserFactory) {
	this.browserFactory = browserFactory;
    }
    
    @Override
    public Activity getActivity(Place place) {
	if(place instanceof RequestPlace) {
	    return new ShowRequestInteractionsActivity((RequestPlace) place, browserFactory);
	}
	return null;
    }

}
