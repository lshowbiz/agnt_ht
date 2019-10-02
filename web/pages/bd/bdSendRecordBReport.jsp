<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title" />
</title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />

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
		action="bdSendRecordBReport.html"
		onsubmit="return validateOthers(this)" id="bdPeriodForm">
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
			<tbody class="window">		
				<form:hidden path="wid" />
				<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc">
								<jecs:label key="bdReconsumMoneyReport.companyCH" />
							</span>
						</th>
						<td colspan="3">
							<span class="textbox">
							 	<jecs:company name="companyCode" selected="${param.companyCode }"
								withAA="true" />
							</span>
						</td>					
					</tr>		
				</c:if>
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc">
							<span class="req">*</span><jecs:locale key="bdBounsDeduct.wweek" />:
						</span>
					</th>
					<td colspan="3">
						<span class="textbox">
						 	<input type="text" name="formatedWeek" id="formatedWeek" size="8" class="textbox-text"/>
						</span>
					</td>					
				</tr>
				<tr>
					<td class="command" colspan="4" align="center">		
						<jecs:power powerCode="bdSendRecordBReport">
							<input type="submit" name="submit"
								value="<jecs:locale key="button.report"/>" class="btn btn_sele"/>
						</jecs:power>
						<input type="hidden" name="strAction" value="bdSendRecordBReport" />				
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