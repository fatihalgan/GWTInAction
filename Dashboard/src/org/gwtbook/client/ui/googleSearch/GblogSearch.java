package org.gwtbook.client.ui.googleSearch;

import org.gwtbook.client.ui.impl.googleSearch.GblogSearchImpl;

import com.google.gwt.core.client.JavaScriptObject;

public class GblogSearch extends JavaScriptObject {

	/**
	 * Reference to the underlying Javascript object. 
	 */
	private static GblogSearchImpl impl = new GblogSearchImpl();
	
	/**
	 * Create an instance of the GblogSearch object
	 * @return The search object
	 */
	public static GblogSearch create(){
		return impl.create();
	}
	
	public void setUserDefinedLabel(String label) {
		impl.setUserDefinedLabel(this,label);
	}

	/**
	 * Sets a restriction on the sites that are searched over. 
	 * @param site Site searches are to be restricted to.
	 */
	public void setSiteRestriction(String site) {
		impl.setSiteRestriction(this,site);
	}

}
