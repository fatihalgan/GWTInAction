package com.packtpub.gwtbook.samples.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("PwStrengthService")
public interface PasswordStrengthService extends RemoteService {
	public int checkStrength(String password);
}
