package by.home.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.home.project.bean.RegistrationUser;
import by.home.project.bean.User;
import by.home.project.bean.UserInfo;
import by.home.project.dao.DAOException;
import by.home.project.dao.UserDAO;
import by.home.project.dao.factory.ConnectionPoolFactory;
import by.home.project.dao.impl.connectionpool.ConnectionPool;
import by.home.project.dao.impl.connectionpool.ConnectionPoolException;

public class DBUserDAO implements UserDAO {

	private static final String SELECT_USER_INFO = "SELECT * FROM users WHERE email = ? AND password=?";
	private static final String INSERT_NEW_USER = "INSERT INTO users(email, password, name, surname,phone_number,role_id,status) VALUES(?,?,?,?,?,1,'Active')";
	private static final String CHECH_EMAIL_ACCESSIBILITY = "SELECT email FROM users WHERE email LIKE ?";
	private static final String SELECT_USER_INFO_FOR_PROFILE_PAGE = "SELECT name, surname, phone_number as phoneNumber, email FROM users WHERE id = ?";
	
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String PHONE_NUMBER = "phoneNumber";
	private static final String EMAIL = "email";

	@Override
	public User signIn(String email, String password) throws DAOException {

		ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_USER_INFO);

			ps.setString(1, email);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			}

			user = new User();

			user.setId(rs.getInt(ID));
			user.setName(rs.getString(NAME));

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}

		return user;
	}

	@Override
	public synchronized boolean registration(RegistrationUser regUser) throws DAOException {

		if (!checkEmailAccessibility(regUser.getEmail())) {
			return false;
		}

		ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(INSERT_NEW_USER);

			ps.setString(1, regUser.getEmail());
			ps.setString(2, regUser.getPassword());
			ps.setString(3, regUser.getName());
			ps.setString(4, regUser.getSurname());
			ps.setString(5, regUser.getPhoneNumber());

			ps.executeUpdate();

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps);
		}

		return true;
	}

	@Override
	public boolean checkEmailAccessibility(String email) throws DAOException {

		ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(CHECH_EMAIL_ACCESSIBILITY);

			ps.setString(1, email);

			rs = ps.executeQuery();

			if (rs.next()) {
				return false;
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps, rs);

		}
		return true;
	}

	@Override
	public UserInfo takeUserInfo(int userId) throws DAOException {

		ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
		ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserInfo userInfo = null;

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_USER_INFO_FOR_PROFILE_PAGE);

			ps.setInt(1, userId);

			rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			}

			userInfo = new UserInfo();

			userInfo.setName(rs.getString(NAME));
			userInfo.setSurname(rs.getString(SURNAME));
			userInfo.setPhoneNumber(rs.getString(PHONE_NUMBER));
			userInfo.setEmail(rs.getString(EMAIL));

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}

		return userInfo;
	}

}
