package by.home.project.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.home.project.controller.command.Command;

public class GoToSignInPage implements Command {

	private static final String SIGNIN_PAGE = "WEB-INF/jsp/signin.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher(SIGNIN_PAGE);
		dispatcher.forward(request, response);

	}

}
