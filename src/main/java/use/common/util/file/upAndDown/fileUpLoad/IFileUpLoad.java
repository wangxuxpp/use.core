package use.common.util.file.upAndDown.fileUpLoad;

import javax.servlet.http.HttpServletRequest;

import use.common.util.file.fileName.FileNameTranslate;
import use.common.util.file.upAndDown.except.FileUpLoadException;

public interface IFileUpLoad {

	public FileNameTranslate fileUpLoad(HttpServletRequest request , String fileKey , String DesFileName) throws FileUpLoadException;
	public FileNameTranslate fileUpLoad(HttpServletRequest request, String fileKey, String desFilePath, String DesFileName ) throws FileUpLoadException;
}
