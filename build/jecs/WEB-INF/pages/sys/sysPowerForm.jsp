<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysPowerDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysPowerDetail.heading"/></content>

<form:form commandName="sysPower" method="post" action="editSysPower.html" onsubmit="return validateSysPower(this)" id="sysPowerForm">

<div class="topNavBar">
	<jecs:locale key="sysModule.current.selected"/>:<jecs:locale key="${sysPower.sysModule.moduleName }"/>
</div>

<div id="titleBar">
	<jecs:power powerCode="${param.strAction}">
		<input type="submit" class="button" name="save"  onclick="bCancel=false"<jecs:locale:message key="operation.button.save"/>" />
	</jecs:power>
	<jecs:power powerCode="deleteSysPower">
		<c:if test="${not empty sysPower.powerId}">
			<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysPower')"<jecs:locale:message key="operation.button.delete"/>" />
		</c:if>
	</jecs:power>
	<input type="button" class="button" name="cancel" onclick="window.location='sysPowers.html?moduleId=${sysPower.sysModule.moduleId }'"<jecs:locale:message key="operation.button.cancel"/>" />
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<spring:bind path="sysPower.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
          <jecs:localefmt:message key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<table class='detail' width="100%">

<form:hidden path="powerId"/>
<input type="hidden" name="moduleId" value="${sysPower.sysModule.moduleId }"/>

    <tr><th>
        <jecs:label  key="sysPower.powerCode"/>
    </th>
    <td align="left" width="100%">
        <form:input path="powerCode" id="powerCode" />
    </td></tr>

    <tr><th>
        <jecs:label  key="sysPower.powerName"/>
    </th>
    <td align="left">
        <form:input path="powerName" id="powerName"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="sysPower.powerDesc"/>
    </th>
    <td align="left">
        <form:input path="powerDesc" id="powerDesc" size="60"/>
    </td></tr>
    
    <tr><th>
        <jecs:label key="sysPower.isDefault"/>
    </th>
    <td align="left">
    	<spring:bind path="sysPower.isDefault">
        	<jecs:list name="${status.expression}" listCode="yesno" value="${status.value}" defaultValue="1"/>
    	</spring:bind>
    </td></tr>
    
    <tr><th>
    	<jecs:label  key="sysUsrTypePow.userType"/>
    </th>
    <td align="left">
    	<c:forEach items="${userTypes}" var="userTypeVar">
    		<c:set var="isChecked" value="false"/>
    		<c:forEach items="${sysPower.sysUsrTypePows}" var="sysUsrTypePowVar">
    			<c:if test="${sysUsrTypePowVar.userType==userTypeVar.key}">
    				<c:set var="isChecked" value="true"/>
    			</c:if>
    		</c:forEach>
    		<c:if test="${isChecked=='true'}">
    		<input type="checkbox" name="userType" value="${userTypeVar.key }" checked="checked" class="checkbox" id="userType_${userTypeVar.key }"/>
    		</c:if>
    		<c:if test="${isChecked=='false'}">
    		<input type="checkbox" name="userType" value="${userTypeVar.key }" class="checkbox" id="userType_${userTypeVar.key }"/>
    		</c:if>    	
    		<label for="userType_${userTypeVar.key }"><jecs:locale key="${userTypeVar.value }"/></label>
    		<br>
    	</c:forEach>
    </td></tr>
    
    <tr><th>
        <jecs:label  key="sysListValue.exCompanyCode"/>
    </th>
    <td align="left">
    	<c:forEach items="${alCompanys}" var="alCompanyVar">
    		<c:set var="isChecked" value="false"/>
    		<c:forEach items="${sysPower.sysCompanyPows}" var="sysCompanyPowVar">
    			<c:if test="${sysCompanyPowVar.companyCode==alCompanyVar.companyCode}">
    				<c:set var="isChecked" value="true"/>
    			</c:if>
    		</c:forEach>
    		<c:if test="${isChecked=='true'}">
    		<input type="checkbox" name="companyCode" value="${alCompanyVar.companyCode }" checked="checked" class="checkbox" id="companyCode_${alCompanyVar.companyCode }"/>
    		</c:if>
    		<c:if test="${isChecked=='false'}">
    		<input type="checkbox" name="companyCode" value="${alCompanyVar.companyCode }" class="checkbox" id="companyCode_${alCompanyVar.companyCode }"/>
    		</c:if>    	
    		<label for="companyCode_${alCompanyVar.companyCode }">${alCompanyVar.companyCode } - ${alCompanyVar.companyName }</label>
    		<br>
    	</c:forEach>
    </td></tr>

</table>

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysPowerForm'));
</script>

<v:javascript formName="sysPower" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
