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
		action="bdSendRecordAllotFiReport.html" id="bdPeriodForm">
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
				<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
					<tr class="edit_tr">
						<th>
							<jecs:label key="bdReconsumMoneyReport.companyCH" />
						</th>
						<td colspan="3">
						 	<jecs:company name="companyCode" selected="${param.companyCode }"
							withAA="false" />
						</td>
					</tr>
				</c:if>
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc">
							<label>
								<jecs:locale key="bd.app.time" />(<jecs:locale key="start.word" />):
							</label>
						</span>
					</th>
					<td>
						<span class="textbox">
						 	<input name="startDate" id="startDate" type="text" class="textbox-text"
							value="${param.startDate }" style="cursor: pointer;"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
						</span>
					</td>
					<th>
						<span class="text-font-style-tc">
							<label>
								<jecs:locale key="bd.app.time" />(<jecs:locale key="end.word" />):
							</label>
						</span>
					</th>
					<td>
						<span class="textbox">
							<input name="endDate" id="endDate" type="text" class="textbox-text"
							value="${param.endDate }" style="cursor: pointer;"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
						</span>
					</td>
				</tr>
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc">
							<jecs:label key="bdBounsDeductReport.type" />
						</span>
					</th>
					<td>
						<span class="textbox">
						 	<jecs:list name="type" listCode="bd.send.allot.fi"
							value="${param.type }" defaultValue="0" styleClass="textbox-text"/>
						</span>
					</td>		
				</tr>
				<tr>
					<td class="command" colspan="4" align="center">		
						<jecs:power powerCode="bonusSendFiReport">
							<input type="submit" name="submit"
								value="<jecs:locale key="button.report"/>" class="btn btn_sele" />
						</jecs:power>
						<input type="hidden" name="strAction" value="bonusSendFiReport" />
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