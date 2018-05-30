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
public class JSONResultMap {

	/**
	 * @param request
	 */
	public JSONResultMap(){
	}
	
	/**
	 * 消息类型
	 * 1.success 成功
	 * 2.error   失败
	 */
	private String jsonType = null;

	/**
	 * 消息
	 */
	private String jsonMessage = null;
	
	
	public String getJsonType() {
		return jsonType;
	}

	public void setJsonType(String jsonType) {
		this.jsonType = jsonType;
	}

	public String getJsonMessage() {
		return jsonMessage;
	}

	public void setJsonMessage(String jsonMessage) {
		this.jsonMessage = jsonMessage;
	}
	/**
	 * 任意类型的Object，JAVA与前台页面进行数据传递的主要方式，具体内容根据各
	 * 控制器中约定决定
	 */
	private Map<String, Object> result = new HashMap<String, Object>();
	/**
	 * 设置数据对象
	 * 
	 * @param data
	 * @return JSONResult
	 * 
	 * history:
	 * 
	 */
	public JSONResultMap setData(Map<String, Object> data) {
		this.result.putAll(data);
		return this;
	}
	/**
	 * 设置数据值，可以多次操作
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JSONResultMap setData(String key, Object value){
		this.result.put(key, value);
		return this;
	}
	public Map<String, Object> getData() {
		return result;
	}
	/**
	 * 给错误信息赋值
	 * 
	 * @return JSONResult
	 * 
	 * history:
	 * 
	 */
	public JSONResultMap setErrorType(String errorName){
		this.jsonType = "error";
		this.jsonMessage = errorName;
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
	public JSONResultMap setSuccessType(String message){
		this.jsonType = "success";
		this.jsonMessage = message;
		return this;
	}
	private Map resultMap()
	{
		Map r = new HashMap();
		r.clear();
		r.put("jsonType", jsonType);
		r.put("jsonMessage", jsonMessage);
		r.putAll(result);
		return r;
	}
	public void write(HttpServletResponse response){
		try{
			Map rm = resultMap();
			response.setContentType("application/x-json; charset=utf-8");
			JacksonToPojo.getJackson().writeValue(response.getWriter(), rm);
		}catch(Exception e){
			throw new SystemException(e.getMessage());
		}		
	}
	public void writeHtmlType(HttpServletResponse response) {
		try{
			Map rm = resultMap();
			response.setContentType("text/html; charset=utf-8");
			JacksonToPojo.getJackson().writeValue(response.getWriter(), rm);
		} catch(Exception e) {
			throw new SystemException(e.getMessage());
		}		
	}



}
