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

<c:if test="${empty bdPeriod.wid}">
<form:form commandName="bdPeriod" method="post" action="jmiMemberActiveReport.html" id="bdPeriodForm">
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
			
			<span class="textbox"><jecs:company name="companyCode" selected="${param.companyCode }"  withAA="false" styleClass="textbox-text" /></span>
			</td>
		</tr>
		</c:if>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="common.startTime"/></span></th>
			<td>
			
			<span class="textbox"><input name="startDate" id="startDate" type="text" value="${param.startDate }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"class="textbox-text" /></span>
			</td>
			
			<th><span class="text-font-style-tc"><jecs:label key="schedule.endTime"/></span></th>
			<td>
			<span class="textbox"><input name="endDate" id="endDate" type="text" value="${param.endDate }" style="cursor: pointer;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="textbox-text" />	</span>
			</td>
		</tr>
		<tr>		
			<td class="command" colspan="4" align="center">
				<jecs:power powerCode="jmiMemberActiveReport">	
				<input type="submit" name="submit" value="<jecs:locale key="button.report"/>" class="btn btn_sele"/>
				</jecs:power>
				<input type="hidden" name="strAction" value="jmiMemberActiveReport"/>
			</td>
		</tr>
	</tbody>
	</table>
	
</form:form>

</c:if>
