<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alRegionDetail.title"/></title>
<content tag="heading"><jecs:locale key="alRegionDetail.heading"/></content>

<spring:bind path="alRegion.*">
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

<form:form commandName="alRegion" method="post" action="editAlRegion.html" onsubmit="return validateAlRegion(this)" id="alRegionForm">
<ul>

<form:hidden path="regionId"/>

    <li>
        <jecs:label styleClass="desc" key="alRegion.companyCode"/>
        <form:errors path="companyCode" cssClass="fieldError"/>
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </li>

    <li>
        <jecs:label styleClass="desc" key="alRegion.regionCode"/>
        <form:errors path="regionCode" cssClass="fieldError"/>
        <form:input path="regionCode" id="regionCode" cssClass="text medium"/>
    </li>

    <li>
        <jecs:label styleClass="desc" key="alRegion.regionName"/>
        <form:errors path="regionName" cssClass="fieldError"/>
        <form:input path="regionName" id="regionName" cssClass="text medium"/>
    </li>

    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AlRegion')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />
    </li>
</ul>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('alRegionForm'));
</script>

<v:javascript formName="alRegion" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
