<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jsysResourceList.title"/></title>
<content tag="heading"><jecs:locale key="jsysResourceList.heading"/></content>
<meta name="menu" content="JsysResourceMenu"/>

<c:if test="${not empty successMessages}">
	<script>
	window.parent.frmJsysResourceTree.location.reload();
	</script>
</c:if>

<div class="topNavBar">
	<jecs:locale key="sysResource.current.selected"/>: <b><jecs:locale key="${parentSysResource.resName }"/></b>
</div>

<table class='grid_table'>
	<tr class='grid_span'>
		<td>
			<jecs:power powerCode="addJsysResource">
			<a href="#" onclick="location.href='<c:url value="/editJsysResource.html"/>?strAction=addJsysResource&parentId=${param.parentId}'">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<font color=black><jecs:locale  key='member.new.num'/></font>
			</a>
			</jecs:power>
		</td>
	</tr>
</table>

<ec:table 
	tableId="jsysResourceListTable"
	items="jsysResourceList"
	var="jsysResource"
	action="${pageContext.request.contextPath}/jsysResources.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="50" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="images/newIcons/pencil_16.gif" border="0" width="16" height="16" 
								onclick="javascript:editJsysResource('${jsysResource.res_id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
					
    			
    			<ec:column property="res_code" title="jsysResource.resCode" />
    			<ec:column property="res_name" title="jsysResource.resName" />
    			<ec:column property="parent_id" title="jsysResource.parentId" >
    				<c:if test="${jsysResource.parent_id==0}"><jecs:locale key="node.root.name"/></c:if>
					<c:if test="${jsysResource.parent_id!=0}"><jecs:locale key="${jsysResource.parent_res_name}"/></c:if>
    			</ec:column>
    			
    			<ec:column property="res_type" title="jsysResource.resType"  styleClass="centerColumn">
    				<jecs:code listCode="res_type" value="${jsysResource.res_type}"/>
    			</ec:column>
    			
    			
    			<ec:column property="sys_type" title="jsysResource.sysType" >
    				<jecs:code listCode="sys_type" value="${jsysResource.sys_type}"/>
    			</ec:column>
    			<ec:column property="res_url" title="jsysResource.resUrl" />
    			<ec:column property="res_des" title="jsysResource.resDes" />
    			<ec:column property="order_no" title="jsysResource.orderNo" />
    			<ec:column property="active" title="jsysResource.active" >
    				<jecs:code listCode="yesno" value="${jsysResource.active}"/>
    			</ec:column>
    			<ec:column property="validate_flag" title="jsysResource.validateFlag" >
    				<jecs:code listCode="yesno" value="${jsysResource.validate_flag}"/>
    			</ec:column>
    			
    			<%--
    			<ec:column property="treeIndex" title="jsysResource.treeIndex" />
    			<ec:column property="treeLevel" title="jsysResource.treeLevel" />
    			 --%>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJsysResource(resId){
   		<jecs:power powerCode="editJsysResource">
					window.location="editJsysResource.html?strAction=editJsysResource&resId="+resId;
			</jecs:power>
		}
</script>
