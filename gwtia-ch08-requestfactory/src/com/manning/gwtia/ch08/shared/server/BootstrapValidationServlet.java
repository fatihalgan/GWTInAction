package com.manning.gwtia.ch08.shared.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.validation.Validation;

import org.hibernate.validator.HibernateValidator;

@SuppressWarnings("serial")
public class BootstrapValidationServlet extends HttpServlet {

	public void init() throws ServletException {
		super.init();
		Validation.byProvider(HibernateValidator.class).configure();
	}
}
