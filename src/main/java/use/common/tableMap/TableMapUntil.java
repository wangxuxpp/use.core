package use.common.tableMap;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import use.common.dao.IdaoUpper;
import use.common.tableMap.tableMapException.TableMapSqlException;


public class TableMapUntil {
	
	private static Map<String , ITableMap> fTableMap = new HashMap<String , ITableMap>();
	
	public static ITableMap getTableMap(String tableName , IdaoUpper fdao)
	{
		ITableMap r = null;
		if (fTableMap.containsKey(tableName))
		{
			r = fTableMap.get(tableName).cloneMap();
		} else {
			r = DataBaseTableMap.instance(tableName, fdao);
			fTableMap.put(tableName, r);
		}
		return r;
	}
	
	
   
	
	 public static void freeAndNull(Object obj)
	    {
	    	Object op = obj;
	    	obj = null;
	    	if (op == null)
	    	{
	    		return ;
	    	}
	    	
			try {
				if (op instanceof Statement)
				{
					((Statement)op).close();
				}
				if (op instanceof PreparedStatement)
				{
					((PreparedStatement)op).close();
				}
				if (op instanceof ResultSet)
				{
					((ResultSet)op).close();
				}
			} catch (SQLException e) {
				throw new TableMapSqlException(e.getMessage());
			}
			finally
			{
				op = null;
			}
	    }
}
