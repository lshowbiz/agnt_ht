package com.joymain.jecs.pd.model;

/**
 * 根据物流单号查询物流实时信息时用到
 * @author gw  2015-06-11
 *
 */
public class MailStatusSend {
	
	private String mailNo;//订单号
	
	public MailStatusSend(){
    }
	
	public MailStatusSend(String mailNo){
        this.mailNo = mailNo;
    }

	public String getMailNo() {
		return mailNo;
	}

	public void setMailNo(String mailNo) {
		this.mailNo = mailNo;
	}

}
