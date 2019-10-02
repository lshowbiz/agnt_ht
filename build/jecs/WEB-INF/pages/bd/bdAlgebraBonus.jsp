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
		<input type="text" name="formatedWeek" id="formatedWeek" value="${param.formatedWeek}" 
			title="<jecs:locale key="label.example"/>200806" />
	</div>
	<div class="new_searchBar">
		<jecs:label key="miMember.memberNo"/>
		<input size="10" name="userCode" type="text" value="${param.userCode}"/>
	</div>
	<div class="new_searchBar">
		<jecs:label key="customerRecord.type"/>
        <jecs:list name="type" listCode="algebra.type" value="${param.type}" defaultValue="" showBlankLine="true"/>
    </div>
    <div class="new_searchBar"> 	
		<jecs:label key="pass.star.type"/>
        <jecs:list name="passStar" listCode="algebra.pass.star" value="${param.passStar}" defaultValue="" showBlankLine="true"/>	
	</div>
	<div class="new_searchBar">
		<jecs:label key="bdBonus.tree.level" />
		<input type="text" name="showLevel" value="${param.showLevel }" size="4"/>
	</div>
	<div class="new_searchBar">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button name="search" id="search" type="submit" onclick='loading();' class="btn btn_sele">
			<jecs:locale key="operation.button.search"/>
		</button>
		<button type="button" class="btn btn_long" name="expandAll"  onclick="tree.expandAll();">
			<jecs:locale key="button.expandAll"/>
		</button>
		<%-- 
		<input name="search" id="search" type="submit" onclick='loading();' class="button" 
			value="<jecs:locale key="operation.button.search"/>"/>
		<input type="button" class="button" name="expandAll"  onclick="tree.expandAll();" 
			value="<jecs:locale key="button.expandAll"/>" />
		--%>
	</div>	

</div>
</form>
<%-- <div><jecs:locale key="miLinkRef.total"/>:${empty miMemberCount?0:miMemberCount }</div> --%>
<div id="kkk"></div>
<script language="javaScript">localMsg ='<jecs:locale key="miMember.localMsg"/>';</script>
<script language="javaScript" src="scripts/MzTreeViewRef.js"></script>
<script language="javascript" type="text/javascript">
loading();
window.tree = new MzTreeView("tree");
tree.setIconPath("images/treeimages/");
<c:if test="${not empty bdlist}">

	<c:forEach items="${bdlist}" var="curBd" varStatus="status">

		<c:if test="${status.count == 1}">
			tree.N["0_<c:out value="${curBd.user_code}"/>"] = "T:<font color=#0066CC><c:out value="${curBd.user_code} - ${curBd.pet_name}"/> - <c:if test="${param.type==1}"> [<jecs:code listCode="pass.star.zero" value="${curBd.pass_star}"/>] </c:if> <c:if test="${param.type==2}"> [<jecs:code listCode="isstore" value="${curBd.isstore}"/></c:if> <c:if test="${param.type==3}"> [<jecs:code listCode="algebra.cio.type" value="${curBd.cio_status}"/>] </c:if> </font> ;";
		</c:if>
		<c:if test="${status.count != 1}">
			
			tree.N["<c:out value="${curBd.link_no}"/>_<c:out value="${curBd.user_code}"/>"] = "T:<font color=#0066CC><c:out value="${curBd.user_code} - ${curBd.pet_name}"/> - <c:if test="${param.type==1}"> [<jecs:code listCode="pass.star.zero" value="${curBd.pass_star}"/>] </c:if> <c:if test="${param.type==2}"> [<jecs:code listCode="isstore" value="${curBd.isstore}"/></c:if> <c:if test="${param.type==3}"> [<jecs:code listCode="algebra.cio.type" value="${curBd.cio_status}"/>] </c:if> </font>;";
			
		</c:if>
	</c:forEach>
</c:if>
window.tree.showRootFirst=false;
tree.wordLine = false;
document.getElementById("kkk").innerHTML=tree.toString();
</script>
