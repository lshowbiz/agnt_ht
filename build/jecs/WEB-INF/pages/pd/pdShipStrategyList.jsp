<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdShipStrategyList.title"/></title>
<content tag="heading"><jecs:locale key="pdShipStrategyList.heading"/></content>
<meta name="menu" content="PdShipStrategyMenu"/>

<script src="<c:url value='/scripts/MzTreeView/jquery.js'/>" type="text/javascript"></script>
<script src="<c:url value='/scripts/MzTreeView/jquery.thickbox.js'/>" type="text/javascript"></script>
<script src="<c:url value='/scripts/MzTreeView/MzTreeView12.js'/>" type="text/javascript"></script>

<div id='strategyTree'  ></div>

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
	 N["0_999999"]="T:<jecs:locale key='pd.ship.strategy'/>";
	 <c:forEach items="${strategyMap}" var="strategy" varStatus="status">
	 N["999999_<c:out value='${strategy.key}'/>"]="T:<jecs:locale  key="${strategy.value}"/>;C:L('<c:out value="${strategy.key}"/>')";
			
	 </c:forEach>
	
	}


document.getElementById("strategyTree").innerHTML=tree.toString();

function L(id)
{
		
		parent.frames['strategyContorl'].location="<c:url value='pdShipStrategyDetails.html'/>?strAction=ssDetailList&ssId="+id;
	
}
</script>





