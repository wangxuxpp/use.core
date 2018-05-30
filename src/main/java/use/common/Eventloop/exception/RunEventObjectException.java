package use.common.Eventloop.exception;

public class RunEventObjectException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RunEventObjectException() {
    	super();
    }
       
    public RunEventObjectException(String message) {
    	super(message);
    }
    
    public RunEventObjectException(String message, Throwable cause) {
        super(message, cause);
    }
        
    public RunEventObjectException(Throwable cause) {
        super(cause);
    }
}
