package use.common.json;

import java.io.IOException;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * json null 值 转 ‘’
 * 项目名称:use.core
 * 类型名称:Jackson2ObjectNullToEmptyMapperBuilder
 * 类型描述:
 * 作者:wx
 * 创建时间:2018年5月26日
 * @version:
 */
public class Jackson2ObjectNullToEmptyMapperBuilder extends Jackson2ObjectMapperBuilder{

	 @SuppressWarnings("unchecked")
	public <T extends ObjectMapper> T build() {
	        ObjectMapper om=new ObjectMapper();
	        // 重写 ObjectMapper  中的一个方法
	        om.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
	            @Override
	            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
	                jgen.writeObject("");// 输出孔字符串
	            }
	        });
	        configure(om);
	        return (T) om;
	    }
}
