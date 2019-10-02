<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysConfigValueDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysConfigValueDetail.heading"/></content>

<form method="post" name="sysConfigValueForm" id="sysConfigValueForm" action="editSysConfigValue.html" onsubmit="return validateOthers(this)">

	<spring:bind path="sysConfigValue.*">
	    <c:if test="${not empty status.errorMessages}">
	    <div class="error">    
	        <c:forEach var="error" items="${status.errorMessages}">
	            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
	                alt="<jecs:locale key="icon.warning"/>" class="icon" />
	            <c:out value="${error}" escapeXml="false"/><br />
	        </c:forEach>
	    </div>
	    </c:if>
	</spring:bind>

	<ec:table 
		items="sysConfigValues"
		var="sysVar"
		action="${pageContext.request.contextPath}/editSysConfigValue.html"
		width="100%"
		showPagination="false"
		form="sysConfigValueForm" sortable="fal./images/extremetablees/extremetable/*.gif"
		>
		<ec:row>
			<ec:column property="company_code" title="alCompany.companyCode" >
				${sysVar.company_code}
	         	<input type="hidden" name=companyCode id="companyCode_${ROWCOUNT}" value="${sysVar.company_code}"/>
			</ec:column>
			<ec:column property="company_name" title="alCompany.companyName"/>
			<ec:column property="config_value" title="sysConfigValue.configValue">
				<input type="hidden" name="valueId1" value="${sysVar.value_id}"/>
	         	<input type="text" name="configValue" value="${sysVar.config_value}" size="60"/>
			</ec:column>
		</ec:row>
	</ec:table>
	
	<c:if test="${not empty param.keyId}">
		<table width="100%" class="detail" border="0">
			<tr>
				<td class="command" align="center">
					<jecs:power powerCode="editSysConfigValue">
					<input type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
					</jecs:power>
					<input type="hidden" name="strAction" value="${param.strAction }"/>
					<input type="hidden" name="keyId" value="${param.keyId }"/>
				</td>
			</tr>
		</table>
	</c:if>

</form>

<script type="text/javascript">
function validateOthers(theForm){
	return isFormPosted();
}
</script>