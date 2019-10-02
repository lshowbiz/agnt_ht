package com.joymain.jecs.mi.dao.hibernate;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import cn.p.dt.union.DZhongmai;

import com.joymain.jecs.Constants;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.mi.dao.JmiCustomerLevelNoteDao;
import com.joymain.jecs.mi.dao.JmiMemberDao;
import com.joymain.jecs.mi.model.JmiCustomerLevelNote;
import com.joymain.jecs.mi.model.JmiMember;
import com.joymain.jecs.mi.model.JmiMemberUpdate;
import com.joymain.jecs.po.dao.JpoMemberOrderDao;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.data.SpringStoredProcedure;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;

public class JmiMemberDaoHibernate extends BaseDaoHibernate implements
		JmiMemberDao {
	private JmiCustomerLevelNoteDao jmiCustomerLevelNoteDao;
	public void setJmiCustomerLevelNoteDao(
			JmiCustomerLevelNoteDao jmiCustomerLevelNoteDao) {
		this.jmiCustomerLevelNoteDao = jmiCustomerLevelNoteDao;
	}
	public boolean getIsJumper(String userCode) {
		// TODO Auto-generated method stub
		String sql = "select count(user_code) from jmi_mycool_no where user_code='"
				+ userCode + "'";
		int count = jdbcTemplate.queryForInt(sql);
		if (count > 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @see com.joymain.jecs.mi.dao.JmiMemberDao#getJmiMembers(com.joymain.jecs.mi.model.JmiMember)
	 */
	public List getJmiMembers(final JmiMember jmiMember) {
		return getHibernateTemplate().find("from JmiMember");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (jmiMember == null) { return
		 * getHibernateTemplate().find("from JmiMember"); } else { // filter on
		 * properties set in the jmiMember HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(jmiMember).ignoreCase
		 * ().enableLike(MatchMode.ANYWHERE); return
		 * session.createCriteria(JmiMember.class).add(ex).list(); } }; return
		 * (List) getHibernateTemplate().execute(callback); }
		 */
	}
	
	public List findJmiMemberById(String userCode) {
		// TODO Auto-generated method stub
		List<JmiMember> list = null;
		StringBuffer hql = new StringBuffer(200);
		hql.append(" select a.bank,a.bankaddress,a.bankbook,a.bankcard,a.bank_city,a.bank_province,a.remark from JMI_MEMBER a where 1=1 ");

		if (!StringUtils.isEmpty(userCode)) {
			hql.append(" and a.user_code = '" + userCode + "'" );
		}
		
		Session session = super.getSession();
		Query query = session.createSQLQuery(hql.toString());
		if(query.list().isEmpty()){
			list = new ArrayList<JmiMember>();
		}else{
			List<Object[]> lists = query.list();
			list = transJmiMemberByLists(lists);
		}
		if(null!=session){
			releaseSession(session);
		 }
		return list;
	}
	
	private List<JmiMember> transJmiMemberByLists(List<Object[]> lists){
		List<JmiMember> list = new ArrayList<JmiMember>();
		int size = lists.size();
		for(int i = 0; i < size; i++){
			Object[] obj = lists.get(i);
			JmiMember jmiMember = new JmiMember();
			jmiMember.setBank(null!=obj[0]?obj[0].toString():"");
			jmiMember.setBankaddress(null!=obj[1]?obj[1].toString():"");
			jmiMember.setBankbook(null!=obj[2]?obj[2].toString():"");
			jmiMember.setBankcard(null!=obj[3]?obj[3].toString():"");
			jmiMember.setBankCity(null!=obj[4]?obj[4].toString():"");
			jmiMember.setBankProvince(null!=obj[5]?obj[5].toString():"");
			
			list.add(jmiMember);
			jmiMember = null;
		}
		return list;
	}
	

	/**
	 * @see com.joymain.jecs.mi.dao.JmiMemberDao#getJmiMember(String userCode)
	 */
	public JmiMember getJmiMember(final String userCode) {
		JmiMember jmiMember = (JmiMember) getHibernateTemplate().get(
				JmiMember.class, userCode);
		// if (jmiMember == null) {
		// log.warn("uh oh, jmiMember with userCode '" + userCode +
		// "' not found...");
		// throw new ObjectRetrievalFailureException(JmiMember.class, userCode);
		// }

		return jmiMember;
	}

	/**
	 * @see com.joymain.jecs.mi.dao.JmiMemberDao#saveJmiMember(JmiMember
	 *      jmiMember)
	 */
	public void saveJmiMember(final JmiMember jmiMember) {
		getHibernateTemplate().saveOrUpdate(jmiMember);
	}

	/**
	 * @see com.joymain.jecs.mi.dao.JmiMemberDao#removeJmiMember(String
	 *      userCode)
	 */
	public void removeJmiMember(final String userCode) {
		getHibernateTemplate().delete(getJmiMember(userCode));
	}

	// added for getJmiMembersByCrm
	public List getJmiMembersByCrm(CommonRecord crm, Pager pager) {
		String hql = "from JmiMember jmiMember where 1=1";

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and userCode = '" + userCode + "'";
		}
		String petName = crm.getString("petName", "");
		if (!StringUtils.isEmpty(petName)) {
			hql += " and petName = '" + petName + "'";
		}
		String userName = crm.getString("userName", "");
		if (!StringUtils.isEmpty(userName)) {
			hql += " and miUserName = '" + userName + "'";
		}
		String firstName = crm.getString("firstName", "");
		if (!StringUtils.isEmpty(firstName)) {
			hql += " and firstName = '" + firstName + "'";
		}
		String companyCode = crm.getString("companyCode", "");
		if (!StringUtils.isEmpty(companyCode) && !"AA".equals(companyCode)) {
			hql += " and companyCode = '" + companyCode + "'";
		}
		String lastName = crm.getString("lastName", "");
		if (!StringUtils.isEmpty(lastName)) {
			hql += " and lastName = '" + lastName + "'";
		}
		String papernumber = crm.getString("papernumber", "");
		if (!StringUtils.isEmpty(papernumber)) {
			hql += " and papernumber = '" + papernumber + "'";
		}
		String cardType = crm.getString("cardType", "");
		if (!StringUtils.isEmpty(cardType)) {
			hql += " and cardType = '" + cardType + "'";
		}
		String memberLevel = crm.getString("memberLevel", "");
		if (!StringUtils.isEmpty(memberLevel)) {
			hql += " and memberLevel = " + memberLevel ;
		}

		String bankcard = crm.getString("bankcard", "");
		if (!StringUtils.isEmpty(bankcard)) {
			hql += " and bankcard = '" + bankcard + "'";
		}
		String gradeType = crm.getString("gradeType", "");
		if (!StringUtils.isEmpty(gradeType)) {
			hql += " and gradeType = " + gradeType ;
		}
		
		String createNo = crm.getString("createNo", "");
		if (!StringUtils.isEmpty(createNo)) {
			hql += " and createNo = '" + createNo + "'";
		}
		String isstore = crm.getString("isstore", "");
		if (!StringUtils.isEmpty(isstore)) {
			hql += " and isstore = '" + isstore + "'";
		}
		String shopType = crm.getString("shopType", "");
		if (!StringUtils.isEmpty(shopType)) {
			hql += " and shopType = '" + shopType + "'";
		}

		String mobiletele = crm.getString("mobiletele", "");
		if (!StringUtils.isEmpty(mobiletele)) {
			hql += " and mobiletele = '" + mobiletele + "'";
		}
		String spouseIdno = crm.getString("spouseIdno", "");
		if (!StringUtils.isEmpty(spouseIdno)) {
			hql += " and spouseIdno = '" + spouseIdno + "'";
		}
		String linkNo = crm.getString("linkNo", "");
		if (!StringUtils.isEmpty(linkNo)) {
			hql += " and linkNo = '" + linkNo + "'";
		}
		String recommendNo = crm.getString("recommendNo", "");
		if (!StringUtils.isEmpty(recommendNo)) {
			hql += " and recommendNo = '" + recommendNo + "'";
		}
		String checkBDate = crm.getString("checkBDate", "");
		if (StringUtil.isDate(checkBDate)) {
			hql += " and checkDate >= to_date('" + checkBDate
					+ " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		String checkEDate = crm.getString("checkEDate", "");
		if (StringUtil.isDate(checkEDate)) {
			hql += " and checkDate <= to_date('" + checkEDate
					+ " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
		String createBTime = crm.getString("createBTime", "");
		if (StringUtil.isDate(createBTime)) {
			hql += " and createTime >= to_date('" + createBTime
					+ " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		String createETime = crm.getString("createETime", "");
		if (StringUtil.isDate(createETime)) {
			hql += " and createTime <= to_date('" + createETime
					+ " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
		String subStoreStatusNotNull = crm.getString("subStoreStatusNotNull",
				"");
		if ("subStoreStatusNotNull".equals(subStoreStatusNotNull)) {
			hql += " and subStoreStatus is not null";
		}
		String sendStatus = crm.getString("sendStatus", "");
		if (!StringUtils.isEmpty(sendStatus)) {
			hql += " and sysUser.sendStatus = '" + sendStatus + "'";
		}
		String userType = crm.getString("userType", "");
		if (!StringUtils.isEmpty(userType)) {
			hql += " and sysUser.userType = '" + userType + "'";
		}

		// 
		if (!pager.getLimit().getSort().isSorted()) {
			// sort
			hql += " order by userCode desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty()
					+ " " + pager.getLimit().getSort().getSortOrder();
		}
		return this.findObjectsByHqlQuery(hql, pager);
	}

	public List getLinkRefbyLinkNoOrderByCreateTime(String linkNo) {
		return this.getHibernateTemplate().find(
				"from JmiMember m where m.jmiLinkRef.jmiMember.userCode='"
						+ linkNo
						+ "' order by m.jmiLinkRef.jmiMember.createTime");
	}

	public List getJmiMembersByCrm(CommonRecord crm) {
		String hql = "from JmiMember jmiMember where 1=1";

		String linkNo = crm.getString("linkNo", "");
		if (!StringUtils.isEmpty(linkNo)) {
			hql += " and linkNo = '" + linkNo + "'";
		}
		String recommendNo = crm.getString("recommendNo", "");
		if (!StringUtils.isEmpty(recommendNo)) {
			hql += " and recommendNo = '" + recommendNo + "'";
		}
		return this.findObjectsByHqlQuery(hql);
	}

	/**
	 * 执行存储过程,检查体系完整性
	 * 
	 * @param checkType
	 * @return
	 */
	public Map callProcCheckRef(String checkType) {

		SpringStoredProcedure spComp = new SpringStoredProcedure(
				this.jdbcTemplate, "Pack_Business_Process.Proc_Check_Ref");

		// 注册参数类型,输入参数和输出参数同时注册,否则不能正确编译存储过程
		spComp.setParameter("Vc_Check_Type", java.sql.Types.VARCHAR);

		spComp.setOutParameter("Vc_Errmsg", oracle.jdbc.OracleTypes.VARCHAR);// 输出错误原因
		spComp.setOutParameter("Vc_Ref_No_Null",
				oracle.jdbc.OracleTypes.VARCHAR);// 输出错误原因
		spComp.compile();

		// 传入输入参数值
		Map<String, Object> inComp = new HashMap<String, Object>();
		inComp.put("Vc_Check_Type", checkType);

		spComp.SetInParam(inComp);
		// 执行存储过程
		Map resultComp = spComp.execute();
		return resultComp;
	}

	/**
	 * 检查身份证编号是否存在
	 * 
	 * @param miMember
	 *            存在返回true,不存在返回false
	 * @return
	 */
	public boolean getCheckMiMemberIdNoByMiMember(JmiMember miMember) {
		
		
		
		
		
		
		String sqlQuery = "select count(m.papernumber) as idsum from JMi_Member m where upper(m.papernumber)=upper('"
				+ miMember.getPapernumber() + "')";
		if (!StringUtil.isEmpty(miMember.getUserCode())) {
			sqlQuery += " and m.user_code!='" + miMember.getUserCode() + "'";
		}
		sqlQuery += " and m.papertype='"
				+ miMember.getPapertype()
				+ "' and ((m.member_type = '2' and m.exit_date is null) or (m.member_type != '2' or m.member_type is null))";

		
		//先判断身份证是否存在，如果存在再进行下一步判断

		List membeList2 = this.findObjectsBySQL(sqlQuery);
		Map idsum2 = (Map) membeList2.get(0);
		boolean flag=false;
		if (!"0".equals(idsum2.get("idsum").toString())){
			//9.29后财年开始
			java.util.Calendar startc=java.util.Calendar.getInstance();
			startc.set(2012, 8, 29, 00, 00, 00);
			java.util.Date papernumberDate=startc.getTime();
			if(new Date().after(papernumberDate)){
				//判断是否在安置体系下
				
				String linkNo="";
				if(!StringUtil.isEmpty(miMember.getUserCode())){
					linkNo=miMember.getUserCode();
				}else{
					linkNo=miMember.getJmiLinkRef().getLinkJmiMember().getUserCode();
				}
				
				sqlQuery += " and m.user_code not in ( Select user_code From jmi_member Connect By Nocycle Prior User_Code = Link_No " +
						"Start With User_Code = (select user_code from (select a.user_code, a.link_no, a.papernumber, level " +
						"from jmi_member a where upper(a.papernumber) = upper('"+miMember.getPapernumber()+"') start with user_code = '"+
						linkNo+"' " +
						"CONNECT BY prior a.link_no = user_code order by level desc) c where rownum = 1)) ";
			}

			//

			List membeList = this.findObjectsBySQL(sqlQuery);
			Map idsum = (Map) membeList.get(0);

			if ("0".equals(idsum.get("idsum").toString())){
				flag=true;//不存在
			}
			
		}else{
			flag=true;//不存在
		}
	
		
		
		

		String sqlQuery1 = "select count(m.spouse_idno) as idsum from JMi_Member m where upper(m.spouse_idno)=upper('"
				+ miMember.getPapernumber() + "')";
		// if(!StringUtil.isEmpty(miMember.getUserCode())){
		// sqlQuery1 += " and m.user_code!='" + miMember.getUserCode() + "'";
		// }
		List membeList1 = this.findObjectsBySQL(sqlQuery1);
		Map idsum1 = (Map) membeList1.get(0);

		if ("0".equals(idsum1.get("idsum").toString())
				&& flag) {
			return false;
		}
		return true;
	}

	/**
	 * 检查配偶身份证编号是否存在
	 * 
	 * @param miMember
	 *            存在返回true,不存在返回false
	 * @return
	 */
	public boolean getCheckMiMemberSpouseIdNoByMiMember(JmiMember miMember) {
		String sqlQuery = "select count(m.papernumber) as idsum from JMi_Member m where upper(m.papernumber)=upper('"
				+ miMember.getSpouseIdno()
				+ "') and ((m.member_type = '2' and m.exit_date is null) or (m.member_type != '2' or m.member_type is null))";
		// if(!StringUtil.isEmpty(miMember.getUserCode())){
		// sqlQuery += " and m.user_code!='" + miMember.getUserCode() + "'";
		// }
		sqlQuery += " and m.papertype='" + miMember.getPapertype() + "'";
		List membeList = this.findObjectsBySQL(sqlQuery);
		Map idsum = (Map) membeList.get(0);

		String sqlQuery1 = "select count(m.spouse_idno) as idsum from JMi_Member m where upper(m.spouse_idno)=upper('"
				+ miMember.getSpouseIdno() + "')";
		if (!StringUtil.isEmpty(miMember.getUserCode())) {
			sqlQuery1 += " and m.user_code!='" + miMember.getUserCode() + "'";
		}
		List membeList1 = this.findObjectsBySQL(sqlQuery1);
		Map idsum1 = (Map) membeList1.get(0);

		if ("0".equals(idsum1.get("idsum").toString())
				&& "0".equals(idsum.get("idsum").toString())) {
			return false;
		}
		return true;
	}

	public List getMiRecommendRefsCountList(String memberNo, String startTime,
			String endTime) {
		String hqlQuery = "from JmiRecommendRef t where t.recommendJmiMember.userCode='"
				+ memberNo + "'";
		if (StringUtil.isDate(startTime) && StringUtil.isDate(endTime)) {
			hqlQuery = hqlQuery
					+ " and t.jmiMember.create_time>=to_date('"
					+ startTime
					+ " 00:00:00','yyyy-mm-dd hh24:mi:ss') and t.jmiMember.create_time<=to_date('"
					+ endTime + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
		}
		hqlQuery = hqlQuery + " order by t.userCode desc";

		return getHibernateTemplate().find(hqlQuery);

	}

	public List getMiRecommendRefsCounts(CommonRecord crm, Pager pager) {

		String startTime = crm.getString("startTime", "");
		String endTime = crm.getString("endTime", "");
		String auditBDate = crm.getString("auditBDate", "");
		String auditEDate = crm.getString("auditEDate", "");
		String companyCode = crm.getString("companyCode", "");
		String cardType = crm.getString("cardType", "");
		String recomCardType = crm.getString("recomCardType", "");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String recommendCount = crm.getString("recommendCount", "");

		String countHql = " (select count(b.user_code) from jmi_member b,jmi_recommend_ref r where b.user_code = r.user_code and r.recommend_no = a.user_code";
		if (!StringUtil.isEmpty(companyCode)) {
			countHql += " and b.company_code='" + companyCode + "'";
		}
		if (!StringUtil.isEmpty(recomCardType)) {
			countHql += " and b.card_type='" + recomCardType + "'";
		}
		if (StringUtil.isDate(startTime)) {
			try {
				countHql += " and b.create_time >=to_date('"
						+ dateFormat.format(dateFormat.parse(startTime))
						+ " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
			} catch (ParseException e) {
			}
		}
		if (StringUtil.isDate(endTime)) {
			try {
				countHql += " and b.create_time <=to_date('"
						+ dateFormat.format(dateFormat.parse(endTime))
						+ " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
			} catch (ParseException e) {
			}
		}
		if (StringUtil.isDate(auditBDate)) {
			try {
				countHql += " and b.check_date >=to_date('"
						+ dateFormat.format(dateFormat.parse(auditBDate))
						+ " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
			} catch (ParseException e) {
			}
		}
		if (StringUtil.isDate(auditEDate)) {
			try {
				countHql += " and b.check_date <=to_date('"
						+ dateFormat.format(dateFormat.parse(auditEDate))
						+ " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
			} catch (ParseException e) {
			}
		}
		countHql += ") ";

		String sql = "select a.user_code,a.card_type,a.check_date,a.create_time, "
				+ countHql + " as recommendcount  from jmi_member a  ";
		sql += " where 1=1";
		if (!StringUtil.isEmpty(companyCode)) {
			sql += " and a.company_code='" + companyCode + "'";
		}
		// String userName =crm.getString("userName", "");
		// if(!StringUtil.isEmpty(userName)){
		// sql += " and a.mi_user_name='" + userName + "'";
		// }
		String memberNo = crm.getString("memberNo", "");
		if (!StringUtil.isEmpty(memberNo)) {
			sql += " and a.user_code='" + memberNo + "'";
		}

		if (!StringUtil.isEmpty(cardType)) {
			sql += " and a.card_type='" + cardType + "'";
		}

		if (!StringUtil.isEmpty(recommendCount)
				&& StringUtil.isInteger(recommendCount)) {
			sql += " and " + countHql + " >= "
					+ StringUtil.formatInt(recommendCount);
			;
		}

		return this.findObjectsBySQL(sql, pager);
	}

	public Map getMemberInfo(String userCode, String flag) {
		Map ret = new HashMap();
		String sql = "select pet_name,sex,email,papernumber,bank,bankaddress,"
				+ "bankcard,bankbook,phone,faxtele,mobiletele,province,city,address,"
				+ "postalcode,exit_date,isstore,active,mi_user_name,bank_city,bank_province,district  from "
				+ flag + " where user_code='" + userCode + "'";
		List list = this.getJdbcTemplate().queryForList(sql);
		if (!list.isEmpty()) {
			ret = (Map) list.get(0);
		}
		return ret;
	}

	
	public List getJWSMemberInfos(String userCode, String phone,String userName,String member_upgrade_day) {
		// TODO Auto-generated method stub
		String sql_1 = "select user_code,t.first_name ||' '|| t.last_name user_name,t.sex,t.phone,t.mobiletele,t.province,t.city,t.district,t.address,t.papernumber,t.remark,t.active,t.card_type, case when t.check_date is not null then "
				+ "ceil(t.check_date + "+member_upgrade_day+" - sysdate) else ceil(t.create_time + "+member_upgrade_day+" - sysdate) end as dd ,t.check_date as check_date from jmi_member t where 1=1 ";
		if(StringUtils.isNotEmpty(userCode)){
			sql_1 +=" and t.user_code='"+userCode+"'";
		}
		if(StringUtils.isNotEmpty(phone)){
			sql_1 +=" and (t.phone='"+phone+"' or t.mobiletele ='"+phone+"')" ;
		}
		if(StringUtils.isNotEmpty(userName)){
			sql_1 +=" and t.first_name||t.last_name ='"+userName+"'";
		}
		String sql = "select t1.user_code,t1.user_name,t1.sex,t1.phone||'/'||t1.mobiletele phone,state.STATE_PROVINCE_NAME||city.CITY_NAME||t1.address address,t1.papernumber,t1.remark,t1.active,t1.card_type,t1.dd,t1.check_date " +
				"  from ("+sql_1+") t1,jal_state_province state,jal_city city " +
						" where t1.province=state.STATE_PROVINCE_ID and city.CITY_ID=t1.city   ";
		
		return this.getJdbcTemplate().queryForList(sql);
	}

	
	public void sendPcn(JmiMember jmiMember,String operateType,String newCustomerLevel,String remark,SysUser defSysUser,String customerLevelNoteId){
		
		String onOff=(String) Constants.sysConfigMap.get("AA").get("pcn.send.onoff");
		String pcnpwd=(String) Constants.sysConfigMap.get("AA").get("pcn.send.pwd");

		
		try {
			  JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			   org.apache.cxf.endpoint.Client client = dcf.createClient("http://dt.p.cn/union/PartnerRegService.asmx?wsdl");
			   
			   Object[] reply =null;
			if("1".equals(onOff)){//1为开启，0关闭
				if("NewAccount".equals(operateType)){
					   DZhongmai dZhongmai = new DZhongmai();
					   
					   dZhongmai.setUserCode(jmiMember.getUserCode());
					   dZhongmai.setAddress("");
					   dZhongmai.setCity("");
					   dZhongmai.setCreateTime(DateUtil.convertToXMLGregorianCalendar(jmiMember.getCreateTime()));
					   dZhongmai.setCustomerLevel(jmiMember.getCustomerLevel());
					   dZhongmai.setDistrict("");
					   dZhongmai.setEmail("");
					   dZhongmai.setFaxtele("");
					   dZhongmai.setMiUserName("");
					   dZhongmai.setMobiletele("");
					   dZhongmai.setPapernumber("");
					   dZhongmai.setPassword(jmiMember.getSysUser().getPassword());
					   dZhongmai.setPetName("");
					   dZhongmai.setPhone("");
					   dZhongmai.setPostalcode("");
					   dZhongmai.setProvince("");
					   dZhongmai.setSex("");
					   

					   reply = client.invoke("NewAccount", new Object[] {pcnpwd, dZhongmai, ""});
					   
					   Boolean flag=(Boolean) reply[0];
					   String msg= (String) reply[1];
					   
					   if(flag==false||flag==null){
						   jmiMember.getSysUser().setSendStatus("2");
						   jmiMember.getSysUser().setRemark(msg);
					   }else{
						   jmiMember.getSysUser().setSendStatus("");
						   jmiMember.getSysUser().setRemark("");
					   }
				}else if("ModifyPwd".equals(operateType)){
					
					
				}else if("ModifyLevel".equals(operateType)){
					   reply = client.invoke("ModifyLevel", new Object[] {pcnpwd,jmiMember.getUserCode(),StringUtil.formatInt(newCustomerLevel),""});
					   Boolean flag=(Boolean) reply[0];
					   String msg= (String) reply[1];
					   
					this.modifyCustomerLevel(jmiMember, defSysUser, newCustomerLevel, remark, flag, msg,customerLevelNoteId);
					   
					   
//					   if(flag==false){
//						   
//					   }else{
//						   
//					   }
				}
				
				
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			if("NewAccount".equals(operateType)){
				   jmiMember.getSysUser().setSendStatus("2");
				   jmiMember.getSysUser().setRemark(e.getMessage());
			}else if("ModifyPwd".equals(operateType)){
				
			}else if("ModifyLevel".equals(operateType)){
				 this.modifyCustomerLevel(jmiMember, defSysUser, newCustomerLevel, remark, false, e.getMessage(),customerLevelNoteId);
			}
		} 
	
		
		
	   
	   
	   
}

	private void modifyCustomerLevel(JmiMember jmiMember,SysUser defSysUser,String newCustomerLevel,String remark,boolean successFlag,String sendRemark,String customerLevelNoteId){
	
		
		
		
		JmiCustomerLevelNote jmiCustomerLevelNote=new JmiCustomerLevelNote();
		
		if(StringUtil.isEmpty(customerLevelNoteId)){//如果ID为空，即系第一次发送
			jmiCustomerLevelNote.setUserCode(jmiMember.getUserCode());
			jmiCustomerLevelNote.setCompanyCode(jmiMember.getCompanyCode());
			jmiCustomerLevelNote.setCreateNo(defSysUser.getUserCode());
			jmiCustomerLevelNote.setCreateTime(new Date());
			jmiCustomerLevelNote.setNewCustomerLevel(newCustomerLevel);
			jmiCustomerLevelNote.setOldCustomerLevel(jmiMember.getCustomerLevel());
			jmiCustomerLevelNote.setRemark(remark);
			
			//保存会员表
			jmiMember.setCustomerLevel(newCustomerLevel);
			this.saveJmiMember(jmiMember);
			
		}else{//否则只更新记录
			jmiCustomerLevelNote=jmiCustomerLevelNoteDao.getJmiCustomerLevelNote(new Long(customerLevelNoteId));
		}
		
		
		
		if(successFlag){//成功
			jmiCustomerLevelNote.setSendRemark("");
			jmiCustomerLevelNote.setStatus("");
		}else{//失败
			jmiCustomerLevelNote.setStatus("1");
			jmiCustomerLevelNote.setSendRemark(sendRemark);
		}
		
		jmiCustomerLevelNoteDao.saveJmiCustomerLevelNote(jmiCustomerLevelNote);
		
		
		
	}

	public List getTWPromotions(Date calEndDate) {
		String sqlQuery="Select a.User_Code,a.card_type From jmi_member a Where Company_Code = 'TW' And Exists (Select 1 From Jpo_Member_Order b " +
				"Where a.User_Code = b.User_Code And b.Order_Type = '1' And Status = '2' And Check_Date >= To_Date('20110314000000', 'yyyy-mm-dd hh24:mi:ss') " +
				"And Check_Date < To_Date('20111224000000','yyyy-mm-dd hh24:mi:ss') And Mo_Id Not In (Select Mo_Id From Jpr_Refund Where Refund_Status = 2))";
		
		List list=this.findObjectsBySQL(sqlQuery);
		
		List returnList=new ArrayList();
		
		String dateFormat="yyyy-MM-dd HH:mm:ss";
		
		DateFormat format1 = new SimpleDateFormat(dateFormat);
		
		for (int i = 0; i < list.size(); i++) {
			String user_code=(String) ((Map) list.get(i)).get("user_code");
			String card_type=(String) ((Map) list.get(i)).get("card_type");
			
			
			
			
			
			
			//查出会员个人PV，审核日期
			String sqlDatePv=" Select Min(Check_Date) as check_date, Sum(Pv_Amt) as pv_amt From Jpo_Member_Order Where User_Code = '"+user_code+"' " +
					"And Order_Type = '1' And Status = '2'";
    		List sqlDatePvList=this.findObjectsBySQL(sqlDatePv);
    		Map DatePvMap=(Map) sqlDatePvList.get(0);
    		//个人PV
    		BigDecimal pvAmt=new BigDecimal((String)DatePvMap.get("pv_amt"));
    		//审核日期
    		String checkTime=(String)DatePvMap.get("check_date");
    		
    		
    		
    		
    		
    		if(!StringUtil.isEmpty(checkTime)){
    			Date checkDate= null;
            	try {
            		checkDate = format1.parse(checkTime);
        		} catch (ParseException e) {
        			e.printStackTrace();
        		}
    			
        		Date checkDate1 =DateUtil.getDateOffset(checkDate, 5, 60);
        		
        		String repvamtsql="Select nvl(Sum(a.Pv_Amt),0) as repvamt From Jpo_Member_Order a Where Exists (Select 1 From jmi_member b" +
        				" Where a.User_Code = b.User_Code  And b.Recommend_No = '"+user_code+"')  And a.Check_Date >= to_date('"+checkTime+"','yyyy-mm-dd hh24:mi:ss') " +
        				"And a.Check_Date < to_date('"+DateUtil.getDateTime(dateFormat, checkDate1)+"','yyyy-mm-dd hh24:mi:ss') " +
        						"And a.Check_Date < to_date('"+DateUtil.getDateTime(dateFormat, calEndDate)+"','yyyy-mm-dd hh24:mi:ss')" +
        						" And a.Order_Type = '1' And a.Status = '2'";
        		
        		String nCardType="";
        		List sqlrepvamtList=this.findObjectsBySQL(repvamtsql);
        		Map repvamtMap=(Map) sqlrepvamtList.get(0);
        		//推荐PV
        		BigDecimal repvamt=new BigDecimal((String)repvamtMap.get("repvamt"));
        		
        		if(pvAmt.compareTo(new BigDecimal("70")) !=-1 && pvAmt.compareTo(new BigDecimal("350")) ==-1){
        			
        			if(repvamt.compareTo(new BigDecimal("280"))!=-1 && repvamt.compareTo(new BigDecimal("630"))==-1 ){
        				nCardType="2";
        			}else if(repvamt.compareTo(new BigDecimal("630"))!=-1){
        				nCardType="3";
        			}
        		}else if(pvAmt.compareTo(new BigDecimal("350")) !=-1 && pvAmt.compareTo(new BigDecimal("700")) ==-1){
        			if(repvamt.compareTo(new BigDecimal("350"))!=-1 ){
        				nCardType="3";
        			}
        		}else if(pvAmt.compareTo(new BigDecimal("700")) !=-1 && pvAmt.compareTo(new BigDecimal("1400")) ==-1){
        			if(repvamt.compareTo(new BigDecimal("700"))!=-1 && repvamt.compareTo(new BigDecimal("1400"))==-1 ){
        				nCardType="4";
        			}else if(repvamt.compareTo(new BigDecimal("1440"))!=-1){
        				nCardType="6";
        			}
        		}else if(pvAmt.compareTo(new BigDecimal("1440")) !=-1 && pvAmt.compareTo(new BigDecimal("2100")) ==-1){
        			if(repvamt.compareTo(new BigDecimal("700"))!=-1 ){
        				nCardType="6";
        			}
        		}else if(pvAmt.compareTo(new BigDecimal("2100")) !=-1 ){
        				
        		}
        		
        		
        		if(!StringUtil.isEmpty(nCardType)){

        			Map map =new HashMap();
            		map.put("user_code", user_code);
            		map.put("card_type", card_type);
            		map.put("pv_amt", pvAmt);
            		map.put("repvamt", repvamt);
            		map.put("nCardType", nCardType);
            		returnList.add(map);
            	
        		}
    			
    		}
    		
    		
		}
		
		return returnList;
	}
	public boolean getCheckMiMemberMobileByMiMember(JmiMember miMember) {
		String sqlQuery = "select count(m.mobiletele) as mobile from JMi_Member m where m.mobiletele = '"+ miMember.getMobiletele() + "' and m.company_code='"+miMember.getCompanyCode()+"'";
		if (!StringUtil.isEmpty(miMember.getUserCode())) {
			sqlQuery += " and m.user_code!='" + miMember.getUserCode() + "'";
		}
		List membeList = this.findObjectsBySQL(sqlQuery);
		Map idsum = (Map) membeList.get(0);

		if ("0".equals(idsum.get("mobile").toString())) {
			return false;
		}
		return true;
	}
	public Date getDsDate() {
		Map map=this.findOneObjectBySQL("select sysdate from dual");
		return DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",map.get("sysdate").toString());
	}
	public String getPapernumberUserCode(JmiMember miMember){
		
		
		String sql="select user_code from (select a.user_code, a.link_no, a.papernumber, level " +
				"from jmi_member a where upper(a.papernumber) = upper('"+miMember.getPapernumber()+"') " +
				"start with user_code = '"+miMember.getJmiLinkRef().getLinkJmiMember().getUserCode()+"' CONNECT BY prior a.link_no = user_code order by level desc) c where rownum = 1";
		
		
		
		
		Map map= this.findOneObjectBySQL(sql);
		if(map!=null&&!map.isEmpty()){
			return map.get("user_code").toString();
		}else{
			return "";
		}
	}
	public String getPapernumberUserCode(String papernumber,String linkNo){
		
		
		String sql="select user_code from (select a.user_code, a.link_no, a.papernumber, level " +
				"from jmi_member a where upper(a.papernumber) = upper('"+papernumber+"') " +
				"start with user_code = '"+ linkNo+"' CONNECT BY prior a.link_no = user_code order by level desc) c where rownum = 1";
		
		
		
		
		Map map= this.findOneObjectBySQL(sql);
		if(map!=null&&!map.isEmpty()){
			return map.get("user_code").toString();
		}else{
			return "";
		}
	}
	public boolean getCheckMiMemberIdNoByMiMemberExist(JmiMember miMember){

		String sqlQuery = "select count(m.papernumber) as idsum from JMi_Member m where upper(m.papernumber)=upper('"
				+ miMember.getPapernumber() + "')";
		if (!StringUtil.isEmpty(miMember.getUserCode())) {
			sqlQuery += " and m.user_code!='" + miMember.getUserCode() + "'";
		}
		sqlQuery += " and m.papertype='"
				+ miMember.getPapertype()
				+ "' and ((m.member_type = '2' and m.exit_date is null) or (m.member_type != '2' or m.member_type is null))";

		//先判断身份证是否存在，如果存在再进行下一步判断

		List membeList2 = this.findObjectsBySQL(sqlQuery);
		Map idsum2 = (Map) membeList2.get(0);

		if ("0".equals(idsum2.get("idsum").toString())){
			return false;
		}
		return true;
	}
	public Map getZcwMemberByUserCode(String userCode) {
//		return this.findOneObjectBySQL("select * from jpo_zcw_member where user_code='"+userCode+"'");
		//Modify By WuCF 20140709 绑定变量
		Map<String,String> map = null;
		StringBuffer sqlBuffer = new StringBuffer("select * from jpo_zcw_member where user_code=? ");
		StringBuffer paramsBuf = new StringBuffer(","+userCode);
		//返回时调用分页的查询的方法
		Object[] parameters = null;
		if(paramsBuf.toString().length()>=1){
	    	parameters = paramsBuf.toString().substring(1).split(",");
		} 
		List list = this.getJdbcTemplate().queryForList(sqlBuffer.toString(), parameters); 
	    if(list!=null && list.size()>=1){
	    	map = (Map<String, String>) list.get(0);
	    }		
		return map;
	}
	public List getJmiTeamType() {
		return this.findObjectsBySQL("select * from jmi_team_type");
	}
	public List getjbdUserStateList(CommonRecord crm, Pager pager) {
		String hql = "select a.*,b.mi_user_name from jbd_User_State_List a left join jmi_member b on a.user_code=b.user_code  where 1=1";

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and a.user_code = '" + userCode + "'";
		}
		
		String miUserName = crm.getString("miUserName", "");
		if (!StringUtils.isEmpty(miUserName)) {
			hql += " and b.mi_user_name = '" + miUserName + "'";
		}
		String papernumber = crm.getString("papernumber", "");
		if (!StringUtils.isEmpty(papernumber)) {
			hql += " and a.papernumber = '" + papernumber + "'";
		}

		String createBTime = crm.getString("createBTime", "");
		if (StringUtil.isDate(createBTime)) {
			hql += " and a.update_time >= to_date('" + createBTime
					+ " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		String createETime = crm.getString("createETime", "");
		if (StringUtil.isDate(createETime)) {
			hql += " and a.update_time <= to_date('" + createETime
					+ " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
		return this.findObjectsBySQL(hql, pager);
	}
	@Override
	public List getJmiClubs(CommonRecord crm, Pager pager) {
		
		String sql="select * from jlb_member where status='1' ";
		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			sql += " and user_code = '" + userCode + "'";
		}
		
		return this.findObjectsBySQL(sql, pager);
	}

	public List getjbdUserDelList(CommonRecord crm, Pager pager) {
		String hql = "select a.*,b.mi_user_name from jbd_user_del_list a, jmi_member  b  where a.user_code=b.user_code  ";

		String userCode = crm.getString("userCode", "");
		if (!StringUtils.isEmpty(userCode)) {
			hql += " and a.user_code = '" + userCode + "'";
		}
		String miUserName = crm.getString("miUserName", "");
		if (!StringUtils.isEmpty(miUserName)) {
			hql += " and b.mi_user_name = '" + miUserName + "'";
		}

		String papernumber = crm.getString("papernumber", "");
		if (!StringUtils.isEmpty(papernumber)) {
			hql += " and a.papernumber = '" + papernumber + "'";
		}
		
		String createBTime = crm.getString("createBTime", "");
		if (StringUtil.isDate(createBTime)) {
			hql += " and a.create_time >= to_date('" + createBTime
					+ " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
		}
		String createETime = crm.getString("createETime", "");
		if (StringUtil.isDate(createETime)) {
			hql += " and a.create_time <= to_date('" + createETime
					+ " 23:59:59','yyyy-MM-dd hh24:mi:ss')";
		}
		return this.findObjectsBySQL(hql, pager);
	}
	public List getJmiMemberUpdate(){
		String sql="select a.id, b.user_code,b.member_level,b.first_name,b.last_name,b.spouse_name,b.spouse_idno,b.pet_name,"
				+ "b.sex,b.email,b.isstore,b.papertype,b.papernumber,b.phone,b.mobiletele,b.province,b.city,b.district,b.address,b.postalcode,"
				+ "b.check_no,b.check_date ,b.create_no,b.create_time,b.remark,b.bank,b.bankcard,b.bankbook,b.start_week,b.valid_week,b.freeze_status,"
				+ "b.title_remark,b.exit_date,b.active,b.member_type ,b.faxtele,b.mi_user_name from jmi_member_update a ,jmi_member b where a.user_code=b.user_code and rownum <=5000";
		return this.findObjectsBySQLTemplateReport(sql);
	}
	@Override
	public void saveJmiMemberUpdate(JmiMemberUpdate jmiMemberUpdate) {
		this.getHibernateTemplate().saveOrUpdate(jmiMemberUpdate);
		
	}
	@Override
	public String getCheckUserCodeCloudshop(String userCode) {
		String sql="select CLOUDSHOP_PHONE from JMI_MEMBER WHERE USER_CODE='"+userCode+"' and IS_CLOUDSHOP='1'";
		List findObjectsBySQLTemplateReport = this.findObjectsBySQLTemplateReport(sql);
		if (findObjectsBySQLTemplateReport.size()==0||findObjectsBySQLTemplateReport.size()>1) {
			return null;
		}
		String object = String.valueOf(findObjectsBySQLTemplateReport.get(0));
		return object.substring(17,object.length()-1);
	}
	@Override
	public String getCheckUserCodeCloudshoPhone(String cloudShopPhone,String userCode) {
		String sql="select CLOUDSHOP_PHONE from JMI_MEMBER WHERE CLOUDSHOP_PHONE='"+cloudShopPhone+"'";
		List findObjectsBySQLTemplateReport = this.findObjectsBySQLTemplateReport(sql);
		if (findObjectsBySQLTemplateReport.size()==0) {
			/*String sqlTemp="select CLOUDSHOP_PHONE from JMI_MEMBER WHERE MOBILETELE='"+cloudShopPhone+"' and USER_CODE ='"+userCode+"'";
			List find = this.findObjectsBySQLTemplateReport(sqlTemp);
			if (find.size()==1) {
				return "success";
			}else{
				return "error_2";
			}*/
			return "success";
		}else{
			return "error";
		}
	}
	
	@Override
	public void updateJmiMemberCloudshopPhone(JmiMember jmiMember) {
		JmiMember member = this.getJmiMember(jmiMember.getUserCode());
		String cloudshopPhone = jmiMember.getCloudshopPhone();
		member.setCloudshopPhone(cloudshopPhone);
		this.getHibernateTemplate().saveOrUpdate(member);
	}
	
}
