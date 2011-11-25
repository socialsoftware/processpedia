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

package pt.ist.processpedia.client.view.home.content.process;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import pt.ist.processpedia.client.Messages;

public class CreateProcessViewImpl extends Composite implements CreateProcessView {

  interface CreateProcessViewImplUiBinder extends UiBinder<Widget,CreateProcessViewImpl> {}
  private static CreateProcessViewImplUiBinder uiBinder = GWT.create(CreateProcessViewImplUiBinder.class);

  private Presenter presenter;

  @UiField
  HasText createProcessTitleContainer,
          processTitleLabelContainer,
          processDescriptionLabelContainer;

  @UiField
  HasText processTitleContainer,
          processDescriptionContainer;

  @UiField
  Button nextAction, cancelAction;

  public CreateProcessViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public void prepareView() {
    Messages messages = presenter.getBrowserFactory().getMessages();
    setCreateProcessTitle(messages.createProcessTitle());
    setProcessTitleLabel(messages.processTitle());
    setProcessDescriptionLabel(messages.processDescription());
    setNextButtonText(messages.next());
    setCancelButtonText(messages.cancel());
    processTitleContainer.setText("");
    processDescriptionContainer.setText("");
  }

  public void setCreateProcessTitle(String createProcessTitle) {
    createProcessTitleContainer.setText(createProcessTitle);
  }

  public void setProcessTitleLabel(String processTitle) {
    processTitleLabelContainer.setText(processTitle+":");
  }

  public void setProcessDescriptionLabel(String processDescription) {
    processDescriptionLabelContainer.setText(processDescription+":");
  }

  public void setNextButtonText(String nextButtonText) {
    nextAction.setText(nextButtonText);
  }

  public void setCancelButtonText(String cancelButtonText) {
    cancelAction.setText(cancelButtonText);
  }

  public String getProcessTitle() {
    return processTitleContainer.getText();
  }

  public String getProcessDescription() {
    return processDescriptionContainer.getText();
  }

  @UiHandler("nextAction")
  public void onNextAction(ClickEvent clickEvent) {
    presenter.onNextAction();
  }

  @UiHandler("cancelAction")
  public void onCancelAction(ClickEvent clickEvent) {
    presenter.onCancelAction();
  }
}
