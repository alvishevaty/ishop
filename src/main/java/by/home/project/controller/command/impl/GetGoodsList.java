
package by.home.project.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.home.project.bean.Category;
import by.home.project.bean.Parameter;
import by.home.project.bean.Product;
import by.home.project.bean.Subcategory;
import by.home.project.controller.command.Command;
import by.home.project.service.ProductService;
import by.home.project.service.ServiceException;
import by.home.project.service.factory.ServiceFactory;

public class GetGoodsList implements Command {

	private static final String CATALOG_PAGE_URL = "/WEB-INF/jsp/catalog.jsp";
	private static final String ERROR_PAGE = "Controller?command=gotoerrorpage";
	private static final String ATTRIBUTE_NAME_GENDER = "gender";
	private static final String ATTRIBUTE_NAME_CATEGORY_LIST = "categoryList";
	private static final String ATTRIBUTE_NAME_SUBCATEGORY_LIST = "subcategoryList";
	private static final String ATTRIBUTE_NAME_GOODS_LIST = "goodsList";
	private static final String CATEGORY_PARAMETER = "category";
	private static final String SUBCATEGORY_PARAMETER = "subcategory";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Product> allProductList = null;
		List<Category> categoryList = null;
		Parameter parameter = new Parameter();

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ProductService productService = serviceFactory.getProductService();

		HttpSession session = request.getSession(true);

		try {

			String genderValue = (String) session.getAttribute(ATTRIBUTE_NAME_GENDER);

			parameter.add(ATTRIBUTE_NAME_GENDER, genderValue);

			categoryList = productService.allCategoryList();
			request.setAttribute(ATTRIBUTE_NAME_CATEGORY_LIST, categoryList);

			if (request.getParameter(CATEGORY_PARAMETER) != null) {
				String category = request.getParameter(CATEGORY_PARAMETER);
				parameter.add(CATEGORY_PARAMETER, category);
				
				List<Subcategory> subcategoryList = productService.allSubcategoryList(category);
				request.setAttribute(category.toLowerCase() + ATTRIBUTE_NAME_SUBCATEGORY_LIST, subcategoryList);
			}

			if (request.getParameter(SUBCATEGORY_PARAMETER) != null) {
				String subcategory = request.getParameter(SUBCATEGORY_PARAMETER);
				parameter.add(SUBCATEGORY_PARAMETER, subcategory);
			}

			allProductList = productService.allGoodsList(parameter);
			request.setAttribute(ATTRIBUTE_NAME_GOODS_LIST, allProductList);

			RequestDispatcher dispatcher = request.getRequestDispatcher(CATALOG_PAGE_URL);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			response.sendRedirect(ERROR_PAGE);
		}

	}

}
