package pt.ist.processpedia.shared.validation;

import pt.ist.processpedia.shared.exception.*;
import pt.ist.processpedia.shared.exception.activationkey.ActivationKeyInvalidException;
import pt.ist.processpedia.shared.exception.activationkey.ActivationKeyIsNullException;
import pt.ist.processpedia.shared.exception.dataobject.DataObjectLabelIsEmptyException;
import pt.ist.processpedia.shared.exception.dataobject.DataObjectLabelIsNullException;
import pt.ist.processpedia.shared.exception.dataobject.DataObjectLabelTooLongException;
import pt.ist.processpedia.shared.exception.dataobject.DataObjectLabelTooShortException;
import pt.ist.processpedia.shared.exception.email.EmailInvalidException;
import pt.ist.processpedia.shared.exception.email.EmailIsEmptyException;
import pt.ist.processpedia.shared.exception.email.EmailIsNullException;
import pt.ist.processpedia.shared.exception.password.PasswordTooShortException;
import pt.ist.processpedia.shared.exception.process.*;
import pt.ist.processpedia.shared.exception.queue.QueueTitleIsEmptyException;
import pt.ist.processpedia.shared.exception.queue.QueueTitleIsNullException;
import pt.ist.processpedia.shared.exception.queue.QueueTitleTooLongException;
import pt.ist.processpedia.shared.exception.queue.QueueTitleTooShortException;
import pt.ist.processpedia.shared.exception.request.*;
import pt.ist.processpedia.shared.exception.user.UserNameIsEmptyException;
import pt.ist.processpedia.shared.exception.user.UserNameIsNullException;

public class InputValidator {

  private static final int PROCESS_TITLE_MIN_LENGTH = 4;
  private static final int PROCESS_TITLE_MAX_LENGTH = 50;
  private static final int REQUEST_TITLE_MIN_LENGTH = 4;
  private static final int REQUEST_TITLE_MAX_LENGTH = 50;
  private static final int PROCESS_DESCRIPTION_MIN_LENGTH = 1;
  private static final int PROCESS_DESCRIPTION_MAX_LENGTH = 100;
  private static final int REQUEST_DESCRIPTION_MIN_LENGTH = 1;
  private static final int REQUEST_DESCRIPTION_MAX_LENGTH = 250;
  private static final int DATA_OBJECT_LABEL_MIN_LENGTH = 1;
  private static final int DATA_OBJECT_LABEL_MAX_LENGTH = 10;
  private static final int QUEUE_TITLE_MIN_LENGTH = 1;
  private static final int QUEUE_TITLE_MAX_LENGTH = 20;
  private static final int PASSWORD_MIN_LENGTH = 4;
  private static final int ACTIVATION_KEY_LENGTH = 32;

  /********************
   * USER VALIDATIONS *
   ********************/

  public static boolean userNameIsEmpty(String userName) {
    return userName.length() == 0;
  }

  public static boolean userNameIsInvalid(String userName) {
    return !userName.contains(" ");
  }

  /**
   * Validates a user name that is not null, not empty and is composed at least by two names (first and last names).
   * @param userName the name of the user to be validated
   * @throws UserNameIsNullException when the provided name is null
   * @throws UserNameInvalidException when the provided name is empty
   * @throws UserNameInvalidException when the provided name does not contain a space separating first and last names
   */
  public static void validateUserName(String userName) throws UserNameIsNullException, UserNameIsEmptyException, UserNameInvalidException {
    if(userName == null)
      throw new UserNameIsNullException();
    else if(userNameIsEmpty(userName))
      throw new UserNameIsEmptyException();
    else if(userNameIsInvalid(userName))
      throw new UserNameInvalidException(userName);
  }

  public static boolean emailIsEmpty(String email) {
    return email.length() == 0;
  }

  public static boolean emailIsInvalid(String email) {
    return !email.contains("@");
  }

  /**
   * Validates an email that is not null, not empty and is well-formed.
   * @param email the email to be validated
   * @throws EmailIsNullException when the provided email is null
   * @throws EmailIsEmptyException when the provided email is empty
   * @throws EmailInvalidException when the provided email is not well-formed
   */
  public static void validateEmail(String email) throws EmailIsNullException, EmailIsEmptyException, EmailInvalidException {
    if(email == null)
      throw new EmailIsNullException();
    else if(emailIsEmpty(email))
      throw new EmailIsEmptyException();
    else if(emailIsInvalid(email))
      throw new EmailInvalidException(email);
  }






  /**********************
   * PROCESS VALIDATION *
   **********************/

  public static boolean isProcessTitleEmpty(String processTitle) {
    return processTitle.length() == 0;
  }

  public static boolean isProcessTitleTooShort(String processTitle) {
    return processTitle.length() < PROCESS_TITLE_MIN_LENGTH;
  }

  public static boolean isProcessTitleTooLong(String processTitle) {
    return processTitle.length() > PROCESS_TITLE_MAX_LENGTH;
  }

  /**
   * Validates a process title that is not null, empty, not too short and neither too long.
   * @param processTitle the process title to be validated
   * @throws ProcessTitleIsNullException when the provided process title is null
   * @throws ProcessTitleIsEmptyException when the provided process title is empty
   * @throws ProcessTitleTooShortException when the provided process title underachieves the pre-defined minimum value of PROCESS_TITLE_MIN_LENGTH
   * @throws ProcessTitleTooLongException when the provided process title exceeds the pre-defined maximum value of PROCESS_TITLE_MAX_LENGTH
   */
  public static void validateProcessTitle(String processTitle) throws ProcessTitleIsNullException, ProcessTitleIsEmptyException, ProcessTitleTooShortException, ProcessTitleTooLongException {
    if(processTitle == null)
      throw new ProcessTitleIsNullException();
    else if(isProcessTitleEmpty(processTitle))
      throw new ProcessTitleIsEmptyException();
    else if(isProcessTitleTooShort(processTitle))
      throw new ProcessTitleTooShortException(processTitle, PROCESS_TITLE_MIN_LENGTH);
    else if(isProcessTitleTooLong(processTitle))
      throw new ProcessTitleTooLongException(processTitle, PROCESS_TITLE_MAX_LENGTH);
  }


  public static boolean isProcessDescriptionNull(String processDescription) {
    return processDescription == null;
  }

  public static boolean isProcessDescriptionEmpty(String processDescription) {
    return processDescription.length() == 0;
  }

  public static boolean isProcessDescriptionTooShort(String processDescription) {
    return processDescription.length() < PROCESS_DESCRIPTION_MIN_LENGTH;
  }

  public static boolean isProcessDescriptionTooLong(String processDescription) {
    return processDescription.length() > PROCESS_DESCRIPTION_MAX_LENGTH;
  }

  /**
   * Validates a process description that is not null, empty, not too short and neither too long.
   * @param processDescription the process description to be validated
   * @throws ProcessDescriptionIsNullException when the provided process description is null
   * @throws ProcessDescriptionIsEmptyException when the provided process description is empty
   * @throws ProcessDescriptionTooShortException when the provided process description underachieves the pre-defined minimum value of PROCESS_DESCRIPTION_MIN_LENGTH
   * @throws ProcessDescriptionTooLongException when the provided process description exceeds the pre-defined maximum value of PROCESS_DESCRIPTION_MAX_LENGTH
   */
  public static void validateProcessDescription(String processDescription) throws ProcessDescriptionIsNullException, ProcessDescriptionIsEmptyException, ProcessDescriptionTooShortException, ProcessDescriptionTooLongException {
    if(isProcessDescriptionNull(processDescription))
      throw new ProcessDescriptionIsNullException();
    else if(isProcessDescriptionEmpty(processDescription))
      throw new ProcessDescriptionIsEmptyException();
    else if(isProcessDescriptionTooShort(processDescription))
      throw new ProcessDescriptionTooShortException(processDescription, PROCESS_DESCRIPTION_MIN_LENGTH);
    else if(isProcessDescriptionTooLong(processDescription))
      throw new ProcessDescriptionTooLongException(processDescription, PROCESS_DESCRIPTION_MAX_LENGTH);
  }

  /**********************
   * REQUEST VALIDATION *
   **********************/

  private static boolean requestTitleIsNull(String requestTitle) {
    return requestTitle == null;
  }

  private static boolean requestTitleIsEmpty(String requesTitle) {
    return requesTitle.length() == 0;
  }

  private static boolean requestTitleIsTooShort(String requestTitle) {
    return requestTitle.length() < REQUEST_TITLE_MIN_LENGTH;
  }

  private static boolean requestTitleIsTooLong(String requestTitle) {
    return requestTitle.length() > REQUEST_TITLE_MAX_LENGTH;
  }

  /**
   * Validates a request title that is not null, not empty, not too short and neither too long.
   * @param requestTitle the request title to be validated
   * @throws RequestTitleIsNullException when the provided request title is null
   * @throws RequestTitleIsEmptyException when the provided request title is empty
   * @throws RequestTitleTooShortException when the provided request title underachieves the pre-defined minimum value of REQUEST_TITLE_MIN_LENGTH
   * @throws RequestTitleTooLongException when the provided request title exceeds the pre-defined maximum value of REQUEST_TITLE_MAX_LENGTH
   */
  public static void validateRequestTitle(String requestTitle) throws RequestTitleIsNullException, RequestTitleIsEmptyException, RequestTitleTooShortException, RequestTitleTooLongException {
    if(requestTitleIsNull(requestTitle))
      throw new RequestTitleIsNullException();
    else if(requestTitleIsEmpty(requestTitle))
      throw new RequestTitleIsEmptyException();
    else if(requestTitleIsTooShort(requestTitle))
      throw new RequestTitleTooShortException(requestTitle, REQUEST_TITLE_MIN_LENGTH);
    else if(requestTitleIsTooLong(requestTitle))
      throw new RequestTitleTooLongException(requestTitle, REQUEST_TITLE_MAX_LENGTH);
  }

  private static boolean requestDescriptionIsNull(String requestDescription) {
    return requestDescription == null;
  }

  private static boolean requestDescriptionIsEmpty(String requestDescription) {
    return requestDescription.length() == 0;
  }

  private static boolean requestDescriptionIsTooShort(String requestDescription) {
    return requestDescription.length() < REQUEST_DESCRIPTION_MIN_LENGTH;
  }

  private static boolean requestDescriptionIsTooLong(String requestDescription) {
    return requestDescription.length() > REQUEST_DESCRIPTION_MAX_LENGTH;
  }

  /**
   * Validates a request description that is not null, not empty, not too short and neither too long.
   * @param requestDescription the request description to be validated
   * @throws RequestDescriptionIsNullException when the provided request description is null
   * @throws RequestDescriptionIsEmptyException when the provided request description is empty
   * @throws RequestDescriptionTooShortException when the provided request description underachieves the pre-defined minimum value of REQUEST_DESCRIPTION_MIN_LENGTH
   * @throws RequestDescriptionTooLongException when the provided request description exceeds the pre-defined maximum value of REQUEST_DESCRIPTION_MAX_LENGTH
   */
  public static void validateRequestDescription(String requestDescription) throws RequestDescriptionIsNullException, RequestDescriptionIsEmptyException, RequestDescriptionTooShortException, RequestDescriptionTooLongException {
    if(requestDescriptionIsNull(requestDescription))
      throw new RequestDescriptionIsNullException();
    else if(requestDescriptionIsEmpty(requestDescription))
      throw new RequestDescriptionIsEmptyException();
    else if(requestDescriptionIsTooShort(requestDescription))
      throw new RequestDescriptionTooShortException(requestDescription, REQUEST_DESCRIPTION_MIN_LENGTH);
    else if(requestDescriptionIsTooLong(requestDescription))
      throw new RequestDescriptionTooLongException(requestDescription, REQUEST_DESCRIPTION_MAX_LENGTH);
  }


  /**************************
   * DATA OBJECT VALIDATION *
   **************************/

  private static boolean dataObjectLabelIsNull(String dataObjectLabel) {
    return dataObjectLabel == null;
  }

  private static boolean dataObjectLabelIsEmpty(String dataObjectLabel) {
    return dataObjectLabel.length() == 0;
  }

  private static boolean dataObjectLabelIsTooShort(String dataObjectLabel) {
    return dataObjectLabel.length() < DATA_OBJECT_LABEL_MIN_LENGTH;
  }

  private static boolean dataObjectLabelIsTooLong(String dataObjectLabel) {
    return dataObjectLabel.length() > DATA_OBJECT_LABEL_MAX_LENGTH;
  }

  /**
   * Validates a data object label that is not null, not empyt, not too short and neither too long.
   * @param dataObjectLabel the data object label to be validated
   * @throws DataObjectLabelIsNullException when the provided label is null
   * @throws DataObjectLabelTooLongException when the provided label is empty
   * @throws DataObjectLabelTooLongException when the provided label underachieves the pre-defined minimum value of DATA_OBJECT_LABEL_MIX_LENGTH
   * @throws DataObjectLabelTooLongException when the provided label exceeds the pre-defined maximum value of DATA_OBJECT_LABEL_MAX_LENGTH
   */
  public static void validateDataObjectLabel(String dataObjectLabel) throws DataObjectLabelIsNullException, DataObjectLabelIsEmptyException, DataObjectLabelTooShortException, DataObjectLabelTooLongException {
    if(dataObjectLabelIsNull(dataObjectLabel))
      throw new DataObjectLabelIsNullException();
    else if(dataObjectLabelIsEmpty(dataObjectLabel))
      throw new DataObjectLabelIsEmptyException();
    else if(dataObjectLabelIsTooShort(dataObjectLabel))
      throw new DataObjectLabelTooShortException(dataObjectLabel, DATA_OBJECT_LABEL_MIN_LENGTH);
    else if(dataObjectLabelIsTooLong(dataObjectLabel))
      throw new DataObjectLabelTooLongException(dataObjectLabel, DATA_OBJECT_LABEL_MAX_LENGTH);
  }

  /*********************
   * QUEUES VALIDATION *
   *********************/

  private static boolean queueTitleIsNull(String queueTitle) {
    return queueTitle==null;
  }

  private static boolean queueTitleIsEmpty(String queueTitle) {
    return queueTitle.length()==0;
  }

  private static boolean queueTitleIsTooShort(String queueTitle) {
    return queueTitle.length() < QUEUE_TITLE_MIN_LENGTH;
  }

  private static boolean queueTitleIsTooLong(String queueTitle) {
    return queueTitle.length() > QUEUE_TITLE_MAX_LENGTH;
  }

  /**
   * Validates a queue title that is not null, not empty, not too short and neither too long.
   * @param queueTitle the queue title to be validated
   * @throws QueueTitleIsNullException when the provided title is null
   * @throws QueueTitleIsEmptyException when the provided title is empty
   * @throws QueueTitleTooShortException when the provided title underachieves the pre-defined minimum value of QUEUE_TITLE_MIN_LENGTH
   * @throws QueueTitleTooLongException when the provided title exceeds the pre-defined maximum value of QUEUE_TITLE_MAX_LENGTH
   */
  public static void validateQueueTitle(String queueTitle) throws QueueTitleIsNullException, QueueTitleIsEmptyException, QueueTitleTooShortException, QueueTitleTooLongException {
    if(queueTitleIsNull(queueTitle))
      throw new QueueTitleIsNullException();
    else if(queueTitleIsEmpty(queueTitle))
      throw new QueueTitleIsEmptyException();
    else if(queueTitleIsTooShort(queueTitle))
      throw new QueueTitleTooShortException(queueTitle, QUEUE_TITLE_MIN_LENGTH);
    else if(queueTitleIsTooLong(queueTitle))
      throw new QueueTitleTooLongException(queueTitle, QUEUE_TITLE_MAX_LENGTH);
  }


  /********************
   * USER VALIDATIONS *
   ********************/



  /**
   * Verifies if a password is at least PASSWORD_MIN_LENGTH character long.
   * @param password the password to be verified
   * @return true if the provided password is at least PASSWORD_MIN_LENGTH character long
   */
  public static boolean isValidPassword(String password) {
    return password!=null && password.length() >= PASSWORD_MIN_LENGTH;
  }

  /**
   * Verifies if a password is at least PASSWORD_MIN_LENGTH character wide.
   * @param password the password to be validated
   * @throws pt.ist.processpedia.shared.exception.password.PasswordTooShortException when the provided password is shorter than PASSWORD_MIN_LENGTH characters
   */
  public static void validateUserPassword(String password) throws PasswordTooShortException {
    if(!isValidPassword(password)) {
      throw new PasswordTooShortException();
    }
  }

  /******************************
   * ACTIVATION KEY VALIDATIONS *
   ******************************/

  public static boolean activationKeyIsNull(String activationKey) {
    return activationKey == null;
  }

  public static boolean activationKeyIsInvalid(String activationKey) {
    return activationKey.length() == ACTIVATION_KEY_LENGTH;
  }

  /**
   * Validates a activation key that is not null, and is ACTIVATION_KEY_LENGTH long.
   * @param activationKey the activation key to be validate
   * @throws ActivationKeyIsNullException when the provided activation key is null
   * @throws ActivationKeyInvalidException when the provided activation length is lesser or more than ACTIVATION_KEY_LENGTH
   */
  public static void validateActivationKey(String activationKey) throws ActivationKeyIsNullException, ActivationKeyInvalidException {
    if(activationKeyIsNull(activationKey))
      throw new ActivationKeyIsNullException();
    else if(activationKeyIsInvalid(activationKey))
      throw new ActivationKeyInvalidException(activationKey);
  }
}
