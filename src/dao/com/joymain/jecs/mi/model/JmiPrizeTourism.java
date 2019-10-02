package com.joymain.jecs.mi.model;
// Generated 2015-12-30 11:28:29 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JMI_PRIZE_TOURISM"
 *     
 */

public class JmiPrizeTourism extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {


    // Fields    
	private Long prizeTouismId;
    private String userCode;
    private String cardname;
    private String cardid;
    private String phone;
    private String province;
    private String city;
    private String area;
    private String address;
    private String beforeyearpoints;
    private String height;
    private String weight;
    private String clothessize;
    private String avoidcertainfood;
    private String acceptanceaddress;
    private Integer passStar;
    private String peertype;
    private String peersex;
    private String peercardname;
    private String peercardid;
    private String peerphone;
    private String peerprovince;
    private String peercity;
    private String peerarea;
    private String peeraddress;
    private String peerheight;
    private String peerweight;
    private String peerclothessize;
    private String peeravoidcertainfood;
    private String isPeer ;


    // Constructors

    /** default constructor */
    public JmiPrizeTourism() {
    }

    
    /** full constructor */
    public JmiPrizeTourism(String cardname, String cardid, String phone, String province, String city, String area, String address, String beforeyearpoints, String height, String weight, String clothessize, String avoidcertainfood, String acceptanceaddress, Integer passStar, String peertype, String peersex, String peercardname, String peercardid, String peerphone, String peerprovince, String peercity, String peerarea, String peeraddress, String peerheight, String peerweight, String peerclothessize, String peeravoidcertainfood) {
        this.cardname = cardname;
        this.cardid = cardid;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.area = area;
        this.address = address;
        this.beforeyearpoints = beforeyearpoints;
        this.height = height;
        this.weight = weight;
        this.clothessize = clothessize;
        this.avoidcertainfood = avoidcertainfood;
        this.acceptanceaddress = acceptanceaddress;
        this.passStar = passStar;
        this.peertype = peertype;
        this.peersex = peersex;
        this.peercardname = peercardname;
        this.peercardid = peercardid;
        this.peerphone = peerphone;
        this.peerprovince = peerprovince;
        this.peercity = peercity;
        this.peerarea = peerarea;
        this.peeraddress = peeraddress;
        this.peerheight = peerheight;
        this.peerweight = peerweight;
        this.peerclothessize = peerclothessize;
        this.peeravoidcertainfood = peeravoidcertainfood;
    }
    

    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="PRIZETOUISMID" 
     *@hibernate.generator-param name="sequence" value="SEQ_MI"
     *         
     */
    public Long getPrizeTouismId() {
		return prizeTouismId;
	}


	public void setPrizeTouismId(Long prizeTouismId) {
		this.prizeTouismId = prizeTouismId;
	}


	// Property accessors
	/**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="50"
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
     *             column="CARDNAME"
     *             length="50"
     *         
     */

    public String getCardname() {
        return this.cardname;
    }
    
    public void setCardname(String cardname) {
        this.cardname = cardname;
    }
    /**       
     *      *            @hibernate.property
     *             column="CARDID"
     *             length="20"
     *         
     */

    public String getCardid() {
        return this.cardid;
    }
    
    public void setCardid(String cardid) {
        this.cardid = cardid;
    }
    /**       
     *      *            @hibernate.property
     *             column="PHONE"
     *             length="20"
     *         
     */

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**       
     *      *            @hibernate.property
     *             column="PROVINCE"
     *             length="10"
     *         
     */

    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    /**       
     *      *            @hibernate.property
     *             column="CITY"
     *             length="10"
     *         
     */

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    /**       
     *      *            @hibernate.property
     *             column="AREA"
     *             length="10"
     *         
     */

    public String getArea() {
        return this.area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    /**       
     *      *            @hibernate.property
     *             column="ADDRESS"
     *             length="200"
     *         
     */

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    /**       
     *      *            @hibernate.property
     *             column="BEFOREYEARPOINTS"
     *             length="20"
     *         
     */

    public String getBeforeyearpoints() {
        return this.beforeyearpoints;
    }
    
    public void setBeforeyearpoints(String beforeyearpoints) {
        this.beforeyearpoints = beforeyearpoints;
    }
    /**       
     *      *            @hibernate.property
     *             column="HEIGHT"
     *             length="20"
     *         
     */

    public String getHeight() {
        return this.height;
    }
    
    public void setHeight(String height) {
        this.height = height;
    }
    /**       
     *      *            @hibernate.property
     *             column="WEIGHT"
     *             length="20"
     *         
     */

    public String getWeight() {
        return this.weight;
    }
    
    public void setWeight(String weight) {
        this.weight = weight;
    }
    /**       
     *      *            @hibernate.property
     *             column="CLOTHESSIZE"
     *             length="10"
     *         
     */

    public String getClothessize() {
        return this.clothessize;
    }
    
    public void setClothessize(String clothessize) {
        this.clothessize = clothessize;
    }
    /**       
     *      *            @hibernate.property
     *             column="AVOIDCERTAINFOOD"
     *             length="20"
     *         
     */

    public String getAvoidcertainfood() {
        return this.avoidcertainfood;
    }
    
    public void setAvoidcertainfood(String avoidcertainfood) {
        this.avoidcertainfood = avoidcertainfood;
    }
    /**       
     *      *            @hibernate.property
     *             column="ACCEPTANCEADDRESS"
     *             length="300"
     *         
     */

    public String getAcceptanceaddress() {
        return this.acceptanceaddress;
    }
    
    public void setAcceptanceaddress(String acceptanceaddress) {
        this.acceptanceaddress = acceptanceaddress;
    }
    /**       
     *      *            @hibernate.property
     *             column="PASS_STAR"
     *             length="2"
     *         
     */

    public Integer getPassStar() {
        return this.passStar;
    }
    
    public void setPassStar(Integer passStar) {
        this.passStar = passStar;
    }
    /**       
     *      *            @hibernate.property
     *             column="PEERTYPE"
     *             length="10"
     *         
     */

    public String getPeertype() {
        return this.peertype;
    }
    
    public void setPeertype(String peertype) {
        this.peertype = peertype;
    }
    /**       
     *      *            @hibernate.property
     *             column="PEERSEX"
     *             length="10"
     *         
     */

    public String getPeersex() {
        return this.peersex;
    }
    
    public void setPeersex(String peersex) {
        this.peersex = peersex;
    }
    /**       
     *      *            @hibernate.property
     *             column="PEERCARDNAME"
     *             length="50"
     *         
     */

    public String getPeercardname() {
        return this.peercardname;
    }
    
    public void setPeercardname(String peercardname) {
        this.peercardname = peercardname;
    }
    /**       
     *      *            @hibernate.property
     *             column="PEERCARDID"
     *             length="20"
     *         
     */

    public String getPeercardid() {
        return this.peercardid;
    }
    
    public void setPeercardid(String peercardid) {
        this.peercardid = peercardid;
    }
    /**       
     *      *            @hibernate.property
     *             column="PEERPHONE"
     *             length="20"
     *         
     */

    public String getPeerphone() {
        return this.peerphone;
    }
    
    public void setPeerphone(String peerphone) {
        this.peerphone = peerphone;
    }
    /**       
     *      *            @hibernate.property
     *             column="PEERPROVINCE"
     *             length="10"
     *         
     */

    public String getPeerprovince() {
        return this.peerprovince;
    }
    
    public void setPeerprovince(String peerprovince) {
        this.peerprovince = peerprovince;
    }
    /**       
     *      *            @hibernate.property
     *             column="PEERCITY"
     *             length="10"
     *         
     */

    public String getPeercity() {
        return this.peercity;
    }
    
    public void setPeercity(String peercity) {
        this.peercity = peercity;
    }
    /**       
     *      *            @hibernate.property
     *             column="PEERAREA"
     *             length="10"
     *         
     */

    public String getPeerarea() {
        return this.peerarea;
    }
    
    public void setPeerarea(String peerarea) {
        this.peerarea = peerarea;
    }
    /**       
     *      *            @hibernate.property
     *             column="PEERADDRESS"
     *             length="200"
     *         
     */

    public String getPeeraddress() {
        return this.peeraddress;
    }
    
    public void setPeeraddress(String peeraddress) {
        this.peeraddress = peeraddress;
    }
    /**       
     *      *            @hibernate.property
     *             column="PEERHEIGHT"
     *             length="20"
     *         
     */

    public String getPeerheight() {
        return this.peerheight;
    }
    
    public void setPeerheight(String peerheight) {
        this.peerheight = peerheight;
    }
    /**       
     *      *            @hibernate.property
     *             column="PEERWEIGHT"
     *             length="20"
     *         
     */

    public String getPeerweight() {
        return this.peerweight;
    }
    
    public void setPeerweight(String peerweight) {
        this.peerweight = peerweight;
    }
    /**       
     *      *            @hibernate.property
     *             column="PEERCLOTHESSIZE"
     *             length="10"
     *         
     */

    public String getPeerclothessize() {
        return this.peerclothessize;
    }
    
    public void setPeerclothessize(String peerclothessize) {
        this.peerclothessize = peerclothessize;
    }
    /**       
     *      *            @hibernate.property
     *             column="PEERAVOIDCERTAINFOOD"
     *             length="20"
     *         
     */

    public String getPeeravoidcertainfood() {
        return this.peeravoidcertainfood;
    }
    
    public void setPeeravoidcertainfood(String peeravoidcertainfood) {
        this.peeravoidcertainfood = peeravoidcertainfood;
    }
   
    /**       
     *      *            @hibernate.property
     *             column="ISPEER"
     *             length="2"
     *         
     */
    public String getIsPeer() {
		return isPeer;
	}


	public void setIsPeer(String isPeer) {
		this.isPeer = isPeer;
	}


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("cardname").append("='").append(getCardname()).append("' ");			
      buffer.append("cardid").append("='").append(getCardid()).append("' ");			
      buffer.append("phone").append("='").append(getPhone()).append("' ");			
      buffer.append("province").append("='").append(getProvince()).append("' ");			
      buffer.append("city").append("='").append(getCity()).append("' ");			
      buffer.append("area").append("='").append(getArea()).append("' ");			
      buffer.append("address").append("='").append(getAddress()).append("' ");			
      buffer.append("beforeyearpoints").append("='").append(getBeforeyearpoints()).append("' ");			
      buffer.append("height").append("='").append(getHeight()).append("' ");			
      buffer.append("weight").append("='").append(getWeight()).append("' ");			
      buffer.append("clothessize").append("='").append(getClothessize()).append("' ");			
      buffer.append("avoidcertainfood").append("='").append(getAvoidcertainfood()).append("' ");			
      buffer.append("acceptanceaddress").append("='").append(getAcceptanceaddress()).append("' ");			
      buffer.append("passStar").append("='").append(getPassStar()).append("' ");			
      buffer.append("peertype").append("='").append(getPeertype()).append("' ");			
      buffer.append("peersex").append("='").append(getPeersex()).append("' ");			
      buffer.append("peercardname").append("='").append(getPeercardname()).append("' ");			
      buffer.append("peercardid").append("='").append(getPeercardid()).append("' ");			
      buffer.append("peerphone").append("='").append(getPeerphone()).append("' ");			
      buffer.append("peerprovince").append("='").append(getPeerprovince()).append("' ");			
      buffer.append("peercity").append("='").append(getPeercity()).append("' ");			
      buffer.append("peerarea").append("='").append(getPeerarea()).append("' ");			
      buffer.append("peeraddress").append("='").append(getPeeraddress()).append("' ");			
      buffer.append("peerheight").append("='").append(getPeerheight()).append("' ");			
      buffer.append("peerweight").append("='").append(getPeerweight()).append("' ");			
      buffer.append("peerclothessize").append("='").append(getPeerclothessize()).append("' ");			
      buffer.append("peeravoidcertainfood").append("='").append(getPeeravoidcertainfood()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JmiPrizeTourism) ) return false;
		 JmiPrizeTourism castOther = ( JmiPrizeTourism ) other; 
         
		 return ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
 && ( (this.getCardname()==castOther.getCardname()) || ( this.getCardname()!=null && castOther.getCardname()!=null && this.getCardname().equals(castOther.getCardname()) ) )
 && ( (this.getCardid()==castOther.getCardid()) || ( this.getCardid()!=null && castOther.getCardid()!=null && this.getCardid().equals(castOther.getCardid()) ) )
 && ( (this.getPhone()==castOther.getPhone()) || ( this.getPhone()!=null && castOther.getPhone()!=null && this.getPhone().equals(castOther.getPhone()) ) )
 && ( (this.getProvince()==castOther.getProvince()) || ( this.getProvince()!=null && castOther.getProvince()!=null && this.getProvince().equals(castOther.getProvince()) ) )
 && ( (this.getCity()==castOther.getCity()) || ( this.getCity()!=null && castOther.getCity()!=null && this.getCity().equals(castOther.getCity()) ) )
 && ( (this.getArea()==castOther.getArea()) || ( this.getArea()!=null && castOther.getArea()!=null && this.getArea().equals(castOther.getArea()) ) )
 && ( (this.getAddress()==castOther.getAddress()) || ( this.getAddress()!=null && castOther.getAddress()!=null && this.getAddress().equals(castOther.getAddress()) ) )
 && ( (this.getBeforeyearpoints()==castOther.getBeforeyearpoints()) || ( this.getBeforeyearpoints()!=null && castOther.getBeforeyearpoints()!=null && this.getBeforeyearpoints().equals(castOther.getBeforeyearpoints()) ) )
 && ( (this.getHeight()==castOther.getHeight()) || ( this.getHeight()!=null && castOther.getHeight()!=null && this.getHeight().equals(castOther.getHeight()) ) )
 && ( (this.getWeight()==castOther.getWeight()) || ( this.getWeight()!=null && castOther.getWeight()!=null && this.getWeight().equals(castOther.getWeight()) ) )
 && ( (this.getClothessize()==castOther.getClothessize()) || ( this.getClothessize()!=null && castOther.getClothessize()!=null && this.getClothessize().equals(castOther.getClothessize()) ) )
 && ( (this.getAvoidcertainfood()==castOther.getAvoidcertainfood()) || ( this.getAvoidcertainfood()!=null && castOther.getAvoidcertainfood()!=null && this.getAvoidcertainfood().equals(castOther.getAvoidcertainfood()) ) )
 && ( (this.getAcceptanceaddress()==castOther.getAcceptanceaddress()) || ( this.getAcceptanceaddress()!=null && castOther.getAcceptanceaddress()!=null && this.getAcceptanceaddress().equals(castOther.getAcceptanceaddress()) ) )
 && ( (this.getPassStar()==castOther.getPassStar()) || ( this.getPassStar()!=null && castOther.getPassStar()!=null && this.getPassStar().equals(castOther.getPassStar()) ) )
 && ( (this.getPeertype()==castOther.getPeertype()) || ( this.getPeertype()!=null && castOther.getPeertype()!=null && this.getPeertype().equals(castOther.getPeertype()) ) )
 && ( (this.getPeersex()==castOther.getPeersex()) || ( this.getPeersex()!=null && castOther.getPeersex()!=null && this.getPeersex().equals(castOther.getPeersex()) ) )
 && ( (this.getPeercardname()==castOther.getPeercardname()) || ( this.getPeercardname()!=null && castOther.getPeercardname()!=null && this.getPeercardname().equals(castOther.getPeercardname()) ) )
 && ( (this.getPeercardid()==castOther.getPeercardid()) || ( this.getPeercardid()!=null && castOther.getPeercardid()!=null && this.getPeercardid().equals(castOther.getPeercardid()) ) )
 && ( (this.getPeerphone()==castOther.getPeerphone()) || ( this.getPeerphone()!=null && castOther.getPeerphone()!=null && this.getPeerphone().equals(castOther.getPeerphone()) ) )
 && ( (this.getPeerprovince()==castOther.getPeerprovince()) || ( this.getPeerprovince()!=null && castOther.getPeerprovince()!=null && this.getPeerprovince().equals(castOther.getPeerprovince()) ) )
 && ( (this.getPeercity()==castOther.getPeercity()) || ( this.getPeercity()!=null && castOther.getPeercity()!=null && this.getPeercity().equals(castOther.getPeercity()) ) )
 && ( (this.getPeerarea()==castOther.getPeerarea()) || ( this.getPeerarea()!=null && castOther.getPeerarea()!=null && this.getPeerarea().equals(castOther.getPeerarea()) ) )
 && ( (this.getPeeraddress()==castOther.getPeeraddress()) || ( this.getPeeraddress()!=null && castOther.getPeeraddress()!=null && this.getPeeraddress().equals(castOther.getPeeraddress()) ) )
 && ( (this.getPeerheight()==castOther.getPeerheight()) || ( this.getPeerheight()!=null && castOther.getPeerheight()!=null && this.getPeerheight().equals(castOther.getPeerheight()) ) )
 && ( (this.getPeerweight()==castOther.getPeerweight()) || ( this.getPeerweight()!=null && castOther.getPeerweight()!=null && this.getPeerweight().equals(castOther.getPeerweight()) ) )
 && ( (this.getPeerclothessize()==castOther.getPeerclothessize()) || ( this.getPeerclothessize()!=null && castOther.getPeerclothessize()!=null && this.getPeerclothessize().equals(castOther.getPeerclothessize()) ) )
 && ( (this.getPeeravoidcertainfood()==castOther.getPeeravoidcertainfood()) || ( this.getPeeravoidcertainfood()!=null && castOther.getPeeravoidcertainfood()!=null && this.getPeeravoidcertainfood().equals(castOther.getPeeravoidcertainfood()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
         result = 37 * result + ( getCardname() == null ? 0 : this.getCardname().hashCode() );
         result = 37 * result + ( getCardid() == null ? 0 : this.getCardid().hashCode() );
         result = 37 * result + ( getPhone() == null ? 0 : this.getPhone().hashCode() );
         result = 37 * result + ( getProvince() == null ? 0 : this.getProvince().hashCode() );
         result = 37 * result + ( getCity() == null ? 0 : this.getCity().hashCode() );
         result = 37 * result + ( getArea() == null ? 0 : this.getArea().hashCode() );
         result = 37 * result + ( getAddress() == null ? 0 : this.getAddress().hashCode() );
         result = 37 * result + ( getBeforeyearpoints() == null ? 0 : this.getBeforeyearpoints().hashCode() );
         result = 37 * result + ( getHeight() == null ? 0 : this.getHeight().hashCode() );
         result = 37 * result + ( getWeight() == null ? 0 : this.getWeight().hashCode() );
         result = 37 * result + ( getClothessize() == null ? 0 : this.getClothessize().hashCode() );
         result = 37 * result + ( getAvoidcertainfood() == null ? 0 : this.getAvoidcertainfood().hashCode() );
         result = 37 * result + ( getAcceptanceaddress() == null ? 0 : this.getAcceptanceaddress().hashCode() );
         result = 37 * result + ( getPassStar() == null ? 0 : this.getPassStar().hashCode() );
         result = 37 * result + ( getPeertype() == null ? 0 : this.getPeertype().hashCode() );
         result = 37 * result + ( getPeersex() == null ? 0 : this.getPeersex().hashCode() );
         result = 37 * result + ( getPeercardname() == null ? 0 : this.getPeercardname().hashCode() );
         result = 37 * result + ( getPeercardid() == null ? 0 : this.getPeercardid().hashCode() );
         result = 37 * result + ( getPeerphone() == null ? 0 : this.getPeerphone().hashCode() );
         result = 37 * result + ( getPeerprovince() == null ? 0 : this.getPeerprovince().hashCode() );
         result = 37 * result + ( getPeercity() == null ? 0 : this.getPeercity().hashCode() );
         result = 37 * result + ( getPeerarea() == null ? 0 : this.getPeerarea().hashCode() );
         result = 37 * result + ( getPeeraddress() == null ? 0 : this.getPeeraddress().hashCode() );
         result = 37 * result + ( getPeerheight() == null ? 0 : this.getPeerheight().hashCode() );
         result = 37 * result + ( getPeerweight() == null ? 0 : this.getPeerweight().hashCode() );
         result = 37 * result + ( getPeerclothessize() == null ? 0 : this.getPeerclothessize().hashCode() );
         result = 37 * result + ( getPeeravoidcertainfood() == null ? 0 : this.getPeeravoidcertainfood().hashCode() );
         return result;
   }   





}
