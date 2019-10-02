<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="publicScheduleDetail.title"/></title>
<link rel="stylesheet" href="${ctx }/styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="${ctx }/scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="${ctx }/scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="${ctx }/scripts/calendar/lang.jsp"> </script> 
<content tag="heading"><jecs:locale key="publicScheduleDetail.heading"/></content>

<%-- 
<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
		<c:if test="${param.strAction eq 'editPublicSchedule'}">
			<jecs:power powerCode="deletePublicSchedule">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PublicSchedule')" value="<jecs:locale key="operation.button.delete"/>" />
		</jecs:power>
		</c:if>
		<input type="button" class="button" onclick="history.back();" value='<jecs:locale key="operation.button.return"></jecs:locale>' />
		
</c:set>
--%>
<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
		</jecs:power>
		<c:if test="${param.strAction eq 'editPublicSchedule'}">
			<jecs:power powerCode="deletePublicSchedule">
				<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PublicSchedule')" ><jecs:locale key="operation.button.delete"/></button>
		</jecs:power>
		</c:if>
		<button type="button" class="btn btn_sele" onclick="history.back();"><jecs:locale key="operation.button.return"></jecs:locale></button>
		
</c:set>
<spring:bind path="publicSchedule.*">
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

<form:form commandName="publicSchedule" method="post" action="editPublicSchedule.html" onsubmit="return validatePublicSchedule(this)" id="publicScheduleForm">

<%-- 
<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PublicSchedule')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
	<tbody class="window">
		<form:hidden path="psId"/>
		
		    <tr class="edit_tr">
			    <th>
			        <span class="text-font-style-tc"><jecs:label  key="publicSchedule.name"/></span>
			    </th>
			    <td>
			        <!--form:errors path="name" cssClass="fieldError"/-->
			        <span class="textbox"><form:input path="name" id="name" cssClass="textbox-text"/></span>
			    </td>
		   
			     <th>
			        <span class="text-font-style-tc"><jecs:label  key="publicSchedule.type"/></span>
			    </th>
			    <td>
			        <!--form:errors path="name" cssClass="fieldError"/-->
			        <span class="textbox"><form:input path="type" id="type" cssClass="textbox-text"/></span>
			    </td>
		    </tr>
		    <tr>
		    	<th></th>
		    	<td></td>
		    	<th></th>
		    	<td><a style="color: red;size: 10px;"> <jecs:locale key="publicSchedule.types" /> </a></td>
		    </tr>
		
		    <tr class="edit_tr">
			    <th>
			        <span class="text-font-style-tc"><jecs:label  key="publicSchedule.startTime"/></span>
			    </th>
			    <td>
			        <!--form:errors path="startTime" cssClass="fieldError"/-->
			        <%-- <form:input path="startTime" id="startTime" cssClass="text medium"/> --%>
			         <span class="textbox">
			         <input name="startTime" id="startTime"  class="textbox-text"
			       	 	value="<fmt:formatDate pattern='yyyy-MM-dd' value='${publicSchedule.startTime}' />"
			        	onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"></span>
			        <%-- 
			        <span class="textbox">
			         <input name="startTime" id="startTime"  class="textbox-text"
			        value="<fmt:formatDate pattern='yyyy-MM-dd' value='${publicSchedule.startTime}' />"
			        readonly="readonly"></span>
			      	<img src="${ctx}/images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
					<script type="text/javascript"> 
						Calendar.setup({
						inputField     :    "startTime", 
						ifFormat       :    "%Y-%m-%d %H:%M",  
						daFormat       :    "%Y-%m-%d %H:%M",  
						button         :    "img_startTime", 
						singleClick    :    true,
						showsTime    :    true,
						timeFormat    :    "24"
						}); 
					</script>
					--%>
			    </td>
		   
			    <th>
			        <span class="text-font-style-tc"><jecs:label  key="publicSchedule.endTime"/></span>
			    </th>
			    <td>
			        <!--form:errors path="endTime" cssClass="fieldError"/-->
			        <%-- <form:input path="endTime" id="endTime" cssClass="text medium"/> --%>
			        <span class="textbox">
			        <input name="endTime" id="endTime" class="textbox-text"
			        	value="<fmt:formatDate pattern='yyyy-MM-dd' value='${publicSchedule.endTime}' />"
			        	onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"></span>
			        <%-- 
			        <span class="textbox">
			        <input name="endTime" id="endTime" class="textbox-text"
			        value="<fmt:formatDate pattern='yyyy-MM-dd' value='${publicSchedule.endTime}' />"
			        readonly="readonly"></span>
			      	<img src="${ctx}/images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
					<script type="text/javascript"> 
						Calendar.setup({
						inputField     :    "endTime", 
						ifFormat       :    "%Y-%m-%d %H:%M",  
						daFormat       :    "%Y-%m-%d %H:%M",  
						button         :    "img_endTime", 
						singleClick    :    true,
						showsTime    :    true,
						timeFormat    :    "24"
						}); 
					</script>
					--%>
			    </td>
		    
		    </tr>
		
		<%--
			<th>
				<span class="text-font-style-tc-tare"><jecs:label  key="busi.common.remark"/></span>
			</th>
			<td  colspan="3">
				<span class="text-font-style-tc-right"><form:textarea  cssClass="textarea_border" path="remark" id="remark"   /></span>
					
			</td>
		 --%>
			 <tr class="edit_tr"><th>
			        <span class="text-font-style-tc-tare"><jecs:label  key="publicSchedule.content"/></span>
			    </th>
			    <td  colspan="3">
			        <!--form:errors path="name" cssClass="fieldError"/-->
			        <span class="text-font-style-tc-right"><form:textarea cssClass="textarea_border" path="content" id="content" cssErrorClass="text medium error"/></span>
			    </td></tr>
			    
			 <tr>		
			<td class="command" colspan="4" align="center">
				<c:out value="${buttons}" escapeXml="false"/>
			</td>
		</tr>
	</tbody>
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PublicSchedule')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('publicScheduleForm'));
</script>

<v:javascript formName="publicSchedule" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
