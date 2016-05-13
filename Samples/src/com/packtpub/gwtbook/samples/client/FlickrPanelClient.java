package com.packtpub.gwtbook.samples.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.packtpub.gwtbook.samples.client.panels.FlickrEditableLabelPanel;

public class FlickrPanelClient implements EntryPoint {

	public void onModuleLoad() {
		FlickrEditableLabelPanel panel = new FlickrEditableLabelPanel();
		RootPanel.get().add(panel);
	}
	
	
}
