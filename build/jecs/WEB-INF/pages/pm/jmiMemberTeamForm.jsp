<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiMemberTeamDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="jmiMemberTeamDetail.heading" />
</content>

<%-- 
<c:set var="buttons">

	<jecs:power powerCode="${param.strAction}">
		<input type="submit" class="button" name="save"
			onclick="bCancel=false"
			value="<jecs:locale key="operation.button.save"/>" />
	</jecs:power>
	<!-- 
	<c:if test="${jmiMemberTeam.code!=null}">
		<jecs:power powerCode="deleteJmiMemberTeam">
			<input type="submit" class="button" name="delete"
				onclick="bCancel=true;return confirmDelete(this.form,'JmiMemberTeam')"
				value="<jecs:locale key="operation.button.delete"/>" />
		</jecs:power>
	</c:if>-->
	<input type="button" class="button" name="back" onclick="javascript:history.back();"
		value="<jecs:locale  key="operation.button.return"/>" />
</c:set>
--%>

<c:set var="buttons">

	<jecs:power powerCode="${param.strAction}">
		<button type="submit" class="btn btn_sele" name="save"
			onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
	</jecs:power>
	<!-- 
	<c:if test="${jmiMemberTeam.code!=null}">
		<jecs:power powerCode="deleteJmiMemberTeam">
			<input type="submit" class="button" name="delete"
				onclick="bCancel=true;return confirmDelete(this.form,'JmiMemberTeam')"
				value="<jecs:locale key="operation.button.delete"/>" />
		</jecs:power>
	</c:if>-->
	<button type="button" class="btn btn_sele" name="back" onclick="javascript:history.back();"><jecs:locale  key="operation.button.return"/></button>
</c:set>

<spring:bind path="jmiMemberTeam.*">
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

<form:form commandName="jmiMemberTeam" method="post"
	action="editJmiMemberTeam.html"
	onsubmit="return validateJmiMemberTeam(this)" id="jmiMemberTeamForm">

<%--   	<div id="titleBar">
		<c:out value="${buttons}" escapeXml="false" />
	</div>
--%>

	<input type="hidden" name="strAction" value="${param.strAction }" />

	<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiMemberTeam')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
	<table class='detail' width="70%">
		<tbody class="window">
		
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><jecs:label key="jmiMemberTeam.code" /></span>
				</th>
				<td>
					<c:choose>
						<c:when test="${param.strAction=='editJmiMemberTeam'}">
							<span class="textbox"><span class="textbox-text">${jmiMemberTeam.code }</span></span><form:hidden path="code" />
						</c:when>
						<c:otherwise>
							<span class="textbox"><form:input path="code" id="code" cssClass="textbox-text" /></span>
						</c:otherwise>				
					</c:choose>
				</td>
			
				<th>
					<span class="text-font-style-tc"><jecs:label key="jmiMemberTeam.name" /></span>
				</th>
				<td>
					<!--form:errors path="name" cssClass="fieldError"/-->
					<span class="textbox"><form:input path="name" id="name" cssClass="textbox-text" /></span>
				</td>
			</tr>
			
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><jecs:label key="jmiMemberTeam.fullname" /></span>
				</th>
				<td>
					<!--form:errors path="name" cssClass="fieldError"/-->
					<span class="textbox"><form:input path="fullName" id="fullName" cssClass="textbox-text" /></span>
				</td>
			
				<th>
					<span class="text-font-style-tc"><jecs:label key="jmiMemberTeam.status" /></span>
				</th>
				<td>
					<span class="textbox"><jecs:list styleClass="textbox-text" listCode="jmimemberteam.status" name="status" id="status"
							showBlankLine="false" value="${jmiMemberTeam.status}"
							defaultValue="0" /></span>
				</td>
			</tr>
			
			<tr class="edit_tr"> 
				<th>
					<span class="text-font-style-tc"><jecs:label key="jmiMemberTeam.isbuy" /></span>
				</th>
				<td>
					<span class="textbox"><jecs:list styleClass="textbox-text" listCode="yesno" name="isBuy" id="isBuy"
							showBlankLine="false" value="${jmiMemberTeam.isBuy}"
							defaultValue="0" /></span>
				</td>
			</tr>
			
			<tr>		
			<td class="command" colspan="4" align="center">
				<c:out value="${buttons}" escapeXml="false"/>
			</td>
		</tr>
		</tbody>
	</table>
	<!--/fieldset-->

	<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JmiMemberTeam')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jmiMemberTeamForm'));
</script>

<v:javascript formName="jmiMemberTeam" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript"
	src="<c:url value="/scripts/validator.jsp"/>"></script>
