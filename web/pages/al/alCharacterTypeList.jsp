<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="alCharacterTypeList.title"/></title>
<content tag="heading"><jecs:locale key="alCharacterTypeList.heading"/></content>
<meta name="menu" content="AlCharacterTypeMenu"/>

<link rel="StyleSheet" href="styles/dtree.css" type="text/css" />
<script type="text/javascript" src="scripts/dtree.js"></script>

<form action="" method="get" name="alCharacterTypeForm" id="alCharacterTypeForm">
<input type="hidden" name="typeId"/>

<table class='grid_table'>
	<tr class='grid_span'>
		<td>
			<jecs:power powerCode="addAlCharacterType">
			<a href="#" onclick="addAlCharacterType(document.alCharacterTypeForm)">
				<img alt="<jecs:locale  key='member.new.num'/>" src="images/newIcons/plus_16.gif" border="0" align="absmiddle">
				<jecs:locale key='member.new.num'/>
			</a>
			</jecs:power>
			
			<jecs:power powerCode="editAlCharacterType">
				<a href="#" onclick="editAlCharacterType(document.alCharacterTypeForm)">
					<img src="images/newIcons/pencil_16.gif" border="0" align="absmiddle">
					<jecs:locale key="button.edit"/>
				</a>
			</jecs:power>
		</td>
	</tr>
</table>

<div class="dtree">

<script type="text/javascript">
	d = new dTree('d');
	d.add(0,-1,"<jecs:locale key="alCharacterType.allType"/>","javascript:selectAlCharacterType('')");
	d.add(999999999,0,"<jecs:locale key="alCharacterType.unTyped"/>","javascript:selectAlCharacterType('unTyped')");
	<c:forEach items="${alCharacterTypeList}" var="alVar">
	d.add(${alVar.typeId}, ${alVar.parentId },"<jecs:locale key="${alVar.typeName}"/>","javascript:selectAlCharacterType(${alVar.typeId})");
	</c:forEach>
   
   document.write(d);
</script>

</div>

</form>

<script type="text/javascript">
function addAlCharacterType(theForm){
	if(theForm.typeId.value!="unTyped"){
		var pars=new Array();
		pars[0]="<jecs:locale key="alCharacterType.addType"/>";
		pars[1]="editAlCharacterType.html?strAction=addAlCharacterType&parentId="+theForm.typeId.value;
		pars[2]=window;
		var ret=showDialog("<%=request.getContextPath()%>",pars,400,150);
	}
}

function editAlCharacterType(theForm){
	if(theForm.typeId.value=="" || theForm.typeId.value=="unTyped"){
		alert("<jecs:locale key="alCharacterType.please.select.type.edit"/>");
		return;
	}
	var pars=new Array();
	pars[0]="<jecs:locale key="alCharacterType.editType"/>";
	pars[1]="editAlCharacterType.html?strAction=editAlCharacterType&typeId="+theForm.typeId.value;
	pars[2]=window;
	var ret=showDialog("<%=request.getContextPath()%>",pars,400,150);
}

function selectAlCharacterType(typeId){
	document.alCharacterTypeForm.typeId.value=typeId;
	window.parent.frmCharacterKey.location="alCharacterKeys.html?strAction=listAlCharacterKeys&typeId="+typeId;
}
</script>