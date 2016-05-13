package org.gwtbook.client.rpc;

public interface AsyncHandler {

	void handleSuccess(Object result);
	void handleFailure(Throwable caught);
}
