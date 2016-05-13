package org.gwtbook.client.ui.impl;

import org.gwtbook.client.ui.PNGImage;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class PNGImageImplIE6 extends PNGImageImpl {

	private String url;
	private boolean isPng;
	
	public Element createElement(String url, int width, int height) {
		this.url = url;
		if(url.endsWith(".png") || url.endsWith(".PNG")) {
			isPng = true;
		} else {
			isPng = false;
		}
		if(isPng) {
			Element div = DOM.createDiv();
			DOM.setInnerHTML(div, "<span style=\"display:inline-block;width:" + width + "px;height:" + height + "px;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + url + "', sizingMethod='scale')\"></span>");
			return DOM.getFirstChild(div);
		} else {
			return super.createElement(url, width, height);
		}
	}
	
	public String getUrl(PNGImage image) {
		if(isPng) return url;
		else return super.getUrl(image);
	}
}
