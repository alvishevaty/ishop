package by.home.project.service;

import java.util.List;

import by.home.project.bean.Address;
import by.home.project.bean.Delivery;
import by.home.project.bean.Payment;

public interface OrderService {
	
	int chechout(List<Integer> goodsOrderList, Address address, Delivery delivery, Payment payment) throws ServiceException;

}
