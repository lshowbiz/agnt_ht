<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="sysModuleList.title"/></title>
<content tag="heading"><jecs:locale key="sysModuleList.heading"/></content>
<meta name="menu" content="SysModuleMenu"/>

<c:if test="${not empty successMessages}">
	<script>
	window.parent.frmSysModuleTree.location.reload();
	</script>
</c:if>

<div class="topNavBar">
	<jecs:locale key="sysModule.current.selected"/>: <b><jecs:locale key="${parentSysModule.moduleName }"/></b>
</div>

<table class='grid_table'>
	<tr class='grid_span'>
		<td>
			<jecs:power powerCode="addSysModule">
			<a href="#" onclick="location.href='<c:url value="/editSysModule.html"/>?strAction=addSysModule&parentId=${param.parentId }'">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key='member.new.num'/></font>
			</a>
			</jecs:power>
		</td>
	</tr>
</table>

<ec:table 
	tableId="sysModuleListTable"
	items="sysModuleList"
	var="sysModule"
	action="${pageContext.request.contextPath}/sysModules.html" 
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" autoIncludeParameters="true" imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="module_id" title="title.operation" sortable="false" style="white-space: nowrap;width:30px;">
			<nobr>
			<jecs:power powerCode="editSysModule">
				<a href="editSysModule.html?strAction=editSysModule&moduleId=${sysModule.module_id }">
				<img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16"/></a>
			</jecs:power>
			&nbsp;
			</nobr>
		</ec:column>
		<ec:column property="module_code" title="sysModule.moduleCode" />
		<ec:column property="module_name" title="sysModule.moduleName" >
			<jecs:locale key="${sysModule.module_name}"/>
		</ec:column>
		<ec:column property="parent_id" title="sysModule.parent" >
			<c:if test="${sysModule.parent_id==0}"><jecs:locale key="node.root.name"/></c:if>
			<c:if test="${sysModule.parent_id!=0}"><jecs:locale key="${sysModule.parent_module_name}"/></c:if>
		</ec:column>
		<ec:column property="module_type" title="sysModule.moduleType" styleClass="centerColumn">
			<jecs:code listCode="module_type" value="${sysModule.module_type}"/>
		</ec:column>
		<ec:column property="link_url" title="sysModule.linkUrl" >
			<jecs:substr key="${sysModule.link_url}" length="20"/>
		</ec:column>
		<ec:column property="order_no" title="sysModule.orderNo" />
		<ec:column property="is_active" title="sysModule.isActive" styleClass="centerColumn">
			<jecs:code listCode="yesno" value="${sysModule.is_active}"/>
		</ec:column>
		<ec:column property="is_validate" title="sysModule.isValidate" styleClass="centerColumn">
			<jecs:code listCode="yesno" value="${sysModule.is_validate}"/>
		</ec:column>
	</ec:row>

</ec:table>	