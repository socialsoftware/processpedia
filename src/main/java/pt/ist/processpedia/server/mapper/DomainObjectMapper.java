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

package pt.ist.processpedia.server.mapper;

import pt.ist.processpedia.server.domain.*;
import pt.ist.processpedia.server.domain.Process;
import pt.ist.processpedia.server.recommendation.RequestRecommendation;
import pt.ist.processpedia.shared.FolderType;
import pt.ist.processpedia.shared.dto.domain.*;
import pt.ist.processpedia.shared.dto.recommendation.RequestRecommendationDto;
import pt.ist.processpedia.shared.dto.recommendation.RequestRecommendationDtoImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DomainObjectMapper {

  public static OperatingParty getUserDtoFromUser(User user) {
    if(user == null)
      return null;
    return new UserDtoImpl(user.getOid(), user.getName(), user.getAvatarUrl());
  }
  
  public static OperatingPartyDto getOperatingPartyDtoFromOperatingParty(OperatingParty operatingParty) {
    if(operatingParty == null)
      return null;
    return new UserDtoImpl(operatingParty.getOid(), operatingParty.getName(), operatingParty.getAvatarUrl());
  }

  public static UserDetailedDtoImpl getPartyDetailedDtoFromParty(Party party) {
    if(party == null)
      return null;
    return new UserDetailedDtoImpl(party.getOid(), party.getName(), party.getAvatarUrl());
  }

  public static ProcessDtoImpl getProcessDtoFromProcess(Process process) {
    if(process==null)
      return null;
    return new ProcessDtoImpl(process.getOid(), process.getTitleTag().getKeyword());
  }

  public static ProcessDetailedDtoImpl getProcessDetailedDtoFromProcess(Process process) {
    if(process == null)
      return null;
    return new ProcessDetailedDtoImpl(process.getOid(), process.getTitleTag().getKeyword(), process.getDescription());
  }

  public static RequestDtoImpl getRequestDtoFromRequest(Request request) {
    if(request == null)
      return null;
    return new RequestDtoImpl(request.getOid(),
        request.getSubjectTag().getKeyword(),
        getOperatingPartyDtoFromOperatingParty(request.getInitiator()),
        getOperatingPartyDtoFromOperatingParty(request.getExecutor()),
        getQueueDtoSetFromQueueSet(request.getQueueSet()),
        request.getCreationTimestamp().toDate(),
        request.getLastUpdateTimestamp().toDate(),
        getProcessDtoFromProcess(request.getProcess()));
  }
  
  public static Set<RequestDto> getRequestDtoSetFromRequestSet(Set<Request> requestSet) {
    Set<RequestDto> requestDtoSet = new HashSet<RequestDto>();
    for(Request request : requestSet) {
      requestDtoSet.add(getRequestDtoFromRequest(request));
    }
    return requestDtoSet;
  }

  public static RequestDetailedDto getRequestDetailedDtoFromRequest(Request request) {
    if(request == null)
      return null;
    return new RequestDetailedDtoImpl(request.getOid(),
        request.getSubjectTag().getKeyword(),
        getCommentDtoFromComment(request.getDescriptionComment()),
        getPartyDetailedDtoFromParty(request.getInitiator()),
        getPartyDetailedDtoFromParty(request.getExecutor()),
        getQueueDtoSetFromQueueSet(request.getQueueSet()),
        request.getCreationTimestamp().toDate(),
        request.getLastUpdateTimestamp().toDate(),
        getProcessDetailedDtoFromProcess(request.getProcess()),
        getDataObjectVersionDtoSetFromDataObjectVersionSet(request.getCreatedDataObjectVersionSet()));
  }

  public static CommentDtoImpl getCommentDtoFromComment(Comment comment) {
    if(comment == null)
      return null;
    return new CommentDtoImpl(comment.getOid(),
        getPartyDetailedDtoFromParty(comment.getAuthor()),
        comment.getCreationTimestamp().toDate(),
        comment.getCommentaryText(),
        getCommentDtoFromComment(comment.getNextComment()));
  }

  public static Set<CommentDto> getCommentDtoSetFromCommentSet(Set<Comment> commentSet) {
    Set<CommentDto> commentDtoSet = new HashSet<CommentDto>();
    for(Comment comment : commentSet) {
      commentDtoSet.add(getCommentDtoFromComment(comment));
    }
    return commentDtoSet;
  }

  public static Set<DataObjectDtoImpl> getDataObjectDtoSetFromDataObjectSet(Set<DataObject> dataObjectSet) {
    Set<DataObjectDtoImpl> dataObjectDtoSet = new HashSet<DataObjectDtoImpl>();
    for(DataObject dataObject : dataObjectSet) {
      dataObjectDtoSet.add(getDataObjectDtoFromDataObject(dataObject));
    }
    return dataObjectDtoSet;
  }

  public static DataObjectDtoImpl getDataObjectDtoFromDataObject(DataObject dataObject) {
    return new DataObjectDtoImpl(dataObject.getOid(),
        getDataObjectVersionDtoFromDataObjectVersion(dataObject.getLastVersion()));
  }

  public static DataObjectVersionDtoImpl getDataObjectVersionDtoFromDataObjectVersion(DataObjectVersion dataObjectVersion) {
    return new DataObjectVersionDtoImpl(dataObjectVersion.getOid(),
        dataObjectVersion.getType(),
        dataObjectVersion.getLabelTag().getKeyword(),
        dataObjectVersion.getExternalizedValue());
  }

  public static Set<DataObjectVersionDtoImpl> getDataObjectVersionDtoSetFromDataObjectVersionSet(Set<DataObjectVersion> dataObjectVersionSet) {
    Set<DataObjectVersionDtoImpl> dataObjectVersionDtoSet = new HashSet<DataObjectVersionDtoImpl>();
    for(DataObjectVersion dataObjectVersion : dataObjectVersionSet) {
      dataObjectVersionDtoSet.add(getDataObjectVersionDtoFromDataObjectVersion(dataObjectVersion));
    }
    return dataObjectVersionDtoSet;
  }

  public static Set<QueueDtoImpl> getQueueDtoSetFromQueueSet(Set<Queue> queueSet) {
    Set<QueueDtoImpl> queueDtoSet = new HashSet<QueueDtoImpl>();
    for(Queue queue : queueSet) {
      queueDtoSet.add(new QueueDtoImpl(queue.getOid(), queue.getTitle()));
    }
    return queueDtoSet;
  }

  public static Queue getQueueFromQueueDto(QueueDtoImpl queueDto) {
    return Processpedia.fromOID(queueDto.getOid());
  }

  public static Set<Queue> getQueueSetFromQueueDtoSet(Set<QueueDtoImpl> queueDtoSet) {
    Set<Queue> queueSet = new HashSet<Queue>();
    for(QueueDtoImpl queueDto : queueDtoSet) {
      queueSet.add(getQueueFromQueueDto(queueDto));
    }
    return queueSet;
  }

  public static Set<DataObject> getDataObjectSetFromDataObjectDtoSet(Set<DataObjectDtoImpl> dataObjectDtoSet) {
    Set<DataObject> dataObjectSet = new HashSet<DataObject>();
    for(DataObjectDtoImpl dataObjectDto : dataObjectDtoSet) {
      DataObject dataObject = Processpedia.fromOID(dataObjectDto.getOid());
      if(dataObject!=null) {
        dataObjectSet.add(dataObject);
      }
    }
    return dataObjectSet;
  }

  public static Set<RequestRecommendationDto> getRequestRecommendationDtoSetFromRequestRecommendationSet(Set<RequestRecommendation> requestRecommendationSet) {
    Set<RequestRecommendationDto> requestRecommendationDtoSet = new HashSet<RequestRecommendationDto>();
    for(RequestRecommendation requestRecommendation : requestRecommendationSet) {
      requestRecommendationDtoSet.add(new RequestRecommendationDtoImpl(requestRecommendation.getRequestTitle(), requestRecommendation.getSupport()));
    }

    
    return requestRecommendationDtoSet;
  }

  public static Set<DataObjectVersion> getDataObjectVersionSetFromDataObjectVersionDtoSet(Set<DataObjectVersionDtoImpl> dataObjectVersionDtoSet) {
    Set<DataObjectVersion> dataObjectVersionSet = new HashSet<DataObjectVersion>();
    for(DataObjectVersionDtoImpl dataObjectVersionDto : dataObjectVersionDtoSet) {
      dataObjectVersionSet.add(getDataObjectVersionFromDataObjectVersionDto(dataObjectVersionDto));
    }
    
    return dataObjectVersionSet;
  }

  public static DataObjectVersion getDataObjectVersionFromDataObjectVersionDto(DataObjectVersionDtoImpl dataObjectVersionDto) {
    return Processpedia.fromOID(dataObjectVersionDto.getOid());
  }

  public static Map<FolderType,Set<RequestDto>> getFolderRequestDtoMapFromFolderRequestMap(Map<FolderType, Set<Request>> folderRequestMap) {
    Map<FolderType,Set<RequestDto>> folderRequestDtoMap = new HashMap<FolderType,Set<RequestDto>>();
    for(FolderType folderType : folderRequestMap.keySet()) {
      Set<RequestDto> requestDtoSet = new HashSet<RequestDto>();
      requestDtoSet.addAll(getRequestDtoSetFromRequestSet(folderRequestMap.get(folderType)));
      folderRequestDtoMap.put(folderType, requestDtoSet);
    }
    return folderRequestDtoMap;
  }
}
