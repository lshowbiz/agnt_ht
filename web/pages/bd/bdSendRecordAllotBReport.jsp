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
<form:form commandName="bdPeriod" method="post" action="bdSendRecordAllotBReport.html" id="bdPeriodForm">
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
				<jecs:power powerCode="bonusSendBReport">	
				<input type="submit" name="submit" value="<jecs:locale key="button.report"/>" class="button"/>
				</jecs:power>
				<input type="hidden" name="strAction" value="bonusSendBReport"/>
	</div>
	<table class="detail" width="100%">
	<form:hidden path="wid"/>
		<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
		<tr>
			<th><jecs:label key="bdReconsumMoneyReport.companyCH"/></th>
			<td>
			
			<jecs:company name="companyCode" selected="${param.companyCode }"  withAA="false"  />
			</td>
		</tr>
		</c:if>
		
		<tr>
			<th><jecs:label key="bdBounsDeductReport.type"/></th>
			<td>
			
<jecs:list name="type" listCode="bd.send.allot" value="${param.type }" defaultValue="0" />	
			</td>
		</tr>
		
		<tr>
			<th><jecs:label key="bdNetWorkCostReport.memberCH"/></th>
			<td>
			
			<select name="leader" id="leader">
			       <option value="">
					<jecs:locale key="list.please.select"/>
					</option>
		       	<c:forEach items="${leaders }" var="leader">

		       		<c:if test="${param.leader==leader }">
		       			<option value="${leader }" selected="selected"><jecs:locale key="${leader }"/></option>        	
		       		</c:if>
		       		<c:if test="${param.leader!=leader}">
		       			<option value="${leader }"><jecs:locale key="${leader }"/></option>  
		       		</c:if>
		       	</c:forEach>
        	</select>	
        	
			</td>
		</tr>
		
		<tr>
			<th><jecs:label key="bdSendRecord.remittanceMoneyScope"/></th>
			<td>
			
<input name="startAllotMoney" type="text" value="${empty param.startAllotMoney ? startAllotMoney : param.startAllotMoney }" size="12"/> - 
<input name="endAllotMoney" type="text" value="${param.endAllotMoney }" size="12"/>	
			</td>
		</tr>
		<%--<tr>
			<th class="command"><jecs:label key="sysOperationLog.moduleName" /></th>
			<td class="command">
				<jecs:power powerCode="bonusSendReport">	
				<input type="submit" name="submit" value="<jecs:locale key="button.report"/>" class="button"/>
				</jecs:power>
				<input type="hidden" name="strAction" value="bonusSendReport"/>
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