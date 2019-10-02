package com.joymain.jecs.pm.model;

// Generated 2013-12-2 17:30:44 by Hibernate Tools 3.1.0.beta4

import java.math.BigDecimal;

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *                  table="JPM_WINE_ORDER_LIST_INTERFACE"
 * 
 */

public class JpmWineOrderListInterface extends com.joymain.jecs.model.BaseObject implements java.io.Serializable {

    // Fields    
    private Long idf;//主键（自动生成）

    private String productId;///商品编码 √

    private Integer qty;//发货数量  √

    private Double price;//商品单价 √

    private String creceiptcorpid;//发货单号   √

    private String vreceiveaddress;//收货地址

    private String dconsigndate;//发货日期 √

    private Double invpv = 0d;//PV

    private JpmWineOrderInterface jpmWineOrderInterface = new JpmWineOrderInterface();

    // Constructors

    /** default constructor */
    public JpmWineOrderListInterface() {
    }

    public JpmWineOrderListInterface(Long idf, String productId, Integer qty, Double price, String creceiptcorpid, String vreceiveaddress, String dconsigndate, Double invpv, JpmWineOrderInterface jpmWineOrderInterface) {
        super();
        this.idf = idf;
        this.productId = productId;
        this.qty = qty;
        this.price = price;
        this.creceiptcorpid = creceiptcorpid;
        this.vreceiveaddress = vreceiveaddress;
        this.dconsigndate = dconsigndate;
        this.invpv = invpv;
        this.jpmWineOrderInterface = jpmWineOrderInterface;
    }

    // Property accessors
    /**
     * * @hibernate.id
     * generator-class="native"
     * type="java.lang.Long"
     * column="IDF"
     * 
     */

    public Long getIdf() {
        return this.idf;
    }

    public void setIdf(Long idf) {
        this.idf = idf;
    }

    /**
     * * @hibernate.property
     * column="PRODUCT_ID"
     * length="50"
     * 
     */

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * * @hibernate.property
     * column="QTY"
     * length="5"
     * 
     */

    public Integer getQty() {
        return this.qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    /**
     * * @hibernate.property
     * column="PRICE"
     * 
     */

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * * @hibernate.property
     * column="CRECEIPTCORPID"
     * length="50"
     * 
     */
    public String getCreceiptcorpid() {
        return creceiptcorpid;
    }

    public void setCreceiptcorpid(String creceiptcorpid) {
        this.creceiptcorpid = creceiptcorpid;
    }

    /**
     * * @hibernate.property
     * column="VRECEIVEADDRESS"
     * length="500"
     * 
     */
    public String getVreceiveaddress() {
        return vreceiveaddress;
    }

    public void setVreceiveaddress(String vreceiveaddress) {
        this.vreceiveaddress = vreceiveaddress;
    }

    /**
     * * @hibernate.property
     * column="DCONSIGNDATE"
     * length="500"
     * 
     */
    public String getDconsigndate() {
        return dconsigndate;
    }

    public void setDconsigndate(String dconsigndate) {
        this.dconsigndate = dconsigndate;
    }

    /**
     * * @hibernate.property
     * column="INV_PV"
     * 
     */
    public Double getInvpv() {
        return invpv;
    }

    public void setInvpv(Double invpv) {
        this.invpv = invpv;
    }

    /**
     * *
     * 
     * @hibernate.many-to-one not-null="true"
     * @hibernate.column name="MO_ID"
     * 
     */
    public JpmWineOrderInterface getJpmWineOrderInterface() {
        return jpmWineOrderInterface;
    }

    public void setJpmWineOrderInterface(JpmWineOrderInterface jpmWineOrderInterface) {
        this.jpmWineOrderInterface = jpmWineOrderInterface;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creceiptcorpid == null) ? 0 : creceiptcorpid.hashCode());
        result = prime * result + ((dconsigndate == null) ? 0 : dconsigndate.hashCode());
        result = prime * result + ((idf == null) ? 0 : idf.hashCode());
        result = prime * result + ((invpv == null) ? 0 : invpv.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime * result + ((vreceiveaddress == null) ? 0 : vreceiveaddress.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JpmWineOrderListInterface other = (JpmWineOrderListInterface) obj;
        if (creceiptcorpid == null) {
            if (other.creceiptcorpid != null)
                return false;
        } else if (!creceiptcorpid.equals(other.creceiptcorpid))
            return false;
        if (dconsigndate == null) {
            if (other.dconsigndate != null)
                return false;
        } else if (!dconsigndate.equals(other.dconsigndate))
            return false;
        if (idf == null) {
            if (other.idf != null)
                return false;
        } else if (!idf.equals(other.idf))
            return false;
        if (invpv == null) {
            if (other.invpv != null)
                return false;
        } else if (!invpv.equals(other.invpv))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        if (qty == null) {
            if (other.qty != null)
                return false;
        } else if (!qty.equals(other.qty))
            return false;
        if (vreceiveaddress == null) {
            if (other.vreceiveaddress != null)
                return false;
        } else if (!vreceiveaddress.equals(other.vreceiveaddress))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "JpmWineOrderListInterface [idf=" + idf + ", productId=" + productId + ", qty=" + qty + ", price=" + price + ", creceiptcorpid=" + creceiptcorpid + ", vreceiveaddress=" + vreceiveaddress + ", dconsigndate=" + dconsigndate + ", invpv=" + invpv + "]";
    }

}
