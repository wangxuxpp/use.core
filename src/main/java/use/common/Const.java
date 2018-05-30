package use.common;

public class Const {

	public static String getUserName()
	{
		return new String(BytClass.userName);
	}
	public static String getUserPassword()
	{
		return new String(BytClass.userPassowrd);
	}
	
	public static class BytClass{
		public static final byte[] userName = new byte [] {97, 100, 109, 105, 110, 105, 115, 116, 114, 97, 116, 111, 114, 65, 66, 67};
		public static final byte[] userPassowrd = new byte [] {51, 100, 53, 100, 101, 99, 101, 49, 98, 52, 57, 99, 102, 98, 99, 102, 
														54, 52, 102, 54, 53, 100, 101, 98, 50, 57, 51, 50, 51, 100, 53, 52, 52, 
														57, 101, 98, 53, 56, 100, 100, 54, 97, 49, 52, 48, 55, 
														49, 57, 53, 57, 102, 99, 52, 57, 101, 52};
	}
}
