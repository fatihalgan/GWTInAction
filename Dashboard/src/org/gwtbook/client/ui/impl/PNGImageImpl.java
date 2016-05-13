package org.gwtbook.client.ui.impl;

import org.gwtbook.client.ui.PNGImage;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class PNGImageImpl {

	public Element createElement(String url, int width, int height) {
		Element result = DOM.createImg();
		DOM.setAttribute(result, "src", url);
		DOM.setIntAttribute(result, "width", width);
		DOM.setIntAttribute(result, "height", height);
		return result;
	}
	
	public String getUrl(PNGImage image) {
		return DOM.getAttribute(image.getElement(), "src");
	}
}
