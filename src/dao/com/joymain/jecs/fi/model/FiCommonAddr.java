package com.joymain.jecs.fi.model;
// Generated 2014-3-25 10:20:59 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="FI_COMMON_ADDR"
 *     
 */

public class FiCommonAddr extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String userCode;
    private String province;
    private String city;
    private String district;
    private String address;


    // Constructors

    /** default constructor */
    public FiCommonAddr() {
    }

	/** minimal constructor */
    public FiCommonAddr(String province, String city) {
        this.province = province;
        this.city = city;
    }
    
    /** full constructor */
    public FiCommonAddr(String province, String city, String district, String address) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="USER_CODE"
     *         
     */

    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="PROVINCE"
     *             length="40"
     *             not-null="true"
     *         
     */

    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    /**       
     *      *            @hibernate.property
     *             column="CITY"
     *             length="40"
     *             not-null="true"
     *         
     */

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    /**       
     *      *            @hibernate.property
     *             column="DISTRICT"
     *             length="40"
     *         
     */

    public String getDistrict() {
        return this.district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    /**       
     *      *            @hibernate.property
     *             column="ADDRESS"
     *             length="500"
     *         
     */

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("province").append("='").append(getProvince()).append("' ");			
      buffer.append("city").append("='").append(getCity()).append("' ");			
      buffer.append("district").append("='").append(getDistrict()).append("' ");			
      buffer.append("address").append("='").append(getAddress()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   
   @Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result
				+ ((district == null) ? 0 : district.hashCode());
		result = prime * result
				+ ((province == null) ? 0 : province.hashCode());
		result = prime * result
				+ ((userCode == null) ? 0 : userCode.hashCode());
		return result;
	}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	
	if (getClass() != obj.getClass())
		return false;
	FiCommonAddr other = (FiCommonAddr) obj;
	if (address == null) {
		if (other.address != null)
			return false;
	} else if (!address.equals(other.address))
		return false;
	if (city == null) {
		if (other.city != null)
			return false;
	} else if (!city.equals(other.city))
		return false;
	if (district == null) {
		if (other.district != null)
			return false;
	} else if (!district.equals(other.district))
		return false;
	if (province == null) {
		if (other.province != null)
			return false;
	} else if (!province.equals(other.province))
		return false;
	if (userCode == null) {
		if (other.userCode != null)
			return false;
	} else if (!userCode.equals(other.userCode))
		return false;
	return true;
}





}
