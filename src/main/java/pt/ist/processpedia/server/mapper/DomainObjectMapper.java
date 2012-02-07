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
import pt.ist.processpedia.server.domain.OperatingParty;
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

  public static UserDto getUserDtoFromUser(User user) {
    if(user == null)
      return null;
    return new UserDtoImpl(user.getOid(), user.getName(), user.getAvatarUrl());
  }

  public static OperatingPartyDto getOperatingPartyDtoFromOperatingParty(OperatingParty operatingParty) {
    if(operatingParty == null)
      return null;
    return new UserDtoImpl(operatingParty.getOid(), operatingParty.getName(), operatingParty.getAvatarUrl());
  }

  public static ProcessDto getProcessDtoFromProcess(Process process) {
    if(process==null)
      return null;
    return new ProcessDtoImpl(process.getOid(), process.getTitle(), process.getDescription());
  }

  public static RequestDto getRequestDtoFromRequest(Request request) {
    if(request == null)
      return null;
    RequestDtoImpl requestDto = new RequestDtoImpl(request.getOid(),
        getOperatingPartyDtoFromOperatingParty(request.getOriginalInitiator()),
        getOperatingPartyDtoFromOperatingParty(request.getInitiator()));
    
    requestDto.setSubject(request.getSubject());
    requestDto.setExecutor(getOperatingPartyDtoFromOperatingParty(request.getExecutor()));
    requestDto.setPublishedQueueSet(getQueueDtoSetFromQueueSet(request.getQueueSet()));
    requestDto.setInitialComment(getCommentDtoFromComment(request.getInitialComment()));
    requestDto.setCreationTimestamp(request.getCreationTimestamp().toDate());
    return requestDto;
  }
  
  public static Set<RequestDto> getRequestDtoSetFromRequestSet(Set<Request> requestSet) {
    Set<RequestDto> requestDtoSet = new HashSet<RequestDto>();
    for(Request request : requestSet) {
      requestDtoSet.add(getRequestDtoFromRequest(request));
    }
    return requestDtoSet;
  }

  public static CommentDto getCommentDtoFromComment(Comment comment) {
    if(comment == null)
      return null;
    CommentDtoImpl commentDto = new CommentDtoImpl(comment.getOid(),
        getOperatingPartyDtoFromOperatingParty(comment.getAuthor()),
        comment.getCreationTimestamp().toDate(),
        comment.getCommentaryText());
    commentDto.setNextComment(getCommentDtoFromComment(comment.getNextComment()));
    return commentDto;
  }

  public static Set<CommentDto> getCommentDtoSetFromCommentSet(Set<Comment> commentSet) {
    Set<CommentDto> commentDtoSet = new HashSet<CommentDto>();
    for(Comment comment : commentSet) {
      commentDtoSet.add(getCommentDtoFromComment(comment));
    }
    return commentDtoSet;
  }

  public static Set<DataObjectDto> getDataObjectDtoSetFromDataObjectSet(Set<DataObject> dataObjectSet) {
    Set<DataObjectDto> dataObjectDtoSet = new HashSet<DataObjectDto>();
    for(DataObject dataObject : dataObjectSet) {
      dataObjectDtoSet.add(getDataObjectDtoFromDataObject(dataObject));
    }
    return dataObjectDtoSet;
  }

  public static DataObjectDto getDataObjectDtoFromDataObject(DataObject dataObject) {
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

  public static Set<QueueDto> getQueueDtoSetFromQueueSet(Set<Queue> queueSet) {
    Set<QueueDto> queueDtoSet = new HashSet<QueueDto>();
    for(Queue queue : queueSet) {
      queueDtoSet.add(new QueueDtoImpl(queue.getOid(), queue.getTitle()));
    }
    return queueDtoSet;
  }

  public static Queue getQueueFromQueueDto(QueueDto queueDto) {
    return Processpedia.fromOID(queueDto.getOid());
  }

  public static Set<Queue> getQueueSetFromQueueDtoSet(Set<QueueDto> queueDtoSet) {
    Set<Queue> queueSet = new HashSet<Queue>();
    for(QueueDto queueDto : queueDtoSet) {
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

  public static Set<DataObjectVersion> getDataObjectVersionSetFromDataObjectVersionDtoSet(Set<DataObjectVersionDto> dataObjectVersionDtoSet) {
    Set<DataObjectVersion> dataObjectVersionSet = new HashSet<DataObjectVersion>();
    for(DataObjectVersionDto dataObjectVersionDto : dataObjectVersionDtoSet) {
      dataObjectVersionSet.add(getDataObjectVersionFromDataObjectVersionDto(dataObjectVersionDto));
    }
    
    return dataObjectVersionSet;
  }

  public static DataObjectVersion getDataObjectVersionFromDataObjectVersionDto(DataObjectVersionDto dataObjectVersionDto) {
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
