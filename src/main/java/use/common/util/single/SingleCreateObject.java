package use.common.util.single;

public class SingleCreateObject {
    public static <T>T singleCreate(T obj , Class<T> cal , ISingleCreateObject<T> backClass)
    {
    	if(obj == null)
    	{
    		synchronized(cal)
    		{
    			if(obj == null)
    			{
    				obj = backClass.createObject();
    			}
    		}
    	}
    	return obj;
    }
}
