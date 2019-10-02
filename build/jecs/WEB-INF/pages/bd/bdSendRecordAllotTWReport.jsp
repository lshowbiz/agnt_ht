<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title" /></title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<%--<script type="text/javascript" src="./scripts/jQProgressBar/jquery.js"></script>
<script type="text/javascript" src="./scripts/jQProgressBar/jquery.progressbar.js"></script>--%>

<script type="text/javascript" src="./scripts/validate.js"> </script>
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/styles/print.css'/>" />
<style media="print">
.noPrint {
	display: none;
}
</style>

<c:if test="${empty bdPeriod.wid}">
	<form:form commandName="bdPeriod" method="post"
		action="bdSendRecordAllotTWReport.html" id="bdPeriodForm">
		<spring:bind path="bdPeriod.*">
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
			<form:hidden path="wid" />
			<tbody class="window">				
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc">
							<jecs:locale key="bdSendRecord.remittanceMoneyScope" />(<jecs:locale key="start.word" />):
						</span>
					</th>
					<td>
						<span class="textbox">
						 	<input name="startAllotMoney" type="text" class="textbox-text"
								value="${empty param.startAllotMoney ? startAllotMoney : param.startAllotMoney }"
								size="12" />
						</span>
					</td>				
					<th>
						<span class="text-font-style-tc">
							<jecs:locale key="bdSendRecord.remittanceMoneyScope" />(<jecs:locale key="end.word" />):
						</span>
					</th>
					<td>
						<span class="textbox">
							<input name="endAllotMoney" type="text" class="textbox-text"
								value="${param.endAllotMoney }" size="12" />
						</span>
					</td>					
				</tr>
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc">
							<jecs:label key="bd.tw.companyName" />
						</span>
					</th>
					<td>
						<span class="textbox">
						 	<input id="companyName" name="companyName" type="text" size="8"
							maxlength="10" value="${param.companyName }" class="textbox-text"/>
						</span>
					</td>	
					<th>
						<span class="text-font-style-tc">
							<jecs:label key="fiPayAdvice.send.date" />
						</span>
					</th>
					<td>
						<span class="textbox">
						 	<input name="remittanceDate" id="remittanceDate" type="text" 
							value="${param.remittanceDate }" class="textbox-text" style="cursor: pointer;"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
						</span>
					</td>					
				</tr>
				<tr>
					<td class="command" colspan="4" align="center">						
						<jecs:power powerCode="bonusSendReport">
							<input type="submit" name="submit"
								value="<jecs:locale key="button.report"/>" class="btn btn_sele" />
						</jecs:power>
						<input type="hidden" name="strAction" value="bonusSendTWReport" />
					</td>
				</tr>
			</tbody>
		</table>

	</form:form>

</c:if>

<script type="text/javascript">
function validateOthers(theForm){
	if(theForm.formatedWeek.value=="" || theForm.formatedWeek.value.length!=6 || !isUnsignedInteger(theForm.formatedWeek.value)){
		alert("<jecs:locale key="week.isError"/>");
		theForm.formatedWeek.focus();
		return false;
	}
	//setTimeout('showProgressBar()', 4000);
	return true;
}
</script>