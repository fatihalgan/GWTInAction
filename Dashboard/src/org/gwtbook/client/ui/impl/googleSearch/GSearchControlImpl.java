/*
 * Copyright 2006 Robert Hanson <iamroberthanson AT gmail.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This code is part of the GWT Widget Library
 */
package org.gwtbook.client.ui.impl.googleSearch;

import org.gwtbook.client.ui.googleSearch.GSearchControl;
import org.gwtbook.client.ui.googleSearch.GblogSearch;
import org.gwtbook.client.ui.googleSearch.GdrawOptions;
import org.gwtbook.client.ui.googleSearch.GlocalSearch;
import org.gwtbook.client.ui.googleSearch.GnewsSearch;
import org.gwtbook.client.ui.googleSearch.GsearcherOptions;
import org.gwtbook.client.ui.googleSearch.GvideoSearch;
import org.gwtbook.client.ui.googleSearch.GwebSearch;
import org.gwtbook.client.ui.googleSearch.KeepListener;

import com.google.gwt.user.client.Element;

public class GSearchControlImpl {

	public native static GSearchControl createSearchObject()/*-{	
	    var theGSearchControl = new $wnd.GSearchControl(); 
	    return theGSearchControl;
	}-*/;

	public native void addSearcher(GSearchControl searchControl, GwebSearch theWebSearcher) /*-{
		searchControl.addSearcher(theWebSearcher);
	}-*/;

	public native void addSearcher(GSearchControl searchControl, GnewsSearch theNewsSearcher) /*-{
		searchControl.addSearcher(theNewsSearcher);
	}-*/;

	
	public native void addSearcher(GSearchControl searchControl, GlocalSearch theLocalSearcher) /*-{
		searchControl.addSearcher(theLocalSearcher);
	}-*/;

	public native void addSearcher(GSearchControl searchControl, GvideoSearch theVideoSearcher) /*-{
		searchControl.addSearcher(theVideoSearcher);
	}-*/;

	public native void addSearcher(GSearchControl searchControl, GblogSearch theBlogSearcher) /*-{
		searchControl.addSearcher(theBlogSearcher);
	}-*/;

	public native void addSearcher(GSearchControl searchControl, GwebSearch theWebSearcher, GsearcherOptions options) /*-{
		var theOptions = new $wnd.GsearcherOptions();
		var expanded =  options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getExpandMode()();
		var rootPanel =  options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getRoot()();
		var videoHeight =  options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getVideoResultsTbHeight()();		
		if (options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getIsExpandedSet()()){
			if (expanded == @org.gwtbook.client.ui.googleSearch.GSearchControl::EXPAND_MODE_CLOSED){
				theOptions.setExpandMode($wnd.GSearchControl.EXPAND_MODE_CLOSED);
			}
			if (expanded == @org.gwtbook.client.ui.googleSearch.GSearchControl::EXPAND_MODE_OPEN){
				theOptions.setExpandMode($wnd.GSearchControl.EXPAND_MODE_OPEN);
			}
			if (expanded == @org.gwtbook.client.ui.googleSearch.GSearchControl::EXPAND_MODE_PARTIAL){
				theOptions.setExpandMode($wnd.GSearchControl.EXPAND_MODE_PARTIAL);
			}
		}
		if (options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getIsRootSet()()){
			theOptions.setRoot(options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getRoot()());
		}
		if (options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getIsVideoHeightSet()()){
			theOptions.setRoot(options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getVideoResultsTbHeight()());
		}
		searchControl.addSearcher(theWebSearcher,theOptions);
	}-*/;

	
	public native void addSearcher(GSearchControl searchControl, GlocalSearch theWebSearcher, GsearcherOptions options) /*-{
		var theOptions = new $wnd.GsearcherOptions();
		var expanded =  options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getExpandMode()();
		var rootPanel =  options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getRoot()();
		var videoHeight =  options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getVideoResultsTbHeight()();		
		if (options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getIsExpandedSet()()){
			if (expanded == @org.gwtbook.client.ui.googleSearch.GSearchControl::EXPAND_MODE_CLOSED){
				theOptions.setExpandMode($wnd.GSearchControl.EXPAND_MODE_CLOSED);
			}
			if (expanded == @org.gwtbook.client.ui.googleSearch.GSearchControl::EXPAND_MODE_OPEN){
				theOptions.setExpandMode($wnd.GSearchControl.EXPAND_MODE_OPEN);
			}
			if (expanded == @org.gwtbook.client.ui.googleSearch.GSearchControl::EXPAND_MODE_PARTIAL){
				theOptions.setExpandMode($wnd.GSearchControl.EXPAND_MODE_PARTIAL);
			}
		}
		if (options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getIsRootSet()()){
			theOptions.setRoot(options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getRoot()());
		}
		if (options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getIsVideoHeightSet()()){
			theOptions.setRoot(options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getVideoResultsTbHeight()());
		}
		searchControl.addSearcher(theWebSearcher,theOptions);
	}-*/;
	
	
	public native void addSearcher(GSearchControl searchControl, GvideoSearch theWebSearcher, GsearcherOptions options) /*-{
		var theOptions = new $wnd.GsearcherOptions();
		var expanded =  options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getExpandMode()();
		var rootPanel =  options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getRoot()();
		var videoHeight =  options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getVideoResultsTbHeight()();		
		if (options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getIsExpandedSet()()){
			if (expanded == @org.gwtbook.client.ui.googleSearch.GSearchControl::EXPAND_MODE_CLOSED){
				theOptions.setExpandMode($wnd.GSearchControl.EXPAND_MODE_CLOSED);
			}
			if (expanded == @org.gwtbook.client.ui.googleSearch.GSearchControl::EXPAND_MODE_OPEN){
				theOptions.setExpandMode($wnd.GSearchControl.EXPAND_MODE_OPEN);
			}
			if (expanded == @org.gwtbook.client.ui.googleSearch.GSearchControl::EXPAND_MODE_PARTIAL){
				theOptions.setExpandMode($wnd.GSearchControl.EXPAND_MODE_PARTIAL);
			}
		}
		if (options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getIsRootSet()()){
			theOptions.setRoot(options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getRoot()());
		}
		if (options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getIsVideoHeightSet()()){
			theOptions.setRoot(options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getVideoResultsTbHeight()());
		}
		searchControl.addSearcher(theWebSearcher,theOptions);
	}-*/;
	
	
	public native void addSearcher(GSearchControl searchControl, GblogSearch theWebSearcher, GsearcherOptions options) /*-{
		var theOptions = new $wnd.GsearcherOptions();
		var expanded =  options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getExpandMode()();
		var rootPanel =  options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getRoot()();
		var videoHeight =  options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getVideoResultsTbHeight()();		
		if (options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getIsExpandedSet()()){
			if (expanded == @org.gwtbook.client.ui.googleSearch.GSearchControl::EXPAND_MODE_CLOSED){
				theOptions.setExpandMode($wnd.GSearchControl.EXPAND_MODE_CLOSED);
			}
			if (expanded == @org.gwtbook.client.ui.googleSearch.GSearchControl::EXPAND_MODE_OPEN){
				theOptions.setExpandMode($wnd.GSearchControl.EXPAND_MODE_OPEN);
			}
			if (expanded == @org.gwtbook.client.ui.googleSearch.GSearchControl::EXPAND_MODE_PARTIAL){
				theOptions.setExpandMode($wnd.GSearchControl.EXPAND_MODE_PARTIAL);
			}
		}
		if (options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getIsRootSet()()){
			theOptions.setRoot(options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getRoot()());
		}
		if (options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getIsVideoHeightSet()()){
			theOptions.setRoot(options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getVideoResultsTbHeight()());
		}
		searchControl.addSearcher(theWebSearcher,theOptions);
	}-*/;
	
	
	public native void addSearcher(GSearchControl searchControl, GnewsSearch theNewsSearcher, GsearcherOptions options) /*-{
		var theOptions = new $wnd.GsearcherOptions();
		var expanded =  options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getExpandMode()();
		var rootPanel =  options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getRoot()();
		var videoHeight =  options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getVideoResultsTbHeight()();		
		if (options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getIsExpandedSet()()){
			if (expanded == @org.gwtbook.client.ui.googleSearch.GSearchControl::EXPAND_MODE_CLOSED){
				theOptions.setExpandMode($wnd.GSearchControl.EXPAND_MODE_CLOSED);
			}
			if (expanded == @org.gwtbook.client.ui.googleSearch.GSearchControl::EXPAND_MODE_OPEN){
				theOptions.setExpandMode($wnd.GSearchControl.EXPAND_MODE_OPEN);
			}
			if (expanded == @org.gwtbook.client.ui.googleSearch.GSearchControl::EXPAND_MODE_PARTIAL){
				theOptions.setExpandMode($wnd.GSearchControl.EXPAND_MODE_PARTIAL);
			}
		}
		if (options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getIsRootSet()()){
			theOptions.setRoot(options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getRoot()());
		}
		if (options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getIsVideoHeightSet()()){
			theOptions.setRoot(options.@org.gwtbook.client.ui.googleSearch.GsearcherOptions::getVideoResultsTbHeight()());
		}
		searchControl.addSearcher(theNewsSearcher,theOptions);
	}-*/;
	
	public native void execute(GSearchControl searchControl,String searchString) /*-{
		searchControl.execute(searchString);
	}-*/;
	
	public native void execute(GSearchControl searchControl) /*-{
		searchControl.execute();
	}-*/;
	

	public native void draw(GSearchControl searchControl, Element div) /*-{
		searchControl.draw(div);
	}-*/;
	
	public native void draw(GSearchControl searchControl, Element div, GdrawOptions options) /*-{
		var theOptions = new $wnd.GdrawOptions();
		if (options.@org.gwtbook.client.ui.googleSearch.GdrawOptions::isInputAttached()()){
			theOptions.setInput(options.@org.gwtbook.client.ui.googleSearch.GdrawOptions::getInputElement()());
		}
		if (options.@org.gwtbook.client.ui.googleSearch.GdrawOptions::isDrawModeSet()()){
			if (options.@org.gwtbook.client.ui.googleSearch.GdrawOptions::getDrawMode()()==@org.gwtbook.client.ui.googleSearch.GSearchControl::DRAW_MODE_LINEAR){
				theOptions.setDrawMode($wnd.GSearchControl.DRAW_MODE_LINEAR);
			} else {
				theOptions.setDrawMode($wnd.GSearchControl.DRAW_MODE_TABBED);
			}
		}
		searchControl.draw(div,theOptions);
	}-*/;

	public native void setLinkTarget(GSearchControl searchControl, int flag) /*-{
		if (flag == @org.gwtbook.client.ui.googleSearch.GSearch::LINK_TARGET_BLANK){
			searchControl.setLinkTarget($wnd.GSearch.LINK_TARGET_BLANK);
		}
		if (flag == @org.gwtbook.client.ui.googleSearch.GSearch::LINK_TARGET_SELF){
			searchControl.setLinkTarget($wnd.GSearch.LINK_TARGET_SELF);
		}
		if (flag == @org.gwtbook.client.ui.googleSearch.GSearch::LINK_TARGET_PARENT){
			searchControl.setLinkTarget($wnd.GSearch.LINK_TARGET_PARENT);
		}
		if (flag == @org.gwtbook.client.ui.googleSearch.GSearch::LINK_TARGET_TOP){
			searchControl.setLinkTarget($wnd.GSearch.LINK_TARGET_TOP);
		}		
	}-*/;

	public native void setTimeoutInterval(GSearchControl searchControl, int timeoutInterval) /*-{
		if (timeoutInterval == @org.gwtbook.client.ui.googleSearch.GSearchControl::TIMEOUT_SHORT){
			searchControl.setTimeoutInterval($wnd.GSearchControl.TIMEOUT_SHORT);
		}
		if (timeoutInterval == @org.gwtbook.client.ui.googleSearch.GSearchControl::TIMEOUT_MEDIUM){
			searchControl.setTimeoutInterval($wnd.GSearchControl.TIMEOUT_MEDIUM);
		}
		if (timeoutInterval == @org.gwtbook.client.ui.googleSearch.GSearchControl::TIMEOUT_LONG){
			searchControl.setTimeoutInterval($wnd.GSearchControl.TIMEOUT_LONG);
		}
	}-*/;

	public native void setResultSetSize(GSearchControl searchControl, int size) /*-{
		if (size == @org.gwtbook.client.ui.googleSearch.GSearch::LARGE_RESULTSET){
			searchControl.setResultSetSize($wnd.GSearch.LARGE_RESULTSET);
		}
		if (size == @org.gwtbook.client.ui.googleSearch.GSearch::SMALL_RESULTSET){
			searchControl.setResultSetSize($wnd.GSearch.SMALL_RESULTSET);
		}
	}-*/;

	public native void cancelSearch(GSearchControl searchControl) /*-{
		searchControl.cancel();
	}-*/;

	public native void clearAllResults(GSearchControl searchControl) /*-{
		searchControl.clearAllResults();
	}-*/;
	
	public native void run()/*-{
	    $wnd.alert("Clicked");
	}-*/;

	public native void setOnKeepCallback(GSearchControl searchControl, KeepListener theListener) /*-{
	    $wnd.__callbackMethod = function(){
	        theListener.@org.gwtbook.client.ui.googleSearch.KeepListener::onKeep()();
	    }
		searchControl.setOnKeepCallback(null, $wnd.__callbackMethod, $wnd.GSearchControl.KEEP_LABEL_KEEP);
	}-*/;

	public native String getResults(GSearchControl searchControl) /*-{
		return searchControl.results;
	}-*/;
}
