package com.packtpub.gwtbook.samples.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.packtpub.gwtbook.samples.client.panels.LiveSearchPanel;

public class LiveSearchClient implements EntryPoint{
	
	public void onModuleLoad() {
		final LiveSearchPanel panel = new LiveSearchPanel();
		RootPanel.get().add(panel);
	}
}
