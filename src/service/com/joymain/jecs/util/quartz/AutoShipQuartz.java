package com.joymain.jecs.util.quartz;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.joymain.jecs.bd.model.BdPeriod;
import com.joymain.jecs.bd.service.BdPeriodManager;
import com.joymain.jecs.fi.model.JfiUsCreditCardLog;
import com.joymain.jecs.fi.service.JfiBankbookJournalManager;
import com.joymain.jecs.fi.service.JfiUsCreditCardLogManager;
import com.joymain.jecs.pm.model.JpmProductSaleTeamType;
import com.joymain.jecs.pm.service.JpmProductSaleManager;
import com.joymain.jecs.po.model.JpoMemberOrder;
import com.joymain.jecs.po.model.JpoMemberOrderFee;
import com.joymain.jecs.po.model.JpoMemberOrderList;
import com.joymain.jecs.po.model.JpoMemberOrderListTask;
import com.joymain.jecs.po.model.JpoMemberOrderTask;
import com.joymain.jecs.po.model.JpoShippingFee;
import com.joymain.jecs.po.service.JpoMemberOrderManager;
import com.joymain.jecs.po.service.JpoMemberOrderTaskManager;
import com.joymain.jecs.po.service.JpoShippingFeeManager;
import com.joymain.jecs.sys.model.SysListValue;
import com.joymain.jecs.sys.model.SysUser;
import com.joymain.jecs.sys.service.SysIdManager;
import com.joymain.jecs.sys.service.SysListValueManager;
import com.joymain.jecs.sys.service.SysUserManager;
import com.joymain.jecs.util.bean.BeanUtil;
import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.uspay.UsCreditCard;
import com.joymain.jecs.util.uspay.UsCreditCardUtil;

public class AutoShipQuartz {
	private JpoMemberOrderManager jpoMemberOrderManager = null;
	private JpoMemberOrderTaskManager jpoMemberOrderTaskManager = null;
	private JdbcTemplate jdbcTemplate = null;
	private SysUserManager sysUserManager = null;
    private SysIdManager sysIdManager = null;
    private JpoShippingFeeManager jpoShippingFeeManager = null;
    private SysListValueManager sysListValueManager = null;
    private BdPeriodManager bdPeriodManager = null;
    private JpmProductSaleManager jpmProductSaleManager = null;
    private UsCreditCardUtil usCreditCardUtil;
    private JfiUsCreditCardLogManager jfiUsCreditCardLogManager;
    private JfiBankbookJournalManager jfiBankbookJournalManager;
    private boolean isStart = false;
    
	public void work() {
		if(isStart==true){
			return;
		}
		isStart = true;
		try{
			DataSource ds = jdbcTemplate.getDataSource();
			Connection con = null;
			Statement ps = null;
			ResultSet rs = null;
			try {
				con = ds.getConnection(); //1。建立Connection
				
				Date dateS = new Date();
				BdPeriod bdPeriod = this.bdPeriodManager.getBdPeriodByTime(dateS, null);
				if (bdPeriod == null) {
					System.out.println("期别不存在");
	    			return;
	    		}
				
				int dayOfWeek = DateUtil.daysBetweenDates(dateS,bdPeriod.getStartTime()) + 1;
				long actionYear = bdPeriod.getWyear() * 10000l;
				long actionMonth = actionYear + bdPeriod.getWmonth() * 100l;
				long actionTime =  actionMonth + bdPeriod.getWweek();
				int actionWeek =  bdPeriod.getAweek();
				
				ps = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //2。创建preparedStatement
				String sql = "select mot_id,user_code from jpo_member_order_task where task_type = 0 and action_time <= " + actionTime + " and action_date=" + dayOfWeek + " and action_week = " + actionWeek;
				rs = ps.executeQuery(sql); //4.执行查询
				while (rs.next()){//循环所有任务,入库待执行任务
					Statement ps1 = null;
					ResultSet rs1 = null;
					try{
						ps1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //2。创建preparedStatement;
						rs1 = ps1.executeQuery("select user_code from jpo_member_order_task where task_type = 1 and user_code = '" + rs.getString("user_code") + "' and action_time = " + actionTime + " and action_date='" + dayOfWeek + "'" + " and action_week = " + actionWeek);
						//只对执行时间小于或等于今天，执行日期为今天的
						if (!rs1.next()){
							JpoMemberOrderTask jpoMemberOrderTask = jpoMemberOrderTaskManager.getJpoMemberOrderTask(rs.getString("mot_id"));

							Set jpoMemberOrderListTaskSet = new HashSet(0);
							Iterator ite = jpoMemberOrderTask.getJpoMemberOrderListTask().iterator();
							
							try {
								JpoMemberOrderTask jpoMemberOrderTaskNew = (JpoMemberOrderTask)BeanUtil.copyBean(jpoMemberOrderTask);
								jpoMemberOrderTaskNew.setMotId(null);
								jpoMemberOrderTaskNew.setActionTime(actionTime);
								jpoMemberOrderTaskNew.setActionDate(dayOfWeek);
								jpoMemberOrderTaskNew.setActionWeek(actionWeek);
								jpoMemberOrderTaskNew.setCreateTime(dateS);
								jpoMemberOrderTaskNew.setStatus("1");
								jpoMemberOrderTaskNew.setTaskType("1");
								jpoMemberOrderTaskNew.setJpoMemberOrderListTask(jpoMemberOrderListTaskSet);
								while(ite.hasNext()){
									JpoMemberOrderListTask jpoMemberOrderListTaskNew = (JpoMemberOrderListTask)BeanUtil.copyBean(ite.next());
									jpoMemberOrderListTaskNew.setMoltId(null);
									jpoMemberOrderListTaskNew.setJpoMemberOrderTask(jpoMemberOrderTaskNew);
									jpoMemberOrderListTaskSet.add(jpoMemberOrderListTaskNew);
								}
								jpoMemberOrderTaskManager.saveJpoMemberOrderTask(jpoMemberOrderTaskNew);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}catch(SQLException ex){
						ex.printStackTrace();
					}finally{
						if(rs1 != null){
							rs1.close(); 
						}
						if (ps1 != null){
							ps1.close();
						}
					}
				}
				
				Statement ps2 = null;
				ResultSet rs2 = null;
				try{
					ps2 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //2。创建preparedStatement;
					rs2 = ps2.executeQuery("select mot_id from jpo_member_order_task where task_type = 1 and status = 1 and action_time = " + actionTime + " and action_date=" + dayOfWeek + "" + " and action_week = " + actionWeek);
					//只对今天的任务进行操作
					while(rs2.next()){
						//判断是否开过单
						JpoMemberOrderTask jpoMemberOrderTask = jpoMemberOrderTaskManager.getJpoMemberOrderTask(rs2.getString("mot_id"));
						
						Statement ps3 = null;
						ResultSet rs3 = null;
						try{
							ps3 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //2。创建preparedStatement;
							rs3 = ps3.executeQuery("select mot_id from jpo_member_order_task where task_type = 1 and status = 2 and user_code = '" + jpoMemberOrderTask.getUserCode() + "' and action_time > " + actionMonth + " and action_time <= " + actionTime);
							if (rs3.next()){
								jpoMemberOrderTask = jpoMemberOrderTaskManager.getJpoMemberOrderTask(rs2.getString("mot_id"));
								jpoMemberOrderTask.setRemark("本月已经AUTOSHIP");
								jpoMemberOrderTask.setStatus("3");
								jpoMemberOrderTask.setRetMsg("本月已经AUTOSHIP");
			        			jpoMemberOrderTaskManager.saveJpoMemberOrderTask(jpoMemberOrderTask);
			            		continue;
							}
						}catch(SQLException ex){
							ex.printStackTrace();
							continue;
						}finally{
							if(rs3 != null){
								rs3.close(); 
							}
							if (ps3 != null){
								ps3.close();
							}
						}
						
						//====开单
						Statement ps4 = null;
						ResultSet rs4 = null;
						JpoMemberOrder jpoMemberOrder = null;
						try{
							ps4 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //2。创建preparedStatement;
							rs4 = ps4.executeQuery("select mo_id,user_code,status from jpo_member_order where mot_id =" + rs2.getString("mot_id"));
							
							if (!rs4.next()){
								//没有生成订单
								jpoMemberOrder = new JpoMemberOrder();
								jpoMemberOrder.setAddress(jpoMemberOrderTask.getAddress());
								jpoMemberOrder.setCity(jpoMemberOrderTask.getCity());
								jpoMemberOrder.setCompanyCode(jpoMemberOrderTask.getCompanyCode());
								jpoMemberOrder.setCountryCode(jpoMemberOrderTask.getCountryCode());
								jpoMemberOrder.setOrderUserCode(jpoMemberOrderTask.getCreateUser());
								jpoMemberOrder.setUserSpCode(jpoMemberOrderTask.getUserCode());
								jpoMemberOrder.setLocked("0");
								jpoMemberOrder.setIsSpecial("0");
								jpoMemberOrder.setOrderTime(new Date());
								jpoMemberOrder.setOrderType("15");
								jpoMemberOrder.setDistrict(jpoMemberOrderTask.getDistrict());
								jpoMemberOrder.setEmail(jpoMemberOrderTask.getEmail());
								jpoMemberOrder.setFirstName(jpoMemberOrderTask.getFirstName());
								jpoMemberOrder.setLastName(jpoMemberOrderTask.getLastName());
								String memberOrderNo = sysIdManager.buildIdStr("pono");//生成订单编号
								jpoMemberOrder.setMemberOrderNo(memberOrderNo);
								jpoMemberOrder.setMobiletele(jpoMemberOrderTask.getMobiletele());
								jpoMemberOrder.setTown(jpoMemberOrderTask.getTown());
								jpoMemberOrder.setPayMode("0");
								jpoMemberOrder.setPickup("0");
								jpoMemberOrder.setPhone(jpoMemberOrderTask.getPhone());
								jpoMemberOrder.setPostalcode(jpoMemberOrderTask.getPostalcode());
								jpoMemberOrder.setProvince(jpoMemberOrderTask.getProvince());
								jpoMemberOrder.setStatus("1");
								jpoMemberOrder.setSubmitStatus("2");
								jpoMemberOrder.setIsPay("1");
								jpoMemberOrder.setSubmitTime(new Date());
								jpoMemberOrder.setMotId(jpoMemberOrderTask.getMotId());
								jpoMemberOrder.setSubmitUserCode(jpoMemberOrderTask.getCreateUser());
								SysUser sysUser = sysUserManager.getSysUser(jpoMemberOrderTask.getUserCode());
								jpoMemberOrder.setSysUser(sysUser);

								jpoMemberOrderTask = jpoMemberOrderTaskManager.getJpoMemberOrderTask(rs2.getString("mot_id"));
								Set jpoMemberOrderSet = null;
				        		jpoMemberOrderSet = this.fillInJfoMemberOrderList(jpoMemberOrderTask,jpoMemberOrder);//生成订单明细
				        		if(jpoMemberOrderSet==null){//检查订单总数是否小于0
				        			jpoMemberOrderTask = jpoMemberOrderTaskManager.getJpoMemberOrderTask(rs2.getString("mot_id"));
				        			jpoMemberOrderTask.setRemark("开单失败,订单明细生成不成功。");
				        			jpoMemberOrderTask.setStatus("4");
				        			jpoMemberOrderTask.setRetMsg("开单失败,订单明细生成不成功。");
				        			jpoMemberOrderTaskManager.saveJpoMemberOrderTask(jpoMemberOrderTask);
				            		continue;
				        		}
				        		
				        		Set jpoMemberOrderFeeSet = null;
				        		jpoMemberOrderFeeSet = this.fillInJfoMemberOrderFee(jpoMemberOrder,jpoMemberOrderSet);//生成订单明细
				            	if(jpoMemberOrderFeeSet == null || jpoMemberOrderFeeSet.size()==0){
				            		jpoMemberOrderTask = jpoMemberOrderTaskManager.getJpoMemberOrderTask(rs2.getString("mot_id"));
				            		jpoMemberOrderTask.setRemark("开单失败,没有指定物流公司。");
				        			jpoMemberOrderTask.setStatus("5");
				        			jpoMemberOrderTask.setRetMsg("开单失败,没有指定物流公司。");
				        			jpoMemberOrderTaskManager.saveJpoMemberOrderTask(jpoMemberOrderTask);
				            		continue;
				            	}
				        		this.calcOrderAmount(jpoMemberOrderSet,jpoMemberOrderFeeSet,jpoMemberOrder);//计算总PV总金额
				        		jpoMemberOrderManager.editJpoMemberOrder(jpoMemberOrder, jpoMemberOrderSet, jpoMemberOrderFeeSet);
							}else{
								if("1".equals(rs4.getString("status"))){
									jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrder(rs4.getString("mo_id"));
								}else{
									jpoMemberOrderTask = jpoMemberOrderTaskManager.getJpoMemberOrderTask(rs2.getString("mot_id"));
				            		jpoMemberOrderTask.setRemark("开单失败,订单已审核。");
				        			jpoMemberOrderTask.setStatus("6");
				        			jpoMemberOrderTask.setRetMsg("开单失败,订单已审核。");
				        			jpoMemberOrderTaskManager.saveJpoMemberOrderTask(jpoMemberOrderTask);
				            		continue;
								}
							}
						}catch(SQLException ex){
							ex.printStackTrace();
							continue;
						}catch(Exception ex){
							ex.printStackTrace();
							jpoMemberOrderTask = jpoMemberOrderTaskManager.getJpoMemberOrderTask(rs2.getString("mot_id"));
		        			jpoMemberOrderTask.setRemark("开单失败,订单明细生成不成功。");
		        			jpoMemberOrderTask.setStatus("4");
		        			jpoMemberOrderTask.setRetMsg(ex.getMessage());
		        			jpoMemberOrderTaskManager.saveJpoMemberOrderTask(jpoMemberOrderTask);
		            		continue;
						}finally{
							if(rs4 != null){
								rs4.close(); 
							}
							if (ps4 != null){
								ps4.close();
							}
						}
						if(jpoMemberOrder!=null){
							//===============刷卡
							try{
								if("US".equals(jpoMemberOrderTask.getCompanyCode())){
									UsCreditCard usCreditCard = new UsCreditCard();
									usCreditCard.setCcnumber(jpoMemberOrderTask.getCardCcNumber());
									usCreditCard.setCcexp(jpoMemberOrderTask.getCardCcExp());
									usCreditCard.setCvv(jpoMemberOrderTask.getCardCvv());
									usCreditCard.setFirstname(jpoMemberOrderTask.getCardFirstName());
									usCreditCard.setLastname(jpoMemberOrderTask.getCardLastName());
									usCreditCard.setAddress1(jpoMemberOrderTask.getCardAddress());
									usCreditCard.setCity(jpoMemberOrderTask.getCardCity());
									usCreditCard.setState(jpoMemberOrderTask.getCardState());
									usCreditCard.setZip(jpoMemberOrderTask.getCardZip());
									usCreditCard.setEmail(jpoMemberOrderTask.getEmail());
									JfiUsCreditCardLog jfiUsCreditCardLog = usCreditCardUtil.payAndLogCreditCard(usCreditCard,jpoMemberOrder.getAmount().toString(), jpoMemberOrder.getMoId().toString());
							        jfiUsCreditCardLog.setUserCode(jpoMemberOrderTask.getUserCode());
							        jfiUsCreditCardLog.setCreateTime(new Date());
							        jfiUsCreditCardLogManager.saveJfiUsCreditCardLog(jfiUsCreditCardLog);
							        SysUser sysUser = jpoMemberOrder.getSysUser();
							        if("1".equals(jfiUsCreditCardLog.getResponse())){
							        	jfiBankbookJournalManager.saveJfiPayDataVerifyByUsCreditCard("US",sysUser , jpoMemberOrder.getAmount(), sysUser.getUserCode(), sysUser.getUserName(),jfiUsCreditCardLog.getTransactionId(),jfiUsCreditCardLog);
							        	jpoMemberOrder.setIsPay("1");
							    		jpoMemberOrderManager.saveJpoMemberOrder(jpoMemberOrder);
							        }else{
										jpoMemberOrderTask = jpoMemberOrderTaskManager.getJpoMemberOrderTask(rs2.getString("mot_id"));
					            		jpoMemberOrderTask.setRemark("信用卡信息有误。");
					        			jpoMemberOrderTask.setStatus("9");
					        			jpoMemberOrderTaskManager.saveJpoMemberOrderTask(jpoMemberOrderTask);
					            		continue;
							        }
								}
							}catch(Exception e){
								jpoMemberOrderTask = jpoMemberOrderTaskManager.getJpoMemberOrderTask(rs2.getString("mot_id"));
			            		jpoMemberOrderTask.setRemark("刷卡失败。");
			        			jpoMemberOrderTask.setStatus("10");
			        			jpoMemberOrderTask.setRetMsg(e.getMessage());
			        			jpoMemberOrderTaskManager.saveJpoMemberOrderTask(jpoMemberOrderTask);
			            		continue;
							}
							
							//===============审核
							SysUser operatorSysUser = sysUserManager.getSysUser(jpoMemberOrderTask.getCreateUser());
							try{
								jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrder, operatorSysUser,"1");
								jpoMemberOrderManager.sendJmsCoin(jpoMemberOrder, operatorSysUser);
			            		jpoMemberOrderTask.setRemark("成功AUTOSHIP!");
			        			jpoMemberOrderTask.setStatus("2");
			        			jpoMemberOrderTask.setRetMsg("成功AUTOSHIP!");
			        			jpoMemberOrderTaskManager.saveJpoMemberOrderTask(jpoMemberOrderTask);
			            		continue;
							}catch(Exception e){
								jpoMemberOrderTask = jpoMemberOrderTaskManager.getJpoMemberOrderTask(rs2.getString("mot_id"));
			            		jpoMemberOrderTask.setRemark("审单失败。");
			        			jpoMemberOrderTask.setStatus("8");
			        			jpoMemberOrderTask.setRetMsg(e.getMessage());
			        			jpoMemberOrderTaskManager.saveJpoMemberOrderTask(jpoMemberOrderTask);
			            		continue;
							}
						}else{
							jpoMemberOrderTask = jpoMemberOrderTaskManager.getJpoMemberOrderTask(rs2.getString("mot_id"));
		            		jpoMemberOrderTask.setRemark("开单出错。");
		        			jpoMemberOrderTask.setStatus("7");
		        			jpoMemberOrderTask.setRetMsg("开单出错。");
		        			jpoMemberOrderTaskManager.saveJpoMemberOrderTask(jpoMemberOrderTask);
		            		continue;
						}
					}
				}catch(SQLException ex){
					ex.printStackTrace();
				}finally{
					try{
						if(rs2 != null){
							rs.close(); 
						}
						if (ps2 != null){
							ps.close();
						}
					} catch (SQLException ex) {
						// Log and ignore  
						ex.printStackTrace();
					}
				}
				
			}catch(SQLException ex){
				ex.printStackTrace();
			}finally{
				try{
					if(rs != null){
						rs.close(); 
					}
					if (ps != null){
						ps.close();
					}
					if (con != null){
						con.close(); //如果没有连接池的话，不要轻易关。connection属于耗费资源:)
					}
				} catch (SQLException ex) {
					// Log and ignore  
					ex.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			isStart = false;
		}
    }
    
    /**
     * 计算费用
     * @param jpoMemberOrderSet
     * @param jpoMemberOrder
     */
    private void calcOrderAmount(Set jpoMemberOrderSet, Set jpoMemberOrderFeeSet, JpoMemberOrder jpoMemberOrder){
    	
    	BigDecimal sumPrice = new BigDecimal(0);
    	BigDecimal sumPV = new BigDecimal(0);
    	
    	Iterator its1 = jpoMemberOrderSet.iterator();
    	while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			sumPV = sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
		}
    	
    	Iterator its2 = jpoMemberOrderFeeSet.iterator();
    	while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2.next();
			sumPrice = sumPrice.add(jpoMemberOrderFee.getFee());
		}
    	
    	jpoMemberOrder.setAmount(sumPrice);
    	jpoMemberOrder.setPvAmt(sumPV);
    }
    
    /**
     * 增加费用
     * @param request
     * @param jpoMemberOrder
     * @param jpoMemberOrderSet
     * @return
     */
    private Set fillInJfoMemberOrderFee(JpoMemberOrder jpoMemberOrder,Set jpoMemberOrderSet){
    	Set jpoMemberOrderFeeSet = new HashSet(0);
    	if("CN".equals(jpoMemberOrder.getCompanyCode()) || "HK".equals(jpoMemberOrder.getCompanyCode()) ){
        	//物流费
        	JpoMemberOrderFee jpoMemberOrderFee = this.shippingFeeCalc(jpoMemberOrder, jpoMemberOrderSet);
        	if(jpoMemberOrderFee!=null){
        		//重销单物流费店铺出
        		jpoMemberOrderFee.setFee(new BigDecimal("0"));
        		jpoMemberOrderFeeSet.add(jpoMemberOrderFee);
        	}
    	}else{
    		JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
    		jpoMemberOrderFee.setDetailType("0000");
    		jpoMemberOrderFee.setFee(new BigDecimal(0));
    		jpoMemberOrderFee.setFeeType("1");//物流费
    		jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
    		jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
    		jpoMemberOrderFeeSet.add(jpoMemberOrderFee);
    	}
    	return jpoMemberOrderFeeSet;
    }
    
    /**
     * 计算物流费
     * @param jpoShippingFee
     * @param sum
     * @param dtProduct
     * @param request
     * @return
     */
    private JpoMemberOrderFee shippingFeeCalc(JpoMemberOrder jpoMemberOrder, Set jpoMemberOrderSet){
    	if("CN".equals(jpoMemberOrder.getCompanyCode())){
    		int dtProduct = 0;
    		BigDecimal sumWeight = new BigDecimal(0);
    		BigDecimal sumVolume = new BigDecimal(0);
    		Iterator its1 = jpoMemberOrderSet.iterator();
    		List<SysListValue> sysListValues = sysListValueManager.getSysListValuesByCode("bd.cardtype","CN");
        	while (its1.hasNext()) {
        		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        		sumWeight = sumWeight.add(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getWeight().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
        		sumVolume = sumVolume.add(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getVolume().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
	    		for(int i = 0 ; i < sysListValues.size() ; i++){
	    			SysListValue sysListValue = (SysListValue)sysListValues.get(i);
	    			if(sysListValue.getValueCode().equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())){
	    				dtProduct = 1;
	    				break;
	    			}
	    		}
        	}
    		if("163732".equals(jpoMemberOrder.getProvince())){
    			dtProduct = 0;
    		}
    		CommonRecord crm = new CommonRecord();
    		crm.addField("countryCode", jpoMemberOrder.getCountryCode());
    		crm.addField("province", jpoMemberOrder.getProvince());
    		crm.addField("city", jpoMemberOrder.getCity());
    		crm.addField("district", jpoMemberOrder.getDistrict());
    		if(dtProduct==1){
    			//大田
    			crm.addField("shippingType", "DTW");
    		}else if(dtProduct==0){
    			//宅急送
    			crm.addField("shippingType", "ZJS");
    		}

    		List jpoShippingFees = jpoShippingFeeManager.getJpoShippingFeesByCrm(crm, null);
    		if(jpoShippingFees.size()>0){
    			JpoShippingFee jpoShippingFee = (JpoShippingFee)jpoShippingFees.get(0);
        		JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
        		jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
        		jpoMemberOrderFee.setFeeType("1");//物流费
        		jpoMemberOrderFee.setDetailType(jpoShippingFee.getShippingType());
        		BigDecimal sumPrice = new BigDecimal(0);
        		if(dtProduct==1){
        			//大田
        			sumPrice = sumVolume.multiply(jpoShippingFee.getShippingFee());
        			sumPrice = sumPrice.add(jpoShippingFee.getShippingRefee());
        		}else if(dtProduct==0){
        			//宅急送
        			if(sumWeight.compareTo(new BigDecimal(1)) == 1){
        				BigDecimal sumSubWeight = sumWeight.subtract(new BigDecimal(1)).setScale(0, BigDecimal.ROUND_UP);
        				sumPrice = sumPrice.add(jpoShippingFee.getShippingFee());
        				sumPrice = sumPrice.add(sumSubWeight.multiply(jpoShippingFee.getShippingRefee()));
        			}else{
        				sumPrice = sumPrice.add(jpoShippingFee.getShippingFee());
        			}
            	}
        		jpoMemberOrderFee.setFee(sumPrice);
        		jpoMemberOrder.setConsumerAmount(sumPrice);
        		return jpoMemberOrderFee;
    		}
    	}else if("HK".equals(jpoMemberOrder.getCompanyCode())){
    		JpoMemberOrderFee jpoMemberOrderFee = new JpoMemberOrderFee();
    		jpoMemberOrderFee.setJpoMemberOrder(jpoMemberOrder);
    		jpoMemberOrderFee.setFeeType("1");//物流费
    		jpoMemberOrderFee.setDetailType("0000");
    		jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
    		return jpoMemberOrderFee;
    	}else{
    		
    	}
    	return null;
    }
    
    /**
     * 生成订单明细
     * @param request
     * @param poMemberOrder
     * @param defSysUser
     * @return
     */
    private Set fillInJfoMemberOrderList(JpoMemberOrderTask jpoMemberOrderTask, JpoMemberOrder jpoMemberOrder){
    	
        Set jpoMemberOrderListSet = new HashSet(0);
        BigDecimal sumPrice = new BigDecimal(0);
    	BigDecimal sumPV = new BigDecimal(0);
    	
    	Iterator ite = jpoMemberOrderTask.getJpoMemberOrderListTask().iterator();
		while(ite.hasNext()){
			JpoMemberOrderListTask jpoMemberOrderListTask = (JpoMemberOrderListTask)ite.next();
			String qty = String.valueOf(jpoMemberOrderListTask.getQty());
			if( !StringUtils.isEmpty(qty) && !"0".equals(qty)){
				if(Integer.parseInt(qty)<0){
					return null;
				}
				JpmProductSaleTeamType jpmProductSale1 = jpoMemberOrderListTask.getJpmProductSaleTeamType();
				JpmProductSaleTeamType jpmProductSale = jpmProductSaleManager.getJpmProductSaleTeamType(jpmProductSale1.getPttId().toString());
				if(jpmProductSale==null){
					return null;
				}
				JpoMemberOrderList jpoMemberOrderList = new JpoMemberOrderList();   
				jpoMemberOrderList.setPrice(jpmProductSale.getPrice());
				jpoMemberOrderList.setJpmProductSaleTeamType(jpmProductSale);
				jpoMemberOrderList.setPv(jpmProductSale.getPv());
				jpoMemberOrderList.setQty(Integer.parseInt(qty));
    			jpoMemberOrderList.setJpoMemberOrder(jpoMemberOrder);
    			jpoMemberOrderList.setVolume(jpmProductSale.getJpmProductSaleNew().getVolume());
    			jpoMemberOrderList.setWeight(jpmProductSale.getJpmProductSaleNew().getWeight());
    			sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
    			sumPV = sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty()))) ;
    			jpoMemberOrderListSet.add(jpoMemberOrderList);
			}
		}
		if(jpoMemberOrderListSet.size()==0){
			return null;
		}
    	return jpoMemberOrderListSet;
    }
    
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
	public void setJpoMemberOrderTaskManager(
			JpoMemberOrderTaskManager jpoMemberOrderTaskManager) {
		this.jpoMemberOrderTaskManager = jpoMemberOrderTaskManager;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void setSysUserManager(SysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
	public void setSysIdManager(SysIdManager sysIdManager) {
		this.sysIdManager = sysIdManager;
	}

	public void setSysListValueManager(SysListValueManager sysListValueManager) {
		this.sysListValueManager = sysListValueManager;
	}

	public void setJpoShippingFeeManager(JpoShippingFeeManager jpoShippingFeeManager) {
		this.jpoShippingFeeManager = jpoShippingFeeManager;
	}

	public void setBdPeriodManager(BdPeriodManager bdPeriodManager) {
		this.bdPeriodManager = bdPeriodManager;
	}

	public void setJpmProductSaleManager(JpmProductSaleManager jpmProductSaleManager) {
		this.jpmProductSaleManager = jpmProductSaleManager;
	}

	public void setUsCreditCardUtil(UsCreditCardUtil usCreditCardUtil) {
		this.usCreditCardUtil = usCreditCardUtil;
	}

	public void setJfiUsCreditCardLogManager(
			JfiUsCreditCardLogManager jfiUsCreditCardLogManager) {
		this.jfiUsCreditCardLogManager = jfiUsCreditCardLogManager;
	}

	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}
}
