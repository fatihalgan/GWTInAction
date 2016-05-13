package org.gwtbook.client.ui.googlevideosearch;

import org.gwtbook.client.ui.impl.googleVideoSearch.GSVideoBarImpl;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

public class GSVideoBar extends JavaScriptObject {
	
	private static GSVideoBarImpl impl = new GSVideoBarImpl();
	
	public static GSVideoBar create(Element bar, Element player) {
		return impl.create(bar, player);
	}
	
	public void execute(String searchString) {
		impl.execute(this, searchString);
	}
}
