package use.common.util.file.upAndDown.fileDownLoad;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;

import use.common.util.file.fileName.FileNameTranslate;
import use.common.util.file.upAndDown.except.FileDownLoadException;

public class FileDownLoad implements IFileDownLoad {
	
	public void fileDownLoad(HttpServletResponse response, File fileInfo , String saveFileName) {
		// TODO Auto-generated method stub
		InputStream fis = null;
		try{
			if(!fileInfo.exists()){
				throw new FileDownLoadException("本地存储文件不存在");
			}
			
			fis = new BufferedInputStream(new FileInputStream(fileInfo));
			String f = saveFileName.equals("") ? fileInfo.getName() : saveFileName;
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="+ new String(f.getBytes("GB2312"), "ISO-8859-1"));
			String exd = fileInfo.getName().substring(fileInfo.getName().lastIndexOf(".")+1);
			response.setContentType("application/" +exd);
			FileCopyUtils.copy(fis, response.getOutputStream());
		}catch(Exception er)
		{
			throw new FileDownLoadException(er.getMessage());
		}
		finally{
			if(fis != null){
				try{
					fis.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public FileNameTranslate fileDownLoad(HttpServletResponse response , FileNameTranslate filePath , String saveFileName) throws FileDownLoadException
	{
		File file = new File(filePath.getFillAllName());
		fileDownLoad(response , file , saveFileName);
		return filePath;
	}
	
	/**
	 * 文件下载
	 * 
	 * @param response
	 * @param filePath 服务器文件路径
	 * @param fileName 服务器文件名
	 * @param saveFileName 目标文件名
	 * @throws IOException
	 */
	public FileNameTranslate fileDownLoad(HttpServletResponse response , String filePath , String fileName , String saveFileName) throws FileDownLoadException
	{
		FileNameTranslate r = FileNameTranslate.tranlate(filePath+fileName);
		return fileDownLoad(response , r , saveFileName);
	}
	
	public FileNameTranslate fileDownLoad(HttpServletResponse response ,String fileName) throws FileDownLoadException
	{
		FileNameTranslate r =  FileNameTranslate.tranlate(fileName);
		return fileDownLoad(response , r , "");
	}

	public void setResponseHead(HttpServletResponse response, String saveFileName) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		response.setContentType("application/x-msdownload;");
		response.setHeader("Content-disposition", "attachment; filename="+ new String(saveFileName.getBytes("GB2312"), "ISO-8859-1"));
		response.setContentType("application/" +saveFileName);
	}



}
