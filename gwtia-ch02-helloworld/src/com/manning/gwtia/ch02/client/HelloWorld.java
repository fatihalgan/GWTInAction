package com.manning.gwtia.ch02.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class HelloWorld implements EntryPoint {

	@Override
	public void onModuleLoad() {
		Label theGreeting = new Label("Hello World!");
		RootPanel.get().add(theGreeting);
		RootPanel.get().add(new Button("Click Me!"));
		SimplePanel testPanel = new SimplePanel();
		testPanel.add(new Label("A label in a panel"));
		RootPanel.get().add(testPanel);
	}

}
