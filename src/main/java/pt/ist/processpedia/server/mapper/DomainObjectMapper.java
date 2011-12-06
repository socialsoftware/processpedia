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
import pt.ist.processpedia.shared.dto.domain.*;
import pt.ist.processpedia.shared.dto.recommendation.RequestRecommendationDto;
import pt.ist.processpedia.shared.dto.recommendation.RequestRecommendationDtoImpl;

import java.util.HashSet;
import java.util.Set;

public class DomainObjectMapper {

  public static UserDtoImpl getUserDtoFromUser(User user) {
    if(user==null)
      return null;
    return new UserDtoImpl(user.getOid(), user.getName());
  }

  public static UserDetailedDtoImpl getUserDetailedDtoFromUser(User user) {
    if(user == null)
      return null;
    return new UserDetailedDtoImpl(user.getOid(), user.getName(), user.getAvatarUrl());
  }

  public static ProcessDto getProcessDtoFromProcess(Process process) {
    if(process==null)
      return null;
    return new ProcessDto(process.getOid(), process.getTitle());
  }

  public static ProcessDetailedDto getProcessDetailedDtoFromProcess(Process process) {
    if(process == null)
      return null;
    return new ProcessDetailedDto(process.getOid(), process.getTitle(), process.getDescription());
  }

  public static RequestDto getRequestDtoFromRequest(Request request) {
    if(request == null)
      return null;
    return new RequestDto(request.getOid(),
        request.getTitle(),
        getUserDtoFromUser(request.getInitiator()),
        getUserDtoFromUser(request.getExecutor()),
        getQueueDtoSetFromQueueSet(request.getPublishedQueueSet()),
        request.getCreationTimestamp().toDate(),
        request.getLastUpdateTimestamp().toDate(),
        getProcessDtoFromProcess(request.getProcess()));
  }

  public static RequestDetailedDto getRequestDetailedDtoFromRequest(Request request) {
    if(request == null)
      return null;
    return new RequestDetailedDtoImpl(request.getOid(),
        request.getTitle(),
        getCommentDtoFromComment(request.getDescriptionComment()),
        getUserDetailedDtoFromUser(request.getInitiator()),
        getUserDetailedDtoFromUser(request.getExecutor()),
        getQueueDtoSetFromQueueSet(request.getPublishedQueueSet()),
        request.getCreationTimestamp().toDate(),
        request.getLastUpdateTimestamp().toDate(),
        getProcessDetailedDtoFromProcess(request.getProcess()),
        getDataObjectDtoSetFromDataObjectSet(request.getCreatedDataObjectSet()));
  }

  public static CommentDtoImpl getCommentDtoFromComment(Comment comment) {
    if(comment == null)
      return null;
    return new CommentDtoImpl(comment.getOid(),
        getUserDetailedDtoFromUser(comment.getAuthor()),
        comment.getCreationTimestamp().toDate(),
        comment.getCommentaryText(),
        getCommentDtoSetFromCommentSet(comment.getReplyCommentSet()));
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
    return new DataObjectDto(dataObject.getOid(),
        getDataObjectVersionDtoFromDataObjectVersion(dataObject.getLatestVersion()));
  }

  public static DataObjectVersionDto getDataObjectVersionDtoFromDataObjectVersion(DataObjectVersion dataObjectVersion) {
    if(dataObjectVersion instanceof AtomicDataObjectVersion) {
      AtomicDataObjectVersion atomicDataObjectVersion = (AtomicDataObjectVersion) dataObjectVersion;
      return new AtomicDataObjectVersionDto(atomicDataObjectVersion.getOid(),
          getDataObjectTypeDtoFromDataObjectType(atomicDataObjectVersion.getType()),
          atomicDataObjectVersion.getLabel(),
          atomicDataObjectVersion.getExternalizedValue());

    } else {
      ComposedDataObjectVersion composedDataObjectVersion = (ComposedDataObjectVersion) dataObjectVersion;
      return new ComposedDataObjectVersionDto(composedDataObjectVersion.getOid(),
          composedDataObjectVersion.getLabel(),
          getDataObjectVersionDtoSetFromDataObjectVersionSet(composedDataObjectVersion.getChildDataObjectVersionSet()));
    }
  }

  public static Set<DataObjectVersionDto> getDataObjectVersionDtoSetFromDataObjectVersionSet(Set<DataObjectVersion> dataObjectVersionSet) {
    Set<DataObjectVersionDto> dataObjectVersionDtoSet = new HashSet<DataObjectVersionDto>();
    for(DataObjectVersion dataObjectVersion : dataObjectVersionSet) {
      dataObjectVersionDtoSet.add(getDataObjectVersionDtoFromDataObjectVersion(dataObjectVersion));
    }
    return dataObjectVersionDtoSet;
  }

  public static AtomicDataObjectVersionDto.DataObjectTypeDto getDataObjectTypeDtoFromDataObjectType(AtomicDataObjectVersion.DataObjectType dataObjectType) {
    if(dataObjectType.equals(AtomicDataObjectVersion.DataObjectType.STRING)) {
      return AtomicDataObjectVersionDto.DataObjectTypeDto.STRING;
    } else {
      return AtomicDataObjectVersionDto.DataObjectTypeDto.FILE;
    }
  }

  public static Set<QueueDto> getQueueDtoSetFromQueueSet(Set<Queue> queueSet) {
    Set<QueueDto> queueDtoSet = new HashSet<QueueDto>();
    for(Queue queue : queueSet) {
      queueDtoSet.add(new QueueDto(queue.getOid(), queue.getTitle()));
    }
    return queueDtoSet;
  }


  public static Set<Queue> getQueueSetFromQueueDtoSet(Set<QueueDto> queueDtoSet) {
    Set<Queue> queueSet = new HashSet<Queue>();
    for(QueueDto queueDto : queueDtoSet) {
      Queue queue = Processpedia.fromOID(queueDto.getOid());
      if(queue!=null) {
        queueSet.add(queue);
      }
    }
    return queueSet;
  }

  public static Set<DataObject> getDataObjectSetFromDataObjectDtoSet(Set<DataObjectDto> dataObjectDtoSet) {
    Set<DataObject> dataObjectSet = new HashSet<DataObject>();
    for(DataObjectDto dataObjectDto : dataObjectDtoSet) {
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
}
