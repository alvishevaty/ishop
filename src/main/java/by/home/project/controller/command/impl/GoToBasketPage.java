package by.home.project.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.home.project.bean.Product;
import by.home.project.controller.command.Command;
import by.home.project.service.ProductService;
import by.home.project.service.ServiceException;
import by.home.project.service.factory.ServiceFactory;

public class GoToBasketPage implements Command {

	private static final String BASKET_PAGE = "/WEB-INF/jsp/basket.jsp";
	private static final String ATTRIBUTE_NAME_BASKET = "basket";
	private static final String ATTRIBUTE_NAME_BASKET_LIST = "basketList";
	private List<Integer> basketGoodsIDList = new ArrayList<Integer>();

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ProductService productService = serviceFactory.getProductService();

		HttpSession session = request.getSession(true);

		basketGoodsIDList = (List<Integer>) session.getAttribute(ATTRIBUTE_NAME_BASKET);

		try {

			if (basketGoodsIDList.isEmpty()) {
				request.setAttribute(ATTRIBUTE_NAME_BASKET_LIST, "null");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(BASKET_PAGE);
				dispatcher.forward(request, response);

			} else {

				List<Product> basketList = productService.basketGoodsList(basketGoodsIDList);

				request.setAttribute(ATTRIBUTE_NAME_BASKET_LIST, basketList);

				RequestDispatcher dispatcher = request.getRequestDispatcher(BASKET_PAGE);
				dispatcher.forward(request, response);
			}

		} catch (ServiceException e) {

		}

	}

}
