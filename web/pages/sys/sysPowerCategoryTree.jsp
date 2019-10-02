<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>tree</title>
		<script src="/jecs/scripts/MzTreeView/jquery.js" type=text/javascript></script>
		<script src="/jecs/scripts/MzTreeView/jquery.thickbox.js" type=text/javascript></script>
		<script src="/jecs/scripts/MzTreeView/MzTreeView12.js" type="text/javascript" ></script>
		<script type="text/javascript">
			function addSysPowerCategory(theForm){
				if(theForm.pcId.value==""){
					alert("<jecs:locale key="sys.message.pleaseChoosePerentCategory"/>");
					return;
				}
				var pars=new Array();
				pars[0]="<jecs:locale key="sys.message.addCategory"/>";
				pars[1]="<c:url value="/sys/editSysPowerCategory.html"/>?parentId="+theForm.pcId.value;
				pars[2]=window;
				showDialog("/jecs",pars,800,450);
			}

			function editSysPowerCategory(theForm){
				if(theForm.pcId.value==""){
					alert("<jecs:locale key="sys.message.pleaseChooseEditCategory"/>");
					return;
				}
				var pars=new Array();
				pars[0]="<jecs:locale key="sys.message.editCategory"/>";
				pars[1]="<c:url value="/sys/editSysPowerCategory.html"/>?pcId="+theForm.pcId.value;
				pars[2]=window;
				var ret=showDialog("/jecs",pars,800,450);
			}

			function selectSysPowerCategory(pcId){
				document.powerCateogryForm.pcId.value=pcId;
				window.parent.listFrame.location="powerList.html?action=list&categoryId="+pcId;
			}
		</script>
	</head>
	<body>
		<form name="powerCateogryForm">
			<div id="titleBar">
				<input type="button" class="button" onclick="addSysPowerCategory(this.form)" value="<jecs:locale key="member.new.num"/>" />
				<input type="button" class="button" onclick="editSysPowerCategory(this.form)" value="<jecs:locale key="button.edit"/>" />
			</div>
			<input type="hidden" name="pcId" />
		</form>
		<script type="text/javascript">
		tree = new MzTreeView("tree");
		with(tree)
		{
			<c:choose>
				<c:when test="${param.for=='powerList'}">
					<c:forEach items="${sysPowerCategoryList}" var="row">
						N["${row.parentId}_${row.pcId}"]="T:${row.categoryName};C:selectSysPowerCategory(${row.pcId})";
					</c:forEach>
				</c:when>
			</c:choose>
		}
		tree.wordLine = false;
		tree.setIconPath("/jecs/images/treeimages/");
		document.write(tree.toString());
		</script>
	</body>
</html>
