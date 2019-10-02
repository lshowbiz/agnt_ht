package com.joymain.jecs.webapp.util.msgUtil;

/*
 * 文件名：  MessageVo.java 2014-11-26
 * 版权：    Copyright 2000-2014 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：    (Initialize)
 * 作者：    Administrator
 * 时间：    2014-11-26
 * 版本号：  RBT_CNCMSXV5.0D605SP33
 * 评审人:    
 * 评审时间:
 */

/**
 * <p>Title: (Initialize)</p>
 * <p>Description: (Initialize)</p>
 * @author  jfoy
 * @version  [RBT_CNCMSXV5.0D605SP33, 2014-11-26]
 * @since SP33
 */
public class MessageVo implements java.io.Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -5204879448518390195L;
    
    /**
     * 访问来源标识(JOCS/WMS)
     */
    private String flag;
    
    /**
     * 接口方法
     */
    private String msgMethod;
    
    /**
     * 返回格式，默认json
     */
    private String msgType;
    
    /**
     * 返回编码，默认utf-8，还支持gbk
     */
    private String charset;
    
    /**
     * 版本号，默认1
     */
    private String ver;
    
    /**
     * Unix时间戳，到秒
     */
    private String timestamp;
    
    /**
     * 接口内容，json字符串
     */
    private String content;
    
    public String getFlag()
    {
        return flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public String getMsgMethod()
    {
        return msgMethod;
    }

    public void setMsgMethod(String msgMethod)
    {
        this.msgMethod = msgMethod;
    }

    public String getMsgType()
    {
        return msgType;
    }

    public void setMsgType(String msgType)
    {
        this.msgType = msgType;
    }

    public String getCharset()
    {
        return charset;
    }

    public void setCharset(String charset)
    {
        this.charset = charset;
    }

    public String getVer()
    {
        return ver;
    }

    public void setVer(String ver)
    {
        this.ver = ver;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

}

