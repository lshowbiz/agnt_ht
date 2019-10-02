<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCountryDetail.title"/></title>
<content tag="heading"><jecs:locale key="alCountryDetail.heading"/></content>

<form:form commandName="alCountry" method="post" action="editAlCountry.html" onsubmit="return validateAlCountry(this)" id="alCountryForm">

<spring:bind path="alCountry.*">
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

<form:hidden path="countryId"/>

<table class="detail" width="70%">
	<tbody class="window" >
		<tr class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label key="alCountry.countryCode"/></span>
			</th>
			<td>
				<span class="textbox">
				<c:if test="${not empty alCountry.countryId }">
					<form:input path="countryCode" id="countryCode" cssClass="textbox-text" readonly="true" size="4"/>
				</c:if>
				<c:if test="${empty alCountry.countryId }">
					<form:input path="countryCode" id="countryCode" cssClass="textbox-text" size="4"/>
				</c:if>
				</span>
			</td>
			<th>
				<span class="text-font-style-tc"><jecs:label key="alCountry.countryName"/></span>
			</th>
			<td>
				<span class="textbox">
					<form:input path="countryName" id="countryName" size="40" htmlEscape="true" cssClass="textbox-text"/>
					<jecs:locale key="alCountry.please.input.character.key"/>
				</span>
			</td>
		</tr>

		<tr>
			<td class="command" colspan="4" align="center">
				<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
				</jecs:power>
				<c:if test="${not empty alCountry.countryId }">
				<jecs:power powerCode="deleteAlCountry">
				<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form, 'AlCountry')"><jecs:locale key="operation.button.delete"/></button>
				</jecs:power>
				</c:if>
				<button class="btn btn_sele" name="cancel" onclick="window.location='alCountrys.html?needReload=1'" ><jecs:locale key="operation.button.cancel"/></button>
				<input type="hidden" name="strAction" value="${param.strAction }"/>
			</td>
		</tr>
    </tbody>

</table>

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('alCountryForm'));
</script>

<v:javascript formName="alCountry" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
