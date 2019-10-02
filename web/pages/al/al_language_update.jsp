<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCountryDetail.title"/></title>
<content tag="heading"><jecs:locale key="alCountryDetail.heading"/></content>

<form:form commandName="alCharacterCoding" method="post" action="al_language_update.html" id="alCharacterCodingForm">

<table class="detail" width="70%">
	<tbody class="window" >
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="al.ref.language"/></span></th>
			<td>
				<span class="textbox">
					<form:select path="characterCode" items="${alCharacterCodings}" itemLabel="characterName" itemValue="characterCode" cssClass="textbox-text"></form:select>
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label key="al.update.language"/></span></th>
			<td>
				<span class="textbox">
					<select name="updateCharacterCode" class="textbox-text">
						<c:forEach items="${alCharacterCodings}" var="alCharacterCodingVar">
							<c:if test="${alCharacterCodingVar.characterCode!='zh_TW'}">
								<option value="${alCharacterCodingVar.characterCode }">${alCharacterCodingVar.characterName }</option>
							</c:if>
						</c:forEach>
					</select>
				<span>
			</td>
		</tr>

		
		<tr>
			<td class="command" colspan="4" align="center">
				<jecs:power powerCode="alLanguageUpdate">
					<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
				</jecs:power>
				<input type="hidden" name="strAction" value="alLanguageUpdate"/>
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
