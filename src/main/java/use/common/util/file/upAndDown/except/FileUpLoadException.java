package use.common.util.file.upAndDown.except;

public class FileUpLoadException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2078430922317858369L;

	public FileUpLoadException() {
    	super();
    }
       
    public FileUpLoadException(String message) {
    	super(message);
    }
    
    public FileUpLoadException(String message, Throwable cause) {
        super(message, cause);
    }
        
    public FileUpLoadException(Throwable cause) {
        super(cause);
    }
}
