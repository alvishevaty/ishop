package by.home.project.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.home.project.controller.command.Command;

public class GoToOrderConfirmationPage implements Command {

	private static final String ORDER_CONFIRMATION_PAGE = "/WEB-INF/jsp/orderConfirm.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher(ORDER_CONFIRMATION_PAGE);
		dispatcher.forward(request, response);

	}

}
