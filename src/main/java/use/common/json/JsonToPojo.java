package use.common.json;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings({"rawtypes","unchecked"})
public class JsonToPojo {
	

	/**
	 * JSON转对象
	 * @param <T> 对象类型
	 * @param bean 
	 * @param jsonStr json字符串
	 * @return
	 * @throws Exception
	 */
	
	public static <T extends Object>  T toBean (T bean , String jsonStr) throws Exception
	{
		T r = bean ;
		if (jsonStr== null || jsonStr.equals(""))
		{
			return null;
		}

		Map m= toMap(jsonStr);
		toBeanValue(r , m);

		return r;
	}
	
	public static void toBeanValue(Object bean , Map m) throws Exception
	{
		Field[] fs = bean.getClass().getDeclaredFields();
		for (Field f : fs)
		{
			if (m.containsKey(f.getName()))
			{
				Object value = m.get(f.getName());
				if (f.getType().isAssignableFrom(List.class) && value instanceof List)
				{
					toBeanValueList(bean , f , (List) value);
				} else if(value instanceof Map){
					f.setAccessible(true);
					Object des = f.get(bean);
					toBeanValue( des ,(Map)value);
				} 
				else{
					
					toBeanValue(bean , f , value);
				}
			}
		}
	}
	
	public static void toBeanValueList(Object bean ,Field f  , List value) throws Exception
	{
		if (f.getType().isAssignableFrom(List.class) )
		{
			Type beanType = f.getGenericType();
			ParameterizedType pt = (ParameterizedType) beanType;
			Class dc = (Class)pt.getActualTypeArguments()[0];
			if (dc != null)
			{
				for (int i=0 ; i< value.size() ;i++)
				{
					Object obj = dc.newInstance();
					Map m = (Map)value.get(i);
					toBeanValue(obj , m);
					f.setAccessible(true);
					((List)f.get(bean)).add(obj);
				}
			}
			
		}
	}
	public static void toBeanValue(Object obj ,Field f , Object value) throws Exception
	{
		f.setAccessible(true);
		try
		{
			if (f.getType().isAssignableFrom(String.class))
			{
				f.set(obj , value.toString());
			} else {
				f.set(obj, value);
			}
		}catch(Exception e)
		{
			f.set(obj , "");
		}
	}
	
	public static Map toMap(String jsonStr) throws Exception
	{
		Map m = null;
		JSONObject json = JSONObject.fromObject(jsonStr);
		m = getJsonMap(json);
		return m;
		
	}
	
	public static List<Map<String ,Object>> getJsonList(JSONArray json) throws Exception
	{
		List l = new ArrayList();
		for (int i = 0  ; i<json.size() ; i++)
		{
			Object obj = json.get(i);

			if (obj instanceof JSONObject)
			{
				l.add(getJsonMap((JSONObject)obj));
			} 
			else {
				if (obj instanceof JSONArray)
				{
					l.add(getJsonList((JSONArray)obj));
				}
			}
		}
		return l;
	}
	
	public static Map<String ,Object> getJsonMap(JSONObject json) throws Exception
	{
		Map m = new HashMap();
		Iterator<JSONObject> it =json.keys();
		while(it.hasNext())
		{
			Object key = it.next();
			Object value = json.get(key.toString());
			if (value instanceof JSONArray)
			{
				m.put(key.toString().toUpperCase(), getJsonList((JSONArray)value));
			} 
			else if(value instanceof JSONObject){
				m.put(key.toString().toUpperCase(), getJsonMap((JSONObject)value));
				
			}else {
				m.put(key.toString().toUpperCase(), value);
			}
		}
			
		return m;
	}
	
}
