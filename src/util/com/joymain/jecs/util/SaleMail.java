package com.joymain.jecs.util;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
//import com.sunrise.util.StringObjectSet;
//import com.sunrise.home.sale.SaleMailMap;
//import com.sunrise.home.sale.SaleMailQuery;
import java.sql.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: ������4</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: ���ݴ��˵��ӿ������޹�˾</p>
 *
 * @author �ӽ���
 * @version 1.0
 */
public class SaleMail {
    //private SaleMail newmail = null;
    public String mailContent = "";

    private Properties props = System.getProperties();
    private Session session = null;
    private Transport transport = null;

    //private Message       msg         = null;
    private MimeMessage msg = null;
    private MimeMultipart mm = new MimeMultipart();
    private MimeBodyPart mbp = new MimeBodyPart();

    private String auth = "false";
    private final static String mailer = "netkiller";
    private String username = null;
    private String password = null;

    private String host = null;
    private String from = null;
    private String to = null;
    private String cc = "";
    private String bcc = "";
    private String subject = "";
    private String text = "";
    private String footer = "";

    private boolean debug = false;
    
    public static void main(String args[]) throws Exception{
    	
    	//SaleMail.mailForResetPwd("ljye@winalite.com", "sunny", "Test", "12345678", "987654");
    	//SaleMail.mailForWelcome("sunny.register@gmail.com", "sunny", "Test", "12345678", "987654");
    	//SaleMail.mailForWelcome("jackeylaw@163.com", "sunny", "Test", "12345678", "987654");
    	SaleMail.mailForChgInfo("jackeylaw@163.com", "sunny", "Previous shipping address: XXXXXXXXXXXXXXXXX<br>Current Shipping address: XXXXXXXXXXXXXXXXX");
    	//SaleMail.mailForOrder("sunny.register@gmail.com", "sunny", "Test-2", "100");
    }
    
    private SaleMail() throws Exception {
        try {
            //setSmtpHost("209.85.143.111");
        	//setSmtpHost("smtp.gmail.com");
        	//setSmtpHost("mail.winalite.com");
        	setSmtpHost("202.134.122.78");
        	//219.90.127.100
            setAuth(true);//it.winalite@gmail.com -winalite123
            setUsername("webmaster");//payment.us@winalite.com webmaster@winalite.com
            setPassword("12369874");
            createSession();
        } catch (Exception ex) {
            System.out.println("==========="+ex.getMessage());
            throw ex;
        }

    }

    public void createSession() throws Exception {
    	MyAuthenticator   myauth   =   new   MyAuthenticator( this.username, this.password); 
    	session = Session.getInstance(props, myauth);
    	//session = Session.getInstance(props, null);
        session.setDebug(debug);
        try {
            transport = session.getTransport("smtp");
        } catch (NoSuchProviderException ex) {
            System.out.println("---1" + ex.getMessage());
            throw ex;
        }
        try {
            transport.connect(host, username, password);
        } catch (MessagingException ex1) {
            System.out.println("---2" + ex1.getMessage());
            throw ex1;
        }
        msg = new MimeMessage(session);
        
    }
    public void closeSession() throws Exception {
        transport.close();
        msg = null;
        session = null;
    }
    public void setDebug(boolean debug) throws Exception {
        this.debug = debug;
    }

    public void setSmtpHost(String str) throws Exception {
        this.host = str;
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", host);
        /*
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        */
    }

    public void setAuth(boolean auth) {
        if (auth) {
            props.put("mail.smtp.auth", "true");
        }
    }

    public void setUsername(String user) {
        this.username = user;
        props.put("mail.smtp.user", username);
    }

    public void setPassword(String pass) {
        this.password = pass;
        props.put("mail.smtp.password", password);
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setFrom(String str) throws Exception {
        this.from = str;
        msg.setFrom(new InternetAddress(from));
    }

    public void setTo(String str) throws Exception {
        this.to = str;
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
    }

    public void setCC(String str) throws Exception {
        this.cc = str;
        msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, false));
    }

    public void setBCC(String str) throws Exception {
        this.bcc = str;
        msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc, false));
    }

    public void setSubject(String str) throws Exception {
        this.subject = str;
        msg.setSubject(subject);
    }

    public void setText(String str) throws Exception {
        this.text = str;
        this.text += this.footer;

//      msg.setText(this.text);
        BodyPart bp = new MimeBodyPart();
        bp.setContent(text, "text/plain; charset=gb2312");
        mm.addBodyPart(bp);
        msg.setContent(mm);
    }

    public void setFooter(String str) {
        this.footer = str;
    }

    public void setHtml(String str) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("<HTML>\n");
        sb.append("<HEAD>\n");
        sb.append("<TITLE>\n");
        //sb.append(subject + "\n");
        sb.append("</TITLE>\n");
        sb.append("</HEAD>\n");

        sb.append("<BODY>\n");
        //sb.append("<H1>" + subject + "</H1>" + "\n");

        sb.append(str);
        sb.append(this.footer);

        sb.append("\n");

        sb.append("</BODY>\n");
        sb.append("</HTML>\n");

        this.text = sb.toString();

        BodyPart bp = new MimeBodyPart();
        bp.setContent(text, "text/html; charset=gb2312");
        mm.addBodyPart(bp);
        msg.setContent(mm);
    }

    public boolean addFileAffix(String filename) {
        boolean bool = false;
        try {
            BodyPart bp = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(filename);
            bp.setDataHandler(new DataHandler(fds));
            //bp.setFileName(fds.getName());
            bp.setFileName(MimeUtility.encodeWord(fds.getName(), "GB2312", null));
            bp.setHeader("Content-ID", fds.getName());
            mm.addBodyPart(bp);
            //msg.setContent(mm);
            bool = true;
        } catch (Exception e) {
            System.err.println("����ʼ�������" + filename + "�������" + e);
        }
        System.out.println("����ʼ�������" + filename);
        return bool;
    }

    public void send() {

        try {
            msg.setHeader("X-Mailer", mailer);
            msg.setSentDate(new java.util.Date());
            //transport.sendMessage(msg, msg.getAllRecipients());
            transport.send(msg);
            System.out.println("Mail was recorded successfully.");
        } catch (MessagingException e) {
            System.out.println("--------------" + e.getMessage());
        }
    }

    public String sendTest() {
        String s = "success";

        try {
            msg.setHeader("X-Mailer", mailer);
            msg.setSentDate(new java.util.Date());
            //transport.sendMessage(msg, msg.getAllRecipients());
            transport.send(msg);
            //System.out.println("Mail was recorded successfully.");

        } catch (MessagingException e) {
            s = "****" + e.toString();
            //System.out.println(e.toString());
            System.out.println("------1--------" + e.getMessage());
        }
        return s;
    }



    private static String getContent(String pageUrl) throws MalformedURLException, IOException {
        BufferedReader br = null;
        InputStream in = null;
        URLConnection conn = null;
        URL url = null;
        url = new URL(pageUrl);
        conn = url.openConnection();
        conn.setUseCaches(false);
        in = conn.getInputStream();
        br = new BufferedReader(new InputStreamReader(in));
        String line = null;
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {
            sb.append(line);

        }
        br.close();
        br = null;
        in.close();
        in = null;

        return sb.toString();

    }
    /*
    public static void sendMails(String toEmail,String emailType) throws Exception{
    	String content = "";
    	//SaleMail newmail = SaleMail.getInstance();
    	newmail.setFrom("Welcome Email<winalite@21cn.com>");
    	newmail.setTo(toEmail);    	
    	if(emailType.equals("welcome")){
    		newmail.setSubject("Welcome to winalite!");
    		content = getContent("http://127.0.0.1/wecs/mail/welcome2.jsp");
    		newmail.setHtml(content);
    	}else if(emailType.equals("order")){
    		newmail.setSubject("Thanks for your order!");
    		content = getContent("http://127.0.0.1/wecs/mail/welcome2.jsp");
    		
    		newmail.setHtml(content);
    	}else
    		return;
    	newmail.send();
    	//newmail.closeSession();
    }
    */
    /**
     * 注册用户发送邮件
     * @param toEmail	收件人email地址	
     * @param userName	收件人姓名
     * @param memberId	收件人的会员ID
     * @param pwd		会员密码
     * @param adPwd		高级密码
     * @throws Exception
     */
    public static void mailForWelcome(String toEmail,String userName, String memberId ,String pwd, String adPwd) throws Exception{
    	String content = "";
    	SaleMail newmail  = new SaleMail();
    	//newmail.setFrom("Winalite International USA<webmaster@winalite.com>");
    	newmail.setFrom("Winalite.USA");
    	newmail.setTo(toEmail);    	
    	
    	newmail.setSubject("Welcome to Winalite International USA!");
    	content = getContent("http://127.0.0.1/wecs/mail/welcome2.jsp");
    	//content = getContent("http://www.winalite.com/mail/welcome2.html");
    	content = content.replace("user_name", userName).replace("member_id", memberId).replace("p_wd", pwd).replace("pwd_ad", adPwd);
    	newmail.setHtml(content);
    	
    	newmail.send();
    	newmail.closeSession();
    }
    /**
     * 用户重置密码发送邮件
     * @param toEmail	收件人email地址	
     * @param userName	收件人姓名
     * @param memberId	收件人的会员ID
     * @param pwd		会员密码
     * @param adPwd		高级密码
     * @throws Exception
     */
    public static void mailForResetPwd(String toEmail,String userName, String memberId ,String pwd, String adPwd) throws Exception{
    	String content = "";
    	SaleMail newmail  = new SaleMail();
    	newmail.setFrom("Winalite.USA");
    	newmail.setTo(toEmail);    	
    	
    	newmail.setSubject("Password has been reset!");
    	content = getContent("http://127.0.0.1/wecs/mail/pwdreset.jsp");
    	//content = getContent("http://www.winalite.com/mail/pwdreset.html");
    	content = content.replace("user_name", userName).replace("member_id", memberId).replace("p_wd", pwd).replace("pwd_ad", adPwd);
    	newmail.setHtml(content);
    	
    	newmail.send();
    	newmail.closeSession();
    }
    
    /**
     * 下订单后发送邮件
     * @param toEmail	收件人email地址
     * @param userName	收件人姓名
     * @param orderNum	订单号
     * @param orderAmount	订单货物数量
     * @throws Exception
     */
    public static void mailForOrder(String toEmail,String userName, String orderNum, String orderAmount) throws Exception{
    	String content = "";
    	SaleMail newmail  = new SaleMail();
    	newmail.setFrom("Winalite.USA");
    	newmail.setTo(toEmail);
    	newmail.setSubject("Order Confirmation from Winalite International USA!");
    	content = getContent("http://127.0.0.1/wecs/mail/order.jsp");
    	//content = getContent("http://www.winalite.com/mail/order.html");
    	content = content.replace("user_name", userName).replace("order_number", orderNum).replace("order_amount", orderAmount);
    	newmail.setHtml(content);
    	
    	newmail.send();
    	newmail.closeSession();
    }
    
    /**
     * 
     * @param toEmail	收件人email地址
     * @param userName	收件人姓名
     * @param chgStr	修改内容
     * @throws Exception
     */
    public static void mailForChgInfo(String toEmail,String userName, String chgStr) throws Exception{
    	String content = "";
    	SaleMail newmail  = new SaleMail();
    	newmail.setFrom("Winalite.USA");
    	newmail.setTo(toEmail);
    	newmail.setSubject(userName + " Account Setting Update!");
    	content = getContent("http://127.0.0.1/wecs/mail/chgInfo.jsp");
    	//content = getContent("http://www.winalite.com/mail/chgInfo.html");
    	content = content.replace("user_name", userName).replace("change_information", chgStr);
    	newmail.setHtml(content);
    	
    	newmail.send();
    	newmail.closeSession();
    } 

}
