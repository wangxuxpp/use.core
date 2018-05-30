package use.common.dao;

import java.util.Map;


@SuppressWarnings("rawtypes")
public interface IGridDao 
{

	
	/**
	 * 分页方法专用
	 * 
	 * @param start 开始记录数
	 * @param rows  每页行数
	 * @param sql	查询语句
	 * @param args  参数列表 
	 * @return Map对象,新分页SQL,新分页参数
	 */
	Map fenyeSql(int start, int rows, String sql, Object[] args);
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
	<T extends IdaoUpper> Map fenyeMap(T iDao ,int start, int rows, String sql, Object[] args);
	/**
	 * 根据传入的SQL语句,取得该查询语句所查询出的记录个数
	 *
	 * @param sql  需要传入到SQL语句
	 * @param args 需要传入到SQL语句中的参数列表,如果没有参数则传入null,
	 * 有参数则需要跟SQL语句中的"?"所对应
	 * @return 返回值类型为long
	 */
	<T extends IdaoUpper> long recordSize(T iDao,String sql, Object[] args);

}
