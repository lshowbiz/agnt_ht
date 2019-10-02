package com.joymain.jecs.pd.dao.hibernate;

import java.util.List;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.pd.dao.PdJpReportDao;

public class PdJpReportHibernate extends BaseDaoHibernate implements PdJpReportDao  {
	private final String publicSql = 
		" From Jmi_Member Jm,                              "
		+ " Jpo_Member_Order Jmo,                           "
		+ " Jpo_Member_Order_list jmol,                     "
		+ " jpm_product_sale_new Jps,                           "
		+ " Jpo_Member_Order_Fee Jmof,                       "
		+ " Jal_City Jc,                                    "
		+ " jal_state_province jsp                           "
		+ " Where Jm.User_Code = Jmo.User_Code               "
		+ " And Jmo.Mo_Id = Jmol.Mo_Id                      "
		+ " And Jmol.Product_Id = Jps.Uni_No                "
		+ " And Jmo.Mo_Id = Jmof.Mo_Id                       "
		+ " And Jmo.Province = to_char(Jsp.State_Province_Id)"
		+ " And Jmo.City = to_char(Jc.City_Id)               "
		+ " And Jsp.State_Province_Id = Jc.State_Province_Id "
		+ " and Jmo.status = '2'"
		+ " and Jmo.company_code = 'JP'";
	
	/**
	 * 包含订单，会员，费用等信息
	 */
	public List getJpReport(String sOrderTime, String eOrderTime) {
		String sql = 
		" Select Distinct to_char(Jm.Create_Time, 'yyyy-mm-dd') as create_time, " +
		" Jm.User_Code, Jm.Last_Name, Jm.First_Name, Jm.Postalcode, jm.bankcard, jm.bankaddress,"
		+ " Jm.Building, Jm.Phone, Jm.Email, Jm.VILLAGE_ADDR, Jmo.Postalcode as jmopost,                         "
		+ " Jmo.Member_Order_No, Jmo.Mo_Id, to_char(jmo.remark) as jmoremark, "
		+ "  Jmo.Amount, Jmo.Order_Type, " +
				" to_char(Jmo.Order_Time, 'yyyy-mm-dd') as order_time, Jmo.Pay_Mode, Jmo.Address,  " +
				" to_char(Sysdate, 'yyyy-mm-dd') as sysdatea,         "
		+ "  Jps.Product_No, Jps.Product_Name,                                                        "
		+ "  Jmof.Fee, Jmof.Fee_Type,                                                                 "
		+ "  Jmol.Qty, Jmol.Price, (Jmol.Qty * Jmol.Price) as totalPrice,                            "
		+ "  jsp.state_province_name, jc.city_name                                                    "
		+ publicSql;
				
		if (!sOrderTime.equals("")) {
			sql += " And Jmo.Order_Time >= To_Date('" + sOrderTime + "', 'yyyy-mm-dd HH24:mi:ss')";
		} 
		if (!eOrderTime.equals("")) {
			sql += " and Jmo.Order_Time < to_date('" + eOrderTime + "', 'yyyy-mm-dd HH24:mi:ss') + 1";
		} 
		sql += " order by jmo.Member_Order_No";
//		log.debug("getJpReport-----------:"+sql);
		return this.findObjectsBySQL(sql);
	}
	
	/**
	 * 包含会员“首次登录日”，“会员编码”
	 */
	public List getTimeList(String sOrderTime, String eOrderTime) {
		String sql = 
			" Select Distinct Jll.User_Code,                                           "
			+ " To_Char(Min(Jll.Operate_Time), 'yyyy-mm-dd hh24:mi:ss') as Firstlogintime"
			+ " From  Jsys_Login_Log Jll, Jmi_Member Jm                                  "
			+ " Where                                                                    "
			+ " Jll.User_Code = Jm.User_Code                                             "
			+ " And                                                                      "
			+ " Jll.User_Code In(                                                        "
			+ " Select Distinct Jm.User_Code                                             "
			+ publicSql;
		if (!sOrderTime.equals("")) {
			sql += " And Jmo.Order_Time >= To_Date('" + sOrderTime + "', 'yyyy-mm-dd')";
		} 
		if (!eOrderTime.equals("")) {
			sql += " and Jmo.Order_Time <= to_date('" + eOrderTime + "', 'yyyy-mm-dd')";
		} 
		sql += " )  GROUP BY Jll.User_Code";
//		log.info("getTimeList-----------:"+sql);
		return this.findObjectsBySQL(sql);
	}
	/**
	 * 包含会员“会员id”，“各种费用”
	 */
	public List getFeeList(String sOrderTime, String eOrderTime) {
		String sql = 
			" Select distinct Jmof.Mo_Id, Jmof.Fee, Jmof.Fee_Type    "
			+ "  From  Jpo_Member_Order_Fee Jmof, Jpo_Member_Order Jmo "
			+ "  Where Jmof.Mo_Id = Jmo.Mo_Id                          "
			+ "  and jmof.mo_id in (                                   "
			+ " Select Distinct Jmo.mo_id                              "
			+ publicSql;
		if (!sOrderTime.equals("")) {
			sql += " And Jmo.Order_Time >= To_Date('" + sOrderTime + "', 'yyyy-mm-dd')";
		} 
		if (!eOrderTime.equals("")) {
			sql += " and Jmo.Order_Time <= to_date('" + eOrderTime + "', 'yyyy-mm-dd')";
		} 
		sql += " )";
//		log.info("getFeeList-----------:"+sql);
		return this.findObjectsBySQL(sql);
	}
	
	/**
	 * 菲律宾报表
	 * 查找pd_send_info表中包含特定三种类型商品的发货单数量
	 */
	public List getPhReport(String sCreateTime, String eCreateTime) {
		String sql = 
			  " Select distinct Psi.Si_No, Psi.Order_No, Psi.Create_Usr_Code,                      "
			+ " Psi.Create_Time, Psi.Custom_Code, Psi.Recipient_Name,                              "
			+ " nvl(psi.ship_type, 0) ship_type                                                    "
			+ " from pd_send_info psi                                                              "
			+ " where                                                                              "
			+ " psi.si_no in (                                                                 "
			+ " Select distinct Psid.Si_No From Pd_Send_Info_Detail Psid                           "
			+ " Where Psid.Product_No In ('P08060400101EN0', 'P08060500101EN0', 'P05010200101EN0'))"
			+ " and psi.country_code = 'PH'";
				
		if (!sCreateTime.equals("")) {
			sql += " And Psi.Create_Time >= To_Date('" + sCreateTime + "', 'yyyy-mm-dd')";
		} 
		if (!eCreateTime.equals("")) {
			sql += " and Psi.Create_Time <= to_date('" + eCreateTime + "', 'yyyy-mm-dd')";
		} 
		sql += " Order By Psi.Order_No Asc";
//		log.debug("getPhReport-----------:" + sql);
		return this.findObjectsBySQL(sql);
	}
	/**
	 * 菲律宾报表
	 * 查找pd_send_info_detail表中包含特定三种类型商品的发货单号，产品编号，产品数量
	 */
	public List getProNo(String sCreateTime, String eCreateTime) {
		String sql = 
			  " select distinct psid.si_no, psid.product_no, psid.qty from pd_send_info_detail psid"
			+ " where psid.si_no in (                                                              "
			+ " select distinct psi.si_no from pd_send_info psi where 1 = 1";
		if (!sCreateTime.equals("")) {
			sql += " And Psi.Create_Time >= To_Date('" + sCreateTime + "', 'yyyy-mm-dd')";
		} 
		if (!eCreateTime.equals("")) {
			sql += " and Psi.Create_Time <= to_date('" + eCreateTime + "', 'yyyy-mm-dd')";
		} 
		sql += " ) and  psid.product_no in ('P08060400101EN0', 'P08060500101EN0', 'P05010200101EN0')  "; 
		sql += " Order By Psid.si_no Asc";
//		log.debug("getProNo-----------:" + sql);
		return this.findObjectsBySQL(sql);
	}
}
