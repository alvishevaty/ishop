package by.home.project.dao;

import by.home.project.bean.RegistrationUser;
import by.home.project.bean.User;
import by.home.project.bean.UserInfo;

public interface UserDAO {
	
	User signIn(String email, String password) throws DAOException;

	boolean registration(RegistrationUser regUser) throws DAOException;

	boolean checkEmailAccessibility(String email) throws DAOException;

	UserInfo takeUserInfo(int userId) throws DAOException;
}
