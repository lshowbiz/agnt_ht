package com.joymain.jecs.util.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 *
 * <p>Title: 类反射工具类 ,取类方法集，属性集</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: sunrise</p>
 *
 * @author wangquan
 * @version 1.0
 */
public final class ReflectUtil {


    /**
     * 取 类对象的 的 属性名称 数组
     * @param obj  类实例对象
     * @param cmpModifier 访问权限 public；private；
     * @return String[]
     */
    private static String[] getFieldsName(Object obj, int cmpModifier) {
        String[] retValue;
        ArrayList list = new ArrayList();
        Field[] fields = obj.getClass().getDeclaredFields();

        if (fields != null){
            for (int i=0;i< fields.length;i++){
                int modifier = fields[i].getModifiers();
                if (modifier == cmpModifier)
                    list.add(fields[i]);
            }
        }

        retValue = new String[list.size()];
        for (int i=0;i<list.size();i++){
            retValue[i] = ((Field)list.get(i)).getName();
        }
        return retValue;

    }

    public static final String[] getPrivateFieldsName(Object obj) {
        return getFieldsName(obj,Modifier.PRIVATE);
    }

    public static final String[] getProtectedFieldsName(Object obj) {
        return getFieldsName(obj,Modifier.PROTECTED);
    }

    public static final String[] getPublicFieldsName(Object obj) {
        return getFieldsName(obj,Modifier.PUBLIC);
    }

    public static final String[] getAllFieldsName(Object obj) {
        String[] retValue = null;
        Field[] fields = obj.getClass().getDeclaredFields();

        if (fields != null){
            retValue = new String[fields.length];
            for (int i=0;i<fields.length;i++){
                retValue[i] = fields[i].getName();
            }
        }
        return retValue;
    }

    /**
     * 返回取得类实例对象 cmpModifier，prefix 方法 名称 数组。
     * @param obj  类实例对象
     * @param cmpModifier 访问权限 public；private；
     * @param prefix  方法前缀 get ，set 等
     * @return String[] 返回取得方法数组。
     */
    private static String[] getMethodsName(Object obj,int cmpModifier, String prefix){
        String[] retValue =null;
        Method[] methods = getMethods(obj,cmpModifier,prefix);
        if(methods != null){
            retValue = new String[methods.length];

            for(int i= 0 ; i< methods.length ;i++){
                retValue[i] = methods[i].getName();
            }
        }
        return retValue;
    }


    public static final String[] getPrivateMethodsName(Object obj){
        return getMethodsName(obj,Modifier.PRIVATE,null);
    }

    public static final String[] getProtectedMethodsName(Object obj) {
        return getMethodsName(obj,Modifier.PROTECTED,null);
    }

    public static final String[] getPublicMethodsName(Object obj) {
        return getMethodsName(obj,Modifier.PUBLIC,null);
    }

    public static final String[] filterPublicMethodsName(Object obj,String prefix) {
        return getMethodsName(obj,Modifier.PUBLIC,prefix);
    }

    /**
     *  返回取得类实例对象 cmpModifier，prefix 方法数组。
     * @param obj  类实例对象
     * @param cmpModifier 访问权限 public；private；
     * @param prefix  方法前缀 get ，set 等
     * @return Method[] 返回取得方法数组。
     */
    private static Method[] getMethods(Object obj,int cmpModifier,String prefix){
        Method[] retMethod = null;
        ArrayList list = new ArrayList();
        Method[] methods = obj.getClass().getDeclaredMethods();
        if (methods != null){
            for (int i=0;i<methods.length;i++){
                int modifier = methods[i].getModifiers();  //public ,private 方法的可访问属性
                if (modifier==cmpModifier){
                    if (prefix!=null && !prefix.equals("")) {
                        if ( methods[i].getName().startsWith(prefix))
                            list.add(methods[i]);
                    }else{
                        list.add(methods[i]);
                    }
                }
            }

            retMethod =  new Method[list.size()];
            for (int i=0;i< list.size();i++){
                retMethod[i] = (Method)list.get(i);
            }
        }
        return retMethod;

    }



    public static final Method[] getPrivateMethods(Object obj,String prefix){
        return getMethods(obj, Modifier.PRIVATE,prefix);
    }

    public static final Method[] getProtectedMethods(Object obj,String prefix){
        return getMethods(obj, Modifier.PROTECTED,prefix);
    }

    /**
     * 取类obj中 开始字符为prefix 的方法 数组
     * @param obj
     * @param prefix
     * @return Method[]
     */
    public static final Method[] getPublicMethods(Object obj,String prefix){
        return getMethods(obj, Modifier.PUBLIC,prefix);
    }


}
