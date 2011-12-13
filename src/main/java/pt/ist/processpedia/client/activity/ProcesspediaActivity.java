package pt.ist.processpedia.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.place.shared.Place;
import com.google.gwt.storage.client.Storage;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.view.ProcesspediaView;

public abstract class ProcesspediaActivity<P extends Place> extends AbstractActivity implements ProcesspediaView.ProcesspediaPresenter {

  private final P place;
  private final BrowserFactory browserFactory;

  /**
   * Creates a new Processpedia generic activity.
   * @param place the place that triggered the activity
   * @param browserFactory the browser factory
   */
  public ProcesspediaActivity(P place, BrowserFactory browserFactory) {
    this.place = place;
    this.browserFactory = browserFactory;
  }

  /**
   * Obtains the place that triggered the activity.
   * @return the place that enabled the activity
   */
  public P getPlace() {
    return place;
  }

  /**
   * Obtains the browser factory containing all the views and activity mappers.
   * @return the browser factory
   */
  public BrowserFactory getBrowserFactory() {
    return browserFactory;
  }

  /**
   * Forwards the application state to a given place.
   * @param place the new place where to contextualize the application
   */
  public final void goTo(Place place) {
    browserFactory.getPlaceController().goTo(place);
  }

  /**
   * Obtains the cookie set on the session storage of the browser of the current user.
   * @return the oid of the user currently logged in
   */
  public String getActorOid() {
    Storage sessionStorage = Storage.getSessionStorageIfSupported();
    return sessionStorage.getItem("actorOid");
  }

  /**
   * Defines the cookie that contains the user oid.
   * @param actorOid the oid of the actor
   */
  public void saveActorOid(String actorOid) {
    Storage sessionStorage = Storage.getSessionStorageIfSupported();
    sessionStorage.setItem("actorOid", actorOid);
  }

  /**
   * Defines the cookie that contains the user name.
   * @param actorName the name of the actor
   */
  public void saveActorName(String actorName) {
    Storage sessionStorage = Storage.getSessionStorageIfSupported();
    sessionStorage.setItem("actorName", actorName);
  }

  /**
   * Handles all the exceptions originated either locally or from web service calls.
   * @param throwable the exception to be handled
   */
  public void handleException(Throwable throwable) {
    browserFactory.getExceptionHandler().handle(throwable);
  }

}