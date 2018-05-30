package use.common.json;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import use.common.exception.SystemException;



/**
 * JSON操作对象，本系统中前台与后台进行通讯使用的对象，注意当JAVA程序向页面
 * 响应时，及时无内容返回也要返回一个空的对象
 * 项目名称:weixue
 * 类型名称:JSONResult
 * 类型描述:	
 * 作者: wx
 * 创建时间:2017年8月16日上午9:25:37
 * 
 * 
 * 
 * @version1.0SSSS
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class JSONResult extends HashMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4983925582163275748L;


	/**
	 * @param request
	 */
	public JSONResult()
	{
		/**
		 * 消息类型
		 * 1.success 成功
		 * 2.error   失败
		 */
		this.put("jsonType", "");
		/**
		 * 消息信息
		 */
		this.put("jsonMessage" , "");
	}
	public JSONResult(Map m)
	{
		this();
		this.putAll(m);
	}
	public void clear()
	{
		super.clear();
		this.put("jsonType", "");
		this.put("jsonMessage" , "");
	}
	public String getJsonType() {
		return this.get("jsonType").toString();
	}

	public void setJsonType(String jsonType) {
		this.put("jsonType", jsonType);
	}

	public String getJsonMessage() {
		return this.get("jsonMessage").toString();
	}

	public void setJsonMessage(String jsonMessage) {
		this.put("jsonMessage", jsonMessage);
	}
	public JSONResult setData(Object data) {
		if (data instanceof Map)
		{
			this.putAll((Map)data);
		}else {
			this.put("result", data);
		}
		return this;
	}
	public Object getData() {
		return this.get("result");
	}
	public JSONResult setData(String key, Object value){
		this.put(key, value);
		return this;
	}
	/**
	 * 给错误信息赋值
	 * 
	 * @return JSONResult
	 * 
	 * history:
	 * 
	 */
	public JSONResult setErrorType(String errorName){
		this.put("jsonType", "error");
		this.put("jsonMessage" , errorName);
		return this;
	}

	/**
	 * 给成功信息赋值
	 * 
	 * @param message
	 * @return JSONResult
	 * 
	 * history:
	 * 
	 */
	public JSONResult setSuccessType(String message){
		this.put("jsonType", "success");
		this.put("jsonMessage" , message);
		return this;
	}
	public void write(HttpServletResponse response){
		try{
			response.setContentType("application/x-json; charset=utf-8");
			JacksonToPojo.getJackson().writeValue(response.getWriter(), this);
		}catch(Exception e){
			throw new SystemException(e.getMessage());
		}		
	}
	public void writeHtmlType(HttpServletResponse response) {
		try{
			response.setContentType("text/html; charset=utf-8");
			JacksonToPojo.getJackson().writeValue(response.getWriter(), this);
		} catch(Exception e) {
			throw new SystemException(e.getMessage());
		}		
	}



}
