package com.joymain.jecs.pd.model;

/**
 * 发货单基本信息编辑接口实体类
 * @author fu 20150906
 *
 */
public class PdSendInfoEdit  extends com.joymain.jecs.model.BaseObject implements java.io.Serializable{
    
	private String siNo;//发货单号
	private String orderNo;//订单号
	private String warehouseNo;//发货仓库
	private String recipientName;//收货人姓名
	private String recipientCaNo;//省
	private String city;//城市
	private String recipientZip;//邮编
	private String recipientAddr;//地址
	private String recipientPhone;//手机
	private String recipientMobile;//电话
	private String shipType;//发货方式   0或空表示正常发货;2暂不发货;3暂停发货
	private String shNo;//物流公司
	private String canDo;//N表示不可以生成DO单，空值表示可以生成DO单
	private String hurryFlag;//HURRY_FLAG 加急发货标志,0或者空表示非加急发货，1表示加急发货；  modify fu 2015-10-29 

    public PdSendInfoEdit(){
    	
    }
	
	public String getSiNo() {
		return siNo;
	}

	public void setSiNo(String siNo) {
		this.siNo = siNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getWarehouseNo() {
		return warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientCaNo() {
		return recipientCaNo;
	}

	public void setRecipientCaNo(String recipientCaNo) {
		this.recipientCaNo = recipientCaNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRecipientZip() {
		return recipientZip;
	}

	public void setRecipientZip(String recipientZip) {
		this.recipientZip = recipientZip;
	}

	public String getRecipientAddr() {
		return recipientAddr;
	}

	public void setRecipientAddr(String recipientAddr) {
		this.recipientAddr = recipientAddr;
	}

	public String getRecipientPhone() {
		return recipientPhone;
	}

	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}

	public String getRecipientMobile() {
		return recipientMobile;
	}

	public void setRecipientMobile(String recipientMobile) {
		this.recipientMobile = recipientMobile;
	}

	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}

	public String getShNo() {
		return shNo;
	}

	public void setShNo(String shNo) {
		this.shNo = shNo;
	}

	public String getCanDo() {
		return canDo;
	}

	public void setCanDo(String canDo) {
		this.canDo = canDo;
	}

	public String getHurryFlag() {
		return hurryFlag;
	}

	public void setHurryFlag(String hurryFlag) {
		this.hurryFlag = hurryFlag;
	}

	@Override
	public boolean equals(Object o) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public String toString() {
		return null;
	}

}
