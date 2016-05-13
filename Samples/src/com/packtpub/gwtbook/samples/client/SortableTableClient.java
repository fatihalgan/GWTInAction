package com.packtpub.gwtbook.samples.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.packtpub.gwtbook.samples.client.panels.SortableTablesPanel;

public class SortableTableClient implements EntryPoint {

	public void onModuleLoad() {
		final SortableTablesPanel panel = new SortableTablesPanel();
		RootPanel.get().add(panel);
	}
	
}
