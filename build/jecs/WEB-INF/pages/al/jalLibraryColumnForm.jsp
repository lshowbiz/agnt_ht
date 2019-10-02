<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jalLibraryColumnDetail.title"/></title>
<content tag="heading"><jecs:locale key="jalLibraryColumnDetail.heading"/></content>



<spring:bind path="jalLibraryColumn.*">
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

<form:form commandName="jalLibraryColumn" method="post" action="editJalLibraryColumn.html" enctype="multipart/form-data" onsubmit="return validateJalLibraryColumn(this)" id="jalLibraryColumnForm">

<%--modify by lihao for bug#3276 --%>
<%-- 
<input type="hidden" name="strAction" value="${param.strAction }"/>
--%>

<!--fieldset style="padding: 2">
<legend>
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JalLibraryColumn')" value="<jecs:locale key="button.delete"/>" />
        <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale key="button.cancel"/>" />

</legend-->
<table class='detail' width="70%">
	<tbody class="window">
		<form:hidden path="columnId"/>
		
	
	    <tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="jalLibraryColumn.columnName"/></span>
		    </th>
		    <td>
		        <!--form:errors path="columnName" cssClass="fieldError"/-->
		        <span class="textbox"><form:input path="columnName" id="columnName" cssClass="textbox-text"/></span>
		    </td>
	   
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="jalLibraryColumn.plateId"/></span>
		    </th>
		    <td>
		        <!--form:errors path="plateId" cssClass="fieldError"/-->
		        <span class="textbox">
			        <select name="plateId" id="plateId" class="textbox-text">
			
						<c:forEach items="${jalLibraryPlates}" var="jalLibraryPlate">
						
							<c:if test="${jalLibraryPlate.plateId==jalLibraryColumn.plateId}"> 
								<option value="${jalLibraryPlate.plateId }" selected>${jalLibraryPlate.plateName }</option>
							</c:if>
							<c:if test="${jalLibraryPlate.plateId!=jalLibraryColumn.plateId}">
								<option value="${jalLibraryPlate.plateId }">${jalLibraryPlate.plateName }</option>
							</c:if>
							
						</c:forEach>
					</select>
				</span>
		        
		        
		    </td>
	    </tr>
	
	    <tr class="edit_tr">
		    <th>
		        <span class="text-font-style-tc"><jecs:label  key="jalLibraryColumn.columnImgs"/></span>
		    </th>
		    <td>
		        
		        	<span class="textbox"><input type="file" name="file" id="file" class="textbox-text"/></span>
		        
		    </td>
	    </tr>
	    
	    <c:if test="${not empty jalLibraryColumn.columnId}">
	    	<tr class="edit_tr"><th>
	        	<span class="text-font-style-tc" style="border:0"><jecs:label  key="jalLibraryColumn.now.columnImgs"/></span>
		    </th>
		    <td>
		        <!--form:errors path="columnImgs" cssClass="fieldError"/-->
		        
		        	<img src="${fileUrl }/${jalLibraryColumn.columnImgs }" width="120px" height="120px"/>
		        
		    </td></tr>
	    </c:if>
	    
	
	   
<%-- 	    
	    <tr><th>
	        <jecs:label key="sysOperationLog.moduleName" />
	    </th>
	    <td align="left">
	        <jecs:power powerCode="${param.strAction}">
					<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />
			</jecs:power>
			<jecs:power powerCode="deleteJalLibraryColumn">
					<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JalLibraryColumn')" value="<jecs:locale key="operation.button.delete"/>" />
			</jecs:power>
	        <input type="button" class="button" name="cancel" onclick="window.location='jalLibraryColumns.html?needReload=1'" value="<jecs:locale key="operation.button.cancel"/>" />
	    </td></tr>
--%>
		<tr>		
			<td class="command" colspan="4" align="center">
				<jecs:power powerCode="${param.strAction}">
					<button type="submit" class="btn btn_sele" name="save"  onclick="bCancel=false" value="" ><jecs:locale key="operation.button.save"/></button>
				</jecs:power>
				<jecs:power powerCode="deleteJalLibraryColumn">
					<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JalLibraryColumn')"><jecs:locale key="operation.button.delete"/></button>
				</jecs:power>
	        	<button type="button" class="btn btn_sele" name="cancel" onclick="window.location='jalLibraryColumns.html?needReload=1'" ><jecs:locale key="operation.button.cancel"/></button>
			</td>
		</tr>
	</tbody>
</table>

</form:form>
<%-- 
<jecs:power powerCode="addJalLibraryFile">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJalLibraryFile.html"/>?strAction=addJalLibraryFile&columnId=${jalLibraryColumn.columnId }'"
			        value="<jecs:locale key="button.upload.library"/>"/>
		</jecs:power>
--%>		
		<jecs:power powerCode="addJalLibraryFile">
			    <button type="button" class="btn btn_sele" style="margin-right: 5px;margin-top: 5px;margin-bottom: 5px;margin-left: 140px"
			        onclick="location.href='<c:url value="/editJalLibraryFile.html"/>?strAction=addJalLibraryFile&columnId=${jalLibraryColumn.columnId }'"><jecs:locale key="button.upload.library"/></button>
		</jecs:power>


<table class='detail' width="100%">
<tr  style="background-color:#E6E6E6;font-size: 9pt;vertical-align: top;">
	<td><jecs:locale  key="jalLibraryFile.fileName"/></td>
	<td><jecs:locale  key="jalLibraryFile.fileUrl"/></td>
	<td><jecs:locale  key="sysOperationLog.moduleName"/></td>
</tr>

<c:forEach  items="${jalLibraryFiles}"  var="jalLibraryFile"  varStatus='status'>
          
	<tr>
		<td>${jalLibraryFile.fileName }</td>
		<td>${jalLibraryFile.fileUrl }</td>
		<td>
		<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJalLibraryFile('${jalLibraryFile.fileId}','${jalLibraryFile.columnId }')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
								
		<img src="<c:url value='/images/icons/deleteIcon.gif'/>" 
								onclick="javascript:delJalLibraryFile('${jalLibraryFile.fileId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.delete"/>"/> 
	</tr>     
</c:forEach>
</table>


<!-- 改为ec:table的实现 -->
<%-- 
<ec:table items="jalLibraryFiles" var="jalLibraryFile"
		width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
		sortable="true" imagePath="./images/extremetable/*.gif">
		
		<ec:row>
		
			<ec:column property="fileName" title="jalLibraryFile.fileName" sortable="false">
				 ${jalLibraryFile.fileName }
			</ec:column>
			<ec:column property="fileUrl" title="jalLibraryFile.fileUrl" sortable="false">
				${jalLibraryFile.fileUrl }
			</ec:column>
			
			<ec:column property="edit" title="sysOperationLog.moduleName" sortable="false">
				<img src="<c:url value='/images/icons/editIcon.gif'/>" 
									onclick="javascript:editJalLibraryFile('${jalLibraryFile.fileId}','${jalLibraryFile.columnId }')"
									style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
									
			<img src="<c:url value='/images/icons/deleteIcon.gif'/>" 
									onclick="javascript:delJalLibraryFile('${jalLibraryFile.fileId}')"
									style="cursor: pointer;" title="<jecs:locale key="button.delete"/>"/> 
			</ec:column>
			
		</ec:row>
</ec:table>
--%>


<script type="text/javascript">
    Form.focusFirstElement($('jalLibraryColumnForm'));
    
    function editJalLibraryFile(fileId,columnId){

   		
		window.location="editJalLibraryFile.html?fileId="+fileId+"&columnId="+columnId;
		
	}
	function delJalLibraryFile(fileId){

   		<jecs:power powerCode="delJalLibraryFile">
			window.location="editJalLibraryColumn.html?strAction=delJalLibraryFile&fileId="+fileId;
		</jecs:power>
	}
</script>

<v:javascript formName="jalLibraryColumn" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>
