package org.gwtbook.client.ui.googleSearch;

import org.gwtbook.client.ui.DashboardComposite;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

public class GoogleSearch extends DashboardComposite {

	GSearchWidget searchWidget;
	boolean showWebSearch = true;
	boolean showVideoSearch = true;
	boolean showBlogSearch = true;
	boolean showLocalSearch = true;
	boolean showNewsSearch = true;
	
	HorizontalPanel temp;
	ScrollPanel outer;

	public GoogleSearch() {
		super();
		createSearch();
		temp = new HorizontalPanel();
		outer = new ScrollPanel();
		outer.setStyleName("googleSearchScroll");
		temp.add(searchWidget);
		outer.add(temp);
		initWidget(outer);
	}
	
	public void createOptionsMenu() {
		optionsMenuBar.addStyleName("submenu");
    	optionsMenuBar.addItem("Web Search", new Command() {
            public void execute(){
            	showWebSearch = !showWebSearch;
            	recreateSearch();
            }
        });
    	optionsMenuBar.addItem("Video Search", new Command() {
            public void execute(){
            	showVideoSearch = !showVideoSearch;
            	recreateSearch();
            }
        });
    	optionsMenuBar.addItem("Blog Search", new Command() {
            public void execute(){
            	showBlogSearch = !showBlogSearch;
            	recreateSearch();
            }
        });
    	optionsMenuBar.addItem("Local Search", new Command() {
            public void execute(){
            	showLocalSearch = !showLocalSearch;
            	recreateSearch();
            }
        });
	}
	
	private void recreateSearch() {
		createSearch();
		temp.remove(0);
		temp.add(searchWidget);
	}
	
	private void createSearch() {
		GwebSearch webSearch = null;
		GvideoSearch videoSearch = null;
		GblogSearch blogSearch = null;
		GlocalSearch localSearch = null;
		GnewsSearch newsSearch = null;
		
		searchWidget = new GSearchWidget();
		GSearchControl sc = searchWidget.getGSearch();
		sc.addOnKeepListener(new KeepListener() {
			public void onKeep() {
				Window.prompt("Saving a result, what name would you like to give it?", "MySearchResult");
			}
		});
		if(showWebSearch) {
			webSearch = GwebSearch.create();
			sc.addSearcher(webSearch);
		}
		if(showVideoSearch) {
			videoSearch = GvideoSearch.create();
			sc.addSearcher(videoSearch);
		}
		if(showBlogSearch) {
			blogSearch = GblogSearch.create();
			sc.addSearcher(blogSearch);
		}
		if(showLocalSearch) {
			localSearch = GlocalSearch.create();
			sc.addSearcher(localSearch);
		}
		if(showNewsSearch) {
			newsSearch = GnewsSearch.create();
			sc.addSearcher(newsSearch);
		}
		GdrawOptions options = new GdrawOptions();
		options.setDrawMode(GSearchControl.DRAW_MODE_TABBED);
		searchWidget.draw(options);
	}

}
