<%@ page language="java" errorPage="/error.jsp"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<c:if test="${isFinished!=true}">
	<form:form commandName="jmiMember" method="post" action=""
		onsubmit="return validateOthers(this)" id="batchUpdateStarRemarkForm"
		enctype="multipart/form-data">

		<spring:bind path="jmiMember.*">
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
			<tbody class="window">
				<tr class="edit_tr">
					<th width="25%">
						<jecs:label
								key="sysDataLog.changeType" />
					</th>
					<td>
						<span class="textbox"> <jecs:list styleClass="textbox-text"
								name="scType" listCode="sc.type" value="${param.scType}"
								defaultValue="1" /> </span>
					</td>
				</tr>


				<tr class="edit_tr">
					<th>
						<jecs:label
								key="fiPayData.importFile" />
					</th>
					<td>
						<input name="importExcelFile"
								type="file" id="importExcelFile" size="50" />
					</td>
				</tr>

				<tr class="edit_tr">
					<th>
						<jecs:label
								key="busi.common.remark" />
					</th>
					<td>
						所导入的文件格式说明:<br /> 1.第一行为抬头，不转入<br />
							2.第1列为： 会员编号
					</td>
				</tr>

				<tr class="edit_tr">
						<td class="command" colspan="2" align="center">

							<jecs:power powerCode="jmiSpecialListImport">
								<button type="submit" class="btn btn_sele" name="import"
									onclick="bCancel=false">
									<jecs:locale key="button.import" />
								</button>
							</jecs:power>

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

	if(theForm.importExcelFile.value==""){
		alert("<jecs:locale key="bdBounsDeduct.importExcelFile.required"/>");
		theForm.importExcelFile.focus();
		return false;
	}
	return true;
}
</script>