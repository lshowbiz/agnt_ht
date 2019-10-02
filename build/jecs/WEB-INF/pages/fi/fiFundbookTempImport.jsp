<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<title><jecs:locale key="fiFundbookTempDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="fiFundbookTempDetail.heading" />
</content>

<form action="" method="post" enctype="multipart/form-data"
	name="fiFundbookTempForm" id="fiFundbookTempForm"
	onsubmit="return validateOthers(this)">

	<spring:bind path="fiFundbookTemp.*">
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
				<th>
					<label for="xlsFile" class="required">
						<span class="req">*</span>
						<jecs:locale key="fiPayData.importFile" />
						:
					</label>
				</th>
				<td colspan="3" >
				 	<input type="file" name="xlsFile" id="xlsFile" size="50" />
				</td>			
			</tr>
			<tr class="edit_tr">
				<th>
					<label>
						<jecs:locale key="fiPayData.import.notice.label" />
						:
					</label>
				</th>
				<td colspan="3" >
				 	1.档案使用 XLS 格式。
					<br />
					2.档案规则：
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					会员编号,基金类型,操作类型,资金类别,金额,摘要
					<br />
					3.档案内第一行为标题，故不汇入。
					<br />
					4.收入金额不使用千分位。
					<br />
					<br />
					<br />
					基金类型：
					<br />
					1.分红基金
					<br />
					2.定向基金
	
					<br />
					<br />
					<jecs:locale key="fiBankbookTemp.dealType" />
					<br />
					<c:forEach items="${dealTypes}" var="dealTypeVar">
	    			${dealTypeVar.key } - <jecs:locale key="${dealTypeVar.value }" />
						<br />
					</c:forEach>
					<br />
					<jecs:locale key="fiBankbookTemp.moneyType" />
					:
					<br />
					1、分红基金存入
					<br />
					2、分红基金转他人分红基金
					<br />
					3、分红基金转定向基金
					<br />
					4、分红基金转发展基金
					<br />
					5、定向基金存入
					<br />
					6、定向基金转入他人定向基金
					<br />
					8、定向基金支付
					<br />
					<br />
					<br />
					备注：执行导入需要时间，请耐心等待，不要离开当前页面。
				</td>			
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<input type="hidden" name="strAction" value="importFiFundbookTempJJ" />
					<jecs:power powerCode="importFiFundbookTempJJ">
						<input type="submit" class="btn btn_sele" name="import"
							onclick="bCancel=false" value="<jecs:locale key="button.import"/>" />
					</jecs:power>
	
					<input type="button" class="btn btn_sele" name="cancel"
						onclick="window.location='fiFundbookTemps.html?strAction=listfiFundbookTempsJJ&needReload=1';"
						value="<jecs:locale key="operation.button.cancel"/>" />
				</td>
			</tr>
		</tbody>
	</table>
</form>

<script type="text/javascript">
Form.focusFirstElement($('fiFundbookTempForm'));

function validateOthers(theForm){
	if(theForm.xlsFile.value=="" || getFileType(theForm.xlsFile.value)!=".xls"){
		alert("<jecs:locale key="fiBankbookTemp.xlsFile.required"/>");
		theForm.xlsFile.focus();
		return false;
	}
	return isFormPosted();
}
</script>