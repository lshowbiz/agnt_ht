package com.joymain.jecs.bd.webapp.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.joymain.jecs.Constants;
import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.web.RequestUtil;
import com.joymain.jecs.webapp.action.BaseController;

public class BdNewOldPeriodController extends BaseController implements Controller {
	private final Log log = LogFactory.getLog(BdNewOldPeriodController.class);
	private BdPeriodManager bdPeriodManager = null;

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("entering 'handleRequest' method...");
		}

		CommonRecord crm = RequestUtil.toCommonRecord(request);
		Pager pager = new Pager("bdPeriodListTable", request, 100);
		/*crm.addField("sortStartTime", "desc");
		
		BdPeriod bdPeriodTmp = this.bdPeriodManager.getBdPeriodByTime(DateUtil.getDateOffset(new Date(),5, 35),null);
		if (bdPeriodTmp == null) {
			bdPeriodTmp = new BdPeriod();
		}
		String wyearAndwweek = bdPeriodTmp.getWyear().toString();
		if(bdPeriodTmp.getWweek()>9){
			wyearAndwweek += bdPeriodTmp.getWweek().toString();
		}else{
			wyearAndwweek += "0" + bdPeriodTmp.getWweek().toString();
		}
		crm.addField("wyearAndwweek", wyearAndwweek);*/
		
		//将当前的时间向后推５２天，得到新的日期（业务规定）--------修改开始
    	//Date date = DateUtil.getDateOffset(new Date(), 5, 52);
    	//定义一个变量，但是该变量在方法中并没有应用，为了最少限度改动代码才这样做的
    	Long wid =1l;
    	//根据这个日期，查询出期别表的对象
    	BdPeriod bdPeriodd =  bdPeriodManager.getBdPeriodByTime(new Date(),wid);
    	//获取工作年
    	int Wyear = bdPeriodd.getWyear();
    	String wwyear = Integer.toString(Wyear);
		crm.addField("wyearAndwweek", wwyear);
		//----------------------修改结束
		List bdPeriods = bdPeriodManager.getBdPeriodsByCrm(crm, pager);
		//根据数据重新设置分页数据
		request.setAttribute("bdPeriodListTable_totalRows", pager.getTotalObjects());
		
		/*for(int i = 0 ; i < bdPeriods.size() ; i++){
			BdPeriod bdPeriod = (BdPeriod)bdPeriods.get(i);
			long time = bdPeriod.getEndTime().getTime()-1000l;
			bdPeriod.setEndTime(new Date(time));
		}*/
		for(int i = 0 ; i < bdPeriods.size() ; i++){
			Map map = new HashMap();
			map  = (Map) bdPeriods.get(i);
			String times = (String) map.get("end_time");
			String timeStart = (String)map.get("start_time");
			//Long time = Long.parseLong(times)-1000l;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date date = sdf.parse(times);
				Date dateStart = sdf.parse(timeStart);
				Long ttt = date.getTime();
				Long time = ttt -1000l;
				map.put("end_time",sdf.format(time));
				//map.put("start_time",sdf.format(dateStart));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		return new ModelAndView("bd/bd_new_old_period", Constants.BDPERIOD_LIST, bdPeriods);
	}
	
}
