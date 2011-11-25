package pt.ist.processpedia.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.place.shared.Place;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.client.Processpedia;
import pt.ist.processpedia.client.place.LoginPlace;
import pt.ist.processpedia.shared.exception.*;
import pt.ist.processpedia.shared.exception.email.EmailInvalidException;
import pt.ist.processpedia.shared.exception.password.PasswordTooShortException;
import pt.ist.processpedia.shared.exception.user.UserAlreadyActiveException;
import pt.ist.processpedia.shared.exception.user.UserEmailAlreadyInUseException;
import pt.ist.processpedia.shared.exception.user.UserInactiveException;

public abstract class ProcesspediaActivity<P extends Place> extends AbstractActivity {

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
   * Handles all the exceptions originated either localy or from web service calls.
   * @param throwable the exception to be handled
   */
  public void handleException(Throwable throwable) {
    Messages messages = browserFactory.getMessages();
    try {
      throw throwable;
    } catch (UserAlreadyActiveException e) {
      Processpedia.showErrorMessage(messages.userAlreadyActive());
    } catch (UserEmailAlreadyInUseException e) {
      Processpedia.showErrorMessage(messages.emailAlreadyInUse());
    } catch (UserInactiveException e) {
      Processpedia.showErrorMessage(messages.inactiveUser());
    } catch (EmailInvalidException e) {
      Processpedia.showErrorMessage(messages.invalidEmail());
    } catch (PasswordTooShortException e) {
      Processpedia.showErrorMessage(messages.invalidPassword());
    } catch (WrongActivationKeyException e) {
      Processpedia.showErrorMessage(messages.wrongActivationKey());
    } catch (WrongCredentialsException e) {
      Processpedia.showErrorMessage(messages.wrongCredentialsWereProvided());
    } catch (PasswordsDoNotMatchException e) {
      Processpedia.showErrorMessage(messages.passwordsDoNotMatch());
    } catch (UnauthenticatedUserException e) {
      Processpedia.showErrorMessage(messages.unauthenticatedUser());
      Storage.getSessionStorageIfSupported().clear();
      goTo(new LoginPlace());
    } catch (Throwable e) {
      Window.alert(e.getMessage());
      Processpedia.showErrorMessage(messages.anUnknownErrorHasOccurred());
    }
  }

}