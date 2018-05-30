package use.common.tableMap;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.ui.ModelMap;

import use.common.dao.IdaoUpper;
import use.common.security.BaseInfo;
import use.common.tableMap.tableMapException.TableMapSqlException;
import use.common.tableMap.tableMapException.TableMapSystemException;



/**
 * 
 * 项目名称:ctc
 * 类型名称:TableMap
 * 类型描述:
 * 作者:wx
 * 创建时间:2016年1月23日
 * @version:
 */
@SuppressWarnings({"unchecked" , "rawtypes"})
public class DataBaseTableMap implements ITableMap{
	
	public static final String cNullField ="#NULL#";
	
	private String tablePrimaryKey ="ID";
	
	private String fTableName;
	private String fAlias;
	private Map <String , Object> fMap;
	
	private DataBaseTableMap()
	{
		if (BaseInfo.getSecurityInfo().getInfo() instanceof Boolean)
		{
			fMap = new HashMap<String , Object>();
			clear();
		}	
	}
	
	public static DataBaseTableMap instance(String tableName , IdaoUpper fdao)
	{
		DataBaseTableMap m = new DataBaseTableMap();
		m.setFTableName(tableName, fdao);
		return m;
	}
	
	/**
	 * 清空信息
	 * 1.清空MAP中所有元素
	 * 2.清空表名和表别名
	 */
	public void clear()
	{
		fTableName="";
		fAlias = "";
		fMap.clear();
	}
	
	/**
	 * 清空Map中的值
	 */
	
	public void clearMapValue()
	{
		Iterator a = fMap.entrySet().iterator();
		
		while( a.hasNext())
		{
			Map.Entry entry = (Map.Entry) a.next(); 
			entry.setValue("");

		}
		
	}
	
	/**
	 * 设置Map中指定Key的Value
	 * @param key
	 * @param value
	 */
	
	public void setMapValue(String key , Object value)
	{
		if (!fMap.containsKey(key))
		{
			throw new TableMapSqlException("Map中不存在指定的KEY:"+key);
		}
		fMap.put(key, value);
	}
	
	/**
	 * 获取Map中的值
	 * @param key
	 * @return
	 */
	public Object GetMapValue(String key)
	{
		return fMap.get(key);
	}
	
	/**
	 * @return the fTableName
	 */
	public String getFTableName() {
		return fTableName;
	}

	/**
	 * 设置Tablename 字段并根据该字段生成Map
	 * @param tableName the fTableName to set
	 * @throws SQLException 
	 */
	public void setFTableName(String tableName , IdaoUpper fdao) 
	{
		if (!(BaseInfo.getSecurityInfo().getInfo() instanceof Boolean))
		{
			return;
		}
		
		fTableName = tableName;
		fMap.clear();
		SqlRowSet rowSet = fdao.queryForRowSet("select * from "+tableName +" where 1<>1"); 
		SqlRowSetMetaData metaData = rowSet.getMetaData();  
		int columnCount = metaData.getColumnCount();  
		for (int i = 1; i <= columnCount; i++) {  
			fMap.put(metaData.getColumnName(i), "");
//		    Map<String,String> fieldMap = new HashMap<String,String>();  
//		    fieldMap.put("ColumnName", metaData.getColumnName(i));  
//		    fieldMap.put("ColumnType", String.valueOf(metaData.getColumnType(i)));  
//		    fieldMap.put("ColumnTypeName", metaData.getColumnTypeName(i));  
//		    fieldMap.put("CatalogName", metaData.getCatalogName(i));  
//		    fieldMap.put("ColumnClassName", metaData.getColumnClassName(i));  
//		    fieldMap.put("ColumnLabel", metaData.getColumnLabel(i));  
//		    fieldMap.put("Precision", String.valueOf(metaData.getPrecision(i)));  
//		    fieldMap.put("Scale", String.valueOf(metaData.getScale(i)));  
//		    fieldMap.put("SchemaName", metaData.getSchemaName(i));  
//		    fieldMap.put("TableName", metaData.getTableName(i));  
//		    fieldMap.put("SchemaName", metaData.getSchemaName(i));  
//		    System.out.println(fieldMap);  
		}
		
	}

	/**
	 * @return the fAlias
	 */
	public String getFAlias() {
		return fAlias;
	}

	/**
	 * @param alias the fAlias to set
	 */
	public void setFAlias(String alias) {
		fAlias = alias;
	}

	/**
	 * @return the fMap
	 */
	public Map<String, Object> getFMap() {
		return fMap;
	}
	
	
	public void synchronizeMap(Map<String , Object> value)
	{
		clearMapValue();
		Iterator a = fMap.entrySet().iterator();
		while(a.hasNext())
		{
			Map.Entry t = (Map.Entry)a.next();
			String key = t.getKey().toString()+this.fAlias;
			if (value.containsKey(key))
			{
				t.setValue(value.get(key).toString().trim());
				continue;
			}else {
				t.setValue(DataBaseTableMap.cNullField);
			}
		}
		
	}
	
	public void synchronizeEqualMap(Map<String, Object> value) {
		clearMapValue();
		Iterator a = fMap.entrySet().iterator();
		while(a.hasNext())
		{
			Map.Entry t = (Map.Entry)a.next();
			String key = t.getKey().toString()+this.fAlias;
			if (value.containsKey(key))
			{
				t.setValue(value.get(key).toString().trim());
				continue;
			}
			else
			{
				t.setValue(DataBaseTableMap.cNullField);
			}
		}
		
	}
	
	/**
	 * 获取HttpServletRequest中和自己相同的值并赋值
	 * @param pRequest
	 */
	public void synchronizeRequestValue(HttpServletRequest pRequest)
	{
		clearMapValue();
		Iterator t = fMap.entrySet().iterator();
		Map m = pRequest.getParameterMap();
		
		while(t.hasNext())
		{
			Map.Entry e = (Map.Entry) t.next();
			String key = e.getKey().toString()+this.fAlias;
			if (m.containsKey(key))
			{
				e.setValue(m.get(key).toString().trim());
				continue;
			} else {
				e.setValue(DataBaseTableMap.cNullField);
			}
		}
	}
	
	/**
	 * 获取ModelMap中和自己相同的值并赋值
	 * @param pModel
	 */
	
	public void synchronizeModelMapValue(ModelMap pModel)
	{
		clearMapValue();
		Iterator t = fMap.entrySet().iterator();
		while(t.hasNext())
		{
			Map.Entry e = (Map.Entry) t.next();
			String key = e.getKey().toString()+this.fAlias;
			if (pModel.containsKey(key))
			{
				e.setValue(pModel.get(key).toString().trim());
				continue;
			}else {
				e.setValue(DataBaseTableMap.cNullField);
			}
		}
	}
	

	public void ReadInfoFromId(String pId , IdaoUpper pdao)
	{
		clearMapValue();
		StringBuffer str = new StringBuffer();
		str.append("select * from ").append(fTableName).append(" where id=").append(pId);
		List l = pdao.executeQuery(str.toString());
		if (l.size() <=0)
		{
			throw new TableMapSystemException("没有要查找指定ID为："+pId+"的记录信息");
		}
		Map m = (Map)l.get(0);
		synchronizeMap(m);
	}
	
	public ITableMap cloneMap()
	{
		DataBaseTableMap r = new DataBaseTableMap();
		r.setFAlias(this.getFAlias());
		r.tablePrimaryKey = this.tablePrimaryKey;
		r.fTableName = this.tablePrimaryKey;
		r.fMap.clear();
		Iterator t = this.fMap.entrySet().iterator();
		while(t.hasNext())
		{
			Map.Entry obj = (Map.Entry)t.next();
			r.fMap.put(obj.getKey().toString(), "");
		}
		return r;
	}

	public String getTablePrimaryKey() {
		return tablePrimaryKey;
	}

	public void setTablePrimaryKey(String tablePrimaryKey) {
		this.tablePrimaryKey = tablePrimaryKey;
	}
}
