<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCharacterTypeList.title"/></title>
<content tag="heading"><jecs:locale key="alCharacterTypeList.heading"/></content>
<meta name="menu" content="AlCharacterTypeMenu"/>

<link rel="StyleSheet" href="../styles/dtree.css" type="text/css" />
<script type="text/javascript" src="../scripts/dtree.js"></script>

<form action="" method="get" name="alCharacterTypeForm" id="alCharacterTypeForm">

<div class="dtree">
<script type="text/javascript">
	d = new dTree('d');
	d.add(0,-1,"<jecs:locale key="webapp.company.title"/>","");
	<c:forEach items="${jamMsnTypes }" var="jamMsnType">
		
		d.add(${jamMsnType.mtId}, 0,"<jecs:locale key="${jamMsnType.msnName }"/>","javascript:selectType('${jamMsnType.mtId}')");
	
	</c:forEach>
   
   document.write(d);
</script>

</div>

</form>

<script type="text/javascript">
function selectType(mtId){
	window.parent.frmModuleList.location="jamMsnModules.html?mtId="+mtId;
}
</script>