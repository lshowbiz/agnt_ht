package com.joymain.jecs.am.model;
// Generated 2009-11-6 15:19:21 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAM_MSN_TYPE"
 *     
 */

public class JamMsnType extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long mtId;
    private String msnKey;
    private String msnName;
    private String msnDesc;
    private String msnStatus;


    // Constructors

    /** default constructor */
    public JamMsnType() {
    }

    
    /** full constructor */
    public JamMsnType(String msnKey, String msnName, String msnDesc, String msnStatus) {
        this.msnKey = msnKey;
        this.msnName = msnName;
        this.msnDesc = msnDesc;
        this.msnStatus = msnStatus;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="MT_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_AM"
     *         
     */

    public Long getMtId() {
        return this.mtId;
    }
    
    public void setMtId(Long mtId) {
        this.mtId = mtId;
    }
    /**       
     *      *            @hibernate.property
     *             column="MSN_KEY"
     *             length="50"
     *         
     */

    public String getMsnKey() {
        return this.msnKey;
    }
    
    public void setMsnKey(String msnKey) {
        this.msnKey = msnKey;
    }
    /**       
     *      *            @hibernate.property
     *             column="MSN_NAME"
     *             length="150"
     *         
     */

    public String getMsnName() {
        return this.msnName;
    }
    
    public void setMsnName(String msnName) {
        this.msnName = msnName;
    }
    /**       
     *      *            @hibernate.property
     *             column="MSN_DESC"
     *             length="250"
     *         
     */

    public String getMsnDesc() {
        return this.msnDesc;
    }
    
    public void setMsnDesc(String msnDesc) {
        this.msnDesc = msnDesc;
    }
    /**       
     *      *            @hibernate.property
     *             column="MSN_STATUS"
     *             length="1"
     *         
     */

    public String getMsnStatus() {
        return this.msnStatus;
    }
    
    public void setMsnStatus(String msnStatus) {
        this.msnStatus = msnStatus;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("msnKey").append("='").append(getMsnKey()).append("' ");			
      buffer.append("msnName").append("='").append(getMsnName()).append("' ");			
      buffer.append("msnDesc").append("='").append(getMsnDesc()).append("' ");			
      buffer.append("msnStatus").append("='").append(getMsnStatus()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JamMsnType) ) return false;
		 JamMsnType castOther = ( JamMsnType ) other; 
         
		 return ( (this.getMtId()==castOther.getMtId()) || ( this.getMtId()!=null && castOther.getMtId()!=null && this.getMtId().equals(castOther.getMtId()) ) )
 && ( (this.getMsnKey()==castOther.getMsnKey()) || ( this.getMsnKey()!=null && castOther.getMsnKey()!=null && this.getMsnKey().equals(castOther.getMsnKey()) ) )
 && ( (this.getMsnName()==castOther.getMsnName()) || ( this.getMsnName()!=null && castOther.getMsnName()!=null && this.getMsnName().equals(castOther.getMsnName()) ) )
 && ( (this.getMsnDesc()==castOther.getMsnDesc()) || ( this.getMsnDesc()!=null && castOther.getMsnDesc()!=null && this.getMsnDesc().equals(castOther.getMsnDesc()) ) )
 && ( (this.getMsnStatus()==castOther.getMsnStatus()) || ( this.getMsnStatus()!=null && castOther.getMsnStatus()!=null && this.getMsnStatus().equals(castOther.getMsnStatus()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMtId() == null ? 0 : this.getMtId().hashCode() );
         result = 37 * result + ( getMsnKey() == null ? 0 : this.getMsnKey().hashCode() );
         result = 37 * result + ( getMsnName() == null ? 0 : this.getMsnName().hashCode() );
         result = 37 * result + ( getMsnDesc() == null ? 0 : this.getMsnDesc().hashCode() );
         result = 37 * result + ( getMsnStatus() == null ? 0 : this.getMsnStatus().hashCode() );
         return result;
   }   





}
