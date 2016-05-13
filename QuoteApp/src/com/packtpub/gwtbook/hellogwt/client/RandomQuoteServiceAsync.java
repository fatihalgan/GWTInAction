package com.packtpub.gwtbook.hellogwt.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RandomQuoteServiceAsync {
	static final RandomQuoteServiceAsync IMPL = (RandomQuoteServiceAsync) GWT.create(RandomQuoteService.class);
	public void getQuote(AsyncCallback<String> callback);
}
