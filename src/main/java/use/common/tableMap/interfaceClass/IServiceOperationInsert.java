package use.common.tableMap.interfaceClass;

import java.sql.Connection;
import java.util.Map;

import use.common.session.ISessionUser;

/**
 * 插入方法回调
 * 
 * @author wx
 * @version  2014-3-20
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
public interface IServiceOperationInsert {

	@SuppressWarnings("rawtypes")
	void insertBackInvoke(String tableName , String Id , ISessionUser user , Map m , Connection cnn) throws Exception;
}
