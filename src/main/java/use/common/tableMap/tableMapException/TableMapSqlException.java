package use.common.tableMap.tableMapException;

public class TableMapSqlException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2705631702581228315L;
	
	public TableMapSqlException() {
    	super();
    }
       
    public TableMapSqlException(String message) {
    	super(message);
    }
    
    public TableMapSqlException(String message, Throwable cause) {
        super(message, cause);
    }
        
    public TableMapSqlException(Throwable cause) {
        super(cause);
    }



}
