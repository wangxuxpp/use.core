package use.common.tableMap.tableMapException;

public class TableMapSystemException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2705631702581228315L;
	
	public TableMapSystemException() {
    	super();
    }
       
    public TableMapSystemException(String message) {
    	super(message);
    }
    
    public TableMapSystemException(String message, Throwable cause) {
        super(message, cause);
    }
        
    public TableMapSystemException(Throwable cause) {
        super(cause);
    }



}