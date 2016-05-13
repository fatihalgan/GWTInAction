package org.gwtbook.client.ui.serverstatus;

import com.google.gwt.user.client.rpc.RemoteService;

public interface ServerStatusService extends RemoteService {
	
	ServerStatusData getStatusData();
}
