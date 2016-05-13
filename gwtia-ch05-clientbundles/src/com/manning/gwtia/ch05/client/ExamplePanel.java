package com.manning.gwtia.ch05.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch05.client.cssresource.CssExample;
import com.manning.gwtia.ch05.client.dataresource.DataExample;
import com.manning.gwtia.ch05.client.imageresource.ImageExample;
import com.manning.gwtia.ch05.client.intro.IntroPanel;
import com.manning.gwtia.ch05.client.textresource.TextExample;

public class ExamplePanel extends Composite {

	interface ExamplePanelUiBinder extends UiBinder<Widget, ExamplePanel> {}
	private static ExamplePanelUiBinder uiBinder = GWT.create(ExamplePanelUiBinder.class);
	
	/**
     * ClientBundle code for the GWT in Action logo shown on the main page.
     */
	interface Resources extends ClientBundle {
		@Source("gwtia.jpg")
		public ImageResource logo();
	}
	
	/**
     * The introPanel that displays introduction information.
     */
	private IntroPanel introPanel;
	
	private DataExample dataExample;
	
	private TextExample textExample;
	
	private ImageExample imageExample;

	private CssExample cssExample;

	/**
     * The Panel in the main screen where the examples will be displayed.
     */
	@UiField
	Panel exampleArea;
	
	private ResizeHandler resizeHandler = new ResizeHandler() {
		@Override
		public void onResize(ResizeEvent event) {
			setWidgetToMaxWidthAndHeight();			
		}
	};
	
	/**
     * The EntryPoint.
     * Binds the UiBinder code and creates the widget to be shown.
     * Adds a ResizeHandler to manage and resize events of the browser that are created
     * Sets up the IntroPanel as the first widget shown to the user.
     */
	public ExamplePanel() {
		initWidget(uiBinder.createAndBindUi(this));
		setWidgetToMaxWidthAndHeight();
		Window.addResizeHandler(resizeHandler);
		introPanel = new IntroPanel();
		setWidgetAsExample(introPanel);
	}
	
	private void setWidgetToMaxWidthAndHeight() {
		setWidth("100%");
		setHeight(Window.getClientHeight() + "px");
	}
	
	/**
     * Simple method to set a widget in the example area and clear out any previous widget.
     * @param widget The new widget to show as the example.
     */
	private void setWidgetAsExample(Widget w) {
		exampleArea.clear();
		exampleArea.add(w);
	}
	
	@UiHandler("introPanel")
	void showInstructionsPanel(ClickEvent event) {
		History.newItem(HistoryTokens.INTRODUCTION);
	}
	
	/**
     * Helper method for showing introduction panel.
     * Called when the intro button is clicked in this widget, or if the user initiates it via
     * lack of a history token in the URL (clicks back/forward, or arrives at http://.......Example.html
     */
	public void showInstructionsPanel() {
		setWidgetAsExample(introPanel);
	}
	
	@UiHandler("data")
	public void showData(ClickEvent event) {
		History.newItem(HistoryTokens.DATA);
	}
	
	/**
     * Helper method for showing history panel.
     * Called when the history button is clicked in this widget, or if the user initiates it via
     * a history token in the URL (clicks back/forward, or arrives at http://.......Example.html#history
     */
	public void showData() {
		if(dataExample == null) dataExample = new DataExample();
		setWidgetAsExample(dataExample);
	}
	
	    
    @UiHandler("text")
    public void showText (ClickEvent event) {
    	History.newItem(HistoryTokens.TEXT);
    }
    
    public void showText() {
    	if(textExample == null) textExample = new TextExample();
    	setWidgetAsExample(textExample);
    }
    
    @UiHandler("image")
    public void showImage (ClickEvent event) {
    	History.newItem(HistoryTokens.IMAGE);
    }
    
    public void showImage() {
    	if (imageExample == null) imageExample = new ImageExample();
        setWidgetAsExample(imageExample);
    }
    
    @UiHandler("css")
    public void showCss (ClickEvent event) {
    	History.newItem(HistoryTokens.CSS);
    }

    public void showCss() {
    	if(cssExample == null) cssExample = new CssExample();
        setWidgetAsExample(cssExample);
    }
}
