package pt.ist.processpedia.client.activity.content.column.left;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.place.RequestPlace;

public class ContentLeftColumnActivityMapper implements ActivityMapper {

    private BrowserFactory browserFactory;
    
    public ContentLeftColumnActivityMapper(BrowserFactory browserFactory) {
	this.browserFactory = browserFactory;
    }

    @Override
    public Activity getActivity(Place place) {
	if(place instanceof RequestPlace) {
	    return new ShowRequestSubRequestsActivity((RequestPlace) place, browserFactory);
	}
	return null;
    }    
}
