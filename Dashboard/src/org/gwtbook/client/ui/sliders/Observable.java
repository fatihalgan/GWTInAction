package org.gwtbook.client.ui.sliders;

import java.util.Iterator;
import java.util.Vector;

public class Observable {

	private boolean changed;
	
	private Vector observers;
	
	public Observable() {
		observers = new Vector();
	}
	
	public void addObserver(Observer observer) {
		if(observer == null) throw new RuntimeException("Trying to add a null observer to observable");
		observers.add(observer);
	}

	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	public void removeObservers() {
		observers.clear();
	}
	
	public void clearChanged() {
		changed = false;
	}
	
	public void setChanged() {
		changed = true;
	}
	
	public int countObservers() {
		return observers.size();
	}
	
	public boolean hasChanged() {
		return changed;
	}
	
	public void notifyObservers() {
		notifyObservers(null);
	}
	
	public void notifyObservers(Object obj) {
		if(hasChanged()){
			for(Iterator it = observers.iterator(); it.hasNext();) {
				Observer observer = (Observer)it.next();
		        observer.update(this, obj);
		    }
		}
	}
	
	

}
