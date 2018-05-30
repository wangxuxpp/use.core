package use.common.util.file.upAndDown.fileDownLoad;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import use.common.util.file.fileName.FileNameTranslate;
import use.common.util.file.upAndDown.except.FileDownLoadException;

public interface IFileDownLoad
{

	public FileNameTranslate fileDownLoad(HttpServletResponse response , FileNameTranslate filePath , String saveFileName) throws FileDownLoadException;
	public FileNameTranslate fileDownLoad(HttpServletResponse response , String filePath , String fileName , String saveFileName) throws FileDownLoadException;
	public FileNameTranslate fileDownLoad(HttpServletResponse response ,String fileName) throws FileDownLoadException;
	public void fileDownLoad(HttpServletResponse response ,File fileInfo, String saveFileName);
	
	public void setResponseHead(HttpServletResponse response ,String saveFileName) throws UnsupportedEncodingException;
}
