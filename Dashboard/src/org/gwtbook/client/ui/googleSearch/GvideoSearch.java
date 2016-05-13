package org.gwtbook.client.ui.googleSearch;

import org.gwtbook.client.ui.impl.googleSearch.GvideoSearchImpl;

import com.google.gwt.core.client.JavaScriptObject;

public class GvideoSearch extends JavaScriptObject {
	
	/**
	 * Reference to underlying JavaScript object 
	 */
	protected static GvideoSearchImpl impl = new GvideoSearchImpl();

	/**
	 * Method for creating a GvideoSearch object.
	 * @return The search object.
	 */
	public static GvideoSearch create(){
		return impl.create();
	}

}
