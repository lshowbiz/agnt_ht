package com.joymain.jecs.pd.model;

/**
 * 换货单和发货退回单直接关联的发货单状态数据结构
 * @author fx 2015-08-14
 *
 */
public class PdSendInfoStatus extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{
	
	private String siNo;//发货单号
	private String status;//发货状态   Y表示已发货
	
	//modify fu 2016-01-07 发货退回单和换货单对应的发货单的物流状态回传
	private String logisticsDo;//物流跟踪号
	private String shNo;//物流公司
	private String goodsShipped;//已发货商品
	
	public PdSendInfoStatus(){
		
	}
	
	public PdSendInfoStatus(String siNo,String status,String logisticsDo,String shNo,String goodsShipped){
		this.siNo = siNo;
		this.status = status;
		this.logisticsDo = logisticsDo;
		this.shNo = shNo;
		this.goodsShipped = goodsShipped;
	}
	
	public String getSiNo() {
		return siNo;
	}

	public void setSiNo(String siNo) {
		this.siNo = siNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLogisticsDo() {
		return logisticsDo;
	}

	public void setLogisticsDo(String logisticsDo) {
		this.logisticsDo = logisticsDo;
	}

	public String getShNo() {
		return shNo;
	}

	public void setShNo(String shNo) {
		this.shNo = shNo;
	}

	public String getGoodsShipped() {
		return goodsShipped;
	}

	public void setGoodsShipped(String goodsShipped) {
		this.goodsShipped = goodsShipped;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
