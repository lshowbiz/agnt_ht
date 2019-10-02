package com.joymain.jecs.mi.model;

import java.math.BigDecimal;

// Generated 2009-9-17 12:04:28 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_LINK_REF"
 *     
 */

public class JmiLinkRef extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String userCode;
	private JmiMember linkJmiMember;
    private long layerId;
    private Long treeNo;
    private String treeIndex;
	private JmiMember jmiMember;
    private BigDecimal departmentPv; 
    private BigDecimal num;

    private String reUserName;//安置事业体姓名
    private String sytName;	//事业体
    private String rankName;//职级

    // Constructors

    public String getReUserName() {
		return reUserName;
	}

	public void setReUserName(String reUserName) {
		this.reUserName = reUserName;
	}

	public String getSytName() {
		return sytName;
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

	/**       
     *      *            @hibernate.property
     *             column="NUM"
     *         
     */
    public BigDecimal getNum() {
		return num;
	}

	public void setNum(BigDecimal num) {
		this.num = num;
	}

	/** default constructor */
    public JmiLinkRef() {
    }

	/** minimal constructor */
    public JmiLinkRef(String linkNo, long layerId, String treeIndex) {
        this.layerId = layerId;
        this.treeIndex = treeIndex;
    }
    
    /** full constructor */
    public JmiLinkRef(String linkNo, long layerId, Long treeNo, String treeIndex) {
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
		 if ( !(other instanceof JmiLinkRef) ) return false;
		 JmiLinkRef castOther = ( JmiLinkRef ) other; 
         
		 return   (this.getLayerId()==castOther.getLayerId())
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
	 * 
	 * @hibernate.one-to-one constrained="true"
	 * 
	 * 
	 */

	public JmiMember getJmiMember() {
		return jmiMember;
	}

	public void setJmiMember(JmiMember jmiMember) {
		this.jmiMember = jmiMember;
	}
	/**
	 * *
	 * 
	 * @hibernate.many-to-one not-null="true"
	 * @hibernate.column name="LINK_NO"
	 * 
	 */
	public JmiMember getLinkJmiMember() {
		return linkJmiMember;
	}

	public void setLinkJmiMember(JmiMember linkJmiMember) {
		this.linkJmiMember = linkJmiMember;
	}

    /**       
     *      *            @hibernate.property
     *             column="DEPARTMENT_PV"
     *             length="12"
     *         
     */
	public BigDecimal getDepartmentPv() {
		return departmentPv;
	}

	public void setDepartmentPv(BigDecimal departmentPv) {
		this.departmentPv = departmentPv;
	}	







}
