package org.gwtbook.client.ui.googleSearch;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class GSearchWidget extends Widget {
	
	GSearchControl gsearchControl = null;
	GwebSearch gwebSearch = null;
	GlocalSearch glocalSearch = null;
	GvideoSearch gvideoSearch = null;
	GblogSearch gblogSearch = null;
	GnewsSearch gnewsSearch = null;
	GsearcherOptions gSearcherOptions = null;
	
	/**
	 * Create the search Widget in a specific Element.
	 * @param e
	 */
	public GSearchWidget(Element e) {
	    setElement(e);
	}
	
	/**
	 * Create the search Widget in a newly created Element.
	 *
	 */
	public GSearchWidget() {
		Element e = DOM.createDiv();
	    setElement(e);
	}
	
	/**
	 * Create a new GSearchControl JavaScript object.
	 * @return A newly created GSearchControl object. 
	 */
	public GSearchControl getGSearch() {
		if (gsearchControl == null) {
			gsearchControl = GSearchControl.create();
		}
		return gsearchControl;
	}

	/**
	 * Create a new GwebSearch JavaScript object.
	 * @return A newly created GwebSearch object. 
	 */
	public GwebSearch getGwebSearch() {
		if (gwebSearch == null) {
			gwebSearch = GwebSearch.create();
		}
		return gwebSearch;
	}
	
	/**
	 * Create a new GvideoSearch JavaScript object.
	 * @return A newly created GvideoSearch object.
	 */
	public GvideoSearch getGvideoSearch() {
		if (gvideoSearch == null) {
			gvideoSearch = GvideoSearch.create();
		}
		return gvideoSearch;
	}
	
	/**
	 * Create a new GblogSearch JavaScript object.
	 * @return A newly created GblogSearch object.
	 */
	public GblogSearch getGblogSearch() {
		if (gblogSearch == null) {
			gblogSearch = GblogSearch.create();
		}
		return gblogSearch;
	}

	/**
	 * Create a new GlocalSearch JavaScript object.
	 * @return A newly created GlocalSearch object .
	 */
	public GlocalSearch getGlocalSearch() {
		if (glocalSearch == null) {
			glocalSearch = GlocalSearch.create();
		}
		return glocalSearch;
	}


	/**
	 * Create a new GnewsSearch JavaScript object.
	 * @return A newly created GnewsSearch object .
	 */
	public GnewsSearch getGnewsSearch() {
		if (gnewsSearch == null) {
			gnewsSearch = GnewsSearch.create();
		}
		return gnewsSearch;
	}
	
	/**
	 * Add a onKeep Listener object to the search widget.
	 * Not working.
	 *  
	 * @param theListener The Callback code fow when keep option for 
	 * a search element is selectd.
	 */
	public void addOnKeepListener(KeepListener theListener) {
		gsearchControl.addOnKeepListener(theListener);
	}
	


	/**
	 * Draw the object in the element it is assigned into.
	 *
	 */
	public void draw() {
		Element e = getElement();
		gsearchControl.draw(e);
	}

	/**
	 * Draw the object in the element it is assigned into, but take
	 * into account the specified search options.
	 * 
	 * @param theOptions The specified search options.
	 * @see GdrawOptions
	 */
	public void draw(GdrawOptions theOptions) {
		Element e = getElement();
		gsearchControl.draw(e,theOptions);
	}		
	
	/**
	 * Execute a search for the specified string.
	 * 
	 * @param string Search specification.
	 */
	public void execute(String string) {
		gsearchControl.execute(string);
	}

	/**
	 *  Execute a search.  Value to search for comes from search input 
	 *  box.
	 *
	 */
	public void execute() {
		gsearchControl.execute();
	}

	/**
	 * Add a GwebSearch to the search control object.
	 * 
	 * @param theWebSearcher
	 */
	public void addSearcher(GwebSearch theWebSearcher) {
		gsearchControl.addSearcher(theWebSearcher);
	}

	/**
	 * Add a GwebSearch to the search control object and control it with 
	 * the provided set op options.
	 * 
	 * @param theWebSearcher
	 * @param options
	 */
	public void addSearcher(GwebSearch theWebSearcher, GsearcherOptions options) {
		gsearchControl.addSearcher(theWebSearcher, options);
	}
	
	/**
	 * Add a GlocalSearch to the search control object.
	 * 
	 * @param theLocalSearcher
	 */
	public void addSearcher(GlocalSearch theLocalSearcher) {
		gsearchControl.addSearcher(theLocalSearcher);
	}

	/**
	 * Add a GlocalSearch to the search control object and control it with 
	 * the provided set op options.

	 * @param theLocalSearcher
	 * @param options
	 */
	public void addSearcher(GlocalSearch theLocalSearcher, GsearcherOptions options) {
		gsearchControl.addSearcher(theLocalSearcher, options);
	}
	
	/**
	 * Add a GvideoSearch to the search control object.
	 * 
	 * @param theVideoSearcher
	 */
	public void addSearcher(GvideoSearch theVideoSearcher) {
		gsearchControl.addSearcher(theVideoSearcher);
	}

	/**
	 * Add a GvideoSearch to the search control object and control it with 
	 * the provided set op options.
	 * 
	 * @param theVideoSearcher
	 * @param options
	 */
	public void addSearcher(GvideoSearch theVideoSearcher, GsearcherOptions options) {
		gsearchControl.addSearcher(theVideoSearcher, options);
	}
	
	
	/**
	 * Add a GblogSearch to the search control object.
	 *  
	 * @param theBlogSearcher
	 */
	public void addSearcher(GblogSearch theBlogSearcher) {
		gsearchControl.addSearcher(theBlogSearcher);
	}
	
	/**
	 * Add a GblogSearch to the search control object and control it with 
	 * the provided set op options.
	 * 
	 * @param theBlogSearcher
	 * @param options
	 */
	public void addSearcher(GblogSearch theBlogSearcher, GsearcherOptions options) {
		gsearchControl.addSearcher(theBlogSearcher, options);
	}
	
	
	/**
	 * Set where the search result will be shown if clicked upon.
	 * Values should come from the GSearch object, e.g.
	 * 
	 * GSearch.LINK_TARGET_BLANK
	 * GSearch.LINK_TARGET_PARENT
	 * 
	 * @param flag
	 * @see Gsearch
	 */
	public void setLinkTarget(int flag) {
		gsearchControl.setLinkTarget(flag);
	}

	/**
	 * Set the search timeout value before searches start when using a non
	 * standard input box.  Values come from GSearchControl and include
	 * 
	 * GSearchControl.TIMEOUT_SHORT
	 * GSearchControl.TIMEOUT_MEDIUM
	 * GSearchControl.TIMEOUT_LONG
	 * 
	 * @param timeoutInterval
	 * @see GSearchControl
	 */
	public void setTimeoutInterval(int timeoutInterval) {
		gsearchControl.setTimeoutInterval(timeoutInterval);
	}
	
	
	/**
	 * Set the Result Size for the searches.  Values come from the GSearch 
	 * object, and include
	 * 
	 * GSearch.LARGE_RESULTSET
	 * GSearch.SMALL_RESULTSET 
	 * 
	 * @param size
	 * @see GSearch
	 */
	public void setResultSetSize(int size) {
		gsearchControl.setResultSetSize(size);
	}
	
	/**
	 * Cancel an ongoing search.
	 *
	 */
	public void cancelSearch() {
		gsearchControl.cancelSearch();
	}
	
	/**
	 * Clear all search results.
	 *
	 */
	public void clearAllResults() {
		gsearchControl.clearAllResults();
	}

}
