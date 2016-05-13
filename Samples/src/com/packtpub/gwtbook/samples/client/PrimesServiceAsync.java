package com.packtpub.gwtbook.samples.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PrimesServiceAsync {

	public void isPrimeNumber(int numberToVerify, AsyncCallback callback);
}
