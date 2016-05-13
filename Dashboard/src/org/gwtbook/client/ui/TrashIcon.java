package org.gwtbook.client.ui;

import com.google.gwt.i18n.client.Localizable;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Image;

public class TrashIcon extends Image implements Localizable {
	
	public void onBrowserEvent(Event event) {
		DOM.eventPreventDefault(event);
		super.onBrowserEvent(event);
	}
	
	public void setIcon() {
		this.setUrl("trash.png");
	}
}
