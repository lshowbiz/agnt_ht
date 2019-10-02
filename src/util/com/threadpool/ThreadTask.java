package com.threadpool;

import java.io.Serializable;

/**
 * 线程任务
 * @author houxyu
 *
 */
public class ThreadTask implements Runnable,Serializable{

	private static final long serialVersionUID = 6881319121604875915L;

	private Object threadPoolTaskData;
	
	
	public ThreadTask(Object threadPoolTaskData) {  
		super();  
		this.threadPoolTaskData = threadPoolTaskData;
	}

	public void run() {
		System.out.println("线程开始执行........");
		threadPoolTaskData = null;
	}

	public Object getTask(){  
	     return this.threadPoolTaskData;  
	} 

}
