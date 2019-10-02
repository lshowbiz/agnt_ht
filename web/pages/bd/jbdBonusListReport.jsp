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
		action="jbdBonusListReport.html"
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
				<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
					<tr class="edit_tr">
						<th>
							<span class="text-font-style-tc">
								<jecs:label key="bdReconsumMoneyReport.companyCH" />
							</span>
						</th>
						<td colspan="3">
						 	<jecs:company name="companyCode" selected="${param.companyCode }"
							withAA="true" />
						</td>				
					</tr>
				</c:if>		
				<tr class="edit_tr">
					<th>
						<span class="text-font-style-tc">
							<span class="req">*</span><label>
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
							<span class="req">*</span><label>
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
				<tr>
					<th>
						<span class="text-font-style-tc">
							<span class="req">*</span><label>
								<jecs:locale key="busi.finance.amount" />:
							</label>
						</span>
					</th>
					<td colspan="3">
						<span class="textbox">
					 		<input name="amount" type="text" value="${param.amount }" size="12" class="textbox-text"/>
					 	</span>
					</td>					
				</tr>
				<tr>
					<td class="command" colspan="4" align="center">	
						<jecs:power powerCode="jbdBonusListReport">
							<input type="submit" name="submit"
								value="<jecs:locale key="button.report"/>" class="btn btn_sele"  />
						</jecs:power>
						<input type="hidden" name="strAction" value="jbdBonusListReport" />	
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>

</c:if>

<script type="text/javascript">
function validateOthers(theForm){
	if(( theForm.formatedWeekStart.value =='' || theForm.formatedWeekEnd.value =='') || (theForm.formatedWeekStart.value.length!=6 || !isUnsignedInteger(theForm.formatedWeekStart.value)||theForm.formatedWeekEnd.value.length!=6 || !isUnsignedInteger(theForm.formatedWeekEnd.value))){
		alert("<jecs:locale key="week.isError"/>");
		theForm.formatedWeekStart.focus();
		return false;
	}
	if(theForm.amount.value=='' || !isDouble(theForm.amount.value)){
		alert("<jecs:locale key="operation.notice.js.moneyNotNullAndInt"/>");
		theForm.amount.focus();
		return false;
	}
	//setTimeout('showProgressBar()', 4000);
	return true;
}

function isDouble(v){
	var a=/^[0-9]*(\.[0-9]{1,2})?$/;
	if(!a.test(v)){
		return false;
	}else{
		return true;
	}}

</script>