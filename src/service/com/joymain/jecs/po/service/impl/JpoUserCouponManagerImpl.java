
package com.joymain.jecs.po.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joymain.jecs.pm.dao.JpmCouponInfoDao;
import com.joymain.jecs.pm.model.JpmCouponInfo;
import com.joymain.jecs.po.dao.JpoUserCouponDao;
import com.joymain.jecs.po.model.JpoUserCoupon;
import com.joymain.jecs.po.service.JpoUserCouponManager;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;

public class JpoUserCouponManagerImpl extends BaseManager implements JpoUserCouponManager {
	private JpoUserCouponDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setJpoUserCouponDao(JpoUserCouponDao dao) {
		this.dao = dao;
	}
	
	private JpmCouponInfoDao jpmCouponInfoDao;
	
	public void setJpmCouponInfoDao(JpmCouponInfoDao jpmCouponInfoDao) {
		this.jpmCouponInfoDao = jpmCouponInfoDao;
	}

	/**
	 * @see com.joymain.jecs.po.service.JpoUserCouponManager#getJpoUserCoupons(com.joymain.jecs.po.model.JpoUserCoupon)
	 */
	public List getJpoUserCoupons(final JpoUserCoupon jpoUserCoupon) {
		return dao.getJpoUserCoupons(jpoUserCoupon);
	}

	/**
	 * @see com.joymain.jecs.po.service.JpoUserCouponManager#getJpoUserCoupon(String
	 *      id)
	 */
	public JpoUserCoupon getJpoUserCoupon(final String id) {
		return dao.getJpoUserCoupon(new BigDecimal(id));
	}

	/**
	 * @see com.joymain.jecs.po.service.JpoUserCouponManager#saveJpoUserCoupon(JpoUserCoupon
	 *      jpoUserCoupon)
	 */
	public void saveJpoUserCoupon(JpoUserCoupon jpoUserCoupon) {
		dao.saveJpoUserCoupon(jpoUserCoupon);
	}

	/**
	 * @see com.joymain.jecs.po.service.JpoUserCouponManager#removeJpoUserCoupon(String
	 *      id)
	 */
	public void removeJpoUserCoupon(final String id) {
		dao.removeJpoUserCoupon(new BigDecimal(id));
	}

	// added for getJpoUserCouponsByCrm
	public List getJpoUserCouponsByCrm(CommonRecord crm, Pager pager) {
		return dao.getJpoUserCouponsByCrm(crm, pager);
	}

	public List getJpoUserCouponsByCrmToSql(CommonRecord crm, Pager pager) {
		return dao.getJpoUserCouponsByCrmToSql(crm, pager);
	}

	@Override
	public void updateUserCouponByIds(String ids) {
		dao.updateUserCouponByIds(ids);
	}

	@Override
	public void saveJpoUserCouponMany(Map map) {
		String userCode=(String)map.get("userCode");
		String cpName=(String)map.get("couponName");
		Integer count =Integer.valueOf((String) map.get("countNum"));
		if (count!=0) {
			JpmCouponInfo byCouponName = jpmCouponInfoDao.getByCouponName(cpName);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
			try {
				Date startTime = sdf.parse("2017-01-01 00:00:00");
				Date endTime= sdf.parse("2050-02-01 23:59:59");
				for (int i = 0; i < count; i++) {
					JpoUserCoupon jpoUserCoupon=new JpoUserCoupon();
					jpoUserCoupon.setUserCode(userCode);
					jpoUserCoupon.setStartTime(startTime);
					jpoUserCoupon.setEndTime(endTime);
					jpoUserCoupon.setStatus("0");
					jpoUserCoupon.setAbleStatus("Y");
					jpoUserCoupon.setRemark("后台批量导入数据");
					jpoUserCoupon.setCreateTime(new Date());
					jpoUserCoupon.setCpId(byCouponName.getCpId());
					dao.saveJpoUserCoupon(jpoUserCoupon);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
	}
}
