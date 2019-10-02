
package com.joymain.jecs.po.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.model.JpoInviteList;
import com.joymain.jecs.po.dao.JpoInviteListDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class JpoInviteListDaoHibernate extends BaseDaoHibernate implements JpoInviteListDao {

    /**
     * @see com.joymain.jecs.po.dao.JpoInviteListDao#getJpoInviteLists(com.joymain.jecs.po.model.JpoInviteList)
     */
    @Override
	public List getJpoInviteLists(final JpoInviteList jpoInviteList) {
        return getHibernateTemplate().find("from JpoInviteList");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpoInviteList == null) {
            return getHibernateTemplate().find("from JpoInviteList");
        } else {
            // filter on properties set in the jpoInviteList
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpoInviteList).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpoInviteList.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoInviteListDao#getJpoInviteList(Long id)
     */
    @Override
	public JpoInviteList getJpoInviteList(final Long id) {
        JpoInviteList jpoInviteList = (JpoInviteList) getHibernateTemplate().get(JpoInviteList.class, id);
        if (jpoInviteList == null) {
            log.warn("uh oh, jpoInviteList with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JpoInviteList.class, id);
        }

        return jpoInviteList;
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoInviteListDao#saveJpoInviteList(JpoInviteList jpoInviteList)
     */    
    @Override
	public void saveJpoInviteList(final JpoInviteList jpoInviteList) {
        getHibernateTemplate().saveOrUpdate(jpoInviteList);
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoInviteListDao#removeJpoInviteList(Long id)
     */
    @Override
	public void removeJpoInviteList(final Long id) {
        getHibernateTemplate().delete(getJpoInviteList(id));
    }
    //added for getJpoInviteListsByCrm
    @Override
	public List getJpoInviteListsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpoInviteList jpoInviteList where 1=1";
    	
    	String userCode = crm.getString("userCode", "");
    	if(!StringUtil.isEmpty(userCode)){
    		 hql += " and jpoInviteList.userCode = '"+userCode+"' ";
    	}
    	
    	String inviteCode = crm.getString("inviteCode", "");
    	if(!StringUtil.isEmpty(inviteCode)){
    		 hql += " and jpoInviteList.inviteCode like '%"+inviteCode+"%' ";
    	}
    	
    	String memberOrderNo = crm.getString("memberOrderNo", "");
    	if(!StringUtil.isEmpty(memberOrderNo)){
    		 hql += " and jpoInviteList.memberOrderNo = '"+memberOrderNo+"' ";
    	}
    	
    	String createTimeStart = crm.getString("createTimeStart", "");
    	if(!StringUtil.isEmpty(createTimeStart)){
    		 hql += " and jpoInviteList.createTime >= to_date('"+createTimeStart+" 00:00:00','yyyy-mm-dd HH24:MI:SS') ";
    	}
    	
    	String createTimeEnd = crm.getString("createTimeEnd", "");
    	if(!StringUtil.isEmpty(createTimeEnd)){
    		 hql += " and jpoInviteList.createTime <= to_date('"+createTimeEnd+" 23:59:59','yyyy-mm-dd HH24:MI:SS') ";
    	}
    	
    	String status = crm.getString("status", "");
    	if(!StringUtil.isEmpty(status)){
    		hql += " and jpoInviteList.status = '"+status+"' ";
    	}

		if (!pager.getLimit().getSort().isSorted()) {
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    
    /**
     * 根据存储过程获取邀请码
     * @author 2017-5-22 fu 
     * @return
     */
	@Override
	public String getJpoInviteListForProcedure(){
		String sql = " select Fn_Build_JSys_Id('invite_code') as inviteCode from dual ";
		Map map = this.findOneObjectBySQL(sql);
		String inviteCode = (String) map.get("invitecode");
		return inviteCode;
	}
	
	/**
     * 查询会员可用的邀请码个数
     * @author 2017-5-22 fu 
     * @param userCode
     * @return int 
     */
	@Override
	public int getAvailableSum(String userCode){
		int qty = 0;
		String sql = "select count(1) qty from JPO_INVITE_LIST where STATUS=0 and USER_CODE='"+userCode+"'";
		Map map = this.findOneObjectBySQL(sql);
		String qtys = (String) map.get("qty");
		if(!StringUtil.isEmpty(qtys)){
			qty = Integer.parseInt(qtys);
		}
		return qty;
	}
	
	/**
     * 批量将邀请码设置为不可用状态
     * @author 2017-5-22 fu 
     * @param userCode
     * @param qty
     * @param memberOrderNo
     * @param editUserCode
     * @return boolean 
     */
	@Override
	public boolean maintainJpoInviteList(String userCode, int qty,String memberOrderNo,String editUserCode){
		boolean rt = false;
		String hql = "from JpoInviteList jpoInviteList where jpoInviteList.status=0 and jpoInviteList.userCode='"+userCode+"'";
		List list = this.findObjectsByHqlQuery(hql);
		if(null!=list && list.size()>0){
			if(qty<=list.size()){
				for(int i=0;i<qty;i++){
					JpoInviteList jpoInviteList = (JpoInviteList)list.get(i);
					jpoInviteList.setStatus("2");
					jpoInviteList.setEditUserCode(editUserCode);
					jpoInviteList.setEditTime(new Date());
					this.saveJpoInviteList(jpoInviteList);
				}
				rt = true;
			}
		}
		return rt;
	}
	
	@Override
	public List getJpoInviteListByHql(String hql) {
		return this.getHibernateTemplate().find(hql);
	}
	@Override
	public void saveJpoInvLists(List<JpoInviteList> list) {
		for (JpoInviteList jpoInviteList : list) {
			this.saveJpoInviteList(jpoInviteList);
		}
	}
}
