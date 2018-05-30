package use.common.util.file.upAndDown;


public class FileUpAndDownUtil {

	private static IFileUpLoadAndDownLoad fud = new FileUpAndDown();

	private static String savePath = "";
	
	
	public static String getSavePath() {
		return savePath;
	}

	public static void setSavePath(String savePath) {
		FileUpAndDownUtil.savePath = savePath;
	}

	public static IFileUpLoadAndDownLoad getUpAndDown() {
		return fud;
	}

	public static void setUpAndDown(IFileUpLoadAndDownLoad fud) {
		FileUpAndDownUtil.fud = fud;
	}

	
}
