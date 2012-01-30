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

package pt.ist.processpedia.client.view.home.content.request;

import java.util.HashSet;
import java.util.Set;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.client.view.util.DataObjectCreationBox;
import pt.ist.processpedia.client.view.util.SelectableDataObject;
import pt.ist.processpedia.client.view.util.TokenTextBox;
import pt.ist.processpedia.shared.dto.domain.DataObjectVersionDto;
import pt.ist.processpedia.shared.dto.recommendation.RequestRecommendationDto;

public class CreateRequestViewImpl extends Composite implements CreateRequestView, FocusHandler {

  interface CreateRequestViewImplUiBinder extends UiBinder<Widget,CreateRequestViewImpl> {}
  private static CreateRequestViewImplUiBinder uiBinder = GWT.create(CreateRequestViewImplUiBinder.class);

  private HandlerRegistration handlerRegistration;
  
  @UiField
  HasText createRequestTitleContainer,
          toLabelContainer,
          requestTitleLabelContainer,
          requestDescriptionLabelContainer,
          requestDataObjectLabelContainer;

  @UiField
  HasText requestDescriptionContainer;

  @UiField(provided = true)
  TokenTextBox toContainer;

  @UiField
  CheckBox expectsAnswerContainer;

  @UiField
  Tree inputDataObjectsContainer;

  @UiField
  Button publishRequestAction, saveAction, cancelAction;

  private Presenter presenter;
  
  @UiField(provided = true)
  SuggestBox subjectSuggestBox;
  
  private MultiWordSuggestOracle subjectOracle;
  
  private MultiWordSuggestOracle toOracle;

  public CreateRequestViewImpl() {
    subjectOracle = new MultiWordSuggestOracle();
    toOracle = new MultiWordSuggestOracle();
    toContainer = new TokenTextBox(toOracle);
    subjectSuggestBox = new SuggestBox(subjectOracle);
    subjectSuggestBox.getTextBox().addFocusHandler(this);
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public void prepareView() {
    if(handlerRegistration!=null) {
      handlerRegistration.removeHandler();
    }
    Messages messages = presenter.getBrowserFactory().getMessages();
    setCreateRequestTitle(messages.createRequestTitle());
    setToLabel(messages.to());
    setRequestTitleLabel(messages.requestTitle());
    setRequestDescriptionLabel(messages.requestDescription());
    setExpectsAnswerLabel(messages.expectsAnswer());
    setRequestDataObjectLabel(messages.requestData());
    setPublishRequestButtonText(messages.publishRequest());
    setSaveDraftButtonText(messages.saveDraft());
    setCancelButtonText(messages.cancel());
    subjectSuggestBox.setText("");
    requestDescriptionContainer.setText("");
    toContainer.setText("");
    expectsAnswerContainer.setValue(false);
    inputDataObjectsContainer.clear();


    DataObjectCreationBox dataObjectCreationBox = new DataObjectCreationBox();
    dataObjectCreationBox.addDataObjectCreationHandler(new DataObjectCreationBox.DataObjectCreationHandler() {
      public void onDataObjectCreation(DataObjectVersionDto dataObjectVersionDto) {
        addDataObjectVersionToTree(dataObjectVersionDto);
      }
    });
    TreeItem root = new TreeItem(dataObjectCreationBox);
    inputDataObjectsContainer.addItem(root);
  }

  private void addDataObjectVersionToTree(DataObjectVersionDto dataObjectVersionDto) {
    inputDataObjectsContainer.insertItem(inputDataObjectsContainer.getItemCount()-1, new SelectableDataObject(dataObjectVersionDto.getLabel(), dataObjectVersionDto.getExternalizedValue()));
  }

  public void setCreateRequestTitle(String createRequestTitle) {
    createRequestTitleContainer.setText(createRequestTitle);
  }

  public void setExpectsAnswerLabel(String expectsAnswerLabel) {
    expectsAnswerContainer.setText(expectsAnswerLabel);
  }

  public void setCancelButtonText(String cancelButtonText) {
    cancelAction.setText(cancelButtonText);
  }
  
  public void setSaveDraftButtonText(String saveDraftButtonText) {
    saveAction.setText(saveDraftButtonText);
  }

  public void setToLabel(String toLabel) {
    toLabelContainer.setText(toLabel);
  }

  public void setRequestTitleLabel(String requestTitleLabel) {
    requestTitleLabelContainer.setText(requestTitleLabel);
  }

  public void setRequestDescriptionLabel(String requestDescriptionLabel) {
    requestDescriptionLabelContainer.setText(requestDescriptionLabel);
  }

  public void setRequestDataObjectLabel(String requestDataObjectLabel) {
    requestDataObjectLabelContainer.setText(requestDataObjectLabel);
  }

  public void setPublishRequestButtonText(String publishRequestButtonText) {
    publishRequestAction.setText(publishRequestButtonText);
  }

  public String getTo() {
    return toContainer.getText();
  }

  public String getRequestTitle() {
    return subjectSuggestBox.getText();
  }

  public String getRequestDescription() {
    return requestDescriptionContainer.getText();
  }

  public Boolean getIsResponseExpected() {
    return expectsAnswerContainer.getValue();
  }

  public MultiWordSuggestOracle getOracle() {
    return toOracle;
  }

  @UiHandler("publishRequestAction")
  public void onPublishRequestAction(ClickEvent clickEvent) {
    presenter.onPublishRequestAction();
  }

  @UiHandler("cancelAction")
  public void onCancelAction(ClickEvent clickEvent) {
    presenter.onCancelAction();
  }

  @Override
  public void setRequestTitle(String requestTitle) {
      subjectSuggestBox.setText(requestTitle);
  }

  @Override
  public void onFocus(FocusEvent focusEvent) {
    subjectSuggestBox.showSuggestionList();
  }

  @Override
  public void setRequestRecommendationSet(Set<RequestRecommendationDto> requestRecommendationDtoSet) {
    Set<Suggestion> suggestionsSet = new HashSet<Suggestion>();
    for(RequestRecommendationDto requestRecommendationDto : requestRecommendationDtoSet) {
      suggestionsSet.add((Suggestion)requestRecommendationDto);
    }
    subjectOracle.setDefaultSuggestions(suggestionsSet);
  }

  @Override
  public void addSelectionHandler(SelectionHandler<Suggestion> selectionHandler) {
    handlerRegistration = subjectSuggestBox.addSelectionHandler(selectionHandler);
  }

}
