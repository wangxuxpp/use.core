package use.common.tableMap;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import use.common.dao.IdaoUpper;
import use.common.tableMap.tableMapException.TableMapSqlException;




/**
 * 
 * 项目名称:ctc
 * 类型名称:ITableMap
 * 类型描述:数据库表Map映射工具类接口
 * 作者:wx
 * 创建时间:2016年1月23日
 * @version:
 */
public interface ITableMap {
	/**
	 * 清空信息
	 * 1.清空MAP中所有元素
	 * 2.清空表名和表别名
	 */
	 void clear();
	/**
	 * 清空Map中的值
	 */
	void clearMapValue();
	/**
	 * 设置Map中指定Key的Value
	 * @param key
	 * @param value
	 */
	void setMapValue(String key , Object value);
	/**
	 * 获取Map中的值
	 * @param key
	 * @return
	 */
	Object GetMapValue(String key);

			/**
	 * @return the fTableName
	 */
	String getFTableName() ;
			/**
	 * 设置Tablename 字段并根据该字段生成Map
	 * @param tableName the fTableName to set
	 * @throws SQLException 
	 */
	void setFTableName(String tableName , IdaoUpper fdao) throws TableMapSqlException;
			/**
	 * @return the fAlias
	 */
	String getFAlias();
	/**
	 * @param alias the fAlias to set
	 */
	void setFAlias(String alias);
	/**
	 * @return the fMap
	 */
	Map<String, Object> getFMap();

	void synchronizeMap(Map<String , Object> value);
	void synchronizeEqualMap(Map<String , Object> value);
	
	/**
	 * 获取HttpServletRequest中和自己相同的值并赋值
	 * @param pRequest
	 */
	void synchronizeRequestValue(HttpServletRequest pRequest);
	/**
	 * 获取ModelMap中和自己相同的值并赋值
	 * @param pModel
	 */
	void synchronizeModelMapValue(ModelMap pModel);

	
	void ReadInfoFromId(String pId , IdaoUpper pdao);
	
	ITableMap cloneMap();
}
