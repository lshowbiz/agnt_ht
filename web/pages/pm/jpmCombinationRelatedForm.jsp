<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<script language="javascript" src="scripts/validate.js"></script>
<title><jecs:locale key="jpmCombinationRelatedDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpmCombinationRelatedDetail.heading"/></content>

<%-- 
<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJpmCombinationRelated">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpmCombinationRelated')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
		<input type="button" class="button" name="back" onclick="javascript:history.back();"
				value="<jecs:locale  key="operation.button.return"/>" />
</c:set>
--%>
<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="button.save"/></button>
		</jecs:power>
		<jecs:power powerCode="deleteJpmCombinationRelated">
				<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpmCombinationRelated')" ><jecs:locale key="button.delete"/></button>
		</jecs:power>
		<button type="button" class="btn btn_sele" name="back" onclick="javascript:history.back();"><jecs:locale  key="operation.button.return"/></button>
</c:set>

<spring:bind path="jpmCombinationRelated.*">
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

<form:form commandName="jpmCombinationRelated" method="post" action="editJpmCombinationRelated.html" onsubmit="return validateJpmCombinationRelated(this)&&validateOthers(this)" id="jpmCombinationRelatedForm">

<%-- 
<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmCombinationRelated')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
	<tbody class="window">
	<form:hidden path="uniNo"/>
	
	    <tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="jpmCombinationRelated.productNo"/></span>
		    </th>
		    <td>
		        <!--form:errors path="productNo" cssClass="fieldError"/-->
		        <span class="textbox"><form:input path="productNo" id="productNo" cssClass="textbox-text"/></span>
		    </td>
	    
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="jpmCombinationRelated.productName"/></span>
		    </th>
		    <td>
		        <!--form:errors path="productName" cssClass="fieldError"/-->
		        <span class="textbox"><form:input path="productName" id="productName" cssClass="textbox-text"/></span>
		    </td>
		</tr>
	
	    <tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="jpmCombinationRelated.productDate"/></span>
		    </th>
		    <td>
		        <!--form:errors path="productDate" cssClass="fieldError"/-->
		        <span class="textbox"><form:input path="productDate" id="productDate" cssClass="textbox-text"/></span>
		    </td>
		
	    	<th>
	        	<span class="text-font-style-tc"><jecs:label  key="jpmCombinationRelated.subProductNo"/></span>
	    	</th>
	    	<td>
	        	<!--form:errors path="subProductNo" cssClass="fieldError"/-->
	       	 <span class="textbox"><form:input path="subProductNo" id="subProductNo" cssClass="textbox-text"/></span>
	    	</td>
	    </tr>
	
	    <tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="jpmCombinationRelated.subProductName"/></span>
		    </th>
		    <td>
		        <!--form:errors path="subProductName" cssClass="fieldError"/-->
		        <span class="textbox"><form:input path="subProductName" id="subProductName" cssClass="textbox-text"/></span>
		    </td>
			<th>
		        <span class="text-font-style-tc"><jecs:label  key="jpmCombinationRelated.qty"/></span>
		    </th>
		    <td>
		        <!--form:errors path="qty" cssClass="fieldError"/-->
		        <span class="textbox"><form:input path="qty" id="qty" cssClass="textbox-text"/></span>
		    </td>
		</tr>
	
	    <tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="jpmCombinationRelated.price"/></span>
		    </th>
		    <td>
		        <!--form:errors path="price" cssClass="fieldError"/-->
		        <span class="textbox"><form:input path="price" id="price" cssClass="textbox-text"/></span>
		    </td>
			<th>
		        <span class="text-font-style-tc"><jecs:label  key="jpmCombinationRelated.pv"/></span>
		    </th>
		    <td>
		        <!--form:errors path="pv" cssClass="fieldError"/-->
		        <span class="textbox"><form:input path="pv" id="pv" cssClass="textbox-text"/></span>
		    </td>
		</tr>
	
	    <tr class="edit_tr">
	    	<th>
	        	<span class="text-font-style-tc"><jecs:label  key="jpmCombinationRelated.totalPrice"/></span>
	    	</th>
		    <td>
		        <!--form:errors path="totalPrice" cssClass="fieldError"/-->
		        <span class="textbox"><form:input path="totalPrice" id="totalPrice" cssClass="textbox-text"/></span>
		    </td>
			<th>
		        <span class="text-font-style-tc"><jecs:label  key="jpmCombinationRelated.totalPv"/></span>
		    </th>
		    <td>
		        <!--form:errors path="totalPv" cssClass="fieldError"/-->
		        <span class="textbox"><form:input path="totalPv" id="totalPv" cssClass="textbox-text"/></span>
		    </td>
		 </tr>
	
	    <tr class="edit_tr">
	    	<th>
	       	 	<span class="text-font-style-tc"><jecs:label  key="jpmCombinationRelated.isFree"/></span>
	    	</th>
	   		<td>
		        <!--form:errors path="isFree" cssClass="fieldError"/-->
		        <span class="textbox"><jecs:list styleClass="textbox-text" listCode="yesno" name="isFree"
							id="isFree" showBlankLine="false"
							value="${jpmCombinationRelated.isFree}" defaultValue=""/></span>
	    	</td>
	    	<th>
	        	<span class="text-font-style-tc"><jecs:label  key="busi.common.remark"/></span>
	   		 </th>
		    <td>
		        <!--form:errors path="remark" cssClass="fieldError"/-->
		        <span class="textbox"><form:input path="remark" id="remark" cssClass="textbox-text"/></span>
		    </td>
		</tr>
		
		
		<c:if test="${strAction=='editJpmCombinationRelated'}">
		    <tr class="edit_tr">
			    <th>
			        <span class="text-font-style-tc"><jecs:label  key="pd.createUserNo"/></span>
			    </th>
			    <td>
			        <!--form:errors path="createUserCode" cssClass="fieldError"/-->
			        <span class="textbox"><form:input path="createUserCode" id="createUserCode" cssClass="textbox-text" disabled="disabled"/></span>
			    </td>
		  
			    <th>
			        <span class="text-font-style-tc"><jecs:label  key="pd.createTime"/></span>
			    </th>
			    <td>
			        <!--form:errors path="createTime" cssClass="fieldError"/-->
			        <span class="textbox"><form:input path="createTime" id="createTime" cssClass="textbox-text" disabled="disabled"/></span>
			    </td>
		    </tr>
		
		    <tr class="edit_tr">
			    <th>
			        <span class="text-font-style-tc"><jecs:label  key="prReFund.editUser"/></span>
			    </th>
			    <td>
			        <!--form:errors path="updateUserCode" cssClass="fieldError"/-->
			        <span class="textbox"><form:input path="updateUserCode" id="updateUserCode" cssClass="textbox-text" disabled="disabled"/></span>
			    </td>
			
			    <th>
			        <span class="text-font-style-tc"><jecs:label  key="poCounterOrder.editTime"/></span>
			    </th>
			    <td>
			        <!--form:errors path="updateTime" cssClass="fieldError"/-->
			        <span class="textbox"><form:input path="updateTime" id="updateTime" cssClass="textbox-text" disabled="disabled"/></span>
			    </td>
			</tr>
		</c:if>
		
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
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpmCombinationRelated')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
	//检查是否为正整数最多保留2为小数
	function isUnsignedMoney2(strInteger)   {  
		var regu = /^([1-9]\d*|0)(\.\d{1,2})?$/; 
		return regu.test(strInteger);
	}

    Form.focusFirstElement($('jpmCombinationRelatedForm'));
    function validateOthers(theForm){
		if(!isUnsignedMoney2(theForm.price.value)){
			alert("价格不符合要求");
			return false;
		}
		if(!isUnsignedMoney2(theForm.pv.value)){
			alert("PV不符合要求");
			return false;
		}
		if(!isUnsignedMoney2(theForm.totalPrice.value)){
			alert("总价格不符合要求");
			return false;
		}
		if(!isUnsignedMoney2(theForm.totalPv.value)){
			alert("总PV不符合要求");
			return false;
		}
    }
    
</script>

<v:javascript formName="jpmCombinationRelated" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
