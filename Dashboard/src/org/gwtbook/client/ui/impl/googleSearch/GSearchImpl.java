package org.gwtbook.client.ui.impl.googleSearch;

import org.gwtbook.client.ui.googleSearch.GSearch;

public class GSearchImpl {

	public native void setUserDefinedLabel(GSearch search, String label) /*-{
		search.setUserDefinedLabel(label);
	}-*/;
	
	public native int getResultSetSize(GSearch search) /*-{
		return search.getResultSetSize();
	}-*/;

	public native void execute(GSearch search) /*-{
		search.execute();
	}-*/;

}
