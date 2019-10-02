<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title" /></title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />

<%--<script type="text/javascript" src="./scripts/jQProgressBar/jquery.js"></script>
<script type="text/javascript" src="./scripts/jQProgressBar/jquery.progressbar.js"></script>--%>

<script type="text/javascript" src="./scripts/validate.js"> </script> 
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/print.css'/>" />
<style media="print">
	.noPrint { 
		display: none;
	}
</style>

<c:if test="${empty bdPeriod.wid}">
<form:form commandName="bdPeriod" method="post" action="jbdNewComerReport.html" onsubmit="return validateOthers(this)" id="bdPeriodForm">
	<spring:bind path="bdPeriod.*">
	    <c:if test="${not empty status.errorMessages}">
	    <div class="error">    
	        <c:forEach var="error" items="${status.errorMessages}">
	            <img src="<c:url value="/images/iconWarning.gif"/>"
	                alt="<jecs:locale key="icon.warning"/>" class="icon" />
	            <c:out value="${error}" escapeXml="false"/><br />
	        </c:forEach>
	    </div>
	    </c:if>
	</spring:bind>

	<div class="searchBar">
				<jecs:power powerCode="jbdNewComerReport">	
				<input type="submit" name="submit" value="<jecs:locale key="button.report"/>" class="button"/>
				</jecs:power>
				<input type="hidden" name="strAction" value="jbdNewComerReport"/>
	</div>
	<table class="detail" width="100%">
	<form:hidden path="wid"/>
		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
		<tr>
			<th><jecs:label key="bdReconsumMoneyReport.companyCH"/></th>
			<td>
			
			<jecs:company name="companyCode" selected="${param.companyCode }"  withAA="true"  />
			</td>
		</tr>
		</c:if>
		<tr>
			<th><label><jecs:locale key="bdBounsDeduct.wweek" />:</label></th>
			<td >
				<input type="text" name="formatedWeek" id="formatedWeek" size="8"/>
				<jecs:locale key="label.example"/>200806
			</td>
		</tr>
		
		<%--<tr>
			<th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
			<td class="command">
				<jecs:power powerCode="bdSendRecordBReport">	
				<input type="submit" name="submit" value="<jecs:locale key="button.report"/>" class="button"/>
				</jecs:power>
				<input type="hidden" name="strAction" value="bdSendRecordBReport"/>
				<%@ include file="/common/progress_bar.jsp"%>
			</td>
		</tr>--%>
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