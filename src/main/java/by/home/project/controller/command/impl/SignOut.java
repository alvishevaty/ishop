package by.home.project.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.home.project.controller.command.Command;

public class SignOut implements Command {

	private static final String MAIN_PAGE_REDIRECT = "Controller?command=gotomainpage";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_PAGE_REDIRECT);
		dispatcher.forward(request, response);

	}

}
