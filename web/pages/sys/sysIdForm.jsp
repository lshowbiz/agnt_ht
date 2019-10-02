<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysIdDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysIdDetail.heading"/></content>

<script language="javascript" src="scripts/validate.js"></script>
<!-- 装载日历JS -->
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form:form commandName="sysId" method="post" action="editSysId.html" onsubmit="return validateForm(this)" id="sysIdForm">

<spring:bind path="sysId.*">
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
<form:hidden path="id"/>
<table class='detail' width="70%" >
	<tbody class="window" >
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="sysId.idCode"/></span></th>
			<td>
				<span class="textbox">
				<c:if test="${not empty sysId.id }">
					<form:input path="idCode" id="idCode" cssClass="textbox-text" readonly="true"/>
				</c:if>
				<c:if test="${empty sysId.id }">
					<form:input path="idCode" id="idCode" cssClass="textbox-text"/>
				</c:if>   
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="sysId.idName"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="idName" id="idName" cssClass="textbox-text"/> 
				</span>
			</td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="sysId.idValue"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="idValue" id="idValue" cssClass="textbox-text"/>
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="alCompany.dateFormat"/></span></th>
			<td>
				<span class="textbox">
				<spring:bind path="sysId.dateFormat">
					<jecs:list styleClass="textbox-text" name="${status.expression}" listCode="date_format" value="${status.value}" defaultValue="0"  onchange="changeFormate(this.form);"/>
				</spring:bind> 
				</span>
			</td>
		</tr>

		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="sysId.dateValue"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="dateValue" id="dateValue" cssClass="textbox-text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				</span>
				
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="sysId.prefix"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="prefix" id="prefix" cssClass="textbox-text"/>   
				</span>
			</td>
		</tr>

		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="sysId.infix"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="infix" id="infix" cssClass="textbox-text"/>
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="sysId.postfix"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="postfix" id="postfix" cssClass="textbox-text"/>   
				</span>
			</td>
		</tr>
		
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="sysId.idLength"/></span></th>
			<td>
				<span class="textbox">
					<form:input path="idLength" id="idLength" cssClass="textbox-text"/>  
				</span>
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="sysId.fixed"/></span></th>
			<td>    
				<span class="textbox">
				<spring:bind path="sysId.fixed">
					<jecs:list styleClass="textbox-text" name="${status.expression}" listCode="fixed" value="${status.value}" defaultValue="0"/>
				</spring:bind>
				</span>	
			</td>
		</tr>
		<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="sysId.seqTable"/></span></th>
			<td>
				<span class="textbox">
					<form:input path="seqTable" id="seqTable" cssClass="textbox-text"/> 
				</span>	
			</td>
			<th><span class="text-font-style-tc"><jecs:label  key="sysId.version"/></span></th>
			<td>
				<span class="textbox">
				<form:input path="version" id="version" cssClass="textbox-text"/>
				</span>	
			</td>
		</tr>
		<tr>
			
			<td class="command" colspan="4" align="center">
				<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
				</jecs:power>
				<c:if test="${not empty sysId.id }">
				<jecs:power powerCode="deleteSysId">
				<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'SysId')"><jecs:locale key="operation.button.delete"/></button>
				</jecs:power>
				</c:if>
				<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='sysIds.html?needReload=1'"><jecs:locale key="operation.button.cancel"/></button>
				<input type="hidden" name="strAction" value="${param.strAction }"/>
			</td>
		</tr>
	</tbody>
</table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('sysIdForm'));
    
    function validateForm(theForm){   	
    	if(theForm.idName.value==""){
    		alert("<jecs:locale key="sysId.idName.required"/>");
    		theForm.idName.focus();
    		return false;
    	}
    	
    	if(theForm.idCode.value==""){
    		alert("<jecs:locale key="sysId.idCode.required"/>");
    		theForm.idCode.focus();
    		return false;
    	}
    	
    	if(theForm.idValue.value=="" || !isInteger(theForm.idValue.value)){
    		alert("<jecs:locale key="sysId.idValue.required"/>");
    		theForm.idValue.focus();
    		return false;
    	}

    	if(theForm.idLength.value != "" && !isUnsignedInteger(theForm.idLength.value)){
    		alert("<jecs:locale key="sysId.idLength.required"/>");
    		theForm.idLength.focus();
    		return false;
    	}
    	var dvalue = theForm.dateValue.value;
    	
    	if(dvalue.length==4)dvalue=dvalue + "0101";
    	else if(dvalue.length==6)dvalue=dvalue + "01";
    	
    	if(theForm.dateFormat.value!="0" && !isValidDateFormat(dvalue,"")){
    		alert("<jecs:locale key="sysId.dateValue.required"/>");
    		theForm.dateFormat.focus();
    		return false;
    	}
    	if(theForm.fixed.value=="1"){
    		if(confirm("<jecs:locale key="sysId.fixed.confirm"/>")==false){
    			return false;
    		}
    	}
    	return isFormPosted();
    }
    
    function changeFormate(theForm){
		var fmt = theForm.dateFormat;
		var dt = theForm.dateValue;
		var df = "%Y%m%d";
		if(fmt.value=="1"){
			dt.value = dt.value.substr(0,4);
			df = "%Y";
		}else if(fmt.value=="2"){
			dt.value = dt.value.substr(0,6);
			df = "%Y%m";
		}else if(fmt.value=="3"){
			dt.value = dt.value.substr(0,8);
			df = "%Y%m%d";
		}else if(fmt.value=="0"){
			dt.value = "";
		}
		
		Calendar.setup({
			inputField     :    "dateValue",
			ifFormat       :    df,
			button         :    "img_dateValue",
			singleClick    :    true
		}); 
	}
</script>

<v:javascript formName="sysId" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
