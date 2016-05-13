package org.gwtbook.client.ui;

import org.gwtbook.client.ui.impl.FlashMovieImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class FlashMovie extends Composite {
	
	FlashMovieImpl impl = (FlashMovieImpl)GWT.create(FlashMovieImpl.class);
	
	public static class FlashMovieParameters {
		public String movieName;
	}
	
	public FlashMovie(FlashMovieParameters params) {
		SimplePanel panel = new SimplePanel();
		String js = impl.createMovie(params);
		DOM.setInnerHTML(panel.getElement(), js);
		initWidget(panel);
	}
	
	public FlashMovie() {
		new RuntimeException("Need Flash Movie Parameters!");
	}
}
