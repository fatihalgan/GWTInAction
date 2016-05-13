package com.manning.gwtia.ch04.client.animation;

import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;


public class AnimationExample extends Composite {

	InlineLabel text;
	Highlight highlightEffect;
	
	FlowPanel holder;
	
	
	/**
	 * Sets up the widget.
	 * We create the GUI, set up event handling, and call initWidget on the holder panel.
	 */
	public AnimationExample(){
		setUpGui();
		// Set up handling of events for the clicking of the button to animate constraints.
		setUpEventHandling();
		initWidget(holder);
	}

	
	private void setUpGui() {
		text = new InlineLabel("Move Mouse over me!");
		highlightEffect = new Highlight(text);
		holder = new FlowPanel();
		holder.add(text);
	}
	
	/**
	 * Add a ClickHandler to the animateConstraint buttons so that when it is clicked
	 * the LayoutPanel returns to its original shape.
	 */
	private void setUpEventHandling() {
		text.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				highlightEffect.run(500000);				
			}
		});
	}
}
