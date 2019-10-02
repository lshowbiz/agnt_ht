package com.joymain.jecs.util.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 对各类对象的操作类
 * @author Aidy.Q
 *
 */
public class BeanUtil {
	
	/**
	 * 深层复制对象
	 * @param fromObject Object
	 * @return Object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object copyBean(Object fromObject) throws IOException, ClassNotFoundException{
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream(); 
		ObjectOutputStream out = new ObjectOutputStream(byteOut); 
		out.writeObject(fromObject); 
		
		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray()); 
		ObjectInputStream in =new ObjectInputStream(byteIn); 
		return in.readObject();
	}
}
