<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>

<title><jecs:locale key="jpoMemberNycDetail.title"/></title>
<content tag="heading"><jecs:locale key="jpoMemberNycDetail.heading"/></content>


<%--<spring:bind path="jpoMemberNyc.*">--%>
<%--<c:if test="${not empty status.errorMessages}">--%>
<%--<div class="error">--%>
<%--<c:forEach var="error" items="${status.errorMessages}">--%>
<%--<img src="<c:url value="/images/iconWarning.gif"/>"--%>
<%--alt="<jecs:locale key="icon.warning"/>" class="icon" />--%>
<%--<c:out value="${error}" escapeXml="false"/><br />--%>
<%--</c:forEach>--%>
<%--</div>--%>
<%--</c:if>--%>
<%--</spring:bind>--%>

<spring:bind path="importJpoMemberNyc.*">
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

<form:form commandName="importJpoMemberNyc" method="post" action="importJpoMemberNyc.html" enctype="multipart/form-data"
           onsubmit="return validateJpoMemberNyc(this)" id="importJpoMemberNycForm">

    <div id="titleBar">
        <c:out value="${buttons}" escapeXml="false"/>
    </div>
    <input type="hidden" name="strAction" value="${param.strAction }"/>

    <!--fieldset style="padding: 2">
    <legend>
    <input type="submit" class="button" name="save" onclick="bCancel=false" value="<jecs:locale
        key="button.save"/>" />
    <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpoMemberNyc')" value="<jecs:locale
        key="button.delete"/>" />
    <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale
        key="button.cancel"/>" />

    </legend-->
    <table class='detail' width="70%">
			
        <form:hidden path="id"/>
		<tbody class="window">				
			<tr class="edit_tr">
        <th width="30%">
            <label for="xlsFile" class="required">
                <span class="req">*</span>
                <jecs:locale key="fiPayData.importFile"/>:
            </label>
        </th>
            <td align="left"><input type="file" name="xlsFile" id="xlsFile" size="50"/></td>
        </tr>
        <tr  class="edit_tr">
            <th><jecs:label key="busi.common.remark" /></th>
            <td>

                     所导入的文件格式说明:<br/>
		1.第一行为抬头，不转入<br/>
		2.第1至第4列为： 期别(必填),会员编号(必填) , 推送时间(必填,格式：yyyy-MM-dd 例如：2016-09-07) , 备注(必填)<br/>
                
                <span style="color: red;font-size: 18px">注意：格式不对的行将会被忽略掉</span>

            </td>
        </tr>

		<%-- 加入下载模板 --%>
		<tr class="edit_tr">
			<th>
				<jecs:label key="upload.module"/>
			</th>
			<td colspan="3">	  
				
				<a href="./images/upload_nyc_import.xls" target="blank"><img src="<c:url value='/images/extremetable/xls.gif'/>" /></a>
			</td>
		</tr>
		
		
        <tr  class="edit_tr">
            <td class="command" colspan="4" align="center">
                <input type="hidden" name="strAction" value="importPoMemberOrder"/>
                
				<button type="submit" class="btn btn_sele" name="import"	onclick="return validatorForm()"><jecs:locale key="button.import"/></button>
				<button type="button" class="btn btn_sele" name="back"	onclick="location.href='${ctx}/jpoMemberNycs.html'"><jecs:locale key="operation.button.return"/></button>
            </td>
        </tr>

    </table>
    <!--/fieldset-->

    <!--table class='detail' width="100%">
    <tr>
    <td>
    <input type="submit" class="button" name="save" onclick="bCancel=false" value="<jecs:locale
        key="button.save"/>" />
    <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('JpoMemberNyc')" value="<jecs:locale
        key="button.delete"/>" />
    <input type="submit" class="button" name="cancel" onclick="bCancel=true" value="<jecs:locale
        key="button.cancel"/>" />
    </td></tr>
    </table-->
</form:form>

<script type="text/javascript">
    Form.focusFirstElement($('jpoMemberNycForm'));
    
    function validatorForm() {
        var files = document.getElementById("xlsFile").value;
        if(!files||files.length==0){
            alert("<jecs:locale key="pleace.select.file"/>");
            return false;
        }
        bCancel=false;
        return true;
    }
</script>




<v:javascript formName="jpoMemberNyc" cdata="false" dynamicJavascript="true"
              staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
