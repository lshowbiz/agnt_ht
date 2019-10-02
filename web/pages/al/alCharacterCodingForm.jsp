<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCharacterCodingDetail.title"/></title>
<content tag="heading"><jecs:locale key="alCharacterCodingDetail.heading"/></content>

<form:form commandName="alCharacterCoding" method="post" action="editAlCharacterCoding.html" onsubmit="return validateAlCharacterCoding(this)" id="alCharacterCodingForm">

<spring:bind path="alCharacterCoding.*">
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

<form:hidden path="characterId"/>

<table class="detail" width="70%">
	<tbody class="window" >
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="alCharacterCoding.characterCode"/></span></th>
			<td>
				<span class="textbox">
					<c:if test="${not empty alCharacterCoding.characterId}">
					<form:input path="characterCode" id="characterCode" size="8" readonly="true" cssClass="textbox-text"/>
					</c:if>
					<c:if test="${empty alCharacterCoding.characterId}">
						<select name="characterCode" id="characterCode" class="textbox-text">
							<c:forEach items="${languageMap}" var="lanVar">
								<option value="${lanVar.key }"<c:if test="${lanVar.key==alCharacterCoding.characterCode}"> selected</c:if>>[${lanVar.key }] ${lanVar.value }</option>
							</c:forEach>
						</select>
					</c:if>
				</span>

			</td>
			<th><span class="text-font-style-tc"><jecs:label key="alCharacterCoding.characterName"/></span></th>
			<td><span class="textbox"><form:input path="characterName" id="characterName" cssClass="textbox-text"/></span></td>
		</tr>
		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="login.userType.console"/></span></th>
			<td><span class="textbox"><form:input path="allowedUser" id="allowedUser" size="50" cssClass="textbox-text"/></span></td>
		</tr>
		<tr class="edit_tr">
			<th><jecs:label key="fiBankbookJournal.notes"/></th>
			<td><jecs:locale key="common.seprate.desc"/></td>
		</tr>
		
		<tr>
			<td class="command" colspan="4" align="center">
				<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
				</jecs:power>
				<c:if test="${not empty alCharacterCoding.characterId}">
					<jecs:power powerCode="deleteAlCharacterCoding">
					<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form, 'AlCharacterCoding')"><jecs:locale key="operation.button.delete"/></button>
					</jecs:power>
				</c:if>
				<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='alCharacterCodings.html'" ><jecs:locale key="operation.button.cancel"/></button>
				<input type="hidden" name="strAction" value="${param.strAction }"/>
			</td>
		</tr>
	</tbody>
</table>

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('alCharacterCodingForm'));
</script>

<v:javascript formName="alCharacterCoding" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
