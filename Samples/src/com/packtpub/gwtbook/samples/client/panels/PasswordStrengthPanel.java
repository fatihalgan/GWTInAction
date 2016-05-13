package com.packtpub.gwtbook.samples.client.panels;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.packtpub.gwtbook.samples.client.PasswordStrengthService;
import com.packtpub.gwtbook.samples.client.PasswordStrengthServiceAsync;

public class PasswordStrengthPanel extends SamplePanel {

	public TextBox  passwordText = new TextBox();
	final PasswordStrengthServiceAsync pwStrengthService = (PasswordStrengthServiceAsync)
		GWT.create(PasswordStrengthService.class);
	public ArrayList strength = new ArrayList();
	
	public PasswordStrengthPanel() {
		HorizontalPanel strengthPanel = new HorizontalPanel();
		strengthPanel.setStyleName("pwStrength-Panel");
		for (int i = 0; i < 9; i++) {
			CheckBox singleBox = new CheckBox();
			strengthPanel.add(singleBox);
			strength.add(singleBox);
		}
		VerticalPanel workPanel = new VerticalPanel();
		passwordText.setStyleName("pwStrength-Textbox");
		passwordText.addKeyboardListener(new KeyboardListener() {
			public void onKeyDown(Widget sender, char keyCode, int modifiers) { }
			public void onKeyPress(Widget sender, char keyCode, int modifiers) { }
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				if (passwordText.getText().length() > 0) {
					AsyncCallback callback = new AsyncCallback() {
						public void onSuccess(Object result) {
							clearStrengthPanel();
							int checkedStrength = ((Integer) result).intValue();
							for (int i = 0; i < checkedStrength; i++) {
								((CheckBox) strength.get(i)).setStyleName
									(getPasswordStrengthStyle(checkedStrength));
							}
						}
						public void onFailure(Throwable caught) {
							Window.alert("Error calling the password strength service." + caught.getMessage());
						}
					};
					pwStrengthService.checkStrength(passwordText.getText(), callback);
				} else {
					clearStrengthPanel();
				}
			}
		});
		HorizontalPanel infoPanel = new HorizontalPanel();
		infoPanel.add(new HTML("<div class='infoProse'>Start typing a password " +
		"string. The strength of the password will be " +
		"checked and displayed below. Red indicates that the " +
		"password is Weak, Orange indicates a Medium " +
		"strength password and Green indicates a Strong " +
		"password. The algorithm for checking the strength " +
		"is very basic and checks the length of the password " +
		"string.</div>"));
		workPanel.add(passwordText);
		workPanel.add(infoPanel);
		workPanel.add(strengthPanel);
		DockPanel workPane = new DockPanel();
		workPane.add(infoPanel, DockPanel.NORTH);
		workPane.add(workPanel, DockPanel.CENTER);
		workPane.setCellHeight(workPanel, "100%");
		workPane.setCellWidth(workPanel, "100%");
		initWidget(workPane);
	}
	
	private void clearStrengthPanel() {
		for(Iterator iter = strength.iterator(); iter.hasNext();) {
			((CheckBox) iter.next()).setStyleName(getPasswordStrengthStyle(0));
		}
	}
	
	private String getPasswordStrengthStyle(int passwordStrength) {
		if (passwordStrength == 3) {
			return "pwStrength-Weak";
		} else if (passwordStrength == 6) {
			return "pwStrength-Medium";
		} else if (passwordStrength == 9) {
			return "pwStrength-Strong";
		} else {
			return "";
		}
	}
}
