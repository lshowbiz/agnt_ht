<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jmiCustomerLevelNoteDetail.title"/></title>
<content tag="heading"><jecs:locale key="jmiCustomerLevelNoteDetail.heading"/></content>

<c:set var="buttons">
	<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false">
		<jecs:locale key="operation.button.save"/>
	</button>
	<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()">
		<jecs:locale key="operation.button.return"/>
	</button>
</c:set>

<spring:bind path="jmiCustomerLevelNote.*">
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

<form:form commandName="jmiCustomerLevelNote" method="post" action="editJmiCustomerLevelNote.html"  id="jmiCustomerLevelNoteForm" onsubmit="if(isFormPosted()){return true;}{return false;}">
<%-- 
<div class="searchBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>
<table class='detail' width="70%">
<tbody class="window">
<form:hidden path="id"/>

    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><jecs:label  key="miMember.memberNo"/>
    </span></th>
    <td>
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="userCode" id="userCode" cssClass="textbox-text"/></span>
    </td><th>
        <span class="text-font-style-tc"><jecs:label  key="miMemberUpgradeNote.newCard"/>
    </span></th>
    <td>
        <!--form:errors path="newCustomerLevel" cssClass="fieldError"/-->
         <span class="textbox">
         	<jecs:list styleClass="textbox-text" name="newCustomerLevel" listCode="bd.customerlevel" 
         		value="${param.newCustomerLevel}" defaultValue="" showBlankLine="true"/>
         </span>	
    </td></tr>


    

    <tr class="edit_tr"><th>
        <span class="text-font-style-tc-tare"><jecs:label  key="busi.common.remark"/>
    </span></th>
    <td colspan="3">
        <span class="text-font-style-tc-right"><form:textarea path="remark" cssClass="textarea_border"/></span>
    </td></tr>
	<tr>
		<td class="command" colspan="4" align="center">
			<c:out value="${buttons}" escapeXml="false"/>
		</td>
	</tr>
</tbody>
</table>

</form:form>
