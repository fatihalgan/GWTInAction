package com.packtpub.gwtbook.samples.server;

import java.util.HashMap;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.packtpub.gwtbook.samples.client.AutoFormFillService;

public class AutoFormFillServiceImpl extends RemoteServiceServlet implements AutoFormFillService {

	private HashMap formInfo = new HashMap();
	
	private void loadCustomerData() {
		HashMap customer1 = new HashMap();
		customer1.put("first name", "Joe");
		customer1.put("last name", "Customer");
		customer1.put("address", "123 peachtree street");
		customer1.put("city", "Atlanta");
		customer1.put("state", "GA");
		customer1.put("zip", "30339");
		customer1.put("phone", "770-123-4567");
		formInfo.put("1111", customer1);
		HashMap customer2 = new HashMap();
		customer2.put("first name", "Jane");
		customer2.put("last name", "Customer");
		customer2.put("address", "456 elm street");
		customer2.put("city", "Miami");
		customer2.put("state", "FL");
		customer2.put("zip", "24156");
		customer2.put("phone", "817-123-4567");
		formInfo.put("2222", customer2);
		HashMap customer3 = new HashMap();
		customer3.put("first name", "Jeff");
		customer3.put("last name", "Customer");
		customer3.put("address", "789 sunset blvd");
		customer3.put("city", "Los Angeles");
		customer3.put("state", "CA");
		customer3.put("zip", "90211");
		customer3.put("phone", "714-478-9802");
		formInfo.put("3333", customer3);
	}
	
	public HashMap getFormInfo(String formKey) {
		loadCustomerData();
		if (formInfo.containsKey(formKey)) {
			return (HashMap) formInfo.get(formKey);
		} else {
			return new HashMap();
		}
	}
}
