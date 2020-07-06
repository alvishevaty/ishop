package by.home.project.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.home.project.bean.User;
import by.home.project.bean.UserInfo;
import by.home.project.controller.command.Command;
import by.home.project.service.ServiceException;
import by.home.project.service.UserService;
import by.home.project.service.factory.ServiceFactory;

public class GoToUserInfoPage implements Command {

	private static final String USER_PROFILE_PAGE = "/WEB-INF/jsp/userprofilepage.jsp";
	private static final String ERROR_PAGE = "Controller?command=gotoerrorpage";
	private static final String ATTRIBUTE_NAME_USER = "user";
	private static final String ATTRIBUTE_NAME_USER_INFO = "userInfo";
	private static Logger logger = Logger.getLogger(Class.class.getName());

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute(ATTRIBUTE_NAME_USER);

		int userId = user.getId();

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		try {
			UserInfo userInfo = userService.takeUserInfo(userId);
			session.setAttribute(ATTRIBUTE_NAME_USER_INFO, userInfo);

			RequestDispatcher dispatcher = request.getRequestDispatcher(USER_PROFILE_PAGE);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			logger.error("Error during redirect to user info page.", e);
			response.sendRedirect(ERROR_PAGE);
		}

	}

}
