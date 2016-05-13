package org.gwtbook.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LoggingChainingCallback implements AsyncCallback {
	
	private AsyncCallback callback;
	
	public LoggingChainingCallback(AsyncCallback callback) {
		this.callback = callback;
	}
	
	public void onFailure(Throwable caught) {
		GWT.log("RPC Failure [" + GWT.getTypeName(callback) + "]", caught);
        callback.onFailure(caught);
	}
	
	public void onSuccess(Object result) {
		GWT.log("RPC Success [" + GWT.getTypeName(callback) + "]", null);
        callback.onSuccess(result);
	}
}
