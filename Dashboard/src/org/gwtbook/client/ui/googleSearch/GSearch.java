package org.gwtbook.client.ui.googleSearch;

import com.google.gwt.core.client.JavaScriptObject;

public class GSearch extends JavaScriptObject {
	
	public static final int LARGE_RESULTSET = 1;
	public static final int SMALL_RESULTSET = 2;
	public static final int LINK_TARGET_BLANK=10;
	public static final int LINK_TARGET_SELF=11;
	public static final int LINK_TARGET_TOP=12;
	public static final int LINK_TARGET_PARENT=13;
	
	public int getResultSize(){
		return 10;
	}
	
}
