
package com.joymain.jecs.pd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import net.sf.json.JSONObject;

import com.joymain.jecs.Constants;
import com.joymain.jecs.service.impl.BaseManager;

import com.joymain.jecs.pd.model.Items;
import com.joymain.jecs.pd.model.MailStatus;
import com.joymain.jecs.pd.model.PdLogisticsBaseDetail;
import com.joymain.jecs.pd.model.PdLogisticsBaseNum;
import com.joymain.jecs.pd.model.PdMailReceipt;
import com.joymain.jecs.pd.model.PdSendInfo;
import com.joymain.jecs.pd.model.RspEntity;
import com.joymain.jecs.pd.dao.MailStatusDao;
import com.joymain.jecs.pd.dao.PdLogisticsBaseDetailDao;
import com.joymain.jecs.pd.dao.PdLogisticsBaseNumDao;
import com.joymain.jecs.pd.service.ItemsManager;
import com.joymain.jecs.pd.service.MailStatusManager;
import com.joymain.jecs.pm.model.JpmProduct;
import com.joymain.jecs.pm.service.JpmProductManager;

import com.joymain.jecs.util.data.CommonRecord;
import com.joymain.jecs.util.data.Pager;
import com.joymain.jecs.util.json.InterfaceJsonUtil;
import com.joymain.jecs.util.string.StringUtil;
public class MailStatusManagerImpl extends BaseManager implements MailStatusManager {
	protected final Log log = LogFactory.getLog(getClass());
    private MailStatusDao dao;
    private ItemsManager itemsManager;
	private PdLogisticsBaseNumDao pdLogisticsBaseNumDao;
	private PdLogisticsBaseDetailDao pdLogisticsBaseDetailDao;
	private JpmProductManager jpmProductManager;
    /**
     * Set the Dao for communication with the data layer.
     * @param dao
     */
    public void setMailStatusDao(MailStatusDao dao) {
        this.dao = dao;
    }

    public void setItemsManager(ItemsManager itemsManager) {
		this.itemsManager = itemsManager;
	}

	public void setPdLogisticsBaseNumDao(PdLogisticsBaseNumDao pdLogisticsBaseNumDao) {
		this.pdLogisticsBaseNumDao = pdLogisticsBaseNumDao;
	}

	public void setPdLogisticsBaseDetailDao(
			PdLogisticsBaseDetailDao pdLogisticsBaseDetailDao) {
		this.pdLogisticsBaseDetailDao = pdLogisticsBaseDetailDao;
	}
	
	public void setJpmProductManager(JpmProductManager jpmProductManager) {
		this.jpmProductManager = jpmProductManager;
	}

	/**
     * @see com.joymain.jecs.pd.service.MailStatusManager#getMailStatuss(com.joymain.jecs.pd.model.MailStatus)
     */
    public List getMailStatuss(final MailStatus mailStatus) {
        return dao.getMailStatuss(mailStatus);
    }

    /**
     * @see com.joymain.jecs.pd.service.MailStatusManager#getMailStatus(Long statusId)
     */
    public MailStatus getMailStatus(final Long statusId) {
        return dao.getMailStatus(statusId);
    }

    /**
     * 处理OMS等接口传递过来的数据返回值的处理
     * @author gw 2014-12-05
     * @return string
     */
    public RspEntity getRspEntityString(RspEntity rspEntity){
    	
    	String sub_msg = rspEntity.getSub_msg();
    	if(!StringUtil.isEmpty(sub_msg) && Constants.INTERFACE_NORMAL.equals(sub_msg)){
    		rspEntity.setRsp(Constants.SUCCESS_STRING);//succ表明是接口数据保存成功
    	}else{
    		
    		rspEntity.setCode(Constants.NO_SUCCESS_STRING);//e000006表明应用参数错误
    	}
		//将java对象转换成json对象
    	JSONObject jsonObject = JSONObject.fromObject(rspEntity);
		//将json对象转换成json字符串
		//return jsonObject.toString();
    	return rspEntity;

    }
    
    /**
     * 物流跟踪单号信息保存的功能
     * @author gw 2014-12-03
     * @param mailStatusJson 接口传递过来的json字符串
     * @return String  
     */
    //返回数据类型说明：
    //正常返回的值INTERFACE_NORMAL = "interfaceNormal";
    //接口传递过来的数据保存是报异常INTERFACE_SAVE_EXCEPTION = "interfaceSaveException";
    //接口传递过来的数据是非法的，比如传个空值，传个无效字符等INTERFACE_ILLEGAL_DATA = "interfaceIllegalData";
    //接口传递过来的数据为空的INTERFACE_NULL = "interfaceNull"
    public RspEntity saveMailStatuss(String mailStatusJson) {
    	log.info("物流单号信息保存方法开始（在类MailStatusManagerImpl的方法saveMailStatuss中开始运行）");
		RspEntity rspEntity = new RspEntity();
    	 MailStatus mailStatuss = null;
    	//-----------在进行数据保存之前，先做数据合法性校验
	      if(StringUtil.isEmpty(mailStatusJson)){
	      	    log.info("物流单号信息保存方法（在类MailStatusManagerImpl的方法saveMailStatuss中开始运行）运行异常:json is null");
	    	    rspEntity.setSub_msg(" json is null ");
				return this.getRspEntityString(rspEntity);
	      }else{
	    	    try{
		             Map<String, Class> classMap = new HashMap<String, Class>();//创建针对类的Map
		             classMap.put("items", Items.class);
		             //下面一行是为了测试业务才注释掉，后期还需要补上去   modify gw  2015-01-13
		             org.json.JSONObject jo = new org.json.JSONObject(mailStatusJson);
		             org.json.JSONObject json = jo.getJSONObject("response");
		             String mailStatusJsonS = String.valueOf(json.get("data"));
		             
		             mailStatuss = InterfaceJsonUtil.returnnoJsonToModel(mailStatusJsonS,MailStatus.class,classMap);

		             if(null==mailStatuss){
                    	rspEntity.setSub_msg(" json is null ");
                    	log.info("物流单号信息保存方法（在类MailStatusManagerImpl的方法saveMailStatuss中开始运行）:json转换成对象异常");
         				return this.getRspEntityString(rspEntity);
                     }else{
                    	 //对接口数据的合法性进行校验
                    	 String checkResult =  this.getCheckMailStatusResult(mailStatuss);
                    	 if(Constants.INTERFACE_NORMAL.equals(checkResult)){
                    		    //检验物流单号的信息在数据库中是否存在
                    		    MailStatus mailStatusDBA = dao.getMailStatusByMailNo(mailStatuss.getMail_no());
                    		    //物流单号的信息在数据库中是不存在的
                    		    if(null==mailStatusDBA){
		                    		    String saveResult =   dao.saveMailStatus(mailStatuss);
		                    		    if(Constants.INTERFACE_NORMAL.equals(saveResult)){
		                    		    	 rspEntity.setSub_msg(Constants.INTERFACE_NORMAL);//正常结果返回
		     							     return this.getRspEntityString(rspEntity);
		                    		    }else{
		                    		    	 rspEntity.setSub_msg(" save or update is exception");//正常结果返回
		    							     return this.getRspEntityString(rspEntity);
		                    		    }
                    		    }
                    		    //物流单号的信息在数据库中是存在的
                    		    else{
                    		    	 rspEntity.setSub_msg(" 物流单号信息"+mailStatuss.getMail_no()+"已经保存 ");
                    		    	 return this.getRspEntityString(rspEntity);
                    		    }
                    	 }else{
                    		 rspEntity.setSub_msg(checkResult);//正常结果返回
                    		 return this.getRspEntityString(rspEntity);
                    	 }
                     }
	    	    }catch(Exception e){
                	log.info("物流单号信息保存方法（在类MailStatusManagerImpl的方法saveMailStatuss中开始运行）异常"+e.toString());
	    	    	rspEntity.setSub_msg(e.toString());
	    			return this.getRspEntityString(rspEntity);
	    	    }
	      }
    }

    /**
     * 校验mailStatus数据的合法性
     * @author gw 2014-12-03
     * @param mailStatus
     * @return String
     */
    private String getCheckMailStatusResult(MailStatus mailStatus) {
    	log.info("物流单号信息合法性校验（在类MailStatusManagerImpl的方法getCheckMailStatusResult中）开始");
    	RspEntity rspEntity = new RspEntity();
    	//物流单号的信息第一层非空的校验：物流单号和物流公司非空的校验
    	//非空校验不通过，那么就是非法字符
		if(StringUtil.isEmpty(mailStatus.getMail_no())||StringUtil.isEmpty(mailStatus.getLogist_comp())){
				if(StringUtil.isEmpty(mailStatus.getMail_no())){
					/*rspEntity.setSub_msg(" mail_no is null ");
	    			return this.getRspEntityString(rspEntity);*/
	    			
	    			return "  mail_no(物流单号) is not null ";
				}else if(StringUtil.isEmpty(mailStatus.getLogist_comp())){
					/*rspEntity.setSub_msg(" logist_comp is null");
	    			return this.getRspEntityString(rspEntity);
	    			*/
	    			return " logist_comp(物流公司) is not  null ";
				}else{
					/*rspEntity.setSub_msg(" json is null");
	    			return this.getRspEntityString(rspEntity);
	    			*/
	    			return "  json is null";
				}
		}
		//非空校验通过，那么是有效字符
		else{
			List<Items> items = mailStatus.getItems();
			try{
				//如果集合数据没有，这里就会报异常
				String mm = items.toString();
			}catch(Exception e){
				/*rspEntity.setSub_msg(" items is null");
    			return this.getRspEntityString(rspEntity);
    			*/
    			return " items(跟踪明细) is not null";
			}
			   //items里面有数据
				if(items.size()>0){
					for(int i=0;i<items.size();i++){
						Items item = items.get(i);
						if(null!=item){
							//Date date = item.getAcceptTime();
							//modify fu 2015-09-07  为解决json串转对象（InterfaceJsonUtil类的方法returnnoJsonToModel）时，将时间字段acceptTime转换成了系统当天时间，而不是json串原有的时间，特意将date类型的字段acceptTime修改成string类型
							String date = item.getAcceptTime();
							if(StringUtil.isEmpty(item.getRemark())|| StringUtil.isEmpty(item.getAcceptAddress())){
									if(StringUtil.isEmpty(item.getRemark())){
										/*rspEntity.setSub_msg(" event is null");
						    			return this.getRspEntityString(rspEntity);
						    			*/
						    			return " remark(事件) is not null ";
									}else{
										/*rspEntity.setSub_msg(" acceptAddress is null");
						    			return this.getRspEntityString(rspEntity);*/
										
						    			return " acceptAddress(地点) is not null";
									}
							}else if(null==date){
										/*rspEntity.setSub_msg(" acceptTime is null");
						    			return this.getRspEntityString(rspEntity);
						    			*/
										return " acceptTime(状态时间) is not null ";
							}
						}
					}
					return Constants.INTERFACE_NORMAL;//正常返回的值
				}else{
					 /*rspEntity.setSub_msg(" items is null");
	    			 return this.getRspEntityString(rspEntity);*/
					
				     return " items(跟踪明细) is null ";
				}
		}
	}

	/**
     * @see com.joymain.jecs.pd.service.MailStatusManager#removeMailStatus(Long statusId)
     */
    public void removeMailStatus(final Long statusId) {
        dao.removeMailStatus(statusId);
    }
    //added for getMailStatussByCrm
    public List getMailStatussByCrm(CommonRecord crm, Pager pager){
	    return dao.getMailStatussByCrm(crm,pager);
    }

    /**
	 * 根据物流跟踪单号查询物流的实时信息
	 * @author fu   2015-11-17
	 * @param  mailNo 物流跟踪单号的组合字符串(各个物流单号之间用逗号隔开)
     * @param siNo,memberOrderNo modify by fu 2015-11-27
	 * @return List
	 */
    public List<MailStatus> sendInterfaceByMailStatus(String mailNo,String siNo,String memberOrderNo,PdSendInfo pdSendInfo) {
    	log.info("物流单号信息查询（在类MailStatusManagerImpl的方法sendInterfaceByMailStatus中）开始");
		if(StringUtil.isEmpty(mailNo)){
	    	log.info("物流单号信息查询（在类MailStatusManagerImpl的方法sendInterfaceByMailStatus中）:mailNo is null ");
			return null;
		}else{
			List<MailStatus> mailStatusList = new ArrayList();
			String[] a = mailNo.split(",");
			Long cc = 1L;
			for(int i=0;i<a.length;i++){
				String b = a[i];
				if(!StringUtil.isEmpty(b)){
					MailStatus mailStatus = dao.getMailStatusByMailNo(b);
					//查询物流单号下已发货商品
					String stringEvent = this.getInterfaceByProductNo(b,siNo,memberOrderNo,pdSendInfo);
					if(null!=mailStatus){
				    	log.info("物流单号信息查询（在类MailStatusManagerImpl的方法sendInterfaceByMailStatus中）,查询到物流信息");
						if(StringUtil.isEmpty(stringEvent)){
					    	continue;
					    }else{
					    	List<Items> itemsListTotal = new ArrayList();
					    	Items itemsOne = new Items();
					    	itemsOne.setRemark(stringEvent);
					    	cc = cc+1L;
					    	itemsOne.setItemsId(cc);
					    	itemsListTotal.add(itemsOne);//将物流单号下已发货商品信息放入查询结果
					    	//modify by fu 2016-1-29  用容灾库查询时注释掉
							//List<Items> list = itemsManager.getItemsList(statusId);
					    	//modify by fu 2016-1-29
					    	List<Items> list = mailStatus.getItems();
							if(null!=list){
								for(int p=0;p<list.size();p++){
									itemsListTotal.add(list.get(p));
								}
							}
							mailStatus.setItems(itemsListTotal);
							mailStatusList.add(mailStatus);
					    }
					}else{
						MailStatus mailStatusOne = new MailStatus();
						mailStatusOne.setMail_no(b);
						if(StringUtil.isEmpty(stringEvent)){
					    	continue;
					    }else{
					    	List<Items> itemsListTotal = new ArrayList();
					    	Items itemsNo = new Items();
					    	itemsNo.setRemark(stringEvent);
					    	cc = cc+1L;
					    	itemsNo.setItemsId(cc);
					    	itemsListTotal.add(itemsNo);//将物流单号下已发货商品信息放入查询结果
					    	Items itemsNoTwo = new Items();
					    	itemsListTotal.add(itemsNoTwo);
					    	
					    	mailStatusOne.setItems(itemsListTotal);
							mailStatusList.add(mailStatusOne);
					    }
					}
				}
			}
	    	log.info("物流单号信息查询（在类MailStatusManagerImpl的方法sendInterfaceByMailStatus中）结束");
	    	return mailStatusList;
		}
	}
	
    /**
	 * 查询物流单号下已发货商品
	 * @author fu    2015-11-17
	 * @param  mailNob 物流单号
	 * @param siNo,memberOrderNo modify by fu 2015-11-27 
	 * @param pdSendInfo 发货单  modify by fu 2016-1-18  
	 * @return String
	 */
	public String getInterfaceByProductNo(String mailNob,String siNo,String memberOrderNo,PdSendInfo pdSendInfo) {
		log.info("在类MailStatusManagerImpl的方法中getInterfaceByProductNo运行");
		try{
			    String result = "";
			    String name = "";//物流公司名称
				if(!StringUtil.isEmpty(mailNob)){
					if(null!=pdSendInfo){
						String orderNo = pdSendInfo.getOrderNo();
						if(!StringUtil.isEmpty(orderNo)){
							String mo = orderNo.substring(0, 2);
							if(!"M0".equals(mo)){
								result = "物流公司："+name+"</br>已发货商品：</br>"+"发货单下商品全部已经发货";
								return result;
							}
						}
					}
					//PdLogisticsBaseNum pdLogisticsBaseNum = pdLogisticsBaseNumDao.getPdLogisticsBaseNumByPdLogisticsBaseNumno(mailNob);
					
					//modify by fu 2015-11-27
					List<PdLogisticsBaseNum> pdLogisticsBaseNumList = pdLogisticsBaseNumDao.getPdLogisticsBaseNumByParams(mailNob,siNo,memberOrderNo);

					if(null==pdLogisticsBaseNumList){
						return null;
					}else{
						for(int m=0;m<pdLogisticsBaseNumList.size();m++){
							    PdLogisticsBaseNum pdLogisticsBaseNum = pdLogisticsBaseNumList.get(m);
								name = pdLogisticsBaseNum.getName();
								Long numId = pdLogisticsBaseNum.getNumId();
								List<PdLogisticsBaseDetail> listpdLogisticsBaseDetail = pdLogisticsBaseDetailDao.getPdLogisticsBaseDetailByInterList(pdLogisticsBaseNum);
								if(null!=listpdLogisticsBaseDetail){
									for(int i=0;i<listpdLogisticsBaseDetail.size();i++){
										JpmProduct jpmProduct = jpmProductManager.getJpmProduct(listpdLogisticsBaseDetail.get(i).getproduct_no());
										if(null!=jpmProduct){
											String productName = jpmProduct.getProductName();
											String qty = listpdLogisticsBaseDetail.get(i).getQty();
											//去掉数量的小数点
											if(!StringUtil.isEmpty(qty)){
											    int a = qty.indexOf(".");
											    if(-1<a){
											    	qty = qty.substring(0, a);
											    }
											}
											result +=  productName+"×"+qty+",";
										}
									}
								}
								/*if(!StringUtil.isEmpty(result)){
									result = result.substring(0, result.length()-1);
									result = "物流公司："+name+"</br>已发货商品：</br>"+result;
								}
								else{
								   result = "物流公司:"+name;
								}*/
						}
						if(!StringUtil.isEmpty(result)){
							result = result.substring(0, result.length()-1);
							result = "物流公司："+name+"</br>已发货商品：</br>"+result;
						}else{
							result = "物流公司:"+name;
						}
						return result;
					}
				}
		}catch(Exception e){
		    log.info("在类MailStatusManagerImpl的方法中getInterfaceByProductNo运行失败"+e.toString());
            e.printStackTrace();
		}
		return null;
	}
	
	

	/**
	 * 保存或更新物流单号实时信息(定时器用到)
	 * @author gw 2015
	 * @param mailStatus json转化过来的对象mailStatus
	 * @return boolean true表明数据成功更新到数据库中,false表明数据更新到数据库中失败
	 */
	public boolean sOrUMailStatus(MailStatus mailStatus,Long numId) {
		log.info("在类MailStatusManagerImpl的方法sOrUMailStatus中运行----------------------begin");
		try{
			String reString = null;
			String mailNo = mailStatus.getMail_no();
			//modify by fu 2016-1-27 物流跟踪的功能现在优化放在容灾库---------begin
			if(!StringUtil.isEmpty(mailNo)){
				reString = dao.sOrUMailStatusReport(mailStatus,numId);
				if(Constants.INTERFACE_NORMAL.equals(reString)){
					return true;
				}else{
					return false;
				}
			}
			//modify by fu 2016-1-27 物流跟踪的功能现在优化放在容灾库---------end

			//modify by fu 2016-1-27 物流跟踪的功能现在优化放在容灾库，之前的功能先注释掉---------begin
	/*		if(!StringUtil.isEmpty(mailNo)){
				//查看数据库中是否有这个物流单号的物流实时信息
				MailStatus mailStatusDBA = dao.getMailStatusByMailNo(mailNo);
				//数据库中没有这个物流单号的物流实时信息，那么做新增操作
				if(null!=mailStatusDBA){
					//下面方法可行，但是有一个问题:主键值成几何倍数增长---暂时注释掉
					Long statusId = mailStatusDBA.getStatusId();
					//先删除掉从表数据
					List<Items> items = itemsManager.getItemsList(statusId);
					if(null!=items){
						if(items.size()>0){
							for(int i=0;i<items.size();i++){
								Items item = items.get(i);
								itemsManager.removeItems(item.getItemsId());
							}
						}
					}
					mailStatusDBA.setLogist_comp(mailStatus.getLogist_comp());
					mailStatusDBA.setMail_no(mailStatus.getMail_no());
					List<Items> itemsjson = mailStatus.getItems();
					if((null!=itemsjson)&&(itemsjson.size()>0)){
							for(int i=0;i<mailStatus.getItems().size();i++){
				    			 Long idTwo = dao.getId();
				    			 itemsjson.get(i).setItemsId(idTwo);
				    			 //itemsjson.get(i).setStatusId(idOne.toString());
				    			 itemsjson.get(i).setMailStatus(mailStatus);
				    		 }
							mailStatusDBA.setItems(itemsjson);
					//上面方法可行，但是有一个问题:主键值成几何倍数增长---暂时注释掉
					mailStatusDBA.setLogist_comp(mailStatus.getLogist_comp());
					mailStatusDBA.setMail_no(mailStatus.getMail_no());
					
					Long statusId = mailStatusDBA.getStatusId();
					List<Items> itemsDBA = itemsManager.getItemsList(statusId);
					List<Items> itemsjson = mailStatus.getItems();
					if((null!=itemsDBA)&&(null!=itemsjson)&&(itemsDBA.size()>0)&&(itemsjson.size()>0)){
							  for(int p1=0;p1<itemsjson.size();p1++){
									  if(itemsDBA.size()>p1){
										        Items itemDBA = itemsDBA.get(p1);
												Items itemJson = itemsjson.get(p1);
												itemDBA.setAcceptTime(itemJson.getAcceptTime());
												itemDBA.setAcceptAddress(itemJson.getAcceptAddress());
												itemDBA.setRemark(itemJson.getRemark());
												itemDBA.setMailStatus(mailStatus);
									  }else{
												Items itemOne = itemsjson.get(p1);
												itemOne.setMailStatus(mailStatus);
												itemsDBA.add(itemOne);
									  }
						      }
							  mailStatusDBA.setItems(itemsDBA); 
					}else{
						mailStatusDBA.setItems(new ArrayList());
					}
					reString = dao.saveMailStatus(mailStatusDBA);
				}else{
				    reString = dao.saveMailStatus(mailStatus);
				}
				if(Constants.INTERFACE_NORMAL.equals(reString)){
					return true;
				}else{
					return false;
				}
			}*/
			//modify by fu 2016-1-27 物流跟踪的功能现在优化放在容灾库，之前的功能先注释掉---------------end
			return false;
		}catch(Exception e){
			log.info("在类MailStatusManagerImpl的方法sOrUMailStatus中运行异常------------------"+e);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取主键
	 */
	public Long getId(){
		return dao.getId();
	}
	
	/**
	 * 在容灾库保存pdMailReceipt
	 * @author modify by fu 2016-01-28
	 * @param pdMailReceipt
     * @return 
	 */
	public void ssavePdMailReceiptReport(PdMailReceipt pdMailReceipt){
		dao.ssavePdMailReceiptReport(pdMailReceipt);
	}
    
}
