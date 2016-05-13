package org.gwtbook.client.ui.impl.googleSearch;

import org.gwtbook.client.ui.googleSearch.GlocalSearch;


public class GlocalSearchImpl extends GSearchImpl {
	
	public native GlocalSearch create() /*-{
		return new $wnd.GlocalSearch();
	}-*/;

	public native void setCenterPoint(GlocalSearch theLocalSearch, String location) /*-{
		theLocalSearch.setCenterPoint(location);
	}-*/;

	public native void execute(GlocalSearch search) /*-{
		search.execute();
	}-*/;

}
