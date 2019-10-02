<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdShipStrategyMainDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdShipStrategyMainDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="editPdShipStrategyMain">
				<%-- <input type="button" class="button" name="save"
								onclick="submitMe();"
								value="<jecs:locale key="operation.button.save"/>" /> --%>
								
				<button type="button" class="btn btn_sele" name="save"  onclick="submitMe();" >
				           <jecs:locale key="operation.button.save"/>
				</button>
				
		</jecs:power>
		<!--<jecs:power powerCode="deletePdShipStrategyMain">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'PdShipStrategyMain')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>-->
		<%-- <input type="button" class="button" name="back" onclick="javascript:history.back();"
		value="<jecs:locale  key="operation.button.return"/>" /> --%>
		<button type="button" class="btn btn_sele" name="back"  onclick="javascript:history.back();" >
		          <jecs:locale  key="operation.button.return"/>
		</button>
</c:set>

<spring:bind path="pdShipStrategyMain.*">
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

<form:form commandName="pdShipStrategyMain" method="post" action="editPdShipStrategyMain.html" onsubmit="return validatePdShipStrategyMain(this)" id="pdShipStrategyMainForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdShipStrategyMain')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
<tbody class="window" >
<form:hidden path="valueId"/>
    <tr  class="edit_tr">
    <th>
        <span class="text-font-style-tc"><jecs:label  key="sysListValue.valueCode"/></span>
    </th>
    <td>
        <!--form:errors path="valueCode" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="valueCode" id="valueCode" cssClass="textbox-text" disabled="true"/></span>
    </td>

    <th>
        <span class="text-font-style-tc"><jecs:label  key="sysListValue.valueTitle"/></span>
    </th>
    <td>
        <!--form:errors path="valueTitle" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="valueTitle" id="valueTitle" cssClass="textbox-text" disabled="true"/></span>
    </td>
    </tr>

    <tr  class="edit_tr">
    <th>
        <span class="text-font-style-tc"><jecs:label  key="sysListValue.exCompanyCode"/></span>
    </th>
    <td>
        <!--form:errors path="exCompanyCode" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="exCompanyCode" id="exCompanyCode" cssClass="textbox-text" disabled="true"/></span>
    </td>

    <th>
        <span class="text-font-style-tc"><jecs:label  key="sysModule.orderNo"/></span>
    </th>
    <td>
        <!--form:errors path="orderNo" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="orderNo" id="orderNo" cssClass="textbox-text" disabled="true"/></span>
    </td></tr>

    <tr  class="edit_tr">
    <th>
        <span class="text-font-style-tc"><jecs:label  key="pd.priority"/></span>
    </th>
    <td>
        <!--form:errors path="priority" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="priority" id="priority" cssClass="textbox-text" onkeypress="if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value" onkeyup="if(!this.value.match(/^[\+\-]?\d*?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\d+)?)?$/))this.o_value=this.value" maxlength="3"/></span>
    </td>

    <th>
        <span class="text-font-style-tc"><jecs:label  key="pdShipStrategyMain.importance"/></span>
    </th>
    <td>
    	<span class="textbox"><jecs:list listCode="pdshipstrategymain.importance" name="importance" id="importance" value="${pdShipStrategyMain.importance}" defaultValue="" showBlankLine="true" styleClass="textbox-text"/></span>
    </td>
    </tr>
    <tr class="edit_tr">
		<td class="command"  colspan="4" align="center">
			<c:out value="${buttons}" escapeXml="false"/>
		</td>
	</tr>
	
	
<%-- <div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div> --%>
</tbody>
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdShipStrategyMain')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdShipStrategyMainForm'));
    
    function submitMe(){
   		var priority = document.getElementById("priority");
   		var importance = document.getElementById("importance");
   		if(importance.value=='1'){
   			if(priority.value==''){
	   			alert("<jecs:locale key="pdShipStrategyMain.priority.isnull"/>");
	   			return false;
   			}
   		}else if(importance.value=='0'){
   			if(priority.value!=''){
	   			alert("<jecs:locale key="pdShipStrategyMain.priority.isnotnull"/>");
	   			priority.value='';
	   			priority.focus();
	   			return false;
   			}
   		}
   		$('pdShipStrategyMainForm').submit();
   	}
    
</script>

<v:javascript formName="pdShipStrategyMain" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
