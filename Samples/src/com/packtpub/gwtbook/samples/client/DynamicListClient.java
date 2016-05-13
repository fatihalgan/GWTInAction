package com.packtpub.gwtbook.samples.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.packtpub.gwtbook.samples.client.panels.DynamicListPanel;

public class DynamicListClient implements EntryPoint {
	
	public void onModuleLoad() {
		final DynamicListPanel panel = new DynamicListPanel();
		RootPanel.get().add(panel);
	}
}
