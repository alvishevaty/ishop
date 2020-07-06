package by.home.project.service.factory;

import by.home.project.service.OrderService;
import by.home.project.service.ProductService;
import by.home.project.service.UserService;
import by.home.project.service.impl.OrderServiceImpl;
import by.home.project.service.impl.ProductServiceImpl;
import by.home.project.service.impl.UserServiceImpl;

public class ServiceFactory {

	private static final ServiceFactory instance = new ServiceFactory();

	private final UserService userService = new UserServiceImpl();
	private final ProductService productService = new ProductServiceImpl();
	private final OrderService orderService = new OrderServiceImpl();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public ProductService getProductService() {
		return productService;
	}
	
	public OrderService getOrderService() {
		return orderService;
	}
}
