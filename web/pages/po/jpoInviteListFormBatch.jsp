<%@ page language="java" errorPage="/error.jsp"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoInviteListDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpoInviteListDetail.heading"/></content>

<%-- <c:set var="buttons">	
		<jecs:power powerCode="${param.strAction}">
				<input type="submit" class="button" name="save"  onclick="bCancel=false" value="<jecs:locale key="button.save"/>" />
		</jecs:power>
		<jecs:power powerCode="deleteJpoInviteList">
				<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete(this.form,'JpoInviteList')" value="<jecs:locale key="button.delete"/>" />
		</jecs:power>
</c:set> --%>

<spring:bind path="jpoInviteList.*">
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

<form:form commandName="jpoInviteList" 
	method="post" action="batchJpoInviteList.html"
	enctype="multipart/form-data"
	onsubmit="return validateJpoInviteListUpload(this)" 
	id="jpoInviteListForm">

<table class='detail' width="100%">
	<tbody>

	<!-- <tr class="edit_tr">
	    <th align="right">
	       	模版：
	    </th>
	    <td colspan="3">
	        <input type="button" onclick="outputInviteList()" value="模版下载" >
	    </td>
    </tr> -->
	
    <tr class="edit_tr">
	    <th align="right">
	       <jecs:label key="uploadForm.file" styleClass="desc" />
	    </th>
	    <td colspan="3">
	        <input type="file" name="inviteFile" id="inviteFile" >
	    </td>
    </tr>
    <tr class="edit_tr">
	    <th align="right">
	       <jecs:label key="busi.common.remark" styleClass="desc" />
	    </th>
	    <td colspan="3">
	        <jecs:locale key="busi.common.remark.jpoInviteList" />
	    </td>
    </tr>
    <tr class="edit_tr">
	    <th align="right">
	       <jecs:label key="upload.module" styleClass="desc" />
	    </th>
	    <td colspan="3">
	    	<!-- 更改为模版下载 -->
	    	<a href="./images/uploadInvite.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a>  
	    	<%-- <a href="javascript:void(0)" target="blank" onclick="outputInviteList()">
	    		<img src="<c:url value='/images/extremetable/xls.gif'/>" /></a> --%>
	    </td>
    </tr>
    <tr class="edit_tr">
    <td colspan="4" align="center">&nbsp;</td>
    </tr>
   <tr class="edit_tr">
	    <td class="command"  colspan="4" align="center">
	        <input type="submit" value="导入" class="btn btn_sele">
	        <button type="button" onclick="window.history.go(-1);" class="btn btn_long">返回</button>
	    </td>
    </tr>
    </tbody>
</table>

</form:form>

<!-- <script type="text/javascript">
    Form.focusFirstElement($('jpoInviteListForm'));
</script> -->

<script type="text/javascript">
function outputInviteList(){
	window.location.href='jpoInviteLists.html?strAction=outputInviteList';
}

function validateJpoInviteListUpload(theFrom){
	
	var file=document.getElementById("inviteFile").value;
	
	if(file==""){
        alert("请选择需要导入的模版！");
        return false;
    }else{
		var nameT1=file.lastIndexOf("."); 
		var nameT2=file.length;
		var name=file.substring(nameT1,nameT2);//后缀名
		if(name!='.xls'){
			alert("导入模版格式不正确");
			return false;
		}else{
			return true;
		}
    }
}
</script>
