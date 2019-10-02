<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<title><jecs:locale key="jamPromotionDetail.title"/></title>
<content tag="heading"><jecs:locale key="jamPromotionDetail.heading"/></content>

<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
		</jecs:power>
		<jecs:power powerCode="deleteJamPromotion">
				<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JamPromotion')"><jecs:locale key="operation.button.delete"/></button>
		</jecs:power>
		<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()"><jecs:locale key="operation.button.return"/></button>
</c:set>

<spring:bind path="jamPromotion.*">
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

<form:form commandName="jamPromotion" method="post" action="editJamPromotion.html"  id="jamPromotionForm">


<input type="hidden" name="strAction" value="${param.strAction }"/>

<table class='detail' width="70%">
<tbody class="window" >
<form:hidden path="id"/>


    <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="jamPromotion.pmName"/></span></th>
		<td>
			<!--form:errors path="pmName" cssClass="fieldError"/-->
			<span class="textbox"><form:input path="pmName" id="pmName" cssClass="textbox-text"/></span>
		</td>
		
		<th><span class="text-font-style-tc"><jecs:label  key="jamPromotion.pmTarget"/></span></th>
		<td>
			<!--form:errors path="pmTarget" cssClass="fieldError"/-->
			<span class="textbox"><form:input path="pmTarget" id="pmTarget" cssClass="textbox-text"/></span>
		</td>
	</tr>


    <tr class="edit_tr">
	
	<th><span class="text-font-style-tc"><jecs:label  key="jamPromotion.pmWay"/></span></th>
    <td >
        <!--form:errors path="pmWay" cssClass="fieldError"/-->
		 <span class="textbox">
         <jecs:list name="pmWay" listCode="jampromotion.pmway" value="${jamPromotion.pmWay}" defaultValue="" showBlankLine="true" styleClass="textbox-text"/>
		</span>		 
    </td>
	<th><span class="text-font-style-tc"><jecs:label  key="customerRecord.type"/></span></th>
    <td>
        <!--form:errors path="pmType" cssClass="fieldError"/-->
		 <span class="textbox">
         <jecs:list name="pmType" listCode="jampromotion.pmtype" value="${jamPromotion.pmType}" defaultValue="" showBlankLine="true" styleClass="textbox-text"/>
		</span>
    </td>
	</tr>


    <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="jamPromotion.noticeDate"/></span></th>
		<td >
			<!--form:errors path="noticeDate" cssClass="fieldError"/-->
			
			<span class="textbox"><form:input path="noticeDate" id="noticeDate" readonly="true"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" cssClass="textbox-text"/></span>
		
		</td>
		<th><span class="text-font-style-tc"> <jecs:label  key="common.startTime"/></span></th>
		<td>
			<!--form:errors path="startTime" cssClass="fieldError"/-->
			<span class="textbox"><form:input path="startTime" id="startTime" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" cssClass="textbox-text"/></span>
			
		</td>
	</tr>

    <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="customerFollow.endTime"/></span></th>
		<td>
			<!--form:errors path="endTime" cssClass="fieldError"/-->
			<span class="textbox"><form:input path="endTime" id="endTime" readonly="true" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" cssClass="textbox-text"/></span>
			
		</td>
		
		<th><span class="text-font-style-tc"><jecs:label  key="customerFollow.state"/></span></th>
		<td>
			<!--form:errors path="status" cssClass="fieldError"/-->
			 <span class="textbox">
			 <jecs:list name="status" listCode="jampromotion.status" styleClass="textbox-text" value="${jamPromotion.status}" defaultValue="0" showBlankLine="true"/>
			 </span>
		</td>
	</tr>
    <tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="jamPromotion.targetGroup"/></span></th>
		<td>
			<!--form:errors path="targetGroup" cssClass="fieldError"/-->
			<span class="textbox"><form:input path="targetGroup" id="targetGroup" cssClass="textbox-text"/></span>
		</td>
		
		<th><span class="text-font-style-tc"><jecs:label  key="jamPromotion.proposer"/></span></th>
		<td>
			<!--form:errors path="proposer" cssClass="fieldError"/-->
			<span class="textbox"><form:input path="proposer" id="proposer" cssClass="textbox-text"/></span>
		</td>
	</tr>

    <tr class="edit_tr">
	<th><jecs:label  key="jamPromotion.detail"/></th>
    <td align="left"  colspan="2">
        <!--form:errors path="detail" cssClass="fieldError"/-->
	    					<FCK:editor instanceName="detail" toolbarSet="simpleBar">
										<jsp:attribute name="value">${jamPromotion.detail}</jsp:attribute>
								</FCK:editor>
    </td></tr>

    <tr class="edit_tr">
	
	<th><jecs:label  key="jamPromotion.analyzed"/></th>
    <td align="left"  colspan="2">
        <!--form:errors path="analyzed" cssClass="fieldError"/-->
        
	    					<FCK:editor instanceName="analyzed" toolbarSet="simpleBar">
										<jsp:attribute name="value">${jamPromotion.analyzed}</jsp:attribute>
								</FCK:editor>
    </td></tr>
	<tr>		
	    <td class="command" colspan="4" align="center">
	    	<c:out value="${buttons}" escapeXml="false"/>
	    </td>
    </tr>
</tbody>
</table>

</form:form>

