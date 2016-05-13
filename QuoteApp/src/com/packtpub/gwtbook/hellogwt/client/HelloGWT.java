package com.packtpub.gwtbook.hellogwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HelloGWT implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	  final Label quoteText = new Label();
	  quoteText.setStyleName("quoteLabel");
	  //create the service
	  //Specify the URL at which our service implementation is
	  //running.
	  Timer timer = new Timer() {
		public void run() {
			//create an async callback to handle the result.
			AsyncCallback<String> callback = new AsyncCallback<String>() {
				public void onSuccess(String result) {
					//display the retrieved quote in the label
					quoteText.setText((String) result);
				}
				public void onFailure(Throwable caught) {
					//display the error text if we cant get quote
					quoteText.setText("Failed to get a quote.");
				}
			};
			//Make the call.
			RandomQuoteServiceAsync.IMPL.getQuote(callback);
		}  
	  };
	//Schedule the timer to run once every second
	timer.scheduleRepeating(1000);
	RootPanel.get().add(quoteText);
  }
}
