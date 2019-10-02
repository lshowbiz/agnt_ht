<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCharacterTypeDetail.title"/></title>
<content tag="heading"><jecs:locale key="alCharacterTypeDetail.heading"/></content>

<c:if test="${not empty successMessages}">
<script>
opener.window.location.reload();
window.close();
</script>
</c:if>

<form:form commandName="alCharacterType" method="post" action="editAlCharacterType.html" onsubmit="return validateAlCharacterType(this)" id="alCharacterTypeForm">

<spring:bind path="alCharacterType.*">
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
<form:hidden path="typeId"/>

<table class="detail" width="70%">
	<tbody class="window" >
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="alCharacterType.parentId"/></span></th>
			<td>
				<c:if test="${not empty parentAlCharacterType.typeId}">
					<span class="textbox"><jecs:locale key="${parentAlCharacterType.typeName }"/></span>
					<input type="hidden" name="parentId" value="${parentAlCharacterType.typeId }"/>
				</c:if>
				<c:if test="${empty parentAlCharacterType.typeId}">
					<span class="textbox"><jecs:locale key="alCharacterType.allType"/></span>
					<input type="hidden" name="parentId" value=""/>
				</c:if>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="alCharacterType.typeName"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="typeName" id="typeName" size="60" cssClass="textbox-text"/>
				</span>
			</td>
		</tr>
    
		<tr>
			<td class="command" colspan="4" align="center">
				<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
				</jecs:power>
				<c:if test="${not empty alCharacterType.typeId}">
					<jecs:power powerCode="deleteAlCharacterType">
					<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form, 'AlCharacterType')"><jecs:locale key="operation.button.delete"/></button>
					</jecs:power>
				</c:if>
				<button type="button" class="btn btn_sele" name="close" onclick="window.close();"><jecs:locale key="operation.button.close"/></button>
				<input type="hidden" name="strAction" value="${param.strAction }"/>
			</td>
		</tr>
	</tbody>
</table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('alCharacterTypeForm'));
</script>

<v:javascript formName="alCharacterType" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
