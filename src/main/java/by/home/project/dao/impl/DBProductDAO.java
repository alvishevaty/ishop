package by.home.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import by.home.project.bean.Category;
import by.home.project.bean.GoodsInfo;
import by.home.project.bean.Parameter;
import by.home.project.bean.Product;
import by.home.project.bean.Subcategory;
import by.home.project.dao.DAOException;
import by.home.project.dao.ProductDAO;
import by.home.project.dao.factory.ConnectionPoolFactory;
import by.home.project.dao.impl.connectionpool.ConnectionPool;
import by.home.project.dao.impl.connectionpool.ConnectionPoolException;

public class DBProductDAO implements ProductDAO {

	private static final String SELECT_ALL_GOODS = "SELECT goods.id, category.name as category, subcategory.name as subcategory, manufacturer.name as manufacturer, "
			+ "goods.name, goods.description, goods.gender, goods.size, goods.season, goods.vendor_code as vendorCode, goods.price "
			+ "FROM goods, subcategory, category, manufacturer "
			+ "WHERE goods.subcategory_id = subcategory.id AND subcategory.category_id = category.id AND goods.manufacturer_id = manufacturer.id AND goods.gender = ?";

	private static final String SELECT_CATEGORY_LIST = "SELECT * FROM category";

	private static final String SELECT_ALL_GOODS_BY_PARAMETER_GC = "SELECT goods.id, category.name as category, subcategory.name as subcategory, manufacturer.name as manufacturer, "
			+ "goods.name, goods.description, goods.gender, goods.size, goods.season, goods.vendor_code as vendorCode, goods.price "
			+ "FROM goods, subcategory, category, manufacturer "
			+ "WHERE goods.gender=? AND subcategory.category_id=(SELECT id FROM category WHERE name=?) AND category.name=? AND goods.subcategory_id=subcategory.id AND goods.manufacturer_id = manufacturer.id";

	private static final String SELECT_ALL_GOODS_BY_PARAMETER_GCS = "SELECT goods.id, category.name as category, subcategory.name as subcategory, manufacturer.name as manufacturer, "
			+ "goods.name, goods.description, goods.gender, goods.size, goods.season, goods.vendor_code as vendorCode, goods.price "
			+ "FROM goods, subcategory, category, manufacturer "
			+ "WHERE goods.gender=? AND subcategory.category_id=(SELECT id FROM category WHERE name=?) AND category.name=? AND goods.subcategory_id=(SELECT id FROM subcategory WHERE name=?) "
			+ "AND subcategory.name=? AND goods.manufacturer_id = manufacturer.id";

	private static final String SELECT_SUBCATEGORY_LIST = "SELECT id, name FROM subcategory WHERE (SELECT category.id FROM category WHERE category.name = ?) = category_id";

	private static final String SELECT_ALL_BASKET_GOODS = "SELECT goods.id, category.name as category, subcategory.name as subcategory, manufacturer.name as manufacturer, "
			+ "goods.name, goods.description, goods.gender, goods.size, goods.season, goods.vendor_code as vendorCode, goods.price "
			+ "FROM goods, subcategory, category, manufacturer "
			+ "WHERE goods.subcategory_id = subcategory.id AND subcategory.category_id = category.id AND goods.manufacturer_id = manufacturer.id AND goods.id = ?";

	private static final String SELECT_GOODS_INFO = "SELECT goods.id, manufacturer.name as manufacturer, goods.name, goods.description, goods.size, "
			+ "goods.season, goods.vendor_code as vendorCode, goods.price " + "FROM goods,manufacturer "
			+ "WHERE goods.manufacturer_id = manufacturer.id AND goods.id = ?";

	private static final String ID = "id";
	private static final String CATEGORY = "category";
	private static final String SUBCATEGORY = "subcategory";
	private static final String MANUFACTURER = "manufacturer";
	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";
	private static final String GENDER = "gender";
	private static final String SIZE = "size";
	private static final String SEASON = "season";
	private static final String VENDOR_CODE = "vendorCode";
	private static final String PRICE = "price";
	
	private static final ConnectionPoolFactory connectionPoolFactory = ConnectionPoolFactory.getInstance();
	private static final ConnectionPool connectionPool = connectionPoolFactory.getConnectionPool();

	@Override
	public List<Product> allGoodsList(String genderValue) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Product product = null;

		List<Product> allProducts = new ArrayList<Product>();

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_ALL_GOODS);
			ps.setString(1, genderValue);
			rs = ps.executeQuery();

			while (rs.next()) {

				product = new Product();

				product.setId(rs.getInt(ID));
				product.setCategory(rs.getString(CATEGORY));
				product.setSubcategory(rs.getString(SUBCATEGORY));
				product.setManufacturer(rs.getString(MANUFACTURER));
				product.setName(rs.getString(NAME));
				product.setDescription(rs.getString(DESCRIPTION));
				product.setGender(rs.getString(GENDER));
				product.setSize(rs.getString(SIZE));
				product.setSeason(rs.getString(SEASON));
				product.setVendorCode(rs.getString(VENDOR_CODE));
				product.setPrice(rs.getBigDecimal(PRICE));

				allProducts.add(product);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}

		return allProducts;
	}

	@Override
	public List<Category> allCategoryList() throws DAOException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		Category category = null;
		List<Category> categoryList = new ArrayList<Category>();

		try {

			con = connectionPool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(SELECT_CATEGORY_LIST);

			while (rs.next()) {

				category = new Category();

				category.setId(rs.getInt(ID));
				category.setName(rs.getString(NAME));

				categoryList.add(category);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, st, rs);
		}

		return categoryList;
	}

	@Override
	public List<Subcategory> allSubcategoryList(String category) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Subcategory subcategory = null;
		List<Subcategory> subcategoryList = new ArrayList<Subcategory>();

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_SUBCATEGORY_LIST);
			ps.setString(1, category);
			rs = ps.executeQuery();

			while (rs.next()) {

				subcategory = new Subcategory();

				subcategory.setId(rs.getInt(ID));
				subcategory.setName(rs.getString(NAME));

				subcategoryList.add(subcategory);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}

		return subcategoryList;
	}

	public List<Product> executeRequest(int number, List<String> keyValue) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Product product = null;
		List<Product> allProducts = new ArrayList<Product>();

		try {

			con = connectionPool.takeConnection();

			if (number == 1) {
				ps = con.prepareStatement(SELECT_ALL_GOODS);
				ps.setString(1, keyValue.get(0)); // 0 - gender parameter value

			} else if (number == 2) {
				ps = con.prepareStatement(SELECT_ALL_GOODS_BY_PARAMETER_GC);
				ps.setString(1, keyValue.get(0)); // 0 - gender parameter value
				ps.setString(2, keyValue.get(1)); // 1 - category parameter value
				ps.setString(3, keyValue.get(1)); // 1 - category parameter value

			} else if (number == 3) {
				ps = con.prepareStatement(SELECT_ALL_GOODS_BY_PARAMETER_GCS);
				ps.setString(1, keyValue.get(0)); // 0 - gender parameter value
				ps.setString(2, keyValue.get(1)); // 1 - category parameter value
				ps.setString(3, keyValue.get(1)); // 1 - category parameter value
				ps.setString(4, keyValue.get(2)); // 2 - subcategory parameter value
				ps.setString(5, keyValue.get(2)); // 2 - subcategory parameter value
			}

			rs = ps.executeQuery();

			while (rs.next()) {

				product = new Product();

				product.setId(rs.getInt(ID));
				product.setCategory(rs.getString(CATEGORY));
				product.setSubcategory(rs.getString(SUBCATEGORY));
				product.setManufacturer(rs.getString(MANUFACTURER));
				product.setName(rs.getString(NAME));
				product.setDescription(rs.getString(DESCRIPTION));
				product.setGender(rs.getString(GENDER));
				product.setSize(rs.getString(SIZE));
				product.setSeason(rs.getString(SEASON));
				product.setVendorCode(rs.getString(VENDOR_CODE));
				product.setPrice(rs.getBigDecimal(PRICE));

				allProducts.add(product);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}

		return allProducts;
	}

	@Override
	public List<Product> allGoodsList(Parameter parameter) throws DAOException {

		String value = null;
		List<Product> allProducts = new ArrayList<Product>();
		List<String> keyValue = new ArrayList<String>();

		Set<String> keys = parameter.getParameter().keySet();

		if (!keys.contains(SUBCATEGORY) & !keys.contains(CATEGORY)) {
			for (String key : keys) {
				if (key.equals(GENDER)) {
					value = parameter.getParameter().get(key);
					keyValue.add(value);
				}
			}

			allProducts = executeRequest(1, keyValue);

		} else if (!keys.contains(SUBCATEGORY)) {
			for (String key : keys) {
				if (key.equals(GENDER)) {
					value = parameter.getParameter().get(key);
					keyValue.add(value);
				} else if (key.equals(CATEGORY)) {
					value = parameter.getParameter().get(key);
					keyValue.add(value);
				}
			}

			allProducts = executeRequest(2, keyValue);

		} else {
			for (String key : keys) {

				if (key.equals(GENDER)) {
					value = parameter.getParameter().get(key);
					keyValue.add(value);
				} else if (key.equals(CATEGORY)) {
					value = parameter.getParameter().get(key);
					keyValue.add(value);
				} else if (key.equals(SUBCATEGORY)) {
					value = parameter.getParameter().get(key);
					keyValue.add(value);
				}
			}

			allProducts = executeRequest(3, keyValue);
		}

		return allProducts;
	}

	@Override
	public List<Product> basketGoodsList(List<Integer> basketGoodsIDList) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Product product = null;
		List<Product> basketGoodsList = new ArrayList<Product>();

		try {

			con = connectionPool.takeConnection();

			for (int goodId : basketGoodsIDList) {

				ps = con.prepareStatement(SELECT_ALL_BASKET_GOODS);
				ps.setInt(1, goodId);
				rs = ps.executeQuery();

				while (rs.next()) {

					product = new Product();

					product.setId(rs.getInt(ID));
					product.setCategory(rs.getString(CATEGORY));
					product.setSubcategory(rs.getString(SUBCATEGORY));
					product.setManufacturer(rs.getString(MANUFACTURER));
					product.setName(rs.getString(NAME));
					product.setDescription(rs.getString(DESCRIPTION));
					product.setGender(rs.getString(GENDER));
					product.setSize(rs.getString(SIZE));
					product.setSeason(rs.getString(SEASON));
					product.setVendorCode(rs.getString(VENDOR_CODE));
					product.setPrice(rs.getBigDecimal(PRICE));

					basketGoodsList.add(product);
				}
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}

		return basketGoodsList;
	}

	@Override
	public boolean goodsInfoUpdate(List<Parameter> updatedInfo) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GoodsInfo goodsInfo(int goodsId) throws DAOException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		GoodsInfo goods = null;

		try {

			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_GOODS_INFO);
			ps.setInt(1, goodsId);
			rs = ps.executeQuery();

			while (rs.next()) {

				goods = new GoodsInfo();

				goods.setId(rs.getInt(ID));
				goods.setManufacturer(rs.getString(MANUFACTURER));
				goods.setName(rs.getString(NAME));
				goods.setDescription(rs.getString(DESCRIPTION));
				goods.setSize(rs.getString(SIZE));
				goods.setSeason(rs.getString(SEASON));
				goods.setVendorCode(rs.getString(VENDOR_CODE));
				goods.setPrice(rs.getBigDecimal(PRICE));

			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("ConnectionPool Exception on the DAL", e);
		} catch (SQLException e) {
			throw new DAOException("SQLException on the DAL", e);
		} finally {
			connectionPool.closeConnection(con, ps, rs);
		}

		return goods;
	}

}
