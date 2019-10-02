package com.joymain.jecs.mi.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.mi.model.JmiRemitSale;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface JmiRemitSaleDao extends Dao{
	
    public int saveJmiRemitSale(JmiRemitSale jmiRemitSale) throws SQLException ;
    
    public void deleJmiRemitSaleByuserCode(String userCode) throws SQLException;
    
    public int deleJmiRemitSaleById(String id) throws SQLException;   
    
    public JmiRemitSale findJmiRemitSaleById(BigDecimal id) throws SQLException ;
    public boolean findJmiRemitSaleById(BigDecimal id,String bool) throws SQLException ;
    public JmiRemitSale findJmiRemitSaleByUserCode(String userCode)throws SQLException;   
    public boolean findJmiRemitSaleByUserCode(String userCode,String bool)throws SQLException;   
    public int updateJmiRemitSaleById(BigDecimal oldjmiRemitSaleId,JmiRemitSale newjmiRemitSale)throws SQLException;
    public void updateJmiRemitSaleByUserCode(String oldUserCode,JmiRemitSale newjmiRemitSale)throws SQLException;

	public int  updateEndWeek(int bdPeriod,String id);
    public List getJmiMemberTeamsByCrm(CommonRecord crm,Pager pager)throws SQLException;
    public List getJmiMemberTeamsByCrm(CommonRecord crm)throws SQLException;

     public int updateRemarkById(BigDecimal id,String remark);	
    public List<Map<String, Object>> ajaxStarEndtWeek(String userCode) ;
    public List<Map<String, Object>> ajaxStarEndtWeek(String userCode,String notIncludeID) ;

    public List<Map<String,Object>> ajaxBdPeriodBonus(String bonus,int year);
    public List<Map<String, Object>> ajaxVerifyJMIMEMBER(String userCode) ;
    public List query();

}
