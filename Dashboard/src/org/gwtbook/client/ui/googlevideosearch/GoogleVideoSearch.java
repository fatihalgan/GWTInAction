package org.gwtbook.client.ui.googlevideosearch;

import org.gwtbook.client.ui.DashboardComposite;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GoogleVideoSearch extends DashboardComposite {
	
	VerticalPanel theArea = new VerticalPanel();
	HorizontalPanel theSearchInput = new HorizontalPanel();
	TextBox theSearch = new TextBox();
	Button startSearch = new Button("Search");
	GVSWidget theWidget;
	
	public GoogleVideoSearch() {
		super();
		theSearchInput.add(theSearch);
		theSearchInput.add(startSearch);
		theWidget = new GVSWidget();
		theArea.add(theWidget);
		theArea.add(theSearchInput);
		startSearch.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				performSearch(theSearch.getText());
				
			}
			
		});
		theSearch.addKeyboardListener(new KeyboardListenerAdapter() {
			public void onKeyDown(Widget sender, char theKey, int modifiers) {
				if (theKey==13) {
					startSearch.click();
				}
			}
		});
		initWidget(theArea);
		performSearch("porjus aurora");
	}
	
	public void performSearch(String searchText) {
		theWidget.execute(searchText);
	}
	
}
