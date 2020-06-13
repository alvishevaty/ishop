package by.home.project.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.home.project.controller.command.Command;

public class ChangeLocale implements Command {

	private static final String LOCAL_PARAMETER = "local";
	private static final String ATTRIBUTE_NAME_LOCAL = "local";
	private static final String ATTRIBUTE_NAME_LAST_REQUEST = "lastUserRequest";
	private static final String MAIN_PAGE = "Controller?command=gotomainpage";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession(true);

		session.setAttribute(ATTRIBUTE_NAME_LOCAL, request.getParameter(LOCAL_PARAMETER));
		
		if (session.getAttribute(ATTRIBUTE_NAME_LAST_REQUEST) != null) {
			
			response.sendRedirect(session.getAttribute(ATTRIBUTE_NAME_LAST_REQUEST).toString());

		} else {
			response.sendRedirect(MAIN_PAGE);
		}

	}

}
