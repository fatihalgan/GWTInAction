package org.gwtbook.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class InfoDialogBox extends DialogBox {

	public InfoDialogBox() {
		super();
		this.setStyleName("info-DialogBox");
	}

	public InfoDialogBox(boolean autoShow) {
		super(autoShow);
		this.setStyleName("info-DialogBox");
	}

	public void setWidget(Widget w) {
		Button okButton = new Button("OK");
		okButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				closeDialog();
				
			}
		});
		DockPanel holder = new DockPanel();
		HorizontalPanel buttonHolder = new HorizontalPanel();
		buttonHolder.add(okButton);
		buttonHolder.addStyleName("ButtonHolder");
		holder.add(buttonHolder, DockPanel.SOUTH);
		buttonHolder.setCellHorizontalAlignment(okButton, HasHorizontalAlignment.ALIGN_CENTER);
		holder.add(w, DockPanel.CENTER);
		super.setWidget(holder);
	}
	
	private void closeDialog() {
		this.hide();
	}

}
