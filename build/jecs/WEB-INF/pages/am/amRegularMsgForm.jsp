<%@ include file="/common/taglibs.jsp"%>
<title><jecs:locale key="amRegularMsgDetail.title"/></title>
<content tag="heading"><jecs:locale key="amRegularMsgDetail.heading"/></content>

<c:set var="buttons">
	<c:choose>
		<c:when test="${param.strAction == 'addAmRegularMsg'}">
			<jecs:power powerCode="${param.strAction}">
					<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
			</jecs:power>
		</c:when>
		<c:when test="${param.strAction == 'editAmRegularMsg'}">
			<jecs:power powerCode="${param.strAction}">
					<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
			</jecs:power>
		</c:when>
		<c:when test="${param.strAction == 'deleteAmRegularMsg'}">
			<jecs:power powerCode="deleteAmRegularMsg">
					<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'AmRegularMsg')" value="<jecs:locale key="operation.button.delete"/>" />
			</jecs:power>
		</c:when>
		
	</c:choose>
</c:set>

<spring:bind path="amRegularMsg.*">
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

<form:form commandName="amRegularMsg" method="post" action="editAmRegularMsg.html" onsubmit="return validateAmRegularMsg(this)" id="amRegularMsgForm">
<!--  -->
<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
	<input type="button" class="button" name="back" onclick="toback();" value="<jecs:locale  key="operation.button.return"/>" />
</div>
<input type="hidden" name="strAction" value="${param.strAction }"/>
<input type="hidden" name="returnView" value="${param.returnView}"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AmRegularMsg')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />

</legend-->
<form:hidden path="uniNo"/>
<form:hidden path="addNo"/>
<form:hidden path="addTime"/>
<form:hidden path="modifyTime"/>
<form:hidden path="companyCode"/>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('AmRegularMsg')" value="<jecs:locale key="operation.button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="operation.button.cancel"/>" />
    </td></tr>
</table-->

<table id="sendTable" class='detail' width="100%" >  
    
    <tr><th width="20%">
        <jecs:label  key="amregularmsg.content"/>
    </th>
    <td align="left" width="80%">
		<form:input path="content" id="content" size="45" cssClass="text medium"/>       	
    </td></tr>
   </table>


</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('amRegularMsgForm'));
    
    function toback(str){
				
    		this.location='<c:url value="/amRegularMsgs.html"/>?strAction=regularMsgSelect';
    	}
    	
</script>

<v:javascript formName="amRegularMsg" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
