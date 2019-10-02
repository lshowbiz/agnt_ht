<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alBranchDetail.title"/></title>
<content tag="heading"><jecs:locale key="alBranchDetail.heading"/></content>

<spring:bind path="alBranch.*">
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

<form:form commandName="alBranch" method="post" action="editAlBranch.html" onsubmit="return validateAlBranch(this)" id="alBranchForm">
<ul>

<form:hidden path="branchId"/>

    <li>
        <jecs:label styleClass="desc" key="alBranch.companyCode"/>
        <form:errors path="companyCode" cssClass="fieldError"/>
        <form:input path="companyCode" id="companyCode" cssClass="text medium"/>
    </li>

    <li>
        <jecs:label styleClass="desc" key="alBranch.branchCode"/>
        <form:errors path="branchCode" cssClass="fieldError"/>
        <form:input path="branchCode" id="branchCode" cssClass="text medium"/>
    </li>

    <li>
        <jecs:label styleClass="desc" key="alBranch.branchName"/>
        <form:errors path="branchName" cssClass="fieldError"/>
        <form:input path="branchName" id="branchName" cssClass="text medium"/>
    </li>

    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AlBranch')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />
    </li>
</ul>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('alBranchForm'));
</script>

<v:javascript formName="alBranch" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
