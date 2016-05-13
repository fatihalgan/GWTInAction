package org.gwtbook.client.ui.impl.googleSearch;

import org.gwtbook.client.ui.googleSearch.GnewsSearch;

public class GnewsSearchImpl {

	public native GnewsSearch create() /*-{
		return new $wnd.GnewsSearch();
	}-*/;

	public native void execute(GnewsSearch search) /*-{
		search.execute();
	}-*/;

}
