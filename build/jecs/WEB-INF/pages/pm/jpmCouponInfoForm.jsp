<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpmCouponInfoDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpmCouponInfoDetail.heading"/></content>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<c:set var="buttons">
		<jecs:power powerCode="${param.strAction}">
			<input type="submit" class="btn btn_sele" name="save"
				onclick="bCancel=false"
				value="<jecs:locale key="operation.button.save"/>" />
		</jecs:power>
		<input type="button" class="btn btn_sele" name="back"
			onclick="javascript:jpmCouponInfo();"
			value="<jecs:locale  key="operation.button.return"/>" />
</c:set>

<spring:bind path="jpmCouponInfo.*">
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

<form:form commandName="jpmCouponInfo" method="post" action="editJpmCouponInfo.html" onsubmit="return validateJpmCouponInfo(this)" id="jpmCouponInfoForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmCouponInfo')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
<tbody class="window">
<form:hidden path="cpId"/>

    <tr  class="edit_tr">
	    <th>
	         <span class="text-font-style-tc"><jecs:label  key="jpmCouponInfo.cpName" required="true"/></span>
	    </th>
	    <td align="left">
	         <span class="textbox"><form:input path="cpName" id="cpName" cssClass="textbox-text"/></span>
	    </td>
		<th>
	         <span class="text-font-style-tc"><jecs:label  key="jpmCouponInfo.cpValue" required="true"/></span>
	    </th>
	    <td align="left">
	        <!--form:errors path="cpValue" cssClass="fieldError"/-->
	        <span class="textbox"><form:input path="cpValue" id="cpValue" cssClass="textbox-text" onkeyup="value=value.replace(/[^\d]/g,'')"/></span>
	    </td>    
    </tr>

  
    <tr  class="edit_tr">
	    <th>
	         <span class="text-font-style-tc"><jecs:label  key="jpmCouponInfo.startTime"/></span>
	    </th>
	    <td align="left">
			 <span class="textbox">
			 <input name="startTime" id="startTime" type="text" class="textbox-text"
				value="" style="cursor: pointer;width:175px;height:20px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',startDate:'%y-%M-%d 00:00:00',readOnly:true})"/>			
			</span>
	    </td>
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="jpmCouponInfo.endTime"/></span>
	    </th>
	    <td align="left">
			<span class="textbox">
			<input name="endTime"  id="endTime" type="text"  class="textbox-text"
				value="" style="cursor: pointer;width:175px;height:20px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',startDate:'%y-%M-%d 23:59:59',readOnly:true})"/></span>
	    </td>
	</tr>
    
    <tr  class="edit_tr"><%--
	    <th>
	        <jecs:label  key="jpmCouponInfo.status"/>
	    </th>
	    <td align="left">
	        <!--form:errors path="status" cssClass="fieldError"/-->
	        <jecs:list styleClass="textbox-text" listCode="couponinfo.status" name="status" id="status"
							showBlankLine="false" value=""
							defaultValue="${jpmCouponInfo.status}" />
	    </td>
    	--%><th>
	         <span class="text-font-style-tc-tare"><jecs:label  key="jpmCouponInfo.remark"/></span>
	    </th>
	    <td colspan="3" align="left">
	        <!--form:errors path="remark" cssClass="fieldError"/-->
	       <span class="text-font-style-tc-right">
	        <form:textarea  cssClass="textarea_border" path="remark" id="remark" />
			</span>
	    </td>
    </tr>
    
	<tr>
		<td class="command" colspan="4" align="center">
			<c:out value="${buttons}" escapeXml="false" />
		</td>
	</tr>

<%--
    <tr><th>
        <jecs:label  key="jpmCouponInfo.createTime"/>
    </th>
    <td align="left">
        <!--form:errors path="createTime" cssClass="fieldError"/-->
        <form:input path="createTime" id="createTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmCouponInfo.createUserCode"/>
    </th>
    <td align="left">
        <!--form:errors path="createUserCode" cssClass="fieldError"/-->
        <form:input path="createUserCode" id="createUserCode" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmCouponInfo.updateTime"/>
    </th>
    <td align="left">
        <!--form:errors path="updateTime" cssClass="fieldError"/-->
        <form:input path="updateTime" id="updateTime" cssClass="text medium"/>
    </td></tr>

    <tr><th>
        <jecs:label  key="jpmCouponInfo.updateUserCode"/>
    </th>
    <td align="left">
        <!--form:errors path="updateUserCode" cssClass="fieldError"/-->
        <form:input path="updateUserCode" id="updateUserCode" cssClass="text medium"/>
    </td></tr>
 --%>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmCouponInfo')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->

</tbody>
</table>
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpmCouponInfoForm'));
  
    function jpmCouponInfo(cpId){
   		<jecs:power powerCode="jpmCouponInfos">
					window.location="jpmCouponInfos.html?strAction=jpmCouponInfos";
		</jecs:power>
	}
    
    function validateJpmCouponInfo(){
    	var cpName = document.getElementById("cpName").value;
    	var cpValue=document.getElementById("cpValue").value;
//    	var status=document.getElementById("status").value;
		var msg="";
		if(cpName ==""||cpName ==null){
			msg+='<jecs:locale key="couponInfoName.check"/>';
		}
		if(cpValue ==""||cpValue ==null){
			msg+='\r\n<jecs:locale key="couponInfocpValue.check"/>';	
		} 
//		if(status ==""||status ==null){
//			msg+='\r\n<jecs:locale key="couponInfoStatus.check"/>';
//		}
		if(msg==""||msg==null){
			return true;
		}
		if(msg!=""||msg!=null){
			alert(msg);
			return false;	
		}
	}  
</script>

<v:javascript formName="jpmCouponInfo" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
