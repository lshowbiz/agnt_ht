<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmCouponInfoss.title" /></title>
<content tag="heading">
<jecs:locale key="jpmCouponInfoss.heading" />
</content>
<meta name="menu" content="JpmCouponInfoMenu" />


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form method="post" action="jpmCouponInfoDayReport.html"
	onsubmit="return validateOthers(this)" id="bdPeriodForm">

	<table class='detail' width="70%">
		<tbody class="window">
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
						<input type="submit" name="submit" value="<jecs:locale key="button.report"/>" class="btn btn_sele" />
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