package com.packtpub.gwtbook.samples.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("PrimesService")
public interface PrimesService extends RemoteService {
	
	public boolean isPrimeNumber(int numberToVerify);
}
