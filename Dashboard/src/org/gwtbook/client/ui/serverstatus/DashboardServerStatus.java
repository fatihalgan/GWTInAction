package org.gwtbook.client.ui.serverstatus;

import org.gwtbook.client.ui.DashboardComposite;

public class DashboardServerStatus extends DashboardComposite {

	ServerStatusComponent serverStatus;
	
	public DashboardServerStatus() {
		super();
		serverStatus = new ServerStatusComponent();
		serverStatus.startUpdateTimer(60);
		initWidget(serverStatus);
	}
	
	protected void onDelete() {
		serverStatus.stopUpdateTimer();
	}
}
