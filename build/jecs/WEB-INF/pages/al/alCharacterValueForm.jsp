<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.joymain.jecs.util.string.StringUtil"%>
<%@page import="com.joymain.jecs.webapp.util.SessionLogin"%>

<title><jecs:locale key="alCharacterValueDetail.title"/></title>
<content tag="heading"><jecs:locale key="alCharacterValueDetail.heading"/></content>

<form method="post" name="alCharacterValueForm" id="alCharacterValueForm" action="editAlCharacterValue.html" onsubmit="return validateOther(this);">
<spring:bind path="alCharacterValue.*">
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
    
	<input type="hidden" name="keyId" value="${param.keyId }"/>

	<ec:table 
		items="alCharacterValues"
		var="alVar"
		action="${pageContext.request.contextPath}/editAlCharacterValue.html"
		width="100%"
		showPagination="false"
		form="alCharacterValueForm" sortable="false" showStatusBar="false" imagePath="./images/extremetable/*.gif"
		>
		<ec:row highlightRow="false">
			<c:set var="allowedUser" value="${alVar.allowed_user}" scope="request"/>
			<ec:column property="character_code" title="alCharacterCoding.characterCode" >
				${alVar.character_code}
	         	<input type="hidden" name=characterCode id="characterCode_${ROWCOUNT}" value="${alVar.character_code}"/>
			</ec:column>
			<ec:column property="character_name" title="alCharacterCoding.characterName"/>
			<ec:column property="character_value" title="alCharacterCoding.characterValue">
				<input type="hidden" name="valueId1" value="${alVar.value_id}"/>
	         	<input type="text" name="characterValue" value="<c:out value="${alVar.character_value}" escapeXml="true"/>" size="60"
	         	<%
	         	if(!SessionLogin.getLoginUser(request).getIsAdmin()){
	         		Object allowedUser=request.getAttribute("allowedUser");
		         	if(allowedUser==null || !StringUtil.hasString(allowedUser.toString().split(","),SessionLogin.getLoginUser(request).getUserCode(),false)){
		         		out.print(" readonly class=\"readonly\"");
		         	}
	         	}
	         	%>
	         	/>
			</ec:column>
		</ec:row>
	</ec:table>
	
	<c:if test="${not empty param.keyId}">
		<table width="100%" class="detail">
			<tr>
				<td class="command" colspan="4" align="center">
					<jecs:power powerCode="editAlCharacterValue">
					<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
					</jecs:power>
					<input type="hidden" name="strAction" value="editAlCharacterValue"/>
				</td>
			</tr>
		</table>
	</c:if>
</form>

<script>
function validateOther(theForm){
	return isFormPosted();
}
</script>