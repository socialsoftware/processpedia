package pt.ist.processpedia.client.exception;

import pt.ist.processpedia.client.BrowserFactory;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.client.notification.ProcesspediaNotification;
import pt.ist.processpedia.client.notification.ProcesspediaNotificationImpl;
import pt.ist.processpedia.client.place.LoginPlace;
import pt.ist.processpedia.shared.exception.PasswordsDoNotMatchException;
import pt.ist.processpedia.shared.exception.UnauthenticatedUserException;
import pt.ist.processpedia.shared.exception.WrongActivationKeyException;
import pt.ist.processpedia.shared.exception.WrongCredentialsException;
import pt.ist.processpedia.shared.exception.email.EmailInvalidException;
import pt.ist.processpedia.shared.exception.email.EmailIsEmptyException;
import pt.ist.processpedia.shared.exception.password.PasswordIsEmptyException;
import pt.ist.processpedia.shared.exception.password.PasswordIsNullException;
import pt.ist.processpedia.shared.exception.password.PasswordTooShortException;
import pt.ist.processpedia.shared.exception.user.UserAlreadyActiveException;
import pt.ist.processpedia.shared.exception.user.UserEmailAlreadyInUseException;
import pt.ist.processpedia.shared.exception.user.UserInactiveException;

import com.google.gwt.core.client.GWT;

public class ExceptionHandlerImpl implements ExceptionHandler {

  private BrowserFactory browserFactory;
  
  public ExceptionHandlerImpl(BrowserFactory browserFactory) {
    this.browserFactory = browserFactory;
  }
  
  @Override
  public void handle(Throwable throwable) {
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
      browserFactory.getPlaceController().goTo(new LoginPlace());
    } catch (Throwable e) {
      errorMessage = messages.anUnknownErrorHasOccurred();
      ProcesspediaNotification notification = new ProcesspediaNotificationImpl(ProcesspediaNotification.Type.ERROR, errorTitle, errorMessage);
      notification.show();
    }
  }
  
  
  
}
