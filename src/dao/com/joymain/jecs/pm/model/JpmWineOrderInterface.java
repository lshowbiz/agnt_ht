package com.joymain.jecs.pm.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// Generated 2013-12-2 17:28:12 by Hibernate Tools 3.1.0.beta4

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *                  table="JPM_WINE_ORDER_INTERFACE"
 * 
 */

public class JpmWineOrderInterface extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {
    private Long moId;//主键，自动生成

    /**
     * 头信息
     */
    private String billCode;//发货单号 √  

    private String dbillDate;//发货日期 √

    private String memberOrderNo;//订单号 √   

    private String userCode;// 会员编号  √

    private String coperatorid;//操作人 √

    private String membername;//会员姓名   √

    private String memberphone;//会员电话    √

    private String memberid;//会员身份证    √

    private String cusprovince;//会员所在省 √

    private String cuscity;//会员所在市 √

    private String areaclCode;//会员所在的区 √ 

    private String memberaddress;//会员详细地址  √   区 放到详细地址里面

    private String postalcode;//邮编   √   

    private String membersex;//性别  √

    /**
     * 从下是收货人的字段
     */
    private String receiverAreaclcode; //收货人所在区    √

    private String receiverCusCity;//收货人所在市    √

    private String receiverCusProvince;//收货人所在省    √

    private String receiverPostalCode;//收货人邮编 √

    private String receiverAddress;//收货人详细地址   √   

    private String receiverCode;//收货人编号    √   发货单号

    private String receiverId;//收货人身份证   √

    private String receiverName;//收货人姓名    √

    private String receiverPhone;//收货人电话   √

    private String receiverSex;//性别   √

    Set<JpmWineOrderListInterface> jpmWineOrderListInterfaceSet = new HashSet<JpmWineOrderListInterface>();

    //以下字段只做本地记录而用到
    private Integer status;

    private Integer resultCode;

    private String resultDescription;

    private Date createTime;//记录生成日期

    // Constructors

    /** default constructor */
    public JpmWineOrderInterface() {
    }

    /** full constructor */
    public JpmWineOrderInterface(Long moId, String billCode, String dbillDate, String memberOrderNo, String userCode, String coperatorid, String membername, String memberphone, String memberid, String cusprovince, String cuscity, String areaclCode, String memberaddress, String postalcode, String membersex, String receiverAreaclcode, String receiverCusCity, String receiverCusProvince, String receiverPostalCode, String receiverAddress, String receiverCode, String receiverId, String receiverName, String receiverPhone, String receiverSex, Set<JpmWineOrderListInterface> jpmWineOrderListInterfaceSet, Integer status, Integer resultCode, String resultDescription, Date createTime) {
        super();
        this.moId = moId;
        this.billCode = billCode;
        this.dbillDate = dbillDate;
        this.memberOrderNo = memberOrderNo;
        this.userCode = userCode;
        this.coperatorid = coperatorid;
        this.membername = membername;
        this.memberphone = memberphone;
        this.memberid = memberid;
        this.cusprovince = cusprovince;
        this.cuscity = cuscity;
        this.areaclCode = areaclCode;
        this.memberaddress = memberaddress;
        this.postalcode = postalcode;
        this.membersex = membersex;
        this.receiverAreaclcode = receiverAreaclcode;
        this.receiverCusCity = receiverCusCity;
        this.receiverCusProvince = receiverCusProvince;
        this.receiverPostalCode = receiverPostalCode;
        this.receiverAddress = receiverAddress;
        this.receiverCode = receiverCode;
        this.receiverId = receiverId;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverSex = receiverSex;
        this.jpmWineOrderListInterfaceSet = jpmWineOrderListInterfaceSet;
        this.status = status;
        this.resultCode = resultCode;
        this.resultDescription = resultDescription;
        this.createTime = createTime;
    }

    // Property accessors
    /**
     * * @hibernate.id
     * generator-class="native"
     * type="java.lang.Long"
     * column="MO_ID"
     * 
     */

    public Long getMoId() {
        return this.moId;
    }

    public void setMoId(Long moId) {
        this.moId = moId;
    }

    /**
     * * @hibernate.property
     * column="MEMBER_ORDER_NO"
     * length="20"
     * 
     */

    public String getMemberOrderNo() {
        return this.memberOrderNo;
    }

    public void setMemberOrderNo(String memberOrderNo) {
        this.memberOrderNo = memberOrderNo;
    }

    /**
     * * @hibernate.property
     * column="USER_CODE"
     * length="20"
     * 
     */

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * * @hibernate.property
     * column="STATUS"
     * type="java.lang.Integer"
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * * @hibernate.property
     * column="RESULT_CODE"
     * type="java.lang.Integer"
     */
    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * * @hibernate.property
     * column="RESULT_DESCRIPTION"
     * length="500"
     * 
     */
    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    /**
     * * @hibernate.property
     * column="BILL_CODE"
     * length="40"
     * 
     */
    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    /**
     * * @hibernate.property
     * column="DBILL_DATE"
     * length="20"
     * 
     */
    public String getDbillDate() {
        return dbillDate;
    }

    public void setDbillDate(String dbillDate) {
        this.dbillDate = dbillDate;
    }

    /**
     * * @hibernate.property
     * column="COPERATOR_ID"
     * length="50"
     * 
     */
    public String getCoperatorid() {
        return coperatorid;
    }

    public void setCoperatorid(String coperatorid) {
        this.coperatorid = coperatorid;
    }

    /**
     * * @hibernate.property
     * column="MEMBER_NAME"
     * length="50"
     * 
     */
    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    /**
     * * @hibernate.property
     * column="MEMBER_PHONE"
     * length="50"
     * 
     */
    public String getMemberphone() {
        return memberphone;
    }

    public void setMemberphone(String memberphone) {
        this.memberphone = memberphone;
    }

    /**
     * * @hibernate.property
     * column="MEMBER_ID"
     * length="20"
     * 
     */
    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    /**
     * * @hibernate.property
     * column="CUSPROVINCE"
     * length="20"
     * 
     */
    public String getCusprovince() {
        return cusprovince;
    }

    public void setCusprovince(String cusprovince) {
        this.cusprovince = cusprovince;
    }

    /**
     * * @hibernate.property
     * column="CUSCITY"
     * length="30"
     * 
     */
    public String getCuscity() {
        return cuscity;
    }

    public void setCuscity(String cuscity) {
        this.cuscity = cuscity;
    }

    /**
     * * @hibernate.property
     * column="AREACL_CODE"
     * length="50"
     * 
     */
    public String getAreaclCode() {
        return areaclCode;
    }

    public void setAreaclCode(String areaclCode) {
        this.areaclCode = areaclCode;
    }

    /**
     * * @hibernate.property
     * column="MEMBER_ADDRESS"
     * length="30"
     * 
     */
    public String getMemberaddress() {
        return memberaddress;
    }

    public void setMemberaddress(String memberaddress) {
        this.memberaddress = memberaddress;
    }

    /**
     * * @hibernate.property
     * column="POSTAL_CODE"
     * length="10"
     * 
     */
    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    /**
     * * @hibernate.property
     * column="MEMBER_SEX"
     * length="10"
     * 
     */
    public String getMembersex() {
        return membersex;
    }

    public void setMembersex(String membersex) {
        this.membersex = membersex;
    }

    /**
     * * @hibernate.property
     * column="RECEIVER_AREACL_CODE"
     * length="50"
     * 
     */
    public String getReceiverAreaclcode() {
        return receiverAreaclcode;
    }

    public void setReceiverAreaclcode(String receiverAreaclcode) {
        this.receiverAreaclcode = receiverAreaclcode;
    }

    /**
     * * @hibernate.property
     * column="RECEIVER_CUS_CITY"
     * length="10"
     * 
     */
    public String getReceiverCusCity() {
        return receiverCusCity;
    }

    public void setReceiverCusCity(String receiverCusCity) {
        this.receiverCusCity = receiverCusCity;
    }

    /**
     * * @hibernate.property
     * column="RECEIVER_CUS_PROVINCE"
     * length="30"
     * 
     */
    public String getReceiverCusProvince() {
        return receiverCusProvince;
    }

    public void setReceiverCusProvince(String receiverCusProvince) {
        this.receiverCusProvince = receiverCusProvince;
    }

    /**
     * * @hibernate.property
     * column="RECEIVER_POSTAL_CODE"
     * length="10"
     * 
     */
    public String getReceiverPostalCode() {
        return receiverPostalCode;
    }

    public void setReceiverPostalCode(String receiverPostalCode) {
        this.receiverPostalCode = receiverPostalCode;
    }

    /**
     * * @hibernate.property
     * column="RECEIVER_ADDRESS"
     * length="500"
     * 
     */
    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    /**
     * * @hibernate.property
     * column="RECEIVER_CODE"
     * length="50"
     * 
     */
    public String getReceiverCode() {
        return receiverCode;
    }

    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode;
    }

    /**
     * * @hibernate.property
     * column="RECEIVER_ID"
     * length="40"
     * 
     */
    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * * @hibernate.property
     * column="RECEIVER_NAME"
     * length="40"
     * 
     */
    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * * @hibernate.property
     * column="RECEIVER_PHONE"
     * length="40"
     * 
     */
    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    /**
     * * @hibernate.property
     * column="RECEIVER_SEX"
     * length="10"
     * 
     */
    public String getReceiverSex() {
        return receiverSex;
    }

    public void setReceiverSex(String receiverSex) {
        this.receiverSex = receiverSex;
    }

    /**
     * * @hibernate.property
     * column="CREATE_TIME"
     * length="10"
     * 
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @hibernate.set lazy="true" cascade="all"
     * @hibernate.key column="MO_ID"
     * @hibernate.one-to-many
     *                        class=
     *                        "com.joymain.jecs.pm.model.JpmWineOrderListInterface"
     */
    public Set<JpmWineOrderListInterface> getJpmWineOrderListInterfaceSet() {
        return jpmWineOrderListInterfaceSet;
    }

    public void setJpmWineOrderListInterfaceSet(Set<JpmWineOrderListInterface> jpmWineOrderListInterfaceSet) {
        this.jpmWineOrderListInterfaceSet = jpmWineOrderListInterfaceSet;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((areaclCode == null) ? 0 : areaclCode.hashCode());
        result = prime * result + ((billCode == null) ? 0 : billCode.hashCode());
        result = prime * result + ((coperatorid == null) ? 0 : coperatorid.hashCode());
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((cuscity == null) ? 0 : cuscity.hashCode());
        result = prime * result + ((cusprovince == null) ? 0 : cusprovince.hashCode());
        result = prime * result + ((dbillDate == null) ? 0 : dbillDate.hashCode());
        result = prime * result + ((memberOrderNo == null) ? 0 : memberOrderNo.hashCode());
        result = prime * result + ((memberaddress == null) ? 0 : memberaddress.hashCode());
        result = prime * result + ((memberid == null) ? 0 : memberid.hashCode());
        result = prime * result + ((membername == null) ? 0 : membername.hashCode());
        result = prime * result + ((memberphone == null) ? 0 : memberphone.hashCode());
        result = prime * result + ((membersex == null) ? 0 : membersex.hashCode());
        result = prime * result + ((moId == null) ? 0 : moId.hashCode());
        result = prime * result + ((postalcode == null) ? 0 : postalcode.hashCode());
        result = prime * result + ((receiverAddress == null) ? 0 : receiverAddress.hashCode());
        result = prime * result + ((receiverAreaclcode == null) ? 0 : receiverAreaclcode.hashCode());
        result = prime * result + ((receiverCode == null) ? 0 : receiverCode.hashCode());
        result = prime * result + ((receiverCusCity == null) ? 0 : receiverCusCity.hashCode());
        result = prime * result + ((receiverCusProvince == null) ? 0 : receiverCusProvince.hashCode());
        result = prime * result + ((receiverId == null) ? 0 : receiverId.hashCode());
        result = prime * result + ((receiverName == null) ? 0 : receiverName.hashCode());
        result = prime * result + ((receiverPhone == null) ? 0 : receiverPhone.hashCode());
        result = prime * result + ((receiverPostalCode == null) ? 0 : receiverPostalCode.hashCode());
        result = prime * result + ((receiverSex == null) ? 0 : receiverSex.hashCode());
        result = prime * result + ((resultCode == null) ? 0 : resultCode.hashCode());
        result = prime * result + ((resultDescription == null) ? 0 : resultDescription.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((userCode == null) ? 0 : userCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JpmWineOrderInterface other = (JpmWineOrderInterface) obj;
        if (areaclCode == null) {
            if (other.areaclCode != null)
                return false;
        } else if (!areaclCode.equals(other.areaclCode))
            return false;
        if (billCode == null) {
            if (other.billCode != null)
                return false;
        } else if (!billCode.equals(other.billCode))
            return false;
        if (coperatorid == null) {
            if (other.coperatorid != null)
                return false;
        } else if (!coperatorid.equals(other.coperatorid))
            return false;
        if (createTime == null) {
            if (other.createTime != null)
                return false;
        } else if (!createTime.equals(other.createTime))
            return false;
        if (cuscity == null) {
            if (other.cuscity != null)
                return false;
        } else if (!cuscity.equals(other.cuscity))
            return false;
        if (cusprovince == null) {
            if (other.cusprovince != null)
                return false;
        } else if (!cusprovince.equals(other.cusprovince))
            return false;
        if (dbillDate == null) {
            if (other.dbillDate != null)
                return false;
        } else if (!dbillDate.equals(other.dbillDate))
            return false;
        if (memberOrderNo == null) {
            if (other.memberOrderNo != null)
                return false;
        } else if (!memberOrderNo.equals(other.memberOrderNo))
            return false;
        if (memberaddress == null) {
            if (other.memberaddress != null)
                return false;
        } else if (!memberaddress.equals(other.memberaddress))
            return false;
        if (memberid == null) {
            if (other.memberid != null)
                return false;
        } else if (!memberid.equals(other.memberid))
            return false;
        if (membername == null) {
            if (other.membername != null)
                return false;
        } else if (!membername.equals(other.membername))
            return false;
        if (memberphone == null) {
            if (other.memberphone != null)
                return false;
        } else if (!memberphone.equals(other.memberphone))
            return false;
        if (membersex == null) {
            if (other.membersex != null)
                return false;
        } else if (!membersex.equals(other.membersex))
            return false;
        if (moId == null) {
            if (other.moId != null)
                return false;
        } else if (!moId.equals(other.moId))
            return false;
        if (postalcode == null) {
            if (other.postalcode != null)
                return false;
        } else if (!postalcode.equals(other.postalcode))
            return false;
        if (receiverAddress == null) {
            if (other.receiverAddress != null)
                return false;
        } else if (!receiverAddress.equals(other.receiverAddress))
            return false;
        if (receiverAreaclcode == null) {
            if (other.receiverAreaclcode != null)
                return false;
        } else if (!receiverAreaclcode.equals(other.receiverAreaclcode))
            return false;
        if (receiverCode == null) {
            if (other.receiverCode != null)
                return false;
        } else if (!receiverCode.equals(other.receiverCode))
            return false;
        if (receiverCusCity == null) {
            if (other.receiverCusCity != null)
                return false;
        } else if (!receiverCusCity.equals(other.receiverCusCity))
            return false;
        if (receiverCusProvince == null) {
            if (other.receiverCusProvince != null)
                return false;
        } else if (!receiverCusProvince.equals(other.receiverCusProvince))
            return false;
        if (receiverId == null) {
            if (other.receiverId != null)
                return false;
        } else if (!receiverId.equals(other.receiverId))
            return false;
        if (receiverName == null) {
            if (other.receiverName != null)
                return false;
        } else if (!receiverName.equals(other.receiverName))
            return false;
        if (receiverPhone == null) {
            if (other.receiverPhone != null)
                return false;
        } else if (!receiverPhone.equals(other.receiverPhone))
            return false;
        if (receiverPostalCode == null) {
            if (other.receiverPostalCode != null)
                return false;
        } else if (!receiverPostalCode.equals(other.receiverPostalCode))
            return false;
        if (receiverSex == null) {
            if (other.receiverSex != null)
                return false;
        } else if (!receiverSex.equals(other.receiverSex))
            return false;
        if (resultCode == null) {
            if (other.resultCode != null)
                return false;
        } else if (!resultCode.equals(other.resultCode))
            return false;
        if (resultDescription == null) {
            if (other.resultDescription != null)
                return false;
        } else if (!resultDescription.equals(other.resultDescription))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (userCode == null) {
            if (other.userCode != null)
                return false;
        } else if (!userCode.equals(other.userCode))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "JpmWineOrderInterface [moId=" + moId + ", billCode=" + billCode + ", dbillDate=" + dbillDate + ", memberOrderNo=" + memberOrderNo + ", userCode=" + userCode + ", coperatorid=" + coperatorid + ", membername=" + membername + ", memberphone=" + memberphone + ", memberid=" + memberid + ", cusprovince=" + cusprovince + ", cuscity=" + cuscity + ", areaclCode=" + areaclCode + ", memberaddress=" + memberaddress + ", postalcode=" + postalcode + ", membersex=" + membersex + ", receiverAreaclcode=" + receiverAreaclcode + ", receiverCusCity=" + receiverCusCity + ", receiverCusProvince=" + receiverCusProvince + ", receiverPostalCode=" + receiverPostalCode + ", receiverAddress=" + receiverAddress + ", receiverCode=" + receiverCode + ", receiverId=" + receiverId + ", receiverName=" + receiverName + ", receiverPhone=" + receiverPhone + ", receiverSex=" + receiverSex + ", status=" + status + ", resultCode=" + resultCode + ", resultDescription=" + resultDescription + ", createTime=" + createTime + "]";
    }

}
