package org.gwtbook.client.ui.clock;

import java.util.Date;

import org.gwtbook.client.ui.DashboardComposite;
import org.gwtbook.client.ui.DashboardPanel;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;

public class Clock extends DashboardComposite {
	
	private Label clockLabel = new Label();
	private boolean local = true;
	
	AlarmTimer alarm;
	ClockTimer clock;
	
	public Clock() {
		super();
		clockLabel.setStyleName("clock");
		clock = new ClockTimer();
		clock.scheduleRepeating(200);
		initWidget(clockLabel);
	}
	
	public void createOptionsMenu() {
		optionsMenuBar.addItem("Local Time", new Command() {
			public void execute() {
				local = true;
				DashboardPanel.getCurrent().setText(getName());
			}
		});
		optionsMenuBar.addItem("GMT Time", new Command() {
			public void execute() {
				local = false;
				DashboardPanel.getCurrent().setText(getName());
			}
		});
		optionsMenuBar.addItem("Alarm", new Command() {
			public void execute() {
				alarm = new AlarmTimer();
    			alarm.schedule(10000);
    			Window.alert("Alarm set in 10 seconds");
			}
		});
		optionsMenuBar.addItem("Alarm Cancel", new Command() {
    		public void execute() {
    			if(alarm != null) {
    				Window.alert("Alarm Cancelled");
    				alarm.cancel();
    				alarm = null;
    			} else Window.alert("Need to set Alarm before it can be cancelled");
    		}
    	});
	}
	
	public Date getTime() {
		Date d = new Date();
        if (!local) d = new Date(d.getTime() - (d.getTimezoneOffset() * 60 * 1000));
        return d;
	}
	
	private class AlarmTimer extends Timer {
		public void run() {
			Window.alert("Alarm");
			alarm = null; 
		}
	}
	
	private class ClockTimer extends Timer {
		public void run() {
			Date d = new Date();
			if(!local) d = new Date(d.getTime() - (d.getTimezoneOffset() * 60 * 1000));
			clockLabel.setText(d.getHours() + ":" + twoDigit(d.getMinutes()) + ":" + twoDigit(d.getSeconds()));
		}
		
		private String twoDigit(int num) {
			String result = Integer.toString(num);
            if (result.length() == 1) return "0" + result;
            return result;
		}
	}
}
