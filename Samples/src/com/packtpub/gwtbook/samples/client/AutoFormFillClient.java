package com.packtpub.gwtbook.samples.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.packtpub.gwtbook.samples.client.panels.AutoFormFillPanel;

public class AutoFormFillClient implements EntryPoint {
	
	public void onModuleLoad() {
		final AutoFormFillPanel panel = new AutoFormFillPanel();
		RootPanel.get().add(panel);
	}
}
