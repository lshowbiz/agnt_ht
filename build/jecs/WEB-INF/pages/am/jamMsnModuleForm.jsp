<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jamMsnModuleDetail.title"/></title>
<content tag="heading"><jecs:locale key="jamMsnModuleDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
		<c:if test="${not empty jamMsnModule.jmmNo }">
			<jecs:power powerCode="deleteJamMsnModule">
					<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JamMsnModule')" value="<jecs:locale key="operation.button.delete"/>" />
			</jecs:power>
		
		</c:if>
</c:set>

<spring:bind path="jamMsnModule.*">
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

<form:form commandName="jamMsnModule" method="post" action="editJamMsnModule.html" id="jamMsnModuleForm">


<input type="hidden" name="strAction" value="${param.strAction }"/>
<input type="hidden" name="mtId" value="${mtId }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JamMsnModule')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%" >
		<tbody class="window" >

<form:hidden path="jmmNo"/>

    <tr class="edit_tr">
	<th><span class="text-font-style-tc"><jecs:label  key="sysDataLog.changeType"/></span></th>
    <td>
        <!--form:errors path="mtId" cssClass="fieldError"/-->
        <span class="textbox">${jamMsnModule.jamMsnType.msnName }</span>
    </td>
	<th><span class="text-font-style-tc"><jecs:label  key="bdOutwardBank.bankCode"/></span></th>
    <td>
        <!--form:errors path="mmKey" cssClass="fieldError"/-->
        <c:if test="${param.strAction=='addJamMsnModule' }">
			<span class="textbox">
        	<form:input path="mmKey" id="mmKey" cssClass="textbox-text"/>
			</span>
        </c:if>
        <c:if test="${param.strAction=='editJamMsnModule' }">
        	<span class="textbox">${jamMsnModule.mmKey }</span>
        </c:if>
    </td>
	</tr>
    
    <tr class="edit_tr">
	<th><span class="text-font-style-tc"><jecs:label  key="alCharacterType.typeName"/></span></th>
    <td>
		<span class="textbox">
        <!--form:errors path="titile" cssClass="fieldError"/-->
        <form:input path="titile" id="titile" cssClass="textbox-text"/>
		</span>
    </td>
	<th><span class="text-font-style-tc"><jecs:label  key="jamMsnModule.mmType"/></span></th>
    <td>
        <c:if test="${param.strAction=='addJamMsnModule' }">
			<span class="textbox">
	        <form:select path="mmType" items="${mmTypeSet}" itemValue="key" itemLabel="value"   cssClass="textbox-text" >
		
			</form:select>
			</span>
        </c:if>
        <c:if test="${param.strAction=='editJamMsnModule' }">
    				<jecs:code listCode="msnmodule.type" value="${jamMsnModule.mmType }"/>
        </c:if>
    </td>
	</tr>
   
    <tr class="edit_tr">
	<th><span class="text-font-style-tc-tare"><jecs:label  key="amAnnounce.content"/></span></th>
    <td colspan="3">
        <!--form:errors path="content" cssClass="fieldError"/-->
		<span class="text-font-style-tc-right">
        <form:textarea path="content" cols="40" rows="7" cssClass="textarea_border"/>
		</span>
    </td></tr>
   

	<tr>
		<td class="command" colspan="4" align="center">
			<jecs:power powerCode="${param.strAction}">
						<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
				</jecs:power>
			<c:if test="${not empty jamMsnModule.jmmNo }">
			<jecs:power powerCode="deleteJamMsnModule">
					<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JamMsnModule')" ><jecs:locale key="operation.button.delete"/></button>
			</jecs:power>
			</c:if>
		</td>
	</tr>
</table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jamMsnModuleForm'));

</script>
