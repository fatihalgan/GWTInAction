package org.gwtbook.client.ui.impl.googleSearch;

import org.gwtbook.client.ui.googleSearch.GblogSearch;

public class GblogSearchImpl {

	public native GblogSearch create()/*-{
		return new $wnd.GblogSearch();
	}-*/;

	public native void setSiteRestriction(GblogSearch searcher, String site)/*-{
		searcher.setSiteRestriction(site);
	}-*/;

	public native void setUserDefinedLabel(GblogSearch searcher, String theString)/*-{
		searcher.setUserDefinedLabel(theString);
	}-*/;
}
