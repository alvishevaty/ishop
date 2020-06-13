package by.home.project.service;

import by.home.project.bean.User;
import by.home.project.bean.UserInfo;
import by.home.project.bean.RegistrationUser;

public interface UserService {
	
	User signIn(String email, String password) throws ServiceException;

	boolean registration(RegistrationUser regUser) throws ServiceException;

	UserInfo takeUserInfo(int userId) throws ServiceException;
}
