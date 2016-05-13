package org.gwtbook.client.ui;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Widget;

public class ToggleMenuItem extends TwoComponentMenuItem {

	public class ToggleMenuItemState{
		static public final boolean ON = true;
		static public final boolean OFF = false;
	}
	
	private Widget[] states;
	
	protected boolean state = ToggleMenuItemState.ON;
	
	public ToggleMenuItem(String theText, Widget state1, Widget state2, 
		Command command){
		super(theText, state1, command);
		states = new Widget[2];
		states[0] = state1;
		states[1] = state2;
	}	

	
	public void toggle() {
		setSecondComponent(states[state?1:0]);
		state = !state;
	}
	
	public boolean getState() {
		return state;
	}
}
