package use.common.security;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import use.common.security.IbaseClass.ISecurityInterface;



public class BaseInfo {
	/**
	 * 控件验证过期时间
	 */													
	private static final byte [] nowDate = new byte [] {50, 48, 49, 56, 45, 49, 50, 45, 50, 48} ;	
	/**
	 * 验证插件所使用的项目名称[大写项目名称]
	 */
	//Envir
	//private static final byte [] sysCode = new byte [] {69, 78, 86, 73, 82} ; 
	
	//BSS
	//private static final byte [] sysCode = new byte [] {66, 83, 83} ; 
	
	//EnvirAN
    private static final byte [] sysCode = new byte [] {69, 78, 86, 73, 82, 65, 78} ;		
	
	//WMES
	//private static final byte [] sysCode = new byte [] {87, 77, 69, 83} ;
	//WMESSQ【字符串转字节】
	//private static final byte [] sysCode = new byte [] {87, 77, 69, 83, 83, 81} ;
	//SIXPOOL
	//private static final byte [] sysCode = new byte []{83, 73, 88, 80, 79, 79, 76};
	/**
	 * 是否验证日期
	 */
	private static final boolean checkDate = false;
	
	public static Object getInfo()
	{

		Random rd = new Random(); 
        int x = rd.nextInt(3)+1;
        if(x != 1)
        {
        	return new Integer(0);
        }else{
        	return new Boolean(false);
        }
	}
	
	private static ISecurityInterface fClass = null;
	public static ISecurityInterface getSecurityInfo ()
	{
		if (fClass == null)
		{
			fClass =  new ISecurityInterface() {
				private Date nowDate = new Date();
				private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				public Object getInfo() 
				{
					//是否需要验证日期是否过期
					if(!checkDate)
					{
						return ClassPatchV.getInfo().getInfo();
					}
					try {
						Date sysDate = sd.parse(new String(BaseInfo.nowDate));
						if (sysDate.before(nowDate))
						{
							return  BaseInfo.getInfo();
						} else {
							return ClassPatchV.getInfo().getInfo();
						}
					} catch (Exception e) {
						return new Integer(0);
					}
				}
			};
		}
		return fClass;
	}
	
	public static class ClassPatchV{
		private static ISecurityInterface aClass = null;
		public static ISecurityInterface getInfo()
		{
			if (aClass == null)
			{
				aClass =  new ISecurityInterface() {
					private String path = BaseInfo.class.getResource("/").getPath();
					private String code = new String(BaseInfo.sysCode);
					public Object getInfo() {
						try {
							if (path.toUpperCase().indexOf(code) < 0)
							{
								return  BaseInfo.getInfo();
							} else {
								return new Boolean(false);
							}
						} catch (Exception e) {
							 return  BaseInfo.getInfo();
						}
					}
				};
			}
			return aClass;
		}
	}
	
	//static {new Thread(new ReadFileInfo()).start();}
}
