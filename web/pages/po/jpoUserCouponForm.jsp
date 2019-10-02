<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jpoUserCouponDetail.title" /></title>
<content tag="heading">
<jecs:locale key="jpoUserCouponDetail.heading" /></content>


<spring:bind path="jpoUserCoupon.*">
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


<form:form 
	commandName="jpoUserCoupon" 
	method="post"
	action="editJpoUserCoupon.html"
	enctype="multipart/form-data"
	onsubmit="return validateJpoUserCouponUpload(this)" 
	id="jpoUserCouponForm">
	<table class='detail' width="100%">
		<tbody>
			<tr class="edit_tr">
				<th align="right"><jecs:label key="uploadForm.file" styleClass="desc" /></th>
				<td colspan="3"><input type="file" name="couponFile" id="couponFile"></td>
			</tr>
			<tr class="edit_tr">
				<th align="right"><jecs:label key="busi.common.remark" styleClass="desc" /></th>
				<td colspan="3">格式：<br />1.上传文件为xsl格式（若为xlsx先转换成xls）<br />2.内容格式为三列：<br />第一列：会员编号(必填项),<br />第二列：代金券名称(必填项),<br />第三列：赠送代金券数量(必填项)<br /></td>
			</tr>
			<tr class="edit_tr">
				<th align="right"><jecs:label key="upload.module" styleClass="desc" /></th>
				<td colspan="3"><a href="./images/userCouponExcel.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a></td>
			</tr>
			<tr class="edit_tr">
				<td colspan="4" align="center">&nbsp;</td>
			</tr>
			<tr class="edit_tr">
				<td class="command" colspan="4" align="center">
					<input type="submit" value="导入" class="btn btn_sele">
					<button type="button" onclick="window.history.go(-1);" class="btn btn_long">返回</button></td>
			</tr>
		</tbody>
	</table>

</form:form>

<script type="text/javascript">
function validateJpoUserCouponUpload(theFrom){
var file=document.getElementById("couponFile").value;
	
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

<v:javascript formName="jpoUserCoupon" cdata="false" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
