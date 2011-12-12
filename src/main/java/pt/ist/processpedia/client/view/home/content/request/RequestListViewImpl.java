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

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.*;
import pt.ist.processpedia.client.Messages;
import pt.ist.processpedia.shared.dto.domain.RequestDtoImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RequestListViewImpl extends Composite implements RequestListView {

  interface RequestListViewImplUiBinder extends UiBinder<Widget,RequestListViewImpl> {}
  private static RequestListViewImplUiBinder uiBinder = GWT.create(RequestListViewImplUiBinder.class);

  @UiField
  VerticalPanel requestGridContainer;

  DataGrid<RequestDtoImpl> requestGrid;

  private ListDataProvider<RequestDtoImpl> listDataProvider;

  private Presenter presenter;

  public RequestListViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  public void prepareView() {
    requestGridContainer.clear();
  }

  public void displayRequestSet(Set<RequestDtoImpl> requestDtoSet) {
    List<RequestDtoImpl> requestList = new ArrayList<RequestDtoImpl>();
    requestList.addAll(requestDtoSet);
    requestGrid  = new DataGrid<RequestDtoImpl>(2000, RequestDtoImpl.KEY_PROVIDER);
    requestGrid.setWidth("100%");
    requestGrid.setHeight("100%");
    requestGridContainer.add(requestGrid);

    Messages messages = presenter.getBrowserFactory().getMessages();

    requestGrid.setEmptyTableWidget(new Label(messages.noRequestsFound()));

    listDataProvider = new ListDataProvider<RequestDtoImpl>();
    listDataProvider.addDataDisplay(requestGrid);

    listDataProvider.setList(requestList);

    ColumnSortEvent.ListHandler<RequestDtoImpl> sortHandler = new ColumnSortEvent.ListHandler<RequestDtoImpl>(listDataProvider.getList());

    Column<RequestDtoImpl, String> senderNameColumn = new Column<RequestDtoImpl, String>(new TextCell()) {
      @Override
      public String getValue(RequestDtoImpl requestDto) {
        return requestDto.getInitiatorDto().getName();
      }
    };
    Column<RequestDtoImpl, String> requestTitleColumn = new Column<RequestDtoImpl, String>(new TextCell()) {
      @Override
      public String getValue(RequestDtoImpl requestDto) {
        return requestDto.getTitle();
      }
    };
    Column<RequestDtoImpl, String> processTitleColumn = new Column<RequestDtoImpl, String>(new TextCell()) {
      @Override
      public String getValue(RequestDtoImpl requestDto) {
        return requestDto.getProcessDto().getTitle();
      }
    };
    Column<RequestDtoImpl, String> lastUpdateTimestampColumn = new Column<RequestDtoImpl, String>(new TextCell()) {
      @Override
      public String getValue(RequestDtoImpl object) {
        return object.getLastUpdateTimestamp().toString();
      }
    };

    senderNameColumn.setSortable(true);
    requestTitleColumn.setSortable(true);
    processTitleColumn.setSortable(true);
    lastUpdateTimestampColumn.setSortable(true);

    sortHandler.setComparator(senderNameColumn, new RequestDtoImpl.CompareSenderName());
    sortHandler.setComparator(requestTitleColumn, new RequestDtoImpl.CompareRequestTitleName());
    sortHandler.setComparator(processTitleColumn, new RequestDtoImpl.CompareProcessTitleName());
    sortHandler.setComparator(lastUpdateTimestampColumn, new RequestDtoImpl.CompareLastUpdateTimestamp());

    requestGrid.addColumnSortHandler(sortHandler);

    requestGrid.addColumn(senderNameColumn, messages.from());
    requestGrid.addColumn(requestTitleColumn, messages.request());
    requestGrid.addColumn(processTitleColumn, messages.process());
    requestGrid.addColumn(lastUpdateTimestampColumn, messages.lastUpdate());

    requestGrid.setColumnWidth(senderNameColumn, 200, Style.Unit.PX);
    requestGrid.setColumnWidth(requestTitleColumn, 200, Style.Unit.PX);
    requestGrid.setColumnWidth(processTitleColumn, 200, Style.Unit.PX);
    requestGrid.setColumnWidth(lastUpdateTimestampColumn, 200, Style.Unit.PX);


    final SingleSelectionModel<RequestDtoImpl> selectionModel = new SingleSelectionModel<RequestDtoImpl>(RequestDtoImpl.KEY_PROVIDER);
    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      public void onSelectionChange(SelectionChangeEvent selectionChangeEvent) {
        presenter.onRequestSelection(selectionModel.getSelectedObject());
      }
    });
    requestGrid.setSelectionModel(selectionModel);


  }
}
