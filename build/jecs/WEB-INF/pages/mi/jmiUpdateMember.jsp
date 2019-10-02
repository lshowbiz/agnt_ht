<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<c:if test="${isFinished!=true}">
<form:form commandName="jmiMember" method="post" action="" onsubmit="return validateOthers(this)" id="batchUpdateStarRemarkForm" enctype="multipart/form-data">

<spring:bind path="jmiMember.*">
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
	   		<input name="importExcelFile" type="file" id="importExcelFile" size="50" />
	   	</td>
	</tr>
    
    <tr class="edit_tr">
	    <th>
	        <jecs:label  key="busi.common.remark"/>
	    </th>
    	<td>
	       	所导入的文件格式说明:<br/>
			1.第一行为抬头，不转入<br/>
			2.第1列为： 会员编号、姓、名、移动电话（不做常规证验）、证件类型（1.身份证 2.护照 3.回乡证）、证件号（不做常规证验）<br/>
			3.<font color="red">提示：除会员编号外，为空的字段即默认为不修改，保持原来信息</font>
    	</td>
   	</tr>
    
    <tr class="edit_tr">
		<td class="command" colspan="2" align="center">
			<jecs:power powerCode="jmiUpdateMember">
			<button type="submit" class="btn btn_sele" name="import"  onclick="bCancel=false">
				<jecs:locale key="button.import"/>
			</button>
			</jecs:power>
		</td>
	</tr>
</table>

</form:form>
</c:if>

<c:if test="${isFinished==true}">
	<div id="titleBar">
		<button type="button" class="button" name="cancel"  onclick="history.back()">
			<jecs:locale key="operation.button.return"/>
		</button>
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