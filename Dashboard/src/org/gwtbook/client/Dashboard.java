package org.gwtbook.client;

import java.util.Date;
import java.util.HashMap;

import org.gwtbook.client.ui.DashboardComposite;
import org.gwtbook.client.ui.DashboardPanel;
import org.gwtbook.client.ui.EditableLabel;
import org.gwtbook.client.ui.InfoDialogBox;
import org.gwtbook.client.ui.TrashIcon;
import org.gwtbook.client.ui.TwoComponentMenuItem;
import org.gwtbook.client.ui.about.About;
import org.gwtbook.client.ui.clock.Clock;
import org.gwtbook.client.ui.flextableexample.FlexTableExample;
import org.gwtbook.client.ui.googleSearch.GoogleSearch;
import org.gwtbook.client.ui.googlevideosearch.GoogleVideoSearch;
import org.gwtbook.client.ui.login.Login;
import org.gwtwidgets.client.ui.LightBox;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowCloseListener;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Dashboard implements EntryPoint {

	/**
     * Unfortunately WindowResize events (along with a very small number of others) get 
     * fired twice by GWT, so we use this simple variable to only execute our onResize 
     * functionality one of the two times.
     */
	boolean informResize = true;
	
	/**
	 * The WindowCloseListener that is associated with the application.  It is not added 
	 * as an anonymous class (which the resize listener is) since we need to remove this 
	 * listener if, and when, the user uses the menu system to change the locale.
	 */
	protected WindowCloseListener dashboardWindowCloseListener;

	/**
     * We use the EditableLabel composite widget to display the dashboard name, allowing the
     * user to change it if they wish to.
     */
	static EditableLabel dashboardName;
	
	/**
	 * Variables for the Dashboard menu system.
	 */
	protected MenuBar menu = new MenuBar();
	protected MenuBar menuCreate = new MenuBar(true);
	protected MenuBar menuHelp = new MenuBar(true);
	protected MenuBar menuLocale = new MenuBar(true);
	
	/**
     * Deferred Binding for the trash icon.
     */
	protected TrashIcon trash = (TrashIcon)GWT.create(TrashIcon.class);
	
	/**
     * Deferred Binding for the set of constants to use.
     */
	protected DashboardConstants constants = (DashboardConstants)GWT.create(DashboardConstants.class);
	
	/**
     * Deferred Binding for the set of messages to use.
     */
	protected DashboardMessages messages = (DashboardMessages)GWT.create(DashboardMessages.class);
	
	/**
	 * Holds the various dahboard panels that we will be creating.
	 */
	HashMap panels = new HashMap();
	
	/**
     * Holds a reference to the book icon placed in the bottom right corner of 
     * the Dashboard screen.
     */
	Image advert;
	
	private String splashShownCookie;
	
	/**
	 * JSNI method to set the value of the confirmDelete JavaScript variable
	 * (used to pass status between Dashboard Menu System and the Dashboard Component Applications)
	 * @param confirmDelete
	 */
	protected native void setConfirmDelete(boolean confirmDelete) /*-{
		$wnd.confirmDelete = confirmDelete;
	}-*/;
	
	/**
	 * JSNI method to get the value of the confirmDelete JavaScript variable
	 * (used to pass status between Dashboard Menu System and the Dashboard Component Applications)
	 * @return
	 */
	protected native boolean getConfirmDelete() /*-{
		return $wnd.confirmDelete;
	}-*/;
	
	/**
     * Method used to build the Dashboard's Create menu bar.  
     * @return
     */
	protected MenuBar buildCreateMenu() {
		// Add four simple applications for the internet version of the Dashboard
		menuCreate.addItem(constants.ClockMenuItemName(), new CreateClockCommand());
		menuCreate.addItem(constants.GoogleSearchMenuItemName(), new CreateGoogleSearchCommand());
		menuCreate.addItem(constants.GoogleVideoSearchMenuItemName(), new CreateGoogleVideoSearchCommand());
		menuCreate.addItem(constants.FlexTableMenuItemName(), new CreateFlexTableCommand());
		menuCreate.addStyleName("submenu");
		return menuCreate;
	}
	
	 /**
     * Method used to build the Dashboard's Help menu bar.
     * Flags used for locale are from http://www.fg-a.com/gifs.html
     * 
     * In the book (Listing 4-1) uses a standard MenuItem - here we use the 
     * TwoComponentMenuItem created later in Chapter 4.
     * 
     * @return
     */
	protected MenuBar buildHelpMenu() {
		// Build the locale changing sub-menu bar using TwoComponentMenuItems
		TwoComponentMenuItem localEN = new TwoComponentMenuItem(constants.EnglishLocale(), new Image("gb.png"), new ChangeLocaleToEnglishCommand());
		TwoComponentMenuItem localTR = new TwoComponentMenuItem(constants.TurkishLocale(), new Image("se.png"), new ChangeLocaleToTurkishCommand());
		TwoComponentMenuItem localUSA = new TwoComponentMenuItem(constants.AmericanLocale(),new Image("us.png"), new ChangeLocaleToAmericanCommand());
		// Add the menu items to the menu bar.
		menuLocale.addItem(localEN);
		menuLocale.addItem(localTR);
		menuLocale.addItem(localUSA);
		// Build the Help menu bar, which for the internet version only has an About
        // option and the locale sub-menu bar.
		menuHelp.addItem(constants.AboutMenuItemName(), new AboutCommand());
		menuHelp.addItem(constants.LocaleMenuItemName(), menuLocale);
		menuHelp.addItem("Demonstrates...", new DemonstratesCommand());
		// Add the style names
		menuLocale.setStyleName("localemenu");
		menuHelp.addStyleName("submenu");
		return menuHelp;
	}
	
	/**
     * Builds the Bookmark menu bar from a XML document.
     * @param menu The menu bar into which we will place the bookmark menu.
     * @param menuTitle A title for the bookmark menu.
     * @param bookmarksUrl A reference to the URL where the bookmark XML file can be found.
     */
	private void loadSubMenu(MenuBar menu, String menuTitle, String bookmarksUrl) {
		// Create a new RequestBuilder object pointing at our bookmark URL 
    	// (the XML file that contains the bookmarks we wish to display) 
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, bookmarksUrl);
		try {
			// Set the result String we expect back to be the empty String
			String requestData = "";
			// Create a callback object to handle the result - in this case 
  		  	// a call to create a new MenuLoaderHandler object
			RequestCallback callback = new MenuLoaderHandler(menu, menuTitle);
			// Execute the request.
			builder.sendRequest(requestData, callback);
		} catch(RequestException e) {
			Window.alert(e.getMessage());
		}
	}
	
	/**
     * Private class that implements the RequestCallback interface and is used
     * to handle the result of a RequestBuilder request.
     *
     */
	private class MenuLoaderHandler implements RequestCallback {
		// New menuBar into which we will build the Bookmark menu 
		private MenuBar parentMenu;
		//Title of the menu bar above
		private String menuTitle;
		
		/**
    	 * Constructor that stores some key variables 
    	 * @param menu
    	 * @param menuTitle
    	 */
		public MenuLoaderHandler(MenuBar menu, String menuTitle) {
			this.parentMenu = menu;
			this.menuTitle = menuTitle;
		}
		
		/**
    	 * Implementation of the RequestBuilder's onError() method.  In this 
    	 * example code, we simply raise an alert window displaying the 
    	 * appropriate message from the GWT i18n messages object.
    	 */
		public void onError(Request request, Throwable onException) {
			Window.alert(messages.BookmarkOnErrorMessage());
		}
		
		/**
    	 * Implementation of the RequestBuilder's onResponseRecieved() method.
    	 */
		public void onResponseReceived(Request request, Response response) {
			// First check if the response's status code is 200 (which is what we are expecting)
			if(response.getStatusCode() != 200) {
				//If not, display an appropriate error message, including the status code...
				Window.alert(messages.BookmarkOnResponseErrorMessage(response.getStatusCode()));
				return;
			}
			// Create a new menuBar which will be used to store the menuItems for the Bookmarks 
			MenuBar subMenu = new MenuBar(true);
			//Set its style name
			subMenu.addStyleName("submenu");
			// Create a new XML Document from the text in the response 
			Document doc = XMLParser.parse(response.getText());
			// From the XML Document, extract all the elements that have tag name of <i>bookmark</i>
			NodeList elements = doc.getElementsByTagName("bookmark");
			// For each of these elements we will extract the title, URL and create a new menuItem
			for(int i = 0; i < elements.getLength(); i++) {
				Node element = elements.item(i);
				NamedNodeMap attrs = element.getAttributes();
				String title = attrs.getNamedItem("title").getNodeValue();
				String url = attrs.getNamedItem("url").getNodeValue();
				MenuItem bookmark = new MenuItem(title, new LinkCommand(url));
				// Add the new menuItem to the sub menu
				subMenu.addItem(bookmark);
			}
			// Now add the new submenu to the parent menu so it is all hooked together.
			parentMenu.addItem(menuTitle, subMenu);
		}
	}
	
	/**
	 * Simple implementation of the Command class in order to provide a menuItem that
	 * when clicked upon goes to a specific URL
	 */
	private class LinkCommand implements Command {
		//URL to go to when executed
		private String url = null;
		
		public LinkCommand(String url) {
			this.url = url;
		}
		
		public void execute() {
			if(url != null) {
				Window.open(url, "_self", "");
			} else {
				Window.alert("URL for bookmark has not been set");
			}
		}
	}
	
	/**
     * Method to set up the window event handling for the Dashboard - 
     * the closing and resizing events are listened to and various actions
     * taken.
     */
	private void setUpWindowEventHandling() {
		// Create a new WindowClose Listener into the variable set up earlier.
		dashboardWindowCloseListener = new WindowCloseListener() {
			/**
    		 * As the window is finally closed then we would typically try and 
    		 * save various elements of state to the server through this method.
    		 * In our example, we just pop up an alert box telling us that we could
    		 * have saved the state here.
    		 */
			public void onWindowClosed() {
				Window.alert(messages.WindowClosedText());
				
			}
			
			/**
			 * This method is called when the user tries to close the window (or 
			 * navigates to another page).  It gives us the opportunity to confirm 
			 * if the user meant to navigate away or not - if we return false from 
			 * this method the the navigation/closing is stopped. 
			 */
			public String onWindowClosing() {
				return messages.WindowClosingText();
			}
		};
		// Now we add the Close Listener to the browser.
		Window.addWindowCloseListener(dashboardWindowCloseListener);
		// Add a Resize Listener, as an anonymous class, to the browser window.
		Window.addWindowResizeListener(new WindowResizeListener() {
			/**
        	 * This method is called when the browser window is resized.
        	 * Unfortunately it gets called twice by GWT for each resize.
        	 */
			public void onWindowResized(int x, int y) {
				// To avoid the double calling we have a simple boolean which we check to 
        		// see if we should inform the user about resizing.
				if(informResize) {
					if(Window.confirm(messages.WindowResizedMessage(x, y))) {
						Window.removeWindowResizeListener(this);
					}
				}
				// Once we have checked the informResize variable then we invert it.
        		// This means that when the second invocation of this method is unfortunately called
        		// by GWT then the functionality is only called once.
				informResize = !informResize;
				addBookAdvert();
			}
		});
	}
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		GWT.log("Starting GWT In Action Dashboard", null);
		
		String fullDateFormat = DateTimeFormat.getFullDateFormat().format(new Date());
		String medTimeFormat = DateTimeFormat.getShortTimeFormat().format(new Date());
		// Create a new EditableLabel using an i18n message and two button whose labels 
    	// come from the i18n constants.
		dashboardName = new EditableLabel(messages.DashboardDefaultNameMessage(medTimeFormat + " " + fullDateFormat),
				constants.NameChangeOK(), constants.NameChangeCANCEL());
		dashboardName.setWordWrap(true);
		// Add a change listener to the editable label so that when the text is changed
        // we could do something.  Typically you would save the new value to the server
        // side code at this point, but in our case we just tell the user we could do that
        // at this point.
		dashboardName.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				Window.alert(messages.SaveDashboardNameMessage());
			}
		});
		dashboardName.setStyleName("dashboard-Name");
		//Create the Dashboard application's menu system
		MenuBar menuCreate = buildCreateMenu();
		MenuBar menuHelp = buildHelpMenu();
		loadSubMenu(menu, "Bookmarks", GWT.getModuleBaseURL()+"bookmarks.xml");
		menu.addItem(constants.HelpMenuName(), menuHelp);
		menu.addItem(constants.CreateMenuName(), menuCreate);
		menu.setAutoOpen(true);
		// Set the icon for the trash
		trash.setIcon();
		trash.addStyleName("trash");
		// Call our method that sets up the window event handling.
		setUpWindowEventHandling();
		GWT.log("Window Event Handling Initialized",null);
		// Add the various components to the browser page
		RootPanel.get().add(menu);
		RootPanel.get().add(trash);
		RootPanel.get().add(dashboardName);
		addBookAdvert();
		GWT.log("Dashboard Components Added to Browser",null);
		//Start API listening
		setUpAPI();
		GWT.log("Dashboard API Initialised",null);
        GWT.log("Dashboard Started",null);
        //Disable the Windows Scroll bars 
		Window.enableScrolling(false);
		//Show splash screen
		showSplashScreen();
	}
	
	 /**
     * Simple method to show the splash screen when the application is loaded
     * In this example, no attempt is made to see if it has already been displayed 
     * to the user; which in a real application could get rather annoying, but is 
     * OK in this examples case. 
     */
	private void showSplashScreen() {
		InfoDialogBox splash = new InfoDialogBox();
		DashboardSplashScreen splashFrame = new DashboardSplashScreen();
		splash.setWidget(splashFrame);
		splash.setTitle("Information on the GWT In Action Dashboard");
		splash.setText("Information on the GWT In Action Dashboard");
		splashFrame.setStyleName("splashWindow");
		//LightBox is in the GWT Widget Library
		LightBox startSplash = new LightBox(splash);
		startSplash.show();
	}
	
	/**
     * Sunple method that places an icon showing the GWT In Action book in the 
     * bottom right corner of the Dashboard.
     */
	private void addBookAdvert() {
		// First, if the object exists, remove it from the Dashboard
		if(advert != null) RootPanel.get().remove(advert);
		// Now, create the image, set the style and add a ClickListener to open
    	// the books homepage when clicked on.
		advert = new Image("book_cover.PNG");
		advert.setStyleName("book-advert");
		advert.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				Window.open("http://www.manning.com/hanson", "BuyTheBook", "");
			}
		});
		// Now position te widget using absolute positioning on the AbsolutePanel RootPanel
    	// First we get the x position by querying for the width of the client window and then subtracting 70 pixels
		int xpos = Window.getClientWidth()-70;
		// Similarly, we get the y position via this line of code:
		int ypos = Window.getClientHeight()-90;
		// Then, we add the widget to the screen in the positions just calculated.
		RootPanel.get().add(advert, xpos, ypos);
	}
	
	/**
	 * Extends the standard GWT Command class to create the Clock component application.  
	 */
	class CreateClockCommand implements Command {
		public void execute() {
			DashboardPanel thePanel;
			String panelName = "clock";
			if(!panels.containsKey(panelName)) {
				DashboardComposite thing = (DashboardComposite)GWT.create(Clock.class);
				thing.addParentMenu(menu);
				thePanel = new DashboardPanel(thing, true, trash);
				panels.put(panelName, thePanel);
			} else {
				thePanel = (DashboardPanel)panels.get(panelName);
				thePanel.show();
			}
		}
	}
	
	/**
	 * Extends the standard GWT Command class to create the About component application.  
	 */
	class AboutCommand implements Command {
		
		public void execute() {
			// Identify a new panel.
			DashboardPanel thePanel;
			// Give the panel an arbitrary name (used for storing in a HashMap)
			String panelName = "about";
			// Check if we have already created the application before....
			if(!panels.containsKey(panelName)) {
				// If not, then create a new instance
				DashboardComposite thing = (DashboardComposite)GWT.create(About.class);
				// Add the Dashboard menu as the new composites parent menu 
    			// (so it can add an about menu if it has one)
				thing.addParentMenu(menu);
				// Create the new panel
				thePanel = new DashboardPanel(thing, true, trash);
				// Add the panel to the HashMap
				panels.put(panelName, thePanel);
			} else {
				// We created it before, so lets just retrieve it from the HashMap
				thePanel = (DashboardPanel)panels.get(panelName);
				//and then show it
				thePanel.show();
			}
		}
	}
	
	/**
	 * Extends the standard GWT Command class to create the Login component application.  
	 */
	class LoginCommand implements Command {
		public void execute() {
			DashboardPanel thePanel;
			String panelName = "login";
			if(!panels.containsKey(panelName)) {
				DashboardComposite thing = (DashboardComposite)GWT.create(Login.class);
				thing.addParentMenu(menu);
				thePanel = new DashboardPanel(thing, true, trash);
				panels.put(panelName, thePanel);
			} else {
				thePanel = (DashboardPanel)panels.get(panelName);
				thePanel.show();
			}
		}
	}
	
	/**
	 * Extends the standard GWT Command class to create the Google Search component application.  
	 */
	class CreateGoogleSearchCommand implements Command {

		public void execute() {
			DashboardPanel thePanel;
			String panelName = "gs";
			if(!panels.containsKey(panelName)) {
				DashboardComposite thing = (DashboardComposite)GWT.create(GoogleSearch.class);
				thing.addParentMenu(menu);
				thePanel = new DashboardPanel(thing, true, trash);
				panels.put(panelName, thePanel);
			} else {
				thePanel = (DashboardPanel)panels.get(panelName);
				thePanel.show();
			}
		}
	}
	
	/**
	 * Extends the standard GWT Command class to create the Google Video Search component application.  
	 */
	class CreateGoogleVideoSearchCommand implements Command {
		public void execute() {
			DashboardPanel thePanel;
			String panelName = "gsv";
			if(!panels.containsKey(panelName)) {
				DashboardComposite thing = (DashboardComposite)GWT.create(GoogleVideoSearch.class);
				thing.addParentMenu(menu);
				thePanel = new DashboardPanel(thing, true, trash);
				panels.put(panelName, thePanel);
			} else {
				thePanel = (DashboardPanel)panels.get(panelName);
				thePanel.show();
			}
		}
	}
	
	class CreateFlexTableCommand implements Command {
		public void execute() {
			DashboardPanel thePanel;
			String panelName = "flext";
			if(!panels.containsKey(panelName)) {
				DashboardComposite thing = (DashboardComposite)GWT.create(FlexTableExample.class);
				thing.addParentMenu(menu);
				thePanel = new DashboardPanel(thing, true, trash);
				panels.put(panelName, thePanel);
			} else {
				thePanel = (DashboardPanel)panels.get(panelName);
				thePanel.show();
			}
		}
	}
	
	/**
	 * Extends the standard GWT Command class to display the Demonstrates.... dialog for the Dashboard  
	 */
	class DemonstratesCommand implements Command {
		public void execute() {
			InfoDialogBox splash = new InfoDialogBox();
			HTML splashHTML = new HTML("The Dashboard example demonstrates many interesting aspects of GWT, far too many to list one-by-one, but some highlights are:" +
           			"<ul><li>creating Menus from code as well as parsing XML documents (to create the Bookmark menu)</li>" +
           			"<li>demonstrates simple Drag and Drop where component applications are created and can be moved around the screen, and deleted by dropping on a trash icon</li>" +
           			"<li>using/creating Composite widgets:" +
           			"   <ul><li>at the top left of the screen is an Editable Label which you can change the value of" +
           			"   <li>And, several sliders are built, culminating in the background colour picker widget (accessed through the Help menu's Colour Picker option)</li></ul></li>" +
           			"<li>inter component communication using JavaScript (JSNI) to indicated to components if they need to request confirmation of being deleted</li>" +
           			"<li>behind the scenes GWT Generators have been automatically creating some of the code (the \"introspection\" in the About menu, for example.</li>" +
           			"<li>standard menu items have also been extended to display either an image, or a toggle image</li>" +
           			"<li>standard browser functionality of dragging an image to the address bar is also disabled in certain circumstances (try moving the trash icon image)</li></ul>" +
           			"<p>The book, GWT in Action, guides you through developing and creating this application");
			splash.setWidget(splashHTML);
			splash.setTitle("Information on the GWT In Action Dashboard");
			splash.setText("Information on the GWT In Action Dashboard");
			splashHTML.setStyleName("componentInfo-Label");
			//LightBox is in the GWT Widget Library
			LightBox startSplash = new LightBox(splash);
			startSplash.show();
		}	
	}
	
	/**
	 * Extends the standard GWT Command class to change the locale to English.
	 */
	class ChangeLocaleToEnglishCommand implements Command {
		public void execute() {
			Window.removeWindowCloseListener(dashboardWindowCloseListener);
			changeLocale("");
		}
	}
	
	/**
	 * Extends the standard GWT Command class to change the locale to Swedish.  
	 */
	class ChangeLocaleToTurkishCommand implements Command {
		public void execute() {
			Window.removeWindowCloseListener(dashboardWindowCloseListener);
			changeLocale("tr");
		}
	}
	
	/**
	 * Extends the standard GWT Command class to change the locale to American English.  
	 */
    class ChangeLocaleToAmericanCommand implements Command{
    	public void execute(){
    		Window.removeWindowCloseListener(dashboardWindowCloseListener);
            changeLocale("en_US");
    	}
    }
    
    /**
     * JSNI method to change the locale of the application - it effectively
     * parses the existing URL and creates a new one for the chosen locale.
     * 
     * It additionally launches any JavaScript debugger that might be 
     * attached to the system (Windows only).  To disable this functionality
     * just remove the "debugger" line.
     * 
     * @param newLocale
     */
    private native void changeLocale(String newLocale)/*-{
    	// Uncomment the "debugger;" line to see how to set debug statements in JSNI code
        // When in web mode, if your browser has a JavaScript debugger attached, it will 
        // launch at this point in the code (when the user changes locale through the menu system). 
        //debugger;
		var currLocation = $wnd.location.toString();
		var noHistoryCurrLocArray = currLocation.split("#");
		var noHistoryCurrLoc = noHistoryCurrLocArray[0];
		var locArray = noHistoryCurrLoc.split("?");
		$wnd.location.href = locArray[0]+"?locale="+newLocale;
    }-*/;
    
    /**
     * First method used in the Dashboard's API - this one is used 
     * to set the Dashboard name.  To see the Dashboard API in use, use the
     * Dashboard_APITest.html file instead of Dashboard.html
     * @param s The String on which to set the Dashboard's name
     */
    public static void setDashboardName(String s) {
    	dashboardName.setText(s);
    }
    
    /**
     * Second method used in the Dashboard's API - this one is used 
     * to get the Dashboard name.
     * @return The name of the Dashboard, as currently set.
     */
    public static String getDashboardName(){
    	return dashboardName.getText();
    }
    
    /**
	 * JSNI method to establish an API that other JavaScript code / GWT applications
	 * can call to interact with the Dashboard application.
	 *
	 */
    public native void setUpAPI()/*-{
		$wnd.__setDashboardName = function(s){
			@org.gwtbook.client.Dashboard::setDashboardName(Ljava/lang/String;)(s);
		}
		$wnd.__getDashboardName = function(){
			return @org.gwtbook.client.Dashboard::getDashboardName()();
		}
	}-*/;
}
