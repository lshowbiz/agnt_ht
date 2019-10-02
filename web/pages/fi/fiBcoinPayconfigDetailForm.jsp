<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBcoinPayconfigDetailDetail.title"/></title>
<content tag="heading"><jecs:locale key="fiBcoinPayconfigDetailDetail.heading"/></content>


<form:form commandName="fiBcoinPayconfigDetail" method="post" enctype="multipart/form-data" action="" onsubmit="return validateFiBcoinPayconfigDetail(this)" id="fiBcoinPayconfigDetailForm">

<input type="hidden" name="strAction" value="${param.strAction }"/>
<input type="hidden" name="flag" value="${flag }"/>
<table class='detail' width="100%">

<form:hidden path="detailId"/>
<form:hidden path="configId"/>

	<tr><th>
        <jecs:label  key="fiBcoinPayconfig.title"/>
    </th>
    <td align="left">
        <!--form:errors path="configId" cssClass="fieldError"/-->
        ${fiBcoinPayconfig.title }
    </td></tr>
    
    <c:if test="${flag == 1}">
	    <tr>
		    <th>
		        <jecs:label  key="busi.product.productno"/>
		    </th>
		    <td align="left">
		        <!--form:errors path="productNo" cssClass="fieldError"/-->
		        
		        <c:if test="${empty fiBcoinPayconfigDetail.detailId}">
		        	<form:input path="productNo" id="productNo" cssClass="text medium"/>
		        </c:if>
		        <c:if test="${not empty fiBcoinPayconfigDetail.detailId}">
		        	${fiBcoinPayconfigDetail.productNo}<form:hidden path="productNo"/>
		        </c:if>
		    </td>
	    </tr>
	    
	    <tr>
		    <th>
		        <jecs:label  key="fiBcoinPayconfigDetail.sumQuantity"/>
		    </th>
		    <td align="left">
		        <!--form:errors path="sumQuantity" cssClass="fieldError"/-->
		        <form:input path="sumQuantity" id="sumQuantity" cssClass="text medium"/>
		    </td>
	    </tr>
	    
	    <c:if test="${not empty fiBcoinPayconfigDetail.detailId}">
	    <tr>
		    <th>
		        <jecs:label  key="fiBcoinPayconfigDetail.nowQuantity"/>
		    </th>
		    <td align="left">
		        <!--form:errors path="sumQuantity" cssClass="fieldError"/-->
		        <form:input path="nowQuantity" id="nowQuantity" cssClass="text medium"/>
		    </td>
	    </tr>
	    </c:if>
    
    </c:if>
    <c:if test="${flag == 2}">
	    <tr><th>
	        <label for="xlsFile" class="required">
	        	<span class="req">*</span> 
	        	<jecs:locale key="fiPayData.importFile"/>:
	        </label>
	    </th>
	    <td align="left"><input type="file" name="xlsFile" id="xlsFile" size="50"/>
	    <jecs:locale key="fiBcoinPayconfigDetail.excel"/>
	    
	    <form:hidden path="productNo" id="productNo"/>
	    </td>
	    </tr>
	</c:if>
	
    <tr><th>
        <jecs:label key="sysOperationLog.moduleName"/>
    </th>
    <td align="left">
        
        
        <input type="submit" class="button" name="save"	onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
        <c:if test="${not empty fiBcoinPayconfigDetail.detailId}">
	        <jecs:power powerCode="deleteFiBcoinPayconfigDetail">
					<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'FiBcoinPayconfigDetail')" value="<jecs:locale key="operation.button.delete"/>" />
			</jecs:power>
		</c:if>
		<input type="button" class="button" name="cancel" onclick="editFiBcoinPayconfig(${fiBcoinPayconfig.configId})" value="<fmt:message key="operation.button.cancel"/>" />
		
    </td></tr>

</table>

</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('fiBcoinPayconfigDetailForm'));

   function editFiBcoinPayconfig(configId){
   		<jecs:power powerCode="editFiBcoinPayconfig">
					window.location="editFiBcoinPayconfig.html?strAction=editFiBcoinPayconfig&configId="+configId;
			</jecs:power>
		}

</script>
<v:javascript formName="fiBcoinPayconfigDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
