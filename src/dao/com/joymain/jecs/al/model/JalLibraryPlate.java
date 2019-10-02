package com.joymain.jecs.al.model;
// Generated 2013-8-16 9:27:58 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAL_LIBRARY_PLATE"
 *     
 */

public class JalLibraryPlate extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long plateId;
    private String plateName;
    private String plateRemark;
    private Integer plateIndex;


    // Constructors

    /** default constructor */
    public JalLibraryPlate() {
    }

	/** minimal constructor */
    public JalLibraryPlate(String plateName) {
        this.plateName = plateName;
    }
    
    /** full constructor */
    public JalLibraryPlate(String plateName, String plateRemark) {
        this.plateName = plateName;
        this.plateRemark = plateRemark;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="PLATE_ID"
     *         
     */

    public Long getPlateId() {
        return this.plateId;
    }
    
    public void setPlateId(Long plateId) {
        this.plateId = plateId;
    }
    /**       
     *      *            @hibernate.property
     *             column="PLATE_NAME"
     *             length="300"
     *             not-null="true"
     *         
     */

    public String getPlateName() {
        return this.plateName;
    }
    
    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }
    /**       
     *      *            @hibernate.property
     *             column="PLATE_REMARK"
     *             length="300"
     *         
     */

    public String getPlateRemark() {
        return this.plateRemark;
    }
    
    public void setPlateRemark(String plateRemark) {
        this.plateRemark = plateRemark;
    }
   
    /**       
     *      *            @hibernate.property
     *             column="PLATE_INDEX"
     *             length="2"
     *         
     */
    public Integer getPlateIndex() {
		return plateIndex;
	}

	public void setPlateIndex(Integer plateIndex) {
		this.plateIndex = plateIndex;
	}

	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("plateName").append("='").append(getPlateName()).append("' ");			
      buffer.append("plateRemark").append("='").append(getPlateRemark()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JalLibraryPlate) ) return false;
		 JalLibraryPlate castOther = ( JalLibraryPlate ) other; 
         
		 return ( (this.getPlateId()==castOther.getPlateId()) || ( this.getPlateId()!=null && castOther.getPlateId()!=null && this.getPlateId().equals(castOther.getPlateId()) ) )
 && ( (this.getPlateName()==castOther.getPlateName()) || ( this.getPlateName()!=null && castOther.getPlateName()!=null && this.getPlateName().equals(castOther.getPlateName()) ) )
 && ( (this.getPlateRemark()==castOther.getPlateRemark()) || ( this.getPlateRemark()!=null && castOther.getPlateRemark()!=null && this.getPlateRemark().equals(castOther.getPlateRemark()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getPlateId() == null ? 0 : this.getPlateId().hashCode() );
         result = 37 * result + ( getPlateName() == null ? 0 : this.getPlateName().hashCode() );
         result = 37 * result + ( getPlateRemark() == null ? 0 : this.getPlateRemark().hashCode() );
         return result;
   }   





}
