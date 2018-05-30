package use.common.Eventloop;

/**
 * 运行对象线程--处理对象接口
 * 项目名称:com
 * 类型名称:IEventLoopHandle
 * 类型描述:
 * 作者:wx
 * 创建时间:2017年5月4日
 * @version:
 */
public interface IEventLoopHandle extends Runnable{
	// 取得订阅的消息后的处理 
	void onMessage(String key, String message);
	
	void setOwner(IEvenLoopPipel loop);
}
