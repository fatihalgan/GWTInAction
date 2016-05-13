package org.gwtbook.client.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupListener;
import com.google.gwt.user.client.ui.PopupPanel;

public class ToolTip extends PopupPanel {

	final int VISIBLE_DELAY = 2000;
	
	static ToolTip visibleTip;
	static Timer removeDelay;
	
	public ToolTip(String message, int x, int y) {
		super(true);
		this.setPopupPosition(x, y);
		this.add(new Label(message));
		if(visibleTip != null && visibleTip != this) {
			visibleTip.hide();
			removeDelay.cancel();
		}
		visibleTip = this;
		this.show();
		removeDelay = new Timer() {
			public void run() {
				visibleTip.hide();
			}
		};
		removeDelay.schedule(VISIBLE_DELAY);
		this.addPopupListener(new PopupListener() {
			public void onPopupClosed(PopupPanel sender, boolean autoClosed) {
				removeDelay.cancel();
			}
			
		});
		this.setStyleName("toolTip");
	}
	
	public boolean onEventPreview(Event event) {
		int type = DOM.eventGetType(event);
		switch(type) {
			case Event.ONMOUSEDOWN:
			case Event.ONCLICK: {
				this.hide();
				return true;
			}
		}
		return false;
	}
}
