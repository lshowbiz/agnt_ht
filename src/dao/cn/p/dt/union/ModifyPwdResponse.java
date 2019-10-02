
package cn.p.dt.union;

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
 *         &lt;element name="ModifyPwdResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="msg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "modifyPwdResult",
    "msg"
})
@XmlRootElement(name = "ModifyPwdResponse")
public class ModifyPwdResponse {

    @XmlElement(name = "ModifyPwdResult")
    protected boolean modifyPwdResult;
    protected String msg;

    /**
     * Gets the value of the modifyPwdResult property.
     * 
     */
    public boolean isModifyPwdResult() {
        return modifyPwdResult;
    }

    /**
     * Sets the value of the modifyPwdResult property.
     * 
     */
    public void setModifyPwdResult(boolean value) {
        this.modifyPwdResult = value;
    }

    /**
     * Gets the value of the msg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets the value of the msg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsg(String value) {
        this.msg = value;
    }

}