<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<title><jecs:locale key="foundationItemDetail.title" />
</title>
<content tag="heading">
<jecs:locale key="foundationItemDetail.heading" />
</content>
<script language="javascript" src="scripts/validate.js"></script>

<spring:bind path="foundationItem.*">
	<c:if test="${not empty status.errorMessages}">
		<div class="error">
			<c:forEach var="error" items="${status.errorMessages}">
				<img src="<c:url value="/images/iconWarning.gif"/>"
					alt="<jecs:locale key="icon.warning"/>" class="icon" />
				<c:out value="${error}" escapeXml="false" />
				<br />
			</c:forEach>
		</div>
	</c:if>
</spring:bind>
<form:form commandName="foundationItem" method="post"
	action="editFoundationItem.html"
	onsubmit="return validateFoundationItem(this)&&validateOthers(this);" id="foundationItemForm" >


	<input type="hidden" name="strAction" value="${param.strAction }" />


	<table class='detail' width="70%">
		<tbody class="window">
			<form:hidden path="fiId" />
	
	<%--
	
		<tr class="edit_tr">
			<th>
				<span class="text-font-style-tc"><jecs:label  key="pmProduct.productName"/></span>
			</th>
			<td>
				<span class="textbox"><form:input path="productName" id="productName" cssClass="textbox-text" /></span>
			</td>
		</tr>
	 --%>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><jecs:label key="foundationItem.name" /></span>
				</th>
				<td>
					<!--form:errors path="name" cssClass="fieldError"/-->
					<span class="textbox"><form:input path="name" id="name" cssClass="textbox-text" /></span>
				</td>
			
				<th>
					<span class="text-font-style-tc"><jecs:label key="foundationItem.amount" /></span>
				</th>
				<td>
					<!--form:errors path="amount" cssClass="fieldError"/-->
					<span class="textbox"><form:input path="amount" id="amount" cssClass="textbox-text" /></span>
				</td>
			</tr>
	
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><jecs:label key="foundationItem.unit" /></span>
				</th>
				<td>
					<!--form:errors path="unit" cssClass="fieldError"/-->
					<span class="textbox"><form:input path="unit" id="unit" cssClass="textbox-text" /></span>
				</td>
			
				<th>
					<span class="text-font-style-tc"><jecs:label key="foundationItem.type" /></span>
				</th>
				<td> 
					<span class="textbox"><jecs:list styleClass="textbox-text" listCode="foundation.type" name="type" id="type"
							showBlankLine="false" value="${foundationItem.type}"
							defaultValue="0" /></span>
				</td>
			</tr>
			
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc"><jecs:label key="foundationItem.status" /></span>
				</th>
				<td> 
					<span class="textbox"><jecs:list styleClass="textbox-text" listCode="jmimemberteam.status" name="status" id="status"
							showBlankLine="false" value="${foundationItem.status}"
							defaultValue="0" /></span>
				</td>
				<th>
					<span class="text-font-style-tc"><jecs:label key="foundationItem.field1" /></span>
				</th>
				<td align="left"> 
					<span class="textbox"><form:input path="field1" id="field1" cssClass="textbox-text" /></span>
				</td>
			</tr>
			
			<tr>
				<th>
					<span class="text-font-style-tc"><jecs:label key="foundationItem.field2" /></span>
				</th>
				<td align="left"> 
					<span class="textbox"><form:input path="field2" id="field2" cssClass="textbox-text" /></span>
				</td>
			</tr>
	
	<%--
		<tr class="edit_tr">
				
			<th>
				<span class="text-font-style-tc-tare"><jecs:label  key="busi.common.remark"/></span>
			</th>
			<td  colspan="3">
			    <span class="text-font-style-tc-right"><form:textarea  cssClass="textarea_border" path="remark" id="remark"   /></span>
					
			</td>
		</tr>
	 --%>
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare"><jecs:label key="foundationItem.remark" /></span>
				</th>
				<td colspan="3">
					<!--form:errors path="remark" cssClass="fieldError"/--> 
					<span class="text-font-style-tc-right"><form:textarea cssClass="textarea_border" path="remark" id="remark"/></span>
				</td>
			</tr>
			
			<tr class="edit_tr">
				<th>
					<span class="text-font-style-tc-tare"><jecs:label key="foundationItem.prompt" /></span>
				</th>
				<td colspan="3">
					<!--form:errors path="remark" cssClass="fieldError"/--> 
					<span class="text-font-style-tc-right"><form:textarea cssClass="textarea_border" path="prompt" id="prompt"/></span>
				</td>
			</tr>
			
			
		<%--
			<tr>		
			    <td class="command" colspan="4" align="center">
			    	<c:out value="${buttons}" escapeXml="false"/>
			    </td>
    		</tr>
		 --%>
		 <%-- 
		<tr><th>
	        <jecs:label key="sysOperationLog.moduleName"/>
	    </th>
	    <td align="left">
	        
	        
	        <input type="submit" class="button" name="save"	onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
	        
	        
			<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
	    </td></tr>
	    --%>
	    
	    	<tr>		
			    <td class="command" colspan="4" align="center">
			    	<button type="submit" class="btn btn_sele" name="save"	onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
	        
					<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()"><jecs:locale key="operation.button.return"/></button>
			    </td>
    		</tr>
	    
	</tbody>
</table>
</form:form>

<%-- 
<form:form commandName="foundationItem" method="post"
	action="editFoundationItem.html"
	onsubmit="return validateFoundationItem(this)&&validateOthers(this);" id="foundationItemForm" >


	<input type="hidden" name="strAction" value="${param.strAction }" />


	<table class='detail' width="100%">

		<form:hidden path="fiId" />

		<tr>
			<th>
				<jecs:label key="foundationItem.name" />
			</th>
			<td align="left">
				<!--form:errors path="name" cssClass="fieldError"/-->
				<form:input path="name" id="name" cssClass="text medium" />
			</td>
		</tr>

		<tr>
			<th>
				<jecs:label key="foundationItem.amount" />
			</th>
			<td align="left">
				<!--form:errors path="amount" cssClass="fieldError"/-->
				<form:input path="amount" id="amount" cssClass="text medium" />
			</td>
		</tr>

		<tr>
			<th>
				<jecs:label key="foundationItem.unit" />
			</th>
			<td align="left">
				<!--form:errors path="unit" cssClass="fieldError"/-->
				<form:input path="unit" id="unit" cssClass="text medium" />
			</td>
		</tr>

		<tr>
			<th>
				<jecs:label key="foundationItem.type" />
			</th>
			<td align="left"> 
				<jecs:list listCode="foundation.type" name="type" id="type"
						showBlankLine="false" value="${foundationItem.type}"
						defaultValue="0" />
			</td>
		</tr>
		
		<tr>
			<th>
				<jecs:label key="foundationItem.status" />
			</th>
			<td align="left"> 
				<jecs:list listCode="jmimemberteam.status" name="status" id="status"
						showBlankLine="false" value="${foundationItem.status}"
						defaultValue="0" />
			</td>
		</tr>
		
		<tr>
			<th>
				<jecs:label key="foundationItem.field1" />
			</th>
			<td align="left"> 
				<form:input path="field1" id="field1" cssClass="text medium" />
			</td>
		</tr>
		
		<tr>
			<th>
				<jecs:label key="foundationItem.field2" />
			</th>
			<td align="left"> 
				<form:input path="field2" id="field2" cssClass="text medium" />
			</td>
		</tr>

		<tr>
			<th>
				<jecs:label key="foundationItem.remark" />
			</th>
			<td align="left">
				<!--form:errors path="remark" cssClass="fieldError"/--> 
				<form:textarea cols='70' rows='3' path="remark" id="remark"
								cssClass="text medium"/>
			</td>
		</tr>
		
		<tr>
			<th>
				<jecs:label key="foundationItem.prompt" />
			</th>
			<td align="left">
				<!--form:errors path="remark" cssClass="fieldError"/--> 
				<form:textarea cols='70' rows='3' path="prompt" id="prompt"
								cssClass="text medium"/>
			</td>
		</tr>
		
		
	<tr><th>
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td align="left">
        
        
        <input type="submit" class="button" name="save"	onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        
        
		<input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />
    </td></tr>
</table>
</form:form>

--%>
<script type="text/javascript">
    Form.focusFirstElement($('foundationItemForm'));
    function validateOthers(theForm){
  		
		if(theForm.elements['name'].value==""){
			alert("请输入项目名称");
			theForm.elements['name'].focus();
			return false;
		}
		
		if(theForm.amount.value=="" || !isUnsignedNumeric(theForm.amount.value)){
			alert("金额输入格式不正确！");
			theForm.amount.focus();
			return false;
		}
		if(theForm.amount.value<=0){
			alert("金额输入格式不正确！");
			theForm.amount.focus();
			return false;
		}
		
		return isFormPosted();
	}
</script>

<v:javascript formName="foundationItem" cdata="false"
	dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
