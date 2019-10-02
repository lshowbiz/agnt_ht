<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<c:if test="${isFinished!=true}">
<form:form commandName="batchUpdateRefund" method="post" action="" onsubmit="return validateOthers(this)" id="batchUpdateRefundForm" enctype="multipart/form-data">

<spring:bind path="batchUpdateRefund.*">
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
	<tr class="edit_tr"><th>
        <jecs:label  key="fiPayData.importFile"/>
    </th>
    <td align="left">
   		<input name="importExcelFile" type="file" id="importExcelFile" size="50" /></td></tr>
    
    <tr class="edit_tr"><th>
        <jecs:label  key="busi.common.remark"/>
    </th>
    <td align="left">
        所导入的文件格式说明:<br/>
		1.第一行为抬头，不转入<br/>
		2.第1列为：第1列为：退单编号、第3列为：退单类型（0：正常，1：五折退货）、第3列为：退款状态(1：未退款，2：已退款，3：不退款)、第4列为：备注<br/>
		
    </td></tr>
    <tr class="edit_tr">
		<th>
			<jecs:label key="upload.module" styleClass="desc" />
		</th>
		<td align="left">	  
			<a href="./images/upload_refund.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a>
		</td>
	</tr>
    <tr class="edit_tr">
		<td class="command" colspan="4" align="center">
			<input type="submit" class="btn btn_sele" name="import"	onclick="bCancel=false"	value="<jecs:locale key="button.import"/>" />
			<input type="button" class="btn btn_sele" name="cancel"	onclick="window.location.href='jprRefundRefunds.html'" value="<jecs:locale key="operation.button.return"/>" />					
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

	if(theForm.importExcelFile.value==""){
		alert("<jecs:locale key="bdBounsDeduct.importExcelFile.required"/>");
		theForm.importExcelFile.focus();
		return false;
	}
	return true;
}
</script>