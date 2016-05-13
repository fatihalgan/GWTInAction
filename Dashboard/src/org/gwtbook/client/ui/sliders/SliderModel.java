package org.gwtbook.client.ui.sliders;

public class SliderModel extends Observable {

	private int currentXValue = 0;
	private int currentYValue = 0;
	
	private int realStartX = 0;
	private int realStartY = 0;
	
	private int realEndX = 0;
	private int realEndY = 0;
	
	
	public void setXValue(int x) {
		currentXValue = x;
		setChanged();
		notifyObservers();
	}
	
	public void setYValue(int y) {
		currentYValue = y;
		setChanged();
		notifyObservers();
	}
	
	public void setXYValue(int x, int y) {
		currentXValue = x;
		currentYValue = y;
		setChanged();
		notifyObservers();
	}
	
	public void setRealStartX(int X){
		realStartX = X;
	}
	
	public void setRealStartY(int Y){
		realStartY = Y;
	}
	
	public void setRealEndX(int X){
		realEndX = X;
	}
	
	
	public void setRealEndY(int Y){
		realEndY = Y;
	}
	
	public int getCurrentXValue(){
		return currentXValue;
	}

	public int getCurrentYValue(){
		return currentYValue;
	}
	
	public int getRealStartX(){
		return realStartX;
	}
	
	public int getRealStartY(){
		return realStartY;
	}
	
	public int getRealEndX(){
		return realEndX;
	}
	
	public int getRealEndY(){
		return realEndY;
	}

	
}
