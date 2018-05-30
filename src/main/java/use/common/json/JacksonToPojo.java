package use.common.json;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings({"unchecked","rawtypes"})
public class JacksonToPojo {

	private static ObjectMapper objMap = new Jackson2ObjectNullToEmptyMapperBuilder().build();
	
	public static ObjectMapper getJackson()
	{
		return objMap;
	}

	public static Map getJsonToMap(String str) throws JsonParseException, JsonMappingException, IOException
	{
		return objMap.readValue(str, Map.class );
	}
	public static List getJsonToList(String str) throws JsonParseException, JsonMappingException, IOException
	{
		return objMap.readValue(str, List.class);
	}
	public static <T extends Object>  T toBean (Class c, String jsonStr) throws Exception
	{
		return (T)objMap.readValue(jsonStr, c);
	}

}
