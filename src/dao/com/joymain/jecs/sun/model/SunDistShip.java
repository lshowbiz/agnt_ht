package com.joymain.jecs.sun.model;

import com.joymain.jecs.pd.model.PdSendInfo;

// Generated 2010-11-22 17:54:34 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="SUN_DIST_SHIP"
 *     
 */

public class SunDistShip extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long sdsId;
//    private String siNo;
    private PdSendInfo pdSendInfo;
    private String distCode;


    // Constructors

    /** default constructor */
    public SunDistShip() {
    }

    
    /** full constructor */
    public SunDistShip(String siNo, String distCode) {
//        this.pdSendInfo.setSiNo(siNo);
        this.distCode = distCode;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="SDS_ID"
     *         
     */

    public Long getSdsId() {
        return this.sdsId;
    }
    
    public void setSdsId(Long sdsId) {
        this.sdsId = sdsId;
    }
    /**
     * *
     * @hibernate.many-to-one not-null="true" 
     * @hibernate.column name="SI_NO"
     * 
     */
    public PdSendInfo getPdSendInfo() {
		return pdSendInfo;
	}


	public void setPdSendInfo(PdSendInfo pdSendInfo) {
		this.pdSendInfo = pdSendInfo;
	}


	/**       
     *      *            @hibernate.property
     *             column="DIST_CODE"
     *             length="20"
     *         
     */

    public String getDistCode() {
        return this.distCode;
    }
    
    public void setDistCode(String distCode) {
        this.distCode = distCode;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("siNo").append("='").append(this.getPdSendInfo().getSiNo()).append("' ");			
      buffer.append("distCode").append("='").append(getDistCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SunDistShip) ) return false;
		 SunDistShip castOther = ( SunDistShip ) other; 
         
		 return ( (this.getSdsId()==castOther.getSdsId()) || ( this.getSdsId()!=null && castOther.getSdsId()!=null && this.getSdsId().equals(castOther.getSdsId()) ) )
 && ( (this.getPdSendInfo().getSiNo()==castOther.getPdSendInfo().getSiNo()) || ( this.getPdSendInfo().getSiNo()!=null && castOther.getPdSendInfo().getSiNo()!=null && this.getPdSendInfo().getSiNo().equals(castOther.getPdSendInfo().getSiNo()) ) )
 && ( (this.getDistCode()==castOther.getDistCode()) || ( this.getDistCode()!=null && castOther.getDistCode()!=null && this.getDistCode().equals(castOther.getDistCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSdsId() == null ? 0 : this.getSdsId().hashCode() );
         result = 37 * result + ( getPdSendInfo() == null ? 0 : this.getPdSendInfo().hashCode() );
         result = 37 * result + ( getDistCode() == null ? 0 : this.getDistCode().hashCode() );
         return result;
   }   





}
