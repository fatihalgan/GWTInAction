package org.gwtbook.client.ui.sliders;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class GridSlider extends Slider {

	public GridSlider() {
		super(new Image("sliderGridBar.png"), new Image("sliderGridThumb.png"), 0, 100, 0, 100, 50, 50, 9, 9);
		verticalControl = true;
		horizontalControl = true;
		canClickBackground = true; // set to false functionality not working
	}
	
	public GridSlider(int startX, int endX, int startY, int endY, int startXPos, int startYPos) {
		super(new Image("sliderGridBar.png"), new Image("sliderGridThumb.png"), startX, endX, startY, endY, startXPos, startYPos, 9, 9);
		verticalControl = true;
		horizontalControl = true;
		canClickBackground = true;
	}

	public GridSlider(Widget background, Widget thumb, int startX, int endX, int startY, int endY, int startXPos, int startYPos, int thumbW, int thumbH) {
		super(background,thumb, startX, endX, startY, endY, startXPos, startYPos,thumbW, thumbH);
		verticalControl = true;
		horizontalControl = true;
		canClickBackground = true;
	}
}
