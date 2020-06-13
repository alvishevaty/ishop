package by.home.project.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.home.project.controller.command.Command;

public class PutIntoBasket implements Command {

	private static final String MAIN_PAGE_REDIRECT = "Controller?command=gotomainpage";
	private static final String ATTRIBUTE_NAME_BASKET = "basket";
	private static final String ATTRIBURE_NAME_BASKET_SIZE = "basketSize";
	private static final String ATTRIBUTE_NAME_LAST_REQUEST = "lastUserRequest";
	private static final String GOOD_PARAMETER = "good";

	private List<Integer> goodsBasketList = new ArrayList<Integer>();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		goodsBasketList.add(Integer.parseInt((request.getParameter(GOOD_PARAMETER))));

		session.setAttribute(ATTRIBUTE_NAME_BASKET, goodsBasketList);
		session.setAttribute(ATTRIBURE_NAME_BASKET_SIZE, goodsBasketList.size());
		
		if (session.getAttribute(ATTRIBUTE_NAME_LAST_REQUEST) != null) {
			response.sendRedirect(session.getAttribute(ATTRIBUTE_NAME_LAST_REQUEST).toString());
		} else {
			response.sendRedirect(MAIN_PAGE_REDIRECT);
		}

	}

}
