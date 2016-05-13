package com.packtpub.gwtbook.samples.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("LiveSearchService")
public interface LiveSearchService extends RemoteService {

	public List getCompletionItems(String itemToMatch);
}
