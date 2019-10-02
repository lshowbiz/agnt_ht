
package com.integracoreb2b;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Order complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Order">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SiteID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipToCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BillToCustID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="RequiredShipDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Carrier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CarrierServiceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ThirdPartyAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrepaidOrCollect" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipToContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipToName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipToAddr1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipToAddr2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipToAddr3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipToCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipToState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipToZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipToCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipToTelephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShipToFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustPoNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BillToPoNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecipientEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomData1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomData2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomData3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DropShip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResidentialFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PackSlipRequired" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderDetails" type="{http://www.integracoreb2b.com/}ArrayOfOrderDetail" minOccurs="0"/>
 *         &lt;element name="OrderNotes" type="{http://www.integracoreb2b.com/}ArrayOfOrderNote" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Order", propOrder = {
    "siteID",
    "orderNumber",
    "shipToCustID",
    "billToCustID",
    "createDate",
    "requiredShipDate",
    "carrier",
    "carrierServiceCode",
    "thirdPartyAccountNumber",
    "prepaidOrCollect",
    "shipToContact",
    "shipToName",
    "shipToAddr1",
    "shipToAddr2",
    "shipToAddr3",
    "shipToCity",
    "shipToState",
    "shipToZip",
    "shipToCountry",
    "shipToTelephone",
    "shipToFax",
    "custPoNum",
    "billToPoNum",
    "recipientEmailAddress",
    "customData1",
    "customData2",
    "customData3",
    "dropShip",
    "residentialFlag",
    "packSlipRequired",
    "orderDetails",
    "orderNotes"
})
public class Order {

    @XmlElement(name = "SiteID")
    protected String siteID;
    @XmlElement(name = "OrderNumber")
    protected String orderNumber;
    @XmlElement(name = "ShipToCustID")
    protected String shipToCustID;
    @XmlElement(name = "BillToCustID")
    protected String billToCustID;
    @XmlElement(name = "CreateDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    @XmlElement(name = "RequiredShipDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requiredShipDate;
    @XmlElement(name = "Carrier")
    protected String carrier;
    @XmlElement(name = "CarrierServiceCode")
    protected String carrierServiceCode;
    @XmlElement(name = "ThirdPartyAccountNumber")
    protected String thirdPartyAccountNumber;
    @XmlElement(name = "PrepaidOrCollect")
    protected String prepaidOrCollect;
    @XmlElement(name = "ShipToContact")
    protected String shipToContact;
    @XmlElement(name = "ShipToName")
    protected String shipToName;
    @XmlElement(name = "ShipToAddr1")
    protected String shipToAddr1;
    @XmlElement(name = "ShipToAddr2")
    protected String shipToAddr2;
    @XmlElement(name = "ShipToAddr3")
    protected String shipToAddr3;
    @XmlElement(name = "ShipToCity")
    protected String shipToCity;
    @XmlElement(name = "ShipToState")
    protected String shipToState;
    @XmlElement(name = "ShipToZip")
    protected String shipToZip;
    @XmlElement(name = "ShipToCountry")
    protected String shipToCountry;
    @XmlElement(name = "ShipToTelephone")
    protected String shipToTelephone;
    @XmlElement(name = "ShipToFax")
    protected String shipToFax;
    @XmlElement(name = "CustPoNum")
    protected String custPoNum;
    @XmlElement(name = "BillToPoNum")
    protected String billToPoNum;
    @XmlElement(name = "RecipientEmailAddress")
    protected String recipientEmailAddress;
    @XmlElement(name = "CustomData1")
    protected String customData1;
    @XmlElement(name = "CustomData2")
    protected String customData2;
    @XmlElement(name = "CustomData3")
    protected String customData3;
    @XmlElement(name = "DropShip")
    protected String dropShip;
    @XmlElement(name = "ResidentialFlag")
    protected String residentialFlag;
    @XmlElement(name = "PackSlipRequired")
    protected String packSlipRequired;
    @XmlElement(name = "OrderDetails")
    protected ArrayOfOrderDetail orderDetails;
    @XmlElement(name = "OrderNotes")
    protected ArrayOfOrderNote orderNotes;

    /**
     * Gets the value of the siteID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteID() {
        return siteID;
    }

    /**
     * Sets the value of the siteID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteID(String value) {
        this.siteID = value;
    }

    /**
     * Gets the value of the orderNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Sets the value of the orderNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderNumber(String value) {
        this.orderNumber = value;
    }

    /**
     * Gets the value of the shipToCustID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipToCustID() {
        return shipToCustID;
    }

    /**
     * Sets the value of the shipToCustID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipToCustID(String value) {
        this.shipToCustID = value;
    }

    /**
     * Gets the value of the billToCustID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillToCustID() {
        return billToCustID;
    }

    /**
     * Sets the value of the billToCustID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillToCustID(String value) {
        this.billToCustID = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the requiredShipDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequiredShipDate() {
        return requiredShipDate;
    }

    /**
     * Sets the value of the requiredShipDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequiredShipDate(XMLGregorianCalendar value) {
        this.requiredShipDate = value;
    }

    /**
     * Gets the value of the carrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * Sets the value of the carrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrier(String value) {
        this.carrier = value;
    }

    /**
     * Gets the value of the carrierServiceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierServiceCode() {
        return carrierServiceCode;
    }

    /**
     * Sets the value of the carrierServiceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierServiceCode(String value) {
        this.carrierServiceCode = value;
    }

    /**
     * Gets the value of the thirdPartyAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThirdPartyAccountNumber() {
        return thirdPartyAccountNumber;
    }

    /**
     * Sets the value of the thirdPartyAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThirdPartyAccountNumber(String value) {
        this.thirdPartyAccountNumber = value;
    }

    /**
     * Gets the value of the prepaidOrCollect property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrepaidOrCollect() {
        return prepaidOrCollect;
    }

    /**
     * Sets the value of the prepaidOrCollect property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrepaidOrCollect(String value) {
        this.prepaidOrCollect = value;
    }

    /**
     * Gets the value of the shipToContact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipToContact() {
        return shipToContact;
    }

    /**
     * Sets the value of the shipToContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipToContact(String value) {
        this.shipToContact = value;
    }

    /**
     * Gets the value of the shipToName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipToName() {
        return shipToName;
    }

    /**
     * Sets the value of the shipToName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipToName(String value) {
        this.shipToName = value;
    }

    /**
     * Gets the value of the shipToAddr1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipToAddr1() {
        return shipToAddr1;
    }

    /**
     * Sets the value of the shipToAddr1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipToAddr1(String value) {
        this.shipToAddr1 = value;
    }

    /**
     * Gets the value of the shipToAddr2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipToAddr2() {
        return shipToAddr2;
    }

    /**
     * Sets the value of the shipToAddr2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipToAddr2(String value) {
        this.shipToAddr2 = value;
    }

    /**
     * Gets the value of the shipToAddr3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipToAddr3() {
        return shipToAddr3;
    }

    /**
     * Sets the value of the shipToAddr3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipToAddr3(String value) {
        this.shipToAddr3 = value;
    }

    /**
     * Gets the value of the shipToCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipToCity() {
        return shipToCity;
    }

    /**
     * Sets the value of the shipToCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipToCity(String value) {
        this.shipToCity = value;
    }

    /**
     * Gets the value of the shipToState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipToState() {
        return shipToState;
    }

    /**
     * Sets the value of the shipToState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipToState(String value) {
        this.shipToState = value;
    }

    /**
     * Gets the value of the shipToZip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipToZip() {
        return shipToZip;
    }

    /**
     * Sets the value of the shipToZip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipToZip(String value) {
        this.shipToZip = value;
    }

    /**
     * Gets the value of the shipToCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipToCountry() {
        return shipToCountry;
    }

    /**
     * Sets the value of the shipToCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipToCountry(String value) {
        this.shipToCountry = value;
    }

    /**
     * Gets the value of the shipToTelephone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipToTelephone() {
        return shipToTelephone;
    }

    /**
     * Sets the value of the shipToTelephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipToTelephone(String value) {
        this.shipToTelephone = value;
    }

    /**
     * Gets the value of the shipToFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipToFax() {
        return shipToFax;
    }

    /**
     * Sets the value of the shipToFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipToFax(String value) {
        this.shipToFax = value;
    }

    /**
     * Gets the value of the custPoNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustPoNum() {
        return custPoNum;
    }

    /**
     * Sets the value of the custPoNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustPoNum(String value) {
        this.custPoNum = value;
    }

    /**
     * Gets the value of the billToPoNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillToPoNum() {
        return billToPoNum;
    }

    /**
     * Sets the value of the billToPoNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillToPoNum(String value) {
        this.billToPoNum = value;
    }

    /**
     * Gets the value of the recipientEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipientEmailAddress() {
        return recipientEmailAddress;
    }

    /**
     * Sets the value of the recipientEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipientEmailAddress(String value) {
        this.recipientEmailAddress = value;
    }

    /**
     * Gets the value of the customData1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomData1() {
        return customData1;
    }

    /**
     * Sets the value of the customData1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomData1(String value) {
        this.customData1 = value;
    }

    /**
     * Gets the value of the customData2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomData2() {
        return customData2;
    }

    /**
     * Sets the value of the customData2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomData2(String value) {
        this.customData2 = value;
    }

    /**
     * Gets the value of the customData3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomData3() {
        return customData3;
    }

    /**
     * Sets the value of the customData3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomData3(String value) {
        this.customData3 = value;
    }

    /**
     * Gets the value of the dropShip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDropShip() {
        return dropShip;
    }

    /**
     * Sets the value of the dropShip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDropShip(String value) {
        this.dropShip = value;
    }

    /**
     * Gets the value of the residentialFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResidentialFlag() {
        return residentialFlag;
    }

    /**
     * Sets the value of the residentialFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResidentialFlag(String value) {
        this.residentialFlag = value;
    }

    /**
     * Gets the value of the packSlipRequired property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackSlipRequired() {
        return packSlipRequired;
    }

    /**
     * Sets the value of the packSlipRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackSlipRequired(String value) {
        this.packSlipRequired = value;
    }

    /**
     * Gets the value of the orderDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOrderDetail }
     *     
     */
    public ArrayOfOrderDetail getOrderDetails() {
        return orderDetails;
    }

    /**
     * Sets the value of the orderDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOrderDetail }
     *     
     */
    public void setOrderDetails(ArrayOfOrderDetail value) {
        this.orderDetails = value;
    }

    /**
     * Gets the value of the orderNotes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOrderNote }
     *     
     */
    public ArrayOfOrderNote getOrderNotes() {
        return orderNotes;
    }

    /**
     * Sets the value of the orderNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOrderNote }
     *     
     */
    public void setOrderNotes(ArrayOfOrderNote value) {
        this.orderNotes = value;
    }

}
