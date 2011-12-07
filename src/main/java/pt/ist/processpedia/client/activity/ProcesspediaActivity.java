package pt.ist.processpedia.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.client.Processpedia;
import pt.ist.processpedia.client.notification.ProcesspediaNotification;
import pt.ist.processpedia.client.notification.ProcesspediaNotificationImpl;
import pt.ist.processpedia.client.place.LoginPlace;
import pt.ist.processpedia.shared.exception.*;
import pt.ist.processpedia.shared.exception.email.EmailInvalidException;
import pt.ist.processpedia.shared.exception.email.EmailIsEmptyException;
import pt.ist.processpedia.shared.exception.password.PasswordIsEmptyException;
import pt.ist.processpedia.shared.exception.password.PasswordIsNullException;
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
    GWT.log(throwable.toString());
    Messages messages = browserFactory.getMessages();
    String errorTitle = messages.error();
    String errorMessage = "";
    try {
      throw throwable;
    } catch (UserAlreadyActiveException e) {
      errorMessage = messages.userAlreadyActive();
      ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.ERROR, errorTitle, errorMessage);
      notification.show();
    } catch (UserEmailAlreadyInUseException e) {
      errorMessage = messages.emailAlreadyInUse();
      ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.ERROR, errorTitle, errorMessage);
      notification.show();
    } catch (UserInactiveException e) {
      errorMessage = messages.inactiveUser();
      ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.ERROR, errorTitle, errorMessage);
      notification.show();
    } catch (EmailInvalidException e) {
      errorMessage = messages.invalidEmail();
      ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.ERROR, errorTitle, errorMessage);
      notification.show();
    } catch (PasswordIsNullException e) {
      errorMessage = messages.passwordIsEmpty();
      ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.ERROR, errorTitle, errorMessage);
      notification.show();
    } catch (PasswordIsEmptyException e) {
      errorMessage = messages.passwordIsEmpty();
      ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.ERROR, errorTitle, errorMessage);
      notification.show();
    } catch (PasswordTooShortException e) {
      errorMessage = messages.invalidPassword();
      ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.ERROR, errorTitle, errorMessage);
      notification.show();
    } catch (WrongActivationKeyException e) {
      errorMessage = messages.wrongActivationKey();
      ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.ERROR, errorTitle, errorMessage);
      notification.show();
    } catch (WrongCredentialsException e) {
      errorMessage = messages.wrongCredentialsWereProvided();
      ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.ERROR, errorTitle, errorMessage);
      notification.show();
    } catch (PasswordsDoNotMatchException e) {
      errorMessage = messages.passwordsDoNotMatch();
      ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.ERROR, errorTitle, errorMessage);
      notification.show();
    } catch (EmailIsEmptyException e) {
      errorMessage = messages.emailIsEmpty();
      ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.ERROR, errorTitle, errorMessage);
      notification.show();
    } catch (UnauthenticatedUserException e) {
      errorMessage = messages.unauthenticatedUser();
      ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.ERROR, errorTitle, errorMessage);
      notification.show();
      Storage.getSessionStorageIfSupported().clear();
      goTo(new LoginPlace());
    } catch (Throwable e) {
      errorMessage = messages.anUnknownErrorHasOccurred();
      ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.ERROR, errorTitle, errorMessage);
      notification.show();
    }
  }

}