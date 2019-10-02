package com.joymain.jecs.pd.model;
// Generated 2014-11-25 15:30:05 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_LOGISTICS_BASE_DETAIL"
 *     
 */

public class PdLogisticsBaseDetail extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long detailId;
    
    //private String numId;
    private PdLogisticsBaseNum pdLogisticsBaseNum;

    private String erp_goods_bn;//erp_goods_bn
    private String product_no;//PRODUCT_NO product_no
    private String qty;//----------------------------------qty
    private Date createTime;
    private String otherOne;
    private String otherTwo;
    
    //modify gw 2015-04-14 套餐拆开发货新加的字段:所属套餐编码
    private String combination_product_no;//combination_product_no  combinationProductNo
    private BigDecimal price;//商品价格


    // Constructors

    /** default constructor */
    public PdLogisticsBaseDetail() {
    }

    
    /** full constructor */
    public PdLogisticsBaseDetail(String erp_goods_bn, String product_no, String qty, Date createTime, String otherOne, String otherTwo,String numId,String combination_product_no,BigDecimal price) {
       // this.numId = numId;
        this.erp_goods_bn = erp_goods_bn;
        this.product_no = product_no;
        this.qty = qty;
        this.createTime = createTime;
        this.otherOne = otherOne;
        this.otherTwo = otherTwo;
        this.combination_product_no = combination_product_no;
        this.price = price;
    }

    // Property accessors
	///**
	// * * @hibernate.id generator-class="sequence"
	// *             type="java.lang.Long"
	// * column="DETAIL_ID"
	// * 
	// * @hibernate.generator-param name="sequence" value="SEQ_PD"
	// */
    /**
	 * * @hibernate.id generator-class="assigned" 
	 * 		type="java.lang.Long"
	 * 
	 * column="DETAIL_ID"
	 * 
	 */
    public Long getDetailId() {
        return this.detailId;
    }
    
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }
    
  /* *//**       
     *      *            @hibernate.property
     *             column="NUM_ID"
     *             length="20"
     *         
     *//*
    public String getNumId() {
        return this.numId;
    }
    
    public void setNumId(String numId) {
        this.numId = numId;
    }*/
    
  
   /**
     * *
     * @hibernate.many-to-one class="com.joymain.jecs.pd.model.PdLogisticsBaseNum" 
     * @hibernate.column name="NUM_ID" not-null="true"
     * 
     */
    public PdLogisticsBaseNum getPdLogisticsBaseNum() {
		return pdLogisticsBaseNum;
	}


	public void setPdLogisticsBaseNum(PdLogisticsBaseNum pdLogisticsBaseNum) {
		this.pdLogisticsBaseNum = pdLogisticsBaseNum;
	}

    /**       
     *      *            @hibernate.property
     *             column="ERP_GOODS_BN"
     *             length="100"
     *         
     */

    public String getErp_goods_bn() {
        return this.erp_goods_bn;
    }
    
   
	public void setErp_goods_bn(String erp_goods_bn) {
        this.erp_goods_bn = erp_goods_bn;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_NO"
     *             length="50"
     *         
     */

    public String getproduct_no() {
        return this.product_no;
    }
    
    public void setproduct_no(String product_no) {
        this.product_no = product_no;
    }
    /**       
     *      *            @hibernate.property
     *             column="QTY"
     *             length="30"
     *         
     */

    public String getQty() {
        return this.qty;
    }
    
    public void setQty(String qty) {
        this.qty = qty;
    }
    /**       
     *      *            @hibernate.property
     *             column="CREATE_TIME"
     *             length="7"
     *         
     */

    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
     *             column="COMBINATION_PRODUCT_NO"
     *             length="50"
     *         
     */
	public String getCombination_product_no() {
		return combination_product_no;
	}

	public void setCombination_product_no(String combination_product_no) {
		this.combination_product_no = combination_product_no;
	}

	/**
	 * * @hibernate.property column="PRICE" length="18" 
	 * 
	 */
	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      //buffer.append("numId").append("='").append(getNumId()).append("' ");			
      buffer.append("erp_goods_bn").append("='").append(getErp_goods_bn()).append("' ");			
      buffer.append("product_no").append("='").append(getproduct_no()).append("' ");			
      buffer.append("qty").append("='").append(getQty()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("otherOne").append("='").append(getOtherOne()).append("' ");			
      buffer.append("otherTwo").append("='").append(getOtherTwo()).append("' ");
      buffer.append("combination_product_no").append("='").append(getCombination_product_no()).append("' ");
      buffer.append("price").append("='").append(getPrice()).append("' ");

      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdLogisticsBaseDetail) ) return false;
		 PdLogisticsBaseDetail castOther = ( PdLogisticsBaseDetail ) other; 
         
		 return ( (this.getDetailId()==castOther.getDetailId()) || ( this.getDetailId()!=null && castOther.getDetailId()!=null && this.getDetailId().equals(castOther.getDetailId()) ) )
// && ( (this.getNumId()==castOther.getNumId()) || ( this.getNumId()!=null && castOther.getNumId()!=null && this.getNumId().equals(castOther.getNumId()) ) )
 && ( (this.getErp_goods_bn()==castOther.getErp_goods_bn()) || ( this.getErp_goods_bn()!=null && castOther.getErp_goods_bn()!=null && this.getErp_goods_bn().equals(castOther.getErp_goods_bn()) ) )
 && ( (this.getproduct_no()==castOther.getproduct_no()) || ( this.getproduct_no()!=null && castOther.getproduct_no()!=null && this.getproduct_no().equals(castOther.getproduct_no()) ) )
 && ( (this.getQty()==castOther.getQty()) || ( this.getQty()!=null && castOther.getQty()!=null && this.getQty().equals(castOther.getQty()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getOtherOne()==castOther.getOtherOne()) || ( this.getOtherOne()!=null && castOther.getOtherOne()!=null && this.getOtherOne().equals(castOther.getOtherOne()) ) )
 && ( (this.getOtherTwo()==castOther.getOtherTwo()) || ( this.getOtherTwo()!=null && castOther.getOtherTwo()!=null && this.getOtherTwo().equals(castOther.getOtherTwo()) ) )
 && ( (this.getCombination_product_no()==castOther.getCombination_product_no()) || ( this.getCombination_product_no()!=null && castOther.getCombination_product_no()!=null && this.getCombination_product_no().equals(castOther.getCombination_product_no()) ) )
 && ( (this.getPrice()==castOther.getPrice()) || ( this.getPrice()!=null && castOther.getPrice()!=null && this.getPrice().equals(castOther.getPrice()) ) );

   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDetailId() == null ? 0 : this.getDetailId().hashCode() );
        // result = 37 * result + ( getNumId() == null ? 0 : this.getNumId().hashCode() );
         result = 37 * result + ( getErp_goods_bn() == null ? 0 : this.getErp_goods_bn().hashCode() );
         result = 37 * result + ( getproduct_no() == null ? 0 : this.getproduct_no().hashCode() );
         result = 37 * result + ( getQty() == null ? 0 : this.getQty().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getOtherOne() == null ? 0 : this.getOtherOne().hashCode() );
         result = 37 * result + ( getOtherTwo() == null ? 0 : this.getOtherTwo().hashCode() );
         result = 37 * result + ( getCombination_product_no() == null ? 0 : this.getCombination_product_no().hashCode() );
         result = 37 * result + ( getPrice() == null ? 0 : this.getPrice().hashCode() );

         return result;
   }   





}
