<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jalLibraryPlateDetail.title"/></title>
<content tag="heading"><jecs:locale key="jalLibraryPlateDetail.heading"/></content>



<spring:bind path="jalLibraryPlate.*">
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

<form:form commandName="jalLibraryPlate" method="post" action="editJalLibraryPlate.html" onsubmit="return validateJalLibraryPlate(this);" id="jalLibraryPlateForm">


<input type="hidden" name="strAction" value="${param.strAction }"/>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JalLibraryPlate')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
	<tbody class="window">
		<form:hidden path="plateId"/>
	
	    <tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="jalLibraryPlate.plateName"/></span>
		    </th>
		    <td>
		        <!--form:errors path="plateName" cssClass="fieldError"/-->
		        <span class="textbox"><form:input path="plateName" id="plateName" cssClass="textbox-text"/></span>
		    </td>
	   
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="jalLibraryPlate.plateIndex"/></span>
		    </th>
		    <td>
		        <!--form:errors path="plateRemark" cssClass="fieldError"/-->
		        <span class="textbox"><form:input path="plateIndex" id="plateIndex" cssClass="textbox-text"/></span>
		    </td>
	    </tr>
	
	    <tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="jalLibraryPlate.plateRemark"/></span>
		    </th>
		    <td align="left">
		        <!--form:errors path="plateRemark" cssClass="fieldError"/-->
		        <span class="textbox"><form:input path="plateRemark" id="plateRemark" cssClass="textbox-text"/></span>
		    </td>
	    </tr>

<%-- 	    
	    <tr><th>
	        <jecs:label key="sysOperationLog.moduleName" />
	    </th>
	    <td align="left">
	        <jecs:power powerCode="${param.strAction}">
					<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
			</jecs:power>
			<jecs:power powerCode="deleteJalLibraryPlate">
					<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JalLibraryPlate')" value="<jecs:locale key="operation.button.delete"/>" />
			</jecs:power>
	        <input type="button" class="button" name="cancel" onclick="window.location='jalLibraryPlates.html?needReload=1'" value="<jecs:locale key="operation.button.cancel"/>" />
	    </td></tr>
	--%>
	    
	    <tr>		
			<td class="command" colspan="4" align="center">
				 <jecs:power powerCode="${param.strAction}">
					<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
				</jecs:power>
				<jecs:power powerCode="deleteJalLibraryPlate">
					<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JalLibraryPlate')" ><jecs:locale key="operation.button.delete"/></button>
				</jecs:power>
	        	<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='jalLibraryPlates.html?needReload=1'"><jecs:locale key="operation.button.cancel"/></button>
			</td>
		</tr>
	</tbody>
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JalLibraryPlate')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jalLibraryPlateForm'));
</script>
<script type="text/javascript">
	function validateJalLibraryPlate(theForm){
		var plateName = document.getElementById("plateName").value;
		var submitFlag = true;
		if(!plateName){
			alert("<jecs:locale key="pleace.input.palteName"/>");
			submitFlag = false;
		}
		return submitFlag;
	}

</script>	


<v:javascript formName="jalLibraryPlate" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
