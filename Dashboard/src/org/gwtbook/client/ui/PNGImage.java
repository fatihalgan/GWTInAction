package org.gwtbook.client.ui;

import org.gwtbook.client.ui.impl.PNGImageImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Image;

public class PNGImage extends Image {

	private PNGImageImpl impl;
	
	public PNGImage(String url, int width, int height) {
		impl = (PNGImageImpl)GWT.create(PNGImageImpl.class);
		setElement(impl.createElement(url, width, height));
		sinkEvents(Event.ONCLICK | Event.MOUSEEVENTS | Event.ONLOAD | Event.ONERROR);
	}

	public String getUrl() {
		return impl.getUrl(this);
	}
	
	public void setUrl() {
		throw new RuntimeException("Not allowed to set url for a PNG image");
	}

}
