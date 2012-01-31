/**
 * Copyright 2011 ESW Software Engineering Group
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/

package pt.ist.processpedia.server;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import pt.ist.processpedia.server.auth.Authenticator;
import pt.ist.processpedia.server.auth.Authenticator.Credential;
import pt.ist.processpedia.server.auth.AuthenticatorFactory;
import pt.ist.processpedia.server.domain.*;
import pt.ist.processpedia.server.domain.Queue;
import pt.ist.processpedia.server.domain.credential.PasswordCredentialInfo;
import pt.ist.processpedia.server.mapper.DomainObjectMapper;
import pt.ist.processpedia.shared.FolderType;
import pt.ist.processpedia.shared.dto.action.*;
import pt.ist.processpedia.shared.dto.action.authenticaded.*;
import pt.ist.processpedia.shared.dto.auth.CredentialDto;
import pt.ist.processpedia.shared.dto.domain.*;
import pt.ist.processpedia.shared.dto.recommendation.RequestRecommendationDto;
import pt.ist.processpedia.shared.dto.recommendation.RequestRecommendationDtoImpl;
import pt.ist.processpedia.shared.dto.response.*;
import pt.ist.processpedia.shared.dto.util.FolderDto;
import pt.ist.processpedia.shared.exception.ProcesspediaException;
import pt.ist.processpedia.shared.exception.authentication.WrongCredentialTypeException;
import pt.ist.processpedia.shared.exception.credential.CredentialInfoIsWrongException;
import pt.ist.processpedia.shared.exception.user.UserNotAuthenticatedException;
import pt.ist.processpedia.shared.service.ProcesspediaService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.lang.System;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jvstm.Atomic;

public class ProcesspediaServiceImpl extends RemoteServiceServlet implements ProcesspediaService {

  private static final long serialVersionUID = 5095049948221761369L;

  @Override
  public String processCall(String payload) throws SerializationException {
    return super.processCall(payload);
  }

  @Override
  public void init() throws ServletException {
    Bootstrap.init();
  }

  private User getUserFromSession() throws ProcesspediaException {
	  HttpSession session = this.getThreadLocalRequest().getSession();
	  Long actorOid = (Long)session.getAttribute("actorOid");
	  if (actorOid == null) {
	    throw new UserNotAuthenticatedException();
    } else {
      try {
        User loggedUser = Processpedia.fromOID(actorOid);
        return loggedUser;
      } catch(NumberFormatException e) {
        throw new UserNotAuthenticatedException();
      }
    }
  }

  @Atomic
  public LoginUserResponseDto loginUser(LoginUserActionDto loginUserActionDto) throws ProcesspediaException {
    HttpSession httpSession = this.getThreadLocalRequest().getSession();
    CredentialDto credentialDto = loginUserActionDto.getCredentialDto();
    
    Authenticator authenticator = AuthenticatorFactory.getAuthenticatorFor(credentialDto);
    Credential credential = authenticator.getCredential(credentialDto);
    User user = authenticator.login(httpSession, credential);
    
    return new LoginUserResponseDto(DomainObjectMapper.getPartyDtoFromParty(user));
  }

  @Atomic
  public SignupUserResponseDto signupUser(SignupUserActionDto signupUserActionDto) throws ProcesspediaException {
    String name = signupUserActionDto.getName();
    String email = signupUserActionDto.getEmail();
    //String avatarUrl = signupUserActionDto.getAvatarUrl();
    String avatarUrl = "";
    String password = signupUserActionDto.getPassword();
    User user = Processpedia.getInstance().createUserWithPasswordCredentialInfo(name, email, avatarUrl, password);
    return new SignupUserResponseDto(user.getEmail());
  }

  @Atomic
  public GetUserSettingsResponseDto getUserSettings(GetUserSettingsActionDto getUserSettingsActionDto) throws ProcesspediaException {
    User actor = getUserFromAuthenticatedActionDto(getUserSettingsActionDto);
    return new GetUserSettingsResponseDto(actor.getName(), actor.getEmail());
  }

  @Atomic
  public UpdateUserSettingsResponseDto updateUserSettings(UpdateUserSettingsActionDto updateUserSettingsActionDto) throws ProcesspediaException {
    String newName = updateUserSettingsActionDto.getName();
    String newEmail = updateUserSettingsActionDto.getEmail();
    String newPassword = updateUserSettingsActionDto.getNewPassword();
    String currentPassword = updateUserSettingsActionDto.getCurrentPassword();

    User actor = getUserFromAuthenticatedActionDto(updateUserSettingsActionDto);
    
    if(actor.getCredentialInfo() instanceof PasswordCredentialInfo) {
      PasswordCredentialInfo credentialInfo = (PasswordCredentialInfo)actor.getCredentialInfo();
      if(credentialInfo.matchPassword(currentPassword)) {
        actor.updateSettings(newName, newEmail);
        credentialInfo.updatePassword(newPassword);
        return new UpdateUserSettingsResponseDto();
      } else {
        throw new CredentialInfoIsWrongException(actor.getEmail());
      }
    } else {
      throw new WrongCredentialTypeException();
    }
  }

  @Atomic
  public CreateProcessResponseDto createProcess(CreateProcessActionDto createProcessActionDto) throws ProcesspediaException {
    Processpedia processpedia = Processpedia.getInstance();
    User actor = getUserFromAuthenticatedActionDto(createProcessActionDto);

    Set<Queue> publishQueueSet = new HashSet<Queue>();
    publishQueueSet.add(actor.getPrivateQueue());
    
    Set<DataObjectVersion> inputDataObjectVersionSet = new HashSet<DataObjectVersion>();
    
    processpedia.createProcess(actor,
        createProcessActionDto.getProcessTitle(),
        createProcessActionDto.getProcessDescription(),
        createProcessActionDto.getProcessTitle(),
        createProcessActionDto.getProcessDescription(),
        true,
        publishQueueSet,
        inputDataObjectVersionSet);
    return new CreateProcessResponseDto();
  }



  @Atomic
  public GetQueueSetResponseDto getQueueSet(GetQueueSetActionDto getQueueSetActionDto) throws ProcesspediaException {
    getUserFromAuthenticatedActionDto(getQueueSetActionDto);
    Processpedia processpedia = Processpedia.getInstance();
    Set<QueueDto> queueDtoSet = DomainObjectMapper.getQueueDtoSetFromQueueSet(processpedia.getQueueSet());
    return new GetQueueSetResponseDto(queueDtoSet);
  }

  @Atomic
  public ActivateAccountResponseDto activateAccount(ActivateAccountActionDto activateAccountActionDto) throws ProcesspediaException {
    //String activationKey = activateAccountActionDto.getActivationKey();
    //User activatedUser = Processpedia.getInstance().activateAccount(activationKey);
    return new ActivateAccountResponseDto();
  }

  @Atomic
  public GetFolderListResponseDto getFolderList(GetFolderListActionDto getFolderListActionDto) throws ProcesspediaException {
    User actor = getUserFromAuthenticatedActionDto(getFolderListActionDto);
    
    List<FolderDto> folderDtoList = new ArrayList<FolderDto>();

    //TODO: MANAGE TO COUNT FOR THE GIVEN ACTOR, THE NUMBER OF NEW REQUESTS IN EACH QUEUE
    folderDtoList.add(new FolderDto(FolderType.INBOX, 0));
    folderDtoList.add(new FolderDto(FolderType.DRAFT, 0));
    folderDtoList.add(new FolderDto(FolderType.HANDLING, 0));
    folderDtoList.add(new FolderDto(FolderType.PENDING, 0));
    folderDtoList.add(new FolderDto(FolderType.HANDLED, 0));
 
    return new GetFolderListResponseDto(folderDtoList);
  }

  /**
   * Attempts to obtain the user associated to the user oid contained in a given authenticated action dto.
   * @param authenticatedActionDto the authenticated action dto containing the user oid
   * @return the session user matching the oid referenced in the authenticated action dto
   * @throws UserNotAuthenticatedException when the user oid contained in the authenticationActionDto mismatch the one defined in the current session
   */
  private User getUserFromAuthenticatedActionDto(AuthenticatedActionDto authenticatedActionDto) throws ProcesspediaException {
    System.out.println("THE NAME OF THE ACTION IS "+authenticatedActionDto.getClass().getSimpleName());
    System.out.println("THE RECEIVED ACTOR OID IS "+authenticatedActionDto.getActorOid());
    User sessionUser = getUserFromSession();
    return sessionUser;
  }

  @Atomic
  public GetFolderRequestSetResponseDto getFolderContents(GetFolderRequestSetActionDto getFolderRequestSetActionDto) throws ProcesspediaException {
    User actor = getUserFromAuthenticatedActionDto(getFolderRequestSetActionDto);    
    FolderType folderType = getFolderRequestSetActionDto.getFolderType();
    return new GetFolderRequestSetResponseDto(DomainObjectMapper.getRequestDtoSetFromRequestSet(actor.getFolderRequestSet(folderType)));
  }

  @Atomic
  public GetRequestResponseDto getRequest(GetRequestActionDto getRequestActionDto) throws ProcesspediaException {
    Request request = (Request)Processpedia.fromOID(getRequestActionDto.getRequestOid());
    return new GetRequestResponseDto(DomainObjectMapper.getRequestDetailedDtoFromRequest(request));
  }

  @Atomic
  public SearchResponseDto search(SearchActionDto searchActionDto) throws ProcesspediaException {
    User actor = getUserFromAuthenticatedActionDto(searchActionDto);
    String criteria = searchActionDto.getCriteria();
    String criteriaTokenRegex = "\"[a-zA-Z0-9 ]+\"|[a-zA-Z0-9]*";
    Set<String> criteriaTokenSet = new HashSet<String>();
    Pattern criteriaPattern = Pattern.compile(criteriaTokenRegex);
    Matcher criteriaMatcher = criteriaPattern.matcher(criteria);
    while(criteriaMatcher.find()) {
      String match = criteriaMatcher.toMatchResult().group().replace("\"","");
      if(!match.trim().equals("")) {
        criteriaTokenSet.add(match);
      }
    }
    Set<RequestDto> requestDtoSet = new HashSet<RequestDto>();
    String searchRegex = null;
    int i = 0;
    for(String criteriaToken : criteriaTokenSet) {
      if(i==0) {
        searchRegex = criteriaToken;
      } else {
        searchRegex = searchRegex+"|"+criteriaToken;
      }
      i++;
    }
    for(Request request : actor.getPrivateQueue().getRequestSet()) {
      Pattern searchPattern = Pattern.compile(searchRegex);
      Matcher searchMatcher = searchPattern.matcher(request.getSubjectTag().getKeyword());
      if(searchMatcher.find()) {
        requestDtoSet.add(DomainObjectMapper.getRequestDtoFromRequest(request));
      }
    }
    return new SearchResponseDto(requestDtoSet);

  }

  @Atomic
  public GetRequestRecommendationResponseDto getRequestRecommendation(GetRequestRecommendationAction getRequestRecommendationAction) throws ProcesspediaException {
    //long parentRequestOid = getRequestRecommendationAction.getParentRequestOid();
    //Request request = Processpedia.fromOID(parentRequestOid);
        
    Set<RequestRecommendationDto> requestRecommendationDtoSet = new HashSet<RequestRecommendationDto>();
    //TODO: THE FOLLOWING LINE IS FOR TEXTING PURPOSES ONLY
    requestRecommendationDtoSet.add(new RequestRecommendationDtoImpl("Fazer X", 0.98));
    requestRecommendationDtoSet.add(new RequestRecommendationDtoImpl("Fazer Y", 0.23));
    requestRecommendationDtoSet.add(new RequestRecommendationDtoImpl("Fazer Z", 0.56));
    return new GetRequestRecommendationResponseDto(requestRecommendationDtoSet);
  }

  @Atomic
  public CreateRequestResponseDto createRequest(CreateRequestActionDto createRequestActionDto) throws ProcesspediaException {
    User actor = getUserFromAuthenticatedActionDto(createRequestActionDto);
    
    String title = createRequestActionDto.getTitle();
    String description = createRequestActionDto.getDescription();
    Boolean expectsAnswer = createRequestActionDto.getResponseExpected();
    Set<Queue> publishedQueueSet = DomainObjectMapper.getQueueSetFromQueueDtoSet(createRequestActionDto.getQueueDtoSet());
    Set<DataObjectVersion> inputDataObjectVersionSet = DomainObjectMapper.getDataObjectVersionSetFromDataObjectVersionDtoSet(createRequestActionDto.getInputDataObjectVersionDtoSet());
    
    Request parentRequest = Processpedia.fromOID(createRequestActionDto.getParentRequestOid());
    Request createdRequest = parentRequest.createSubRequest(actor, title, description, expectsAnswer, publishedQueueSet, inputDataObjectVersionSet);
    
    return new CreateRequestResponseDto(createdRequest.getOid());

  }

  @Atomic
  public SaveDraftRequestActionDto saveDraftRequest(SaveDraftRequestActionDto saveDraftRequestActionDto) throws ProcesspediaException {
    return new SaveDraftRequestActionDto();
  }
}
