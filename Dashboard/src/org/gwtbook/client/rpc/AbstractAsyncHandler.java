package org.gwtbook.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AbstractAsyncHandler implements AsyncCallback, AsyncHandler {
	
	public void onFailure(Throwable caught) {
		handleFailure(caught);
	}
	
	public void onSuccess(Object result) {
		handleSuccess(result);
	}
	
	public abstract void handleFailure(Throwable caught);

    public abstract void handleSuccess(Object result);

}
