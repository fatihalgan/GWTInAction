package com.manning.gwtia.ch05.client.intro;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class IntroPanel extends Composite {

	interface InstructionsPanelUiBinder extends UiBinder<Widget, IntroPanel> {}
	private static InstructionsPanelUiBinder uiBinder = GWT.create(InstructionsPanelUiBinder.class);
	
	@UiField
	ScrollPanel scrollPanel;
	
	private ResizeHandler resizeHandler = new ResizeHandler() {
		@Override
		public void onResize(ResizeEvent event) {
			scrollPanel.setHeight((event.getHeight() - 20) + "px");			
		}
	};
	
	public IntroPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		Window.addResizeHandler(resizeHandler);
	}
}
