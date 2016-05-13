package com.packtpub.gwtbook.samples.client;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class PrimesClient implements EntryPoint {

	public void onModuleLoad() {
		final TextBox primeNumber = new TextBox();
		final PrimesServiceAsync primesService = (PrimesServiceAsync)GWT.create(PrimesService.class);
		final Button checkPrime = new Button("Is this a prime number?", new ClickListener() {
			public void onClick(Widget sender) {
				AsyncCallback callback = new AsyncCallback() {
					public void onFailure(Throwable caught) {
						Window.alert("Error while calling the primes service!");						
					}

					public void onSuccess(Object result) {
						if(((Boolean)result).booleanValue()) {
							Window.alert("Yes, " + primeNumber.getText() + " is a prime number.");
						} else {
							Window.alert("No, " + primeNumber.getText() + " is not a prime number.");
						}
					}
				};
				primesService.isPrimeNumber(Integer.parseInt(primeNumber.getText()), callback);
			}
		});
		RootPanel.get().add(primeNumber);
		RootPanel.get().add(checkPrime);
	}

}
