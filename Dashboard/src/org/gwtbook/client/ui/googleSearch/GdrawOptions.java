package org.gwtbook.client.ui.googleSearch;

import com.google.gwt.user.client.Element;

public class GdrawOptions {

	
	/**
	 * Should the input be taken from a non default input box? 
	 */
	private Element eToAttachInput = null;
	
	/**
	 * What should the draw mode be?
	 */
	private int drawMode = -1;
	
	
	/**
	 * Constructor
	 *
	 */
	public GdrawOptions() {
	
	}
	
	/**
	 * Is the draw mode been set to anything but the default?
	 * @return True if it has.
	 */
	public boolean isDrawModeSet(){
		return (drawMode!=-1);
	}

	/**
	 * Is an non default input box attached to the draw options?
	 * @return true if one is.
	 */
	public boolean isInputAttached(){
		return (eToAttachInput!=null);
	}
	
	
	/**
	 * Get the input element
	 * @return Input element
	 */public Element getInputElement(){
		return eToAttachInput;
	}
	
	 /**
	  * Set input element
	  * @param e Input element
	  */
	public void setInput(Element e){
		eToAttachInput = e;
	}
	
	/**
	 * Set Draw Mode
	 * @param mode The new Draw Mode
	 */
	public void setDrawMode(int mode){
		drawMode = mode;
	}

	/**
	 * Get Draw Mode
	 * @return The Draw Mode.
	 */
	public int getDrawMode(){
		return drawMode;
	}

}
