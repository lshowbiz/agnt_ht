<%@ page language="java" errorPage="/error.jsp"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<title><jecs:locale key="bdBounsDeductDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="bdBounsDeductDetail.heading" />
</content>

<c:if test="${isFinished!=true}">
	<form:form commandName="bdBounsDeduct" method="post" action=""
		onsubmit="return validateOthers(this)" id="bdBounsDeductForm"
		enctype="multipart/form-data">

		<spring:bind path="bdBounsDeduct.*">
			<c:if test="${not empty status.errorMessages}">
				<div class="error">
					<c:forEach var="error" items="${status.errorMessages}">
						<img src="<c:url value="/images/iconWarning.gif"/>"
							alt="<jecs:locale key="icon.warning"/>" class="icon" />
						<c:out value="${error}" escapeXml="false" />
						<br />
					</c:forEach>
				</div>
			</c:if>
		</spring:bind>
		
		<table class='detail' width="70%">
			<form:hidden path="id" />
			<tbody class="window">				
				<tr class="edit_tr">
					<th width="25%">
						<span class="req">*</span><jecs:label key="fiPayData.importFile" />
					</th>
					<td colspan="3" >
					 	<input name="importExcelFile" type="file" id="importExcelFile"
						size="50" />
					</td>			
				</tr>
				<tr class="edit_tr">
					<th>
						<jecs:label key="busi.common.remark" />
					</th>
					<td colspan="3" >
					 	第一列：财年；第二列：会员编号；第三列：2013财年：1.东南亚旅游奖励 2.海外豪华旅游奖励 。
					</td>			
				</tr>
				<tr>
					<td class="command" colspan="4" align="center">
						<jecs:power powerCode="jbdTravelPointDetailImport">
							<input type="submit" class="btn btn_sele" name="import"
								onclick="bCancel=false"
								value="<jecs:locale key="button.import"/>" />
						</jecs:power>
						<input type="button" class="btn btn_sele" name="cancel"
						onclick="window.history.back()"
						value="<jecs:locale key="operation.button.return"/>" />	
						<input type="hidden" name="strAction"
							value="jbdTravelPointDetailImport" />
					</td>
				</tr>
			</tbody>
		</table>

	</form:form>
</c:if>

<c:if test="${isFinished==true}">
	<div id="titleBar">
		<input type="button" class="button" name="cancel"
			onclick="history.back()"
			value="<jecs:locale key="operation.button.return"/>" />
	</div>
	<table>
		<c:forEach items="${messages}" var="messageVar">
			<tr>
				<td>
					&gt;&gt;${messageVar}
				</td>
			</tr>
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