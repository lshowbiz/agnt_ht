package com.joymain.jecs.bd.model;
// Generated 2009-9-29 13:08:55 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JBD_USER_CARD_LIST"
 *     
 */

public class JbdUserCardList extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String userCode;
    private Integer wweek;
    private String oldCard;
    private String newCard;
    private String updateType;

    // Constructors
    /**       
     *      *            @hibernate.property
     *             column="UPDATE_TYPE"
     *             length="1"
     *         
     */
    public String getUpdateType() {
		return updateType;
	}


	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}


	/** default constructor */
    public JbdUserCardList() {
    }

    
    /** full constructor */
    public JbdUserCardList(String userCode, Integer wweek, String oldCard, String newCard) {
        this.userCode = userCode;
        this.wweek = wweek;
        this.oldCard = oldCard;
        this.newCard = newCard;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_BD"
     *         
     */
    public Long getId() {
    	return id;
    }


    public void setId(Long id) {
    	this.id = id;
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
     *             column="W_WEEK"
     *             length="22"
     *         
     */
    public Integer getWweek() {
		return wweek;
	}


	public void setWweek(Integer wweek) {
		this.wweek = wweek;
	}


	/**       
     *      *            @hibernate.property
     *             column="OLD_CARD"
     *             length="2"
     *         
     */

    public String getOldCard() {
        return this.oldCard;
    }
    
    public void setOldCard(String oldCard) {
        this.oldCard = oldCard;
    }
    /**       
     *      *            @hibernate.property
     *             column="NEW_CARD"
     *             length="2"
     *         
     */

    public String getNewCard() {
        return this.newCard;
    }
    
    public void setNewCard(String newCard) {
        this.newCard = newCard;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
      buffer.append("wweek").append("='").append(getWweek()).append("' ");			
      buffer.append("oldCard").append("='").append(getOldCard()).append("' ");			
      buffer.append("newCard").append("='").append(getNewCard()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JbdUserCardList) ) return false;
		 JbdUserCardList castOther = ( JbdUserCardList ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getWweek()==castOther.getWweek()) || ( this.getWweek()!=null && castOther.getWweek()!=null && this.getWweek().equals(castOther.getWweek()) ) )
 && ( (this.getOldCard()==castOther.getOldCard()) || ( this.getOldCard()!=null && castOther.getOldCard()!=null && this.getOldCard().equals(castOther.getOldCard()) ) )
 && ( (this.getNewCard()==castOther.getNewCard()) || ( this.getNewCard()!=null && castOther.getNewCard()!=null && this.getNewCard().equals(castOther.getNewCard()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getWweek() == null ? 0 : this.getWweek().hashCode() );
         result = 37 * result + ( getOldCard() == null ? 0 : this.getOldCard().hashCode() );
         result = 37 * result + ( getNewCard() == null ? 0 : this.getNewCard().hashCode() );
         return result;
   }






}
