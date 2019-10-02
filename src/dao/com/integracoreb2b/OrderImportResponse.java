
package com.integracoreb2b;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderImportResult" type="{http://www.integracoreb2b.com/}OrderResults" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "orderImportResult"
})
@XmlRootElement(name = "OrderImportResponse")
public class OrderImportResponse {

    @XmlElement(name = "OrderImportResult")
    protected OrderResults orderImportResult;

    /**
     * Gets the value of the orderImportResult property.
     * 
     * @return
     *     possible object is
     *     {@link OrderResults }
     *     
     */
    public OrderResults getOrderImportResult() {
        return orderImportResult;
    }

    /**
     * Sets the value of the orderImportResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderResults }
     *     
     */
    public void setOrderImportResult(OrderResults value) {
        this.orderImportResult = value;
    }

}
