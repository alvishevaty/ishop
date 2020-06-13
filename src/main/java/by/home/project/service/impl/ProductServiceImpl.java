package by.home.project.service.impl;

import java.util.List;

import by.home.project.bean.Category;
import by.home.project.bean.GoodsInfo;
import by.home.project.bean.Parameter;
import by.home.project.bean.Product;
import by.home.project.bean.Subcategory;
import by.home.project.dao.DAOException;
import by.home.project.dao.ProductDAO;
import by.home.project.dao.factory.DAOFactory;
import by.home.project.service.ProductService;
import by.home.project.service.ServiceException;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> allGoodsList(String gender) throws ServiceException {

		List<Product> allProductList = null;

		try {
			DAOFactory daoProductFactory = DAOFactory.getInstance();
			ProductDAO productDAO = daoProductFactory.getProductDAO();

			allProductList = productDAO.allGoodsList(gender);

		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

		return allProductList;

	}

	@Override
	public List<Category> allCategoryList() throws ServiceException {

		List<Category> categoryList = null;

		try {
			DAOFactory daoProductFactory = DAOFactory.getInstance();
			ProductDAO productDAO = daoProductFactory.getProductDAO();
			
			categoryList = productDAO.allCategoryList();

		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

		return categoryList;
	}

	@Override
	public List<Subcategory> allSubcategoryList(String category) throws ServiceException {

		List<Subcategory> subcategoryList = null;

		try {
			DAOFactory daoProductFactory = DAOFactory.getInstance();
			ProductDAO productDAO = daoProductFactory.getProductDAO();
			
			subcategoryList = productDAO.allSubcategoryList(category);

		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

		return subcategoryList;
	}

	@Override
	public List<Product> allGoodsList(Parameter parameter) throws ServiceException {

		List<Product> allProductList = null;

		try {
			DAOFactory daoProductFactory = DAOFactory.getInstance();
			ProductDAO productDAO = daoProductFactory.getProductDAO();

			allProductList = productDAO.allGoodsList(parameter);

		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

		return allProductList;

	}

	@Override
	public List<Product> basketGoodsList(List<Integer> basketGoodsIDList) throws ServiceException {

		List<Product> basketGoodsList = null;

		try {
			DAOFactory daoProductFactory = DAOFactory.getInstance();
			ProductDAO productDAO = daoProductFactory.getProductDAO();

			basketGoodsList = productDAO.basketGoodsList(basketGoodsIDList);

		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

		return basketGoodsList;
	}

	@Override
	public boolean goodsInfoUpdate(List<Parameter> updatedInfo) throws ServiceException {

		boolean updateMethodStatus = false;

		try {
			DAOFactory daoProductFactory = DAOFactory.getInstance();
			ProductDAO productDAO = daoProductFactory.getProductDAO();

			updateMethodStatus = productDAO.goodsInfoUpdate(updatedInfo);
			
		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

		return updateMethodStatus;
	}

	@Override
	public GoodsInfo goodsInfo(int goodsId) throws ServiceException {

		GoodsInfo goods = null;

		try {
			DAOFactory daoProductFactory = DAOFactory.getInstance();
			ProductDAO productDAO = daoProductFactory.getProductDAO();

			goods = productDAO.goodsInfo(goodsId);
			
		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

		return goods;
	}

}