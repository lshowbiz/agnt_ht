
package com.joymain.jecs.mi.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.dao.JmiAssureDao;
import com.joymain.jecs.mi.model.JmiAssure;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class JmiAssureDaoHibernate extends BaseDaoHibernate implements JmiAssureDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiAssureDao#getJmiAssures(com.joymain.jecs.mi.model.JmiAssure)
     */
    public List getJmiAssures(final JmiAssure jmiAssure) {
    	
    	if(jmiAssure == null) {
    		return getHibernateTemplate().find("from JmiAssure");
    	}
    	
        String hql = "from JmiAssure jmiAssure where 1=1";
    	//担保类型
    	String assureType = jmiAssure.getAssureType();
    	if(StringUtils.isNotEmpty(assureType)){
    		hql += " and jmiAssure.assureType='"+assureType.trim()+"' ";
    	}
    	
    	//会员编号
    	String userCode = jmiAssure.getUserCode();
    	if(StringUtils.isNotEmpty(userCode)){
    		hql += " and jmiAssure.userCode='"+userCode.trim()+"' ";
    	}
    	
    	//是否达成担保
    	String isFlag = jmiAssure.getIsFlag();
    	if(StringUtils.isNotEmpty(isFlag)){
    		hql += " and jmiAssure.isFlag='"+isFlag.trim()+"' ";
    	}
    	hql += " order by jmiAssure.createTime desc";
    	return getHibernateTemplate().find(hql);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiAssureDao#getJmiAssure(BigDecimal id)
     */
    public JmiAssure getJmiAssure(final Long id) {
        JmiAssure jmiAssure = (JmiAssure) getHibernateTemplate().get(JmiAssure.class, id);

        return jmiAssure;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiAssureDao#saveJmiAssure(JmiAssure jmiAssure)
     */    
    public void saveJmiAssure(final JmiAssure jmiAssure) {
        getHibernateTemplate().saveOrUpdate(jmiAssure);
    }

    @Override
    public void updateJmiAssure(JmiAssure jmiAssure) {
    	 getHibernateTemplate().saveOrUpdate(jmiAssure);
    }
    /**
     * @see com.joymain.jecs.mi.dao.JmiAssureDao#removeJmiAssure(BigDecimal id)
     */
    public void removeJmiAssure(final Long id) {
    	JmiAssure o =getJmiAssure(id);
    	if(null!=o){
    		getHibernateTemplate().delete(o);
    	}
    }
    //added for getJmiAssuresByCrm
    public List getJmiAssuresByCrm(CommonRecord crm, Pager pager){
    	
    	try {
			String hql = "from JmiAssure jmiAssure where 1=1";
			//担保类型
			String assureType = crm.getString("assureType","");
			if(StringUtils.isNotEmpty(assureType)){
				hql += " and jmiAssure.assureType='"+assureType.trim()+"' ";
			}
			
			//会员编号
			String userCode = crm.getString("userCode","");
			if(StringUtils.isNotEmpty(userCode)){
				hql += " and jmiAssure.userCode='"+userCode.trim()+"' ";
			}
			
			//业务开始时间
			String startTime = crm.getString("startTime", "");
			if (StringUtil.isDate(startTime)) {
				hql += " and jmiAssure.startTime >= to_date('" + startTime
						+ " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
			}
			//业务结束时间
			String endTime = crm.getString("endTime", "");
			if (StringUtil.isDate(endTime)) {
				hql += " and jmiAssure.endTime <= to_date('" + endTime
						+ " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
			}
			
			//是否达成担保
			String isFlag = crm.getString("isFlag","");
			if(StringUtils.isNotEmpty(isFlag)){
				hql += " and jmiAssure.isFlag='"+isFlag.trim()+"' ";
			}
			hql += " order by jmiAssure.createTime desc";
			return this.findObjectsByHqlQuery(hql, pager);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
    }
    
    @Override
    public List getReportByContion(CommonRecord crm) {
    	/*select jm.pet_name,ja.user_code,ja.user_code from jmi_assure ja 
    	left join jmi_member jm on ja.user_code = jm.user_code;*/
    	
    	String sqlQuery = "select jm.pet_name,ja.user_code from jmi_assure ja "+
    					  "left join jmi_member jm on ja.user_code = jm.user_code where 1=1 ";
    	//担保类型
    	String assureType = crm.getString("assureType","");
    	if(StringUtils.isNotEmpty(assureType)){
    		sqlQuery += " and ja.ASSURE_TYPE='"+assureType.trim()+"' ";
    	}
    	
    	//会员编号
    	String userCode = crm.getString("userCode","");
    	if(StringUtils.isNotEmpty(userCode)){
    		sqlQuery += " and ja.USER_CODE='"+userCode.trim()+"' ";
    	}
    	
    	//业务开始时间
    	String startTime = crm.getString("startTime", "");
		if (StringUtil.isDate(startTime)) {
			sqlQuery += " and ja.START_TIME >= to_date('" + startTime
					+ " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		//业务结束时间
		String endTime = crm.getString("endTime", "");
		if (StringUtil.isDate(endTime)) {
			sqlQuery += " and ja.END_TIME <= to_date('" + endTime
					+ " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
		
		sqlQuery += " order by ja.CREATE_TIME desc";
		return this.findObjectsBySQL(sqlQuery);
    }
    
    @Override
    public int removeJmiAssureByIds(String ids) {
    	String sql = "delete from jmi_assure where id in("+ids+")";
    	int result = jdbcTemplate.update(sql);
    	return result;
    }
    @Override
    public JpoMemberOrder getJpoMemberOrderByInterface(String memberOrderNo) {
    	String hql = " from JpoMemberOrder jpoMemberOrder where 1=1 and jpoMemberOrder.status = '2' and jpoMemberOrder.memberOrderNo='"+memberOrderNo+"' ";
		List<JpoMemberOrder> list = this.findObjectsByHqlQuery(hql);
		if(null==list){
			return null;
		}else{
			if(list.size()>0){
				return list.get(0);
			}else{
				return null;
			}
		}
    }
    @Override
    public void saveJmiAssureList(List<JmiAssure> jmiAssureList) throws Exception {
    	getHibernateTemplate().saveOrUpdateAll(jmiAssureList);
    }
}
