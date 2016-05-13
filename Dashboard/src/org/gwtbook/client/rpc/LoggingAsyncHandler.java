package org.gwtbook.client.rpc;

import com.google.gwt.core.client.GWT;

public abstract class LoggingAsyncHandler extends AbstractAsyncHandler {
	
	public final void onSuccess(Object result){
        GWT.log("RPC Success [" + GWT.getTypeName(this) + "]", null);
        super.onSuccess(result);
    }

    public final void onFailure(Throwable caught){
        GWT.log("RPC Failure [" + GWT.getTypeName(this) + "]", caught);
        super.onFailure(caught);
    }

}
