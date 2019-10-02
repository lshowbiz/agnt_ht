
package cn.p.dt.union;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="authStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regData" type="{http://dt.p.cn/union/}DZhongmai" minOccurs="0"/>
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
    "authStr",
    "regData",
    "msg"
})
@XmlRootElement(name = "NewAccount")
public class NewAccount {

    protected String authStr;
    protected DZhongmai regData;
    protected String msg;

    /**
     * Gets the value of the authStr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthStr() {
        return authStr;
    }

    /**
     * Sets the value of the authStr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthStr(String value) {
        this.authStr = value;
    }

    /**
     * Gets the value of the regData property.
     * 
     * @return
     *     possible object is
     *     {@link DZhongmai }
     *     
     */
    public DZhongmai getRegData() {
        return regData;
    }

    /**
     * Sets the value of the regData property.
     * 
     * @param value
     *     allowed object is
     *     {@link DZhongmai }
     *     
     */
    public void setRegData(DZhongmai value) {
        this.regData = value;
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
