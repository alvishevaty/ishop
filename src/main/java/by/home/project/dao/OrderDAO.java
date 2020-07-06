package by.home.project.dao;

import java.util.List;

import by.home.project.bean.Address;
import by.home.project.bean.Delivery;
import by.home.project.bean.Payment;

public interface OrderDAO {
	
	int checkout(List<Integer> goodsOrderList, Address address, Delivery delivery, Payment payment) throws DAOException;

}
