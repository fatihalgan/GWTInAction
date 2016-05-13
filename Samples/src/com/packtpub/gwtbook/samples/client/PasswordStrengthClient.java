package com.packtpub.gwtbook.samples.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.packtpub.gwtbook.samples.client.panels.PasswordStrengthPanel;

public class PasswordStrengthClient implements EntryPoint {

	public void onModuleLoad() {
		final PasswordStrengthPanel panel = new PasswordStrengthPanel();
		RootPanel.get().add(panel);
	}
	
}
