package use.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSerial {
    
	public static byte[] serialize(Object obj) throws IOException
    {
        ObjectOutputStream oStream = null;
        ByteArrayOutputStream bStream = null;
    	bStream = new ByteArrayOutputStream();
    	oStream = new ObjectOutputStream(bStream);
    	oStream.writeObject(obj);
        byte[] bytes = bStream.toByteArray();
        return bytes;
    }
    
    public static Object unserialize(byte[] bytes) throws Exception, ClassNotFoundException {
        ByteArrayInputStream is = null;
        is = new ByteArrayInputStream(bytes);
        ObjectInputStream obj = new ObjectInputStream(is);
        return obj.readObject();
    }
 
	public static boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}

}
