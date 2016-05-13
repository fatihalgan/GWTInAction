package org.gwtbook.client.ui.googleSearch;

import org.gwtbook.client.ui.impl.googleSearch.GwebSearchImpl;

public class GwebSearch extends GSearch {

	/**
	 * Reference to underlying JavaScript implementaiton 
	 */
	private static GwebSearchImpl impl = new GwebSearchImpl();
	
	/**
	 * Method to create a GwebSearch object
	 * @return The search object.
	 */
	public static GwebSearch create(){ 
		return impl.create();
	}

	/**
	 * Sets a restriction on the sites that are searched over. 
	 * @param site Site searches are to be restricted to.
	 */
	public void setSiteRestriction(String site) {
		impl.setSiteRestriction(this,site);
	}

}
