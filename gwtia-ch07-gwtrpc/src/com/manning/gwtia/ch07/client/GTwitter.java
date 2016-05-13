package com.manning.gwtia.ch07.client;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.manning.gwtia.ch07.shared.FeedData;
import com.manning.gwtia.ch07.shared.TwitterService;
import com.manning.gwtia.ch07.shared.TwitterServiceAsync;

/**
 * Renders a simple interface for getting a specified user's Twitter
 * history.  The interface has the user to type in the target
 * user's Twitter name into a text box and then clicking the "Get Tweets"
 * button will display the user's history on the page.
 */
public class GTwitter extends Composite {

	/** Text box for entering in the Twitter user's name. */
    private TextBox txtScreenName = new TextBox();
  
    /** Button to activate the fetching of tweets. */
    private Button btnGetTweets = new Button("Get Tweets");
  
    /** Panel to display the tweets returned by the server-side service. */
    private VerticalPanel tweetPanel = new VerticalPanel();

    
    public GTwitter() {
    	FlowPanel rootPanel = new FlowPanel();
    	rootPanel.add(txtScreenName);
    	rootPanel.add(btnGetTweets);
    	rootPanel.add(tweetPanel);
    	initWidget(rootPanel);
    	
    	/*
         * An async callback coded as an anonymous class.  This can easily be moved into an inner
         * class or a separate class, making it easier to test as a separate unit.
         * 
         * This callback handles the results from a call to the getUserTimeline() method on the server.
         */
    	final AsyncCallback<ArrayList<FeedData>> updateTweetPanelCallback = 
    			new AsyncCallback<ArrayList<FeedData>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error: " + caught.getMessage());
					}

					@Override
					public void onSuccess(ArrayList<FeedData> result) {
						tweetPanel.clear();
			            for (FeedData status : result) {
			            	PredefinedFormat fmt = PredefinedFormat.TIME_SHORT;
			            	String dateStr = DateTimeFormat.getFormat(fmt).format(status.getCreatedAt());
			            	tweetPanel.add(new Label(dateStr + ": " + status.getText()));
			            }	
					}
    			};
    			
    			/*
    	         * An click handler coded as an anonymous class.  This can easily be moved into an inner
    	         * class or a separate class, making it easier to test as a separate unit.
    	         * 
    	         * This method triggers a call to getUserTimeline() on the server when a user
    	         * clicks the btnGetTweets button.
    	         */
    			btnGetTweets.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						TwitterServiceAsync service = GWT.create(TwitterService.class);
						/*
			             * Alternative way to set the target URL on the server.  In most cases (as we do here),
			             * you would use the @RemoteServiceRelativePath annotation on the service interface.
			             */
			            // ((ServiceDefTarget) service).setServiceEntryPoint(GWT.getModuleBaseURL() + "service");
						service.getUserTimeline(txtScreenName.getText(), updateTweetPanelCallback);

					}
				});
    }
}
