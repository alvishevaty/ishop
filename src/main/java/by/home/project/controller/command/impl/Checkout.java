package by.home.project.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.home.project.bean.Address;
import by.home.project.bean.Delivery;
import by.home.project.bean.Payment;
import by.home.project.bean.User;
import by.home.project.controller.command.Command;
import by.home.project.service.OrderService;
import by.home.project.service.ServiceException;
import by.home.project.service.factory.ServiceFactory;

public class Checkout implements Command {

	private static final String ATTRIBUTE_NAME_BASKET = "basket";
	private static final String ATTRIBUTE_NAME_BASKET_SIZE = "basketSize";
	private static final String ATTRIBUTE_NAME_ORDER_NUMBER = "orderNumber";
	private static final String ORDER_CONFIRMATION_REDIRECT = "Controller?command=gotoorderconfirmationpage&order=success";
	private static final String ERROR_PAGE = "Controller?command=gotoerrorpage";

	private List<Integer> goodsOrderList = new ArrayList<Integer>();

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		HttpSession session = request.getSession(true);

		User user = (User) session.getAttribute("user");

		int userId = user.getId();
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String house = request.getParameter("house");
		String flat = request.getParameter("flat");
		String postCode = request.getParameter("postCode");

		String deliveryType = request.getParameter("deliveryType");
		String deliveryDate = request.getParameter("deliveryDate");

		String paymentMethod = request.getParameter("paymentMethod");
		String amount = request.getParameter("amount");

		goodsOrderList = (List<Integer>) session.getAttribute(ATTRIBUTE_NAME_BASKET);
		Delivery delivery = new Delivery(deliveryType, deliveryDate, userId);
		Payment payment = new Payment(paymentMethod, Integer.parseInt(amount));
		Address address = new Address(country, city, street, house, flat, postCode, userId);

		System.out.println(goodsOrderList);
		System.out.println(delivery);
		System.out.println(payment);
		System.out.println(address);
		
		try {
			int orderNumber = orderService.chechout(goodsOrderList, address, delivery, payment);
			request.setAttribute("orderNumber", orderNumber);
			goodsOrderList.clear();
			session.setAttribute(ATTRIBUTE_NAME_BASKET, goodsOrderList);
			session.setAttribute(ATTRIBUTE_NAME_BASKET_SIZE, 0);
			session.setAttribute(ATTRIBUTE_NAME_ORDER_NUMBER, orderNumber);
			response.sendRedirect(ORDER_CONFIRMATION_REDIRECT);
			
		} catch (ServiceException e) {
			response.sendRedirect(ERROR_PAGE);
		}	
	}

}
