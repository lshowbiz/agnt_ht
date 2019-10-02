<%@ page language="java" errorPage="/error.jsp"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<c:if test="${isFinished!=true}">
	<form:form commandName="jfiInvoice" method="post" action=""
		onsubmit="return validateOthers(this)" id="jfiInvoiceImport"
		enctype="multipart/form-data">

		<spring:bind path="jfiInvoice.*">
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
					<th>
						<jecs:label key="fiPayData.importFile" />
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
					 	所导入的文件格式说明:
						<br />
						1.第一行为抬头，不转入
						<br />
						2.第1至第7列分别为： 会员编号、姓名 、发票所属奖金结算年度 、发票所属奖金结算财月 、发票金额、发票所属公司 、备注
						<br />
						3.
						<font color="red">提示：只支持xls格式</font>
					</td>			
				</tr>
				<tr>
					<td class="command" colspan="4" align="center">
						<jecs:power powerCode="jfiInvoicesImport">
							<input type="submit" class="btn btn_sele" name="import"
								onclick="bCancel=false"
								value="<jecs:locale key="button.import"/>" />
							<input type="button" class="btn btn_sele"
								onclick="javascript:history.back(-1);"
								value="<jecs:locale key="button.back"/>" />
						</jecs:power>
						<input type="hidden" name="strAction" value="importJfiInvoice" />
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