package org.gwtbook.client.ui;

import org.gwtbook.client.ui.sliders.GridSlider;
import org.gwtbook.client.ui.sliders.VerticalSlider;
import org.gwtwidgets.client.wrap.Effect;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ChangeListenerCollection;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SourcesChangeEvents;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ColourPickerWidget extends Composite implements SourcesChangeEvents {
	
	PNGImage swatchImage;
	public ColourPickerModel theModel;
	final GridSlider swatch;
	final VerticalSlider hueSlider;
	
	private Label R = new Label();
	private Label G = new Label();
	private Label B = new Label();
	
	private ChangeListenerCollection changeListeners;
	
	public ColourPickerWidget() {
		this(0.1, 0.12, 0.99);
	}
	
	public ColourPickerWidget(double h, double s, double v) {
		// Create an instance of the Model
		theModel = new ColourPickerModel(this, h,s,v);
		// Create the View 
		HorizontalPanel mainPanel = new HorizontalPanel();
		SimplePanel grid = new SimplePanel();
		SimplePanel hue = new SimplePanel();
		hueSlider = new VerticalSlider(new PNGImage("hue.png", 14, 182), new PNGImage("hline.png", 18, 6), 0, 360, (int)(h * 360), 18, 6);
		hueSlider.addStyleName("slider-colour-hue");
		hue.add(hueSlider);
		swatchImage = new PNGImage("pickerbg.png", 180, 180);
		
		swatch = new GridSlider(swatchImage, new Image("select.gif"), 0, 100, 0, 100, (int)(s * 100),(int)((1 - v) * 100), 9, 9);
		swatch.addStyleName("slider-colour-swatch");
		
		grid.add(swatch);
		setSwatchBackground();
		mainPanel.add(grid);
		mainPanel.add(hue);
		VerticalPanel details = new VerticalPanel();
		details.add(R);
		details.add(G);
		details.add(B);
		mainPanel.add(details);
		final VerticalPanel outerPanel = new VerticalPanel();
		outerPanel.add(mainPanel);
		Button close =  new Button("Close");
		close.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				hidePicker();
				//outerPanel.setVisible(false);
			}
		});
		close.setWidth("100%");
		outerPanel.add(close);

		initWidget(outerPanel);
		
		// Establish the controllers
		createController();
	}
	
	public void createController() {
		// Listen for changes in the Hue slider, if so, change the background colour of the grid slider.
		hueSlider.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				double sliderPos = 360 - new Double(hueSlider.model().getCurrentYValue()).doubleValue();
				double fullValue = new Double(360).doubleValue();
				double hVal = (sliderPos / fullValue);
				theModel.setHSV(swatch, hVal, theModel.S, theModel.V);
				setSwatchBackground();
				setColorText();
			}
		});
		// Listen for changes in the grid slider, if any update the model
		swatch.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender){
				double hue = ((360 - new Double(hueSlider.model().getCurrentYValue()).doubleValue()))/360;
				double sat = (new Double(swatch.model().getCurrentXValue()).doubleValue()) / 100;
				double val = (100 - new Double(swatch.model().getCurrentYValue()).doubleValue()) / 100;
				theModel.setHSV(swatch,hue,sat,val);
				setColorText();
			}
		});
	}
	
	public class ColourPickerModel {
		int R, G, B;
		double H, S, V;
		public String RGBHexString;
		private String RGBSwatchHexString;
		
		public ColourPickerModel(Widget sender, double h, double s, double v) {
			setHSV(sender, h,s,v);
		}
		
		private int[] HSVtoRGB(double hueToConvert, double satToConvert, double visToConvert) {
			int R,G,B;
			//String sR="ff",sG="ff",sB="ff";
			if ( satToConvert == 0 ) {
				//HSV values = 0 ÷ 1 
				R = new Double(visToConvert * 255).intValue();
				G = new Double(visToConvert * 255).intValue();
				B = new Double(visToConvert * 255).intValue();
			} else {
				double var_h = hueToConvert * 6;
				if ( var_h == 6 ) var_h = 0;      //H must be < 1
				int var_i = new Double(Math.floor( var_h )).intValue();             //Or ... var_i = floor( var_h )
				double var_1 = visToConvert * ( 1 - satToConvert );
				double var_2 = visToConvert * ( 1 - satToConvert * ( var_h - var_i ) );
				double var_3 = visToConvert * ( 1 - satToConvert * ( 1 - ( var_h - var_i ) ) );
				double var_r;
				double var_g;
				double var_b;
			   
				if(var_i == 0) { 
					var_r = visToConvert; 
					var_g = var_3; 
					var_b = var_1; 
				} else if(var_i == 1) { 
					var_r = var_2; 
					var_g = visToConvert; 
					var_b = var_1;
				} else if(var_i == 2) { 
				   	var_r = var_1; 
				   	var_g = visToConvert;
				   	var_b = var_3; 
				} else if(var_i == 3) { 
					var_r = var_1; 
					var_g = var_2; 
					var_b = visToConvert;
				} else if(var_i == 4) { 
					var_r = var_3; 
					var_g = var_1; 
					var_b = visToConvert;     
				} else { 
					var_r = visToConvert; 
					var_g = var_1;
					var_b = var_2; 
				}
				R = new Double(var_r * 255).intValue();                  //RGB results = 0 ÷ 255
				G = new Double(var_g * 255).intValue();
				B = new Double(var_b * 255).intValue();
			}
			int[] returnArray = new int[3];
			returnArray[0] = R;
			returnArray[1] = G;
			returnArray[2] = B;
			return returnArray;
		}
		
		public String rgbInt2rgbHex(int R, int G, int B) {
			String sR,sG,sB;   
			sR = Integer.toHexString(R);
			sG = Integer.toHexString(G);
			sB = Integer.toHexString(B);
			// Guard against values of R,G,B being only 1 digit long, which will cause 
			// problems in the setting of background colour etc.
			if((sR.length() == 1)){sR = "0" + sR;}
			if((sG.length() == 1)){sG = "0" + sG;}
			if((sB.length() == 1)){sB = "0" + sB;}
			return sR+sG+sB;
		}
		
		public void setHSV(Widget sender, double hue, double sat, double vis) {
			this.H = hue;
			this.S = sat;
			this.V = vis;
			int[] rgbVals = HSVtoRGB(hue, sat, vis);
			this.R = rgbVals[0];
			this.G = rgbVals[1];
			this.B = rgbVals[2];
			this.RGBHexString = rgbInt2rgbHex(rgbVals[0], rgbVals[1], rgbVals[2]);
			rgbVals = HSVtoRGB(hue, 1.0, 1.0);
			// Special Hex value for Swatch Background)
			this.RGBSwatchHexString = rgbInt2rgbHex(rgbVals[0], rgbVals[1], rgbVals[2]);
	    	if(changeListeners != null) changeListeners.fireChange(sender);
		}
	}
	
	private void setSwatchBackground() {
		String backgroundColour = "#" + theModel.RGBSwatchHexString;
		DOM.setStyleAttribute(swatchImage.getElement(),"background", backgroundColour);
	}
	
	private void setColorText() {
		//R.setText(""+theModel.R);
		//G.setText(""+theModel.G);
		//B.setText(""+theModel.B);
	}
	
	public String getHexColour() {
		return "#" + theModel.RGBHexString;
	}
	
	public void addChangeListener(ChangeListener listener) {
		if (changeListeners == null) changeListeners = new ChangeListenerCollection();
		changeListeners.add(listener);
	}
	
	public void removeChangeListener(ChangeListener listener) {
		if (changeListeners != null) changeListeners.remove(listener);
	}
	
	private void hidePicker() {
		Effect.switchOff(this);
	}
}