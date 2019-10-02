<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysManagerDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysManagerDetail.heading"/></content>

<script language="javascript" src="scripts/validate.js"></script>

<form:form commandName="sysManager" method="post" action="editSysManager.html" onsubmit="return validateOthers(this)" id="sysManagerForm">
<input type="hidden" name="modifyType" value="${param.modifyType }" />
<spring:bind path="sysManager.*">
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
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="bdReconsumMoneyReport.companyCH"/></span></th>
			<td>
				<form:hidden path="companyCode"/>
				<span id="spnCompanyCode"><span class="textbox">${sysManager.companyCode }</span></span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="sysDepartment.departmentName"/></span></th>
			<td>
				<form:hidden path="departmentId"/>
				<span id="spnDepartmentName">${sysDepartment.departmentName }</span>
				<button type="button" value="" class="btn btn_sele" onclick="selectDepartment(this.form)"><jecs:locale key="button.select"/> </button>  
			</td>
		</tr>
		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="sysUser.userCode"/></span></th>
			<td>
				<span class="textbox">
				<c:if test="${param.strAction!='editSysManager'}">
				<form:input path="userCode" cssClass="textbox-text"/>
				</c:if>
				<c:if test="${param.strAction=='editSysManager'}">
				<form:input path="userCode" readonly="true"  cssClass="textbox-text"/>
				</c:if>   
				</span>
			</td>
			<th><span class="text-font-style-tc"><label for="userName" class="required"><span class="req">*</span> <jecs:locale  key="sysUser.userName"/>:</label></span></th>
			<td>
				<span class="textbox">
					<form:input path="sysUser.userName" cssClass="textbox-text"/>    
				</span>
			</td>
			
		</tr>
		
		<c:if test="${param.strAction=='addSysManager'}">
			<tr class="edit_tr">
				<th><span class="text-font-style-tc"><jecs:label key="sysUser.password"/></span></th>
				<td>
					<span class="textbox">
					<form:password path="sysUser.password" cssClass="textbox-text"/>
					</span>
					&nbsp;<jecs:locale key="sys.menuDefaultLimt"/>:<font color='red' size='3' ><b>${sysManager.sysUser.password}</b></font>    
				</td>
				<th>
					<span class="text-font-style-tc">
						<label for="repeatPassword" class="required"><span class="req">*</span> <jecs:locale  key="sysUser.repeatPassword"/>:</label>
					</span>
				</th>
				<td>
					<span class="textbox">
					<input type="password" value="${sysManager.sysUser.password}" name="repeatPassword" id="repeatPassword" Class="textbox-text"/>  
					</span>
				</td>
			</tr>
			
		</c:if>

		<tr class="edit_tr">
			<th>
				<span class="text-font-style-tc">
					<label for="email" class="required"><span class="req">*</span> <jecs:locale  key="sysUser.email"/>:</label>
				</span>
			</th>
			<td>
				<span class="textbox">
				<form:input path="email" id="email" cssClass="textbox-text"/> 
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="sysUser.tel"/></span></th>
			<td>
				<span class="textbox"><form:input path="tel" id="tel" cssClass="textbox-text"/></span> 
			</td>
		</tr>

		<tr class="edit_tr">
			<th>
			<jecs:power powerCode="iphoneChange">
				<span class="text-font-style-tc"><jecs:label  key="sysUser.mobile"/></span>
			</jecs:power>
			</th>
			<td>
			<jecs:power powerCode="iphoneChange">
				<span class="textbox">
				 <form:input path="mobile" id="mobile" cssClass="textbox-text"/>  
				</span>
				</jecs:power>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="sysDepartment.fax"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="fax" id="fax" cssClass="textbox-text"/>    
				</span>
			</td>
		</tr>

		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><label for="email" class="required"><span class="req">*</span> <jecs:locale  key="alCompany.countryCode"/>:</label></span></th>
			<td>
				<span class="textbox">
				<form:select path="countryCode" items="${alCountrys}" itemLabel="countryName" itemValue="countryCode" cssClass="textbox-text"/> 
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="sysUser.address"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="address" id="address" size="50" cssClass="textbox-text"/>  
				</span>
			</td>
		</tr>
		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label key="sysModule.orderNo"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="depOrder" id="depOrder" size="6" cssClass="textbox-text"/> 
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="sysUser.defCharacterCoding"/></span></th>
			<td>
				<span class="textbox">
				<form:select  cssClass="textbox-text" path="sysUser.defCharacterCoding" items="${alCharacterCodings }" itemLabel="characterName" itemValue="characterCode"/>
				</span>
			</td>
		</tr>
		
		<c:if test="${sysManager.userCode!='root'}">
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="sysUser.state"/></span></th>
			<td>
				<span class="textbox">
				<spring:bind path="sysManager.sysUser.state">
					<jecs:list name="${status.expression}" listCode="yesno" value="${status.value}" defaultValue="1"  styleClass="textbox-text"/>
				</spring:bind> 
				</span>
			</td>
		</tr>
		</c:if>
		
		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:locale key="title.check.login.ip"/>:</span></th>
			<td>
				<span class="textbox"><input type="checkbox" name="ipCheck" id="ipCheck"<c:if test="${sysManager.sysUser.ipCheck=='1' }"> checked</c:if> value="1"/></span>
			</td>
		</tr>
		
		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="title.allowed.ip"/></span></th>
			<td>
				<table border="0" class="detailSub">
					<tr>
						<td>
							<select name="allowedIps" size="6" id="allowedIps" Class="textbox-text">
							<c:forEach items="${sysUserIps}" var="sysUserIpVar">
								<option value="${sysUserIpVar.ipAddress}">${sysUserIpVar.ipAddress}</option>
							</c:forEach>
							</select>
						</td>
						<td>
							<input type="button" name="button" id="button" class="btn btn_sele" value="<jecs:locale key="operation.button.add"/>" onclick="addNewIP(this.form)"/>
							<input type="hidden" name="allowedIpStr"/>
						</td>
						<td><span class="textbox"><input type="text" name="newIP" id="newIP" class="textbox-text"/></span></td>
						<td>
						
							<input type="button" name="button2" id="button2" class="btn btn_sele" value="<jecs:locale key="operation.button.delete"/>" onclick="removeIP(this.form)"/>
						
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td class="command" colspan="4" align="center">
				<jecs:power powerCode="${param.strAction}">
					<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
				</jecs:power>
				<c:if test="${empty param.modifyType}">
				<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='sysManagers.html?companyCode=${sysManager.companyCode }&departmentId=${sysDepartment.departmentId }&needReload=1'"><jecs:locale key="operation.button.cancel"/>
				</button>
				</c:if>
				
				<c:if test="${param.modifyType=='account'}">
				<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='sysAccounts.html?needReload=1'"><jecs:locale key="operation.button.cancel"/></button>
				</c:if>
			
				<input type="hidden" name="strAction" value="${param.strAction }"/>
			</td>
		</tr>
	</tbody>
</table>

</form:form>

<script type="text/javascript">
function validateOthers(theForm){
	if(theForm.companyCode.value=="" || theForm.departmentId.value==""){
		alert("<jecs:locale key="info.select.company.or.department"/>");
		return false;
	}
	
	if(theForm.userCode.value=="" || !isValidateUserCode(theForm.userCode.value) || theForm.userCode.value.length>20){
		alert("<jecs:locale key="sysUser.userCode.required"/>");
		theForm.userCode.focus();
		return false;
	}
	
	if(document.getElementById("sysUser.userName").value==""){
		alert("<jecs:locale key="sysUser.userName.required"/>");
		document.getElementById("sysUser.userName").focus();
		return false;
	}
	
	<c:if test="${param.strAction=='addSysManager'}">
	if(document.getElementById("sysUser.password").value==""){
		alert("<jecs:locale key="sysUser.password.required"/>");
		document.getElementById("sysUser.password").focus();
		return false;
	}
	
	if(theForm.repeatPassword.value==""){
		alert("<jecs:locale key="sysUser.repeatPassword.required"/>");
		theForm.repeatPassword.focus();
		return false;
	}
	
	if(document.getElementById("sysUser.password").value!=theForm.repeatPassword.value){
		alert("<jecs:locale key="error.password.not.accord"/>");
		document.getElementById("sysUser.password").focus();
		return false;
	}
	</c:if>
	
	if(theForm.email.value==""){
		alert("<jecs:locale key="sysUser.email.required"/>");
		theForm.email.focus();
		return false;
	}
	
	if(theForm.countryCode.value==""){
		alert("<jecs:locale key="sysManager.countryCode.required"/>");
		theForm.countryCode.focus();
		return false;
	}
	
	if(theForm.depOrder.value!="" && !isUnsignedInteger(theForm.depOrder.value)){
   		alert("<jecs:locale key="sysModule.orderNo.error"/>");
   		theForm.depOrder.focus();
   		return false;
   	}
   	
   	var ipStr="";
   	for(var i=0;i<theForm.allowedIps.options.length;i++){
   		if(i>0){
   			ipStr+=",";
   		}
   		ipStr+=theForm.allowedIps.options[i].value;
   	}
   	theForm.allowedIpStr.value=ipStr;
	return isFormPosted();
}

function selectDepartment(theForm){
	var pars=new Array();
	pars[0]="<jecs:locale key="title.select.department"/>";
	pars[1]="sys_select_department.html?strAction=sysSelectDepartment&departmentId="+theForm.departmentId.value;
	pars[2]=window;
	var ret=showDialogWin("<%=request.getContextPath()%>",pars,300,350,"yes");
	//if(ret!=undefined && ret!=""){
		theForm.companyCode.value=ret.companyCode;
		document.getElementById("spnCompanyCode").innerHTML=ret.companyCode;
		theForm.departmentId.value=ret.departmentId;
		document.getElementById("spnDepartmentName").innerHTML=ret.departmentName;
	//}
}

function addNewIP(theForm){
	if(theForm.newIP.value=="" || !javaValidIPAddress(theForm.newIP.value)){
		return;
	}
	var newOption=new Option(theForm.newIP.value,theForm.newIP.value);
	try{
        theForm.allowedIps.add(newOption);
    }catch(e){
        theForm.allowedIps.add(newOption, null);
    }
}

function removeIP(theForm){
	var selectedIndex=theForm.allowedIps.selectedIndex;
	if(selectedIndex>=0){
		theForm.allowedIps.remove(selectedIndex);
	}
}
</script>