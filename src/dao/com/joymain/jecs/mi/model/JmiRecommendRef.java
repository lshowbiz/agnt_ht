package com.joymain.jecs.mi.model;





/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_RECOMMEND_REF"
 *     
 */

public class JmiRecommendRef extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String userCode;
    private JmiMember recommendJmiMember;
    private long layerId;
    private Long treeNo;
    private String treeIndex;
    private JmiMember jmiMember;
    
    private String reUserName;//推荐事业体姓名
    private String sytName;	//事业体
    private String rankName;//职级
    
    
    // Constructors


	public String getSytName() {
		return sytName;
	}

	public String getReUserName() {
		return reUserName;
	}

	public void setReUserName(String reUserName) {
		this.reUserName = reUserName;
	}

	public void setSytName(String sytName) {
		this.sytName = sytName;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	/** default constructor */
    public JmiRecommendRef() {
    }

	/** minimal constructor */
    public JmiRecommendRef(String recommendNo, long layerId, String treeIndex) {
        this.layerId = layerId;
        this.treeIndex = treeIndex;
    }
    
    /** full constructor */
    public JmiRecommendRef(String recommendNo, long layerId, Long treeNo, String treeIndex) {
        this.layerId = layerId;
        this.treeNo = treeNo;
        this.treeIndex = treeIndex;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             type="java.lang.String"
     *             column="USER_CODE"
     *         
     */


	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}   


    /**       
     *      *            @hibernate.property
     *             column="LAYER_ID"
     *             length="12"
     *             not-null="true"
     *         
     */

    public long getLayerId() {
        return this.layerId;
    }
    
    public void setLayerId(long layerId) {
        this.layerId = layerId;
    }
    /**       
     *      *            @hibernate.property
     *             column="TREE_NO"
     *             length="12"
     *         
     */

    public Long getTreeNo() {
        return this.treeNo;
    }
    
    public void setTreeNo(Long treeNo) {
        this.treeNo = treeNo;
    }
    /**       
     *      *            @hibernate.property
     *             column="TREE_INDEX"
     *             length="4000"
     *             not-null="true"
     *         
     */

    public String getTreeIndex() {
        return this.treeIndex;
    }
    
    public void setTreeIndex(String treeIndex) {
        this.treeIndex = treeIndex;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("layerId").append("='").append(getLayerId()).append("' ");			
      buffer.append("treeNo").append("='").append(getTreeNo()).append("' ");			
      buffer.append("treeIndex").append("='").append(getTreeIndex()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiRecommendRef) ) return false;
		 JmiRecommendRef castOther = ( JmiRecommendRef ) other; 
         
		 return  
 (this.getLayerId()==castOther.getLayerId())
 && ( (this.getTreeNo()==castOther.getTreeNo()) || ( this.getTreeNo()!=null && castOther.getTreeNo()!=null && this.getTreeNo().equals(castOther.getTreeNo()) ) )
 && ( (this.getTreeIndex()==castOther.getTreeIndex()) || ( this.getTreeIndex()!=null && castOther.getTreeIndex()!=null && this.getTreeIndex().equals(castOther.getTreeIndex()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getLayerId();
         result = 37 * result + ( getTreeNo() == null ? 0 : this.getTreeNo().hashCode() );
         result = 37 * result + ( getTreeIndex() == null ? 0 : this.getTreeIndex().hashCode() );
         return result;
   }
   /**
	 * *
	 * @hibernate.many-to-one not-null="true"
	 * @hibernate.column name="RECOMMEND_NO"
	 * 
	 */
	public JmiMember getRecommendJmiMember() {
		return recommendJmiMember;
	}
	
	public void setRecommendJmiMember(JmiMember recommendJmiMember) {
		this.recommendJmiMember = recommendJmiMember;
	}
	
	/**
	 *      *            @hibernate.one-to-one
	 *    				constrained="true"
	 *             
	 *                  
	 */
	public JmiMember getJmiMember() {
		return jmiMember;
	}
	
	public void setJmiMember(JmiMember jmiMember) {
		this.jmiMember = jmiMember;
	}



}
