package use.common.util.file.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import use.common.exception.SystemException;


public class UseFileUtils {

	public static void fileSaveAs(byte[] f , String p)
	{
		File fo = new File(p);
		
		Path filePath = fo.toPath();
		File pf = filePath.getParent().toFile();
		
		if(!pf.exists())
		{
			pf.mkdirs();
		}
		FileOutputStream ou = null;  
        try
        {
        	ou = new FileOutputStream(fo);
        	ou.write(f);  
        }catch(Exception er)
        {
        	throw new SystemException(er.getMessage());
        }
        finally{
        	try {
				ou.close();
			} catch (IOException e) {
			}
        	
        }
		
	}
	/**
	 * 复制文件
	 * @param f 源文件
	 * @param p 目标文件路径
	 * @throws IOException
	 */
	public static void fileSaveAs(File f , String p)
	{
		File fo = new File(p);
		
		Path filePath = fo.toPath();
		File pf = filePath.getParent().toFile();
		
		if(!pf.exists())
		{
			pf.mkdirs();
		}

		FileInputStream in = null;  
		FileOutputStream ou = null;  
        try
        {
        	in = new FileInputStream(f);  
        	ou = new FileOutputStream(fo);
        	in.getChannel().transferTo(0, in.getChannel().size(), ou.getChannel());     
        }catch(Exception er)
        {
        	throw new SystemException(er.getMessage());
        }
        finally{
        	try {
				in.close();
				ou.close();
			} catch (IOException e) {
			}
        	
        }
		
	}
}
