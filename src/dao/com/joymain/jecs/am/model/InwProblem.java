package com.joymain.jecs.am.model;
// Generated 2013-8-13 16:39:57 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="INW_PROBLEM"
 *     
 */

public class InwProblem extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    

    private Long id;
    private String species;
    private String ask;
    private String answer;
    private String other;


    // Constructors

    /** default constructor */
    public InwProblem() {
    }

	/** minimal constructor */
    public InwProblem(String species) {
        this.species = species;
    }
    
    /** full constructor */
    public InwProblem(String species, String ask, String answer, String other) {
        this.species = species;
        this.ask = ask;
        this.answer = answer;
        this.other = other;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *         @hibernate.generator-param name="sequence" value="SEQ_AM" 
     */
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.property
     *             column="SPECIES"
     *             length="2"
     *             not-null="true"
     *         
     */

    public String getSpecies() {
        return this.species;
    }
    
    public void setSpecies(String species) {
        this.species = species;
    }
    /**       
     *      *            @hibernate.property
     *             column="ASK"
     *             length="400"
     *         
     */

    public String getAsk() {
        return this.ask;
    }
    
    public void setAsk(String ask) {
        this.ask = ask;
    }
    /**       
     *      *            @hibernate.property
     *             column="ANSWER"
     *             length="900"
     *         
     */

    public String getAnswer() {
        return this.answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    /**       
     *      *            @hibernate.property
     *             column="OTHER"
     *             length="200"
     *         
     */

    public String getOther() {
        return this.other;
    }
    
    public void setOther(String other) {
        this.other = other;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("species").append("='").append(getSpecies()).append("' ");			
      buffer.append("ask").append("='").append(getAsk()).append("' ");			
      buffer.append("answer").append("='").append(getAnswer()).append("' ");			
      buffer.append("other").append("='").append(getOther()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof InwProblem) ) return false;
		 InwProblem castOther = ( InwProblem ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getSpecies()==castOther.getSpecies()) || ( this.getSpecies()!=null && castOther.getSpecies()!=null && this.getSpecies().equals(castOther.getSpecies()) ) )
 && ( (this.getAsk()==castOther.getAsk()) || ( this.getAsk()!=null && castOther.getAsk()!=null && this.getAsk().equals(castOther.getAsk()) ) )
 && ( (this.getAnswer()==castOther.getAnswer()) || ( this.getAnswer()!=null && castOther.getAnswer()!=null && this.getAnswer().equals(castOther.getAnswer()) ) )
 && ( (this.getOther()==castOther.getOther()) || ( this.getOther()!=null && castOther.getOther()!=null && this.getOther().equals(castOther.getOther()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getSpecies() == null ? 0 : this.getSpecies().hashCode() );
         result = 37 * result + ( getAsk() == null ? 0 : this.getAsk().hashCode() );
         result = 37 * result + ( getAnswer() == null ? 0 : this.getAnswer().hashCode() );
         result = 37 * result + ( getOther() == null ? 0 : this.getOther().hashCode() );
         return result;
   }   





}