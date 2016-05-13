package com.manning.gwtia.ch10.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AsyncDays implements Comparable<AsyncDays>, IsSerializable {

	int day;
	
	public AsyncDays(){}
	
	public AsyncDays(int day) {
		this.day = day;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getPhotosNumber() {
		int num = 0;
		return num;
	}
	
	@Override
	public int compareTo(AsyncDays otherDay) {
		return (day - otherDay.getDay());
	}
}
