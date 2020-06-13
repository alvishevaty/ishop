package by.home.project.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.home.project.bean.RegistrationUser;
import by.home.project.bean.User;
import by.home.project.bean.UserInfo;
import by.home.project.dao.DAOException;
import by.home.project.dao.UserDAO;
import by.home.project.dao.factory.DAOFactory;
import by.home.project.service.ServiceException;
import by.home.project.service.UserService;

public class UserServiceImpl implements UserService {
	
	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

	@Override
	public User signIn(String email, String password) throws ServiceException {

		if (email == null || email.isEmpty() || isValidEmail(email) == false) {
			throw new ServiceException("Incorrect email");
		}

		User user = null;

		try {
			DAOFactory daoUserFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoUserFactory.getUserDAO();
			user = userDAO.signIn(email, password);
			
		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

		return user;
	}

	@Override
	public boolean registration(RegistrationUser regUser) throws ServiceException {

		boolean registrationStatus;

		if (!isCorrectRegistrationData(regUser)) {
			return false;
		}

		try {
			DAOFactory daoUserFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoUserFactory.getUserDAO();
			registrationStatus = userDAO.registration(regUser);

		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

		return registrationStatus;
	}

	public boolean isValidEmail(String email) {

		final String correctEmailRegex = EMAIL_REGEX;

		Pattern pattern = Pattern.compile(correctEmailRegex);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	public boolean isCorrectRegistrationData(RegistrationUser regUser) {

		String email = regUser.getEmail();
		String password = regUser.getPassword();
		String name = regUser.getName();
		String surname = regUser.getSurname();
		String phoneNumber = regUser.getPhoneNumber();
		int minLength = 6;

		if (email == null || email.isEmpty() || !isValidEmail(email)) {
			return false;
		} else if (password == null || password.isEmpty() || password.length() < minLength) {
			return false;
		} else if (name == null || name.isEmpty()) {
			return false;
		} else if (surname == null || surname.isEmpty()) {
			return false;
		} else if (phoneNumber == null || phoneNumber.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public UserInfo takeUserInfo(int userId) throws ServiceException {

		UserInfo userInfo = null;

		try {
			DAOFactory daoUserFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoUserFactory.getUserDAO();
			userInfo = userDAO.takeUserInfo(userId);
			
		} catch (DAOException e) {
			throw new ServiceException("DAOException on the Service Layer", e);
		}

		return userInfo;
	}

}
