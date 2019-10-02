package com.joymain.jecs.fi.webapp.action;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mobset.smsSDK;
import mobset.str_SendMsg;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.math.MathUtil;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.fi.model.JfiPosImport;
import com.joymain.jecs.fi.service.FiBankbookJournalManager;
import com.joymain.jecs.fi.service.JfiPosImportManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
/**
 * POS刷卡金认领
 * @author Administrator
 * EC
 */
@SuppressWarnings({"rawtypes","unused"})
public class JfiPosImportFormController extends BaseFormController {
    private JfiPosImportManager jfiPosImportManager = null;
    private FiBankbookJournalManager fiBankbookJournalManager = null;

    public void setJfiPosImportManager(JfiPosImportManager jfiPosImportManager) {
        this.jfiPosImportManager = jfiPosImportManager;
    }
    public JfiPosImportFormController() {
        setCommandName("jfiPosImport");
        setCommandClass(JfiPosImport.class);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        JfiPosImport jfiPosImport = new JfiPosImport();
        return jfiPosImport;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }
    	SysUser loginSysUser = SessionLogin.getLoginUser(request);
    	SysUser operatorSysUser = SessionLogin.getOperatorUser(request);

        JfiPosImport jfiPosImport = (JfiPosImport) command;
        jfiPosImport.setStatus("2");
        jfiPosImport.setInc("0");
        if(StringUtil.isEmpty(request.getParameter("amount"))|| jfiPosImport.getAmount() == null || StringUtil.isEmpty(jfiPosImport.getPid())|| StringUtil.isEmpty(jfiPosImport.getTel())|| StringUtil.isEmpty(jfiPosImport.getPosNo())){
        	saveMessage(request, LocaleUtil.getLocalText("参考号、金额、流水号、验证码为必填项。"));
        	return showForm(request, response, errors);
        }
        if(StringUtil.isEmpty(request.getParameter("amount").trim())|| StringUtil.isEmpty(jfiPosImport.getAmount().toString().trim())|| StringUtil.isEmpty(jfiPosImport.getPid().trim())|| StringUtil.isEmpty(jfiPosImport.getTel().trim())|| StringUtil.isEmpty(jfiPosImport.getPosNo().trim())){
        	saveMessage(request, LocaleUtil.getLocalText("参考号、金额、流水号、验证码为必填项。"));
        	return showForm(request, response, errors);
        }
        if(!MathUtil.isDecimal(request.getParameter("amount"))){
        	saveMessage(request, LocaleUtil.getLocalText("金额格式不正确"));
        	return showForm(request, response, errors);
        }
        String messageCode = jfiPosImport.toString();
        List jfiPosImports = jfiPosImportManager.getJfiPosImports(jfiPosImport);
        if(jfiPosImports==null||jfiPosImports.size()!=1){
        	saveMessage(request, LocaleUtil.getLocalText("无此信息，请检查后再输入。"));
        	return showForm(request, response, errors);
        }else{
        	jfiPosImport = (JfiPosImport)jfiPosImports.get(0);
            ///判断验证码是否过时、进钱
        	jfiPosImport.setInc("1");
        	jfiPosImport.setIncTime(new Date());
        	jfiPosImport.setUserCode(loginSysUser.getUserCode());
        	jfiPosImport.setMessageCode(messageCode);
        	try{
        		fiBankbookJournalManager.saveJfiPayDataVerifyByPosImport("CN", loginSysUser, operatorSysUser.getUserCode(), operatorSysUser.getUserName(), jfiPosImport);
        	}catch(AppException app){
        		app.printStackTrace();
        		saveMessage(request, LocaleUtil.getLocalText("获取资金失败，请联系客服，错误代码:1,")+app.getMessage());
        	    return new ModelAndView("redirect:editJfiPosImport.html");
        	}catch(Exception e){
        		e.printStackTrace();
        		saveMessage(request, LocaleUtil.getLocalText("获取资金失败，请联系客服，未知错误代码:2,")+e.getMessage());
        	    return new ModelAndView("redirect:editJfiPosImport.html");
        	}
            saveMessage(request, LocaleUtil.getLocalText("验证成功，你的刷卡金已存入您的会员基金账户！"));
        }
        return new ModelAndView("redirect:editJfiPosImport.html");
    }
    
    private void sendSms(JfiPosImport jfiPosImport){
    	if(jfiPosImport==null){
    		jfiPosImport = new JfiPosImport();
    		jfiPosImport.setTel("15989136432");
    		jfiPosImport.setMessageCode("手验短信验证");
    	}
		smsSDK sdk = new smsSDK();
		try {
			int iRet = sdk.Sms_Connect("www.mobset.com", 111043, "test", "281512", 30); // 测试时请更改企业ID,用户名,密码
			if (iRet == 0){// 登录成功
				iRet = sdk.Sms_KYSms();
				if (iRet >= 0) {
					str_SendMsg[] sendMsg = new str_SendMsg[1];
					sendMsg[0] = new str_SendMsg();
					sendMsg[0].strExNum = ""; // 扩展号码，不用扩展请留空。
					sendMsg[0].strMobile = jfiPosImport.getTel(); // 目标手机号码，测试时请更改号码。
					sendMsg[0].strMsg = LocaleUtil.getLocalText("tel.code").replace("{code}", jfiPosImport.getMessageCode()); // 短信内容
					iRet = sdk.Sms_Send(sendMsg, 1);
					if (iRet > 0) {
						log.error("发送短消息成功");
					}else{
						log.error("发送短消息失败:"+iRet);
					}
				}else{
					log.error("获取短消息失败:"+iRet);
				}
			}else{
				log.error("登录失败:"+iRet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sdk.Sms_DisConnect(); // 断开与服务器的连接
			sdk = null;
		}
    }
    
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
    	String[] allowedFields = {
    			"pid",
    			"tel",
    			"amount",
    			"posNo"
    	};
    	binder.setAllowedFields(allowedFields);
		super.initBinder(request, binder);
	}
	public void setFiBankbookJournalManager(
			FiBankbookJournalManager fiBankbookJournalManager) {
		this.fiBankbookJournalManager = fiBankbookJournalManager;
	}
}
