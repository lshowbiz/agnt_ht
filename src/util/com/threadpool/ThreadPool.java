package com.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 */
public class ThreadPool {
	//线程池维护线程所允许的空闲时间的单位  
	private static final TimeUnit UNIT = TimeUnit.SECONDS;
	
	//线程池所使用的缓冲队列,这里队列大小为3  
	private static final BlockingQueue<Runnable> WORKQUEUE = new ArrayBlockingQueue<Runnable>(ThreadPoolCommon.blockingNumber);  

	//线程池对拒绝任务的处理策略：AbortPolicy为抛出异常；CallerRunsPolicy为重试添加当前的任务，
	//他会自动重复调用execute()方法；DiscardOldestPolicy为抛弃旧的任务，DiscardPolicy为抛弃当前的任务  
	private static final CallerRunsPolicy HANDLER = new ThreadPoolExecutor.CallerRunsPolicy(); 
	
	public static ThreadPoolExecutor threadPool = null;

	private ThreadPool(){
		
	}
	
	/**
	 * 静态 初始化线程池 
	 */
	static{
		threadPool = new ThreadPoolExecutor(ThreadPoolCommon.COREPOOLSIZE, ThreadPoolCommon.MAXINUMPOOLSIZE
				, ThreadPoolCommon.KEEPALIVETIME, UNIT, WORKQUEUE, HANDLER);  
	}
	
	public static void addThreadToPool(Runnable task){
		ThreadFactory tf = threadPool.getThreadFactory();
		tf.newThread(task);
		threadPool.execute(task);
	}
}
