package org.gwtbook.client.ui.googleSearch;

import org.gwtbook.client.ui.impl.googleSearch.GnewsSearchImpl;

public class GnewsSearch extends GSearch {

	/**
	 * Reference to underlying JAvaScript implementaiton 
	 */
	private static GnewsSearchImpl impl = new GnewsSearchImpl();
	
	/**
	 * Method to create a GwebSearch object
	 * @return The search object.
	 */
	public static GnewsSearch create(){ 
		return impl.create();
	}

}
