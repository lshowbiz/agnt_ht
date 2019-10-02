<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
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

<c:if test="${empty bdPeriod.wid}">
<form:form commandName="bdPeriod" method="post" action="jbdMonthlyAnalysisAmountReport.html" onsubmit="return validateOthers(this)" id="bdPeriodForm">
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
<%-- 
	<div class="searchBar">
				<jecs:power powerCode="jbdMonthlyAnalysisAmountReport">	
				<input type="submit" name="submit" value="<jecs:locale key="button.report"/>" class="button"/>
				</jecs:power>
				<input type="hidden" name="strAction" value="jbdMonthlyAnalysisAmountReport"/>
	</div>
--%>
	<table class="detail" width="70%">
	<tbody class="window">
	<form:hidden path="wid"/>
	
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><label><jecs:locale key="miMember.memberNo" />:</label></span></th>
			<td>
			<span class="textbox">
				<input type="text" name="userCode" id="userCode" size="8" class="textbox-text"/>
				</span>
			</td>
		
			<th><span class="text-font-style-tc"><label>财月:</label></span></th>
			<td>
				<span class="textbox">
				<input type="text" name="formatedWeek" id="formatedWeek" size="8" class="textbox-text"/>
				<jecs:locale key="label.example"/>200806
				</span>
			</td>
		</tr>
		<tr class="edit_tr">
			
			<td class="command" align="center" colspan="4">
				<jecs:power powerCode="jbdMonthlyAnalysisAmountReport">	
					<button type="submit" name="submit" class="btn btn_sele">
						<jecs:locale key="button.report"/>
					</button>
				</jecs:power>
				<input type="hidden" name="strAction" value="jbdMonthlyAnalysisAmountReport"/>
			</td>
		</tr>
		</tbody>
	</table>
	
</form:form>

</c:if>

<script type="text/javascript">
function validateOthers(theForm){
	if(theForm.formatedWeek.value=="" || theForm.formatedWeek.value.length!=6 || !isUnsignedInteger(theForm.formatedWeek.value)){
		alert("请输入正确的财月");
		theForm.formatedWeek.focus();
		return false;
	}
	//setTimeout('showProgressBar()', 4000);
	return true;
}
</script>