<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoMemberOrderDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberOrderDetail.heading"/></content>

<form action="" method="post" enctype="multipart/form-data" name="jpoMemberOrderForm" id="jpoMemberOrderForm" onsubmit="return validateOthers(this)">

<spring:bind path="jpoMemberOrder.*">
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


<table class='detail' width="70%">
<tbody class="window">
    <tr class="edit_tr"><th>
        <label for="xlsFile" class="required">
        	<span class="req">*</span> 
        	<jecs:locale key="fiPayData.importFile"/>:
        </label>
    </th>
    <td align="left"><input type="file" name="xlsFile" id="xlsFile" size="50"/></td>
    </tr>

    <tr class="edit_tr"><th>
    	<label>
        	<jecs:locale key="fiPayData.import.notice.label"/>:
        </label>
    </th>
    <td align="left">
        <jecs:locale key="jpoMemberBathROrder.import.notice.text"/>
    </td></tr>
    
    <tr class="edit_tr">
		
		<td class="command"  colspan="4" align="center">
			<input type="hidden" name="strAction" value="importPoMemberOrder"/>
				<button type="submit" class="btn btn_sele" name="import"	onclick="bCancel=false"><jecs:locale key="button.import"/></button>
		</td>
	</tr>
</tbody>
</table>
</form>

<script type="text/javascript">
Form.focusFirstElement($('jpoMemberOrderForm'));

function validateOthers(theForm){
	if(theForm.xlsFile.value=="" || getFileType(theForm.xlsFile.value)!=".xls"){
		alert("<jecs:locale key="jpoMemberOrder.xlsFile.required"/>");
		theForm.xlsFile.focus();
		return false;
	}
	return isFormPosted();
}
</script>