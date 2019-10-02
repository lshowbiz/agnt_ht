<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysModuleDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysModuleDetail.heading"/></content>

<script language="javascript" src="scripts/validate.js"></script>

<form:form commandName="sysModule" method="post" action="editSysModule.html" onsubmit="return validateSysModule(this)&&validateOthers(this)" id="sysModuleForm">

<spring:bind path="sysModule.*">
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

<table class='detail' width="70%" >
	<tbody class="window" >

	<form:hidden path="moduleId"/>
	<form:hidden path="parentId"/>

		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="sysModule.parent"/></span></th>
			<td>
				<span id="spnModuleName"><span class="textbox"><jecs:locale key="${parentSysModule.moduleName }"/></span></span>
				<input type="button" value="<jecs:locale key="button.select"/>" class="btn btn_sele" onclick="selectParentModule(this.form)">
			</td>
			<th><span class="text-font-style-tc"><jecs:label key="sysModule.moduleCode"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="moduleCode" id="moduleCode" size="30" cssClass="textbox-text"/>
				</span>
			</td>
		</tr>

		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="sysModule.moduleName"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="moduleName" id="moduleName" size="30" cssClass="textbox-text"/>
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="sysModule.moduleDesc"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="moduleDesc" id="moduleDesc" size="60" cssClass="textbox-text"/>
				</span>
			</td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="sysModule.moduleType"/></span></th>
			<td>
				<span class="textbox">
				<spring:bind path="sysModule.moduleType">
					<jecs:list name="${status.expression}" listCode="module_type" value="${status.value}" defaultValue="0" styleClass="textbox-text"/>
				</spring:bind>
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="sysModule.linkUrl"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="linkUrl" id="linkUrl" size="60" cssClass="textbox-text"/>
				<span>
			</td>
		</tr>

		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><label for="sysModule.otherUrl1"><jecs:locale key="sysModule.otherUrl"/>1:</label></span></th>
			<td>
				<span class="textbox">
				<form:input path="otherUrl1" id="otherUrl1" size="60" cssClass="textbox-text"/>
				</span>
			</td>
			<th><span class="text-font-style-tc"><label for="sysModule.otherUrl2"><jecs:locale key="sysModule.otherUrl"/>2:</label></span></th>
			<td>
				<span class="textbox">
				<form:input path="otherUrl2" id="otherUrl2" size="60" cssClass="textbox-text"/>
				</span>
			</td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><label for="sysModule.otherUrl3"><jecs:locale key="sysModule.otherUrl"/>3:</label></span></th>
			<td>
				<span class="textbox">
				<form:input path="otherUrl3" id="otherUrl3" size="60" cssClass="textbox-text"/>
				<span>
			</td>
			<th><span class="text-font-style-tc"><label for="sysModule.otherUrl4"><jecs:locale key="sysModule.otherUrl"/>4:</label></span></th>
			<td>
				<span class="textbox">
				<form:input path="otherUrl4" id="otherUrl4" size="60" cssClass="textbox-text"/>
				<span>
			</td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><label for="sysModule.otherUrl5"><jecs:locale key="sysModule.otherUrl"/>5:</label></span></th>
			<td>
				<span class="textbox">
				<form:input path="otherUrl5" id="otherUrl5" size="60" cssClass="textbox-text"/>
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="sysModule.orderNo"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="orderNo" id="orderNo" size="6" cssClass="textbox-text"/>
				</span>
			</td>
		</tr>
		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="sysModule.isActive"/></span></th>
			<td>
				<span class="textbox">
				<spring:bind path="sysModule.isActive">
					<jecs:list name="${status.expression}" listCode="yesno" value="${status.value}" defaultValue="1" styleClass="textbox-text"/>
				</spring:bind>
				</span>
			</td>
			
			<th><span class="text-font-style-tc"><jecs:label  key="sysModule.isValidate"/></span></th>
			<td>
				<span class="textbox">
				<spring:bind path="sysModule.isValidate">
					<jecs:list name="${status.expression}" listCode="yesno" value="${status.value}" defaultValue="1" styleClass="textbox-text"/>
				</spring:bind>
				</span>
			</td>
		</tr>

		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="sysUsrTypePow.userType"/></span></th>
			<td>
				<span class="textbox">
				<c:forEach items="${userTypes}" var="userTypeVar">
					<c:set var="isChecked" value="false"/>
					<c:forEach items="${sysModule.sysUsrTypePows}" var="sysUsrTypePowVar">
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
					
				</c:forEach>
				</span>
			</td>
		</tr>
		
		<tr class="edit_tr">
			<th align="left" ><span class="edit_tr_span"><jecs:label  key="sysCompanyPow.companyCode"/><span></th>
			<td align="left"  colspan="3">
				
				<c:forEach items="${alCompanys}" var="alCompanyVar">
					<c:set var="isChecked" value="false"/>
					<c:forEach items="${sysModule.sysCompanyPows}" var="sysCompanyPowVar">
						<c:if test="${sysCompanyPowVar.companyCode==alCompanyVar.companyCode}">
							<c:set var="isChecked" value="true"/>
						</c:if>
					</c:forEach>
					<c:if test="${isChecked=='true'}">
					<div class="edit_tr_div">
					<input type="checkbox" name="companyCode" value="${alCompanyVar.companyCode }" checked="checked" id="companyCode_${alCompanyVar.companyCode }"/>
					${alCompanyVar.companyCode } - ${alCompanyVar.companyName }
					</div>
					</c:if>
					<c:if test="${isChecked=='false'}">
					<div class="edit_tr_div">
					<input type="checkbox" name="companyCode" value="${alCompanyVar.companyCode }" id="companyCode_${alCompanyVar.companyCode }"/>
					${alCompanyVar.companyCode } - ${alCompanyVar.companyName }
					</div>
					</c:if>    	
					
					
				</c:forEach>
			</td>
		</tr>
		
		<tr>
			
			<td class="command" colspan="4" align="center">
				<jecs:power powerCode="${param.strAction}">
					<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
				</jecs:power>
				<jecs:power powerCode="deleteSysModule">
					<c:if test="${not empty sysModule.moduleId}">
					<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysModule')"><jecs:locale key="operation.button.delete"/></button>
					</c:if>
				</jecs:power>
				<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='sysModules.html?parentId=${sysModule.parentId }';"><jecs:locale key="operation.button.cancel"/></button>
				<input type="hidden" name="strAction" value="${param.strAction }"/>
			</td>
		</tr>
	</tbody>
</table>
<!--/fieldset-->
</form:form>

<script type="text/javascript">
function validateOthers(theForm){
	if(theForm.orderNo.value!="" && !isUnsignedInteger(theForm.orderNo.value)){
   		alert("<jecs:locale key="sysModule.orderNo.error"/>");
   		theForm.orderNo.focus();
   		return false;
   	}
    return isFormPosted();
}

function selectAllCompany(theForm){
	var companyCodes=document.getElementsByName("companyCode");
	if(companyCodes!=undefined && companyCodes.length>0){
		for(var i=0;i<companyCodes.length;i++){
			companyCodes[i].checked=theForm.selectAll.checked;
		}
	}
}

function selectParentModule(theForm){
	//open("/jecs/sys_select_module.html?action=sysSelectModule&moduleId="+theForm.parentId.value);
	var pars=new Array();
	pars[0]="<jecs:locale key="title.select.parent.module"/>";
	pars[1]="sys_select_module.html?action=sysSelectModule&moduleId="+theForm.parentId.value;
	pars[2]=window;
	var ret=showDialogWin("<%=request.getContextPath()%>",pars,300,350,"yes");
	if(ret!=undefined && ret!=""){
		theForm.parentId.value=ret.moduleId;
		document.getElementById("spnModuleName").innerHTML=ret.moduleName;
	}
}
</script>

<v:javascript formName="sysModule" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>