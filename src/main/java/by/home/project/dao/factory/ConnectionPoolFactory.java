package by.home.project.dao.factory;

import by.home.project.dao.impl.connectionpool.ConnectionPool;

public class ConnectionPoolFactory {

	private volatile static ConnectionPoolFactory instance;

	private final ConnectionPool connectionPool = new ConnectionPool();

	private ConnectionPoolFactory() {
	}

	public static ConnectionPoolFactory getInstance() {
		if (instance == null) {
			instance = new ConnectionPoolFactory();	
		}
		return instance;
	}
	
	public ConnectionPool getConnectionPool() {
		return connectionPool;
	}
}
