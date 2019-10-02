<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdBounsDeductDetail.title"/></title>
<content tag="heading"><jecs:locale key="bdBounsDeductDetail.heading"/></content>

<c:if test="${isFinished!=true}">
<form:form commandName="bdPeriod" method="post" action="jmiChangeCardTypeImport.html" onsubmit="return validateOthers(this)" id="bdBounsDeductForm" enctype="multipart/form-data">

<spring:bind path="bdPeriod.*">
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

<table class='detail' width="100%">

<form:hidden path="wid"/>

	<tr><th>
        <jecs:label  key="fiPayData.importFile"/>
    </th>
    <td align="left">
   		<input name="importExcelFile" type="file" id="importExcelFile" size="50" /></td></tr>
    
    <tr><th>
        <jecs:label  key="busi.common.remark"/>
    </th>
    <td align="left">
        <jecs:locale key="jmiChangeCardTypeImport.remark"/>
    </td></tr>
    
    <tr>
		<th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
		<td class="command">
			<jecs:power powerCode="importJmiChangeCardType">
			<input type="submit" class="button" name="import"  onclick="bCancel=false" value="<jecs:locale key="button.import"/>" />
			</jecs:power>
			<input type="hidden" name="strAction" value="importJmiChangeCardType"/>
		</td>
	</tr>
</table>

</form:form>
</c:if>

<c:if test="${isFinished==true}">
	<div id="titleBar">
		<input type="button" class="button" name="cancel"  onclick="history.back()" value="<jecs:locale key="operation.button.return"/>" />
	</div>
	<table>
		<c:forEach items="${messages}" var="messageVar">
			<tr><td>&gt;&gt;${messageVar}</td></tr>
		</c:forEach>
	</table>
</c:if>

<script type="text/javascript">
function validateOthers(theForm){
	if(theForm.importExcelFile.value==""){
		alert("<jecs:locale key="bdBounsDeduct.importExcelFile.required"/>");
		theForm.importExcelFile.focus();
		return false;
	}
	return true;

}
</script>