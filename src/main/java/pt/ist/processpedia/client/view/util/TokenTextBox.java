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

package pt.ist.processpedia.client.view.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import java.util.HashSet;
import java.util.Set;

public class TokenTextBox extends Composite {

  interface TokenTextBoxUiBinder extends UiBinder<Widget,TokenTextBox> {}
  private static TokenTextBoxUiBinder uiBinder = GWT.create(TokenTextBoxUiBinder.class);

  @UiField
  FlowPanel tokensContainer;

  @UiField(provided = true)
  SuggestBox suggestBox;

  private Set<String> valueSet;

  public TokenTextBox(SuggestOracle suggestOracle) {
    suggestBox = new SuggestBox(suggestOracle);
    initWidget(uiBinder.createAndBindUi(this));
    valueSet = new HashSet<String>();
    suggestBox.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
      public void onSelection(SelectionEvent<SuggestOracle.Suggestion> suggestionSelectionEvent) {
        onSuggestionSelection(suggestionSelectionEvent.getSelectedItem().getReplacementString());
      }
    });
  }

  private void deleteLastTokenIfAny() {
    if(tokensContainer.getWidgetCount()>0) {
      Token token = (Token)tokensContainer.getWidget(tokensContainer.getWidgetCount() - 1);
      Window.alert("Vou remover " + token.getText());
      removeToken(token);
    }
  }

  private void onSuggestionSelection(String item) {
    if(!valueSet.contains(item)) {
      valueSet.add(item);
      Token token = new Token(this,item);
      tokensContainer.insert(token, tokensContainer.getWidgetCount());
    }
    suggestBox.setText("");
  }

  public void removeToken(Token token) {
    String tokenText = token.getText();
    valueSet.remove(tokenText);
    tokensContainer.remove(token);
  }

  public void setText(String text) {
    suggestBox.setText(text);
  }

  public String getText() {
    String result = "";
    for(String s : valueSet) {
      result = result + s +", ";
    }
    return result;
  }

}
