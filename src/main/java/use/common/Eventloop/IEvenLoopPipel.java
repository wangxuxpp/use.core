package use.common.Eventloop;

import java.util.concurrent.LinkedBlockingQueue;

/**
 *  运行对象线程接口
 * 项目名称:com
 * 类型名称:IEvenLoopPiple
 * 类型描述:
 * 作者:wx
 * 创建时间:2017年5月4日
 * @version:
 */
public interface IEvenLoopPipel {
	
	boolean put(IEventLoopHandle v);

	boolean isEnable();
	void setEnable(boolean isEnable);

	String getfName();
	void setfName(String fName);
	
	@SuppressWarnings("rawtypes")
	LinkedBlockingQueue getQueue();
}
