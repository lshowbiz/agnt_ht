<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alBranchList.title"/></title>
<content tag="heading"><jecs:locale key="alBranchList.heading"/></content>
<meta name="menu" content="AlBranchMenu"/>

<script src="./scripts/MzTreeView/jquery.js" type="text/javascript"></script>
<script src="./scripts/MzTreeView/jquery.thickbox.js" type="text/javascript"></script>
<script src="./scripts/MzTreeView/MzTreeView12.js" type="text/javascript" ></script>

<script type="text/javascript">
tree = new MzTreeView("tree");

with(tree){
	N["0_A"]="T:<jecs:locale key="alCharacterType.allType"/>;C:doSelectCharacterType('0','<jecs:locale key="alCharacterType.allType"/>');";
	<c:if test="${not empty alCharacterTypeList}">
		<c:forEach items="${alCharacterTypeList}" var="alVar">
			<c:set var="parentId" value="${alVar.parentId}"/>
			<c:if test="${alVar.parentId==0}">
				<c:set var="parentId" value="A"/>
			</c:if>
			N["${parentId}_${alVar.typeId}"]="T:<jecs:locale key="${alVar.typeName}"/>;C:doSelectCharacterType('${alVar.typeId}','<jecs:locale key="${alVar.typeName}"/>');";
		</c:forEach>
	</c:if>
}
tree.wordLine = false;
tree.showRootFirst=true;
tree.setIconPath("./images/treeimages/");
document.write(tree.toString());

<c:if test="${param.typeId=='unTyped'}">
tree.focus('0');
</c:if>
<c:if test="${param.typeId!='unTyped'}">
tree.focus('${param.typeId}');
</c:if>

function doSelectCharacterType(typeId, typeName){
	if(typeId!="" && typeId!=0){
		var obj=new Object();
		obj.typeId=typeId;
		obj.typeName=typeName;
		window.top.returnValue=obj;
		window.top.close();
	}
}
</script>