package com.joymain.jecs.sys.model;
// Generated 2009-10-27 16:35:51 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_BACK_SERVICE_INFO"
 *     
 */

public class SysBackServiceInfo extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long bsiId;
    private String exeFlag;
    private String info;
    private String bsiType;
    private String command;


    // Constructors

    /** default constructor */
    public SysBackServiceInfo() {
    }

    
    /** full constructor */
    public SysBackServiceInfo(String exeFlag, String info, String bsiType, String command) {
        this.exeFlag = exeFlag;
        this.info = info;
        this.bsiType = bsiType;
        this.command = command;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="BSI_ID"
     *         @hibernate.generator-param name="sequence" value="SEQ_SYS"
     */

    public Long getBsiId() {
        return this.bsiId;
    }
    
    public void setBsiId(Long bsiId) {
        this.bsiId = bsiId;
    }
    /**       
     *      *            @hibernate.property
     *             column="EXE_FLAG"
     *             length="2"
     *         
     */

    public String getExeFlag() {
        return this.exeFlag;
    }
    
    public void setExeFlag(String exeFlag) {
        this.exeFlag = exeFlag;
    }
    /**       
     *      *            @hibernate.property
     *             column="INFO"
     *             length="500"
     *         
     */

    public String getInfo() {
        return this.info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }
    /**       
     *      *            @hibernate.property
     *             column="BSI_TYPE"
     *             length="8"
     *         
     */

    public String getBsiType() {
        return this.bsiType;
    }
    
    public void setBsiType(String bsiType) {
        this.bsiType = bsiType;
    }
    /**       
     *      *            @hibernate.property
     *             column="COMMAND"
     *             length="500"
     *         
     */

    public String getCommand() {
        return this.command;
    }
    
    public void setCommand(String command) {
        this.command = command;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("exeFlag").append("='").append(getExeFlag()).append("' ");			
      buffer.append("info").append("='").append(getInfo()).append("' ");			
      buffer.append("bsiType").append("='").append(getBsiType()).append("' ");			
      buffer.append("command").append("='").append(getCommand()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysBackServiceInfo) ) return false;
		 SysBackServiceInfo castOther = ( SysBackServiceInfo ) other; 
         
		 return ( (this.getBsiId()==castOther.getBsiId()) || ( this.getBsiId()!=null && castOther.getBsiId()!=null && this.getBsiId().equals(castOther.getBsiId()) ) )
 && ( (this.getExeFlag()==castOther.getExeFlag()) || ( this.getExeFlag()!=null && castOther.getExeFlag()!=null && this.getExeFlag().equals(castOther.getExeFlag()) ) )
 && ( (this.getInfo()==castOther.getInfo()) || ( this.getInfo()!=null && castOther.getInfo()!=null && this.getInfo().equals(castOther.getInfo()) ) )
 && ( (this.getBsiType()==castOther.getBsiType()) || ( this.getBsiType()!=null && castOther.getBsiType()!=null && this.getBsiType().equals(castOther.getBsiType()) ) )
 && ( (this.getCommand()==castOther.getCommand()) || ( this.getCommand()!=null && castOther.getCommand()!=null && this.getCommand().equals(castOther.getCommand()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getBsiId() == null ? 0 : this.getBsiId().hashCode() );
         result = 37 * result + ( getExeFlag() == null ? 0 : this.getExeFlag().hashCode() );
         result = 37 * result + ( getInfo() == null ? 0 : this.getInfo().hashCode() );
         result = 37 * result + ( getBsiType() == null ? 0 : this.getBsiType().hashCode() );
         result = 37 * result + ( getCommand() == null ? 0 : this.getCommand().hashCode() );
         return result;
   }   





}
