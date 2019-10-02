package com.joymain.jecs.pm.model;

import java.math.BigDecimal;
import java.util.HashSet;
// Generated 2009-9-16 16:01:36 by Hibernate Tools 3.1.0.beta4
import java.util.Set;



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_PRODUCT"
 *     
 */

public class JpmProduct extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String productNo;
    private String productCategory;
    private String productName;
    private String unitNo;
    private String smNo;
    private String remark;
    private String productDesc;
    private String combineFlag;
    private String lockFlag="0";
    
    //新增三个熟悉
    private String productStyle;
    private String productSize;
    private String productProvider;
    
    private String statisticsCategory;
    
    private Set<JpmProductCombination> jpmProductCombinations = new HashSet(); 
    
    
    private Long proNum;
    
    private String erpProductNo;//商品ERP编码
    
    // Constructors
   
    private BigDecimal erpPrice = new BigDecimal(0);//ERP价格

	/** default constructor */
    public JpmProduct() {
    }

    
    public Long getProNum() {
		return proNum;
	}


	public void setProNum(Long proNum) {
		this.proNum = proNum;
	}


	/** full constructor */
    public JpmProduct(String productCategory, String productName, String unitNo, String smNo, String remark) {
        this.productCategory = productCategory;
        this.productName = productName;
        this.unitNo = unitNo;
        this.smNo = smNo;
        this.remark = remark;
    }
    

    /**
	 * *
	 * 
	 * @hibernate.set lazy="false" inverse="true" cascade="all-delete-orphan"
	 *                order-by="product_No"
	 * @hibernate.collection-key column="PRODUCT_NO"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.pm.model.JpmProductCombination"
	 * 
	 */
    public Set<JpmProductCombination> getJpmProductCombinations() {
		return jpmProductCombinations;
	}


	public void setJpmProductCombinations(
			Set<JpmProductCombination> jpmProductCombinations) {
		this.jpmProductCombinations = jpmProductCombinations;
	}

	
	

	public String getStatisticsCategory() {
		return statisticsCategory;
	}

	/**       
     *      *            @hibernate.property
     *             column="STATISTICS_CATEGORY"
     *             length="10"
     *         
     */
	public void setStatisticsCategory(String statisticsCategory) {
		this.statisticsCategory = statisticsCategory;
	}


	// Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="PRODUCT_NO"
     *         
     */

    public String getProductNo() {
        return this.productNo;
    }
    /**
     * @spring.validator type="required"
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_CATEGORY"
     *             length="10"
     *         
     */

    public String getProductCategory() {
        return this.productCategory;
    }
    
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    
    
    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_NAME"
     *             length="200"
     *         
     */

    public String getProductName() {
        return this.productName;
    }
    /**
     * @spring.validator type="required"
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**       
     *      *            @hibernate.property
     *             column="UNIT_NO"
     *             length="10"
     *         
     */

    public String getUnitNo() {
        return this.unitNo;
    }
    
    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="SM_NO"
     *             length="2"
     *         
     */

    public String getSmNo() {
        return this.smNo;
    }
    /**
     * @spring.validator type="required"
     */
    public void setSmNo(String smNo) {
        this.smNo = smNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="REMARK"
     *             length="500"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**       
     *      *            @hibernate.property
     *             column="PRODUCT_DESC"
     *             length="500"
     *         
     */

    public String getProductDesc() {
		return productDesc;
	}


	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/**       
     *      *            @hibernate.property
     *             column="COMBINE_FLAG"
     *             
     *         
     */
	public String getCombineFlag() {
		return combineFlag;
	}


	public void setCombineFlag(String combineFlag) {
		this.combineFlag = combineFlag;
	}
	/**       
     *      *            @hibernate.property
     *             column="LOCK_FLAG"
     *             
     *         
     */

	public String getLockFlag() {
		return lockFlag;
	}


	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}


	/**       
     *      *            @hibernate.property
     *             column="PRODUCT_STYLE"
     *             
     *         
     */
	public String getProductStyle() {
		return productStyle;
	}


	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}

	/**       
     *      *            @hibernate.property
     *             column="PRODUCT_SIZE"
     *             
     *         
     */
	public String getProductSize() {
		return productSize;
	}


	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	/**       
     *      *            @hibernate.property
     *             column="PRODUCT_PROVIDER"
     *             
     *         
     */
	public String getProductProvider() {
		return productProvider;
	}


	public void setProductProvider(String productProvider) {
		this.productProvider = productProvider;
	}


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("productCategory").append("='").append(getProductCategory()).append("' ");			
      buffer.append("productName").append("='").append(getProductName()).append("' ");			
      buffer.append("unitNo").append("='").append(getUnitNo()).append("' ");			
      buffer.append("smNo").append("='").append(getSmNo()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpmProduct) ) return false;
		 JpmProduct castOther = ( JpmProduct ) other; 
         
		 return ( (this.getProductNo()==castOther.getProductNo()) || ( this.getProductNo()!=null && castOther.getProductNo()!=null && this.getProductNo().equals(castOther.getProductNo()) ) )
 && ( (this.getProductCategory()==castOther.getProductCategory()) || ( this.getProductCategory()!=null && castOther.getProductCategory()!=null && this.getProductCategory().equals(castOther.getProductCategory()) ) )
 && ( (this.getProductName()==castOther.getProductName()) || ( this.getProductName()!=null && castOther.getProductName()!=null && this.getProductName().equals(castOther.getProductName()) ) )
 && ( (this.getUnitNo()==castOther.getUnitNo()) || ( this.getUnitNo()!=null && castOther.getUnitNo()!=null && this.getUnitNo().equals(castOther.getUnitNo()) ) )
 && ( (this.getSmNo()==castOther.getSmNo()) || ( this.getSmNo()!=null && castOther.getSmNo()!=null && this.getSmNo().equals(castOther.getSmNo()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getProductNo() == null ? 0 : this.getProductNo().hashCode() );
         result = 37 * result + ( getProductCategory() == null ? 0 : this.getProductCategory().hashCode() );
         result = 37 * result + ( getProductName() == null ? 0 : this.getProductName().hashCode() );
         result = 37 * result + ( getUnitNo() == null ? 0 : this.getUnitNo().hashCode() );
         result = 37 * result + ( getSmNo() == null ? 0 : this.getSmNo().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         return result;
   }

   /**       
    * @hibernate.property
    *   column="ERP_PRODUCT_NO"
    *             
    *         
    */
	public String getErpProductNo() {
		return erpProductNo;
	}
	

	public void setErpProductNo(String erpProductNo) {
		this.erpProductNo = erpProductNo;
	}

	/**       
    * @hibernate.property
    *   column="ERP_PRICE"
    *             
    *         
    */
	public BigDecimal getErpPrice() {
		return erpPrice;
	}


	public void setErpPrice(BigDecimal erpPrice) {
		this.erpPrice = erpPrice;
	}
}
