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

public class CommentDtoImpl extends DomainObjectDtoImpl implements CommentDto {

  private UserDetailedDto authorDto;
  private Date creationTimestamp;
  private String commentaryText;
  private CommentDto nextCommentDto;

  public CommentDtoImpl() {}

  public CommentDtoImpl(long commentOid, UserDetailedDto authorDto, Date creationTimestamp, String commentaryText, CommentDto nextComment) {
    super(commentOid);
    setAuthorDto(authorDto);
    setCreationTimestamp(creationTimestamp);
    setCommentaryText(commentaryText);
    setNextCommentDto(nextCommentDto);
  }

  public UserDetailedDto getAuthorDto() {
    return authorDto;
  }

  public void setAuthorDto(UserDetailedDto authorDto) {
    this.authorDto = authorDto;
  }

  public Date getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp(Date creationTimestamp) {
    this.creationTimestamp = creationTimestamp;
  }

  public String getCommentaryText() {
    return commentaryText;
  }

  public void setCommentaryText(String commentaryText) {
    this.commentaryText = commentaryText;
  }

  public CommentDto getNextCommentDto() {
    return nextCommentDto;
  }

  public void setNextCommentDto(CommentDto nextCommentDto) {
    this.nextCommentDto = nextCommentDto;
  }
}
