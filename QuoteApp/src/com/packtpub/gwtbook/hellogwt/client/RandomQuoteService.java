package com.packtpub.gwtbook.hellogwt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("QuoteService")
public interface RandomQuoteService extends RemoteService {
	public String getQuote();
}
