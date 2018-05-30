package use.common.Eventloop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 运行对象线程
 * 【用于数据量较大无法快速执行完成，放入线程池中由系统执行异步操作】
 * 项目名称:com
 * 类型名称:EventLoopThread
 * 类型描述:
 * 作者:wx
 * 创建时间:2017年5月4日
 * @version:
 */
public class EventLoopPipel implements IEvenLoopPipel , Runnable {

protected final Logger log = LoggerFactory.getLogger(EventLoopPipel.class);
	
	private volatile boolean isEnable = true;
	private volatile boolean startPoolMode = true;
	
	private LinkedBlockingQueue<IEventLoopHandle> queue = null;
	private ExecutorService pool = null;
	private String fName = "";
	private int pThreadCount = 0;
	
	public EventLoopPipel(String name)
	{
		this(name , Runtime.getRuntime().availableProcessors());
	}
	public EventLoopPipel(String name , int threadCount)
	{
		this(name  , threadCount , false);
	}
	/**
	 * 
	 * @param name
	 * @param arrayMax
	 * @param threadCount
	 * @param isPoolMode
	 */
	public EventLoopPipel(String name , int threadCount , boolean isPoolMode)
	{
		pThreadCount = threadCount;
		pool = Executors.newFixedThreadPool(pThreadCount);
		fName = name;
		queue =  new LinkedBlockingQueue<IEventLoopHandle>();
		startPoolMode = isPoolMode;
		if(isPoolMode)
		{
			pool.execute(this);
		}else {
			new Thread(this).start();
		}
	}
	public void terminate()
	{
		isEnable = false;
		pool.shutdownNow();
	}
	public void resume()
	{
		if(isEnable)
		{
			return;
		}
		isEnable = true;
		pool = Executors.newFixedThreadPool(pThreadCount);
		if (startPoolMode)
		{
			pool.execute(this);
		}else {
			new Thread(this).start();
		}
	}

	public synchronized boolean put(IEventLoopHandle v)
	{
		return queue.offer(v);
	}
	public void run() {
		// TODO Auto-generated method stub
		while(isEnable)
		{
			IEventLoopHandle r = null;
			try {
				r = queue.take();
			} catch (InterruptedException e) {
				log.error("获取"+fName+"对象异常，异常原因："+e.getMessage());
			}
			if(r != null)
			{
				try
				{
					r.setOwner(this);
					pool.execute(r);
				}catch(Exception er)
				{
					log.error("运行"+fName+"对象方法异常，异常原因："+er.getMessage());
				}
			}
		}
	}
	
	public boolean isEnable() {
		return isEnable;
	}
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	@SuppressWarnings("rawtypes")
	public LinkedBlockingQueue getQueue() {
		// TODO Auto-generated method stub
		return this.queue;
	}
	
	
}
