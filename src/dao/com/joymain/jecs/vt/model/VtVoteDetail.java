package com.joymain.jecs.vt.model;
// Generated 2009-12-11 11:17:37 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="VT_VOTE_DETAIL"
 *     
 */

public class VtVoteDetail extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long vdId;
    private String content;
    private Long orderNo;
    private VtVote vtVote;
    private Set<VtVoteNote> vtVoteNotes= new HashSet<VtVoteNote>(0);
    // Constructors

	/**
     * *
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="VT_ID"
     * 
     */
    public VtVote getVtVote() {
		return vtVote;
	}


	public void setVtVote(VtVote vtVote) {
		this.vtVote = vtVote;
	}


	/** default constructor */
    public VtVoteDetail() {
    }

    
    /** full constructor */
    public VtVoteDetail(Long vtId, String content, Long orderNo) {
        this.content = content;
        this.orderNo = orderNo;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="native"
     *             type="java.lang.Long"
     *             column="VD_ID"
     *         
     */

    public Long getVdId() {
        return this.vdId;
    }
    
    public void setVdId(Long vdId) {
        this.vdId = vdId;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONTENT"
     *             length="2000"
     *         
     */

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    /**       
     *      *            @hibernate.property
     *             column="ORDER_NO"
     *             length="22"
     *         
     */

    public Long getOrderNo() {
        return this.orderNo;
    }
    
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("content").append("='").append(getContent()).append("' ");			
      buffer.append("orderNo").append("='").append(getOrderNo()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof VtVoteDetail) ) return false;
		 VtVoteDetail castOther = ( VtVoteDetail ) other; 
         
		 return ( (this.getVdId()==castOther.getVdId()) || ( this.getVdId()!=null && castOther.getVdId()!=null && this.getVdId().equals(castOther.getVdId()) ) )
 && ( (this.getContent()==castOther.getContent()) || ( this.getContent()!=null && castOther.getContent()!=null && this.getContent().equals(castOther.getContent()) ) )
 && ( (this.getOrderNo()==castOther.getOrderNo()) || ( this.getOrderNo()!=null && castOther.getOrderNo()!=null && this.getOrderNo().equals(castOther.getOrderNo()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getVdId() == null ? 0 : this.getVdId().hashCode() );
         result = 37 * result + ( getContent() == null ? 0 : this.getContent().hashCode() );
         result = 37 * result + ( getOrderNo() == null ? 0 : this.getOrderNo().hashCode() );
         return result;
   }


	/**
	 * *
	 * 
	 * @hibernate.set lazy="true" inverse="true" cascade="all"
	 * @hibernate.collection-key column="VD_ID"
	 * @hibernate.collection-one-to-many class="com.joymain.jecs.vt.model.VtVoteDetail"
	 * 
	 */
public Set<VtVoteNote> getVtVoteNotes() {
	return vtVoteNotes;
}


public void setVtVoteNotes(Set<VtVoteNote> vtVoteNotes) {
	this.vtVoteNotes = vtVoteNotes;
}   





}
