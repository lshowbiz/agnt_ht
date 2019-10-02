package com.joymain.jecs.bd.dao.hibernate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;

import com.joymain.jecs.bd.dao.BdPeriodDao;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.dao.hibernate.BaseDaoHibernate;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.date.DateUtil;

public class BdPeriodDaoHibernate extends BaseDaoHibernate implements BdPeriodDao {
	public String getBonusCalculateYearMonth() {
		// TODO Auto-generated method stub
		String sql = "Select Min(w_Year || Lpad(w_Week, 2, 0)) year_week From Jbd_Period Where Archiving_Status <> 1";
//		this.jdbcTemplate.queryForObject(sql, String.class);
//		List list = this.getJdbcTemplate().queryForList(sql);
		
		
		return (String) this.getJdbcTemplate().queryForObject(sql, String.class);
	}
	/**
	 * @see com.joymain.jecs.bd.dao.BdPeriodDao#getBdPeriods(com.joymain.jecs.bd.model.BdPeriod)
	 */
	public List getBdPeriods(final BdPeriod bdPeriod) {
		return getHibernateTemplate().findByExample(bdPeriod);
	}

	/**
	 * @see com.joymain.jecs.bd.dao.BdPeriodDao#getBdPeriod(Long wid)
	 */
	public BdPeriod getBdPeriod(final Long wid) {
		BdPeriod bdPeriod = (BdPeriod) getHibernateTemplate().get(BdPeriod.class, wid);
		if (bdPeriod == null) {
			log.warn("uh oh, bdPeriod with id '" + wid + "' not found...");
			throw new ObjectRetrievalFailureException(BdPeriod.class, wid);
		}

		return bdPeriod;
	}

	/**
	 * @see com.joymain.jecs.bd.dao.BdPeriodDao#saveBdPeriod(BdPeriod bdPeriod)
	 */
	public void saveBdPeriod(final BdPeriod bdPeriod) {
		getHibernateTemplate().saveOrUpdate(bdPeriod);
	}

	/**
	 * @see com.joymain.jecs.bd.dao.BdPeriodDao#removeBdPeriod(Long wid)
	 */
	public void removeBdPeriod(final Long wid) {
		getHibernateTemplate().delete(getBdPeriod(wid));
	}
	
	/**
	 * 获取所有期别
	 * @return
	 */
	public List getAllBdPeriods(){
		String hqlQuery = "from BdPeriod bdPeriod";
		return this.findObjectsByHqlQuery(hqlQuery);
	}

	//added for getBdPeriodsByCrm
	public List getBdPeriodsByCrm(CommonRecord crm, Pager pager) {
		//修改新旧期别对比－特将下面部分注释掉
		/*String hql = "from BdPeriod bdPeriod where 1=1";
		
		String wweek = crm.getString("wweek", "");
		if (!StringUtils.isEmpty(wweek)) {
			hql += " and wweek='" + wweek + "'";
		}

		String wyearAndwweek = crm.getString("wyearAndwweek", "");
		if (!StringUtils.isEmpty(wyearAndwweek)) {
			hql += " and wyear || lpad(wweek,2,0)<=" + wyearAndwweek + "";
		}
		
		// 
		String sortStartTime = crm.getString("sortStartTime", "");
		if (!StringUtils.isEmpty(sortStartTime)) {
			hql += " order by startTime " + sortStartTime;
		}else{
			if (!pager.getLimit().getSort().isSorted()) {
				//
				hql += " order by wyear desc, wweek desc";
			} else {
				hql += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
			}
		}*/
		
		String wwyear = crm.getString("wyearAndwweek", "");
		int endYearOne = Integer.parseInt(wwyear);
		int endYearTwo = endYearOne+1;
		String wwyearEnd = String.valueOf(endYearTwo);
		String sqlQuery = " select * from ( " +
                           "  select *  from  "+
                                 " ( select * from jbd_period t  where concat(w_year, Lpad(w_week, 2, 0)) > " +
                                         " (select max(concat(w_year, Lpad(w_week, 2, 0))) from jbd_period t where t.bonus_send <> 0)" +
                                                 " order by t.end_time asc )  where rownum <= 104 "+
                          " union all "+
                          "  select * from "+
                                  " ( select *  from jbd_period t where concat(w_year, Lpad(w_week, 2, 0)) <= " +
                                          "(select max(concat(w_year, Lpad(w_week, 2, 0))) from jbd_period t where t.bonus_send <> 0)  " +
                                                  "order by t.end_time desc )  where rownum <= 170 "+
                         " ) order by end_time desc ";
		return this.findObjectsBySQL(sqlQuery, pager);
		
	}

	/**
	 * 根据工作年及工作周获取对应的资料
	 * @param wyear 工作年
	 * @param wweek 工作周
	 * @param wid 被排除的ID
	 * @return
	 */
	public BdPeriod getBdPeriodByWeek(final Integer wyear, final Integer wweek, final Long wid) {
		String hqlQuery="from BdPeriod where wyear='" + wyear + "' and wweek='" + wweek + "'";
		if(wid!=null){
			hqlQuery+=" and id<>'"+wid+"'";
		}
		return (BdPeriod) this.getObjectByHqlQuery(hqlQuery);
	}
	
	/**
	 * 获取对应时间区间的期数,如果期数ID不为空,则不包含对应的期数ID
	 * @author gw 2013-08-07修改
	 * @param theTime 检查的时候
	 * @param wid 排除的ID
	 * @return
	 */
	public BdPeriod getBdPeriodByTime(final Date theTime, final Long wid){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String hqlQuery="from BdPeriod where startTime <= to_date('"+dateFormat.format(theTime)+"','yyyy-mm-dd hh24:mi:ss') and endTime > to_date('"+dateFormat.format(theTime)+"','yyyy-mm-dd hh24:mi:ss')";
		//老系统更改-特将该部分注释掉２０１３－０８－０７修改时注释
		/*if(wid!=null){
			hqlQuery+=" and id<>'"+wid+"'";
		}*/
		return (BdPeriod)this.getObjectByHqlQuery(hqlQuery);
	}
	
	/**
	 * 获取最早的未发放奖金的周
	 * @return
	 */
	public BdPeriod getLastUnsendBonus(){
		String hqlQuery="from BdPeriod where bonusSend=0 or bonusSend is null order by wyear asc, wweek asc";
		return (BdPeriod)this.getObjectByHqlQuery(hqlQuery);
	}

	/**
	 * 获取未来的后几期
	 * @param Year
	 * @param Month
	 * @param latelyCount
	 * @return
	 */
	public String getFutureBdYearMonth(final String year,final String month,final int latelyCount){
		String sql = "Select Max(w_Year || Lpad(w_Month, 2, 0)) period From (Select Distinct w_year,w_month From Jbd_Period Order By w_Year, w_Month) Where Concat(w_Year, Lpad(w_Month, 2, 0)) >= " + year + " || Lpad(" + month + ", 2, 0)And Rownum <= " +latelyCount + "";
		return this.findOneObjectBySQL(sql).get("period").toString();
	}
	
	/**
	 * 获取最后的已发放奖金的周
	 * @return
	 */
	public BdPeriod getLatestSendBonus(){
		return (BdPeriod)this.getObjectByHqlQuery("from BdPeriod where bonusSend=1 order by wyear desc, wweek desc");
	}
	
	/**
	 * 获取某一期的上一期
	 * @param bdPeriod
	 * @return
	 */
	public BdPeriod getPreviousWeek(BdPeriod bdPeriod){
		return (BdPeriod)this.getObjectByHqlQuery("from BdPeriod where endTime<= to_date('"+DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", new Date())+"','yyyy-MM-dd hh24:mi:ss') order by endTime desc");
	}
	
	/**
	 * 获取某工作年工作月所对应的工作周资料
	 * @param wyear
	 * @param wmonth
	 * @return
	 */
	public List getBdPeriodsByMonth(final Integer wyear, final Integer wmonth ){
		return this.getHibernateTemplate().find("from BdPeriod where wyear=? and wmonth=? order by wweek asc", new Object[]{wyear, wmonth});
	}
	
	/**
	 * 获取某段期别内的期别列表
	 * @param startWyear
	 * @param startWweek
	 * @param endWyear
	 * @param endWweek
	 * @return
	 */
	public List getBdPeriodsByRange(final Integer startWyear, final Integer startWweek, final Integer endWyear, final Integer endWweek){
		return this.getHibernateTemplate().find("from BdPeriod where concat(wyear, Lpad(wweek, 2, 0))>=? and concat(wyear, Lpad(wweek, 2, 0))<=?  order by wyear asc, wweek asc", new Object[]{startWyear.toString()+StringUtils.leftPad(startWweek.toString(), 2, '0'),endWyear.toString()+StringUtils.leftPad(endWweek.toString(), 2, '0')});
	}
	
	/**
	 * 获取距某一时间最近的几期
	 * @param theDate
	 * @param latelyCount
	 * @return
	 */
	public List getLatestBdPeriods(final String theDate, int latelyCount){
		Pager pager=new Pager();
		pager.setPageSize(latelyCount);
		return this.findObjectsBySQL("select concat(w_year, Lpad(w_week, 2, 0)) as long_w_week,a.* from jbd_period a where a.start_time<=to_date('"+theDate+" 12:00:00','yyyy-MM-dd hh:mi:ss') and DAY_STATUS=1 and a.bonus_send=0 order by long_w_week desc ", pager);
	}
	
	/**
	 * 获取距某一时间最近的几期
	 * @param theDate
	 * @param latelyCount
	 * @return
	 */
	public List getLatestBdPeriodsFweek(final String theDate, int latelyCount){
		Pager pager=new Pager();
		pager.setPageSize(latelyCount);
		return this.findObjectsBySQL("select concat(w_year, Lpad(w_week, 2, 0)) as long_w_week,concat(f_year, Lpad(f_week, 2, 0)) as long_f_week,a.* from jbd_period a where a.start_time<=to_date('"+theDate+" 12:00:00','yyyy-MM-dd hh:mi:ss') and DAY_STATUS=1 and a.bonus_send=0 order by long_w_week desc ", pager);
	}
	
	/**
	 * 获取距某一时间未来的几期
	 * @param theDate
	 * @param latelyCount
	 * @return
	 */
	public List getFutureBdPeriods(final String theDate, int latelyCount){
		Pager pager=new Pager();
		pager.setPageSize(latelyCount);
		return this.findObjectsBySQL("select concat(w_year, Lpad(w_week, 2, 0)) as long_w_week,a.* from bd_period a where a.start_time>'"+theDate+"' order by long_w_week asc ", pager);
	}
	
	/**
	 * 根据已经格式化的期别获取对应的期数
	 * @param formatedWeek 如200806
	 * @return
	 */
	public BdPeriod getBdPeriodByFormatedWeek(final String formatedWeek){
		return (BdPeriod)this.getObjectByHqlQuery("from BdPeriod where concat(wyear, Lpad(wweek, 2, 0))='"+formatedWeek+"'");
	}

	/**
	 * 查询所有期别,按开始时间排序
	 * @return
	 */
	public List getBdPeriodsByStartTime(String startTime){
		String hqlQuery="from BdPeriod b";
			hqlQuery += " where to_date(b.startTime,'yyyy-MM-dd hh24:mi:ss') <=to_date('" + startTime + " 00:00:00','yyyy-MM-dd hh24:mi:ss')";
			hqlQuery += " order by b.startTime desc";
		return this.findObjectsByHqlQuery(hqlQuery);
	}

	public BdPeriod getNextWeek(BdPeriod bdPeriod) {
		DateFormat dateFormat =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		String sql="from BdPeriod where startTime>= to_date('"+dateFormat.format(bdPeriod.getEndTime())+"','yyyy-mm-dd hh24:mi:ss') order by endTime ";
		return (BdPeriod)this.getObjectByHqlQuery(sql);
		
	}

	/**
	 * 获取月结周
	 * @param formatedWeek
	 * @return
	 */
	public BdPeriod getMonthBdPeriod(String formatedWeek) {
		return (BdPeriod)this.getObjectByHqlQuery("from BdPeriod where concat(wyear, Lpad(wweek, 2, 0))='"+formatedWeek+"' and monthStatus=1");
	}

	/**
	 * 获取最早的日结算周
	 * @return
	 */
	public String getLastDayBonus(){
		return this.findOneObjectBySQL("Select Max(concat(w_year,lpad(w_week,2,0))) as period From jbd_period  Where day_status = 1").get("period").toString();
	}
	

	/**
	 * 获取某工作年工作月所对应的工作周资料
	 * @param fyear
	 * @param fmonth
	 * @return
	 */
	public List getBdPeriodsByFmonth(final Integer fyear, final Integer fmonth ){
		return this.getHibernateTemplate().find("from BdPeriod where fyear=? and fmonth=? order by fweek asc", new Object[]{fyear, fmonth});
	}
	
	/**
	 * @Description 根据条件查询最大或最小的start_time 
	 * @author houxyu
	 * @date 2016-3-16
	 * @return
	 */
	public String getStartTimeByOrder(String flag){
		String sql = "";
		if("max".equals(flag)){
			sql = " select max(start_time) s from JBD_PERIOD ";
		}else if("min".equals(flag)){
			sql = " select min(start_time) s from JBD_PERIOD ";
		}
		return this.findOneObjectBySQL(sql).get("s").toString();
	}
}