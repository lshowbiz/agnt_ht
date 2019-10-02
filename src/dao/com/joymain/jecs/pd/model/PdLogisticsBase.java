package com.joymain.jecs.pd.model;
// Generated 2014-11-25 15:22:04 by Hibernate Tools 3.1.0.beta4

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_LOGISTICS_BASE"
 *     
 */

public class PdLogisticsBase extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long baseId;
    private String member_order_no;//member_order_no member_order_no
    private String si_no;//SI_NO si_no
    private String status;//-------------------------------status
    private String wms_do;//wms_do
    private Date status_time;//status_time
    private String status_code;//status_code
    private String status_name;//status_name
    private String operator;//----------------------------operator
    private String otherOne;//modify by fu 2016-05-05 物流实时信息查询状态字段:Y表示已经查询过物流实时信息查询了,空表示需要查询物流实时信息
    private String otherTwo;
    private String otherThree;
    private String otherFour;
    private String otherFive;
    
    //modify gw 2015-04-24换货单管理的DO单和LO单的类型区分：MO订单，EX关联到换货单
    private String num_order_type;//num_order_type  numOrderType
    private Integer isfull_pay;//订单全额支付字段表示：是否是全额支付，0：不是，1：是.结算单只统计全额支付的订单，这个字段结算单用到

    private List<PdLogisticsBaseNum> mail_list = new ArrayList();//modify by fu 2015-09-22 由WMS物流的玉喜获悉：真实的DO单，一个DO单，有且仅有一个一个物流单号,并且商品不允许夸DO单

    
    // Constructors

    /** default constructor */
    public PdLogisticsBase() {
    }

    
    /** full constructor */
    public PdLogisticsBase(String member_order_no, String si_no, String status, String wms_do, Date status_time, String status_code, String status_name, String operator, String otherOne, String otherTwo, String otherThree, String otherFour, String otherFive,String num_order_type,Integer isfull_pay) {
        this.member_order_no = member_order_no;
        this.si_no = si_no;
        this.status = status;
        this.wms_do = wms_do;
        this.status_time = status_time;
        this.status_code = status_code;
        this.status_name = status_name;
        this.operator = operator;
        this.otherOne = otherOne;
        this.otherTwo = otherTwo;
        this.otherThree = otherThree;
        this.otherFour = otherFour;
        this.otherFive = otherFive;
        this.num_order_type = num_order_type;
        this.isfull_pay = isfull_pay;

    }
    
    // Property accessors
	///**
	// * * @hibernate.id generator-class="sequence"
	// *             type="java.lang.Long"
	// * column="BASE_ID"
	// * 
	// * @hibernate.generator-param name="sequence" value="SEQ_PD"
	// */
    /**
	 * * @hibernate.id generator-class="assigned" 
	 * 		type="java.lang.Long"
	 * 
	 * column="BASE_ID"
	 * 
	 */
    public Long getBaseId() {
        return this.baseId;
    }
    
    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }
    /**       
     *      *            @hibernate.property
     *             column="MEMBER_ORDER_NO"
     *             length="20"
     *         
     */

    public String getMember_order_no() {
        return this.member_order_no;
    }
    
    public void setMember_order_no(String member_order_no) {
        this.member_order_no = member_order_no;
    }
    /**       
     *      *            @hibernate.property
     *             column="SI_NO"
     *             length="20"
     *         
     */

    public String getSi_no() {
        return this.si_no;
    }
    
    public void setSi_no(String si_no) {
        this.si_no = si_no;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS"
     *             length="10"
     *         
     */

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    /**       
     *      *            @hibernate.property
     *             column="WMS_DO"
     *             length="30"
     *         
     */

    public String getWms_do() {
        return this.wms_do;
    }
    
    public void setWms_do(String wms_do) {
        this.wms_do = wms_do;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS_TIME"
     *             length="7"
     *         
     */

    public Date getStatus_time() {
        return this.status_time;
    }
    
    public void setStatus_time(Date status_time) {
        this.status_time = status_time;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS_CODE"
     *             length="30"
     *         
     */

    public String getStatus_code() {
        return this.status_code;
    }
    
    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }
    /**       
     *      *            @hibernate.property
     *             column="STATUS_NAME"
     *             length="100"
     *         
     */

    public String getStatus_name() {
        return this.status_name;
    }
    
    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
    /**       
     *      *            @hibernate.property
     *             column="OPERATOR"
     *             length="200"
     *         
     */

    public String getOperator() {
        return this.operator;
    }
    
    public void setOperator(String operator) {
        this.operator = operator;
    }
    /**       
     *      *            @hibernate.property
     *             column="OTHER_ONE"
     *             length="200"
     *         
     */

    public String getOtherOne() {
        return this.otherOne;
    }
    
    public void setOtherOne(String otherOne) {
        this.otherOne = otherOne;
    }
    /**       
     *      *            @hibernate.property
     *             column="OTHER_TWO"
     *             length="200"
     *         
     */

    public String getOtherTwo() {
        return this.otherTwo;
    }
    
    public void setOtherTwo(String otherTwo) {
        this.otherTwo = otherTwo;
    }
    /**       
     *      *            @hibernate.property
     *             column="OTHER_THREE"
     *             length="200"
     *         
     */

    public String getOtherThree() {
        return this.otherThree;
    }
    
    public void setOtherThree(String otherThree) {
        this.otherThree = otherThree;
    }
    /**       
     *      *            @hibernate.property
     *             column="OTHER_FOUR"
     *             length="200"
     *         
     */

    public String getOtherFour() {
        return this.otherFour;
    }
    
    public void setOtherFour(String otherFour) {
        this.otherFour = otherFour;
    }
    /**       
     *      *            @hibernate.property
     *             column="OTHER_FIVE"
     *             length="200"
     *         
     */

    public String getOtherFive() {
        return this.otherFive;
    }
    
    public void setOtherFive(String otherFive) {
        this.otherFive = otherFive;
    }
    
    
    /**       
     *      *            @hibernate.property
     *             column="NUM_ORDER_TYPE"
     *             length="100"
     *         
     */
    public String getNum_order_type() {
    	return num_order_type;
    }

    public void setNum_order_type(String num_order_type) {
    	this.num_order_type = num_order_type;
    }

    
    
    /**
	 * * @hibernate.property column="ISFULL_PAY" length="2"
	 * 
	 */
    public Integer getIsfull_pay() {
		return isfull_pay;
	}


	public void setIsfull_pay(Integer isfullPay) {
		isfull_pay = isfullPay;
	}


/**
	 * *
	 * 
     * @hibernate.list  inverse="true" cascade="all" 
     * @hibernate.collection-key column="BASE_ID"
     * @hibernate.collection-index column="positionPdNum"
     * @hibernate.collection-one-to-many 
     * class="com.joymain.jecs.pd.model.PdLogisticsBaseNum"
     * 
     */
    public List<PdLogisticsBaseNum> getMail_list() {
		return mail_list;
	}

	


	public void setMail_list(List<PdLogisticsBaseNum> mail_list) {
		this.mail_list = mail_list;
	}


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("member_order_no").append("='").append(getMember_order_no()).append("' ");			
      buffer.append("si_no").append("='").append(getSi_no()).append("' ");			
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("wms_do").append("='").append(getWms_do()).append("' ");			
      buffer.append("status_time").append("='").append(getStatus_time()).append("' ");			
      buffer.append("status_code").append("='").append(getStatus_code()).append("' ");			
      buffer.append("status_name").append("='").append(getStatus_name()).append("' ");			
      buffer.append("operator").append("='").append(getOperator()).append("' ");			
      buffer.append("otherOne").append("='").append(getOtherOne()).append("' ");			
      buffer.append("otherTwo").append("='").append(getOtherTwo()).append("' ");			
      buffer.append("otherThree").append("='").append(getOtherThree()).append("' ");			
      buffer.append("otherFour").append("='").append(getOtherFour()).append("' ");			
      buffer.append("otherFive").append("='").append(getOtherFive()).append("' ");
      buffer.append("num_order_type").append("='").append(getNum_order_type()).append("' ");

      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdLogisticsBase) ) return false;
		 PdLogisticsBase castOther = ( PdLogisticsBase ) other; 
         
		 return ( (this.getBaseId()==castOther.getBaseId()) || ( this.getBaseId()!=null && castOther.getBaseId()!=null && this.getBaseId().equals(castOther.getBaseId()) ) )
 && ( (this.getMember_order_no()==castOther.getMember_order_no()) || ( this.getMember_order_no()!=null && castOther.getMember_order_no()!=null && this.getMember_order_no().equals(castOther.getMember_order_no()) ) )
 && ( (this.getSi_no()==castOther.getSi_no()) || ( this.getSi_no()!=null && castOther.getSi_no()!=null && this.getSi_no().equals(castOther.getSi_no()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) )
 && ( (this.getWms_do()==castOther.getWms_do()) || ( this.getWms_do()!=null && castOther.getWms_do()!=null && this.getWms_do().equals(castOther.getWms_do()) ) )
 && ( (this.getStatus_time()==castOther.getStatus_time()) || ( this.getStatus_time()!=null && castOther.getStatus_time()!=null && this.getStatus_time().equals(castOther.getStatus_time()) ) )
 && ( (this.getStatus_code()==castOther.getStatus_code()) || ( this.getStatus_code()!=null && castOther.getStatus_code()!=null && this.getStatus_code().equals(castOther.getStatus_code()) ) )
 && ( (this.getStatus_name()==castOther.getStatus_name()) || ( this.getStatus_name()!=null && castOther.getStatus_name()!=null && this.getStatus_name().equals(castOther.getStatus_name()) ) )
 && ( (this.getOperator()==castOther.getOperator()) || ( this.getOperator()!=null && castOther.getOperator()!=null && this.getOperator().equals(castOther.getOperator()) ) )
 && ( (this.getOtherOne()==castOther.getOtherOne()) || ( this.getOtherOne()!=null && castOther.getOtherOne()!=null && this.getOtherOne().equals(castOther.getOtherOne()) ) )
 && ( (this.getOtherTwo()==castOther.getOtherTwo()) || ( this.getOtherTwo()!=null && castOther.getOtherTwo()!=null && this.getOtherTwo().equals(castOther.getOtherTwo()) ) )
 && ( (this.getOtherThree()==castOther.getOtherThree()) || ( this.getOtherThree()!=null && castOther.getOtherThree()!=null && this.getOtherThree().equals(castOther.getOtherThree()) ) )
 && ( (this.getOtherFour()==castOther.getOtherFour()) || ( this.getOtherFour()!=null && castOther.getOtherFour()!=null && this.getOtherFour().equals(castOther.getOtherFour()) ) )
 && ( (this.getOtherFive()==castOther.getOtherFive()) || ( this.getOtherFive()!=null && castOther.getOtherFive()!=null && this.getOtherFive().equals(castOther.getOtherFive()) ) )
 && ( (this.getNum_order_type()==castOther.getNum_order_type()) || ( this.getNum_order_type()!=null && castOther.getNum_order_type()!=null && this.getNum_order_type().equals(castOther.getNum_order_type()) ) );
		 
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBaseId() == null ? 0 : this.getBaseId().hashCode() );
         result = 37 * result + ( getMember_order_no() == null ? 0 : this.getMember_order_no().hashCode() );
         result = 37 * result + ( getSi_no() == null ? 0 : this.getSi_no().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         result = 37 * result + ( getWms_do() == null ? 0 : this.getWms_do().hashCode() );
         result = 37 * result + ( getStatus_time() == null ? 0 : this.getStatus_time().hashCode() );
         result = 37 * result + ( getStatus_code() == null ? 0 : this.getStatus_code().hashCode() );
         result = 37 * result + ( getStatus_name() == null ? 0 : this.getStatus_name().hashCode() );
         result = 37 * result + ( getOperator() == null ? 0 : this.getOperator().hashCode() );
         result = 37 * result + ( getOtherOne() == null ? 0 : this.getOtherOne().hashCode() );
         result = 37 * result + ( getOtherTwo() == null ? 0 : this.getOtherTwo().hashCode() );
         result = 37 * result + ( getOtherThree() == null ? 0 : this.getOtherThree().hashCode() );
         result = 37 * result + ( getOtherFour() == null ? 0 : this.getOtherFour().hashCode() );
         result = 37 * result + ( getOtherFive() == null ? 0 : this.getOtherFive().hashCode() );
         result = 37 * result + ( getNum_order_type() == null ? 0 : this.getNum_order_type().hashCode() );

         return result;
   }   





}
