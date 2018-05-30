package use.common.util.propertyFile;

import java.util.Properties;

/**
 * 配置文件读取
 * @author Administrator
 *
 */
public class ReadPropertyValue 
{

	/**
	 * 获取配置文件布尔型值
	 * @param prop
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Boolean getBoolean(Properties prop , String key , boolean defaultValue)
	{
		try
		{
			return Boolean.parseBoolean(prop.getProperty(key).trim());
		}catch(Exception er)
		{
			return defaultValue;
		}
	}
	/**
	 * 读取配置文件字符串值
	 * @param prop
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getStr(Properties prop , String key , String defaultValue)
	{
		try
		{
			return prop.getProperty(key).trim();
		}catch(Exception er)
		{
			return defaultValue;
		}
	}
	/**
	 * 读取配置文件整型值
	 * @param prop
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Integer getInt(Properties prop , String key , int defaultValue)
	{
		try
		{
			return Integer.parseInt(prop.getProperty(key).trim()); 
		}catch(Exception er)
		{
			return defaultValue;
		}
	}
	/**
	 * 读取配置文件long值
	 * @param prop
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Long getLong(Properties prop , String key , Long defaultValue)
	{
		try
		{
			return Long.parseLong(prop.getProperty(key).trim()); 
		}catch(Exception er)
		{
			return defaultValue;
		}
	}
}
