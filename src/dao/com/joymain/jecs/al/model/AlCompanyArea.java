package com.joymain.jecs.al.model;
// Generated 2009-5-21 18:58:10 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_COMPANY_AREA"
 *     
 */

public class AlCompanyArea extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String companyCode;
    private String level1AreaCode;
    private String level2AreaCode;
    private String level3AreaCode;
    private String level4AreaCode;
    private String level5AreaCode;
    private String level6AreaCode;


    // Constructors

    /** default constructor */
    public AlCompanyArea() {
    }

    
    /** full constructor */
    public AlCompanyArea(String companyCode, String level1AreaCode, String level2AreaCode, String level3AreaCode, String level4AreaCode, String level5AreaCode, String level6AreaCode) {
        this.companyCode = companyCode;
        this.level1AreaCode = level1AreaCode;
        this.level2AreaCode = level2AreaCode;
        this.level3AreaCode = level3AreaCode;
        this.level4AreaCode = level4AreaCode;
        this.level5AreaCode = level5AreaCode;
        this.level6AreaCode = level6AreaCode;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="ID"
     *         
     */

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="LEVEL1_AREA_CODE"
     *             length="30"
     *         
     */

    public String getLevel1AreaCode() {
        return this.level1AreaCode;
    }
    
    public void setLevel1AreaCode(String level1AreaCode) {
        this.level1AreaCode = level1AreaCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="LEVEL2_AREA_CODE"
     *             length="30"
     *         
     */

    public String getLevel2AreaCode() {
        return this.level2AreaCode;
    }
    
    public void setLevel2AreaCode(String level2AreaCode) {
        this.level2AreaCode = level2AreaCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="LEVEL3_AREA_CODE"
     *             length="30"
     *         
     */

    public String getLevel3AreaCode() {
        return this.level3AreaCode;
    }
    
    public void setLevel3AreaCode(String level3AreaCode) {
        this.level3AreaCode = level3AreaCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="LEVEL4_AREA_CODE"
     *             length="30"
     *         
     */

    public String getLevel4AreaCode() {
        return this.level4AreaCode;
    }
    
    public void setLevel4AreaCode(String level4AreaCode) {
        this.level4AreaCode = level4AreaCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="LEVEL5_AREA_CODE"
     *             length="30"
     *         
     */

    public String getLevel5AreaCode() {
        return this.level5AreaCode;
    }
    
    public void setLevel5AreaCode(String level5AreaCode) {
        this.level5AreaCode = level5AreaCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="LEVEL6_AREA_CODE"
     *             length="30"
     *         
     */

    public String getLevel6AreaCode() {
        return this.level6AreaCode;
    }
    
    public void setLevel6AreaCode(String level6AreaCode) {
        this.level6AreaCode = level6AreaCode;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("companyCode").append("='").append(getCompanyCode()).append("' ");			
      buffer.append("level1AreaCode").append("='").append(getLevel1AreaCode()).append("' ");			
      buffer.append("level2AreaCode").append("='").append(getLevel2AreaCode()).append("' ");			
      buffer.append("level3AreaCode").append("='").append(getLevel3AreaCode()).append("' ");			
      buffer.append("level4AreaCode").append("='").append(getLevel4AreaCode()).append("' ");			
      buffer.append("level5AreaCode").append("='").append(getLevel5AreaCode()).append("' ");			
      buffer.append("level6AreaCode").append("='").append(getLevel6AreaCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AlCompanyArea) ) return false;
		 AlCompanyArea castOther = ( AlCompanyArea ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getCompanyCode()==castOther.getCompanyCode()) || ( this.getCompanyCode()!=null && castOther.getCompanyCode()!=null && this.getCompanyCode().equals(castOther.getCompanyCode()) ) )
 && ( (this.getLevel1AreaCode()==castOther.getLevel1AreaCode()) || ( this.getLevel1AreaCode()!=null && castOther.getLevel1AreaCode()!=null && this.getLevel1AreaCode().equals(castOther.getLevel1AreaCode()) ) )
 && ( (this.getLevel2AreaCode()==castOther.getLevel2AreaCode()) || ( this.getLevel2AreaCode()!=null && castOther.getLevel2AreaCode()!=null && this.getLevel2AreaCode().equals(castOther.getLevel2AreaCode()) ) )
 && ( (this.getLevel3AreaCode()==castOther.getLevel3AreaCode()) || ( this.getLevel3AreaCode()!=null && castOther.getLevel3AreaCode()!=null && this.getLevel3AreaCode().equals(castOther.getLevel3AreaCode()) ) )
 && ( (this.getLevel4AreaCode()==castOther.getLevel4AreaCode()) || ( this.getLevel4AreaCode()!=null && castOther.getLevel4AreaCode()!=null && this.getLevel4AreaCode().equals(castOther.getLevel4AreaCode()) ) )
 && ( (this.getLevel5AreaCode()==castOther.getLevel5AreaCode()) || ( this.getLevel5AreaCode()!=null && castOther.getLevel5AreaCode()!=null && this.getLevel5AreaCode().equals(castOther.getLevel5AreaCode()) ) )
 && ( (this.getLevel6AreaCode()==castOther.getLevel6AreaCode()) || ( this.getLevel6AreaCode()!=null && castOther.getLevel6AreaCode()!=null && this.getLevel6AreaCode().equals(castOther.getLevel6AreaCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getCompanyCode() == null ? 0 : this.getCompanyCode().hashCode() );
         result = 37 * result + ( getLevel1AreaCode() == null ? 0 : this.getLevel1AreaCode().hashCode() );
         result = 37 * result + ( getLevel2AreaCode() == null ? 0 : this.getLevel2AreaCode().hashCode() );
         result = 37 * result + ( getLevel3AreaCode() == null ? 0 : this.getLevel3AreaCode().hashCode() );
         result = 37 * result + ( getLevel4AreaCode() == null ? 0 : this.getLevel4AreaCode().hashCode() );
         result = 37 * result + ( getLevel5AreaCode() == null ? 0 : this.getLevel5AreaCode().hashCode() );
         result = 37 * result + ( getLevel6AreaCode() == null ? 0 : this.getLevel6AreaCode().hashCode() );
         return result;
   }   





}
