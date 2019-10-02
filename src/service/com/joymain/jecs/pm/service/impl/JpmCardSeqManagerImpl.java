
package com.joymain.jecs.pm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.joymain.jecs.pm.dao.JpmCardSeqDao;
import com.joymain.jecs.pm.model.JpmCardSeq;
import com.joymain.jecs.pm.service.JpmCardSeqManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.service.impl.BaseManager;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.exception.AppException;
import com.joymain.jecs.util.string.StringUtil;
public class JpmCardSeqManagerImpl extends BaseManager implements JpmCardSeqManager {
    private JpmCardSeqDao dao;

    private SysIdManager sysIdManager;
    
    public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	/**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setJpmCardSeqDao(JpmCardSeqDao dao) {
        this.dao = dao;
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCardSeqManager#getJpmCardSeqs(com.joymain.jecs.pm.model.JpmCardSeq)
     */
    public List getJpmCardSeqs(final JpmCardSeq jpmCardSeq) {
        return dao.getJpmCardSeqs(jpmCardSeq);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCardSeqManager#getJpmCardSeq(String id)
     */
    public JpmCardSeq getJpmCardSeq(final String id) {
        return dao.getJpmCardSeq(new Long(id));
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCardSeqManager#saveJpmCardSeq(JpmCardSeq jpmCardSeq)
     */
    public void saveJpmCardSeq(JpmCardSeq jpmCardSeq) {
        dao.saveJpmCardSeq(jpmCardSeq);
    }

    /**
     * @see com.joymain.jecs.pm.service.JpmCardSeqManager#removeJpmCardSeq(String id)
     */
    public void removeJpmCardSeq(final String id) {
        dao.removeJpmCardSeq(new Long(id));
    }
    //added for getJpmCardSeqsByCrm
    public List getJpmCardSeqsByCrm(CommonRecord crm, Pager pager){
	return dao.getJpmCardSeqsByCrm(crm,pager);
    }

	public void saveUserJpmCardSeq(JpoMemberOrder jpoMemberOrder,String oldCard,String newCard) {
		Set<JpoMemberOrderList> jpoMemberOrderList=jpoMemberOrder.getJpoMemberOrderList();
		
		java.util.Calendar startc=java.util.Calendar.getInstance();
		startc.set(2013, 0, 19, 00, 00, 00);
		java.util.Date startDate=startc.getTime();
		

		java.util.Calendar endc=java.util.Calendar.getInstance();
		endc.set(2013, 1, 9, 00, 00, 00);
		java.util.Date endDate=endc.getTime();
		
		Date curDate=new Date();
		
		
		
		if("CN".equals(jpoMemberOrder.getCompanyCode())){
			if(curDate.after(startDate)&&curDate.before(endDate)){
				if("4".equals(jpoMemberOrder.getOrderType())||"9".equals(jpoMemberOrder.getOrderType())||"14".equals(jpoMemberOrder.getOrderType())){
					if(jpoMemberOrder.getDiscountAmount()!=null){
						int qty=jpoMemberOrder.getDiscountAmount().intValue()/1000;
						if(qty>0){
							for (int i = 1; i <= qty; i++) {
								JpmCardSeq seq=new JpmCardSeq();
								String cardseq_order=sysIdManager.buildIdStr("cardseq_order");
								seq.setCardNo(cardseq_order);
								seq.setMemberOrderNo(jpoMemberOrder.getMemberOrderNo());
								seq.setPassword("00000000");
								seq.setUserCode(jpoMemberOrder.getSysUser().getUserCode());
								seq.setCreateTime(curDate);
								seq.setState("1");
								this.saveJpmCardSeq(seq);
							}
						}
					}
				}
			}
			
			
		}
		//************
		
		

	/*	java.util.Calendar startc=java.util.Calendar.getInstance();
		startc.set(2010, 4, 8, 00, 00, 00);
		
		
		java.util.Date startDate=startc.getTime();
		
		Date curDate=new Date();
		
		if("CN".equals(jpoMemberOrder.getCompanyCode())){
			if((curDate.after(startDate))){
				this.saveUserJpmCardSeq2(jpoMemberOrder, jpoMemberOrderList, oldCard, newCard);
			}else{
				//鍘熸柟妗�
				int qty=0;
				List<Long> molIds=new ArrayList<Long>();
				for (JpoMemberOrderList list : jpoMemberOrderList) {
					//100581,閫佸崱
					if(100581==list.getJpmProductSale().getUniNo()){
						qty+=list.getQty();
						for (int i = 0; i < list.getQty(); i++) {
							molIds.add(list.getMolId());
						}
					}
				}
				if(qty>0){
					String ids=dao.getJpmCardSeqsNotUse(qty);
					if(ids==null){
						throw new AppException("cardNo.not.find");
					}
					List<JpmCardSeq> list=dao.getJpmCardSeqByIds(ids);
					if(list.size()!=qty){
						throw new AppException("cardNo.not.find");
					}
					for (int i = 0; i < list.size(); i++) {
						JpmCardSeq seq=list.get(i);
						
						seq.setCreateTime(new Date());
						seq.setState("1");
						seq.setUserCode(jpoMemberOrder.getSysUser().getUserCode());
						seq.setMolId(molIds.get(i));
						seq.setMemberOrderNo(jpoMemberOrder.getMemberOrderNo());
						dao.saveJpmCardSeq(seq);
					}
				}	
			}
		}*/
	}

//	public String saveJpmCardSeqAndGetPwd(String cardNo, String sequenceNo, String userCode) {
//		JpmCardSeq jpmCardSeq=dao.getJpmCardSeqByCardNoAndSeq(cardNo, sequenceNo);
//		if(jpmCardSeq==null){
//			throw new AppException("jpmCardSeq.notfind.cardNo");
//		}
//		if(!StringUtil.isEmpty(jpmCardSeq.getUserCode())&&!userCode.equals(jpmCardSeq.getUserCode())){
//			throw new AppException("jpmCardSeq.cardNo.used");
//		}
//		
//		String returnPwd=jpmCardSeq.getPassword();
//		
//		if(!StringUtil.isEmpty(jpmCardSeq.getUserCode())){
//			return returnPwd;
//		}
//		jpmCardSeq.setCreateTime(new Date());
//		jpmCardSeq.setUserCode(userCode);
//		jpmCardSeq.setState("1");
//		dao.saveJpmCardSeq(jpmCardSeq);
//		
//		return returnPwd;
//	}
	/**
	 * 
	 */
	private void saveUserJpmCardSeq2(JpoMemberOrder jpoMemberOrder,Set<JpoMemberOrderList> jpoMemberOrderList,String oldCard,String newCard){
		//瑕侀�丣OMYE鍙锋�绘暟
		int jmQty=0;
		
		//棣栬喘鍗曘�佸崌绾�
		if("1".equals(jpoMemberOrder.getOrderType())||"2".equals(jpoMemberOrder.getOrderType())){
			if("6".equals(newCard)){
				jmQty+=(StringUtil.formatInt("5")-StringUtil.formatInt(oldCard));
			}else{
				jmQty+=(StringUtil.formatInt(newCard)-StringUtil.formatInt(oldCard));
			}
		}
		//涓�绾у簵閾洪璐�
		if("6".equals(jpoMemberOrder.getOrderType())){
			jmQty+=10;
		}
		//浜岀骇棣嗛璐崟
		if("11".equals(jpoMemberOrder.getOrderType())){
			jmQty+=6;
		}
		//浜岀骇棣嗗崌绾у崟
		if("12".equals(jpoMemberOrder.getOrderType())){
			jmQty+=4;
		}
		
		java.util.Calendar startc=java.util.Calendar.getInstance();
		startc.set(2010, 4, 18, 00, 00, 00);
		
		java.util.Calendar endc=java.util.Calendar.getInstance();
		endc.set(2010, 4, 23, 23, 59, 59);
		
		java.util.Date startDate=startc.getTime();
		java.util.Date endDate=endc.getTime();
		Date curDate=new Date();
		
		
		//棰愰『涓汉鎶ょ悊濂楄 100581
		for (JpoMemberOrderList list : jpoMemberOrderList) {
			//WuCF JpmProductSaleNew Modify By WuCF 20130917
			/*if(100581==list.getJpmProductSale().getUniNo()){*/
			if(100581==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo()){
				jmQty+=list.getQty();
			}
			//11鍙峰埌16鍙� 淇冮攢
			//101821   101823
//			if((curDate.after(startDate))&&(curDate.before(endDate))){
//				if(101821==list.getJpmProductSale().getUniNo()||101823==list.getJpmProductSale().getUniNo()){
//					jmQty+=list.getQty();
//				}
//			}
			//18鍙峰埌23鍙蜂績閿�
			
			if((curDate.after(startDate))&&(curDate.before(endDate))){
				//WuCF JpmProductSaleNew Modify By WuCF 20130917
				/*if(101943==list.getJpmProductSale().getUniNo()){//3000鍏冪壒鎯犲瑁咃細涔�3000閫�3000銆愬唴鍚�2濂楅瀹夊瑁�+3濂楅椤哄瑁咃紙閫�3涓猨oyme鍙风爜锛夐��5鐩掔孩鏅ぉ銆�
					jmQty+=list.getQty()*3;
				}
				if(101945==list.getJpmProductSale().getUniNo()){//6000鍏冪壒鎯犲瑁咃細涔�6000閫�6000銆愬唴鍚�5濂楅瀹夊瑁�+5濂楅椤哄瑁咃紙閫�5涓猨oyme鍙风爜锛夐��10鐩掔孩鏅ぉ銆�
					jmQty+=list.getQty()*5;
				}
				if(101948==list.getJpmProductSale().getUniNo()){//12000鍏冪壒鎯犲瑁咃細涔�12000閫�12000銆愬唴鍚�4濂楅瀹夊瑁�+4濂楅椤哄瑁咃紙閫�4涓猨oyme鍙风爜锛�+ 4濂楁姢鍏�+6涓ソ鍙ｆ澂+ 1濂楁梾琛屽簥鍨� 閫�20鐩掔孩鏅ぉ銆�
					jmQty+=list.getQty()*4;
				}*/
				if(101943==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo()){//3000鍏冪壒鎯犲瑁咃細涔�3000閫�3000銆愬唴鍚�2濂楅瀹夊瑁�+3濂楅椤哄瑁咃紙閫�3涓猨oyme鍙风爜锛夐��5鐩掔孩鏅ぉ銆�
					jmQty+=list.getQty()*3;
				}
				if(101945==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo()){//6000鍏冪壒鎯犲瑁咃細涔�6000閫�6000銆愬唴鍚�5濂楅瀹夊瑁�+5濂楅椤哄瑁咃紙閫�5涓猨oyme鍙风爜锛夐��10鐩掔孩鏅ぉ銆�
					jmQty+=list.getQty()*5;
				}
				if(101948==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo()){//12000鍏冪壒鎯犲瑁咃細涔�12000閫�12000銆愬唴鍚�4濂楅瀹夊瑁�+4濂楅椤哄瑁咃紙閫�4涓猨oyme鍙风爜锛�+ 4濂楁姢鍏�+6涓ソ鍙ｆ澂+ 1濂楁梾琛屽簥鍨� 閫�20鐩掔孩鏅ぉ銆�
					jmQty+=list.getQty()*4;
				}
			}
			
			
			
		}
		//
		
		
		this.saveJpmCardSeqByQty(jmQty, jpoMemberOrder);
		
	}
	private void saveJpmCardSeqByQty(int jmQty,JpoMemberOrder jpoMemberOrder){
		if(jmQty>0){
			String ids=dao.getJpmCardSeqsNotUse(jmQty);
			/**
			 * 2011-9-5 淇敼锛屼笉妫�鏌OYME鍙锋湁娌℃湁绌虹殑
			 */
			if(ids!=null){
				//throw new AppException("cardNo.not.find");
				List<JpmCardSeq> list=dao.getJpmCardSeqByIds(ids);
				if(list.size()==jmQty){
					//throw new AppException("cardNo.not.find");

					for (int i = 0; i < list.size(); i++) {
						JpmCardSeq seq=list.get(i);
						
						seq.setCreateTime(new Date());
						seq.setState("1");
						seq.setUserCode(jpoMemberOrder.getSysUser().getUserCode());
						
						//seq.setMolId();
						
						seq.setMemberOrderNo(jpoMemberOrder.getMemberOrderNo());
						dao.saveJpmCardSeq(seq);
					}
				}
			}
			
		}
	}
	/**
	 * 淇冮攢鏂规
	 * 淇冮攢鏃堕棿锛�2010骞�3鏈�16鏃�-4鏈�30鏃�
	 * 8 24
	 */
	private void saveUserJpmCardSeq1(JpoMemberOrder jpoMemberOrder,Set<JpoMemberOrderList> jpoMemberOrderList){
		BigDecimal totalPrice=new BigDecimal(0); 
		
		int ddqty=0;
		
		for (JpoMemberOrderList list : jpoMemberOrderList) {
			/*if(8==list.getJpmProductSale().getUniNo()||24==list.getJpmProductSale().getUniNo()||
					11==list.getJpmProductSale().getUniNo()||
					100581==list.getJpmProductSale().getUniNo()||
					100489==list.getJpmProductSale().getUniNo()||
					25==list.getJpmProductSale().getUniNo()||
					19==list.getJpmProductSale().getUniNo()||
					18==list.getJpmProductSale().getUniNo()||
					17==list.getJpmProductSale().getUniNo()||
					100301==list.getJpmProductSale().getUniNo()||
					21==list.getJpmProductSale().getUniNo()){*/
			//WuCF JpmProductSaleNew Modify By WuCF 20130917
			if(8==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo()||24==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo()||
					11==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo()||
					100581==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo()||
					100489==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo()||
					25==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo()||
					19==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo()||
					18==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo()||
					17==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo()||
					100301==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo()||
					21==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo()){
//					if("1".equals(jpoMemberOrder.getOrderType())){
//						totalPrice = totalPrice.add(list.getPrice().divide(new BigDecimal(0.9),0, BigDecimal.ROUND_UP).multiply(new BigDecimal(list.getQty())));
//					}else{
						totalPrice = totalPrice.add(list.getPrice().multiply(new BigDecimal(list.getQty())));
//					}
			}
			if("6".equals(jpoMemberOrder.getOrderType()) && 23==list.getJpmProductSaleTeamType().getJpmProductSaleNew().getUniNo() ){
				ddqty+=list.getQty();
			}
		}
		BigDecimal res = totalPrice.divide(new BigDecimal(600) ,0, BigDecimal.ROUND_DOWN);
		
		BigDecimal qty=res.multiply(new BigDecimal(2));
		
		int qtyInt=qty.intValue();
		
		qtyInt+=ddqty*50;

		//浜岀骇鐢熸椿棣嗛璐�佸崌绾�
		if("11".equals(jpoMemberOrder.getOrderType())){
			qtyInt+=20;
		}
		if("12".equals(jpoMemberOrder.getOrderType())){
			qtyInt+=30;
		}
		
		if(qtyInt>0){
			String ids=dao.getJpmCardSeqsNotUse(qtyInt);
			if(ids==null){
				throw new AppException("cardNo.not.find");
			}
			List<JpmCardSeq> list=dao.getJpmCardSeqByIds(ids);
			if(list.size()!=qtyInt){
				throw new AppException("cardNo.not.find");
			}
			for (int i = 0; i < list.size(); i++) {
				JpmCardSeq seq=list.get(i);
				
				seq.setCreateTime(new Date());
				seq.setState("1");
				seq.setUserCode(jpoMemberOrder.getSysUser().getUserCode());
				
				//seq.setMolId();
				
				seq.setMemberOrderNo(jpoMemberOrder.getMemberOrderNo());
				dao.saveJpmCardSeq(seq);
			}
		}
	}

	public void saveUserJpmCardSeqByUser(JpmCardSeq jpmCardSeq, int qty) {
		String ids=dao.getJpmCardSeqsNotUse(qty);
		if(ids==null){
			throw new AppException("cardNo.not.find");
		}
		List<JpmCardSeq> list=dao.getJpmCardSeqByIds(ids);
		if(list.size()!=qty){
			throw new AppException("cardNo.not.find");
		}
		for (int i = 0; i < list.size(); i++) {
			JpmCardSeq seq=list.get(i);
			
			seq.setCreateTime(new Date());
			seq.setState("1");
			seq.setUserCode(jpmCardSeq.getUserCode());
			seq.setMemberOrderNo(jpmCardSeq.getMemberOrderNo());
			dao.saveJpmCardSeq(seq);
		}
	}

	public List getJpmCardSeqGrade(CommonRecord crm,String strAction) {
		return dao.getJpmCardSeqGrade(crm,strAction);
	}
	
	
}
