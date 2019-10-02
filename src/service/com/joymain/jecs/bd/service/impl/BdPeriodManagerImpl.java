package com.joymain.jecs.bd.service.impl;

import java.util.Date;
import java.util.List;

import com.joymain.jecs.bd.dao.BdPeriodDao;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.string.StringUtil;

public class BdPeriodManagerImpl extends BaseManager implements BdPeriodManager {
	private BdPeriodDao dao;

	public String getBonusCalculateYearMonth() {
		// TODO Auto-generated method stub
		return dao.getBonusCalculateYearMonth();
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * @param dao
	 */
	public void setBdPeriodDao(BdPeriodDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.joymain.jecs.bd.service.BdPeriodManager#getBdPeriods(com.joymain.jecs.bd.model.BdPeriod)
	 */
	public List getBdPeriods(final BdPeriod bdPeriod) {
		return dao.getBdPeriods(bdPeriod);
	}

	/**
	 * @see com.joymain.jecs.bd.service.BdPeriodManager#getBdPeriod(String wid)
	 */
	public BdPeriod getBdPeriod(final String wid) {
		return dao.getBdPeriod(new Long(wid));
	}

	/**
	 * @see com.joymain.jecs.bd.service.BdPeriodManager#saveBdPeriod(BdPeriod bdPeriod)
	 */
	public void saveBdPeriod(BdPeriod bdPeriod) {
		dao.saveBdPeriod(bdPeriod);
	}

	/**
	 * 获取未来的后几期
	 * @param Year
	 * @param Month
	 * @param latelyCount
	 * @return
	 */
	public String getFutureBdYearMonth(final String year,final String month,final int latelyCount){
		return dao.getFutureBdYearMonth(year,month,latelyCount);
	}

	/**
	 * @see com.joymain.jecs.bd.service.BdPeriodManager#removeBdPeriod(String wid)
	 */
	public void removeBdPeriod(final String wid) {
		dao.removeBdPeriod(new Long(wid));
	}
	
	/**
	 * 获取所有期别
	 * @return
	 */
	public List getAllBdPeriods(){
		return dao.getAllBdPeriods();
	}

	//added for getBdPeriodsByCrm
	public List getBdPeriodsByCrm(CommonRecord crm, Pager pager) {
		return dao.getBdPeriodsByCrm(crm, pager);
	}

	/**
	 * 根据工作年及工作周获取对应的资料
	 * @param wyear 工作年
	 * @param wweek 工作周
	 * @param wid 被排除的ID
	 * @return
	 */
	public BdPeriod getBdPeriodByWeek(final Integer wyear, final Integer wweek, final Long wid) {
		return dao.getBdPeriodByWeek(wyear, wweek, wid);
	}
	
	/**
	 * 获取对应时间区间的期数,如果期数ID不为空,则不包含对应的期数ID
	 * @param theTime
	 * @param wid
	 * @return
	 */
	public BdPeriod getBdPeriodByTime(final Date theTime, final Long wid){
		return dao.getBdPeriodByTime(theTime, wid);
	}
	
	/**
	 * 获取最早的未发放奖金的周
	 * @return
	 */
	public BdPeriod getLastUnsendBonus(){
		return dao.getLastUnsendBonus();
	}
	
	/**
	 * 获取最后的已发放奖金的周
	 * @return
	 */
	public BdPeriod getLatestSendBonus(){
		return dao.getLatestSendBonus();
	}
	
	/**
	 * 获取某一期的上一期
	 * @param bdPeriod
	 * @return
	 */
	public BdPeriod getPreviousWeek(BdPeriod bdPeriod){
		return dao.getPreviousWeek(bdPeriod);
	}
	
	/**
	 * 获取某工作年工作月所对应的工作周资料
	 * @param wyear
	 * @param wmonth
	 * @return
	 */
	public List getBdPeriodsByMonth(final String wyear, final String wmonth ){
		return dao.getBdPeriodsByMonth(new Integer(wyear), new Integer(wmonth));
	}
	
	/**
	 * 获取某段期别内的期别列表
	 * @param startWyear
	 * @param startWweek
	 * @param endWyear
	 * @param endWweek
	 * @return
	 */
	public List getBdPeriodsByRange(final String startWyear, final String startWweek, final String endWyear, final String endWweek){
		return dao.getBdPeriodsByRange(new Integer(startWyear), new Integer(startWweek), new Integer(endWyear), new Integer(endWweek));
	}
	
	/**
	 * 获取距某一时间最近的几期
	 * @param theDate
	 * @param latelyCount
	 * @return
	 */
	public List getLatestBdPeriods(final String theDate, int latelyCount){
		return dao.getLatestBdPeriods(theDate, latelyCount);
	}

	/**
	 * 获取距某一时间未来的几期
	 * @param theDate
	 * @param latelyCount
	 * @return
	 */
	public List getFutureBdPeriods(final String theDate, int latelyCount){
		return dao.getFutureBdPeriods(theDate, latelyCount);
	}
	
	/**
	 * 根据已经格式化的期别获取对应的期数
	 * @param formatedWeek 如200806
	 * @return
	 */
	public BdPeriod getBdPeriodByFormatedWeek(final String formatedWeek){
		return dao.getBdPeriodByFormatedWeek(formatedWeek);
	}

	/**
	 * 查询所有期别,按开始时间排序
	 * @return
	 */
	public List getBdPeriodsByStartTime(String startTime){
		return dao.getBdPeriodsByStartTime(startTime);
	}

	public BdPeriod getNextWeek(BdPeriod bdPeriod) {
		return dao.getNextWeek(bdPeriod);
	}

	public BdPeriod getMonthBdPeriod(String formatedWeek) {
		return dao.getMonthBdPeriod(formatedWeek);
	}

	public Integer getBdPeriodByTimeFormated(Date theTime, Long wid) {

		BdPeriod bdPeriod = this.getBdPeriodByTime(theTime, wid);		
		
		String bdWeek= bdPeriod.getWyear()+""+ (bdPeriod.getWweek()<10?"0" + bdPeriod.getWweek():bdPeriod.getWweek());
		
		return StringUtil.formatInt(bdWeek);
	}

	public Long getBdPeriodByTimeFormatedAll(Date theTime, Long wid) {

		BdPeriod bdPeriod = this.getBdPeriodByTime(theTime, wid);		
		return bdPeriod.getWyear()*10000L + bdPeriod.getWmonth()*100L+bdPeriod.getWweek();
	}

	public String getLastDayBonus() {
		return dao.getLastDayBonus();
	}

	public List getLatestBdPeriodsFweek(String theDate, int latelyCount) {
		return dao.getLatestBdPeriodsFweek(theDate, latelyCount);
	}

	public List getBdPeriodsByFmonth(String fyear, String fmonth) {
		return dao.getBdPeriodsByFmonth(new Integer(fyear), new Integer(fmonth));
	}

	public String getStartTimeByOrder(String flag) {
		// TODO Auto-generated method stub
		return dao.getStartTimeByOrder(flag);
	}
	
}