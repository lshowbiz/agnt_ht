package com.joymain.jecs.bd.dao;

import java.util.Date;
import java.util.List;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.dao.Dao;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public interface BdPeriodDao extends Dao {

	public String getBonusCalculateYearMonth();
	//	public List getPiProductsByHql(String hql);

	//	public List getPiProductsBySql(String sql);
	/**
	 * Retrieves all of the bdPeriods
	 */
	public List getBdPeriods(BdPeriod bdPeriod);

	/**
	 * Gets bdPeriod's information based on primary key. An ObjectRetrievalFailureException Runtime Exception is thrown if nothing is found.
	 * 
	 * @param wid the bdPeriod's wid
	 * @return bdPeriod populated bdPeriod object
	 */
	public BdPeriod getBdPeriod(final Long wid);

	/**
	 * Saves a bdPeriod's information
	 * @param bdPeriod the object to be saved
	 */
	public void saveBdPeriod(BdPeriod bdPeriod);

	/**
	 * Removes a bdPeriod from the database by wid
	 * @param wid the bdPeriod's wid
	 */
	public void removeBdPeriod(final Long wid);
	
	/**
	 * 获取所有期别
	 * @return
	 */
	public List getAllBdPeriods();

	//added for getBdPeriodsByCrm
	public List getBdPeriodsByCrm(CommonRecord crm, Pager pager);

	/**
	 * 获取未来的后几期
	 * @param Year
	 * @param Month
	 * @param latelyCount
	 * @return
	 */
	public String getFutureBdYearMonth(final String year,final String month,final int latelyCount);

	/**
	 * 根据工作年及工作周获取对应的资料
	 * @param wyear 工作年
	 * @param wweek 工作周
	 * @param wid 被排除的ID
	 * @return
	 */
	public BdPeriod getBdPeriodByWeek(final Integer wyear, final Integer wweek, final Long wid);
	
	/**
	 * 获取对应时间区间的期数,如果期数ID不为空,则不包含对应的期数ID
	 * @param theTime
	 * @param wid
	 * @return
	 */
	public BdPeriod getBdPeriodByTime(final Date theTime, final Long wid);
	
	/**
	 * 获取最早的未发放奖金的周
	 * @return
	 */
	public BdPeriod getLastUnsendBonus();
	
	/**
	 * 获取最后的已发放奖金的周
	 * @return
	 */
	public BdPeriod getLatestSendBonus();
	
	/**
	 * 获取某一期的上一期
	 * @param bdPeriod
	 * @return
	 */
	public BdPeriod getPreviousWeek(BdPeriod bdPeriod);
	
	/**
	 * 获取某工作年工作月所对应的工作周资料
	 * @param wyear
	 * @param wmonth
	 * @return
	 */
	public List getBdPeriodsByMonth(final Integer wyear, final Integer wmonth );
	
	/**
	 * 获取某段期别内的期别列表
	 * @param startWyear
	 * @param startWweek
	 * @param endWyear
	 * @param endWweek
	 * @return
	 */
	public List getBdPeriodsByRange(final Integer startWyear, final Integer startWweek, final Integer endWyear, final Integer endWweek);
	
	/**
	 * 获取距某一时间最近的几期
	 * @param theDate
	 * @param latelyCount
	 * @return
	 */
	public List getLatestBdPeriods(final String theDate, int latelyCount);
	
	/**
	 * 获取距某一时间未来的几期
	 * @param theDate
	 * @param latelyCount
	 * @return
	 */
	public List getFutureBdPeriods(final String theDate, int latelyCount);
	
	/**
	 * 根据已经格式化的期别获取对应的期数
	 * @param formatedWeek 如200806
	 * @return
	 */
	public BdPeriod getBdPeriodByFormatedWeek(final String formatedWeek);

	/**
	 * 查询所有期别,按开始时间排序
	 * @return
	 */
	public List getBdPeriodsByStartTime(String startTime);	
	/**
	 * 获取某一期的下一期
	 * @param bdPeriod
	 * @return
	 */
	public BdPeriod getNextWeek(BdPeriod bdPeriod);
	/**
	 * 获取月结周
	 * @param formatedWeek
	 * @return
	 */
	public BdPeriod getMonthBdPeriod(String formatedWeek);
	/**
	 * 获取最早的日结算周
	 * @return
	 */
	public String getLastDayBonus();

	/**
	 * 获取距某一时间最近的几期
	 * @param theDate
	 * @param latelyCount
	 * @return
	 */
	public List getLatestBdPeriodsFweek(final String theDate, int latelyCount);

	/**
	 * 获取某工作年工作月所对应的工作周资料
	 * @param fyear
	 * @param fmonth
	 * @return
	 */
	public List getBdPeriodsByFmonth(final Integer fyear, final Integer fmonth );
	
	/**
	 * @Description 根据条件查询最大或最小的start_time 
	 * @author houxyu
	 * @date 2016-3-16
	 * @return
	 */
	public String getStartTimeByOrder(String flag);
}