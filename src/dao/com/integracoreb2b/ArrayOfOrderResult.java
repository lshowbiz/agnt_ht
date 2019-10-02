
package com.integracoreb2b;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfOrderResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfOrderResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderResult" type="{http://www.integracoreb2b.com/}OrderResult" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfOrderResult", propOrder = {
    "orderResult"
})
public class ArrayOfOrderResult {

    @XmlElement(name = "OrderResult", nillable = true)
    protected List<OrderResult> orderResult;

    /**
     * Gets the value of the orderResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderResult }
     * 
     * 
     */
    public List<OrderResult> getOrderResult() {
        if (orderResult == null) {
            orderResult = new ArrayList<OrderResult>();
        }
        return this.orderResult;
    }

}
