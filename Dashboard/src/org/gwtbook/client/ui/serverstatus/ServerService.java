package org.gwtbook.client.ui.serverstatus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class ServerService {

	private static ServerService instance;
	private ServerStatusServiceAsync proxy;
	
	private ServerService() {
		proxy = (ServerStatusServiceAsync)GWT.create(ServerStatusService.class);
		((ServiceDefTarget)proxy).setServiceEntryPoint(GWT.getModuleBaseURL() + "server-status");
	}
	
	public static ServerService getInstance() {
        if (instance == null) {
            instance = new ServerService();
        }
        return instance;
    }

    public void getStatusData(AsyncCallback callback) {
        proxy.getStatusData(callback);
    }

}
