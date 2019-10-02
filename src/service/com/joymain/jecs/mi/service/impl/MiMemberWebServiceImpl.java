package com.joymain.jecs.mi.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.joymain.jecs.Constants;
import com.joymain.jecs.mi.model.JmiCustomerLevelNote;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.MiStatusResponse;
import com.joymain.jecs.mi.service.JmiCustomerLevelNoteManager;
import com.joymain.jecs.mi.service.JmiMemberManager;
import com.joymain.jecs.mi.service.MiMemberWebService;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.util.string.StringUtil;



public class MiMemberWebServiceImpl implements MiMemberWebService
{

    private JmiMemberManager jmiMemberManager;
    private JmiCustomerLevelNoteManager jmiCustomerLevelNoteManager;
    private JpoMemberOrderManager jpoMemberOrderManager;
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
	public void setJmiCustomerLevelNoteManager(
			JmiCustomerLevelNoteManager jmiCustomerLevelNoteManager) {
		this.jmiCustomerLevelNoteManager = jmiCustomerLevelNoteManager;
	}
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}
	public MiStatusResponse getChangeCustomerLevel(String userCode,String customerLevel) {
		/**
		 * statusResponse
		 * 0 成功
		 * 1 失败
		 * 2 编号不存
		 * 3 级别不存在
		 * 
		 */
		String statusResponse="";
		
		MiStatusResponse miMemberResponse=new MiStatusResponse();
		
		
		if(StringUtil.isEmpty(userCode)){
			statusResponse="2";
		}else{
			JmiMember jmiMember=jmiMemberManager.getJmiMember(userCode);
			if(jmiMember==null){
				statusResponse="2";
			}else{
				Map customerLevelMap=this.getListOptions(jmiMember.getCompanyCode(), "customerlevel");
				if(customerLevelMap.get(customerLevel)==null){
					statusResponse="3";
				}else{
					try {
						jmiMember.setCustomerLevel(customerLevel);
						jmiMemberManager.saveJmiMember(jmiMember);
						statusResponse="0";
					} catch (Exception e) {
						statusResponse="1";
					}
				}
			}	
		}
		miMemberResponse.setStatusResponse(statusResponse);
		return miMemberResponse;
	}
	
	
    	private LinkedHashMap<String, String> getListOptions(String companyCode, String listCode) {
    		Set valueSets = Constants.sysListMap.get(listCode).entrySet();
    		LinkedHashMap<String, String> optionMap=new LinkedHashMap<String, String>();
    		if (valueSets != null) {
    			Iterator iter = valueSets.iterator();
    			while (iter.hasNext()) {
    				Map.Entry entry=(Map.Entry)iter.next();
    				String[] values = (String[])entry.getValue();

    				if(StringUtils.contains(values[1],companyCode)){
    					//如果当前用户所属公司在排除公司之内,则不显示
    					continue;
    				}else{
    					optionMap.put(entry.getKey().toString(), values[0]);
    				}
    			}
    		}
    		
    		return optionMap;
    	}
    	/**
    	 * statusResponse 0.成功 1.失败 
    	 * 	remark 内容
    	 */
    	public MiStatusResponse changeCustomerLevelByAmount(String userCode,String amount){

    		String statusResponse="";
    		String remark="";
    		MiStatusResponse miMemberResponse=new MiStatusResponse();
    		if(StringUtil.isEmpty(userCode)){
    			statusResponse="1";
    			remark="会员不存在";
    		}else if(!StringUtil.isDouble(amount)){
    			statusResponse="1";
    			remark="金额不能为空";
    		}else{
    			JmiMember jmiMember=jmiMemberManager.getJmiMember(userCode);
    			if(jmiMember==null){
    				statusResponse="1";
        			remark="会员不存在";
    			}else{
    				
    				String checkTime = jpoMemberOrderManager.getMemberFirstOrderStatusTime(jmiMember.getUserCode());
    	    		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    	    		Date checkDate= null;
    	        	try {
    	        		checkDate = format1.parse(checkTime);
    	    		} catch (ParseException e) {
    	    			e.printStackTrace();
    	    		}
    	    		java.util.Calendar calendar=java.util.Calendar.getInstance();
    	    		calendar.set(2011, 1, 18, 00, 00, 00);

    	    		java.util.Date ccdate=calendar.getTime();
    	    				
    	    		boolean cardTypeError=true;
    	    		//2月18日前的正式会员交1500就给改级别
//    	    		if((checkDate.before(ccdate) && "0".equals(jmiMember.getCardType())) || (checkDate.before(ccdate) && new Double(amount)!=1500d )){
//    	    			cardTypeError=false;
//            			remark="级别错误,加入时间:"+checkTime+" 级别:"+jmiMember.getCardType()+" 应支付1500";
//    	    		}
    	    		//2月18日后的金级会员交3000给改级别
    	    		if((checkDate.after(ccdate) && ("0".equals(jmiMember.getCardType())||"1".equals(jmiMember.getCardType()))) || (checkDate.after(ccdate) && new Double(amount)!=3000d)){
    	    			cardTypeError=false;
            			remark="级别错误,加入时间:"+checkTime+" 级别:"+jmiMember.getCardType()+" 应支付3000";
    	    		}
    				if(cardTypeError){
    					
    					
    					
    					if(StringUtil.formatInt(jmiMember.getCustomerLevel())>=1){
    						statusResponse="1";
                			remark="级别大于升级级别";
    					}else{
    							try {
            						JmiCustomerLevelNote jmiCustomerLevelNote=new JmiCustomerLevelNote();
        							jmiCustomerLevelNote.setUserCode(jmiMember.getUserCode());
        							jmiCustomerLevelNote.setCompanyCode(jmiMember.getCompanyCode());
        							jmiCustomerLevelNote.setCreateNo("");
        							jmiCustomerLevelNote.setCreateTime(new Date());
        							jmiCustomerLevelNote.setNewCustomerLevel("1");
        							jmiCustomerLevelNote.setOldCustomerLevel(jmiMember.getCustomerLevel());
        							jmiCustomerLevelNote.setRemark("接口付款升级，金额:"+amount);
        							jmiCustomerLevelNote.setAmount(new BigDecimal(amount));
        							jmiCustomerLevelNoteManager.saveJmiCustomerLevelNote(jmiCustomerLevelNote);

            						jmiMember.setCustomerLevel("1");
            						jmiMemberManager.saveJmiMember(jmiMember);
            						
        							statusResponse="0";
        						} catch (Exception e) {
        							statusResponse="1";
        							remark=e.getMessage();
        						}
    					}
    				}else{
    					statusResponse="1";
    				}
    			}
    		}
    		miMemberResponse.setStatusResponse(statusResponse);
    		miMemberResponse.setRemark(remark);
    		
    		
    		
    		return miMemberResponse;
    	}
}