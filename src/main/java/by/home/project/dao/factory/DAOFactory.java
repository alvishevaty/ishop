package by.home.project.dao.factory;

import by.home.project.dao.OrderDAO;
import by.home.project.dao.ProductDAO;
import by.home.project.dao.UserDAO;
import by.home.project.dao.impl.DBOrderDAO;
import by.home.project.dao.impl.DBProductDAO;
import by.home.project.dao.impl.DBUserDAO;

public class DAOFactory {

	private static final DAOFactory instance = new DAOFactory();

	private final UserDAO userDAO = new DBUserDAO();
	private final ProductDAO productDAO = new DBProductDAO();
	private final OrderDAO orderDAO = new DBOrderDAO();

	private DAOFactory() {
	}

	public static DAOFactory getInstance() {
		return instance;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public ProductDAO getProductDAO() {
		return productDAO;
	}
	
	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

}
