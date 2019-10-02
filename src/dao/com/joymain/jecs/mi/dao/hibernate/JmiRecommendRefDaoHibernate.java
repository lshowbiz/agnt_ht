
package com.joymain.jecs.mi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiRecommendRef;
import com.joymain.jecs.mi.dao.JmiRecommendRefDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.data.SpringStoredProcedure;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiRecommendRefDaoHibernate extends BaseDaoHibernate implements JmiRecommendRefDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiRecommendRefDao#getJmiRecommendRefs(com.joymain.jecs.mi.model.JmiRecommendRef)
     */
    public List getJmiRecommendRefs(final JmiRecommendRef jmiRecommendRef) {
        return getHibernateTemplate().find("from JmiRecommendRef");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiRecommendRef == null) {
            return getHibernateTemplate().find("from JmiRecommendRef");
        } else {
            // filter on properties set in the jmiRecommendRef
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiRecommendRef).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiRecommendRef.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiRecommendRefDao#getJmiRecommendRef(String memberNo)
     */
    public JmiRecommendRef getJmiRecommendRef(final String memberNo) {
        JmiRecommendRef jmiRecommendRef = (JmiRecommendRef) getHibernateTemplate().get(JmiRecommendRef.class, memberNo);
//        if (jmiRecommendRef == null) {
//            log.warn("uh oh, jmiRecommendRef with memberNo '" + memberNo + "' not found...");
//            throw new ObjectRetrievalFailureException(JmiRecommendRef.class, memberNo);
//        }

        return jmiRecommendRef;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiRecommendRefDao#saveJmiRecommendRef(JmiRecommendRef jmiRecommendRef)
     */    
    public void saveJmiRecommendRef(final JmiRecommendRef jmiRecommendRef) {
        getHibernateTemplate().saveOrUpdate(jmiRecommendRef);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiRecommendRefDao#removeJmiRecommendRef(String memberNo)
     */
    public void removeJmiRecommendRef(final String memberNo) {
        getHibernateTemplate().delete(getJmiRecommendRef(memberNo));
    }
    //added for getJmiRecommendRefsByCrm
    public List getJmiRecommendRefsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiRecommendRef jmiRecommendRef where 1=1";
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
			hql += " order by memberNo desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
    }
    public JmiRecommendRef getNewMiLinkRefManagersByRecommendNo(JmiRecommendRef jmiRecommendRef) {
		if (jmiRecommendRef == null) {
			return null;
		}
		JmiRecommendRef miNewRecommendRef = new JmiRecommendRef();

		CommonRecord crm = new CommonRecord();
		/*TODO:Alvin 10月23号(用推荐人查下线)
		crm.addField("treeIndex", miRecommendRef.getTreeIndex());
		crm.addField("memberNo", miRecommendRef.getMemberNo());
		crm.addField("layerId", String.valueOf(miRecommendRef.getTreeIndex().length() + 3));
		*/
		crm.addField("recommendNo", jmiRecommendRef.getUserCode());
		List miRecommendRefs = getMiRecommendRefManagersByTree(crm);
		if (miRecommendRefs == null) {
			miNewRecommendRef.setTreeIndex(jmiRecommendRef.getTreeIndex() + "000");
		} else {
			// miNewRecommendRef.setTreeIndex(miRecommendRef.getTreeIndex() +
			// numToString(miRecommendRefs.size()));

			for (int i = 0; i < 46656; i++) {
				String treeIndex = jmiRecommendRef.getTreeIndex() + numToString(i);
				int m = 0;
				for (m = 0; m < miRecommendRefs.size(); m++) {
					JmiRecommendRef mL = (JmiRecommendRef) miRecommendRefs.get(m);
					if (treeIndex.equals(mL.getTreeIndex())) {
						break;
					}
				}
				if (m == miRecommendRefs.size()) {
					miNewRecommendRef.setTreeIndex(treeIndex);
					break;
				}
			}

		}
		miNewRecommendRef.setLayerId(jmiRecommendRef.getLayerId() + 1);
		miNewRecommendRef.setRecommendJmiMember(jmiRecommendRef.getJmiMember());
		return miNewRecommendRef;
	}

	public String numToString(Integer num) {
		String str = "000";
		for (int i = 0; i < num; i++) {
			str = getNextNo(0, str);
		}
		return str;
	}

	public String getNextNo(int sonFlag, String str) {
		str = str.toLowerCase();
		String strNext = "";
		if (sonFlag == 0) {
			String strTemp1 = str.substring(0, str.length() - 3);
			String strTemp2 = str.substring(str.length() - 3, str.length());
			String s1 = strTemp2.substring(0, 1);
			String s2 = strTemp2.substring(1, 2);
			String s3 = strTemp2.substring(2, 3);
			if ("z".equals(s3)) {
				strNext = strTemp1 + s1 + getNextChar(s2) + "0";
				if ("z".equals(s2)) {
					strNext = strTemp1 + getNextChar(s1) + "00";
					if ("z".equals(s1)) {
						strNext = "";
					} else {

						// strNext = strTemp1 + getNextChar(s1) + s2 + s3;
					}
				} else {

					// strNext = strTemp1 + s1 + getNextChar(s2) + s3;
				}
			} else {

				strNext = strTemp1 + s1 + s2 + getNextChar(s3);
			}
		} else {

			strNext = str + "000";
		}
		return strNext;
	}

	String getNextChar(String str) {
		String strNext = "";
		String strArray = "0123456789abcdefghijklmnopqrstuvwxyz";
		if ((strArray.length() - 1 > strArray.indexOf(str)) && (strArray.indexOf(str) >= 0)) {
			strNext = strArray.substring(strArray.indexOf(str) + 1, strArray.indexOf(str) + 2);
		}
		return strNext;
	}
	
	public List getMiRecommendRefManagersByTree(CommonRecord crm) {

		String hqlQuery = "from JmiRecommendRef where 1=1";

		String treeIndex = crm.getString("treeIndex", "");
		if (!StringUtils.isEmpty(treeIndex)) {
			hqlQuery += " and treeIndex like '" + treeIndex + "%'";
		}

		String recommendNo = crm.getString("recommendNo", "");
		if (!StringUtils.isEmpty(recommendNo)) {
			hqlQuery += " and recommendJmiMember.userCode = '" + recommendNo + "'";
		}

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hqlQuery += " and userCode <> '" + userCode + "'";
		}
		
//		===============接点查询
				String treeIndexs = crm.getString("treeIndexs", "");
				String layerIds = crm.getString("layerIds", "");
				if (!StringUtils.isEmpty(treeIndexs)&&!StringUtils.isEmpty(layerIds)) {
					hqlQuery +=" and (treeIndex like '" + treeIndexs + "'";
					String tmphql = " or treeIndex like '" + treeIndexs;
					for(int i=0;i<Integer.parseInt(layerIds);i++){
						tmphql += "___";
						hqlQuery += tmphql + "'";
					}			
					hqlQuery += ")";
				}
//		===============

		String layerId = crm.getString("layerId", "");
		if (!StringUtils.isEmpty(layerId)) {
			hqlQuery += " and layerId<= " + layerId + "";
		}

		// 设置排序
		hqlQuery += " order by layerId,userCode asc";

		List miRecommendRefs = this.getHibernateTemplate().find(hqlQuery);

		return miRecommendRefs;
	}
	/**
	 * 获取推荐人
	 * @param treeIndex
	 * @return
	 */
	public List getRecommendRefbyRecommendNo(String recommendNo){
		return this.getHibernateTemplate().find("from JmiRecommendRef m where m.recommendJmiMember.userCode='" + recommendNo + "' order by  m.jmiMember.notFirst desc ,m.jmiMember.checkDate desc ,m.jmiMember.createTime desc  ");
	}
	/**
	 * 执行存储过程,接点转移
	 * @param memberNo
	 * @param linkNo
	 * @param noCheck
	 * @return
	 */
	public String getCallProcChangeRecommend(final String memberNo, final String recommendNo, final String userCode, final String noCheck){
		SpringStoredProcedure spComp = new SpringStoredProcedure(this.jdbcTemplate, "Pack_Business_Process.Proc_Recommend_Update");

		//注册参数类型,输入参数和输出参数同时注册,否则不能正确编译存储过程
		spComp.setParameter("Vc_Member_No", java.sql.Types.VARCHAR);
		spComp.setParameter("Vc_Recommend_No", java.sql.Types.VARCHAR);
		spComp.setParameter("Vc_Status", java.sql.Types.VARCHAR);
		spComp.setParameter("Vc_Character_Code", java.sql.Types.VARCHAR);

		spComp.setOutParameter("Vc_Errmsg", oracle.jdbc.OracleTypes.VARCHAR);// 输出错误原因
		spComp.compile();

		//传入输入参数值
		Map<String, Object> inComp = new HashMap<String, Object>();
		inComp.put("Vc_Member_No", memberNo);
		inComp.put("Vc_Recommend_No", recommendNo);
		inComp.put("Vc_Status", noCheck);
		inComp.put("Vc_Character_Code", userCode);

		spComp.SetInParam(inComp);
		//执行存储过程ddd
		Map resultComp = spComp.execute();
		String Vc_msg = (String)resultComp.get("Vc_Errmsg");
		return Vc_msg.trim();
	}
	/**
	 * 获取某推荐下的会员
	 * @param linkNo
	 */
	public List getJmiRecommendRefsByRecommendNo(String recommendNo){
		return this.getHibernateTemplate().find("from JmiRecommendRef m where m.recommendJmiMember.userCode='" + recommendNo + "' order by m.treeIndex");
	}
	/**
	 * 获取推荐人数
	 * @param treeIndex
	 * @return
	 */
	public int getRecommendRefCount(String treeIndex){
		String hql = "from JmiRecommendRef where treeIndex like '" + treeIndex +"%'";
		return this.getTotalCountByHQL(hql);
	}
	/**
	 * 获取推荐人数
	 * @param treeIndex
	 * @return
	 */
	public List getRecommendRefByTreeIndex(String treeIndex){
		String hql = "from JmiRecommendRef where treeIndex like '" + treeIndex +"%'";
		return this.getHibernateTemplate().find(hql);
	}

	public List getRecommendRefByUserCodes(String userCodes) {
		String hql = "from JmiRecommendRef where userCode in ("+userCodes+")";
		return this.getHibernateTemplate().find(hql);
	}


	public List getJmiRecommendRefOrder(String treeIndex) {
		List poMemberOrderList=this.findObjectsBySQL("select o.mo_id,o.user_code as user_code,o.pv_amt as pv_amt,o.member_order_no as member_order_no from jpo_member_order o where o.user_code in " +
				"(select r.user_code from jmi_recommend_ref r where r.tree_index like '"+treeIndex+"_________') and o.status='2' and o.order_type in ('1','2') and o.check_date >= to_date('2010-7-10 09:00:00', 'yyyy-mm-dd hh24:mi:ss') and o.check_date < to_date('2010-10-01 16:00:00', 'yyyy-mm-dd hh24:mi:ss')");
		String ids="";
		for (int i = 0; i < poMemberOrderList.size(); i++) {
			if(i>0){
				ids+=",";
			}
			Map map=(Map) poMemberOrderList.get(i);
			ids+=map.get("mo_id");
			
		}
		if(ids!=""){
			List jprRefunds=this.findObjectsBySQL("select f.user_code as user_code,-f.pv_amt as pv_amt,f.ro_no as member_order_no from jpr_refund f where f.mo_id in ("+ids+")");
			poMemberOrderList.addAll(jprRefunds);
		}
		
		return poMemberOrderList;
	}

	public List getMembersByUserCode(String userCode) {
		return this.findObjectsBySQL("select * from jmi_recommend_ref t where t.tree_index like (select tree_index from jmi_recommend_ref where user_code='"+userCode+"') || '%' and t.user_code!='"+userCode+"'");
	}
	
}
