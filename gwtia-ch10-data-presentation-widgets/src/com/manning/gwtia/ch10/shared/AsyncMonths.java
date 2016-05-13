package com.manning.gwtia.ch10.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AsyncMonths implements Comparable<AsyncMonths>, IsSerializable {

	int month;
	
	public AsyncMonths(){}
	
	public AsyncMonths(int month){
		this.month = month;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getPhotosNumber() {
		int num = 0;
		return num;
	}

	@Override
	public int compareTo(AsyncMonths otherMonth) {
		return (month-otherMonth.getMonth());
	}
}
