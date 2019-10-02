<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jamMsnTypeDetail.title"/></title>
<content tag="heading"><jecs:locale key="jamMsnTypeDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
		<%--<jecs:power powerCode="deleteJamMsnType">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JamMsnType')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>--%>
</c:set>

<spring:bind path="jamMsnType.*">
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

<form:form commandName="jamMsnType" method="post" action="editJamMsnType.html" id="jamMsnTypeForm">


<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JamMsnType')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%" >
<form:hidden path="mtId"/>
	<tbody class="window" >
		<tr class="edit_tr">
		
			<th><span class="text-font-style-tc"><jecs:label  key="bdOutwardBank.bankCode"/></span></th>
			<td>
				
				<!--form:errors path="msnKey" cssClass="fieldError"/-->
				<c:if test="${param.strAction=='addJamMsnType' }">
					<span class="textbox">
					<form:input path="msnKey" id="msnKey" cssClass="textbox-text"/>
					</span>
				</c:if>
				<c:if test="${param.strAction!='addJamMsnType' }">
					${jamMsnType.msnKey }
				</c:if>
				
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="jamMsnType.msnName"/></span></th>
			<td>
				<!--form:errors path="msnName" cssClass="fieldError"/-->
				<c:if test="${param.strAction!='viewJamMsnType' }">
					<span class="textbox">
					<form:input path="msnName" id="msnName" cssClass="textbox-text"/>
					</span>
				</c:if>
				<c:if test="${param.strAction=='viewJamMsnType' }">
					${jamMsnType.msnName }
				</c:if>
			</td>
		</tr>
		
		<c:if test="${param.strAction!='addJamMsnType' }">
			<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="customerRecord.state"/></span></th>
			<td>
				<!--form:errors path="msnStatus" cssClass="fieldError"/-->
				<span class="textbox">
				<c:if test="${param.strAction!='viewJamMsnType' }">  
				 <jecs:list name="msnStatus" id="msnStatus" listCode="msnstatus" value="${jamMsnType.msnStatus }" defaultValue="0" styleClass="textbox-text"/>
		   
				</c:if>
				<c:if test="${param.strAction=='viewJamMsnType' }">
					<jecs:code listCode="msnstatus" value="${jamMsnType.msnStatus }" />
				</c:if>
				</span>
			  </td>
			  </tr>
		</c:if>
		
		<tr class="edit_tr">
		<th><span class="text-font-style-tc-tare"><jecs:label  key="busi.common.remark"/></span></th>
		<td colspan="3">
			<!--form:errors path="msnDesc" cssClass="fieldError"/-->
			<c:if test="${param.strAction!='viewJamMsnType' }">
				<span class="text-font-style-tc-right">
				<form:textarea path="msnDesc" cssClass="textarea_border"/>
				</span>
			</c:if>
			<c:if test="${param.strAction=='viewJamMsnType' }">
				${jamMsnType.msnDesc }
			</c:if>
		</td></tr>
		
		
		
		<tr>
			<td class="command" colspan="4" align="center">
			<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
			</jecs:power>
			<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()" ><jecs:locale key="operation.button.return"/></button>
			</td>
		</tr>
		
	</tbody>
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JamMsnType')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jamMsnTypeForm'));
</script>

<v:javascript formName="jamMsnType" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
