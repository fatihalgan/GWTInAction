package org.gwtbook.client.ui.about;

import org.gwtbook.client.ui.DashboardComposite;

import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.VerticalPanel;

public class About extends DashboardComposite {
	
	Frame dynamicAboutMessage;
	VerticalPanel aboutPanel = new VerticalPanel();
	
	public About() {
		super();
		create();
	}
	
	private void create() {
		dynamicAboutMessage = new Frame("About.html");
		dynamicAboutMessage.setStyleName("about-Message");
		aboutPanel.add(dynamicAboutMessage);
		aboutPanel.setStyleName("about-Panel");
		initWidget(aboutPanel);
	}
}
