package org.gwtbook.server;

import org.gwtbook.client.ui.serverstatus.ServerStatusData;
import org.gwtbook.client.ui.serverstatus.ServerStatusService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ServerStatusImpl extends RemoteServiceServlet implements ServerStatusService {

	public ServerStatusData getStatusData() {
		ThreadGroup parentThread = Thread.currentThread().getThreadGroup();
		while(parentThread.getParent() != null) {
			parentThread = parentThread.getParent();
		}
		ServerStatusData result = new ServerStatusData();
		result.serverName = getThreadLocalRequest().getServerName();
		result.totalMemory = Runtime.getRuntime().totalMemory();
		result.freeMemory = Runtime.getRuntime().freeMemory();
		result.maxMemory = Runtime.getRuntime().maxMemory();
		result.threadCount = parentThread.activeCount();
		return result;
	}
	
}
