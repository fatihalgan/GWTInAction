package org.gwtbook.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

public class DashboardComposite extends Composite implements FocusListener,
		DashboardCompositeInterface {

	protected String applicationDescription = "Dummy Application Description";
	private MenuBar parentMenu;
	protected MenuBar optionsMenuBar = new MenuBar(true);
	private MenuItem optionsMenu;
	
	public DashboardComposite(MenuBar parentMenu) {
		this.parentMenu = parentMenu;
	}
	
	public DashboardComposite() {
		createOptionsMenu();
	}
	
	public void addParentMenu(MenuBar parentMenu) {
		this.parentMenu = parentMenu;
	}
	
	protected void setOptionsMenuName(String name) {
		optionsMenu.setTitle(name);
	}
	
	public void onFocus(Widget sender) {
		addMenu();
	}

	public void onLostFocus(Widget sender) {
		removeMenu();
	}
	
	protected void onDelete() { }
	
	public void addMenu() {
		if(parentMenu != null) {
			optionsMenu = new MenuItem(getName(), optionsMenuBar);
			parentMenu.addItem(optionsMenu);
		}
	}
	
	public void removeMenu() {
		if(parentMenu != null) {
			parentMenu.removeItem(optionsMenu);
		}
	}

	public void createOptionsMenu() {
		// TODO Auto-generated method stub

	}

	public String getName() {
		return "GENERATOR FAILED";
	}

}
