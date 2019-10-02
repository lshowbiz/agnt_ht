<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseUserList.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseUserList.heading"/></content>
<meta name="menu" content="PdWarehouseUserMenu"/>
<script src="<c:url value='/scripts/MzTreeView/jquery.js'/>" type="text/javascript"></script>
<script src="<c:url value='/scripts/MzTreeView/jquery.thickbox.js'/>" type="text/javascript"></script>
<script src="<c:url value='/scripts/MzTreeView/MzTreeView12.js'/>" type="text/javascript"></script>

<div id="warehouseTree">

</div>

<script type="text/javascript">
	
tree = new MzTreeView("tree");
tree.setIconPath("<c:url value='/images/treeimages/'/>");
//tree.icons["leafMenu"] = "leafMenu.gif";
//tree.icons["leafModule"] = "leafModule.gif";
//tree.icons["folderMenu"] = "folderClosedMenu.gif";
//tree.icons["folderModule"] = "folderClosedModule.gif";
//
//tree.iconsExpand["folderMenu"]="folderOpenMenu.gif";
//tree.iconsExpand["folderModule"]="folderOpenModule.gif";

with(tree)
	{
	 N["0_999999"]="T:<jecs:locale key='pdAdjustStock.warehouseNo'/>";
	 <c:forEach items="${warehouseList}" var="pdWarehouse" varStatus="status">
	 N["999999_<c:out value='${pdWarehouse.warehouseNo}'/>"]="T:<c:out value='${pdWarehouse.warehouseNo}'/>-<c:out value='${pdWarehouse.warehouseName}'/>;C:L('<c:out value="${pdWarehouse.warehouseNo}"/>')";
			
	 </c:forEach>
	
	}


document.getElementById("warehouseTree").innerHTML=tree.toString();

function L(id)
{
	<c:if test="${param.controlFlag=='user'}">
		parent.frames['pdWarehouseContorl'].location="<c:url value='/sys_my_man_m_tree.html'/>?strAction=pdWarehouseUserTree&companyCode=${companyCode}&warehouseNo="+id;
	</c:if>
	<c:if test="${param.controlFlag=='area'}">
		parent.frames['pdWarehouseContorl'].location="<c:url value='pdWarehouseAreas.html'/>?strAction=pdWarehouseAreaContent&warehouseNo="+id;
	</c:if>
}
</script>
