package use.common.spring.ExceptionResolver;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import use.common.util.Util;


public abstract class SpringExceptionAbstract {

	/**
	 * 是否为ajax请求
	 * @param request
	 * @return
	 */
	public boolean isAjax(HttpServletRequest request)
	{
		return isSystemAjax(request) || isUserAjax(request);
	}
    /** 
     * 判断当前请求是否为异步请求. 
     */  
    private boolean isSystemAjax(HttpServletRequest request){  
    	String rq = request.getHeader("X-Requested-With");
    	if(rq == null)
    	{
    		return false;
    	}
    	return rq.equals("XMLHttpRequest") ? true : false;
    }
    private boolean isUserAjax(HttpServletRequest hr)
    {
    	String head = hr.getHeader("X-Requested-With");
    	if(head == null)
    	{
    		head = Util.getRequestValue(hr, "X-Requested-With");
    		return "XMLHttpRequestclient".equals(head) ? true : false;
    	}
    	return "XMLHttpRequestclient".equals(head) ? true : false;
    }
    
    /**
     * 获取异常信息输出
     * @param ex
     * @return
     */
    public static String getThrowableMessage(Throwable ex) {  
        StringWriter sw = new StringWriter();  
        PrintWriter pw = new PrintWriter(sw);  
        ex.printStackTrace(pw);  
        return sw.toString();  
    } 
}
