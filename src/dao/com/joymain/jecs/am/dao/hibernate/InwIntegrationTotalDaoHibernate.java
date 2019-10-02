
package com.joymain.jecs.am.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.am.model.InwIntegration;
import com.joymain.jecs.am.model.InwIntegrationTotal;
import com.joymain.jecs.am.dao.InwIntegrationDao;
import com.joymain.jecs.am.dao.InwIntegrationTotalDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.orm.ObjectRetrievalFailureException;

public class InwIntegrationTotalDaoHibernate extends BaseDaoHibernate implements InwIntegrationTotalDao {
	
	//创新积分明细的DAO
	private InwIntegrationDao inwIntegrationDao;

    public void setInwIntegrationDao(InwIntegrationDao inwIntegrationDao) {
		this.inwIntegrationDao = inwIntegrationDao;
	}
    

	/**
     * @see com.joymain.jecs.am.dao.InwIntegrationTotalDao#getInwIntegrationTotals(com.joymain.jecs.am.model.InwIntegrationTotal)
     */
    public List getInwIntegrationTotals(final InwIntegrationTotal inwIntegrationTotal) {
        return getHibernateTemplate().find("from InwIntegrationTotal");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (inwIntegrationTotal == null) {
            return getHibernateTemplate().find("from InwIntegrationTotal");
        } else {
            // filter on properties set in the inwIntegrationTotal
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(inwIntegrationTotal).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(InwIntegrationTotal.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.am.dao.InwIntegrationTotalDao#getInwIntegrationTotal(BigDecimal id)
     */
    public InwIntegrationTotal getInwIntegrationTotal(final Long id) {
        InwIntegrationTotal inwIntegrationTotal = (InwIntegrationTotal) getHibernateTemplate().get(InwIntegrationTotal.class, id);
        if (inwIntegrationTotal == null) {
            log.warn("uh oh, inwIntegrationTotal with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(InwIntegrationTotal.class, id);
        }

        return inwIntegrationTotal;
    }

    /**
     * @see com.joymain.jecs.am.dao.InwIntegrationTotalDao#saveInwIntegrationTotal(InwIntegrationTotal inwIntegrationTotal)
     */    
    public void saveInwIntegrationTotal(final InwIntegrationTotal inwIntegrationTotal) {
        getHibernateTemplate().saveOrUpdate(inwIntegrationTotal);
    }

    /**
     * @see com.joymain.jecs.am.dao.InwIntegrationTotalDao#removeInwIntegrationTotal(BigDecimal id)
     */
    public void removeInwIntegrationTotal(final Long id) {
        getHibernateTemplate().delete(getInwIntegrationTotal(id));
    }
    //added for getInwIntegrationTotalsByCrm
    public List getInwIntegrationTotalsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from InwIntegrationTotal inwIntegrationTotal where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}
	 ***/
    	// 
		if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }

    /**
     * 用户减掉积分的操作
     * @author yxzz  2014-06-05
     * @param userCode
     * @param integratotal
     * @param uniqueCode
     * @return boolean
     */
	public boolean minusIntegrationTotal(String userCode, String integratotal,String uniqueCode) {
		
		//首先通过唯一标识,查看同一条请求减积分有没有在数据库中录入了
		boolean checkExist = inwIntegrationDao.getCheckExist(uniqueCode);
		if(checkExist){
				//首先查出用户所拥有的积分总数
				String hql = " from InwIntegrationTotal a where a.userCode='"+userCode+"' ";
				List<InwIntegrationTotal> list =  this.findObjectsByHqlQuery(hql);
				if(null!=list){
					if(list.size()>0){
						InwIntegrationTotal inwIntegrationTotal = list.get(0);
						if(!StringUtil.isEmpty(integratotal)){
							int total = Integer.parseInt(integratotal);
							String totalOriginal = inwIntegrationTotal.getTotalPoints();
								if(!StringUtil.isEmpty(totalOriginal)){
										int totalO = Integer.parseInt(totalOriginal);
										//总积分total0大于减掉的积分total
										if(totalO>total){
											int a = totalO - total;
											String b = String.valueOf(a);
											inwIntegrationTotal.setTotalPoints(b);
											//更改创新积分的总积分
											this.saveInwIntegrationTotal(inwIntegrationTotal);
											//同时,向创新积分的明细里面插入一条数据
											InwIntegration inwIntegration = new InwIntegration();
											inwIntegration.setIntegrationMinus(integratotal);
											inwIntegration.setIntegrationMinusTime(new Date());
											inwIntegration.setOther(uniqueCode);
											inwIntegration.setOperatorUserCode(userCode);
											inwIntegrationDao.saveInwIntegration(inwIntegration);
											//减掉积分成功
											return true;
										}
								}
						}
					}
			    }
				//减掉积分失败
				return false;
		}else{
				//减掉积分失败
				return false;
		}
	}


	/**
     * 建议回复时给于积分对总积分的同步操作
     * @author yxzz  2014-06-05
     * @param userCode
     * @param parseInt
     * @return 
     */
	public void updateOrSaveInwIntegrationTotal(String userCode, int parseInt) {
		//首先查出用户所拥有的积分总数
		String hql = " from InwIntegrationTotal a where a.userCode='"+userCode+"' ";
		List<InwIntegrationTotal> list =  this.findObjectsByHqlQuery(hql);
		
			//创新总积分表里面有会员的总积分,则修改创新总积分
			if(list.size()>0){
				InwIntegrationTotal inwIntegrationTotal = list.get(0);
				String totalPoints = inwIntegrationTotal.getTotalPoints();
				int d = Integer.parseInt(totalPoints)+parseInt;
				//d>0,说明新修改的积分比总积分大,否则的话,就不让同步的修改积分了(因为总积分已经使用了,你再减就没有意义了
				if(d>0){
					inwIntegrationTotal.setTotalPoints(String.valueOf(d));
					this.saveInwIntegrationTotal(inwIntegrationTotal);
				}
			}
			//创新总积分表里面没有会员的总积分,则录入一条新数据
			else{
				InwIntegrationTotal inwIntegrationTotal = new InwIntegrationTotal();
				inwIntegrationTotal.setUserCode(userCode);
				inwIntegrationTotal.setTotalPoints(String.valueOf(parseInt));
				this.saveInwIntegrationTotal(inwIntegrationTotal);
			}
	}
	
}
