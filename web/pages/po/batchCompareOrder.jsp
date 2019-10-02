<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<c:if test="${isFinished!=true}">
<form:form commandName="batchCompareOrder" method="post" action="" onsubmit="return validateOthers(this)" id="batchCompareOrderForm" enctype="multipart/form-data">

<spring:bind path="batchCompareOrder.*">
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
	<tr class="edit_tr">
	<th width="25%">
        <jecs:label  key="fiPayData.importFile"/>
    </th>
    <td>
   		<input name="importExcelFile" type="file" id="importExcelFile" size="50" /></td></tr>
    
    <tr class="edit_tr">
		<th width="25%">
			<jecs:label key="upload.module" styleClass="desc" />
		</th>
		<td>	  
			<a href="./images/upload_compareorder.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a>
		</td>
	</tr>
    <tr class="edit_tr">
		<td  class="command"  colspan="2" align="center">
			<button type="submit" class="btn btn_sele" name="import"	onclick="bCancel=false"><jecs:locale key="button.import"/></button>
				
		</td>
	</tr>
  </tbody>
</table>

</form:form>
</c:if>

<c:if test="${isFinished==true}">
	<div id="titleBar">
		<input type="button" class="button" name="cancel"  onclick="location.href='batchCompareOrderPrc.html';" value="<jecs:locale key="operation.button.return"/>" />
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