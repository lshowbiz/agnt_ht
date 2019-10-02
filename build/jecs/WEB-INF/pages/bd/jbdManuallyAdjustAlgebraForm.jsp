<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdManuallyAdjustAlgebraDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdManuallyAdjustAlgebraDetail.heading"/></content>


<spring:bind path="jbdManuallyAdjustAlgebra.*">
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

<form:form commandName="jbdManuallyAdjustAlgebra" method="post" action="editJbdManuallyAdjustAlgebra.html"  id="jbdManuallyAdjustAlgebraForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%">

<tbody class="window">
<form:hidden path="id"/>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="bdBounsDeduct.wweek"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="wweek" cssClass="fieldError"/-->
        <form:input cssClass="textbox-text" path="wweek" id="wweek" />
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="miMember.memberNo"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="userCode" cssClass="fieldError"/-->
        <form:input cssClass="textbox-text" path="userCode" id="userCode" />
    </span></td></tr>

    <tr class="edit_tr"><th><span class="text-font-style-tc">
        <jecs:label  key="bdMemberBounsCalcSell.level"/>
    </span></th>
    <td><span class="textbox">
        <!--form:errors path="algebra" cssClass="fieldError"/-->
        <jecs:list styleClass="textbox-text"  name="algebra" listCode="algebra" value="${jbdManuallyAdjustAlgebra.algebra}" defaultValue="" showBlankLine="true"/>
    </span></td></tr>
    
    <tr>
    	<td class="command" align="center" colspan="4">
    			<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" >
					<jecs:locale key="operation.button.save"/>
				</button>
			</jecs:power>
		<jecs:power powerCode="deleteJbdManuallyAdjustAlgebra">
		<c:if test="${not empty jbdManuallyAdjustStar.id}">
				<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdManuallyAdjustAlgebra')">
					<jecs:locale key="operation.button.delete"/>
				</button>
				</c:if>
		</jecs:power>
		
		
		<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()" >
			<jecs:locale key="operation.button.return"/>
		</button>
	
	
    	</td>
    </tr>
</tbody>
</table>
</form:form>
