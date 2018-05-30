package use.common.util.file.upAndDown;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import use.common.util.file.fileName.FileNameTranslate;
import use.common.util.file.upAndDown.except.FileDownLoadException;
import use.common.util.file.upAndDown.except.FileUpLoadException;
import use.common.util.file.upAndDown.fileDownLoad.FileDownLoad;
import use.common.util.file.upAndDown.fileDownLoad.IFileDownLoad;
import use.common.util.file.upAndDown.fileUpLoad.FileUpLoad;
import use.common.util.file.upAndDown.fileUpLoad.IFileUpLoad;


public class FileUpAndDown implements IFileUpLoadAndDownLoad{

	private static IFileDownLoad fdown = new FileDownLoad();
	private static IFileUpLoad fup = new FileUpLoad();
	
	public FileNameTranslate fileDownLoad(HttpServletResponse response,
			FileNameTranslate filePath, String saveFileName) throws FileDownLoadException {
		// TODO Auto-generated method stub
		return fdown.fileDownLoad(response, filePath , saveFileName);
	}

	public FileNameTranslate fileDownLoad(HttpServletResponse response,
			String filePath, String fileName, String saveFileName)
			throws FileDownLoadException {
		return fdown.fileDownLoad(response, filePath , fileName , saveFileName);
	}

	public FileNameTranslate fileDownLoad(HttpServletResponse response,
			String fileName) throws FileDownLoadException {
		return fdown.fileDownLoad(response, fileName);
	}

	public FileNameTranslate fileUpLoad(HttpServletRequest request,
			String fileKey, String DesFileName) throws FileUpLoadException {
		// TODO Auto-generated method stub
		return fup.fileUpLoad(request, fileKey, DesFileName);
	}

	public FileNameTranslate fileUpLoad(HttpServletRequest request,
			String fileKey, String desFilePath, String DesFileName)
			throws FileUpLoadException {
		return fup.fileUpLoad(request, fileKey, desFilePath, DesFileName);
	}

	public void fileDownLoad(HttpServletResponse response, File fileInfo, String saveFileName) {
		// TODO Auto-generated method stub
		fdown.fileDownLoad(response, fileInfo, saveFileName);
	}

	public void setResponseHead(HttpServletResponse response, String saveFileName) throws UnsupportedEncodingException {
		fdown.setResponseHead(response, saveFileName);
		
	}

}
