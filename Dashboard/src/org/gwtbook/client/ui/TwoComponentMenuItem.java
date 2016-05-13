package org.gwtbook.client.ui;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class TwoComponentMenuItem extends MenuItem {

	protected HorizontalPanel theMenuItem = new HorizontalPanel();
	
	public TwoComponentMenuItem(String theText, Widget secondComponent,
			Command theCommand) {
		super(theText, true, theCommand);
		theMenuItem.add(new Label(theText + " "));
		theMenuItem.add(secondComponent);
		setStyleName(theMenuItem.getWidget(0).getElement(), "gwt-MenuItem", true);
		setStyleName(theMenuItem.getWidget(1).getElement(), "image", true);
		setStyleName(theMenuItem.getElement(),"holder",true);
		setSecondComponent(secondComponent);
	}
	
	public void setSecondComponent(Widget newComponent) {
		theMenuItem.remove(1);
		theMenuItem.add(newComponent);
		SimplePanel dummyContainer = new SimplePanel();
		dummyContainer.add(theMenuItem);
		String test = DOM.getInnerHTML(dummyContainer.getElement());
		this.setHTML(test);
	}
	
	public void setFirstComponent(String newComponent) {
		theMenuItem.remove(0);
		theMenuItem.insert(new Label(newComponent), 0);
		SimplePanel dummyContainer = new SimplePanel();
		dummyContainer.add(theMenuItem);
		String test = DOM.getInnerHTML(dummyContainer.getElement());
		this.setHTML(test);
	}
	
}
