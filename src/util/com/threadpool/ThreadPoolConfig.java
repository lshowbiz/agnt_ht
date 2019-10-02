package com.threadpool;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * 解析线程池的属性配置文件
 * @author houxyu
 *
 */
public class ThreadPoolConfig implements Serializable {
		
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private static  Properties configProperties=null;
		
		public static Properties getPoperties(){
			return configProperties;
		}
		
		public static void setPoperties(Properties configProperties){
			ThreadPoolConfig.configProperties=null;
			ThreadPoolConfig.configProperties=configProperties;
		}

		/**
		 * 这里利用了单例模式
		 * @param name
		 * @return
		 */
		public static String getProperty(String name){
			String propertyName="";
			if(configProperties!=null){
				propertyName=(String)configProperties.getProperty(name);
			}else{
				ClassLoader classLoader=ThreadPoolConfig.class.getClassLoader();
				InputStream webProperties=classLoader.getResourceAsStream("threadPool.properties");
				ThreadPoolConfig.configProperties=new Properties();
				try {
					configProperties.load(webProperties);
					propertyName=(String)configProperties.getProperty(name);
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(webProperties!=null){
						try {
							webProperties.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			return propertyName;
		}
}
