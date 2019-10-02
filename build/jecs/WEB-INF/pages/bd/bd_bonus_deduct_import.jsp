<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdBounsDeductDetail.title"/></title>
<content tag="heading"><jecs:locale key="bdBounsDeductDetail.heading"/></content>

<c:if test="${isFinished!=true}">
<form:form commandName="bdBounsDeduct" method="post" action="" onsubmit="return validateOthers(this)" id="bdBounsDeductForm" enctype="multipart/form-data">

<spring:bind path="bdBounsDeduct.*">
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

<table class='detail' width="70%">
<tbody class="window">
<form:hidden path="id"/>
	<tr class="edit_tr"><th width="30%">
        <jecs:label key="bdBounsDeduct.treatedNo"/>
    </th>
    <td>
		<form:input path="treatedNo"/>
    </td></tr>

	<tr class="edit_tr"><th>
        <jecs:label  key="fiPayData.importFile"/>
    </th>
    <td>
   		<input name="importExcelFile" type="file" id="importExcelFile" size="50" /></td></tr>
    
    <tr class="edit_tr"><th>
        <jecs:label  key="busi.common.remark"/>
    </th>
    <td>
        <jecs:locale key="bdBounsDeduct.remark"/>
    </td></tr>
    
    <tr class="edit_tr">
		<td class="command" colspan="4" align="center">
			<jecs:power powerCode="importBdBonusDeduct">
			<button type="submit" class="btn btn_sele" name="import"	><jecs:locale key="button.report"/></button>
			</jecs:power>
			<input type="hidden" name="strAction" value="importBdBonusDeduct"/>
			
		</td>
	</tr>
</tbody>
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
	if(theForm.treatedNo.value==""){
		alert("<jecs:locale key="bdBounsDeduct.treatedNo.required"/>");
		theForm.treatedNo.focus();
		return false;
	}
	if(theForm.importExcelFile.value==""){
		alert("<jecs:locale key="bdBounsDeduct.importExcelFile.required"/>");
		theForm.importExcelFile.focus();
		return false;
	}
	return true;
}
</script>