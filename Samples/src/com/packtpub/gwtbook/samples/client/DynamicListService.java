package com.packtpub.gwtbook.samples.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("DynamicListsService")
public interface DynamicListService extends RemoteService{
	public List getManufacturers();
	public List getBrands(String manufacturer);
	public List getModels(String manufacturer, String brand);
}
