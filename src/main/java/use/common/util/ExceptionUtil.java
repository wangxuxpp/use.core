package use.common.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.util.Assert;

public class ExceptionUtil {

	/**
	 * 异常处理
	 * @param er
	 * @param log
	 */
    public static void throwError( Throwable er , Logger log )
    {
    	Assert.notNull(er, "异常信息不能NULL");
    	StackTraceElement stackTraceElement = null;
    	List<StackTraceElement> el = new ArrayList<StackTraceElement>();
    	
    	for (StackTraceElement a : er.getStackTrace())
    	{
    		if (a.getClassName().equals("use.common.exception.JsonException") || 
    			a.getClassName().equals("use.aspect.common.aspact.ReturnJsonAspect"))
    		{
    			continue;
    		}
    		else 
    		{
    			el.add(a);
    		}
    		
    	}
    	
    	StackTraceElement[] aa =new StackTraceElement[el.size()] ;
    	er.setStackTrace(el.toArray(aa));
    	stackTraceElement= er.getStackTrace()[0]; 
		String erInfo = "File="+stackTraceElement.getFileName();
		erInfo += ",Line="+stackTraceElement.getLineNumber();
		erInfo += ",Class="+stackTraceElement.getClassName();
		erInfo += ",Method="+stackTraceElement.getMethodName();
		if(log != null)
		{
			log.error("/**********************************************$$$************************************************************/");
			log.error("系统运行异常："+erInfo);
			log.error("系统运行异常：", er);
		}else {
			System.out.println("/**********************************************$$$************************************************************/");
			System.out.println(erInfo);
		}
		
		//er.printStackTrace();
    }
    /**
     * 异常处理
     * @param er
     */
    public static void throwError( Throwable er)
    {
    	throwError(er , null);
    }
}
