package org.gwtbook.client.ui;

import org.gwtbook.client.DashboardMessages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.FocusListenerCollection;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SourcesFocusEvents;
import com.google.gwt.user.client.ui.Widget;

public class DashboardPanel extends DialogBox implements SourcesFocusEvents {

	static int lastZIndex = 0;
	int widgetHeight;
	FocusListenerCollection listeners = new FocusListenerCollection();
	private Widget trash;
	private int id;
	private static int lastId = 0;
	private boolean visible = true;
	private DashboardComposite parkComponent;
	private static DashboardPanel current = null;
	
	protected DashboardMessages messages = (DashboardMessages)GWT.create(DashboardMessages.class);
	
	public DashboardPanel(DashboardComposite component, boolean showOnCreate, Widget trash) {
		super();
		this.trash = trash;
		this.id = ++ lastId;
		this.sinkEvents(Event.ONDBLCLICK);
		this.addDashboardComponent(component, showOnCreate);
		center();
	}
	
	public DashboardPanel(Widget trash) {
		throw new RuntimeException("Cannot create new Dasboard Application without an Application component");
	}
	
	public void addFocusListener(FocusListener listener) {
		listeners.add(listener);
	}

	public void removeFocusListener(FocusListener listener) {
		listeners.remove(listener);
	}
	
	public void onBrowserEvent(Event event) {
		super.onBrowserEvent(event);
		int type = DOM.eventGetType(event);
		switch(type) {
			case Event.ONDBLCLICK: {
				toggleShow();
				break;
			}
		}
	}
	
	public void toggleShow() {
		if(visible) {
			int width = this.getOffsetWidth();
			widgetHeight = this.getOffsetHeight();
			Image empty = new Image("hidden.png");
			empty.setWidth(width+"px");
			empty.setHeight("1px");
			this.setWidget(empty);
		} else {
			this.setWidget(parkComponent);
		}
		visible = !visible;
	}
	
	public void onMouseDown(Widget sender, int x, int y) {
		super.onMouseDown(sender, x, y);
		if(DashboardPanel.current != null) {
			DashboardPanel.current.listeners.fireLostFocus(DashboardPanel.current);
			DashboardPanel.current.removeStyleName("selectedDashboardComponent");
			bringToFront();
		}
		DashboardPanel.current = this;
		addStyleName("selectedDashboardComponent");
		DashboardPanel.current.listeners.fireFocus(this);
	}
	
	public void onMouseUp(Widget sender, int x, int y) {
		super.onMouseDown(sender, x, y);
		if(detectCollision(this, trash)) {
			removeFromDashboard();
		}
	}
	
	public void removeFromDashboard() {
		if(getConfirmDelete()) {
			if(Window.confirm(messages.ConfirmDeleteMessage(this.parkComponent.getName()))) {
				this.listeners.fireLostFocus(this);
				hide();
				this.parkComponent.onDelete();
			}
		} else {
			this.listeners.fireLostFocus(this);
			hide();
			this.parkComponent.onDelete();
		}
	}
	
	private native boolean getConfirmDelete()/*-{
		return $wnd.confirmDelete
	}-*/;
	
	public boolean onEventPreview(Event event) {
		return true;
	}
	
	boolean detectCollision(Widget w1, Widget w2) {
		int left1, left2;
		int right1, right2;
		int top1, top2;
		int bottom1, bottom2;
		
		left1 = w1.getAbsoluteLeft();
		left2 = w2.getAbsoluteLeft();
		right1 = w1.getAbsoluteLeft() + w1.getOffsetWidth();
		right2 = w2.getAbsoluteLeft() + w2.getOffsetWidth();
		top1 = w1.getAbsoluteTop();
		top2 = w2.getAbsoluteTop();
		bottom1 = w1.getAbsoluteTop() + w1.getOffsetHeight();
		bottom2 = w2.getAbsoluteTop() + w2.getOffsetHeight();
		
		if(bottom1 < top2) return false;
		if(top1 > bottom2) return false;
		
		if(right1 < left2) return false;
		if(left1 > right2) return false;
		return true;
	}
	
	public static DashboardPanel getCurrent() {
		return current;
	}
	
	public int getId() {
		return id;
	}
	
	private void addDashboardComponent(DashboardComposite component, boolean showAtStart) {
		parkComponent = component;
		this.addFocusListener(component);
		this.setText(component.getName());
		this.setWidget(component);
		if(showAtStart) {
			this.show();
		}
	}
	
	public void center() {
		this.setPopupPosition((Window.getClientWidth()/2 - this.getOffsetWidth()/2), 
				(Window.getClientHeight()/2 - this.getOffsetHeight()/2));
	}
	
	public void bringToFront() {
		DOM.setStyleAttribute(this.getElement(), "zIndex", ""+lastZIndex++);
	}
	
	public void show() {
		super.show();
		center();
		bringToFront();
	}
	
	
}
