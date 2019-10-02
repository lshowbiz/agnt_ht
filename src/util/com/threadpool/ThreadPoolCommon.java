package com.threadpool;

import java.io.Serializable;

/**
 * 线程池 配置文件中的静态属性
 * @author houxyu
 *
 */
public class ThreadPoolCommon implements Serializable{
	/**
	 * 私有构造函数 不允许直接new
	 */
	private ThreadPoolCommon(){
		
	}
	private static final long serialVersionUID = -6967190862309232826L;

	/**
	 * 线程池维护线程的最少数量  
	 */
	public static int COREPOOLSIZE = Integer.parseInt(ThreadPoolConfig.getProperty("COREPOOLSIZE"));
	
	/**
	 * 线程池维护线程的最大数量   
	 */
	public static int MAXINUMPOOLSIZE = Integer.parseInt(ThreadPoolConfig.getProperty("MAXINUMPOOLSIZE"));
	
	/**
	 * 线程池维护线程所允许的空闲时间  
	 */
	public static int KEEPALIVETIME = Integer.parseInt(ThreadPoolConfig.getProperty("KEEPALIVETIME"));
	
	/**
	 * 线程池所使用的缓冲队列,这里队列大小
	 */
	public static int blockingNumber = Integer.parseInt(ThreadPoolConfig.getProperty("blockingNumber"));
}
