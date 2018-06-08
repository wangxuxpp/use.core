package use.common.util;

import java.util.Map;

import javax.servlet.ServletRequest;

import use.common.util.single.ISingleCreateObject;

/**
 * 
 * 项目名称:ctc
 * 类型名称:Util
 * 类型描述:系统工具类
 * 作者:wx
 * 创建时间:2016年1月23日
 * @version:
 */
@SuppressWarnings({"rawtypes"})
public class Util {
		
    public static String getRequestValue(ServletRequest request , String key)
    {
    	return request.getParameter(key) == null ? null : request.getParameter(key).toString().trim();
    }
    public static String getRequestValue(Map requestMap , String key)
    {
    	return requestMap.get(key) == null ? null : requestMap.get(key).toString().trim();
    }
    public static <T>T singleCreate(T obj , Class<T> cal , ISingleCreateObject<T> backClass)
    {
    	if(obj == null)
    	{
    		synchronized(cal)
    		{
    			if(obj == null)
    			{
    				obj = backClass.createObject();
    			}
    		}
    	}
    	return obj;
    }

}
