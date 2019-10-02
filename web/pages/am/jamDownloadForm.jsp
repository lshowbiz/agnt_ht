<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jamDownloadDetail.title"/></title>
<content tag="heading"><jecs:locale key="jamDownloadDetail.heading"/></content>

<c:set var="buttons">

		<jecs:power powerCode="${param.strAction}">
		<%-- 		<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="operation.button.save"/>" />	--%>
			<button type="submit" class="btn btn_sele" name="save" onclick="bCancel=false"><jecs:locale key="operation.button.save"/></button>
		</jecs:power>
		<jecs:power powerCode="deleteJamDownload">
				<c:if test="${not empty jmiAddrBook.fabId}">
				<%-- 
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JamDownload')" value="<jecs:locale key="operation.button.delete"/>" />
				--%>
				<button type="submit" class="btn btn_sele" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JamDownload')" ><jecs:locale key="operation.button.delete"/></button>
				</c:if>
		</jecs:power>
		<%-- <input type="button" class="button" name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />	--%>
		<button type="button" class="btn btn_sele" name="cancel" onclick="window.history.back()"><jecs:locale key="operation.button.return"/></button>
</c:set>

<spring:bind path="jamDownload.*">
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

<form:form commandName="jamDownload" method="post" action="editJamDownload.html"  id="jamDownloadForm">

<%-- 
<div class="searchBar">
	<c:out value="${buttons}" escapeXml="false"/>
</div>
--%>
<input type="hidden" name="strAction" value="${param.strAction }"/>
<table class='detail' width="70%">
<tbody class="window" >

<form:hidden path="dlId"/>

    <tr class="edit_tr">
	    <th>
	    	<span class="text-font-style-tc">
	        	<jecs:label  key="jamDownload.fileType"/>
	        </span>
	    </th>
	    <td>
	        <!--form:errors path="fileType" cssClass="fieldError"/-->
	        <c:if test="${param.strAction=='viewJamDownload' }">
	        		
	    		<span class="textbox"><jecs:code listCode="filetype" value="${jamDownload.fileType}"/></span>
	        </c:if>
	        <c:if test="${param.strAction!='viewJamDownload' }">
	        
	        <span class="textbox"><jecs:list listCode="filetype" styleClass="textbox-text" name="fileType" id="fileType" value="${jamDownload.fileType}" defaultValue="" showBlankLine="true"/></span>
	        </c:if>
	    </td>
   
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="uploadForm.name"/></span>
	    </th>
	    <td>
	        <!--form:errors path="fileName" cssClass="fieldError"/-->
	        <c:if test="${param.strAction=='viewJamDownload' }">
	        	${jamDownload.fileName}
	        </c:if>
	        <c:if test="${param.strAction!='viewJamDownload' }">
	        
	        <span class="textbox"><form:input path="fileName" id="fileName" cssClass="textbox-text"/></span>
	        </c:if>
	    </td>
    </tr>

	<!-- input -->
    <tr class="edit_tr">
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="jamDownload.fileLink"/></span>
	    </th>
	    <td>
	        <!--form:errors path="fileLink" cssClass="fieldError"/-->
	        <c:if test="${param.strAction=='viewJamDownload' }">
	        	
	        	<span class="textbox"><a target="blank" href="${jamDownload.fileLink}">${jamDownload.fileLink}</a></span>
	        	
	        </c:if>
	        <c:if test="${param.strAction!='viewJamDownload' }">
	        <span class="textbox"><form:input path="fileLink" id="fileLink" cssClass="textbox-text" cssStyle="width:500px"/></span>
	        </c:if>
	    </td>
    
	    <th>
	        <span class="text-font-style-tc"><jecs:label  key="customerFollow.state"/></span>
	    </th>
	    <td>
	        <c:if test="${param.strAction=='viewJamDownload' }">
	    				<jecs:code listCode="amannounce.status" value="${jamDownload.status}"/>
	        </c:if>
	        <c:if test="${param.strAction!='viewJamDownload' }">
	        	<span class="textbox"><jecs:list styleClass="textbox-text" listCode="amannounce.status" name="status" id="status" value="${jamDownload.status}" defaultValue="0"/></span>
	        </c:if>
	        <!--form:errors path="status" cssClass="fieldError"/-->
	    </td>
    
    </tr>

<%-- 
    <tr class="edit_tr"><th>
        <span class="text-font-style-tc"><jecs:label  key="vtVote.description"/></span>
    </th>
    <td align="left">
        <!--form:errors path="fileDesc" cssClass="fieldError"/-->
        <c:if test="${param.strAction=='viewJamDownload' }">
        	${jamDownload.fileDesc}
        </c:if>
        <c:if test="${param.strAction!='viewJamDownload' }">
		<form:textarea path="fileDesc" id="fileDesc"  rows="7" cols="35"/>
        </c:if>
    </td></tr>
--%>
	<!-- textarea -->
	<tr class="edit_tr">
				
		<th>
			<span class="text-font-style-tc-tare"><jecs:label  key="vtVote.description"/></span>
		</th>
		<td  colspan="3">
		    <span class="text-font-style-tc-right">
		    <c:if test="${param.strAction!='viewJamDownload' }">
				<form:textarea path="fileDesc" id="fileDesc" cssClass="textarea_border"/>
       		 </c:if>
        	</span>
			
		</td>
	</tr>
	
	
    
    <!-- button -->
    <tr>		
	    <td class="command" colspan="4" align="center">
	    	<c:out value="${buttons}" escapeXml="false"/>
	    </td>
    </tr>
	</tbody>
</table>
<%--   
<table class='detail' width="100%">

<form:hidden path="dlId"/>

    <tr><th>
        <jecs:label  key="jamDownload.fileType"/>
    </th>
    <td align="left">
        <!--form:errors path="fileType" cssClass="fieldError"/-->
        <c:if test="${param.strAction=='viewJamDownload' }">
    				<jecs:code listCode="filetype" value="${jamDownload.fileType}"/>
        </c:if>
        <c:if test="${param.strAction!='viewJamDownload' }">
        <jecs:list listCode="filetype" name="fileType" id="fileType" value="${jamDownload.fileType}" defaultValue="" showBlankLine="true"/>
        </c:if>
    </td></tr>

    <tr><th>
        <jecs:label  key="uploadForm.name"/>
    </th>
    <td align="left">
        <!--form:errors path="fileName" cssClass="fieldError"/-->
        <c:if test="${param.strAction=='viewJamDownload' }">
        	${jamDownload.fileName}
        </c:if>
        <c:if test="${param.strAction!='viewJamDownload' }">
        <form:input path="fileName" id="fileName" cssClass="text medium"/>
        </c:if>
    </td></tr>

    <tr><th>
        <jecs:label  key="jamDownload.fileLink"/>
    </th>
    <td align="left">
        <!--form:errors path="fileLink" cssClass="fieldError"/-->
        <c:if test="${param.strAction=='viewJamDownload' }">
        	<a target="blank" href="${jamDownload.fileLink}">${jamDownload.fileLink}</a>
        	
        </c:if>
        <c:if test="${param.strAction!='viewJamDownload' }">
        <form:input path="fileLink" id="fileLink" cssClass="text medium" cssStyle="width:500px"/>
        </c:if>
    </td></tr>

    <tr><th>
        <jecs:label  key="vtVote.description"/>
    </th>
    <td align="left">
        <!--form:errors path="fileDesc" cssClass="fieldError"/-->
        <c:if test="${param.strAction=='viewJamDownload' }">
        	${jamDownload.fileDesc}
        </c:if>
        <c:if test="${param.strAction!='viewJamDownload' }">
		<form:textarea path="fileDesc" id="fileDesc"  rows="7" cols="35"/>
        </c:if>
    </td></tr>

    <tr><th>
        <jecs:label  key="customerFollow.state"/>
    </th>
    <td align="left">
        <c:if test="${param.strAction=='viewJamDownload' }">
    				<jecs:code listCode="amannounce.status" value="${jamDownload.status}"/>
        </c:if>
        <c:if test="${param.strAction!='viewJamDownload' }">
        <jecs:list listCode="amannounce.status" name="status" id="status" value="${jamDownload.status}" defaultValue="0"/>
        </c:if>
        <!--form:errors path="status" cssClass="fieldError"/-->
    </td></tr>

</table>
--%>
</form:form>

