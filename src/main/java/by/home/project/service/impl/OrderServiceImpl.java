package by.home.project.service.impl;

import java.util.List;

import by.home.project.bean.Address;
import by.home.project.bean.Delivery;
import by.home.project.bean.Payment;
import by.home.project.dao.DAOException;
import by.home.project.dao.OrderDAO;
import by.home.project.dao.factory.DAOFactory;
import by.home.project.service.OrderService;
import by.home.project.service.ServiceException;

public class OrderServiceImpl implements OrderService{

	@Override
	public int chechout(List<Integer> goodsOrderList, Address address, Delivery delivery, Payment payment)
			throws ServiceException {
		
		DAOFactory daoOrderFactory = DAOFactory.getInstance();
		OrderDAO orderDAO = daoOrderFactory.getOrderDAO();

		int orderCount;
		
		try {
			orderCount = orderDAO.checkout(goodsOrderList, address, delivery, payment);
		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

		return orderCount;
	}

}
