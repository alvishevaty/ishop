package by.home.project.dao.impl.connectionpool;

public class ConnectionPoolException extends Exception{
	
	private static final long serialVersionUID = 4438127289300310790L;

	public ConnectionPoolException () {
		super();
	}
	
	public ConnectionPoolException (String message) {
		super(message);
	}
	
	public ConnectionPoolException (Exception e) {
		super(e);
	}
	
	public ConnectionPoolException (String message, Exception e) {
		super(message, e);
	}
	
}
