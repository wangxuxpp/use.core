package use.common.util.file.fileName;

import java.io.File;

public class FileNameTranslate {

	//文件扩展名
	private String fileExtendName;
	//文件扩展及文件名
	private String fileNameAndExtend;
	//文件路径
	private String filePath;
	//文件路径及文件名不包括扩展名
	private String filePathAndName;
	//文件路径及文件名包括扩展名
	private String fillAllName;
	
	public String getFillAllName() {
		return fillAllName;
	}
	public void setFillAllName(String fillAllName) {
		this.fillAllName = fillAllName;
	}
	public String getFileExtendName() {
		return fileExtendName;
	}
	public void setFileExtendName(String fileExtendName) {
		this.fileExtendName = fileExtendName;
	}
	public String getFileNameAndExtend() {
		return fileNameAndExtend;
	}
	public void setFileNameAndExtend(String fileNameAndExtend) {
		this.fileNameAndExtend = fileNameAndExtend;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFilePathAndName() {
		return filePathAndName;
	}
	public void setFilePathAndName(String filePathAndName) {
		this.filePathAndName = filePathAndName;
	}
	
	private FileNameTranslate()
	{
		
	}
	/**
	 * 
	 * @param fileName 文件名称带路径
	 */
	public static FileNameTranslate tranlate(String fileName)
	{
		FileNameTranslate r = new FileNameTranslate();
		
		String name = fileName.replace("\\",File.separator);
		name = name.replace("/", File.separator);
		String dir = name.substring(0 , name.lastIndexOf(File.separator));
		String fn = name.substring(name.lastIndexOf(File.separator)+1);
		
		r.fileNameAndExtend = fn;
		r.fileExtendName = fn.substring(fn.lastIndexOf(".")+1);
		r.filePath = dir;
		r.filePathAndName = dir + fn.substring(0 , fn.lastIndexOf("."));
		r.fillAllName = name;
		return r;
	}
}
