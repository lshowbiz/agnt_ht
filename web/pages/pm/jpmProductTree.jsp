<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseUserList.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseUserList.heading"/></content>
<meta name="menu" content="PdWarehouseUserMenu"/>
<script src="<c:url value='/scripts/MzTreeView/jquery.js'/>" type="text/javascript"></script>
<script src="<c:url value='/scripts/MzTreeView/jquery.thickbox.js'/>" type="text/javascript"></script>
<script src="<c:url value='/scripts/MzTreeView/MzTreeView12.js'/>" type="text/javascript"></script>


<form action="jpmProductTree.html" method="get" name="pmProductTreeQueryFrom" id="pmProductTreeQueryFrom">
       <table>
          <input name="strAction" id="strAction" type="text" value="combineProduct" style="display:none"/>
          <td nowrap="nowrap"><jecs:label key="jpmProductSaleNew.productNo"/></td>
	      <td><input name="productNo" id="productNo" type="text" value="${param.productNo }"/></td>
          <td>
          
          <button name="query"  type="button" class="btn btn_sele" style="width:auto" size="1" onclick="queryJpmProductTree(document.pmProductTreeQueryFrom)">
          	<jecs:locale key="operation.button.search"/>
          </button></td>
       </table>
</form>


<div id='productTree'></div>

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
	 <c:forEach items="${products}" var="product" varStatus="status">
	 N["999999_<c:out value='${product.productNo}'/>"]="T:<c:out value='${product.productNo}'/>-<c:out value='${product.productName}'/>;C:L('<c:out value="${product.productNo}"/>')";
			
	 </c:forEach>
	
	}


document.getElementById("productTree").innerHTML=tree.toString();

function L(id)

{
	
	parent.frames['jpmProductContent'].location="<c:url value='./jpmProductCombinations.html?strAction=jpmProductCombination&productNo='/>"+id;
	
}

function queryJpmProductTree(theForm){
   theForm.submit();
}

</script>

