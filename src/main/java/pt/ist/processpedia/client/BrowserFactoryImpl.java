package pt.ist.processpedia.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

import pt.ist.processpedia.client.activity.content.ContentActivityManager;
import pt.ist.processpedia.client.activity.content.ContentActivityMapper;
import pt.ist.processpedia.client.activity.header.HeaderActivityManager;
import pt.ist.processpedia.client.activity.header.HeaderActivityMapper;
import pt.ist.processpedia.client.activity.home.ProcesspediaActivityManager;
import pt.ist.processpedia.client.activity.home.ProcesspediaActivityMapper;
import pt.ist.processpedia.client.activity.sidebar.SidebarActivityManager;
import pt.ist.processpedia.client.activity.sidebar.SidebarActivityMapper;
import pt.ist.processpedia.client.auth.AuthenticationHandler;
import pt.ist.processpedia.client.exception.ExceptionHandler;
import pt.ist.processpedia.client.exception.ExceptionHandlerImpl;
import pt.ist.processpedia.client.place.HomePlace;
import pt.ist.processpedia.client.place.ProcesspediaPlaceHistoryMapper;
import pt.ist.processpedia.client.service.DataSwitch;
import pt.ist.processpedia.client.view.account.AccountActivationView;
import pt.ist.processpedia.client.view.account.AccountActivationViewImpl;
import pt.ist.processpedia.client.view.home.HomeView;
import pt.ist.processpedia.client.view.home.HomeViewImpl;
import pt.ist.processpedia.client.view.home.content.process.CreateProcessView;
import pt.ist.processpedia.client.view.home.content.process.CreateProcessViewImpl;
import pt.ist.processpedia.client.view.home.content.process.ProcessView;
import pt.ist.processpedia.client.view.home.content.process.ProcessViewImpl;
import pt.ist.processpedia.client.view.home.content.request.*;
import pt.ist.processpedia.client.view.home.content.request.line.ChildRequestLineView;
import pt.ist.processpedia.client.view.home.content.request.line.ChildRequestLineViewImpl;
import pt.ist.processpedia.client.view.home.content.splash.*;
import pt.ist.processpedia.client.view.home.header.HeaderView;
import pt.ist.processpedia.client.view.home.header.HeaderViewImpl;
import pt.ist.processpedia.client.view.home.content.settings.SettingsView;
import pt.ist.processpedia.client.view.home.content.settings.SettingsViewImpl;
import pt.ist.processpedia.client.view.home.sidebar.FolderListView;
import pt.ist.processpedia.client.view.home.sidebar.FolderListViewImpl;
import pt.ist.processpedia.client.view.home.sidebar.LoadingFoldersView;
import pt.ist.processpedia.client.view.home.sidebar.LoadingFoldersViewImpl;
import pt.ist.processpedia.client.view.login.LoginView;
import pt.ist.processpedia.client.view.login.LoginViewImpl;
import pt.ist.processpedia.client.view.signup.SignupView;
import pt.ist.processpedia.client.view.signup.SignupViewImpl;

public class BrowserFactoryImpl implements BrowserFactory {

  private static final EventBus EVENT_BUS = new SimpleEventBus();
  private static final PlaceController PLACE_CONTROLLER = new PlaceController(EVENT_BUS);

  private static ProcesspediaActivityMapper PROCESSPEDIA_ACTIVITY_MAPPER;
  private static ProcesspediaActivityManager PROCESSPEDIA_ACTIVITY_MANAGER;

  private static HeaderActivityMapper HEADER_ACTIVITY_MAPPER;
  private static HeaderActivityManager HEADER_ACTIVITY_MANAGER;

  private static ContentActivityMapper CONTENT_ACTIVITY_MAPPER;
  private static ContentActivityManager CONTENT_ACTIVITY_MANAGER;

  private static SidebarActivityMapper SIDEBAR_ACTIVITY_MAPPER;
  private static SidebarActivityManager SIDEBAR_ACTIVITY_MANAGER;
  
  private static ExceptionHandler EXCEPTION_HANDLER;
  
  private static final ProcesspediaPlaceHistoryMapper PROCESSPEDIA_PLACE_HISTORY_MAPPER = GWT.create(ProcesspediaPlaceHistoryMapper.class);
  private static final PlaceHistoryHandler PLACE_HISTORY_HANDLER = new PlaceHistoryHandler(PROCESSPEDIA_PLACE_HISTORY_MAPPER);
  
  private static final Messages MESSAGES = GWT.create(Messages.class);
  private static final SimpleLayoutPanel PROCESSPEDIA_CONTAINER = new SimpleLayoutPanel();

  private static final DataSwitch DATA_SWITCH = GWT.create(DataSwitch.class);
  private static final AuthenticationHandler AUTHENTICATION_HANDLER = GWT.create(AuthenticationHandler.class);

  private static final LoginView LOGIN_VIEW = new LoginViewImpl();
  private static final SignupView SIGNUP_VIEW = new SignupViewImpl();
  private static final AccountActivationView ACCOUNT_ACTIVATION_VIEW = new AccountActivationViewImpl();
  private static final HeaderView HEADER_VIEW = new HeaderViewImpl();
  private static final HomeView HOME_VIEW = new HomeViewImpl();
  private static final SettingsView SETTINGS_VIEW = new SettingsViewImpl();
  private static final CreateProcessView CREATE_PROCESS_VIEW = new CreateProcessViewImpl();
  private static final CreateRequestView CREATE_REQUEST_VIEW = new CreateRequestViewImpl();
  private static final FolderListView FOLDER_LIST_VIEW = new FolderListViewImpl();
  private static final RequestListView REQUEST_LIST_VIEW = new RequestListViewImpl();
  private static final RequestDetailedView REQUEST_DETAILED_VIEW = new RequestDetailedViewImpl();

  private static final ProcessView PROCESS_VIEW = new ProcessViewImpl();

  private static final NoFolderSelectedView NO_FOLDER_SELECTED_VIEW = new NoFolderSelectedViewImpl();
  private static final NoRequestsFoundView NO_REQUESTS_FOUND_VIEW = new NoRequestsFoundViewImpl();

  private static final LoadingFoldersView LOADING_FOLDERS_VIEW = new LoadingFoldersViewImpl();
  
  private static BrowserFactory INSTANCE = new BrowserFactoryImpl();

  private BrowserFactoryImpl() {

    PROCESSPEDIA_ACTIVITY_MAPPER = new ProcesspediaActivityMapper(this);
    //HEADER_ACTIVITY_MAPPER = new HeaderActivityMapper(this);
    //CONTENT_ACTIVITY_MAPPER = new ContentActivityMapper(this);
    //SIDEBAR_ACTIVITY_MAPPER = new SidebarActivityMapper(this);

    PROCESSPEDIA_ACTIVITY_MANAGER = new ProcesspediaActivityManager(this);
    //HEADER_ACTIVITY_MANAGER = new HeaderActivityManager(this);
    //CONTENT_ACTIVITY_MANAGER = new ContentActivityManager(this);
    //SIDEBAR_ACTIVITY_MANAGER = new SidebarActivityManager(this);

    EXCEPTION_HANDLER = new ExceptionHandlerImpl(this);
    
    PLACE_HISTORY_HANDLER.register(PLACE_CONTROLLER, EVENT_BUS, new HomePlace());
    
  }

  public static BrowserFactory getInstance() {
    if(INSTANCE==null) {
      INSTANCE = new BrowserFactoryImpl();
    }
    return INSTANCE;
  }

  public PlaceController getPlaceController() {
    return PLACE_CONTROLLER;
  }

  public PlaceHistoryHandler getPlaceHistoryHandler() {
    return PLACE_HISTORY_HANDLER;
  }

  public Messages getMessages() {
    return MESSAGES;
  }

  public Widget getAppContainer() {
    return PROCESSPEDIA_CONTAINER;
  }

  public LoginView getLoginView() { return LOGIN_VIEW; }
  public SignupView getSignupView() { return SIGNUP_VIEW; }
  public AccountActivationView getAccountActivationView() { return ACCOUNT_ACTIVATION_VIEW; }
  public HomeView getHomeView() { return HOME_VIEW; }
  public HeaderView getHeaderView() { return HEADER_VIEW; }
  public SettingsView getSettingsView() { return SETTINGS_VIEW; }
  public CreateProcessView getCreateProcessView() { return CREATE_PROCESS_VIEW; }
  public CreateRequestView getCreateRequestView() { return CREATE_REQUEST_VIEW; }
  public FolderListView getFolderListView() { return FOLDER_LIST_VIEW; }
  public RequestListView getProcessListView() { return REQUEST_LIST_VIEW; }
  public RequestDetailedView getRequestDetailedView() { return REQUEST_DETAILED_VIEW; }

  public ProcessView getProcessView() { return PROCESS_VIEW; }

  public ChildRequestLineView getChildRequestLineView() { return new ChildRequestLineViewImpl(); }

  public LoadingFoldersView getLoadingFoldersView() { return LOADING_FOLDERS_VIEW; }

  public LoadingMessageView getLoadingMessageView() {
    return new LoadingMessageViewImpl();
  }

  public NoFolderSelectedView getNoFolderSelectedView() { return NO_FOLDER_SELECTED_VIEW; }
  public NoRequestsFoundView getNoRequestsFoundView() { return NO_REQUESTS_FOUND_VIEW; }

  public DataSwitch getDataSwitch() {
    return DATA_SWITCH;
  }
  
  public AuthenticationHandler getAuthenticationHandler() {
    return AUTHENTICATION_HANDLER;
  }

  public ProcesspediaActivityMapper getProcesspediaActivityMapper() {
    return PROCESSPEDIA_ACTIVITY_MAPPER;
  }

  public EventBus getEventBus() {
    return EVENT_BUS;
  }

  public AcceptsOneWidget getProcesspediaContainer() {
    return PROCESSPEDIA_CONTAINER;
  }

  public ExceptionHandler getExceptionHandler() {
    return EXCEPTION_HANDLER;
  }
}
