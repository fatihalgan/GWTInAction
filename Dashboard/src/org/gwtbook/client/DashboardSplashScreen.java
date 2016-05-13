package org.gwtbook.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.TabPanel;

public class DashboardSplashScreen extends Composite {
	
	public DashboardSplashScreen() {
		TabPanel info = new TabPanel();
		Frame intro = new Frame(GWT.getModuleBaseURL() + "Splash/Intro.html");
		info.add(intro, "Introduction");
		
		Frame dev = new Frame(GWT.getModuleBaseURL() + "Splash/Development.html");
		info.add(dev, "Development");
		
		Frame i18n = new Frame(GWT.getModuleBaseURL() + "Splash/Internationalization.html");
		info.add(i18n, "Internationalization");
		
		Frame widgets = new Frame(GWT.getModuleBaseURL() + "Splash/Widgets.html");
		info.add(widgets, "Widgets");
		
		info.selectTab(0);
		this.initWidget(info);
	}
}
