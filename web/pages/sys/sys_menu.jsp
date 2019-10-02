<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<style type="text/css">
html {
	overflow-y:auto!important;
	*overflow-y:scroll;
	height:100%;
	width:100%;
} 

body {
	background: #dfe6f5;
    color: #000000;
    font-family: "Microsoft YaHei";
    font-size: 13px;
    margin: 0;
    padding: 0;
}

td,th{
	font-size:13px;
	font-family: "Microsoft YaHei";
}

div{
	font-size: 13px;
}

span{
	font-size: 13px;
}

a:link {
	color: #000000;
	text-decoration: none;
}
a:visited {
	text-decoration: none;
	color: #000000;
}
a:hover {
	text-decoration: underline;
}
a:active {
	text-decoration: none;
	color: #FF0000;
}

.menu {
	cursor: pointer;
	width: 100%;
	font-size: 13px;
	padding-left: 0px;
	padding-top: 1px;
	padding-bottom:1px;
	color: #000000;
	overflow: hidden;
	text-overflow: ellipsis;
	height: 24x;
    line-height: 24px;
	margin:1px 0;
}
.menu_down {
	background-color: #fff;
	cursor: pointer;
	width: 100%;
	font-size: 13px;
	padding-left: 0px;
	padding-top: 1px;
	padding-bottom:1px;
	color: #000000;
	overflow: hidden;
	text-overflow: ellipsis;
	height: 24px;
    line-height: 24px;
	margin:1px 0;

}
.submenu {
	background-color: #fff;
	width: 100%;
	cursor: pointer;
	font-size: 12px;
	padding-left: 0px;
	padding-top: 1px;
	padding-bottom:1px;
	color: #666666;
	overflow: hidden;
	text-overflow: ellipsis;
	line-height:24px
}
.submenu_down {
	background-color: #fff;
	width: 100%;
	cursor: pointer;
	font-size: 12px;
	padding-left: 0px;
	padding-top: 1px;
	padding-bottom:1px;
	color: #666666;
	overflow: hidden;
	text-overflow: ellipsis;
line-height:24px
}
.submenu_active {
	cursor: pointer;
	width: 100%;
	font-size: 13px;
	padding-left: 0px;
	padding-top: 1px;
	padding-bottom:1px;
	color: #000000;
	overflow: hidden;
	text-overflow: ellipsis;
	border-bottom: 1px solid #c6c6c6;
}

</style>
</head>
<body>
	<div style="display:none">
		<img src="images/menu_tree/L0.gif"/>
		<img src="images/menu_tree/L1.gif"/>
		<img src="images/menu_tree/L2.gif"/>
		<img src="images/menu_tree/L3.gif"/>
		<img src="images/menu_tree/L4.gif"/>
		<img src="images/menu_tree/L5.gif"/>
		<img src="images/menu_tree/P0.gif"/>
		<img src="images/menu_tree/P1.gif"/>
		<img src="images/menu_tree/P2.gif"/>
		<img src="images/menu_tree/P3.gif"/>
		<img src="images/menu_tree/M0.gif"/>
		<img src="images/menu_tree/M1.gif"/>
		<img src="images/menu_tree/M2.gif"/>
		<img src="images/menu_tree/M3.gif"/>
	</div>
<div id="treeView"></div>
	<script src="./scripts/MzTreeView/jquery.js"></script>
	<script src="./scripts/MzTreeView/jquery.thickbox.js"></script>
	<script src="./scripts/MzTreeView/MzTreeView13_menu.js" ></script>

	<script>
	var arrayObj = new Array();
	var arrayObjUrl = new Array();
	
	<c:if test="${not empty sysModules}">
		<c:forEach items="${sysModules}" var="row" varStatus="status">
			
			tree${status.count-1} = new MzTreeView("tree"+"${status.count-1}");
			tree${status.count-1}.setRoot("${row.module_id}");
			with(tree${status.count-1}){
				<c:if test="${not empty row.menu_list}">
					<c:forEach items="${row.menu_list}" var="menu_var">
						<c:set var="menuUrl" value="${menu_var.link_url}"/>
						N["${menu_var.parent_id}_${menu_var.module_id}"]="T:<jecs:locale key="${menu_var.module_name}"/>;url:<c:url value="${menuUrl}"/><c:if test="${menuUrl!=null}">;C:L('${status.count-1}')</c:if>";
					</c:forEach>
				</c:if>
			}
			tree${status.count-1}.wordLine = false;
			tree${status.count-1}.onClickOpen=true;
			tree${status.count-1}.showRootFirst=false;
			tree${status.count-1}.setIconPath("./images/menu_tree/");
			tree${status.count-1}.setTarget("contentFrm");
			//document.write(tree.toString());
			arrayObj.push(tree${status.count-1}.toString()+"<input type='hidden' id='tree_num' value='${status.count-1}' />");
			arrayObjUrl.push(tree${status.count-1});
			
		</c:forEach>
	</c:if>

	
	function L(num){
		var n;
		var text="";

		n=arrayObjUrl[num].currentNode;

		if(n && n.url!="" && n.url!="#"){
			//window.top.contentFrm.location=n.url;
			window.parent.addTab(n.T,n.url,n.id+num);
		}

		//text=n.T;
		//n=n.parentNode;
		//while(n!=null){
		//	if(n.T!=undefined) text=n.T+">>"+text;
		//	n=n.parentNode;
		//}
		//window.top.document.getElementById("menuLabel").innerHTML=text;

	}
	function viewTree(num){
		if($('#treeView').find('#tree_num').val()!=null){
			//console.log('222222222222');   
			arrayObj[$('#treeView').find('#tree_num').val()]=document.getElementById("treeView").innerHTML;
		}
		
		document.getElementById("treeView").innerHTML=arrayObj[num];
		
	}

	
	</script>

</body>
</html>