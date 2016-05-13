package org.gwtbook.client.ui.googleSearch;

import com.google.gwt.user.client.Element;

public class GsearcherOptions {

	private int expandMode = -1;
	private Element rootElement = null;
	private int MAX_HEIGHT = 100;
	private int videoHeight = -1;
	
	public GsearcherOptions() {
		
	}
	
	/**
	 * Return if a new Expanded Mode has been set
	 * @return True if it has
	 */
	public boolean getIsExpandedSet(){
		return (expandMode>-1);
	}
	
	/**
	 * Return if a new root element for result display is set 
	 * @return True if so
	 */
	public boolean getIsRootSet(){
		return (rootElement!=null);
	}
	
	/**
	 * Return if the video height is set 
	 * @return True if so.
	 */
	public boolean getIsVideoHeightSet(){
		return (videoHeight>-1);
	}
	
	/**
	 * Set the expand Mode for the searcher 
	 * @param expandModeVal
	 */
	public void setExpandMode(int expandModeVal){
		expandMode = expandModeVal;
	}
	
	
	
	/**
	 * Get the expanded mode set for this searhcer 
	 * @return the expandMode
	 */
	public int getExpandMode(){
		return expandMode;
	}
	
	
	
	/**
	 * Set new root display element
	 * @param e root Display element
	 */
	public void setRoot(Element e){
		rootElement = e;
	}
	
	
	/**
	 * Get the new root display element 
	 * @return
	 */
	public Element getRoot(){
		return rootElement;
	}
	
	
	/**
	 * Set the height for display of videos in the search
	 * Not working properly in this version
	 * @param proposedHeight
	 */
	public void setVideoResultsTbHeight(int proposedHeight){
		if (proposedHeight > MAX_HEIGHT){
			proposedHeight = MAX_HEIGHT;
		}
		videoHeight = proposedHeight;
	}
	
	/**
	 * Get the value set for video display height
	 * @return
	 */
	public int getVideoResultsTbHeight(){
		return videoHeight;
	}
}
