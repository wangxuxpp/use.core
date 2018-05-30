package use.common.session;

public interface ISessionUser {

	int getUserId();
	String getUserName();
	String getIp();
	
	void setUserId(int userId);
	void setUserName(String userName);
	void setIp();
}
