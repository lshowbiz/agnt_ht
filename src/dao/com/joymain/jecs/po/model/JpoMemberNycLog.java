package com.joymain.jecs.po.model;
// Generated 2016-9-1 17:01:00 by Hibernate Tools 3.1.0.beta4

import java.sql.Timestamp;
import java.util.Date;


/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JPO_MEMBER_NYC_LOG"
 *     
 */

public class JpoMemberNycLog extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private String id;
    private String targetId;
    private String oldData;
    private String newData;
    private Date creatAt;
    private String creator;


    // Constructors

    /** default constructor */
    public JpoMemberNycLog() {
    }

    
    /** full constructor */
    public JpoMemberNycLog(String id, String targetId, String oldData, String newData, Timestamp creatAt, String creator) {
        this.id = id;
        this.targetId = targetId;
        this.oldData = oldData;
        this.newData = newData;
        this.creatAt = creatAt;
        this.creator = creator;
    }
    

    // Property accessors
    /**
     * * @hibernate.id generator-class="assigned"  type="java.lang.String"
     * column="ID"
     *
     *
     *
     */

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="TARGET_ID"
     *             
     */

    public String getTargetId() {
        return this.targetId;
    }
    
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="OLD_DATA"
     *             
     */

    public String getOldData() {
        return this.oldData;
    }
    
    public void setOldData(String oldData) {
        this.oldData = oldData;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="NEW_DATA"
     *             
     */

    public String getNewData() {
        return this.newData;
    }
    
    public void setNewData(String newData) {
        this.newData = newData;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="CREAT_AT"
     *             
     */

    public Date getCreatAt() {
        return this.creatAt;
    }
    
    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
    }
    /**       
     *      *            @hibernate.id
     *             generator-class="assigned"
     *         
     *                @hibernate.property
     *                 column="CREATOR"
     *             
     */

    public String getCreator() {
        return this.creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }




    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
        if ( (other == null ) ) return false;
        if ( !(other instanceof JpoMemberNycLog) ) return false;
        JpoMemberNycLog castOther = ( JpoMemberNycLog ) other;

        return ( (this.getId().equals(castOther.getId())) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
                && ( (this.getTargetId()==castOther.getTargetId()) || ( this.getTargetId()!=null && castOther.getTargetId()!=null && this.getTargetId().equals(castOther.getTargetId()) ) )
                && ( (this.getCreator()==castOther.getCreator()) || ( this.getCreator()!=null && castOther.getCreator()!=null && this.getCreator().equals(castOther.getCreator()) ) )
                && ( (this.getCreatAt()==castOther.getCreatAt()) || ( this.getCreatAt()!=null && castOther.getCreatAt()!=null && this.getCreatAt().equals(castOther.getCreatAt()) ) )
                && ( (this.getNewData()==castOther.getNewData()) || ( this.getNewData()!=null && castOther.getNewData()!=null && this.getNewData().equals(castOther.getNewData()) ) )
                && ( (this.getOldData()==castOther.getOldData()) || ( this.getOldData()!=null && castOther.getOldData()!=null && this.getOldData().equals(castOther.getOldData()) ) );
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
        result = 37 * result + ( getTargetId() == null ? 0 : this.getTargetId().hashCode() );
        result = 37 * result + ( getCreator() == null ? 0 : this.getCreator().hashCode() );
        result = 37 * result + ( getCreatAt() == null ? 0 : this.getCreatAt().hashCode() );
        result = 37 * result + ( getNewData() == null ? 0 : this.getNewData().hashCode() );
        result = 37 * result + ( getOldData() == null ? 0 : this.getOldData().hashCode() );
        return result;
    }


    @Override
    public String toString() {
        return this.id;
    }
}
