<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="inwSuggestionDetail.title"/></title>
<content tag="heading"><jecs:locale key="inwSuggestionDetail.heading"/></content>
<input type="button" class="button" name="cancel"  onclick="returnOld()" value="<jecs:locale key="operation.button.return"/>" />
<spring:bind path="inwSuggestion.*">
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
<form:form  commandName="inwSuggestion" method="post" action="editInwSuggestion.html" onsubmit="return validateInwSuggestion(this)" id="inwSuggestionForm" name="inwSuggestionForm">

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>
<table class='detail' width="100%">


    <tr><th>
        <jecs:label  key="inwSuggestion.suggestedTopics"/>
    </th>
    <td align="left" width="80%" colspan="3">
        ${inwSuggestion.subject }
    </td></tr>
    <tr><th>
        <jecs:label  key="inwSuggestion.proposalContent"/>
    </th>
    <td align="left" colspan="3">
       <form:textarea path="content" id="content" cssClass="text medium"  cssStyle="width:80%;height:45px;margin-top:10px;resize:none;" readonly="true"/>
    </td></tr>
    <tr><th>
        <jecs:label  key="inwSuggestion.createTime"/>
    </th>
    <td align="left" colspan="3">
        ${inwSuggestion.createTime }
    </td></tr>
    <tr><th>
        <jecs:label  key="inwSuggestion.userCode"/>
    </th>
    <td align="left" colspan="3">
         ${inwSuggestion.userCode }
    </td></tr>
     <tr>
       <td nowrap="nowrap"><form:input path="demandsortId" id="demandsortId" cssClass="text medium" maxlength="50" size="15"  cssStyle="display:none"/></td>
       <td colspan="3" ><form:input path="demandId" id="demandId" cssClass="text medium" maxlength="50" size="15"  cssStyle="display:none"/></td>
                
    </tr>
    

</table>
</form:form>

<script type="text/javascript">
       
      function returnOld(){
              window.location.href="inwSuggestions.html?strAction=phoneSuggestion";
      }
      
      
</script>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
