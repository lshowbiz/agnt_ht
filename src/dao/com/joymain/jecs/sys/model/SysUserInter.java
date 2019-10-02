package com.joymain.jecs.sys.model;


// Generated 2008-3-8 15:17:35 by Hibernate Tools 3.1.0.beta4

public class SysUserInter extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	private String member_num;
	protected String login_password;
	public String getMember_num() {
		return member_num;
	}
	public void setMember_num(String memberNum) {
		member_num = memberNum;
	}
	public String getLogin_password() {
		return login_password;
	}
	public void setLogin_password(String loginPassword) {
		login_password = loginPassword;
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	
}