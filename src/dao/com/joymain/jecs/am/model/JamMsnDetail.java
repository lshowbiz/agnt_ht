package com.joymain.jecs.am.model;
// Generated 2009-11-6 15:20:16 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAM_MSN_DETAIL"
 *     
 */

public class JamMsnDetail extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long mdId;
    private String userCode;
    private JamMsnType jamMsnType;
    private String status;


    // Constructors

    /** default constructor */
    public JamMsnDetail() {
    }

    
    /** full constructor */
    public JamMsnDetail(String userCode, Long mtId, String status) {
        this.userCode = userCode;
        this.status = status;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="MD_ID"
     *@hibernate.generator-param name="sequence" value="SEQ_AM"
     *         
     */

    public Long getMdId() {
        return this.mdId;
    }
    
    public void setMdId(Long mdId) {
        this.mdId = mdId;
    }
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="20"
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
     *             column="STATUS"
     *             length="1"
     *         
     */

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");				
      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JamMsnDetail) ) return false;
		 JamMsnDetail castOther = ( JamMsnDetail ) other; 
         
		 return ( (this.getMdId()==castOther.getMdId()) || ( this.getMdId()!=null && castOther.getMdId()!=null && this.getMdId().equals(castOther.getMdId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )

 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMdId() == null ? 0 : this.getMdId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         return result;
   }

	/**
    * *
    * @hibernate.many-to-one not-null="true"
    * @hibernate.column name="MT_ID"
    * 
    */
	public JamMsnType getJamMsnType() {
		return jamMsnType;
	}
	
	
	public void setJamMsnType(JamMsnType jamMsnType) {
		this.jamMsnType = jamMsnType;
	}   





}
