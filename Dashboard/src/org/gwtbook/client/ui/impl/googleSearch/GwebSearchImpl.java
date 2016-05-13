package org.gwtbook.client.ui.impl.googleSearch;

import org.gwtbook.client.ui.googleSearch.GwebSearch;


public class GwebSearchImpl extends GSearchImpl {
	
	public native GwebSearch create() /*-{
		return new $wnd.GwebSearch();
	}-*/;

	public native void setSiteRestriction(GwebSearch searcher, String site) /*-{
		searcher.setSiteRestriction(site);
	}-*/;

	public native String getResults(GwebSearch searcher) /*-{
		return "Results"+searcher.results;
	}-*/;

	public native void setUserDefinedLabel(GwebSearch searcher, String theString)/*-{
		searcher.setUserDefinedLabel(theString);
	}-*/;
}
