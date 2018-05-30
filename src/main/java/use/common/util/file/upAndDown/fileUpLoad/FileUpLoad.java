package use.common.util.file.upAndDown.fileUpLoad;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import use.common.util.file.fileName.FileNameTranslate;
import use.common.util.file.upAndDown.except.FileUpLoadException;

public class FileUpLoad implements IFileUpLoad{

	/**
	 * 上传文件
	 * 
	 * @param request 请求
	 * @param fileKey 请求文件所使用的KEY
	 * @param DesFileName 目标路径文件名 
	 * @return String     全路径文件名 
	 * 
	 * history
	 *
	 */
	public FileNameTranslate fileUpLoad(HttpServletRequest request , String fileKey , String DesFileName) throws FileUpLoadException{
		FileNameTranslate r = FileNameTranslate.tranlate(DesFileName);
		return fileUpLoad(request , fileKey , r.getFilePath() , r.getFileNameAndExtend());
	}
	/**
	 * 上传文件
	 * @param request 请求
	 * @param fileKey 请求文件所使用的KEY
	 * @param desFilePath 目标路径
	 * @param DesFileName 目标文件名
	 * @return
	 * @throws Exception
	 */
	public FileNameTranslate fileUpLoad(HttpServletRequest request, String fileKey, String desFilePath, String DesFileName ) throws FileUpLoadException
	{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile cfile = (CommonsMultipartFile) multipartRequest.getFile(fileKey);
		File dir = new File(desFilePath+File.separator);
		if (!dir.exists()){
			dir.mkdirs();
		}
		String fileName = desFilePath+File.separator+DesFileName;
		File fo = null;
		try{
			fo = new File(fileName);
			cfile.getFileItem().write(fo);
		}catch(Exception e){
			throw new FileUpLoadException(e.getMessage());
		}
		FileNameTranslate r = FileNameTranslate.tranlate(fileName);
		return r;
	}
}
