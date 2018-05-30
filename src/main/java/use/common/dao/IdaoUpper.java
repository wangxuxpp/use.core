package use.common.dao;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

@SuppressWarnings("rawtypes")
public interface IdaoUpper 
{
	List executeQuery(String sql, Object... args);
	
	int executeUpdate(String sql, Object... args);
	int getSequenceNextVal(String sequenceName);
	
	SqlRowSet queryForRowSet(String talbeSql); 
	
	
}
