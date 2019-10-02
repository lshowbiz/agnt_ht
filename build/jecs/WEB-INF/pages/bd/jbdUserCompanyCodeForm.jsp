<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdUserCompanyCodeDetail.title"/></title>
<content tag="heading"><jecs:locale key="jbdUserCompanyCodeDetail.heading"/></content>

<c:set var="buttons">
	<jecs:power powerCode="${param.strAction}">
		<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false">
			<jecs:locale key="operation.button.save"/>
		</button>	
	<%-- 
	<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
	--%>
	</jecs:power>
</c:set>

<spring:bind path="jbdUserCompanyCode.*">
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

<form:form commandName="jbdUserCompanyCode" method="post" action="editJbdUserCompanyCode.html" onsubmit="return validateOthers(this);"  id="jbdUserCompanyCodeForm">
<%-- 
<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%">

<form:hidden path="id"/>
	<tbody class="window">
    <tr class="edit_tr">
    	<th>
        	<span class="text-font-style-tc"><jecs:label  key="miMember.memberNo"/></span>
    	</th>
	    <td>
	    	<span class="textbox">
	        <!--form:errors path="userCode" cssClass="fieldError"/-->
	        <form:input path="userCode" id="userCode" cssClass="textbox-text"/>
	       	</span>
	    </td>
	
		<th>
	        <span class="text-font-style-tc"><jecs:label  key="bdBounsDeduct.wweek"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	        <!--form:errors path="wweek" cssClass="fieldError"/-->
	        <form:input path="wweek" id="wweek" cssClass="textbox-text"/>
	        </span>
	    </td>
	</tr>

    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="jbdUserCompanyCode.newCompany"/></span>
	    </th>
	    <td>
	    	<span class="textbox">
	        <!--form:errors path="newCompany" cssClass="fieldError"/-->
			<jecs:company name="newCompany" selected="${jbdUserCompanyCode.newCompany }" 
			 prompt="" withAA="false" styleClass="textbox-text" />
			</span>
	    </td>
	</tr>
	<tr class="edit_tr">
		<td class="command" align="center" colspan="4"><c:out value="${buttons}" escapeXml="false"/>
			<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()">
				<jecs:locale key="operation.button.return"/>
			</button></td>
		
	</tr>
</table>
</form:form>

<script type="text/javascript">
function validateOthers(theForm){
	if(theForm.wweek.value=="" || theForm.wweek.value.length!=6 || !isUnsignedInteger(theForm.wweek.value)){
		alert("<jecs:locale key="week.isError"/>");
		theForm.wweek.focus();
		return false;
	}
	//setTimeout('showProgressBar()', 4000);
	return true;
}
</script>

