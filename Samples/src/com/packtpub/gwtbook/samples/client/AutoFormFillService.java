package com.packtpub.gwtbook.samples.client;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("AutoFormFillService")
public interface AutoFormFillService extends RemoteService {

	public HashMap getFormInfo(String formKey);
}
