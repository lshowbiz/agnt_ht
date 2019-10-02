<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
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
		2.第1至第4列为： 会员编号	开始期	结束期	销售奖资格（1启用0关闭）	感恩奖资格（1启用0关闭）
    </td></tr>
    
    <tr class="edit_tr">
		<td  class="command" colspan="4" align="center">
		
			<jecs:power powerCode="jbdManualConImport">
			<button type="submit" class="btn btn_sele" name="import"  onclick="bCancel=false" >
				<jecs:locale key="button.import"/>
			</button>
			</jecs:power>
			<input type="hidden" name="strAction" value="jbdManualConImport"/>
		
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