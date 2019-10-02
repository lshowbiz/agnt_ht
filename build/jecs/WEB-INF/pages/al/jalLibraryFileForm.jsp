<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jalLibraryFileDetail.title"/></title>
<content tag="heading"><jecs:locale key="jalLibraryFileDetail.heading"/></content>
<!-- 
<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJalLibraryFile">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JalLibraryFile')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set>
-->
<spring:bind path="jalLibraryFile.*">
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

<form:form commandName="jalLibraryFile" method="post" action="editJalLibraryFile.html" id="jalLibraryFileForm">
<!-- 

<div id="titleBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
-->
<input type="hidden" name="strAction" value="${param.strAction }"/>
<input type="hidden" name="columnId" value="${jalLibraryColumn.columnId }"/>
<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JalLibraryFile')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
	<tbody class="window">
	<form:hidden path="fileId"/>
	
	<tr class="edit_tr">
	    
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="jalLibraryFile.columnId"/></span>
	    </th>
	    <td>
	        <span class="textbox"><span class="textbox-text">${jalLibraryColumn.columnName }</span></span>
	    </td>
	
		<th><span class="text-font-style-tc"><jecs:label  key="jalLibraryFile.fileName"/></span></th>
		<td><span class="textbox"><form:input path="fileName" id="fileName" cssClass="textbox-text"/></span></td>
	</tr>
	
	<tr class="edit_tr">
		<th><span class="text-font-style-tc"><jecs:label  key="jalLibraryFile.fileUrl"/></span></th>
		<td><span class="textbox"><form:input path="fileUrl" id="fileUrl" cssClass="textbox-text"/></span></td>
	</tr>
	
	<!--   
	<tr>
		<th><jecs:label  key="jalLibraryFile.fileUrl"/></th>
		<td align="left"><input type="file" name="file" id="file"  onchange="file_onselect()"/></td>
	</tr>
	 -->  
	 

<%-- 	
	<tr><th>
	        <jecs:label key="sysOperationLog.moduleName" />
	    </th>
	    <td align="left">
	        
			<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
			
			
	        <input type="button" class="button" name="cancel" onclick="window.location='jalLibraryColumns.html?needReload=1'" value="<jecs:locale key="operation.button.cancel"/>" />
	    </td></tr>
--%>
		<tr>		
			<td class="command" colspan="4" align="center">
				 
				<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" ><jecs:locale key="operation.button.save"/></button>
			
			
	        	<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='jalLibraryColumns.html?needReload=1'" ><jecs:locale key="operation.button.cancel"/></button>
			</td>
		</tr>
	
    </tbody>
</table>
<!--/fieldset-->

<!--table class='detail' width="100%">
    <tr>
    <td>
        <input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JalLibraryFile')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />
    </td></tr>
</table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jalLibraryFileForm'));
    
    function file_onselect(){
    	
    	var path=document.getElementById("file").value;
        var name=path.split('\\'); 
        var bb=name[name.length-1];             
        document.getElementById("fileName").value=bb.substr(0,bb.indexOf('.'));
    }
    
    function validateJalLibraryFile(theForm){
		
		//var theForm = document.getElementById("jalLibraryFileForm")
		
		document.getElementById('save').disabled=true;
		//document.getElementById('save').value='<jecs:locale key="upload.waiting"/>';
		
		theForm.submit();
	}
</script>

<v:javascript formName="jalLibraryFile" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
