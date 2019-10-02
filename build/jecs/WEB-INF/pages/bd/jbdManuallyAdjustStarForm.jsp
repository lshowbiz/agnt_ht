<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdManuallyAdjustStarDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdManuallyAdjustStarDetail.heading"/></content>



<spring:bind path="jbdManuallyAdjustStar.*">
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

<form:form commandName="jbdManuallyAdjustStar" method="post" action="editJbdManuallyAdjustStar.html"  id="jbdManuallyAdjustStarForm">

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

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="jbdMemberLinkCalcHist.honorStar"/>
    </span></th>
    <td><span class="textbox">
				<jecs:list styleClass="textbox-text" name="honorStar" listCode="honor.star.zero" value="${jbdManuallyAdjustStar.honorStar}" defaultValue="" showBlankLine="true"/>
    </span></td>

	<th><span class="text-font-style-tc">
        <jecs:label  key="jbdMemberLinkCalcHist.passStar"/>
    </span></th>
    <td><span class="textbox">
				<jecs:list styleClass="textbox-text" name="passStar" listCode="pass.star.zero" value="${jbdManuallyAdjustStar.passStar}" defaultValue="" showBlankLine="true"/>
    </span></td></tr>

    <tr class="edit_tr">
    <th><span class="text-font-style-tc">
        <jecs:label  key="jbdMemberLinkCalcHist.honorGrade"/>
    </span></th>
    <td><span class="textbox">
				<jecs:list styleClass="textbox-text" name="honorGrade" listCode="honor.grade.zero" value="${jbdManuallyAdjustStar.honorGrade}" defaultValue="" showBlankLine="true"/>
    </span></td>
	
	<th><span class="text-font-style-tc">
        <jecs:label  key="jbdMemberLinkCalcHist.passGrade"/>
    </span></th>
    <td><span class="textbox">
				<jecs:list styleClass="textbox-text" name="passGrade" listCode="pass.grade.zero" value="${jbdManuallyAdjustStar.passGrade}" defaultValue="" showBlankLine="true"/>
    </span></td></tr>

	<tr class="edit_tr">
	<%-- <th class="command"><jecs:label key="sysOperationLog.moduleName" /></span></th> --%>
	<td class="command" align="center" colspan="4">
		
		<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" >
					<jecs:locale key="operation.button.save"/>
				</button>
		</jecs:power>

		<jecs:power powerCode="deleteJbdManuallyAdjustStar">
				<c:if test="${not empty jbdManuallyAdjustStar.id}">
					<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JbdManuallyAdjustStar')" >
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
