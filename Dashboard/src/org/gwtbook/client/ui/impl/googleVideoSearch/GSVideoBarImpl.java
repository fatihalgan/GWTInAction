package org.gwtbook.client.ui.impl.googleVideoSearch;

import org.gwtbook.client.ui.googlevideosearch.GSVideoBar;

import com.google.gwt.user.client.Element;

public class GSVideoBarImpl {

	public native GSVideoBar create(Element bar, Element player) /*-{
		var options = {
			largeResultSet : true,
			horizontal : true,
			thumbnailSize : $wnd.GSVideoBar.THUMBNAILS_SMALL }
		var theGSVideoBar = new $wnd.GSVideoBar(bar, player, options);
		return theGSVideoBar;
	}-*/;
	
	public native void execute(GSVideoBar theControl, String searchString) /*-{
		theControl.execute(searchString);
	}-*/;
}
