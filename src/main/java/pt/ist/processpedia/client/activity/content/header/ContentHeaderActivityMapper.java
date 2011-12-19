package pt.ist.processpedia.client.activity.content.header;

import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.place.RequestPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class ContentHeaderActivityMapper implements ActivityMapper {

    private BrowserFactory browserFactory;
    
    public ContentHeaderActivityMapper(BrowserFactory browserFactory) {
	this.browserFactory = browserFactory;
    }
    
    @Override
    public Activity getActivity(Place place) {
	
	if(place instanceof RequestPlace) {
	    return new ShowRequestHeaderInformationActivity((RequestPlace) place, browserFactory);
	}
	
	return null;
    }

}
