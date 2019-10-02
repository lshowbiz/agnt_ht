package com.joymain.jecs.po.webapp.action;

import java.util.Locale;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
import com.joymain.jecs.webapp.action.BaseFormController;
import com.joymain.jecs.webapp.util.LocaleUtil;
import com.joymain.jecs.webapp.util.SessionLogin;
import com.joymain.jecs.fi.model.FiBankbookBalance;
import com.joymain.jecs.fi.model.JfiBankbookBalance;
import com.joymain.jecs.fi.service.FiBankbookBalanceManager;
import com.joymain.jecs.fi.service.JfiBankbookBalanceManager;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.service.JmiRecommendRefManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

public class JpoMemberOrderPayOtherFormController extends BaseFormController {
    private JpoMemberOrderManager jpoMemberOrderManager = null;
    private JmiRecommendRefManager jmiRecommendRefManager;
    private FiBankbookBalanceManager fiBankbookBalanceManager = null;
    private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
    public void setJmiRecommendRefManager(
			JmiRecommendRefManager jmiRecommendRefManager) {
		this.jmiRecommendRefManager = jmiRecommendRefManager;
	}
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
        this.jpoMemberOrderManager = jpoMemberOrderManager;
    }
    public JpoMemberOrderPayOtherFormController() {
        setCommandName("jpoMemberOrder");
        setCommandClass(JpoMemberOrder.class);
    }

	@Override
	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception {

		String memberOrderNo = request.getParameter("memberOrderNo");
		String userCode = request.getParameter("userCode");
		String stepNext = request.getParameter("stepNext");
		
		JpoMemberOrder jpoMemberOrder = null;
		if (!StringUtils.isEmpty(memberOrderNo)) {
		     jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(memberOrderNo);
		}
		if("stepNext".equals(stepNext)){
			if(!StringUtil.isEmpty(userCode)&&!checkUnderMember(request, userCode)){
				response.sendRedirect("editJpoMemberOrderPayOther.html");
				this.saveMessage(request, getText("会员不存在或不在推荐网络下"));
				return null;
			}
			if( StringUtils.isEmpty(memberOrderNo)||jpoMemberOrder==null||"2".equals(jpoMemberOrder.getStatus())||!jpoMemberOrder.getSysUser().getUserCode().equals(userCode) || "30".equals(jpoMemberOrder.getOrderType()) || "93".equals(jpoMemberOrder.getOrderType())){
				response.sendRedirect("editJpoMemberOrderPayOther.html");
				this.saveMessage(request, getText("error.poMemberOrder.null"));
					return null;
			}else{
		    		SysUser defSysUser=SessionLogin.getLoginUser(request);
					request.setAttribute("viewOrder", "viewOrder");
			        
			        FiBankbookBalance fiBankbookBalance = fiBankbookBalanceManager.getFiBankbookBalance(defSysUser.getUserCode(),"1");
			        request.setAttribute("coin", fiBankbookBalance.getBalance());
			        
			        JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(defSysUser.getUserCode());
			        request.setAttribute("bankbook", jfiBankbookBalance.getBalance());
			}
		}
	
		
		
		

	        
        
		return super.showForm(request, response, errors);
	}
	
    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String memberOrderNo = request.getParameter("memberOrderNo");
        JpoMemberOrder jpoMemberOrder = null;

        if (!StringUtils.isEmpty(memberOrderNo)) {
            jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(memberOrderNo);
        } 
        if(jpoMemberOrder==null) {
        	jpoMemberOrder = new JpoMemberOrder();
        	SysUser sysUser=new SysUser();
        	jpoMemberOrder.setSysUser(sysUser);
        }

        return jpoMemberOrder;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("entering 'onSubmit' method...");
        }

        SysUser operatorSysUser = SessionLogin.getOperatorUser(request);
    	SysUser defSysUser=SessionLogin.getLoginUser(request);
		String memberOrderNo = request.getParameter("memberOrderNo");
		
        JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(memberOrderNo);
        
        

		String userCode = request.getParameter("userCode");
		if(!StringUtil.isEmpty(userCode)&&!checkUnderMember(request, userCode)){
			this.saveMessage(request, getText("poOrder.editedFail"));
	        return new ModelAndView("redirect:editJpoMemberOrderPayOther.html");
		}
		
		if(StringUtils.isEmpty(memberOrderNo) || "30".equals(jpoMemberOrder.getOrderType())||jpoMemberOrder==null|| !defSysUser.getCompanyCode().equals(jpoMemberOrder.getCompanyCode())  ||"2".equals(jpoMemberOrder.getStatus())||!jpoMemberOrder.getSysUser().getUserCode().equals(userCode)){
        	this.saveMessage(request, getText("poOrder.editedFail"));
	        return new ModelAndView("redirect:editJpoMemberOrderPayOther.html");
		}

		String password = request.getParameter("password");
		String passwordMd5 = StringUtil.encodePassword(password, "md5");
		if(!passwordMd5.equals(defSysUser.getPassword2())){
    		saveMessage(request, LocaleUtil.getLocalText(defSysUser.getDefCharacterCoding(),"fiMoney.fail.password"));
    		return new ModelAndView("redirect:editJpoMemberOrderPayOther.html");
		}
        
    	try{
			jpoMemberOrder.setUserSpCode(defSysUser.getUserCode());
        	BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        	if(amount.compareTo(new BigDecimal("0"))==1){
        		jpoMemberOrder.setPayByJJ("1");
            	if(amount.compareTo(jpoMemberOrder.getAmount())!=-1){
            		jpoMemberOrder.setJjAmount(jpoMemberOrder.getAmount());
            		jpoMemberOrder.setAmount(new BigDecimal("0"));
            	}else{
            		jpoMemberOrder.setJjAmount(amount);
            		jpoMemberOrder.setAmount(jpoMemberOrder.getAmount().subtract(amount));
            	}
    			jpoMemberOrderManager.checkJpoMemberOrderByJJ(jpoMemberOrder, operatorSysUser,"1");
        	}else if(amount.compareTo(new BigDecimal("0"))==-1){
        		this.saveMessage(request, getText("poOrder.editedFail"));
    	        return new ModelAndView("redirect:editJpoMemberOrderPayOther.html");
        	}else{
        		jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrder, operatorSysUser,"1");
        	}
			jpoMemberOrderManager.sendJmsCoin(jpoMemberOrder, operatorSysUser);
        	this.saveMessage(request, getText("poOrder.editedSuc"));
		}catch (AppException app) {
			app.printStackTrace();
			this.saveMessage(request, LocaleUtil.getLocalText("poMemberOrder.memberOrderNo")+ ":"+ jpoMemberOrder.getMemberOrderNo()+ LocaleUtil.getLocalText("poOrder.editedFail")+ "," + app.getMessage());
		} catch(Exception e){
			e.printStackTrace();
        	this.saveMessage(request, LocaleUtil.getLocalText("checkError.Code.111")+ ":"+ jpoMemberOrder.getMemberOrderNo()+ LocaleUtil.getLocalText("poOrder.editedFail"));
		}

        return new ModelAndView("redirect:editJpoMemberOrderPayOther.html");
    }
    protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		// TODO Auto-generated method stub
    	String[] allowedFields = {"asdfgrtgbghg"};
				binder.setAllowedFields(allowedFields);
		//		binder.setDisallowedFields(disallowedFields);
		//		binder.setRequiredFields(requiredFields);
		super.initBinder(request, binder);
	}
    
    private boolean checkUnderMember(HttpServletRequest request,String userCode){
    	SysUser defSysUser=SessionLogin.getLoginUser(request);
		JmiRecommendRef defRecommendRef = jmiRecommendRefManager.getJmiRecommendRef(defSysUser.getUserCode());
		
		JmiRecommendRef miRecommendRef = jmiRecommendRefManager.getJmiRecommendRef(userCode);
		
		if(defRecommendRef==null || miRecommendRef==null){
			return false;
		}
		
		//判断推荐人是否在当前会员下
		String rdefIndex=defRecommendRef.getTreeIndex();
		String rIndex=miRecommendRef.getTreeIndex();
		if(rIndex.length()<rdefIndex.length()|| !rdefIndex.equals(StringUtil.getLeft(rIndex, rdefIndex.length()))){
			return false;
		}
		return true;
    }
	public void setFiBankbookBalanceManager(
			FiBankbookBalanceManager fiBankbookBalanceManager) {
		this.fiBankbookBalanceManager = fiBankbookBalanceManager;
	}
	public void setJfiBankbookBalanceManager(
			JfiBankbookBalanceManager jfiBankbookBalanceManager) {
		this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
	}
}
