package com.manning.gwtia.ch09.server;

import com.google.web.bindery.requestfactory.shared.Locator;
import com.google.web.bindery.requestfactory.shared.ServiceLocator;

public class ContactLocator extends Locator<Contact, Long> implements ServiceLocator {

	private static ContactService serviceInstance;
	
	private static ContactService getServiceInstance () {
        if (serviceInstance == null) serviceInstance = new ContactService();
        return serviceInstance;
    }

	@Override
	public Contact create(Class<? extends Contact> clazz) {
		return new Contact();
	}
	
	@Override
	public Contact find(Class<? extends Contact> clazz, Long id) {
		return getServiceInstance().find(id);
	}
	
	@Override
	public Class<Contact> getDomainType() {
		return Contact.class;
	}
	
	@Override
	public Class<Long> getIdType() {
		return Long.class;
	}
	
	@Override
	public Long getId(Contact contact) {
		return contact.getId();
	}
	
	@Override
	public Object getVersion(Contact contact) {
		return contact.getVersion();
	}
	
	@Override
	public Object getInstance(Class<?> clazz) {
		return ContactLocator.getServiceInstance();
	}
	
}
