package com.joymain.jecs.am.model;
// Generated 2009-11-24 15:41:15 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JAM_MSN_MODULE"
 *     
 */

public class JamMsnModule extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long jmmNo;
    private String content;
    private String titile;
    private String mmKey;
    private String mmType;
    private JamMsnType jamMsnType;
    // Constructors

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


	/** default constructor */
    public JamMsnModule() {
    }

    
    /** full constructor */
    public JamMsnModule(String content, String titile, String mmKey, String mmType, Long mtId) {
        this.content = content;
        this.titile = titile;
        this.mmKey = mmKey;
        this.mmType = mmType;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="JMM_NO"
     *@hibernate.generator-param name="sequence" value="SEQ_AM"
     *         
     */

    public Long getJmmNo() {
        return this.jmmNo;
    }
    
    public void setJmmNo(Long jmmNo) {
        this.jmmNo = jmmNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="CONTENT"
     *             length="4000"
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
     *             column="TITILE"
     *             length="100"
     *         
     */

    public String getTitile() {
        return this.titile;
    }
    
    public void setTitile(String titile) {
        this.titile = titile;
    }
    /**       
     *      *            @hibernate.property
     *             column="MM_KEY"
     *             length="50"
     *         
     */

    public String getMmKey() {
        return this.mmKey;
    }
    
    public void setMmKey(String mmKey) {
        this.mmKey = mmKey;
    }
    /**       
     *      *            @hibernate.property
     *             column="MM_TYPE"
     *             length="10"
     *         
     */

    public String getMmType() {
        return this.mmType;
    }
    
    public void setMmType(String mmType) {
        this.mmType = mmType;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("content").append("='").append(getContent()).append("' ");			
      buffer.append("titile").append("='").append(getTitile()).append("' ");			
      buffer.append("mmKey").append("='").append(getMmKey()).append("' ");			
      buffer.append("mmType").append("='").append(getMmType()).append("' ");		
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JamMsnModule) ) return false;
		 JamMsnModule castOther = ( JamMsnModule ) other; 
         
		 return ( (this.getJmmNo()==castOther.getJmmNo()) || ( this.getJmmNo()!=null && castOther.getJmmNo()!=null && this.getJmmNo().equals(castOther.getJmmNo()) ) )
 && ( (this.getContent()==castOther.getContent()) || ( this.getContent()!=null && castOther.getContent()!=null && this.getContent().equals(castOther.getContent()) ) )
 && ( (this.getTitile()==castOther.getTitile()) || ( this.getTitile()!=null && castOther.getTitile()!=null && this.getTitile().equals(castOther.getTitile()) ) )
 && ( (this.getMmKey()==castOther.getMmKey()) || ( this.getMmKey()!=null && castOther.getMmKey()!=null && this.getMmKey().equals(castOther.getMmKey()) ) )
 && ( (this.getMmType()==castOther.getMmType()) || ( this.getMmType()!=null && castOther.getMmType()!=null && this.getMmType().equals(castOther.getMmType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getJmmNo() == null ? 0 : this.getJmmNo().hashCode() );
         result = 37 * result + ( getContent() == null ? 0 : this.getContent().hashCode() );
         result = 37 * result + ( getTitile() == null ? 0 : this.getTitile().hashCode() );
         result = 37 * result + ( getMmKey() == null ? 0 : this.getMmKey().hashCode() );
         result = 37 * result + ( getMmType() == null ? 0 : this.getMmType().hashCode() );
         return result;
   }   





}
