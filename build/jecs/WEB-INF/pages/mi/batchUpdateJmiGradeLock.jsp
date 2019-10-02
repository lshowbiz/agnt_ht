<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<c:if test="${isFinished!=true}">
<form:form commandName="batchUpdateJmiGradeLock" method="post" action="" onsubmit="return validateOthers(this)" id="batchUpdateJmiGradeLockForm" enctype="multipart/form-data">

<spring:bind path="batchUpdateJmiGradeLock.*">
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
	<tr class="edit_tr"><th width="35%">
        <jecs:label  key="fiPayData.importFile"/>
    </th>
    <td>
    	
   		<input name="importExcelFile" type="file" id="importExcelFile" size="50" /></td></tr>
    	
    <tr class="edit_tr"><th>
        <jecs:label  key="busi.common.remark"/>
    </th>
    <td>
    	
       	 所导入的文件格式说明:<br/>
		1.第一行为抬头，不转入<br/>
		2.第1行为： 会员编号,期别,身份<br/>
		
    </td></tr>
    <tr class="edit_tr">
		<th>
			<jecs:label key="upload.module" styleClass="desc" />
		</th>
		<td>	  
			
				<a href="./images/upload_batchupdatejmigradelock.xls" target="blank">
					<img src="<c:url value='/images/extremetable/xls.gif'/>" />
				</a>
		
		</td>
	</tr>
    
    <tr class="edit_tr">
		
		<td class="command" colspan="4" align="center">
			<jecs:power powerCode="batchUpdateJmiGradeLock">
				<button type="submit" class="btn btn_sele" name="import"  onclick="bCancel=false">
					<jecs:locale key="button.import"/>
				</button>
			</jecs:power>
			<button type="button" class="btn btn_sele" name="cancel"  onclick="window.location.href='jmiGradeLocks.html'">
				<jecs:locale key="operation.button.return"/>
			</button>
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