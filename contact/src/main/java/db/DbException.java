package db;

public class DbException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5682144063337858343L;
	
	private boolean openConnection = false;
	private boolean closeConnection = false;
	
	private boolean executingStatement = false;
	private boolean closingStatement = false;
	
	public DbException(String message){
		super(message);
	}
	
	public boolean isOpenConnection() {
		return openConnection;
	}

	public void setOpenConnection() {
		this.openConnection = true;
	}

	public boolean isCloseConnection() {
		return closeConnection;
	}

	public void setClosingConnection() {
		this.closeConnection = true;
	}

	public boolean isExecutingStatement() {
		return executingStatement;
	}

	public void setExecutingStatement() {
		this.executingStatement = true;
	}

	public boolean isClosingStatement() {
		return closingStatement;
	}

	public void setClosingStatement() {
		this.closingStatement = true;
	}
}
