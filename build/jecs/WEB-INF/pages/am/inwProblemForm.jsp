<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwProblemDetail.title"/></title>
<content tag="heading"><jecs:locale key="inwProblemDetail.heading"/></content>

<c:set var="buttons">
	<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
	<input type="button" class="button" name="cancel" onclick="location.href='<c:url value="/inwProblems.html"/>?strAction=queryInwProblem'" value="<jecs:locale key="operation.button.return"/>" />
</c:set>

<spring:bind path="inwProblem.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="/images/iconWarning.gif"/>"
                alt="<jecs:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>
<form:form commandName="inwProblem" method="post" action="editInwProblem.html" onsubmit="return validateInwProblem(this)" id="inwProblemForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="addInwProblem"/>
<table class='detail' width="100%">
    <form:hidden path="id"/>
    <tr><td align="right">
        <jecs:label  key="inwProblem.species" required="true"/>
    </td>
    <td align="left">
         <jecs:list name="species" listCode="inwproblem.type" value="${inwProblem.species}" defaultValue="" showBlankLine=""/>	
    </td></tr>
    <tr><td align="right">
        <jecs:label  key="inwProblem.problem" required="true"/>
    </td>
    <td align="left">
        <form:input path="ask" id="ask" cssClass="text medium"/>
    </td></tr>
    <tr><td align="right">
        <jecs:label  key="miMember.securityQuestionAnswer" required="true"/>
    </td>
    <td align="left">
        <form:textarea path="answer" id="answer" cssClass="text medium" cssErrorClass="text medium error" rows="10" cols="60"/>
    </td></tr>
</table>
</form:form>
<v:javascript formName="inwProblem" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
