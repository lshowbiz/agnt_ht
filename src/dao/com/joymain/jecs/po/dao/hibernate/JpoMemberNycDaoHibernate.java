
package com.joymain.jecs.po.dao.hibernate;

import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.po.dao.JpoMemberNycDao;
import com.joymain.jecs.po.model.JpoMemberNyc;
import com.joymain.jecs.po.model.JpoNycQualified;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.CustomField;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.ObjectRetrievalFailureException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JpoMemberNycDaoHibernate extends BaseDaoHibernate implements JpoMemberNycDao {

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberNycDao#getJpoMemberNycs(com.joymain.jecs.po.model.JpoMemberNyc)
     */
    public List getJpoMemberNycs(final JpoMemberNyc jpoMemberNyc) {
        return getHibernateTemplate().find("from JpoMemberNyc");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (jpoMemberNyc == null) {
            return getHibernateTemplate().find("from JpoMemberNyc");
        } else {
            // filter on properties set in the jpoMemberNyc
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(jpoMemberNyc).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(JpoMemberNyc.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberNycDao#getJpoMemberNyc(BigDecimal id)
     */
    public JpoMemberNyc getJpoMemberNyc(final Long id) {
        JpoMemberNyc jpoMemberNyc = (JpoMemberNyc) getHibernateTemplate().get(JpoMemberNyc.class, id);
        if (jpoMemberNyc == null) {
            log.warn("uh oh, jpoMemberNyc with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(JpoMemberNyc.class, id);
        }

        return jpoMemberNyc;
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberNycDao#saveJpoMemberNyc(JpoMemberNyc jpoMemberNyc)
     */    
    public void saveJpoMemberNyc(final JpoMemberNyc jpoMemberNyc) {
        getHibernateTemplate().saveOrUpdate(jpoMemberNyc);
    }

    /**
     * @see com.joymain.jecs.po.dao.JpoMemberNycDao#removeJpoMemberNyc(Long id)
     */
    public void removeJpoMemberNyc(final Long id) {
        getHibernateTemplate().delete(getJpoMemberNyc(id));
    }
    //added for getJpoMemberNycsByCrm
    public List getJpoMemberNycsByCrm(CommonRecord crm, Pager pager){
    	String hql = "from JpoMemberNyc jpoMemberNyc where 1=1";
    	/** 
	---example---
	String userCode = crm.getString("userCode", "");
	if(StringUtils.isNotEmpty(userCode)){
		hql += "???????????";
	}


	 ***/
    	//
        String yearofmonth = crm.getString("yearofmonth", "");
        if(StringUtil.isObjectNotBlank(yearofmonth)){
            hql += " and yearOfMonth = '"+yearofmonth.trim()+"'";
        }

        String memberno = crm.getString("memberno", "");
        if(StringUtil.isObjectNotBlank(memberno)){
            hql += " and memberNo = '"+memberno.trim()+"'";
        }



        if (!pager.getLimit().getSort().isSorted()) {
			//sort
			hql += " order by id desc";
		} else {
			hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		log.info("execute query nyc sql:"+hql);
		return this.findObjectsByHqlQuery(hql, pager);
    }

    public List getJpoMemberNycQualifiedsByCrm(CommonRecord crm, Pager pager){
        try{
            String hql = "   SELECT ID,USER_CODE,USER_NAME,PAPERNUMBER,RECOMMEND_NO," +
                    "RECOMMEND_NAME,MEMBER_ORDER_NO,PRODUCT_NO,PRODUCT_NAME,QUALIFY,CHECK_DATE,QUALIFY_DATE,CHECK_TIME,JS_WEEK,STATUS,REMARKS FROM (SELECT ID,USER_CODE,USER_NAME,PAPERNUMBER,RECOMMEND_NO, " +
                    "RECOMMEND_NAME,MEMBER_ORDER_NO,PRODUCT_NO,PRODUCT_NAME,QUALIFY,CHECK_DATE,QUALIFY_DATE,CHECK_TIME,JS_WEEK,'0' AS STATUS,'' AS REMARKS FROM Table(fn_get_nyc_qualify()) union all " +
                    "SELECT ID,USER_CODE,USER_NAME,PAPERNUMBER,RECOMMEND_NO," +
                    "RECOMMEND_NAME,MEMBER_ORDER_NO,PRODUCT_NO,PRODUCT_NAME,QUALIFY,CHECK_DATE,QUALIFY_DATE,CHECK_TIME,JS_WEEK,'1' AS STATUS,REMARKS FROM JPO_MEMBER_NYC_QUALIFY) " +

                    " WHERE 1=1 ";

            if (crm.indexOfKey("ids")>=0) {
                CustomField ids = crm.getField("ids");
                if(null!=ids && StringUtil.isObjectNotBlank(ids.getValue())){

                    hql+=" and id in ('"+ids.getValue() +"')";
                }
            }
            if (crm.indexOfKey("jsweek")>=0) {
                CustomField jsweek = crm.getField("jsweek");
                if(null!=jsweek && StringUtil.isObjectNotBlank(jsweek.getValue())){
                    hql+=" and JS_WEEK= "+jsweek.getValue();
                }

            }
            if (crm.indexOfKey("memberno")>=0) {
                CustomField memberno = crm.getField("memberno");
                if(null!=memberno && StringUtil.isObjectNotBlank(memberno.getValue())){
                    hql += " and USER_CODE = '"+memberno.getValue()+"'";
                }
            }

            if (crm.indexOfKey("paperNumber")>=0) {
                CustomField paperNumber = crm.getField("paperNumber");
                if(null!=paperNumber && StringUtil.isObjectNotBlank(paperNumber.getValue())){
                    hql += " and PAPERNUMBER = '"+paperNumber.getValue()+"'";
                }
            }

            if (crm.indexOfKey("orderNo")>=0) {
                CustomField orderNo = crm.getField("orderNo");
                if(null!=orderNo && StringUtil.isObjectNotBlank(orderNo.getValue())){
                    hql += " and MEMBER_ORDER_NO = '"+orderNo.getValue()+"'";
                }
            }

            if (crm.indexOfKey("recommendNo")>=0) {
                CustomField recommendNo = crm.getField("recommendNo");
                if(null!=recommendNo && StringUtil.isObjectNotBlank(recommendNo.getValue())){
                    hql += " and RECOMMEND_NO = '"+recommendNo.getValue()+"'";
                }
            }

            if (crm.indexOfKey("qualify")>=0) {
                CustomField qualify = crm.getField("qualify");
                if(null!=qualify && StringUtil.isObjectNotBlank(qualify.getValue())){
                    hql += " and QUALIFY = '"+qualify.getValue()+"'";
                }
            }

            if (crm.indexOfKey("status")>=0) {
                CustomField status = crm.getField("status");
                if(null!=status && StringUtil.isObjectNotBlank(status.getValue())){
                    hql += " and STATUS = '"+status.getValue()+"'";
                }
            }

            if (crm.indexOfKey("dateType")>=0) {
                CustomField dateType = crm.getField("dateType");
                String startTime ="";
                String endTime="";
                if (crm.indexOfKey("startTime")>=0) {
                    CustomField cfstartTime = crm.getField("startTime");
                    if(cfstartTime!=null){
                        startTime=cfstartTime.getValue().toString();
                    }
                }
                if (crm.indexOfKey("endTime")>=0) {
                    CustomField cfendTime = crm.getField("endTime");
                    if(cfendTime!=null){
                        endTime=cfendTime.getValue().toString();
                    }
                }



                if(null!=dateType && StringUtil.isObjectNotBlank(dateType.getValue())){

                    if("1".equals(dateType.getValue().toString())){
                        if(StringUtil.isObjectNotBlank(startTime)){
                            hql+=" and CHECK_DATE > to_date('"+startTime+"','yyyy-mm-dd') ";
                        }
                        if(StringUtil.isObjectNotBlank(endTime)){
                            hql+=" and CHECK_DATE <= to_date('"+endTime+"' ,'yyyy-mm-dd')";
                        }
                    }else{
                        if(StringUtil.isObjectNotBlank(startTime)){
                            hql+=" and CHECK_TIME > to_date('"+startTime+"','yyyy-mm-dd') ";
                        }
                        if(StringUtil.isObjectNotBlank(endTime)){
                            hql+=" and CHECK_TIME <= to_date('"+endTime+"','yyyy-mm-dd') ";
                        }
                    }

                }
            }


            if (!pager.getLimit().getSort().isSorted()) {
                //sort
                hql += " order by id desc";
            } else {
                String field= pager.getLimit().getSort().getProperty();
                if(!"paperNumber".equals(field)){
                    field=underscoreName(field);
                }


                hql += " ORDER BY  " +field + " " + pager.getLimit().getSort().getSortOrder();
            }

            return this.findObjectsBySQL(hql,new RowMapper(){

                public JpoNycQualified mapRow(ResultSet rs, int rowNum) throws SQLException {
                    JpoNycQualified nyc = new JpoNycQualified();
                    nyc.setId(rs.getLong("ID"));
                    nyc.setUserCode(rs.getString("USER_CODE"));
                    nyc.setUserName(rs.getString("USER_NAME"));
                    nyc.setPaperNumber(rs.getString("PAPERNUMBER"));
                    nyc.setRecommendNo(rs.getString("RECOMMEND_NO"));
                    nyc.setRecommendName(rs.getString("RECOMMEND_NAME"));
                    nyc.setMemberOrderNo(rs.getString("MEMBER_ORDER_NO"));
                    nyc.setProductNo(rs.getString("PRODUCT_NO"));
                    nyc.setProductName(rs.getString("PRODUCT_NAME"));
                    nyc.setQualify(rs.getString("QUALIFY"));
                    nyc.setJsWeek(rs.getBigDecimal("JS_WEEK").longValue());
                    nyc.setCheckDate(rs.getDate("CHECK_DATE"));
                    nyc.setCheckTime(rs.getDate("CHECK_TIME"));
                    nyc.setQualifyDate(rs.getDate("QUALIFY_DATE"));
                    String status = rs.getString("STATUS");
                    nyc.setStatus(status);
                    nyc.setRemarks(rs.getString("REMARKS"));

                    return nyc;
                }

            },pager);
        }catch (Exception e){
            return new ArrayList();
        }

    }


    public void bacthUpdateNycStatus(){
        getJdbcTemplate().execute("UPDATE JPO_MEMBER_NYC SET STATUS='1' WHERE PUSH_AT< (SYSDATE-30) AND  STATUS= '0'");
    }

    public static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }
}
