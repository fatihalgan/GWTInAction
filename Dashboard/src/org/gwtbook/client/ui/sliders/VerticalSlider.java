package org.gwtbook.client.ui.sliders;

import org.gwtbook.client.ui.PNGImage;

import com.google.gwt.user.client.ui.Widget;

public class VerticalSlider extends Slider {

	public VerticalSlider() {
		super(new PNGImage("sliderVertBar.png", 20, 200), 
				new PNGImage("sliderVertThumb.png", 20, 9), 0, 0, 0, 100,
				0, 50, 20, 9);
		verticalControl = true;
		horizontalControl = true;
		canClickBackground = true;
	}
	
	public VerticalSlider(int start, int end, int startPos) {
		super(new PNGImage("sliderVertBar.png", 20, 200), new PNGImage("sliderVertThumb.png", 20, 9), 0, 0, start, end, 0, startPos, 20, 9);
		verticalControl = true;
		horizontalControl = false;
		canClickBackground = true; 
	}
	
	public VerticalSlider(Widget background, Widget thumb, int start, int end, int startPos, int thumbW, int thumbH){
		super(background, thumb, 0, 0, start, end, 0, startPos, thumbW, thumbH);
		verticalControl = true;
		horizontalControl = false;
		canClickBackground = true;
	}
}
