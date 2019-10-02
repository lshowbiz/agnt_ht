<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysRoleDetail.title"/></title>
<content tag="heading"><jecs:locale key="sysRoleDetail.heading"/></content>

<form:form commandName="sysUser" method="post" action="sys_power_simple.html" onsubmit="return validateOthers(this)" id="sysPowerForm" name="sysPowerForm">
<input type="hidden" name="userCode" value="${param.userCode }"/>
<input type="hidden" name="strAction" value="sysPowerSimple"/>

<table width="100%" height="100%" border="0">
	<tr>
		<td height="20">
			<jecs:locale key="give.power.to" />
			<fmt:param value="${param.userCode}"></fmt:param>
		. 
		<jecs:locale key="please.select.company"/>:
			<select name="companyCode" onChange="changeCompany(this.form)">
				<c:forEach items="${alCompanys}" var="alCompanyVar">
					<option value="${alCompanyVar.companyCode }"<c:if test="${alCompanyVar.companyCode==companyCode}"> selected</c:if>>${alCompanyVar.companyCode } - ${alCompanyVar.companyName}</option>
				</c:forEach>
			</select>		</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>
<ec:table 
	tableId="sysModuleListTable"
	items="sysModules"
	var="sysModule"
	form="sysPowerForm"
	width="100%"
	sortable="false" autoIncludeParameters="false" imagePath="./images/extremetable/*.gif" showPagination="false" showStatusBar="false">
	<ec:row>
		<ec:column alias="moduleId" headerCell="selectAll" style="width:5px;">
			<input type="checkbox" name="moduleId" value="${sysModule.module_id}" class="checkbox" <c:if test="${not empty sysModule.slave_power_id}"> checked</c:if>/>
		</ec:column>
		<ec:column property="module_name" title="sysModule.moduleName" >
			<c:forEach begin="1" end="${sysModule.tree_level-1}">　　</c:forEach>
			<c:if test="${sysModule.tree_level==1}"><font color="#990000"></c:if>
			<jecs:locale key="${sysModule.module_name}"/>
			<c:if test="${sysModule.tree_level==1}"></font></c:if>
		</ec:column>
	</ec:row>

</ec:table>	

<table width="100%">
    <tr>
		<td align="center">
			<jecs:power powerCode="sysPowerSimple">
				<input type="submit" class="button" name="save"  onclick="bCancel=false"<jecs:locale:message key="button.submit"/>" />
			</jecs:power>
	
			<input type="button" class="button" name="cancel" onclick="toback()"<jecs:locale:message key="operation.button.return"/>" />
		</td>
	</tr>

</table>

</form:form>

<script type="text/javascript">
function validateOthers(theForm){
	return isFormPosted();
}

function toback(){
    this.location='<c:url value="/sysAccounts.html"/>?needReload=1';
}

function changeCompany(theForm){
	window.location="sys_power_simple.html?userCode=${param.userCode}&companyCode="+theForm.companyCode.value;
}
</script>

<v:javascript formName="sysRole" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript"  src="<c:url value="/scripts/validator.jsp"/>"></script>