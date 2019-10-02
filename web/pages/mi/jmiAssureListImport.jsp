<%@ page language="java" errorPage="/error.jsp"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<c:if test="${isFinished!=true}">
	<form:form commandName="jmiAssure" method="post"
		action="jmiAssureListImport.html"
		onsubmit="return validateOthers(this)" id="batchUpdateStarRemarkForm"
		enctype="multipart/form-data">
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

		<table>
			<c:forEach items="${messages}" var="messageVar">
				<tr>
					<td>
						&gt;&gt;${messageVar}
					</td>
				</tr>
			</c:forEach>
		</table>
		<br />
		
		
		<table class='detail' width="70%">
			<tbody class="window">				
				<tr class="edit_tr">
					<th width="40%">
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
						2.第1列为：担保类型
						<br />
						3.第2列为：担保内容
						<br />
						4.第3列为：担保编号
						<br />
						5.第4列为：担保订单编号
						<font style="color: red">
							(注意：不退货担保此订单编号必填，多个担保订单编号请用英文半角格式的逗号(,) 分隔)</font>
						<br />
						6.第5列为：业务开始时间
						<br />
						7.第6列为：业务结束时间
						<br />
						8.第7列为：是否达成担保
						<br />
						9.第8列为：备注
						<br />
					</td>			
				</tr>
				<tr class="edit_tr">
					<th>
						<jecs:label key="upload.module" styleClass="desc" />
					</th>
					<td colspan="3" >
					 	<a href="./images/import_jmiAssure.xls" target="blank"><img
								src="<c:url value='/images/extremetable/xls.gif'/>" />
						</a>
					</td>			
				</tr>
				<tr>
					<td class="command" colspan="4" align="center">
						<jecs:power powerCode="jmiAssureListImport">
							<input type="submit" class="btn btn_sele" name="import"
								onclick="bCancel=false"
								value="<jecs:locale key="button.import"/>" />
						</jecs:power>
						<input type="button" class="btn btn_sele" name="cancel"
						onclick="history.back()"
						value="<jecs:locale key="operation.button.return"/>" />
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
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