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

package pt.ist.processpedia.shared.dto.response;

import pt.ist.processpedia.shared.dto.util.FolderDto;

import java.util.List;

public class GetFolderListResponseDto extends ResponseDto {

  private List<FolderDto> folderDtoList;

  public GetFolderListResponseDto() {}

  public GetFolderListResponseDto(List<FolderDto> folderDtoList) {
    setFolderDtoList(folderDtoList);
  }

  public List<FolderDto> getFolderDtoList() {
    return folderDtoList;
  }

  public void setFolderDtoList(List<FolderDto> folderDtoList) {
    this.folderDtoList = folderDtoList;
  }
}
