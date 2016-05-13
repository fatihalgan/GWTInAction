package org.gwtbook.client.ui.sliders;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventPreview;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ChangeListenerCollection;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SourcesChangeEvents;
import com.google.gwt.user.client.ui.SourcesClickEvents;
import com.google.gwt.user.client.ui.SourcesMouseEvents;
import com.google.gwt.user.client.ui.Widget;

public abstract class Slider extends Composite implements MouseListener, SourcesChangeEvents, Observer {
	
	private SliderThumb theSliderThumbnail;
	
	public boolean verticalControl = true;
	
	public boolean horizontalControl = false;
	
	public boolean canClickBackground = false;
	
	private ChangeListenerCollection changeListeners;
	
	private int thumbHeight;
	private int thumbWidth;
	
	int startX;
	int startY;
	int endX;
	int endY;
	
	private int startXPosition = 0;
	private int startYPosition = 0;
	private float totalRealLifeX = 0; 
	private float totalRealLifeY = 0; 
	
	private float divisionsX = 0;
	private float divisionsY = 0;
	
	private float totalOnScreenX = 0;
	private float totalOnScreenY = 0; 

	private Widget background;
	private FlowPanel mainPanel;
	private FlowPanel backgroundPanel;
	
	private SliderModel theModel = new SliderModel();
	
	public Slider(Widget slideRule, Widget slideThumb, int startX, int endX, int startY, int endY, int startXPos, int startYPos, int thumbWidth, int thumbHeight) {
		// Establish the "Model"
		model().setRealStartX(startX);
		model().setRealEndX(endX);
		model().setRealStartY(startY);
		model().setRealEndY(endY);
		startXPosition = startXPos;
		startYPosition = startYPos;
		model().setXValue(startXPos);
		model().setYValue(startYPos);
		
		// Capture thumbnail details
		this.thumbHeight = thumbHeight;
		this.thumbWidth = thumbWidth;
		
		// Create the "View" 
		createLayout(slideRule, slideThumb);
		
		// Create the "Controller"
		controller();
		model().addObserver(this);
	}

	private void controller(){ }
	
	private void createLayout(Widget slideRule, Widget slideThumb){
		DOM.addEventPreview(new EventPreview() {
			public boolean onEventPreview(Event event) {
				switch (DOM.eventGetType(event)) {
					case Event.ONMOUSEDOWN:
					case Event.ONMOUSEMOVE:
					case Event.ONMOUSEUP:
						DOM.eventPreventDefault(event);
				}
				return true;
	        }
	      	});
	    
		background = slideRule;
		background.addStyleName("slider-background");
		
		mainPanel = new FlowPanel();
		mainPanel.addStyleName("slider");
		
		backgroundPanel = new FlowPanel();
		
		theSliderThumbnail = new SliderThumb(slideThumb);

		backgroundPanel.add(background);
		backgroundPanel.add(theSliderThumbnail);

		mainPanel.add(backgroundPanel);
		
		initWidget(mainPanel);
		
		if (!(slideRule instanceof SourcesClickEvents)) {
            throw new RuntimeException("Slide Rule must allow for click events");
        }
		((SourcesMouseEvents)slideRule).addMouseListener(this);
		
		// When the window gets resized, the slider thumbs become out of position, 
		// so need to be moved.  Put a small delay on this to ensure the elements are 
		// in position (not sure this is fully needed, unlike the initialisation delay) 
		// but kept just in case there are issues in larger applications.
		Window.addWindowResizeListener(new WindowResizeListener() {
			public void onWindowResized(int width, int height) {
				// Make slider invisible here - otherwise we get a jumping effect on screen
				theSliderThumbnail.setVisible(false);
				// Set up a DeferredCommand for executing the redimension code.
				DeferredCommand.addCommand(new Command() {
					public void execute() {
						redimensionalise();
					}
				});
			}
			});
		
	
		// Finally, we need to initialise all the on screen dimensions; though we 
		// need to wait a short period of time to ensure that all widgets have been 
		// added otherwise it appears the the code has no idea of the size dimensions.
		// So to do this, we set up a DeferredCommand.
		DeferredCommand.addCommand(new Command() {
			public void execute() {
				initialize();
			}
		});
	}

	
	
	private class SliderThumb extends PopupPanel implements MouseListener {
		private Widget theThumb;
		private int dragStartX;
		private int dragStartY;
		private boolean dragging = false;
		private int absY;
		private int absX;
		
		public SliderThumb(Widget thumb) {
			super();
			if(!(thumb instanceof SourcesMouseEvents)) throw new RuntimeException("Confirm thumbnail must allow for mouse events");
			theThumb = thumb;
			this.add(thumb);
			((SourcesMouseEvents)thumb).addMouseListener(this);
			this.addStyleName("slider-thumbnail");
		}
		
		public int getThumbYPos() {
			return absY;
		}
		
		public int getThumbXPos() {
			return absX;
		}
		
		public void setPosX(int pos) {
			this.setPopupPosition(pos, startY);
		}
		
		public void setPosY(int pos) {
			this.setPopupPosition(startX, pos);
		}
		
		public void setPosXY(int xPosition, int yPosition) {
			this.setPopupPosition(xPosition, yPosition);
		}
		
		public void onMouseDown(Widget sender, int x, int y) {
			dragging = true;
			DOM.setCapture(theThumb.getElement());
			dragStartX = x;
			dragStartY = y;
		}
		
		public void onMouseEnter(Widget arg0) {
			// Not an interesting event for us
		}
		public void onMouseLeave(Widget arg0) {
			// Not an interesting event for us
		}
		
		public void onMouseMove(Widget sender, int x, int y) {
			if(dragging) {
				absX = 0;
				absY = 0;
				absX = x + getAbsoluteLeft() - dragStartX;
				if(horizontalControl) {
					if(absX < startX) {
		    			absX = startX;
		    		}
		    		if(absX > endX){
		    			absX = endX;
		    		}
				} else {
					absX = startX;
				}
				
				if(verticalControl) {
					absY = y + getAbsoluteTop() - dragStartY;
		    		if(absY < startY) {
		    			absY = startY;
		    		}
		    		if(absY > endY) {
		    			absY = endY;
		    		}
				} else {
					absY = startY;
				}
				model().setXValue(calculateXindex());
		    	model().setYValue(calculateYindex());
			}
		}
		
		public void onMouseUp(Widget sender, int x, int y) {
			dragging = false;
			DOM.releaseCapture(theThumb.getElement());
		}
	}
	
	private void initialize() {
		dimensionalise();
		theSliderThumbnail.setPopupPosition(startX, startY);
		if(startXPosition!=0){setXYPosition(startXPosition,startYPosition);}
		if(startYPosition!=0){setXYPosition(startXPosition,startYPosition);}
		totalRealLifeX = model().getRealEndX()-model().getRealStartX();
		totalOnScreenX = endX-startX;
		divisionsX = totalOnScreenX/totalRealLifeX;
		totalRealLifeY = model().getRealEndY()-model().getRealStartY();
		totalOnScreenY = endY-startY;
		divisionsY = totalOnScreenY/totalRealLifeY;
	}
	
	/**
	 * Derives the positioning of the slider on the screen.
	 * *MUST* be called after the screen has been rendered (which seems to 
	 * require a short delay to be introduced before calling this method)
	 */
	private void dimensionalise() {
		startX = getAbsoluteLeft();
		startY = getAbsoluteTop();
		endX = startX + background.getOffsetWidth() - thumbWidth;
		endY = startY + background.getOffsetHeight() - thumbHeight;
	}
	/**
	 * When the screen has been resized the previous stored dimensions are no 
	 * longer valid, so new dimensions are calculated and the slider repositioned. 
	 *
	 */	
	private void redimensionalise() {
		theSliderThumbnail.setVisible(false);
		dimensionalise();
		setXYPosition(model().getCurrentXValue(), model().getCurrentYValue());
		theSliderThumbnail.setVisible(true);
	}
	
	private void setXYPosition(int actualRealLifeXValue, int actualRealLifeYValue) {
		if((actualRealLifeXValue < model().getRealStartX()) || (actualRealLifeXValue > model().getRealEndX())) {
			throw new RuntimeException("Requested start position of slider thumb is not within the bounds of the slider");
		}
		if((actualRealLifeYValue < model().getRealStartY()) || (actualRealLifeYValue > model().getRealEndY())) {
			throw new RuntimeException("Requested start position of slider thumb is not within the bounds of the slider");
		}
		
		int actualScreenXPosition = startX;
		if(horizontalControl) {
			actualScreenXPosition = indexXtoScreenX(actualRealLifeXValue);
		} 
		
		int actualScreenYPosition = startY;
		if(verticalControl) {
			actualScreenYPosition = indexYtoScreenY(actualRealLifeYValue); 
		}
		theSliderThumbnail.setPosXY(actualScreenXPosition, actualScreenYPosition);
	}
	
	
	/**
	 * Would handle moving the slide thumb by clicking on the slider.
	 * The clicking works below (including handling scroll off-sets in the 
	 * browser window) but I cannot fathom out how to fire the onChange event to
	 * indicate to listeners the the slider has changed.
	 */
	public void onMouseDown(Widget sender, int x, int y) {
		if(canClickBackground) {
			int scrollOffsetX = getScrollXOffset();
			int scrollOffsetY = getScrollYOffset();
			int xr = 0, yr = 0;
			xr = startX + x - thumbWidth / 2 + scrollOffsetX - (this.getAbsoluteLeft());
			yr = startY + y - thumbHeight / 2 + scrollOffsetY - (this.getAbsoluteTop());
			int newX = calculateXindex(xr);
			int newY = calculateYindex(yr);
			model().setXYValue(newX, newY);
		}
	}
	
	public void onMouseEnter(Widget sender) {
		// Not an interesting event to us
	}
	
	public void onMouseLeave(Widget sender) {
		// Not an interesting event to us
	}
	
	public void onMouseMove(Widget sender, int x, int y) {
		// Not an interesting event to us
	}
	
	public void onMouseUp(Widget sender, int x, int y) {
		// Not an interesting event to us
	}
	
	private int indexXtoScreenX(int indexX) {
		float totalRealLifeX = model().getRealEndX() - model().getRealStartX();
		float totalOnScreenX = endX - startX;
		float divisionsX = totalOnScreenX / totalRealLifeX;
		return new Float(((indexX * divisionsX) + startX)).intValue();
	}

	private int indexYtoScreenY(int indexY) {
		float totalRealLifeY = model().getRealEndY() - model().getRealStartY();
		float totalOnScreenY = endY - startY;
		float divisionsY = totalOnScreenY / totalRealLifeY;
		return new Float(((indexY * divisionsY) + startY)).intValue();
	}

	private int calculateXindex(int screenX) {
		float actualScreenPositionX = new Float(screenX).floatValue();
		int actualRealLifeValueX = new Float(actualScreenPositionX / divisionsX).intValue();
		return actualRealLifeValueX;
	}

	private int calculateXindex() {
		return calculateXindex(theSliderThumbnail.getThumbXPos() - startX);
	}
	
	private int calculateYindex(int screenY) {
		float actualScreenPositionY = new Float(screenY).floatValue();
		int actualRealLifeValue = new Float(actualScreenPositionY / divisionsY).intValue();
		return actualRealLifeValue;
	}

	private int calculateYindex() {
		return calculateYindex(theSliderThumbnail.getThumbYPos() - startY);
	}

	public SliderModel model() {
		return theModel;
	}
	
	public void setModel(SliderModel newModel) {
		theModel = newModel;
	}
	
	/**
	 * Implement the Observer interface.
	 * When the model is updated the standard functionality for updating is to
	 * set the known XY Position and then fire off onChange events to any 
	 * objects that are listening to this widget events instead of the underlying
	 * model (or any alternative model provided by the programmer).
	 */
	public void update(Observable observable, Object arg) {
		setXYPosition(theModel.getCurrentXValue(), theModel.getCurrentYValue());
		//Fire change events to anyone who might be registered with us.
		if (changeListeners != null) changeListeners.fireChange(this);	
	}

	public void addChangeListener(ChangeListener listener) {
		if(changeListeners == null) changeListeners = new ChangeListenerCollection();
		changeListeners.add(listener);
	}
	
	public void removeChangeListener(ChangeListener listener) {
		if(changeListeners != null) changeListeners.remove(listener);
	}
	
	private int getScrollYOffset() {
		return Window.getScrollTop();
	}
	
	private int getScrollXOffset() {
		return Window.getScrollLeft();
	}
}
