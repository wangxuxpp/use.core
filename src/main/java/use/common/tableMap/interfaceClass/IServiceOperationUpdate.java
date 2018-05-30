package use.common.tableMap.interfaceClass;

import java.sql.Connection;
import java.util.Map;

import use.common.session.ISessionUser;

/**
 * 
 * 修改方法回调
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
public interface IServiceOperationUpdate {

	void updateBackInvoke(String tableName , String Id , ISessionUser user , @SuppressWarnings("rawtypes") Map m , Connection cnn) throws Exception;
}
