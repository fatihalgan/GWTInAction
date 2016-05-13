package org.gwtbook.client.ui.calculator;

import java.util.HashSet;

import org.gwtbook.client.ui.DashboardComposite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Calculator extends DashboardComposite {
	
	final int NULL_OP = -1;
	final int EQUALS = 0;
	final int CANCEL = 1;
	final int ADD = 10;
	final int SUB = 11;
	final int DIV = 12;
	final int MULT = 13;
	
	final int CHANGE_STYLE_DELAY = 100;

	VerticalPanel theCalculator;
	TextBox theDisplay;
	Grid theKeys;
	FocusPanel theKeyPad;
	HashSet opKeys;
	
	int storedValue = 0;
	boolean newNumber = true;
	int operation = NULL_OP;
	
	public Calculator() {
		create();
	}
	
	private void create() {
		theDisplay = new TextBox();
		theCalculator = new VerticalPanel();
		theKeyPad = new FocusPanel();
		theKeys = new Grid(4,4);
		opKeys = new HashSet();
		
		final Button[][] keys = new Button[4][4];
		keys[0][0] = new Button("7");
		addNumberButtonPressAction(keys[0][0],'7',2);
		keys[0][1] = new Button("8");
		addNumberButtonPressAction(keys[0][1],'8',3);
		keys[0][2] = new Button("9");
		addNumberButtonPressAction(keys[0][2],'9',4);
		keys[0][3] = new Button("/");
		addOperatorButtonPressAction(keys[0][3],'/',12);
		
		keys[1][0] = new Button("4");
		addNumberButtonPressAction(keys[1][0],'4',5);
		keys[1][1] = new Button("5");
		addNumberButtonPressAction(keys[1][1],'5',6);
		keys[1][2] = new Button("6");
		addNumberButtonPressAction(keys[1][2],'6',7);
		keys[1][3] = new Button("*");
		addOperatorButtonPressAction(keys[1][3],'*',13);
		
		keys[2][0] = new Button("1");
		addNumberButtonPressAction(keys[2][0],'1',8);
		keys[2][1] = new Button("2");
		addNumberButtonPressAction(keys[2][1],'2',9);
		keys[2][2] = new Button("3");
		addNumberButtonPressAction(keys[2][2],'3',10);
		keys[2][3] = new Button("-");
		addOperatorButtonPressAction(keys[2][3],'-',14);
		
		keys[3][0] = new Button("0");
		addNumberButtonPressAction(keys[3][0],'0',11);
		keys[3][1] = new Button("C");
		addOperatorButtonPressAction(keys[3][1],'C',15);
		keys[3][2] = new Button("=");
		addOperatorButtonPressAction(keys[3][2],'=',16);
		keys[3][3] = new Button("+");
		addOperatorButtonPressAction(keys[3][3],'+',17);
		
		opKeys.add("*");
		opKeys.add("/");
		opKeys.add("-");
		opKeys.add("+");
		
		for(int y=0;y<4;y++){
			for(int x=0;x<4;x++){
				theKeys.setWidget(x,y,keys[x][y]);
				keys[x][y].setTabIndex(x*3+y);
				keys[x][y].setStyleName("calculator-Button");
			}
		}
		
		theKeyPad.add(theKeys);
		
		theKeyPad.addKeyboardListener(new KeyboardListenerAdapter() {
			public void onKeyPress(Widget sender, char keyCode, int modifiers) {
				if (Character.isDigit(keyCode)) {
					int numberPressed = new Integer(keyCode).intValue() - 48;
					int col=0;
					int row=3;
					if (numberPressed > 0) {
						col = numberPressed % 3 - 1;
						if (col < 0) {
							col = 2 ;
							row = 3 - (numberPressed / 3);
						} else {
							row = 2 - (numberPressed / 3);
						}
					}
					keys[row][col].click();
					//displayNumber(keyCode);
				} else {
					if (opKeys.contains("" + keyCode)) {
						performCalculation(keyCode);
					} else {
						if (keyCode == 13) {
							performCalculation('=');
						}
					}
				}
			}
		});
		theKeyPad.setAccessKey('u');
		theKeyPad.setTabIndex(1);
		
		theDisplay.addKeyboardListener(new KeyboardListenerAdapter() {
			public void onKeyPress(Widget sender, char keyCode, int modifiers) {
				if (!((Character.isDigit(keyCode)) || (opKeys.contains("" + keyCode)))) {
					((TextBox)sender).cancelKey();
				}
			}
		});

		
		theKeyPad.setStyleName("calculator-Keypad");
		theDisplay.setStyleName("calculator-Display");
		theCalculator.setStyleName("calculator-Calculator");
		theCalculator.add(theDisplay);
		theCalculator.add(theKeyPad);
		
		theKeyPad.setTabIndex(100);
		theKeyPad.setAccessKey('c');
		
		initWidget(theCalculator);


	}
	
	private int calculate() {
		switch (operation) {
		case NULL_OP:
			break;
		case ADD:
			storedValue = storedValue + new Integer(theDisplay.getText()).intValue();
			break;
		case SUB:
			storedValue = storedValue - new Integer(theDisplay.getText()).intValue();
			break;
		case MULT:
			storedValue = storedValue * new Integer(theDisplay.getText()).intValue();
			break;
		case DIV:
			storedValue = storedValue / new Integer(theDisplay.getText()).intValue();
			break;
		default:
			break;
		}
		return storedValue;
	}
	
	private void performCalculation(char op) {
		GWT.log("Operation Selected: " + op, null);
		newNumber = true;
		if(operation != NULL_OP) {
			storedValue = calculate();
		} else {
			storedValue = new Integer(theDisplay.getText()).intValue();
		}
		theDisplay.setText("" + storedValue);
		switch(op) {
			case '*':
				operation=MULT;
				break;
			case '+':
				operation=ADD;
				break;
			case '/':
				operation=DIV;
				break;
			case '-':
				operation=SUB;
				break;
			case '=':
				operation=NULL_OP;
				break;
			case 'C':
				operation=NULL_OP;
				newNumber = true;
				theDisplay.setText("0");
				break;
		}
		
	}
	
	private void displayNumber(char num) {
		GWT.log("Number Pressed: " + num, null);
		if(newNumber) {
			theDisplay.setText("" + num);
			newNumber = false;
		} else {
			theDisplay.setText(theDisplay.getText() + num);
		}
	}
	
	private void addNumberButtonPressAction(Button w, final char numberChar, int tabIndex) {
		w.unsinkEvents(Event.KEYEVENTS);
		w.addClickListener(new ClickListener() {

			public void onClick(final Widget sender) {
				sender.setStyleName("calculator-Button-selected");
				displayNumber(numberChar);
				Timer t = new Timer() {
					public void run() {
						sender.setStyleName("calculator-Button");						
					}
				};
				t.schedule(CHANGE_STYLE_DELAY);
			}
			
		});
		w.setTabIndex(100 + tabIndex);
	}
	
	private void addOperatorButtonPressAction(Button w, final char op, int tabIndex) {
		w.unsinkEvents(Event.KEYEVENTS);
		w.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				performCalculation(op);
			}
		});
		w.setTabIndex(100 + tabIndex);
	}
	
	
}
