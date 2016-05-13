package org.gwtbook.client.ui.login;

import java.util.Date;

import org.gwtbook.client.ui.DashboardComposite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Login extends DashboardComposite {
	final int LOGIN_PANEL = 0;
	final int LOGGED_IN_PANEL = 1;
	final int ERROR_PANEL = 2;

	DeckPanel application;
	VerticalPanel loginPanel;
	PasswordTextBox password;
	TextBox username;
	Button loginButton;
	Button logoutButton;
	Image appImage;
	
	Frame loggedInMessage;
	VerticalPanel loggedInPanel;
	private HTMLPanel errorPanelMsg;
	private VerticalPanel errorPanel;
	private Button errorButton;
	
	final String loggedInCookie = "GWT-book-loggedIn";
	
	public Login() {
		super();
		create();
		if(getLoggedIn()) {
			application.showWidget(this.LOGGED_IN_PANEL);
		}
	}
	
	private void create() {
		application = new DeckPanel();
		application.add(createLoginPanel());
		application.add(createLoggedInPanel());
		application.add(createErrorPanel());
		application.setStyleName("login-visiblePanel");
		application.showWidget(LOGIN_PANEL);
		initWidget(application);
	}
	
	private Widget createLoginPanel() {
		final FormPanel form = new FormPanel();
		loginPanel = new VerticalPanel();
		loginPanel.addStyleName("login-loginPanel");
		password = new PasswordTextBox();
		password.addStyleName("login-password");
		password.setName("Passwd");
		username = new TextBox();
		username.addStyleName("login-userName");
		username.setName("Email");
		loginButton = new Button("Login");
		loginButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				form.submit();				
			}
		});
		
		appImage = new Image("myApp.PNG");
		Hidden accountType = new Hidden();
		accountType.setDefaultValue("TEST");
		accountType.setName("accountType");
		
		Hidden service = new Hidden();
		service.setDefaultValue("GWTBook");
		service.setName("service");
		
		Hidden source = new Hidden();
		source.setDefaultValue("GWTBook-GWTBook-1.00");
		source.setName("source");
		
		loginPanel.add(appImage);
		loginPanel.add(username);
		loginPanel.add(password);
		loginPanel.add(loginButton);
		loginPanel.add(accountType);
		loginPanel.add(service);
		loginPanel.add(source);
		
		form.setAction(GWT.getModuleBaseURL()+"loginService");
	    form.setEncoding(FormPanel.ENCODING_URLENCODED);
	    form.setMethod(FormPanel.METHOD_POST);
	    form.setWidget(loginPanel);
	    
	    form.addFormHandler(new FormHandler() {
			public void onSubmit(FormSubmitEvent event) {
								
			}

			public void onSubmitComplete(FormSubmitCompleteEvent event) {
				checkLogin(event.getResults());				
			}
	    });
	    return form;
	}
	
	private Widget createErrorPanel() {
		String HTML = "<b>Unable to LogIn</b><br/>Test";
		errorPanelMsg = new HTMLPanel(HTML);
		errorPanel = new VerticalPanel();
		errorPanel.addStyleName("login-errorPanel");
		errorButton = new Button("Back");
		errorButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				changeState(LOGIN_PANEL);				
			}
		});
		errorPanel.add(errorPanelMsg);
		errorPanel.add(errorButton);
		return errorPanel;
	}
	
	private Widget createLoggedInPanel() {
		logoutButton = new Button("Logout");
		logoutButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				changeState(LOGIN_PANEL);				
			}
		});
		loggedInMessage = new Frame("loggedIn.html");
		loggedInMessage.setStyleName("login-loggedInFrame");
		loggedInPanel = new VerticalPanel();
		loggedInPanel.addStyleName("login-loggedInPanel");
		loggedInPanel.add(loggedInMessage);
		loggedInPanel.add(logoutButton);
		return loggedInPanel;
	}
	
	public void setLoggedIn(boolean loggedIn) {
		Date currDate = new Date();
		Date expireDate = new Date(currDate.getTime() + 1 * 60 * 1000);
		Cookies.setCookie(loggedInCookie, "" + loggedIn + expireDate);
	}
	
	public boolean getLoggedIn(){
		return new Boolean(Cookies.getCookie(loggedInCookie)).booleanValue();
	}
	
	private void changeState(int newState) {
		switch (newState) {
		case LOGIN_PANEL:
			application.showWidget(LOGIN_PANEL);
			setLoggedIn(false);
			break;
		case LOGGED_IN_PANEL:
			application.showWidget(LOGGED_IN_PANEL);
			setLoggedIn(true);
			break;
		case ERROR_PANEL:
			application.showWidget(ERROR_PANEL);
			break;
		}
	}
	
	private void checkLogin(String isValidLogin) {
		if(new Boolean(isValidLogin).booleanValue()) {
			changeState(LOGGED_IN_PANEL);
		} else {
			changeState(ERROR_PANEL);
		}
	}
	
	
}
