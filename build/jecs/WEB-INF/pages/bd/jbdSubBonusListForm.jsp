<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<title><jecs:locale key="bdBounsDeductDetail.title"/></title>
<content tag="heading"><jecs:locale key="bdBounsDeductDetail.heading"/></content>

<c:if test="${isFinished!=true}">
<form:form commandName="jbdSubBonusList" method="post" action="" onsubmit="return validateOthers(this)" id="bdBounsDeductForm" enctype="multipart/form-data">

<spring:bind path="jbdSubBonusList.*">
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
	<tr class="edit_tr"><th width="35%">
        <jecs:label key="bdBounsDeduct.treatedNo"/>
    </th>
    <td align="left">
		<form:input path="treatedNo"/>
    </td></tr>

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
		1.第一行为抬头，不导入<br/>
		2.第1列为： 会员编号 第2列为： 金额(两位小数) 第3列为： 摘要<br/>
    </td></tr>
    
    <tr class="edit_tr"	>
		<td class="command" colspan="4" align="center">
			<button type="submit" name="import" class="btn btn_sele"><jecs:locale key="button.report"/></button>
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