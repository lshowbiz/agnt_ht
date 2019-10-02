<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<c:if test="${isFinished!=true}">
<form:form commandName="jbdMemberLinkCalcHist" method="post" action="" onsubmit="return validateOthers(this)" id="jbdMemberLinkCalcHistForm" enctype="multipart/form-data">

<spring:bind path="jbdMemberLinkCalcHist.*">
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
			2.第1至第4列为： 会员编号	期别	   资格奖衔   资格奖衔备注<br/>
			3.资格奖衔(
		<c:forEach items="${starsMap }" var="star">
			${star.key }:${star.value } &nbsp;&nbsp;
		</c:forEach>
		)
    </td></tr>
    
    <tr class="edit_tr">
		
		<td colspan="4" align="center" class="command" >
			<jecs:power powerCode="jbdMemberQualifyStarImport">
			<button type="submit" class="btn btn_sele" name="import"  onclick="bCancel=false">
				<jecs:locale key="button.import"/>
			</button>
			</jecs:power>
			<input type="hidden" name="strAction" value="jbdMemberQualifyStarImport"/>
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