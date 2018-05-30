package use.common.exception;

/**
 * 
 * 项目名称:ctc
 * 类型名称:SystemException
 * 类型描述:系统异常类
 * 作者:wx
 * 创建时间:2016年1月23日
 * @version:
 */
@SuppressWarnings("serial")
public class SystemException extends RuntimeException {

	public SystemException() {
		super();
	}
	
	public SystemException(String message) {
		super(message);
	}

	public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
        
    public SystemException(Throwable cause) {
        super(cause);
    }

}
