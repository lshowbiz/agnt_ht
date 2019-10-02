<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseDetail.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
			<c:if test="${param.strAction == 'editPdWarehouse' || param.strAction == 'addPdWarehouse'}">
<%-- 				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
 --%>				
				<button type="submit" class="btn btn_sele"  name="save" onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
			</c:if>
		</jecs:power>
		
<%-- 		<input type="button" class="button" name="goBack"  onclick="gooBack()" value="<jecs:locale key="operation.button.return"/>" />		
 --%>		
           <button type="button" class="btn btn_sele"  name="goBack" onclick="gooBack()" ><jecs:locale key="operation.button.return"/></button>
</c:set>

<spring:bind path="pdWarehouse.*">
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

<form:form commandName="pdWarehouse" method="post" action="editPdWarehouse.html" onsubmit="return validatePdWarehouse(this)" id="pdWarehouseForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdWarehouse')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
<tbody class="window" >



    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="busi.warehouse.warehouseno"/></span>
    </th>
    <td>
        <!--form:errors path="warehouseNo" cssClass="fieldError"/-->
        <c:choose >
    	      
    	      	<c:when test="${param.strAction=='addPdWarehouse'}">
    	      		<span class="textbox"><form:input path="warehouseNo" id="warehouseNo"   cssClass="textbox-text"/></span>
    	      	</c:when>
    	      	<c:otherwise>
    	      		<span class="textbox"><form:input path="warehouseNo" id="warehouseNo" readonly="true"  cssClass="textbox-text"/></span>
    	      	</c:otherwise>
    	  </c:choose>
    	      		
        
        	
    </td>
    <th>
        <span class="text-font-style-tc"><jecs:label  key="pdWarehouse.warehouseName"/></span>
    </th>
    <td>
        <!--form:errors path="warehouseName" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="warehouseName" id="warehouseName" cssClass="textbox-text"/></span>
    </td></tr>
    
		<c:if test="${sessionScope.sessionLogin.companyCode == 'AA'}">
    <tr class="edit_tr"><th>
        <span class="textbox"><jecs:label  key="sys.company.code"/></span>
    </th>    <td align="left">
       <span class="textbox"><jecs:company name="companyCode" selected="${pdWarehouse.companyCode}"  prompt="" withAA="true"   /></span>
        
    
        
         
    </td></tr>
		</c:if>
		<c:if test="${sessionScope.sessionLogin.companyCode != 'AA'}">
    <form:hidden path="companyCode"/>
		</c:if>


    

   

    <tr  class="edit_tr"><th>
        <span class="text-font-style-tc"><jecs:label  key="miMember.province"/></span>
    </th>
    <td align="left">
        <!--form:errors path="stateCode" cssClass="fieldError"/-->
        <span class="textbox"><select name="stateCode" class="textbox-text">
					<option value=""></option>
					
        <c:forEach items="${alStateProvinces}" var="alStateProvince">
						<option value="${alStateProvince.stateProvinceId}" <c:if test="${alStateProvince.stateProvinceId eq pdWarehouse.stateCode}">selected</c:if> >
								${alStateProvince.stateProvinceName}
						</option>
					</c:forEach>
				</select></span>	
        <!--form:input path="stateCode" id="stateCode" cssClass="text medium"/-->
    </td>
    
    <th>
         <span class="text-font-style-tc"><jecs:label  key="busi.city"/></span>
    </th>
    <td align="left">
        <!--form:errors path="city" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="city" id="city" cssClass="textbox-text"/></span>
    </td></tr>

    
    
    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><font color="red">*</font><jecs:label  key="pd.shno"/></span>
    </th>
    <td>
        <!--form:errors path="warehouseZip" cssClass="fieldError"/-->
        <span class="textbox"><jecs:list listCode="pd.shno" name="shNo" id="shNo" showBlankLine="true" value="${pdWarehouse.shNo}" defaultValue="" styleClass="textbox-text"/></span>
    </td>
    

    <th>
        <span class="text-font-style-tc"><jecs:label  key="pdWarehouse.warehouseLevel"/></span>
    </th>
    <td>
        <!--form:errors path="warehouseLevel" cssClass="fieldError"/-->
        <!--form:input path="warehouseLevel" id="warehouseLevel" cssClass="text medium"/-->
        
	     <span class="textbox"><jecs:list listCode="pd.warehouselevels" name="warehouseLevel" id="warehouseLevel" showBlankLine="true" value="${pdWarehouse.warehouseLevel}" defaultValue="" styleClass="textbox-text"/></span>
        
        <!-- 
	        <c:if test="${empty pdWarehouse.warehouseNo}">
	        		<jecs:list listCode="pd.warehouselevels" name="warehouseLevel" id="warehouseLevel" showBlankLine="true" value="${pdWarehouse.warehouseLevel}" defaultValue=""/>
	        </c:if>
	        
	        <c:if test="${not empty pdWarehouse.warehouseNo}">
	        	<jecs:code listCode="pd.warehouselevels" value="${pdWarehouse.warehouseLevel}"/>
	        </c:if>
         -->
         
    </td></tr>
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="pdWarehouse.warehouseAddr"/></span>
	    </th>
	    <td colspan="3">
	        <!--form:errors path="warehouseAddr" cssClass="fieldError"/-->
	        <form:input path="warehouseAddr" id="warehouseAddr" cssClass="textbox"  size="80" cssStyle="width:500px;"/>
	    </td>
    </tr>
   <tr class="edit_tr">
    <th>
        <span class="text-font-style-tc"><jecs:label  key="subStore.postalcode"/></span>
    </th>
    <td>
        <!--form:errors path="warehouseZip" cssClass="fieldError"/-->
        <span class="textbox"><form:input path="warehouseZip" id="warehouseZip" cssClass="textbox-text"/></span>
    </td></tr>
    <tr class="edit_tr"><th>
        <span class="text-font-style-tc-tare"><jecs:label  key="busi.common.remark"/></span>
    </th>
    <td colspan="3">
        <!--form:errors path="remark" cssClass="fieldError"/-->
        <span class="text-font-style-tc-right"><form:textarea path="remark" id="remark" cssClass="textarea_border"/></span>
    </td></tr>
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
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('PdWarehouse')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('pdWarehouseForm'));
    
    function gooBack(){
          window.history.back();
    }
    
</script>

<v:javascript formName="pdWarehouse" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
