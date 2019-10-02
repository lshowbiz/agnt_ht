<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title" /></title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />

<!-- 装载日历JS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form method="post" action="jfiBankbookDayReport.html"
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
						</span>
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
							<jecs:locale key="title.date" />
							:
						</label>
					</span>
				</th>
				<td colspan="3">
					<span class="textbox">
						<input name="reportDate" id="reportDate" type="text" class="textbox-text"
							value="${param.reportDate }" style="cursor: pointer;"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" />
					</span>
				</td>
			</tr>
			<tr>
				<td class="command" colspan="4" align="center">
					<jecs:power powerCode="fiBankbookDayReport">
						<input type="submit" name="submit"
							value="<jecs:locale key="button.report"/>" class="btn btn_sele" />
					</jecs:power>
					<input type="hidden" name="strAction" value="fiBankbookDayReport" />
				</td>
			</tr>
		</tbody>
	</table>
</form>

<script type="text/javascript">
function validateOthers(theForm){
	if(theForm.reportDate.value==""){
		alert("<jecs:locale key="please.input.date"/>");
		return false;
	}
	
	return true;
}
</script>