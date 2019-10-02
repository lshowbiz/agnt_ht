<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jsysResourceDetail.title"/></title>
<content tag="heading"><jecs:locale key="jsysResourceDetail.heading"/></content>

<script language="javascript" src="scripts/validate.js"></script>

<form:form commandName="jsysResource" method="post" action="editJsysResource.html" onsubmit="return validateJsysResource(this)" id="jsysResourceForm">

<spring:bind path="jsysResource.*">
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

<table class='detail' width="70%" >
<form:hidden path="resId"/>
<form:hidden path="parentId"/>
	<tbody class="window" >
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="jsysResource.parentId"/></span></th>
			<td>
				<!--form:errors path="parentId" cssClass="fieldError"/-->
				<!--<form:input path="parentId" id="parentId" cssClass="text medium"/>-->
				
				<span id="spnResourceName"><span class="textbox"><jecs:locale key="${parentSysResource.resName}"/></span></span>
				<input type="button" value="<jecs:locale key="button.select"/>" class="btn btn_sele" onclick="selectParentModule(this.form)">
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="jsysResource.resCode"/></span></th>
			<td>
				<span class="textbox">
				<!--form:errors path="resCode" cssClass="fieldError"/-->
				<form:input path="resCode" id="resCode" size="30" cssClass="textbox-text"/>
				</span>
			</td>
		</tr>

		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="jsysResource.resName"/></span></th>
			<td>
				<span class="textbox">
				<!--form:errors path="resName" cssClass="fieldError"/-->
				<form:input path="resName" id="resName" size="30" cssClass="textbox-text"/>
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="jsysResource.resType"/></span></th>
			<td>
				<span class="textbox">
				<!--form:errors path="resType" cssClass="fieldError"/-->
				
				 <spring:bind path="jsysResource.resType">
					<jecs:list name="${status.expression}" listCode="module_type" value="${status.value}" defaultValue="0" styleClass="textbox-text"/>
				</spring:bind>
				</span>
			</td>
		</tr>

		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="jsysResource.sysType"/></span></th>
			<td>
				<span class="textbox">
				<!--form:errors path="sysType" cssClass="fieldError"/-->
				<spring:bind path="jsysResource.sysType">
					<jecs:list name="${status.expression}" listCode="sys_type" value="${status.value}" defaultValue="0" styleClass="textbox-text"/>
				</spring:bind>
				</span>
			</td>
			
			<th><span class="text-font-style-tc"><jecs:label  key="jsysResource.resUrl"/></span></th>
			<td>
				<span class="textbox">
				<!--form:errors path="resUrl" cssClass="fieldError"/-->
				<form:input path="resUrl" id="resUrl" size="60" cssClass="textbox-text"/>
				</span>
			</td>
		</tr>
		
		<tr class="edit_tr">
			
			<th><span class="text-font-style-tc"><jecs:label  key="jsysResource.resDes"/></span></th>
			<td>
				<span class="textbox">
				<!--form:errors path="resDes" cssClass="fieldError"/-->
				<form:input path="resDes" id="resDes" size="60"  cssClass="textbox-text"/>
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="jsysResource.orderNo"/></span></th>
			<td>
				<span class="textbox">
				<!--form:errors path="orderNo" cssClass="fieldError"/-->
				<form:input path="orderNo" id="orderNo" size="6"  cssClass="textbox-text"/>
				</span>
			</td>
		</tr>
		<tr class="edit_tr">
			
			<th><span class="text-font-style-tc"><jecs:label  key="jsysResource.active"/></span></th>
			<td>
				<!--form:errors path="active" cssClass="fieldError"/-->
				<!--<form:input path="active" id="active" cssClass="text medium"/>-->
				<span class="textbox">
				<spring:bind path="jsysResource.active">
					<jecs:list name="${status.expression}" listCode="yesno" value="${status.value}" defaultValue="1" styleClass="textbox-text"/>
				</spring:bind>
				</span>
			</td>
			
			<th><span class="text-font-style-tc"><jecs:label  key="jsysResource.validateFlag"/></span></th>
			<td>
				<!--form:errors path="validateFlag" cssClass="fieldError"/-->
				<span class="textbox">
				<spring:bind path="jsysResource.validateFlag">
					<jecs:list name="${status.expression}" listCode="yesno" value="${status.value}" defaultValue="1" styleClass="textbox-text"/>
				</spring:bind>
				</span>
			</td>
		</tr>

		
		<!-- 
		<tr><th>
			<jecs:label  key="jsysResource.treeIndex"/>
		</th>
		<td>
			
			<form:input path="treeIndex" id="treeIndex" cssClass="text medium"/>
		</td></tr>

		<tr><th>
			<jecs:label  key="jsysResource.treeLevel"/>
		</th>
		<td>
			
			<form:input path="treeLevel" id="treeLevel" cssClass="text medium"/>
		</td></tr>
		 
		
		<c:set var="buttons">

			<jecs:power powerCode="${param.strAction}">
					<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
			</jecs:power>
			<jecs:power powerCode="deleteJsysResource">
					<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JsysResource')" value="<jecs:locale key="button.delete"/>" />
			</jecs:power>
	</c:set>
	-->
		<tr>
			<td class="command" colspan="4" align="center">
				<jecs:power powerCode="${param.strAction}">
					<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
				</jecs:power>
				<jecs:power powerCode="deleteJsysResource">
					<c:if test="${not empty jsysResource.resId}">
					<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JsysResource')"><jecs:locale key="operation.button.delete"/></button>
					</c:if>
				</jecs:power>
				<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='jsysResources.html?parentId=${jsysResource.parentId}';"><jecs:locale key="operation.button.cancel"/></button>
				<input type="hidden" name="strAction" value="${param.strAction }"/>
			</td>
		</tr>
	</tbody>
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JsysResource')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    //Form.focusFirstElement($('jsysResourceForm'));
    
function selectParentModule(theForm){
	//open("/jecs/sys_select_module.html?action=sysSelectModule&moduleId="+theForm.parentId.value);
	var pars=new Array();
	pars[0]="<jecs:locale key="title.select.parent.resource"/>";
	pars[1]="jsysResource_select.html?action=jsysSelectResource&resId="+theForm.parentId.value;
	pars[2]=window;
	var ret=showDialogWin("<%=request.getContextPath()%>",pars,300,350,"yes");
	if(ret!=undefined && ret!=""){
		theForm.parentId.value=ret.resId;
		document.getElementById("spnResourceName").innerHTML=ret.resName;
	}
}
</script>

<v:javascript formName="jsysResource" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
