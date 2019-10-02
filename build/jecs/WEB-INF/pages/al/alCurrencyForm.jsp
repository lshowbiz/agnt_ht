<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCurrencyDetail.title"/></title>
<content tag="heading"><jecs:locale key="alCurrencyDetail.heading"/></content>

<spring:bind path="alCurrency.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="alCurrency" method="post" action="editAlCurrency.html" onsubmit="return validateAlCurrency(this)" id="alCurrencyForm">
<ul>

<form:hidden path="currencyId"/>

    <li>
        <jecs:label styleClass="desc" key="alCurrency.currencyCode"/>
        <form:errors path="currencyCode" cssClass="fieldError"/>
        <form:input path="currencyCode" id="currencyCode" cssClass="text medium"/>
    </li>

    <li>
        <jecs:label styleClass="desc" key="alCurrency.currencyName"/>
        <form:errors path="currencyName" cssClass="fieldError"/>
        <form:input path="currencyName" id="currencyName" cssClass="text medium"/>
    </li>

    <li>
        <jecs:label styleClass="desc" key="alCurrency.exchangeRate"/>
        <form:errors path="exchangeRate" cssClass="fieldError"/>
        <form:input path="exchangeRate" id="exchangeRate" cssClass="text medium"/>
    </li>

    <li>
        <jecs:label styleClass="desc" key="alCurrency.currencySymbol"/>
        <form:errors path="currencySymbol" cssClass="fieldError"/>
        <form:input path="currencySymbol" id="currencySymbol" cssClass="text medium"/>
    </li>

    <li>
        <jecs:label styleClass="desc" key="alCurrency.currencyStyle"/>
        <form:errors path="currencyStyle" cssClass="fieldError"/>
        <form:input path="currencyStyle" id="currencyStyle" cssClass="text medium"/>
    </li>

    <li>
        <jecs:label styleClass="desc" key="alCurrency.currencyKey"/>
        <form:errors path="currencyKey" cssClass="fieldError"/>
        <form:input path="currencyKey" id="currencyKey" cssClass="text medium"/>
    </li>

    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AlCurrency')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />
    </li>
</ul>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('alCurrencyForm'));
</script>

<v:javascript formName="alCurrency" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
