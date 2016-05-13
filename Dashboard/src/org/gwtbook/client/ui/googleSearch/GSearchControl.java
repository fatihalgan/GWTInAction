package org.gwtbook.client.ui.googleSearch;

import org.gwtbook.client.ui.impl.googleSearch.GSearchControlImpl;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

public class GSearchControl extends JavaScriptObject {
	
	/**
	 * Object that implements the JNSI calls to the GSearchControl
	 */
	private static GSearchControlImpl impl = new GSearchControlImpl();
	
	/**
	 * Various constants defined here
	 */
	public static final int DRAW_MODE_LINEAR=1;
	public static final int DRAW_MODE_TABBED=2;
	public static final int TIMEOUT_SHORT = 10;
	public static final int TIMEOUT_MEDIUM = 11;
	public static final int TIMEOUT_LONG = 12;
	public static final int EXPAND_MODE_CLOSED = 20;
	public static final int EXPAND_MODE_OPEN = 21;
	public static final int EXPAND_MODE_PARTIAL = 22;
	
	/**
	 * Create method provides an instance from the underlying javascript object
	 * @return A valid GSearchControl Javascript object
	 */
	public static GSearchControl create(){
		return GSearchControlImpl.createSearchObject();
	}
	
	/**
	 * For future use
	 * @param theListener
	 */
	public  void addOnKeepListener(KeepListener theListener){
		impl.setOnKeepCallback(this, theListener);	
	}


	public void execute(String searchString){
		impl.execute(this, searchString);
	}

	public void execute() {
		impl.execute(this);
	}

	
	public void addSearcher(GwebSearch theWebSearcher) {
		impl.addSearcher(this, theWebSearcher);
	}
	
	public void addSearcher(GlocalSearch theLocalSearcher) {
		impl.addSearcher(this,theLocalSearcher);
	}
	
	public void addSearcher(GvideoSearch theVideoSearcher) {
		impl.addSearcher(this,theVideoSearcher);
	}
	
	public void addSearcher(GblogSearch theBlogSearcher) {
		impl.addSearcher(this,theBlogSearcher);
	}
	
	public void addSearcher(GnewsSearch theNewsSearcher) {
		impl.addSearcher(this,theNewsSearcher);
	}
	
	public void addSearcher(GwebSearch theWebSearcher, GsearcherOptions options) {
		impl.addSearcher(this, theWebSearcher, options);
	}
	
	public void addSearcher(GlocalSearch theLocalSearcher, GsearcherOptions options) {
		impl.addSearcher(this,theLocalSearcher, options);
	}
	
	public void addSearcher(GvideoSearch theVideoSearcher, GsearcherOptions options) {
		impl.addSearcher(this,theVideoSearcher, options);
	}
	
	public void addSearcher(GblogSearch theBlogSearcher, GsearcherOptions options) {
		impl.addSearcher(this,theBlogSearcher, options);
	}
	
	public void draw(Element e){
		impl.draw(this,e);
	}
	
	public void draw(Element e, GdrawOptions options){
		impl.draw(this,e,options);
	}	
	
	public void setLinkTarget(int flag){
		impl.setLinkTarget(this,flag);
	}
	
	public void setTimeoutInterval(int timeoutInterval){
		impl.setTimeoutInterval(this,timeoutInterval);
	}

	public void setResultSetSize(int size) {
		impl.setResultSetSize(this,size);
	}

	public void cancelSearch() {
		impl.cancelSearch(this);
	}
	
	public void clearAllResults(){
		impl.clearAllResults(this);
	}

}
