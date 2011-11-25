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
import pt.ist.processpedia.shared.dto.domain.RequestDto;
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

  DataGrid<RequestDto> requestGrid;

  private ListDataProvider<RequestDto> listDataProvider;

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

  public void displayRequestSet(Set<RequestDto> requestDtoSet) {
    List<RequestDto> requestList = new ArrayList<RequestDto>();
    requestList.addAll(requestDtoSet);
    requestGrid  = new DataGrid<RequestDto>(2000, RequestDto.KEY_PROVIDER);
    requestGrid.setWidth("100%");
    requestGrid.setHeight("100%");
    requestGridContainer.add(requestGrid);

    Messages messages = presenter.getBrowserFactory().getMessages();

    requestGrid.setEmptyTableWidget(new Label(messages.noRequestsFound()));

    listDataProvider = new ListDataProvider<RequestDto>();
    listDataProvider.addDataDisplay(requestGrid);

    listDataProvider.setList(requestList);

    ColumnSortEvent.ListHandler<RequestDto> sortHandler = new ColumnSortEvent.ListHandler<RequestDto>(listDataProvider.getList());

    Column<RequestDto, String> senderNameColumn = new Column<RequestDto, String>(new TextCell()) {
      @Override
      public String getValue(RequestDto requestDto) {
        return requestDto.getInitiatorDto().getName();
      }
    };
    Column<RequestDto, String> requestTitleColumn = new Column<RequestDto, String>(new TextCell()) {
      @Override
      public String getValue(RequestDto requestDto) {
        return requestDto.getTitle();
      }
    };
    Column<RequestDto, String> processTitleColumn = new Column<RequestDto, String>(new TextCell()) {
      @Override
      public String getValue(RequestDto requestDto) {
        return requestDto.getProcessDto().getTitle();
      }
    };
    Column<RequestDto, String> lastUpdateTimestampColumn = new Column<RequestDto, String>(new TextCell()) {
      @Override
      public String getValue(RequestDto object) {
        return object.getLastUpdateTimestamp().toString();
      }
    };

    senderNameColumn.setSortable(true);
    requestTitleColumn.setSortable(true);
    processTitleColumn.setSortable(true);
    lastUpdateTimestampColumn.setSortable(true);

    sortHandler.setComparator(senderNameColumn, new RequestDto.CompareSenderName());
    sortHandler.setComparator(requestTitleColumn, new RequestDto.CompareRequestTitleName());
    sortHandler.setComparator(processTitleColumn, new RequestDto.CompareProcessTitleName());
    sortHandler.setComparator(lastUpdateTimestampColumn, new RequestDto.CompareLastUpdateTimestamp());

    requestGrid.addColumnSortHandler(sortHandler);

    requestGrid.addColumn(senderNameColumn, messages.from());
    requestGrid.addColumn(requestTitleColumn, messages.request());
    requestGrid.addColumn(processTitleColumn, messages.process());
    requestGrid.addColumn(lastUpdateTimestampColumn, messages.lastUpdate());

    requestGrid.setColumnWidth(senderNameColumn, 200, Style.Unit.PX);
    requestGrid.setColumnWidth(requestTitleColumn, 200, Style.Unit.PX);
    requestGrid.setColumnWidth(processTitleColumn, 200, Style.Unit.PX);
    requestGrid.setColumnWidth(lastUpdateTimestampColumn, 200, Style.Unit.PX);


    final SingleSelectionModel<RequestDto> selectionModel = new SingleSelectionModel<RequestDto>(RequestDto.KEY_PROVIDER);
    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      public void onSelectionChange(SelectionChangeEvent selectionChangeEvent) {
        presenter.onRequestSelection(selectionModel.getSelectedObject());
      }
    });
    requestGrid.setSelectionModel(selectionModel);


  }
}
