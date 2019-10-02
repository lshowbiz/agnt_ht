<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysDepartmentList.title"/></title>
<content tag="heading"><jecs:locale key="sysDepartmentList.heading"/></content>
<meta name="menu" content="SysDepartmentMenu"/>

<c:if test="${not empty successMessages}">
	<script>
	window.parent.frmDepartmentTree.location.reload();
	</script>
</c:if>

<div class="topNavBar">
	<jecs:locale key="sysDepartment.departmentTreeTitle"/>, <jecs:locale key="sysDepartment.current.selected"/>: <b>${alCompany.companyName } - <jecs:locale key="${parentSysDepartment.departmentName }"/></b>
</div>

<table class='grid_table'>
	<tr class='grid_span'>
		<td>
			<jecs:power powerCode="addSysDepartment">
				<c:if test="${hasPermit==true}">
			<a href="#" onclick="location.href='<c:url value="/editSysDepartment.html"/>?strAction=addSysDepartment&companyCode=${param.companyCode }&parentId=${param.parentId }'">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale key="member.new.num"/><jecs:locale key="title.department"/></font>
			</a>
			</c:if>
			</jecs:power>
		</td>
	</tr>
</table>

<ec:table 
	tableId="sysDepartmentListTable"
	items="sysDepartmentList"
	var="sysDepartment"
	action="${pageContext.request.contextPath}/sysDepartments.html"
	width="100%"
	retrieveRowsCallback="limit"
	autoIncludeParameters="true"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row onclick="javascript:editSysDepartment('${sysDepartment.department_id}')">
		<ec:column property="department_id" title="title.operation" sortable="false" style="white-space: nowrap;width:5px;">
			<jecs:power powerCode="editSysDepartment">
				<c:if test="${fiPayAdvice.status!=2 && fiPayAdvice.status!=4}">
					<a href="editSysDepartment.html?strAction=editSysDepartment&departmentId=${sysDepartment.department_id}">
					<img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16"/></a>
				</c:if>
			</jecs:power>
			&nbsp;
		</ec:column>
		<ec:column property="parent_id" title="sysDepartment.parentDepartmentName" >
			${sysDepartment.parent_department_name }&nbsp;
		</ec:column>
		<ec:column property="department_name" title="sysDepartment.departmentName" />
		<ec:column property="full_name" title="sysDepartment.fullName" />
		<ec:column property="tel" title="alCompany.phone" />
		<ec:column property="fax" title="sysDepartment.fax" />
		<ec:column property="order_no" title="sysModule.orderNo" />
	</ec:row>

</ec:table>	