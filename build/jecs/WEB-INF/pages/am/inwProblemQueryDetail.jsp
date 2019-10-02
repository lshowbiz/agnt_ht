<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwProblemDetail.title"/></title>
<content tag="heading"><jecs:locale key="inwProblemDetail.heading"/></content>

<c:set var="buttons">
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
<form  name="inwProblemDetailQuery" action="">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<table class='detail' width="100%">
    <tr><td align="right">
        <jecs:label  key="inwProblem.species" required="true"/>
    </td>
    <td align="left">
         <c:if test="${inwProblem.species=='1'}">
               <jecs:code listCode="inwproblem.type" value="1"/>
         </c:if>
         <c:if test="${inwProblem.species=='2'}">
               <jecs:code listCode="inwproblem.type" value="2"/>
         </c:if>
         <c:if test="${inwProblem.species=='3'}">
               <jecs:code listCode="inwproblem.type" value="3"/>
         </c:if>
         <c:if test="${inwProblem.species=='4'}">
               <jecs:code listCode="inwproblem.type" value="4"/>
         </c:if>
    </td></tr>
    <tr><td align="right">
        <jecs:label  key="inwProblem.problem" required="true"/>
    </td>
    <td align="left">
       ${inwProblem.ask }
    </td></tr>
    <tr><td align="right">
        <jecs:label  key="miMember.securityQuestionAnswer" required="true"/>
    </td>
    <td align="left">
        ${inwProblem.answer }
    </td></tr>
</table>
</form>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>