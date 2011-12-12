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

package pt.ist.processpedia.shared.dto.domain;

import java.util.Date;
import java.util.Set;

public class RequestDetailedDtoImpl extends RequestDtoImpl implements RequestDetailedDto {

  private CommentDto descriptionCommentDto;
  private Set<DataObjectDto> dataObjectDtoSet;

  public RequestDetailedDtoImpl() {}

  public RequestDetailedDtoImpl(long oid, String title, CommentDtoImpl descriptionCommentDto, UserDetailedDtoImpl initiatorDto, UserDetailedDtoImpl executorDto, Set<QueueDto> publishedQueueDtoSet, Date creationTimestamp, Date lastUpdateTimestamp, ProcessDto processDto, Set<DataObjectDto> dataObjectDtoSet) {
    super(oid, title, initiatorDto, executorDto, publishedQueueDtoSet, creationTimestamp, lastUpdateTimestamp, processDto);
    setDescriptionCommentDto(descriptionCommentDto);
    setDataObjectDtoList(dataObjectDtoSet);
  }

  public CommentDto getDescriptionCommentDto() {
    return descriptionCommentDto;
  }

  public UserDetailedDto getInitiatorDetailedDto() {
    return (UserDetailedDto)super.getInitiatorDto();
  }

  public UserDetailedDto getExecutorDetailedDto() {
    return (UserDetailedDto)super.getExecutorDto();
  }

  public void setDescriptionCommentDto(CommentDto descriptionCommentDto) {
    this.descriptionCommentDto = descriptionCommentDto;
  }

  public ProcessDetailedDto getProcessDetailedDto() {
    return (ProcessDetailedDto)super.getProcessDto();
  }

  public Set<DataObjectDto> getDataObjectDtoSet() {
    return dataObjectDtoSet;
  }

  public void setDataObjectDtoList(Set<DataObjectDto> dataObjectDtoSet) {
    this.dataObjectDtoSet = dataObjectDtoSet;
  }

  public UserDetailedDto getSenderDetailedDto() {
    return (UserDetailedDto)super.getSenderDto();
  }
}
