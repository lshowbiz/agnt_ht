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
		action="jbdComingHonorStarReport.html"
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
				<tr>			
					<th>
						<span class="text-font-style-tc">
							<label>
								<jecs:locale key="bdBounsDeduct.sweek" />:
							</label>
						</span>
					</th>
					<td>
						<span class="textbox">
						 	<input type="text" name="formatedWeekStart" id="formatedWeekStart"
								size="8" class="textbox-text"/>
						</span>
					</td>
					<th>
						<span class="text-font-style-tc">
							<label>
								<jecs:locale key="bdBounsDeduct.eweek" />:
							</label>
						</span>
					</th>
					<td>
						<span class="textbox">
							<input type="text" name="formatedWeekEnd" id="formatedWeekEnd"
								size="8" class="textbox-text"/>
						</span>	
					</td>					
				</tr>
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc">
							<label>
								<jecs:locale key="bdBounsDeduct.wweek" />:
							</label>
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
						<jecs:power powerCode="jbdComingHonorStarReport">
							<input type="submit" name="submit"
								value="<jecs:locale key="button.report"/>" class="btn btn_sele" />
						</jecs:power>
						<input type="hidden" name="strAction"
							value="jbdComingHonorStarReport" />						
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>

</c:if>

<script type="text/javascript">
function validateOthers(theForm){
	if(( theForm.formatedWeekStart.value !='' || theForm.formatedWeekEnd.value !='') && (theForm.formatedWeekStart.value.length!=6 || !isUnsignedInteger(theForm.formatedWeekStart.value)||theForm.formatedWeekEnd.value.length!=6 || !isUnsignedInteger(theForm.formatedWeekEnd.value))||theForm.formatedWeek.value=="" || theForm.formatedWeek.value.length!=6 || !isUnsignedInteger(theForm.formatedWeek.value)){
		alert("<jecs:locale key="week.isError"/>");
		theForm.formatedWeekStart.focus();
		return false;
	}
	//setTimeout('showProgressBar()', 4000);
	return true;
}
</script>