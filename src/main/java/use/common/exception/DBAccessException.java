package use.common.exception;

/**
 * 
 * 项目名称:ctc
 * 类型名称:DBAccessException
 * 类型描述:数据库访问异常类
 * 作者:wx
 * 创建时间:2016年1月23日
 * @version:
 */
@SuppressWarnings("serial")
public class DBAccessException extends RuntimeException {

	public DBAccessException() {
    	super();
    }
       
    public DBAccessException(String message) {
    	super(message);
    }
    
    public DBAccessException(String message, Throwable cause) {
        super(message, cause);
    }
        
    public DBAccessException(Throwable cause) {
        super(cause);
    }
    
}
