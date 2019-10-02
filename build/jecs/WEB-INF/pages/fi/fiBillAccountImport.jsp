<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="scripts/validate.js"></script>
<title><jecs:locale key="fiBillAccountDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="fiBillAccountDetail.heading" />
</content>

<form action="" method="post" enctype="multipart/form-data"
	name="fiBillAccountForm" id="fiBillAccountForm"
	onsubmit="return validateOthers(this)">

	<spring:bind path="fiBillAccount.*">
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
					<label for="xlsFile" class="required">
						<span class="req">*</span>
						<jecs:locale key="fiPayData.importFile" />
						:
					</label>
				</th>
				<td>
					<input type="file" name="xlsFile" id="xlsFile" size="50" />
				</td>
			</tr>
			<tr class="edit_tr">
				<th width="25%">
					<label>
						<jecs:locale key="fiPayData.import.notice.label" />
						:
					</label>
				</th>
				<td>
					格式：适用类型
					<span class="req">*</span>，平台类型
					<span class="req">*</span>，商户号
					<span class="req">*</span>，经销商编号
					<span class="req">*</span>，注册名称
					<span class="req">*</span>，联系方式，邮箱，备注
					<br>
					适用类型(数字)：
					<br>
					1，PC
					<br>
					补充说明：
					<br>
					1.数据文件使用 XLS 格式。
					<br>
					2.数据文件内第一行为标题，需要填写完整
					<br>
					3.数据文件内第三行起为需要导入的数据，请注意必填字段
					<br>
				</td>
			</tr>
			<tr class="edit_tr">
				<td class="command" colspan="2" align="center">
					<input type="submit" class="btn btn_sele" name="import"
						onclick="bCancel=false" value="<jecs:locale key="button.import"/>" />
	
					<input type="button" class="btn btn_sele" name="cancel"
						onclick="window.location='fiBillAccounts.html?needReload=1';"
						value="<jecs:locale key="operation.button.cancel"/>" />
				</td>
			</tr>
		</tbody>
	</table>
</form>

<script type="text/javascript">
	Form.focusFirstElement($('fiBillAccountForm'));

	window.onload = function() {
		var str = "";
		var purity = document.getElementsByName('providerType')[0].options;
		var arr = new Array();
		for (i = 0; i < Number(purity.length); i++) {
			arr[Number(purity[i].value)] = purity[i].text; //alert(purity[i].value + "----"+ purity[i].text);
		}
		for (i = 1; i < Number(arr.length); i++) {
			str += (i + ":" + arr[i] + "__tag_115$22_")
		}
		document.getElementById('myspan').innerHTML = str;
	}

	function validateOthers(theForm) {
		if (theForm.xlsFile.value == ""
				|| getFileType(theForm.xlsFile.value) != ".xls") {
			alert("请选择需要导入的数据文件!");
			theForm.xlsFile.focus();
			return false;
		}
		return isFormPoste
</script>