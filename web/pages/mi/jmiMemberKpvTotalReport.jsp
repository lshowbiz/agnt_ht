<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="bdPeriodList.title" /></title>
<content tag="heading">
<jecs:locale key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<script type="text/javascript" src="./scripts/validate.js"> </script> 
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/print.css'/>" />
<style media="print">
	.noPrint { 
		display: none;
	}
</style>
<c:if test="${not empty errorMessages }">

	${ errorMessages }
	
		<c:remove var="errorMessages" scope="session" />
</c:if>
<form:form commandName="bdPeriod" method="post" action="jmiMemberKpvTotalReport.html"  onsubmit="return validateOthers(this)"  id="bdPeriodForm"  enctype="multipart/form-data">


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

	<table class="detail" width="70%">
	<tbody class="window" >
	<form:hidden path="wid"/>
		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="bdReconsumMoneyReport.companyCH"/></span></th>
			<td>
			<span class="textbox"><jecs:company name="companyCode" selected="${param.companyCode }"  withAA="false"  styleClass="textbox-text" /></span>
			</td>
		</tr>
		</c:if>
		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:locale key="bdBounsDeduct.wweek" /></span></th>
			<td >
				<span class="textbox"><input type="text" name="formatedWeek" id="formatedWeek" size="8" class="textbox-text"/></span>
				<jecs:locale key="label.example"/>200806
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="fiPayData.importFile"/></span></th>
		    <td align="left">
		   		<span class="textbox"><input name="importExcelFile" type="file" id="importExcelFile" size="50" /></span>
			</td>
			</tr>
		    
		<tr>
		
		<tr>		
			<td class="command" colspan="4" align="center">
			<input type="submit" name="submit" value="<jecs:locale key="button.report"/>" class="btn btn_sele"/>
				<input type="hidden" name="strAction" value="jmiMemberKpvTotalReport"/>
		</td>
		</tr>
	</table>
	
</form:form>

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
