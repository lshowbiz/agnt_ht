
package com.integracoreb2b;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.integracoreb2b package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AuthHeader_QNAME = new QName("http://www.integracoreb2b.com/", "AuthHeader");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.integracoreb2b
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayOfOrderResult }
     * 
     */
    public ArrayOfOrderResult createArrayOfOrderResult() {
        return new ArrayOfOrderResult();
    }

    /**
     * Create an instance of {@link OrderImport }
     * 
     */
    public OrderImport createOrderImport() {
        return new OrderImport();
    }

    /**
     * Create an instance of {@link OrderNote }
     * 
     */
    public OrderNote createOrderNote() {
        return new OrderNote();
    }

    /**
     * Create an instance of {@link ArrayOfOrder }
     * 
     */
    public ArrayOfOrder createArrayOfOrder() {
        return new ArrayOfOrder();
    }

    /**
     * Create an instance of {@link ArrayOfOrderNote }
     * 
     */
    public ArrayOfOrderNote createArrayOfOrderNote() {
        return new ArrayOfOrderNote();
    }

    /**
     * Create an instance of {@link ArrayOfOrderDetail }
     * 
     */
    public ArrayOfOrderDetail createArrayOfOrderDetail() {
        return new ArrayOfOrderDetail();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link OrderImportResponse }
     * 
     */
    public OrderImportResponse createOrderImportResponse() {
        return new OrderImportResponse();
    }

    /**
     * Create an instance of {@link OrderResult }
     * 
     */
    public OrderResult createOrderResult() {
        return new OrderResult();
    }

    /**
     * Create an instance of {@link OrderResults }
     * 
     */
    public OrderResults createOrderResults() {
        return new OrderResults();
    }

    /**
     * Create an instance of {@link AuthHeader }
     * 
     */
    public AuthHeader createAuthHeader() {
        return new AuthHeader();
    }

    /**
     * Create an instance of {@link OrderDetail }
     * 
     */
    public OrderDetail createOrderDetail() {
        return new OrderDetail();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.integracoreb2b.com/", name = "AuthHeader")
    public JAXBElement<AuthHeader> createAuthHeader(AuthHeader value) {
        return new JAXBElement<AuthHeader>(_AuthHeader_QNAME, AuthHeader.class, null, value);
    }

}
