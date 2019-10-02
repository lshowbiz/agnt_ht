
package com.joymain.jecs.mi.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.model.JmiLinkRef;
import com.joymain.jecs.mi.dao.JmiLinkRefDao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.data.SpringStoredProcedure;
import com.joymain.jecs.util.string.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

public class JmiLinkRefDaoHibernate extends BaseDaoHibernate implements JmiLinkRefDao {

    /**
     * @see com.joymain.jecs.mi.dao.JmiLinkRefDao#getJmiLinkRefs(com.joymain.jecs.mi.model.JmiLinkRef)
     */
    public List getJmiLinkRefs(final JmiLinkRef jmiLinkRef) {
        return getHibernateTemplate().find("from JmiLinkRef");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jmiLinkRef == null) {
            return getHibernateTemplate().find("from JmiLinkRef");
        } else {
            // filter on properties set in the jmiLinkRef
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jmiLinkRef).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JmiLinkRef.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiLinkRefDao#getJmiLinkRef(String memberNo)
     */
    public JmiLinkRef getJmiLinkRef(final String memberNo) {
        JmiLinkRef jmiLinkRef = (JmiLinkRef) getHibernateTemplate().get(JmiLinkRef.class, memberNo);


        return jmiLinkRef;
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiLinkRefDao#saveJmiLinkRef(JmiLinkRef jmiLinkRef)
     */    
    public void saveJmiLinkRef(final JmiLinkRef jmiLinkRef) {
        getHibernateTemplate().saveOrUpdate(jmiLinkRef);
    }

    /**
     * @see com.joymain.jecs.mi.dao.JmiLinkRefDao#removeJmiLinkRef(String memberNo)
     */
    public void removeJmiLinkRef(final String memberNo) {
        getHibernateTemplate().delete(getJmiLinkRef(memberNo));
    }
    //added for getJmiLinkRefsByCrm
    public List getJmiLinkRefsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JmiLinkRef jmiLinkRef where 1=1";
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
	/**
	 * 根据外部参数获取下一个接点对象
	 * 
	 * @param miLinkRef
	 * @return
	 */
	public JmiLinkRef getNewMiLinkRefManagersByLinkNo(JmiLinkRef miLinkRef, int maxLink) {
		if (miLinkRef == null) {
			return null;
		}
		JmiLinkRef miNewLinkRef = new JmiLinkRef();
		CommonRecord crm = new CommonRecord();
		
		crm.addField("linkNo",miLinkRef.getUserCode());
		List miLinkRefs = getMiLinkRefManagersByTree(crm);
		if (miLinkRefs == null) {
			miNewLinkRef.setTreeIndex(miLinkRef.getTreeIndex() + "00");
		} else if (miLinkRefs.size() >= maxLink) {
			return null;
		} else {
			for (int i = 1; i <= maxLink; i++) {
				String treeIndex = miLinkRef.getTreeIndex() + numToString(i);
				int m = 0;
				for (m = 0; m < miLinkRefs.size(); m++) {
					JmiLinkRef mL = (JmiLinkRef) miLinkRefs.get(m);
					if (treeIndex.equals(mL.getTreeIndex())) {
						break;
					}
				}
				if (m == miLinkRefs.size()) {
					miNewLinkRef.setTreeIndex(treeIndex);
					break;
				}
			}
		}
		if (StringUtil.isEmpty(miNewLinkRef.getTreeIndex())) {
			return null;
		}
		miNewLinkRef.setLayerId(miLinkRef.getLayerId() + 1);
		miNewLinkRef.setLinkJmiMember(miLinkRef.getJmiMember());
		return miNewLinkRef;
	}
	public String numToString(Integer num) {
		String str = "00";
		for (int i = 0; i < num; i++) {
			str = getNextNo(0, str);
		}
		return str;
	}

	public String getNextNo(int sonFlag, String str) {
		str = str.toLowerCase();
		String strNext = "";
		if (sonFlag == 0) {
			String strTemp1 = str.substring(0, str.length() - 2);
			String strTemp2 = str.substring(str.length() - 2, str.length());
			String s1 = strTemp2.substring(0, 1);
			String s2 = strTemp2.substring(1, 2);
			if ("z".equals(s2)) {
				strNext = strTemp1 + getNextChar(s1) + "0";
				if ("z".equals(s1)) {
					strNext = "";
				} else {

					//strNext = strTemp1 + getNextChar(s1) + s2;
				}
			} else {

				strNext = strTemp1 + s1 + getNextChar(s2);
			}
		} else {

			strNext = str + "00";
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
	/**
	 * 根据外部参数分页获取对应的键值列表
	 * 
	 * @param crm
	 * @return
	 */
	public List getMiLinkRefManagersByTree(CommonRecord crm) {
		String hqlQuery = "from JmiLinkRef where 1=1";

		String memberNoIsEqual = crm.getString("memberNoIsEqual", "");
		if ("1".equals(memberNoIsEqual)) {
			String userCode = crm.getString("userCode", "");
			if (!StringUtils.isEmpty(userCode)) {
				hqlQuery += " and userCode = '" + userCode + "'";
			}
		} else {
			String userCode = crm.getString("userCode", "");
			if (!StringUtils.isEmpty(userCode)) {
				hqlQuery += " and userCode <> '" + userCode + "'";
			}
		}
		String linkNo = crm.getString("linkNo", "");
		if (!StringUtils.isEmpty(linkNo)) {
			hqlQuery += " and linkJmiMember.userCode = '" + linkNo + "'";
		}
		
//===============接点查询
		String treeIndexs = crm.getString("treeIndexs", "");
		String layerIds = crm.getString("layerIds", "");
		if (!StringUtils.isEmpty(treeIndexs)&&!StringUtils.isEmpty(layerIds)) {
			hqlQuery +=" and (treeIndex like '" + treeIndexs + "'";
			String tmphql = " or treeIndex like '" + treeIndexs;
			for(int i=0;i<Integer.parseInt(layerIds);i++){
				tmphql += "__";
				hqlQuery += tmphql + "'";
			}			
			hqlQuery += ")";
		}
//===============

		String treeIndex = crm.getString("treeIndex", "");
		if (!StringUtils.isEmpty(treeIndex)) {
			hqlQuery += " and treeIndex like '" + treeIndex + "%'";
		}
		String layerId = crm.getString("layerId", "");
		if (!StringUtils.isEmpty(layerId)) {
			hqlQuery += " and layerId<= " + layerId + "";
		}

		// 设置排序
		if("departmentPv".equals(crm.getString("departmentPv", ""))){
			hqlQuery += " order by departmentPv ";
		}else{
			hqlQuery += " order by layerId,userCode asc";
		}
		List miLinkRefs = this.getHibernateTemplate().find(hqlQuery);

		return miLinkRefs;
	}
	public List getLinkRefbyLinkNoOrderByCreateTime(String linkNo) {
		return this.getHibernateTemplate().find("from JmiLinkRef m where m.linkJmiMember.userCode='" + linkNo + "' order by m.jmiMember.createTime");
	}	
	/**
	 * 获取接点人
	 * @param treeIndex
	 * @return
	 */
	public List getLinkRefbyLinkNo(String linkNo){
		return this.getHibernateTemplate().find("from JmiLinkRef m where m.linkJmiMember.userCode='" + linkNo + "' order by m.userCode");
	}
	
	 /**
	 * 执行存储过程,接点转移
	 * @param memberNo
	 * @param linkNo
	 * @param noCheck
	 * @return
	 */
	public String getCallProcChangeLink(final String memberNo, final String linkNo, final String userCode, final String noCheck) {
		SpringStoredProcedure spComp = new SpringStoredProcedure(this.jdbcTemplate, "Pack_Business_Process.Proc_Link_Update");

		//注册参数类型,输入参数和输出参数同时注册,否则不能正确编译存储过程
		spComp.setParameter("Vc_Member_No", java.sql.Types.VARCHAR);
		spComp.setParameter("Vc_Link_No", java.sql.Types.VARCHAR);
		spComp.setParameter("Vc_Status", java.sql.Types.VARCHAR);
		spComp.setParameter("Vc_Character_Code", java.sql.Types.VARCHAR);

		spComp.setOutParameter("Vc_Errmsg", oracle.jdbc.OracleTypes.VARCHAR);// 输出错误原因
		spComp.compile();

		//传入输入参数值
		Map<String, Object> inComp = new HashMap<String, Object>();
		inComp.put("Vc_Member_No", memberNo);
		inComp.put("Vc_Link_No", linkNo);
		inComp.put("Vc_Status", noCheck);
		inComp.put("Vc_Character_Code", userCode);

		spComp.SetInParam(inComp);
		//执行存储过程
		Map resultComp = spComp.execute();
		String Vc_msg = (String)resultComp.get("Vc_Errmsg");
		return Vc_msg.trim();
	}	
	/**
	 * 获取某接点下的会员
	 * @param linkNo
	 */
	public List getLinkRefsbyLinkNo(String linkNo){
		return this.getHibernateTemplate().find("from JmiLinkRef m where m.linkJmiMember.userCode='" + linkNo + "' order by m.treeIndex");
	}

	public List getLinkRefbyLinkNo(String linkNo,String sortField) {
		return this.getHibernateTemplate().find("from JmiLinkRef m where m.linkJmiMember.userCode='" + linkNo + "' order by "+sortField);
	}

	public List getLinkRefbyLinkNoByWeekGroupPv(String linkNo, String curWeek) {
		return this.getHibernateTemplate().find("select m from JmiLinkRef m,JbdDayBounsCalc d where m.linkJmiMember.userCode=d.userCode and d.wweek="+curWeek+" and  m.linkJmiMember.userCode='" + linkNo + "' order by d.weekGroupPv");
	}
	/**
	 * 获取安置人数
	 * @param treeIndex
	 * @return
	 */
	public int getLinkRefCount(String treeIndex){
		String hql = "from JmiLinkRef where treeIndex like '" + treeIndex +"%'";
		return this.getTotalCountByHQL(hql);
	}
	/**
	 * 获取安置人数
	 * @param treeIndex
	 * @return
	 */
	public List getLinkRefByTreeIndex(String treeIndex){
		String hql = "from JmiLinkRef where treeIndex like '" + treeIndex +"%'";
		return this.getHibernateTemplate().find(hql);
	}
}
