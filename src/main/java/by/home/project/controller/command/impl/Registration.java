package by.home.project.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.home.project.bean.RegistrationUser;
import by.home.project.bean.User;
import by.home.project.controller.command.Command;
import by.home.project.service.ServiceException;
import by.home.project.service.UserService;
import by.home.project.service.factory.ServiceFactory;

public class Registration implements Command{
	
	private static final String EMAIL_PARAMETER = "email";
	private static final String PASSWORD_PARAMETER = "password";
	private static final String NAME_PARAMETER = "name";
	private static final String SURNAME_PARAMETER = "surname";
	private static final String PHONE_NUMBER_PARAMETER = "phoneNumber";
	private static final String ATTRIBUTE_NAME_USER = "user";
	private static final String MAIN_PAGE_REDIRECT = "Controller?command=gotomainpage&registration=success&signin=success";
	private static final String REGISTRATION_ERROR_REDIRECT = "Controller?command=gotoregistrationpage&registration=error";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter(EMAIL_PARAMETER);
		String password = request.getParameter(PASSWORD_PARAMETER);
		String name = request.getParameter(NAME_PARAMETER);
		String surname = request.getParameter(SURNAME_PARAMETER);
		String phoneNumber = request.getParameter(PHONE_NUMBER_PARAMETER);
		HttpSession session = request.getSession(true);
		
		RegistrationUser regUser = new RegistrationUser(email, password, name, surname,phoneNumber);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		try {
			
			boolean registrationStatus = userService.registration(regUser);
						
			if (registrationStatus) {
				User user = userService.signIn(email, password);
				session.setAttribute(ATTRIBUTE_NAME_USER, user);
				response.sendRedirect(MAIN_PAGE_REDIRECT);
           		}
			
           		 else {
            			response.sendRedirect(REGISTRATION_ERROR_REDIRECT);
           		 }

		} catch (ServiceException e) {
			response.sendRedirect(REGISTRATION_ERROR_REDIRECT);
		}
		
	}

}
