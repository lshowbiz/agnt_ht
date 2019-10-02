<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title" />
</title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />

<form method="post" action="fiBankbookFMonthReport.html"
	onsubmit="return validateOthers(this)" id="bdPeriodForm">
	
	<table class='detail' width="70%">
		<tbody class="window">
			<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc">
							<label>
								<jecs:locale key="bdReconsumMoneyReport.companyCH" />
								:
							</label>
						
					</th>
					<td colspan="3">
						<select name="companyCode">
							<c:forEach items="${alCompanys}" var="alCompanyVar">
								<option value="${alCompanyVar.companyCode }"
									<c:if test="${alCompanyVar.companyCode==param.companyCode}"> selected</c:if>>
									${alCompanyVar.companyName }
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</c:if>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc">
						<label>
							<span class="req">*</span>
							<jecs:label key="fiBankbookTemp.bankbookType" />
							:
						</label>
					</span>
				</th>
				<td>
					<span class="textbox">
						<jecs:list listCode="fibankbooktemp.bankbooktype" styleClass="textbox-text"
						name="bankbookType" id="bankbookType" value="${param.bankbookType}"
						defaultValue="1" />
					</span>
				</td>
				<th>
					<span class="text-font-style-tc">
						<label>
							<span class="req">*</span>
							<jecs:locale key="title.date" />
							:
						</label>
					</span>
				</th>
				<td>
					<span class="textbox">
						<input name="fyear" id="fyear" type="text" value="" class="textbox-text"
							/>						
					</span>(ex:2013)
				</td>
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<jecs:power powerCode="fiBankbookFMonthReportJJ">
						<input type="submit" name="submit"
							value="<jecs:locale key="button.report"/>" class="btn btn_sele" />
					</jecs:power>
					<input type="hidden" name="strAction"
						value="fiBankbookFMonthReportJJ" />
				</td>
			</tr>
		</tbody>
	</table>

</form>

<script type="text/javascript">
function validateOthers(theForm){
	if(theForm.fyear.value=="" || !isUnsignedNumeric(theForm.fyear.value)){
		alert("请输入正确格式的日期!");
		return false;
	}
	
	if(theForm.fyear.value.length != 4){
		alert("请输入正确格式的日期!");
		return false;
	}
	
	return true;
}
</script>
<script language="javascript" src="scripts/validate.js"></script>