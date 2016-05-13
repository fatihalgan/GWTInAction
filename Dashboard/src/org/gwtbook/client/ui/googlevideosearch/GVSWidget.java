package org.gwtbook.client.ui.googlevideosearch;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GVSWidget extends Composite {
	
	GSVideoBar gsVideoBar = null;
	
	public GSVideoBar getGSVideoBar(Element bar, Element player) {
		if(gsVideoBar == null) gsVideoBar = GSVideoBar.create(bar, player);
		return gsVideoBar;
	}
	
	VerticalPanel theArea = new VerticalPanel();
	Label bar2 = new Label("Loading");
	Label player2 = new Label("Loading");
	
	public GVSWidget() {
		theArea.add(bar2);
		theArea.add(player2);
		initWidget(theArea);
		getGSVideoBar(bar2.getElement(), player2.getElement());
	}
	
	public void execute(String searchText) {
		gsVideoBar.execute(searchText);
	}
}
