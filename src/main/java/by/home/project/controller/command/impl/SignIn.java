package by.home.project.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.home.project.bean.User;
import by.home.project.controller.command.Command;
import by.home.project.service.ServiceException;
import by.home.project.service.UserService;
import by.home.project.service.factory.ServiceFactory;

public class SignIn implements Command {

	private static final String EMAIL_PARAMETER = "email";
	private static final String PASSWORD_PARAMETER = "password";
	private static final String ATTRIBUTE_NAME_USER = "user";
	private static final String MAIN_PAGE_REDIRECT = "Controller?command=gotomainpage&signin=success";
	private static final String SIGN_IN_ERROR_REDIRECT = "Controller?command=gotosigninpage&signin=error";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter(EMAIL_PARAMETER);
		String password = request.getParameter(PASSWORD_PARAMETER);

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();
		HttpSession session = request.getSession(true);

		try {

			User user = userService.signIn(email, password);
			session.setAttribute(ATTRIBUTE_NAME_USER, user);

			if (user != null) {
				response.sendRedirect(MAIN_PAGE_REDIRECT);
			} else {
				response.sendRedirect(SIGN_IN_ERROR_REDIRECT);
			}

		} catch (ServiceException e) {
			response.sendRedirect(SIGN_IN_ERROR_REDIRECT);
		}

	}

}
