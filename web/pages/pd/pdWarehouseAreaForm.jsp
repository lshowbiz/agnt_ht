<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseAreaDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseAreaDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
<%-- 				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
 --%>				
					<button type="submit" class="btn btn_sele"  name="save" onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
		</jecs:power>
		<jecs:power powerCode="deletePdWarehouseArea">
			 <c:if test="${pdWarehouseArea.waId != null}">
<%-- 				<input type="submit" class="button" name="delete" onclick="$('strAction').value='deletePdWarehouseArea';bCancel=true;return confirmDelete(this.form,'PdWarehouseArea')" value="<jecs:locale key="operation.button.delete"/>" />
 --%>			 
                    <button type="submit" class="btn btn_sele"  name="delete" onclick="$('strAction').value='deletePdWarehouseArea';bCancel=true;return confirmDelete(this.form,'PdWarehouseArea')" ><jecs:locale key="operation.button.delete"/></button>
             </c:if>
		</jecs:power>
<%-- 		<input type="button" class="button" name="cancel" onclick="toBack('pdWarehouseAreaContent');" value="<jecs:locale key="operation.button.return"/>" />
 --%>		
            <button type="button" class="btn btn_sele"  name="cancel" onclick="toBack('pdWarehouseAreaContent');" ><jecs:locale key="operation.button.return"/></button>
            
</c:set>

<spring:bind path="pdWarehouseArea.*">
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

<form:form commandName="pdWarehouseArea" method="post" action="editPdWarehouseArea.html" onsubmit="return validatePdWarehouseArea(this)" id="pdWarehouseAreaForm">


<input type="hidden" name="strAction" id="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdWarehouseArea')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
<tbody class="window" >
<form:hidden path="waId"/>


    

    
<tr height="50px"></tr>
    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><jecs:label  key="busi.warehouse.warehouseno"/></span>
    </th>
    <td>
        <!--form:errors path="warehouseNo" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="warehouseNo" id="warehouseNo" readonly="true"  cssClass="textbox-text"/></span>
    </td>

		<th>
        <span class="text-font-style-tc"><jecs:label  key="alStateProvince.stateProvinceName"/></span>
    </th>
    <td align="left">
        <!--form:errors path="areaCode" cssClass="fieldError"/-->
        
        <span class="textbox"><select name="areaCode" class="textbox-text">
					<option value=""></option>
					<c:forEach items="${alStateProvinces}" var="alStateProvince">
						<option value="${alStateProvince.stateProvinceId}" <c:if test="${alStateProvince.stateProvinceId eq pdWarehouseArea.areaCode}">selected</c:if> >
								${alStateProvince.stateProvinceName}
						</option>
					</c:forEach>
				</select></span>
				
        <!--<form:input path="areaCode" id="areaCode" cssClass="text medium"/>-->
    </td></tr>
    <tr height="15px"></tr>
	<tr class="edit_tr">
			<td class="command"  colspan="4" align="center">
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
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdWarehouseArea')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdWarehouseAreaForm'));
    
     function toback(str){
			
				
    		this.location='<c:url value="/pdWarehouseAreas.html"/>?strAction='+str;
    	}
</script>

<v:javascript formName="pdWarehouseArea" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
