<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="miLinkRefList.title"/></title>
<content tag="heading"><jecs:locale key="miLinkRefList.heading"/></content>

<script>
function loading(){
	var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>';
	str += '&nbsp;&nbsp;<jecs:locale key="button.loading"/>';
	document.getElementById("kkk").innerHTML=str;
} 
</script>
<img style="display:none" src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>

<form action="" method="get" name="miMemberForm" id="miMemberForm" onsubmit='document.getElementById("search").disabled = true;'>
<div class="searchBar">

<div class="new_searchBar">		
	<jecs:label key="bdBounsDeduct.wweek" />	
	<input type="text" name="formatedWeek" id="formatedWeek" size="8" value="${param.formatedWeek}" />
	</div>
	
<div class="new_searchBar">			
<jecs:label key="miMember.memberNo"/>
				<input size="10" name="userCode" type="text" value="${param.userCode}"/>
</div>

<div class="new_searchBar">		
	<jecs:label key="bdBonus.tree.level" />
	<input type="text" name="showLevel" value="${param.showLevel }" />
</div>

<div class="new_searchBar" style="margin-left: 60px">		
				<button name="search" id="search" type="submit" onclick='loading();' class="btn btn_sele" >
					<jecs:locale key="operation.button.search"/>
				</button>
				<button type="button" class="btn btn_long" name="expandAll"  onclick="tree.expandAll();" >
					<jecs:locale key="button.expandAll"/>
				</button>
				</div>
	
</div>
</form>

<table style="width: 70%;margin-left: 60px;">
<tr>
	<td><div style="float: left;">会员编号 - 会员姓名 - [身份] -  [业绩] - [审核日期] - 状态(授权/停权)</div></td>
</tr>
<tr>
	<td><div id="kkk"></div></td>
</tr>
</table>

<script language="javaScript">localMsg ='<jecs:locale key="miMember.localMsg"/>';</script>
<script language="javaScript" src="scripts/MzTreeViewRef.js"></script>
<script language="javascript" type="text/javascript">
loading();
window.tree = new MzTreeView("tree");
tree.setIconPath("images/treeimages/");
<c:if test="${not empty bdlist}">

	<c:forEach items="${bdlist}" var="curBd" varStatus="status">

		<c:if test="${status.count == 1}">
			tree.N["0_<c:out value="${curBd.user_code}"/>"] = "T:<font color=#0066CC><c:out value="${curBd.user_code} - ${curBd.user_name}"/>  - [<jecs:code listCode="grade.type" value="${curBd.grade_type}"/>] - [<c:out value="${curBd.ys_pv}"/>] - [<fmt:formatDate value="${curBd.check_date }" pattern="yyyy-MM-dd"/>]  -  <c:if test="${curBd.state=='1'}">授权</c:if><c:if test="${curBd.state=='0'}">停权</c:if> </font> ;";
		</c:if>
		<c:if test="${status.count != 1}">
			
			tree.N["<c:out value="${curBd.link_no}"/>_<c:out value="${curBd.user_code}"/>"] = "T:<font color=#0066CC><c:out value="${curBd.user_code} - ${curBd.user_name}"/>  - [<jecs:code listCode="grade.type" value="${curBd.grade_type}"/>] - [<c:out value="${curBd.ys_pv}"/>] - [<fmt:formatDate value="${curBd.check_date }" pattern="yyyy-MM-dd"/>]   -  <c:if test="${curBd.state=='1'}">授权</c:if><c:if test="${curBd.state=='0'}">停权</c:if> </font> ;";
			
		</c:if>
	</c:forEach>
</c:if>
window.tree.showRootFirst=false;
tree.wordLine = false;
document.getElementById("kkk").innerHTML=tree.toString();
</script>
