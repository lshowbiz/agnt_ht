<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<c:if test="${isFinished!=true}">
<form:form commandName="jmiRemitSale" method="post" action="" onsubmit="return validateOthers(this)" id="jmiRemitSaleImportForm" enctype="multipart/form-data">

<spring:bind path="jmiRemitSale.*">
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

<table class='detail' width="100%">
<tbody class="window">
   	<tr class="edit_tr">
			<th><span class="text-font-style-tc"><jecs:label  key="fiPayData.importFile"/></span></th>
		    <td align="left">
		   		<span class="textbox"><input name="importExcelFile" type="file" id="importExcelFile" size="50" /></span>
			</td>
	</tr>
    
    <tr class="edit_tr">
	    <th>
			<label>
				<jecs:locale key="fiPayData.import.notice.label" />
						:
			</label>
		</th>
		<c:if test="${param.strAction == 'remitSaleImport'}">
    	<td colspan="3" >
			<jecs:locale key="remitSaleImport.import.notice.text" />
		</td>
		<tr class="edit_tr">
			<!--  模板  -->
			<td align="center">
				<jecs:label key="upload.module" styleClass="desc" />
			</td>
			<td  colspan="3">	
			     <a href="./images/jmiRemitSaleImport.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a>  
			</td>
		</tr>
		</c:if>
		<c:if test="${param.strAction == 'remitSaleTeamImport'}">
    	<td colspan="3" >
			<jecs:locale key="remitSaleTeamImport.import.notice.text" />
		</td>
		<tr class="edit_tr">
			<!--  模板  -->
			<td align="center">
				<jecs:label key="upload.module" styleClass="desc" />
			</td>
			<td  colspan="3">	
			     <a href="./images/jmiRemitSaleTeamImport.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a>  
			</td>
	</tr>
		</c:if>
					
    </tr>
    
    
    
    <tr class="edit_tr">
		<td class="command" colspan="2" align="center">
			<button type="submit" class="btn btn_sele" name="import"  onclick="bCancel=false">
				<jecs:locale key="button.import"/>
			</button>
			<button type="button" class="btn btn_sele" name="cancel"  onclick="window.location.href='jmiRemitSale.html'">
				<jecs:locale key="operation.button.return"/>
			</button>
			
		</td>
	</tr>
	</tbody>
</table>

</form:form>
</c:if>

<c:if test="${isFinished==true}">
	<div id="titleBar">
		<input type="button" class="button" name="cancel"  onclick="history.back()" value="<jecs:locale key="operation.button.return"/>" />
	</div>
	<table>
		<c:forEach items="${messages}" var="messageVar">
			<tr><td>&gt;&gt;${messageVar}</td></tr>
		</c:forEach>
	</table>
</c:if>

<script type="text/javascript">
function validateOthers(theForm){

	var file  = theForm.importExcelFile.value;
	if(file =='' || file == null){
		alert("<jecs:locale key="fiPayData.xlsFile.required"/>");
		theForm.importExcelFile.focus();
		return false;
	}else{
		var index = file.lastIndexOf(".");
		if(index < 0) {
			alert("<jecs:locale key="error.xlsFormat"/>");
			return false;
			} else {
			var ext = file.substring(index + 1, file.length);
			if(ext != "xls") {
				alert("<jecs:locale key="error.xlsFormat"/>");
				return false;
			}
		}
		return true;
	}
}
</script>