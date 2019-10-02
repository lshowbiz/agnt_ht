<%@ include file="/common/taglibs.jsp"%>
 
<title><jecs:locale key="pdWarehouseUserList.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseUserList.heading"/></content>
<meta name="menu" content="PdWarehouseUserMenu"/>
<script src="<c:url value='/scripts/MzTreeView/jquery.js'/>" type="text/javascript"></script>
<script src="<c:url value='/scripts/MzTreeView/jquery.thickbox.js'/>" type="text/javascript"></script>
<script src="<c:url value='/scripts/MzTreeView/MzTreeView12.js'/>" type="text/javascript"></script>

<div id='productTree'  ></div>

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
	 N["0_999999"]="T:<jecs:locale key='pdWarehouseStock.productNo'/>";
	 <c:forEach items="${productSaleNews}" var="productSaleNew" varStatus="status">
	 N["999999_<c:out value='${productSaleNew.uniNo}'/>"]="T:(<c:out value='${productSaleNew.companyCode}'/>): <c:out value='${productSaleNew.jpmProduct.productNo}'/>-<c:out value='${productSaleNew.productName}'/>;C:L('<c:out value="${productSaleNew.uniNo}"/>','<c:out value="${productSaleNew.jpmProduct.productNo}"/>')";
			
	 </c:forEach>
	
	}


document.getElementById("productTree").innerHTML=tree.toString();

function L(id,productNo)

{
	                                            
	parent.frames['jpmProductContent'].location="./jpmProductSaleRelateds.html?strAction=jpmProductSaleRelated&uniNo="+id+"&productNo="+productNo;
	
}
</script>

