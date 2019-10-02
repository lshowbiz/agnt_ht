
package com.integracoreb2b;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderNote complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderNote">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NoteType" type="{http://microsoft.com/wsdl/types/}char"/>
 *         &lt;element name="NoteText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderNote", propOrder = {
    "noteType",
    "noteText"
})
public class OrderNote {

    @XmlElement(name = "NoteType")
    protected int noteType;
    @XmlElement(name = "NoteText")
    protected String noteText;

    /**
     * Gets the value of the noteType property.
     * 
     */
    public int getNoteType() {
        return noteType;
    }

    /**
     * Sets the value of the noteType property.
     * 
     */
    public void setNoteType(int value) {
        this.noteType = value;
    }

    /**
     * Gets the value of the noteText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoteText() {
        return noteText;
    }

    /**
     * Sets the value of the noteText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoteText(String value) {
        this.noteText = value;
    }

}
