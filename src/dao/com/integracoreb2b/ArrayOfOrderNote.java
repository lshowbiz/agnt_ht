
package com.integracoreb2b;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfOrderNote complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfOrderNote">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderNote" type="{http://www.integracoreb2b.com/}OrderNote" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfOrderNote", propOrder = {
    "orderNote"
})
public class ArrayOfOrderNote {

    @XmlElement(name = "OrderNote", nillable = true)
    protected List<OrderNote> orderNote;

    /**
     * Gets the value of the orderNote property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderNote property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderNote().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderNote }
     * 
     * 
     */
    public List<OrderNote> getOrderNote() {
        if (orderNote == null) {
            orderNote = new ArrayList<OrderNote>();
        }
        return this.orderNote;
    }

}
