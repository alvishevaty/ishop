package by.home.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.home.project.bean.Address;
import by.home.project.bean.Delivery;
import by.home.project.bean.Payment;
import by.home.project.dao.DAOException;
import by.home.project.dao.OrderDAO;
import by.home.project.dao.factory.ConnectionPoolFactory;
import by.home.project.dao.impl.connectionpool.ConnectionPool;
import by.home.project.dao.impl.connectionpool.ConnectionPoolException;

public class DBOrderDAO implements OrderDAO {

	private static final ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
	private static final ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();

	private static final String INSERT_ADDRESS_INFO = "INSERT INTO address(country, city, street, flat, post_code, users_id) VALUES(?,?,?,?,?,?)";
	private static final String SELECT_ADDRESS_ID = "SELECT id FROM address WHERE users_id = ?";
	private static final String INSERT_DELIVERY_INFO = "INSERT INTO delivery(users_id, address_id, type, date) VALUES(?,?,?,?)";
	private static final String SELECT_DELIVERY_ID = "SELECT id FROM delivery WHERE users_id = ?";
	private static final String INSERT_PAYMENT_INFO = "INSERT INTO payment(amount, type, status, delivery_id) VALUES(?,?,'Paid',?)";
	private static final String SELECT_PAYMENT_ID = "SELECT id FROM payment WHERE delivery_id = ?";
	private static final String INSERT_ORDER_INFO = "INSERT INTO order(delivery_id, payment_id, users_id) VALUES(?,?,?)";
	private static final String SELECT_ORDER_COUNT = "SELECT COUNT(*) FROM order";

	@Override
	public int checkout(List<Integer> goodsOrderList, Address address, Delivery delivery, Payment payment)
			throws DAOException {

		insertAddressInfo(address);
		int addressId = selectAddressId(address);

		insertDeliveryInfo(delivery, addressId);
		int deliveryId = selectDeliveryId(delivery);

		insertPaymentInfo(payment, deliveryId);

		return deliveryId;

	}

	public void insertAddressInfo(Address address) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(INSERT_ADDRESS_INFO);

			ps.setString(1, address.getCountry());
			ps.setString(2, address.getCity());
			ps.setString(3, address.getStreet());
			ps.setString(4, address.getFlat());
			ps.setString(5, address.getPostCode());
			ps.setInt(6, address.getUserId());

			ps.executeUpdate();

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps);
		}

	}

	public int selectAddressId(Address address) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int addressId = 0;

		try {
			
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_ADDRESS_ID);

			ps.setInt(1, address.getUserId());
			rs = ps.executeQuery();

			List<String> counter = new ArrayList<String>();
			while (rs.next()) {
				counter.add(rs.getString(1));

			}

			if (counter != null && !counter.isEmpty()) {
				addressId = Integer.parseInt(counter.get(counter.size() - 1));
			}

			return addressId;

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}

	}

	public void insertDeliveryInfo(Delivery delivery, int addressId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(INSERT_DELIVERY_INFO);

			ps.setInt(1, delivery.getUserId());
			ps.setInt(2, addressId);
			ps.setString(3, delivery.getDeliveryType());
			ps.setString(4, delivery.getDeliveryDate());

			ps.executeUpdate();

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps);
		}
	}

	public int selectDeliveryId(Delivery delivery) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int deliveryId = 0;

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_DELIVERY_ID);

			ps.setInt(1, delivery.getUserId());
			rs = ps.executeQuery();

			List<String> counter = new ArrayList<String>();
			while (rs.next()) {
				counter.add(rs.getString(1));
			}

			if (counter != null && !counter.isEmpty()) {
				deliveryId = Integer.parseInt(counter.get(counter.size() - 1));
			}

			return deliveryId;

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}

	}

	public void insertPaymentInfo(Payment payment, int deliveryId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(INSERT_PAYMENT_INFO);

			ps.setInt(1, payment.getAmount());
			ps.setString(2, payment.getPaymentMethod());
			ps.setInt(3, deliveryId);

			ps.executeUpdate();

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps);
		}

	}

	public int selectPaymentId(int deliveryId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int paymentId = 0;

		try {
			
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_PAYMENT_ID);

			ps.setInt(1, deliveryId);
			rs = ps.executeQuery();

			while (rs.next()) {
				paymentId = Integer.parseInt(rs.getString(1));
			}

			return paymentId;

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}

	}

	public void insertOrderInfo(int deliveryId, int paymentId, int userId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(INSERT_ORDER_INFO);

			ps.setInt(1, deliveryId);
			ps.setInt(2, paymentId);
			ps.setInt(3, userId);
			
			ps.executeUpdate();

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps);
		}
	}

	public int selectOrderCount() throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int orderCount = 0;

		try {
			
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_ORDER_COUNT);
			rs = ps.executeQuery();

			while (rs.next()) {
				orderCount = Integer.parseInt(rs.getString(1));
			}
			
			return orderCount;

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}
	}
}
