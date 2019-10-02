
package com.integracoreb2b;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderMessage" type="{http://www.integracoreb2b.com/}ArrayOfOrderResult" minOccurs="0"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderResults", propOrder = {
    "orderMessage",
    "error"
})
public class OrderResults {

    @XmlElement(name = "OrderMessage")
    protected ArrayOfOrderResult orderMessage;
    protected String error;

    /**
     * Gets the value of the orderMessage property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOrderResult }
     *     
     */
    public ArrayOfOrderResult getOrderMessage() {
        return orderMessage;
    }

    /**
     * Sets the value of the orderMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOrderResult }
     *     
     */
    public void setOrderMessage(ArrayOfOrderResult value) {
        this.orderMessage = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setError(String value) {
        this.error = value;
    }

}
