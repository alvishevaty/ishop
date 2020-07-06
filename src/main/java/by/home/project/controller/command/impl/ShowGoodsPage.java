package by.home.project.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.home.project.bean.GoodsInfo;
import by.home.project.controller.command.Command;
import by.home.project.service.ProductService;
import by.home.project.service.ServiceException;
import by.home.project.service.factory.ServiceFactory;

public class ShowGoodsPage implements Command {

	private static final String GOODSINFO_PAGE = "/WEB-INF/jsp/goodsinfo.jsp";
	private static final String GOODS_ID_PARAMETER = "goodsId";
	private static final String ATTRIBUTE_NAME_PRODUCT = "product";
	private static final String ERROR_PAGE = "Controller?command=gotoerrorpage";
	private static Logger logger = Logger.getLogger(ShowGoodsPage.class.getName());

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int goodsId = Integer.parseInt(request.getParameter(GOODS_ID_PARAMETER));

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ProductService productService = serviceFactory.getProductService();

		try {
			GoodsInfo goods = productService.goodsInfo(goodsId);
			request.setAttribute(ATTRIBUTE_NAME_PRODUCT, goods);

			RequestDispatcher dispatcher = request.getRequestDispatcher(GOODSINFO_PAGE);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			logger.error("Error during redirect to goods info page.", e);
			response.sendRedirect(ERROR_PAGE);
		}
	}

}
