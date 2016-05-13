package org.gwtbook.client.ui.googleSearch;

import org.gwtbook.client.ui.impl.googleSearch.GlocalSearchImpl;

import com.google.gwt.core.client.JavaScriptObject;

public class GlocalSearch extends JavaScriptObject {
	
	/**
	 * Link to the underlying JavaScript object 
	 */
	private static GlocalSearchImpl impl = new GlocalSearchImpl();
	

	/**
	 * Create an instance of a GlocalSearch object.
	 * @return
	 */
	public static GlocalSearch create(){
		return impl.create();
	}
	
	/**
	 * Execute a search on this particular object only.
	 *
	 */
	public void execute(){
		impl.execute(this);
	}
	
	/**
	 * Method to set the centre point - currently only strings are allowed
	 * but the API allows a GLatLng to be used, though this would imply a
	 * dependancy on Google Maps which we wish to avoid, just for now. 
	 * @param location
	 */
	public void setCenterPoint(String location) {
		impl.setCenterPoint(this,location);		
	}
	
}
