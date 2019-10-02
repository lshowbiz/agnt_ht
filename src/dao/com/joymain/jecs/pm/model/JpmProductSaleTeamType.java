package com.joymain.jecs.pm.model;
// Generated 2013-6-3 18:03:51 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPM_PRODUCT_SALE_TEAM_TYPE"
 *     
 */

public class JpmProductSaleTeamType extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


	// Fields    

	private Long pttId;
	private Long uniNo;
	private String teamCode;
	private String orderType;
	private String companyCode;
	private BigDecimal price = new BigDecimal(0);
	private BigDecimal pv = new BigDecimal(0);
	private String state;
	private String province;

	private JpmProductSaleNew jpmProductSaleNew = new JpmProductSaleNew();
	private JmiMemberTeam jmiMemberTeam = new JmiMemberTeam();
	
	private String isHidden;
	private BigDecimal discountPrice;
	// Constructors
	
	private String orderTypeW;
	private String priceW;
	private String pvW;
	private String teamCodeP;
	

	/** default constructor */
	public JpmProductSaleTeamType() {
	}


	/** full constructor */
	public JpmProductSaleTeamType(Long uniNo, String teamCode, String orderType, String companyCode, BigDecimal price, BigDecimal pv, String state, String province) {
		this.uniNo = uniNo;
		this.teamCode = teamCode;
		this.orderType = orderType;
		this.companyCode = companyCode;
		this.price = price;
		this.pv = pv;
		this.state = state;
		this.province = province;
	}



	// Property accessors
	/**       
	 *      *            @hibernate.id
	 *             generator-class="sequence"
	 *             type="java.lang.Long"
	 *             column="PTT_ID"
	 *         @hibernate.generator-param name="sequence" value="SEQ_PM" 
	 */
	public Long getPttId() {
		return this.pttId;
	}

	public void setPttId(Long pttId) {
		this.pttId = pttId;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="UNI_NO"
	 *             length="10"
	 *         
	 */

	public Long getUniNo() {
		return this.uniNo;
	}
	 
	public void setUniNo(Long uniNo) {
		this.uniNo = uniNo;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="TEAM_CODE"
	 *             length="10"
	 *         
	 */

	public String getTeamCode() {
		return this.teamCode;
	}
	/**
     * @spring.validator type="required"
     */
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="ORDER_TYPE"
	 *             length="3"
	 *         
	 */

	public String getOrderType() {
		return this.orderType;
	}
	/**
     * @spring.validator type="required"
     */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="COMPANY_CODE"
	 *             length="2"
	 *         
	 */

	public String getCompanyCode() {
		return this.companyCode;
	}
	 
	/**
     * @spring.validator type="required"
     */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="PRICE"
	 *             length="18"
	 *         
	 */

	public BigDecimal getPrice() {
		return this.price;
	}
	/**
     * @spring.validator type="required"
     */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="PV"
	 *             length="10"
	 *         
	 */

	public BigDecimal getPv() {
		return this.pv;
	}
	/**
     * @spring.validator type="required"
     */
	public void setPv(BigDecimal pv) {
		this.pv = pv;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="STATE"
	 *             length="1"
	 *         
	 */

	public String getState() {
		return this.state;
	}
	/**
     * @spring.validator type="required"
     */
	public void setState(String state) {
		this.state = state;
	}
	/**       
	 *      *            @hibernate.property
	 *             column="PROVINCE"
	 *             length="6"
	 *         
	 */

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}


	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}


	/**
	 * toString
	 * @return String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		buffer.append("uniNo").append("='").append(getUniNo()).append("' ");			
		buffer.append("teamCode").append("='").append(getTeamCode()).append("' ");			
		buffer.append("orderType").append("='").append(getOrderType()).append("' ");			
		buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
		buffer.append("price").append("='").append(getPrice()).append("' ");			
		buffer.append("pv").append("='").append(getPv()).append("' ");			
		buffer.append("state").append("='").append(getState()).append("' ");			
		buffer.append("province").append("='").append(getProvince()).append("' ");			
		buffer.append("]");

		return buffer.toString();
	}


	public boolean equals(Object other) {
		if ( (this == other ) ) return true;
		if ( (other == null ) ) return false;
		if ( !(other instanceof JpmProductSaleTeamType) ) return false;
		JpmProductSaleTeamType castOther = ( JpmProductSaleTeamType ) other; 

		return ( (this.getPttId()==castOther.getPttId()) || ( this.getPttId()!=null && castOther.getPttId()!=null && this.getPttId().equals(castOther.getPttId()) ) )
		&& ( (this.getUniNo()==castOther.getUniNo()) || ( this.getUniNo()!=null && castOther.getUniNo()!=null && this.getUniNo().equals(castOther.getUniNo()) ) )
		&& ( (this.getTeamCode()==castOther.getTeamCode()) || ( this.getTeamCode()!=null && castOther.getTeamCode()!=null && this.getTeamCode().equals(castOther.getTeamCode()) ) )
		&& ( (this.getOrderType()==castOther.getOrderType()) || ( this.getOrderType()!=null && castOther.getOrderType()!=null && this.getOrderType().equals(castOther.getOrderType()) ) )
		&& ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
		&& ( (this.getPrice()==castOther.getPrice()) || ( this.getPrice()!=null && castOther.getPrice()!=null && this.getPrice().equals(castOther.getPrice()) ) )
		&& ( (this.getPv()==castOther.getPv()) || ( this.getPv()!=null && castOther.getPv()!=null && this.getPv().equals(castOther.getPv()) ) )
		&& ( (this.getState()==castOther.getState()) || ( this.getState()!=null && castOther.getState()!=null && this.getState().equals(castOther.getState()) ) )
		&& ( (this.getProvince()==castOther.getProvince()) || ( this.getProvince()!=null && castOther.getProvince()!=null && this.getProvince().equals(castOther.getProvince()) ) );
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + ( getPttId() == null ? 0 : this.getPttId().hashCode() );
		result = 37 * result + ( getUniNo() == null ? 0 : this.getUniNo().hashCode() );
		result = 37 * result + ( getTeamCode() == null ? 0 : this.getTeamCode().hashCode() );
		result = 37 * result + ( getOrderType() == null ? 0 : this.getOrderType().hashCode() );
		result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
		result = 37 * result + ( getPrice() == null ? 0 : this.getPrice().hashCode() );
		result = 37 * result + ( getPv() == null ? 0 : this.getPv().hashCode() );
		result = 37 * result + ( getState() == null ? 0 : this.getState().hashCode() );
		result = 37 * result + ( getProvince() == null ? 0 : this.getProvince().hashCode() );
		return result;
	}

	/**
	 * *
	 * @hibernate.many-to-one not-null="true" inverse="true" insert="false" update="false" lazy="false"
	 * @hibernate.column name="UNI_NO"
	 * 
	 */
	public JpmProductSaleNew getJpmProductSaleNew() {
		return jpmProductSaleNew;
	}


	public void setJpmProductSaleNew(JpmProductSaleNew jpmProductSaleNew) {
		this.jpmProductSaleNew = jpmProductSaleNew;
	}

	/**
	 * *
	 * @hibernate.many-to-one not-null="true" inverse="true" insert="false" update="false" 
	 * @hibernate.column name="TEAM_CODE"
	 * 
	 */
	public JmiMemberTeam getJmiMemberTeam() {
		return jmiMemberTeam;
	}

	 
	public void setJmiMemberTeam(JmiMemberTeam jmiMemberTeam) {
		this.jmiMemberTeam = jmiMemberTeam;
	}   
	
	/**       
	 *      *            @hibernate.property
	 *             column="IS_HIDDEN"
	 *             length="1"
	 *         
	 */
	public String getIsHidden() {
		return isHidden;
	}


	public void setIsHidden(String isHidden) {
		this.isHidden = isHidden;
	}


	public String getOrderTypeW() {
		return orderTypeW;
	}


	public void setOrderTypeW(String orderTypeW) {
		this.orderTypeW = orderTypeW;
	}


	public String getPriceW() {
		return priceW;
	}


	public void setPriceW(String priceW) {
		this.priceW = priceW;
	}


	public String getPvW() {
		return pvW;
	}


	public void setPvW(String pvW) {
		this.pvW = pvW;
	}


	public String getTeamCodeP() {
		return teamCodeP;
	}


	public void setTeamCodeP(String teamCodeP) {
		this.teamCodeP = teamCodeP;
	}
}
