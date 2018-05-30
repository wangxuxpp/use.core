package use.common.Eventloop.exception;

public class GetEventObjectException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GetEventObjectException() {
    	super();
    }
       
    public GetEventObjectException(String message) {
    	super(message);
    }
    
    public GetEventObjectException(String message, Throwable cause) {
        super(message, cause);
    }
        
    public GetEventObjectException(Throwable cause) {
        super(cause);
    }

}
