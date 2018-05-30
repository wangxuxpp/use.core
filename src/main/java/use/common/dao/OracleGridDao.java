package use.common.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import use.common.dao.excption.GridDBException;


@SuppressWarnings("rawtypes")
public class  OracleGridDao implements IGridDao{
	/**
     * 将传入的无分页的SQL语句组装成有分页的SQL语句,
     * 返回的语句可以当成实际的数据库表直接使用
     * 返回的表中将在原SQL语句基础上增加新的列ROWNO,代表行的编号
     * 
     * 注意：为了提高性能,需要在内层嵌套的SQL语句中使用,不能在最终的查询结果上使用
     * 
     * @param sql 原始无分页的SQL语句
     * @param end 分页结束行号
     * @return 分页的SQL语句,可以当表使用
     */
	private  String fenyeSql(String sql, int end) {
		if(end > 0) {    	
    		return String.format(
    				"SELECT * FROM " + 
    				"  (SELECT TABLE_ALIAS_.*, ROWNUM ROWNO FROM " + 
    				"      (%s) TABLE_ALIAS_ WHERE ROWNUM <= ?)  " +
    				"WHERE ROWNO >= ? ", sql);
    		
    	} else {    		
    		return String.format(
    				"SELECT * FROM " + 
    				"  (SELECT TABLE_ALIAS_.*, ROWNUM ROWNO FROM " + 
    				"      (%s) TABLE_ALIAS_) " +
    				"WHERE ROWNO >= ? ", sql);
    	}
    }
	
	
	/**
	 * 分页方法专用
	 * 
	 * @param start 开始记录数
	 * @param rows  每页行数
	 * @param sql	查询语句
	 * @param args  参数列表 
	 * @return Map对象,新分页SQL,新分页参数
	 */	   
	
	public  Map fenyeSql(int start, int rows, String sql, Object[] args) {
		Map<String, Object> result = new HashMap<String, Object>();	
		try {
			int end = start + rows;
			String newSql = fenyeSql(sql, end);
			int len = 0;
			if(args == null || args.length <= 0) {
				len = 0;
			} else {
				len = args.length;
			}
			//生成新的参数列表,原有的参数列表以及后增加的两个开始记录和结束记录数的参数
			Object newArgs[] = new Object[end > 0 ? (len + 2) : (len + 1)];
			for(int i = 0; i < len; i++) {
				newArgs[i] = args[i];
			}
			if(end > 0) {
				newArgs[len] = end;
				newArgs[len + 1] = start + 1;
			} else {
				newArgs[len] = start + 1;
			}
			result.put("newSql", newSql);
			result.put("newArgs", newArgs);
			
			return result;
		} catch(Exception e) {
			throw new GridDBException(e.getMessage());
		}
	}

	/**
	 * 将分页查询的结果放入到map中并且同时放入总行数
	 *
	 * @param start	开始记录数
	 * @param rows  每页行数
	 * @param sql   查询语句
	 * @param args  参数列表 
	 * @return Map  注意其中的key在此方法中是写死的
	 * 		key: fenyeList-----------------------分页查询的结果集
	 * 		key: recordSize----------------------查询的总记录数
	 */
	@SuppressWarnings("unchecked")
	public  <T extends IdaoUpper> Map fenyeMap(T iDao ,int start, int rows, String sql, Object[] args) {
		Map<String, Object> result = new HashMap<String, Object>();	
		try {
			Map fenyeSql = fenyeSql(start, rows, sql, args);
			
			String newSql = (String)fenyeSql.get("newSql");
			Object[] newArgs = (Object[])fenyeSql.get("newArgs");
			
			List<Map<String, Object>> fenyeList = iDao.executeQuery(newSql, newArgs);
			//总记录数
			long recordSize = recordSize(iDao,sql, args);
			
			result.put("fenyeList", fenyeList);
			result.put("recordSize", recordSize);
			result.put("newSql", newSql);
			result.put("newArgs", newArgs);
			result.put("oldSql", sql);
		} catch(Exception e) {
			result.put("fenyeList", new ArrayList(0));
			result.put("recordSize", new Long(0));
		}
		return result;
	}
	
	/**
	 * 根据传入的SQL语句,取得该查询语句所查询出的记录个数
	 *
	 * @param sql  需要传入到SQL语句
	 * @param args 需要传入到SQL语句中的参数列表,如果没有参数则传入null,
	 * 有参数则需要跟SQL语句中的"?"所对应
	 * @return 返回值类型为long
	 */
	public  <T extends IdaoUpper> long recordSize(T iDao,String sql, Object[] args) {
		try {
			List l = iDao.executeQuery(sizeSql(sql), args);
			String str = ((Map)l.get(0)).get("IC").toString();
			return Long.valueOf(str);
		} catch(Exception e) {
			throw new GridDBException(e);
		}
	}
	/**
     * 对传入的SQL语句进行重构,变成查询记录数的SQL语句
     * 返回的SQL语句可以直接执行,进而获取记录集中的记录数
     * 
     * 如：传入SQL语句为 select id, name from table
     * 转换后的SQL语句为 select count(1) from table
     * 
     * @param sql 原始SQL语句
     * @return 重构后查询记录数的SQL语句
     */
	private  String sizeSql(String sql) {
		if(sql.toUpperCase().indexOf("UNION") != -1 || sql.toUpperCase().indexOf("GROUP BY") != -1 
				|| sql.toUpperCase().indexOf("CASE") != -1) {
    		return new StringBuffer().append("SELECT COUNT(1) IC FROM (").append(sql).append(")").toString();
    	}
		sql = sql.trim();
    	int selectPos = sql.toUpperCase().indexOf("SELECT");
    	int fromPos = sql.toUpperCase().indexOf("FROM");
    	if(selectPos == -1 || fromPos == -1) {
    		throw new GridDBException("无效的SQL语句,未发现SELECT,FROM关键字");
    	}
    	String sizeSql = sql.substring(0, selectPos + 6) + " COUNT(1) IC " + 
        	sql.substring(fromPos, sql.length());
    	
    	return sizeSql;
    }

}
