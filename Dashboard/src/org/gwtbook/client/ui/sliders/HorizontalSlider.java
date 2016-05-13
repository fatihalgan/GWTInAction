package org.gwtbook.client.ui.sliders;

import org.gwtbook.client.ui.PNGImage;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class HorizontalSlider extends Slider {

	public HorizontalSlider() {
		super(new Image("sliderHorizBar.png"), new Image("sliderHorizThumb.png"), 0, 100, 0, 0, 50, 0, 9, 20);
		verticalControl = false;
		horizontalControl = true;
		canClickBackground = false;
	}
	
	public HorizontalSlider(int start, int end, int startPos) {
		super(new PNGImage("sliderHorizBar.png", 200, 20), new PNGImage("sliderHorizThumb.png", 9, 20), start, end, 0, 0, startPos, 0, 9, 20);
		verticalControl = false;
		horizontalControl = true;
		canClickBackground = false;
	}

	public HorizontalSlider(Widget background, Widget thumb, int start, int end, int startPos, int thumbW, int thumbH) {
		super(background, thumb, 0, 0, start, end, 0, startPos, thumbW, thumbH);
		verticalControl = true;
		horizontalControl = false;
		canClickBackground = false;
	}
}
