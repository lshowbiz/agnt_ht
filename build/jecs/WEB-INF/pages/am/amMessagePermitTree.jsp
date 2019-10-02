<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="pdWarehouseUserList.title"/></title>
<content tag="heading"><jecs:locale key="pdWarehouseUserList.heading"/></content>
<meta name="menu" content="PdWarehouseUserMenu"/>
<script type="text/javascript" src="./scripts/dtree.js"></script>









<form action="" method="get" name="alCharacterTypeForm" id="alCharacterTypeForm">

 
<div class="dtree">
<script type="text/javascript">
d = new dTree('d');
d.add(0,-1,"<jecs:locale key="amMessage.msgClassNo"/>","");
<c:forEach items="${msgTypes }" var="msgType">
	//d.add(${msgType.valueCode}, 0,"<jecs:locale key="${msgType.valueTitle }"/>","javascript:selectType('${msgType.valueCode}')");

	var valueTitle = "<jecs:locale key="${msgType.valueTitle }"/>";
	var title = "<jecs:locale key="${msgType.valueTitle }"/>";
	if(valueTitle.length > 8){ 
		valueTitle = valueTitle.substr(0,8) + "...";
	}
	d.add(${msgType.valueCode}, 0,valueTitle,"javascript:selectType('${msgType.valueCode}')",title);
	
</c:forEach>

document.write(d);
</script>

</div>

</form>

<script type="text/javascript">
function selectType(msgClassNo){
	window.parent.amMessagePermitContorl.location="<c:url value='amMessagePermits.html'/>?strAction=amMessagePermitUserContent&msgClassNo="+msgClassNo;
}
</script>

















