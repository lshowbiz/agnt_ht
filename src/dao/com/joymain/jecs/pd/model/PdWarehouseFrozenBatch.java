package com.joymain.jecs.pd.model;

import java.util.HashSet;
import java.util.Set;

// Generated 2012-2-6 10:19:04 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="PD_WAREHOUSE_FROZEN_BATCH"
 *     
 */

public class PdWarehouseFrozenBatch extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long batchId;
    private String batchCode;
    
    private Set<PdWarehouseFrozenDetail> pdWarehouseFrozenDetails = new HashSet<PdWarehouseFrozenDetail>(0);


    // Constructors

    /**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all-delete-orphan"
	 *                
	 * @hibernate.collection-key column="batch_Id"
	 * @hibernate.collection-one-to-many 
	 * class="com.joymain.jecs.pd.model.PdWarehouseFrozenDetail"
	 * 
	 */
    public Set<PdWarehouseFrozenDetail> getPdWarehouseFrozenDetails() {
		return pdWarehouseFrozenDetails;
	}


	public void setPdWarehouseFrozenDetails(
			Set<PdWarehouseFrozenDetail> pdWarehouseFrozenDetails) {
		this.pdWarehouseFrozenDetails = pdWarehouseFrozenDetails;
	}


	/** default constructor */
    public PdWarehouseFrozenBatch() {
    }

    
    /** full constructor */
    public PdWarehouseFrozenBatch(String batchCode) {
        this.batchCode = batchCode;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *       generator-class="sequence"
     *             column="BATCH_ID"
     *             type="java.lang.Long"
     *      @hibernate.generator-param name="sequence" value="SEQ_PD"    
     */         
     

    public Long getBatchId() {
        return this.batchId;
    }
    
    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }
    /**       
     *      *            @hibernate.property
     *             column="BATCH_CODE"
     *             length="10"
     *         
     */

    public String getBatchCode() {
        return this.batchCode;
    }
    
    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("batchCode").append("='").append(getBatchCode()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PdWarehouseFrozenBatch) ) return false;
		 PdWarehouseFrozenBatch castOther = ( PdWarehouseFrozenBatch ) other; 
         
		 return ( (this.getBatchId()==castOther.getBatchId()) || ( this.getBatchId()!=null && castOther.getBatchId()!=null && this.getBatchId().equals(castOther.getBatchId()) ) )
 && ( (this.getBatchCode()==castOther.getBatchCode()) || ( this.getBatchCode()!=null && castOther.getBatchCode()!=null && this.getBatchCode().equals(castOther.getBatchCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBatchId() == null ? 0 : this.getBatchId().hashCode() );
         result = 37 * result + ( getBatchCode() == null ? 0 : this.getBatchCode().hashCode() );
         return result;
   }   





}
