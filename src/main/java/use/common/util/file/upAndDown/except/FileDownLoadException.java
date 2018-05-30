package use.common.util.file.upAndDown.except;

public class FileDownLoadException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5718047161663537541L;
	

	public FileDownLoadException() {
    	super();
    }
       
    public FileDownLoadException(String message) {
    	super(message);
    }
    
    public FileDownLoadException(String message, Throwable cause) {
        super(message, cause);
    }
        
    public FileDownLoadException(Throwable cause) {
        super(cause);
    }

}
