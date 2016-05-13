package org.gwtbook.client.ui.serverstatus;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServerStatusServiceAsync {
	
	void getStatusData(AsyncCallback callback);
}
