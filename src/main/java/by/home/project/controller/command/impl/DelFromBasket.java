package by.home.project.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.home.project.controller.command.Command;

public class DelFromBasket implements Command{
	
	private static final String ATTRIBUTE_NAME_BASKET = "basket";
	private static final String ATTRIBUTE_NAME_BASKET_SIZE = "basketSize";
	private static final String ATTRIBUTE_NAME_LAST_REQUEST = "lastUserRequest";	
	private static final String GOOD_PARAMETER = "good";
	private static final String MAIN_PAGE = "/index.jsp";
	
	private List<Integer> goodsBasketList = new ArrayList<Integer>();
	private int indexOfDelitedGoods;

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		goodsBasketList = (List<Integer>) session.getAttribute(ATTRIBUTE_NAME_BASKET);
		indexOfDelitedGoods = goodsBasketList.indexOf(Integer.parseInt((request.getParameter(GOOD_PARAMETER))));
		
		goodsBasketList.remove(indexOfDelitedGoods);
		
		session.setAttribute(ATTRIBUTE_NAME_BASKET, goodsBasketList);
		session.setAttribute(ATTRIBUTE_NAME_BASKET_SIZE, goodsBasketList.size());

		if (session.getAttribute(ATTRIBUTE_NAME_LAST_REQUEST) != null) {
			response.sendRedirect(session.getAttribute(ATTRIBUTE_NAME_LAST_REQUEST).toString());

		} else {
			response.sendRedirect(MAIN_PAGE);
		}
	}
}
