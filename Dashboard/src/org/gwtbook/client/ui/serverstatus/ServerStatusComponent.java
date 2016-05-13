package org.gwtbook.client.ui.serverstatus;

import java.util.Date;

import org.gwtbook.client.UpdateableComposite;
import org.gwtbook.client.rpc.LoggingAsyncHandler;
import org.gwtbook.client.rpc.LoggingChainingCallback;
import org.gwtbook.client.ui.ToolTip;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ServerStatusComponent extends UpdateableComposite {

	final int HEADER_ROW = 0;
	final int STATNAME_COL = 0;
	final int STATVAL_COL = 1;
	final int SERVERNAME_ROW = 1;
	final int TOTALMEM_ROW = 2;
	final int FREEMEM_ROW = 3;
	final int MAXMEM_ROW = 4;
	final int THREAD_COUNT_ROW = 5;
	
	private Panel composite = new VerticalPanel();
	private Label titleBar = new Label("Server Status");
	private Button updateButton = new Button("Manual Update");
	private Grid serverStatus = new Grid(6, 2);
	private Label labelLastUpdated = new Label();
    private Label labelTotalMemory = new Label();
    private Label labelFreeMemory = new Label();
    private Label labelThreadCount = new Label();
    private Label labelMaxMemory = new Label();
    private Label labelServerName = new Label();
    
    public ServerStatusComponent() {
    	initWidget(composite);
    	
    	composite.setStyleName("server-status");
    	titleBar.setStyleName("title-bar");
    	serverStatus.setStyleName("stats-grid");
    	updateButton.setStyleName("update-button");
    	labelLastUpdated.setStyleName("last-updated");
    	
    	updateButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				getStatusDataFromServer();
			}
    	});
    	
    	addDataRow("NAME",new Label("VALUE"),HEADER_ROW);
        addDataRow("Server Name", labelServerName, SERVERNAME_ROW);
        addDataRow("Total Memory (kB)", labelTotalMemory, TOTALMEM_ROW);
        addDataRow("Free Memory (kB)", labelFreeMemory, FREEMEM_ROW);
        addDataRow("Max Memory (kB)", labelMaxMemory, MAXMEM_ROW);
        addDataRow("Thread Count", labelThreadCount, THREAD_COUNT_ROW);
        
        serverStatus.addTableListener(new TableListener() {

			public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
				if(row == HEADER_ROW) {
					if(cell == STATNAME_COL) Window.alert("Would re-order based on statistic name here");
					else Window.alert("Would re-order based on statistic values here");
				} else {
					Widget clickedOn = serverStatus.getWidget(row, cell);
					int toolY = clickedOn.getAbsoluteTop();
					int toolX = clickedOn.getAbsoluteLeft() + clickedOn.getOffsetWidth();
					switch(row) {
						case SERVERNAME_ROW : new ToolTip("The name of the server being monitored", toolX, toolY);
											  break;
						case TOTALMEM_ROW : new ToolTip("Total memory available on server", toolX, toolY);
											break;
						case FREEMEM_ROW : new ToolTip("Amount of free memory on server", toolX, toolY);
										   break;
						case MAXMEM_ROW : new ToolTip("Maximum memory available to application on server", toolX, toolY);
										  break;
						case THREAD_COUNT_ROW : new ToolTip("Number of server threads in use", toolX, toolY);
												break;
					}
				}
			}
        	
        });
        
        composite.add(titleBar);
        composite.add(serverStatus);
        composite.add(updateButton);
        composite.add(labelLastUpdated);
        getStatusDataFromServer();
    }
    
    private void addDataRow(String statsName, Widget statsValue, int row) {
        serverStatus.setWidget(row, 0, new Label(statsName));
        serverStatus.setWidget(row, 1, statsValue);
        serverStatus.getCellFormatter().setStyleName(row, 0, "stat-name");
        serverStatus.getCellFormatter().setStyleName(row, 1, "stat-value");
    }
    
    private void getStatusDataFromServer() {
    	ServerService.getInstance().getStatusData(new LoggingChainingCallback(new ServerStatsUpdater()));
    }

	
	public void update() {
		getStatusDataFromServer();
	}
	
	public abstract class AbstractAsyncCallback implements AsyncCallback {
		
		public void onFailure(Throwable caught) {
			GWT.log("RPC Error: ", caught);
		}
		
		public void onSuccess(Object result) {
			GWT.log("RPC Success", null);
		}
	}
	
	class StatusDataCallback extends AbstractAsyncCallback {
		public void onSuccess(Object result) {
			
		}
	}
	
	class ServerStatsUpdater extends LoggingAsyncHandler {

		public void handleFailure(Throwable caught) {
			Window.alert("Currently unable to access the server");			
		}

		public void handleSuccess(Object result) {
			ServerStatusData data = (ServerStatusData)result;
			labelServerName.setText(data.serverName);
			labelTotalMemory.setText(toKB(data.totalMemory));
            labelFreeMemory.setText(toKB(data.freeMemory));
            labelMaxMemory.setText(toKB(data.maxMemory));
            labelThreadCount.setText(Integer.toString(data.threadCount));
            labelLastUpdated.setText(new Date().toString());
		}
	}
	
	class ServerStatsUpdater1 implements AsyncCallback {
        
		public void onFailure(Throwable caught) { }

        public void onSuccess(Object result) {
            ServerStatusData data = (ServerStatusData) result;
            labelServerName.setText(data.serverName);
            labelTotalMemory.setText(toKB(data.totalMemory));
            labelFreeMemory.setText(toKB(data.freeMemory));
            labelMaxMemory.setText(toKB(data.maxMemory));
            labelThreadCount.setText(Integer.toString(data.threadCount));
            labelLastUpdated.setText(new Date().toString());
        }
    }
	
	private String toKB(long bytes) {
		long kbytes = bytes / 1024;
		return Long.toString(kbytes);
    }


}
