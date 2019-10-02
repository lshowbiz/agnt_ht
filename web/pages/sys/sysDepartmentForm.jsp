<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysDepartmentDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysDepartmentDetail.heading"/></content>

<script language="javascript" src="scripts/validate.js"></script>

<form:form commandName="sysDepartment" method="post" action="editSysDepartment.html" onsubmit="return validateSysDepartment(this)&&validateOthers(this)" id="sysDepartmentForm">

<spring:bind path="sysDepartment.*">
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

<table class="detail" width="70%">
<form:hidden path="departmentId"/>
	<tbody class="window" >
	<tr class="edit_tr">
	
		<th><span class="text-font-style-tc"><jecs:label key="fiPayBank.companyCode"/></span></th>
		<td>
			<span class="textbox">
				<form:hidden path="companyCode"/>${sysDepartment.companyCode }
			</span>
		</td>
		
		<th><span class="text-font-style-tc"><jecs:label key="sysDepartment.parentDepartmentName"/></span></th>
		<td>
			<span class="textbox">
			<form:hidden path="parentId"/>${parentSysDepartment.departmentName }
			</span>
		</td>
	</tr>
    <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="sysDepartment.departmentName"/></span></th>
		<td>
			<span class="textbox"><form:input path="departmentName" id="departmentName" cssClass="textbox-text"/></span>
		</td>
		<th><span class="text-font-style-tc"><jecs:label  key="sysDepartment.fullName"/></span></th>
		<td>
			<span class="textbox"><form:input path="fullName" id="fullName" cssClass="textbox-text"/></span>
		</td>
	</tr>
    <tr class="edit_tr">
		
		<th><span class="text-font-style-tc"><jecs:label  key="alCompany.phone"/></span></th>
		<td>
			<span class="textbox"><form:input path="tel" id="tel" cssClass="textbox-text"/></span>
		</td>
		
		<th><span class="text-font-style-tc"><jecs:label  key="sysDepartment.fax"/></span></th>
		<td>
			<span class="textbox"><form:input path="fax" id="fax" cssClass="textbox-text"/></span>
		</td>
	</tr>

    <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="sysModule.orderNo"/></span></th>
		<td>
			<span class="textbox"><form:input path="orderNo" id="orderNo" size="6" cssClass="textbox-text"/></span>
		</td>
		<th><span class="text-font-style-tc"><jecs:label key="login.userType.console"/></span></th>
	    <td><span class="textbox"><form:input path="allowedUser" id="allowedUser" size="50" cssClass="textbox-text"/></span></td>
	</tr>
    
   
	<tr>
	    <th><jecs:label key="fiBankbookJournal.notes"/></th>
	    <td><jecs:locale key="common.department.seprate.desc"/></td>
	</tr>
    
    <tr>
		<td class="command" colspan="4" align="center">
			<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
			</jecs:power>
			<jecs:power powerCode="deleteSysDepartment">
				<c:if test="${not empty sysDepartment.departmentId}">
				<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysDepartment')"><jecs:locale key="operation.button.delete"/></button>
				</c:if>
			</jecs:power>
			<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='sysDepartments.html?companyCode=${sysDepartment.companyCode }&parentId=${sysDepartment.parentId }'"><jecs:locale key="operation.button.cancel"/></button>
			<input type="hidden" name="strAction" value="${param.strAction }"/>
		</td>
	</tr>

</table>
</form:form>

<script type="text/javascript">
Form.focusFirstElement($('sysDepartmentForm'));

function validateOthers(theForm){
	if(theForm.orderNo.value!="" && !isUnsignedInteger(theForm.orderNo.value)){
   		alert("<jecs:locale key="sysModule.orderNo.error"/>");
   		theForm.orderNo.focus();
   		return false;
   	}
    return true;
}
</script>

<v:javascript formName="sysDepartment" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>