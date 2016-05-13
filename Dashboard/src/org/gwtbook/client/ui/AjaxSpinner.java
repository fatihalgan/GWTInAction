package org.gwtbook.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;

public class AjaxSpinner extends Composite {
	
	private FlowPanel spinnerPanel = new FlowPanel();
	private Image spinnerImage;
	
	AjaxSpinner() {
		spinnerImage = new Image("ajax_spinner.gif");
		spinnerPanel.add(spinnerImage);
		initWidget(spinnerPanel);
	}
	
	public AjaxSpinner(boolean state) {
		this();
		if (!state) hide();
	}
	
	public void hide() {
		spinnerImage.setVisible(false);
	}
	
	public void show() {
		spinnerImage.setVisible(true);
	}
}
