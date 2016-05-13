package com.manning.gwtia.ch06.client.split;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class SplitLayoutPanelExample extends Composite {

	private static SplitLayoutPanelExampleUiBinder uiBinder = GWT.create(SplitLayoutPanelExampleUiBinder.class);

	interface SplitLayoutPanelExampleUiBinder extends UiBinder<Widget, SplitLayoutPanelExample> { }

	public SplitLayoutPanelExample() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
