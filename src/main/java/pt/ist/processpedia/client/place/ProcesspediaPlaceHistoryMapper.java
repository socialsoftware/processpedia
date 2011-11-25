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

package pt.ist.processpedia.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({
    LoginPlace.Tokenizer.class,
    SignupPlace.Tokenizer.class,
    HomePlace.Tokenizer.class,
    FolderPlace.Tokenizer.class,
    AccountActivationPlace.Tokenizer.class,
    RequestPlace.Tokenizer.class,
    SearchPlace.Tokenizer.class})
public interface ProcesspediaPlaceHistoryMapper extends PlaceHistoryMapper {}
