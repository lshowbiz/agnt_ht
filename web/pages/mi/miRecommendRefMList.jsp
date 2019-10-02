<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="miRecommendRefList.title"/></title>
<content tag="heading"><jecs:locale key="miRecommendRefList.heading"/></content>

<script>
function loading(){
	var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>';
	str += '&nbsp;&nbsp;<jecs:locale key="button.loading"/>';
	document.getElementById("kkk").innerHTML=str;
}
</script>
<img style="display:none" src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>

<form action="" method="get" name="miMemberForm" id="miMemberForm" onsubmit='document.getElementById("search").disabled = true;'>
<div class="searchBar">&nbsp;&nbsp;
<jecs:locale key="miMember.memberNo"/>
				<input name="memberNo" size="10" type="text" value="${empty param.memberNo ? memberNo : param.memberNo }"/>
<jecs:locale key="miMember.layerId"/>
				<select name="layerId">
				<c:forEach items='${layerList}' var='item'>
				  <option value="${item}" ${item==param.layerId?"selected=selected":"" }>${item}</option>
				</c:forEach>
				</select>
				<%--<c:if test="${sessionScope.sessionLogin.userType=='C' }">
					<input name="layerId" size="1" type="text" value="${empty param.layerId ? layerId : param.layerId }"/>
				</c:if>--%>
				<input name="search" id="search" type="submit" onclick='loading();' class="button" value="<jecs:locale key="operation.button.search"/>"/>
				<input type="button" class="button" name="expandAll"  onclick="tree.expandAll();" value="<jecs:locale key="button.expandAll"/>" />

	
</div>
</form>
<div><jecs:locale key="miRecommendRef.total"/>:${empty miMemberCount?0:miMemberCount }</div>
<div id="kkk"></div>
<script language="javaScript">localMsg ='<jecs:locale key="miMember.localMsg"/>';</script>
<script language="javaScript" src="scripts/MzTreeViewRef.js"></script>
<script language="javascript" type="text/javascript">
loading();
window.tree = new MzTreeView("tree");
tree.setIconPath("images/treeimages/");
<c:if test="${not empty miRecommendRefForm}">
<c:if test="${sessionScope.sessionLogin.userType!='C'}">
	<c:forEach items="${miRecommendRefList}" var="mi" varStatus="status">
	<c:if test="${mi.jmiMember.cardType==0 }">
		<c:set var="cardType" value="#999999"/>
	</c:if>
	<c:if test="${mi.jmiMember.cardType==1 }">
		<c:set var="cardType" value="#009900"/>
	</c:if>
	<c:if test="${mi.jmiMember.cardType==2 }">
		<c:set var="cardType" value="#006666"/>
	</c:if>
	<c:if test="${mi.jmiMember.cardType==3 }">
		<c:set var="cardType" value="#0033CC"/>
	</c:if>
	<c:if test="${mi.jmiMember.cardType==4 }">
		<c:set var="cardType" value="#CC0000"/>
	</c:if>
		<c:if test="${status.count == 1}">
			<c:set var="rootTreeIndex" value="${fn:length(mi.treeIndex)}"/>
			tree.N["0_<c:out value="${mi.userCode}"/>"] = "T:<font color=#0066CC><c:out value="${mi.userCode} - ${mi.jmiMember.petName}"/></font> [ <font color='${cardType}'><jecs:code listCode="bd.cardtype" value="${mi.jmiMember.cardType}"/></font> ] <font color=#FF0000><jecs:locale key="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0?'login.Out':'' }"/></font>[<jecs:locale key="${(not empty mi.jmiMember.checkDate)?('logType.C'):('pd.createTime') }"/>:<fmt:formatDate value="${(not empty mi.jmiMember.checkDate)?(mi.jmiMember.checkDate):(mi.jmiMember.createTime) }" pattern="yyyy-MM-dd"/>];";
		</c:if>
		<c:if test="${status.count != 1}">
			tree.N["<c:out value="${mi.jmiMember.recommendNo}"/>_<c:out value="${mi.userCode}"/>"] = "T:<font color=#0066CC><c:out value="${mi.userCode} - ${mi.jmiMember.petName}"/></font> [ <font color='${cardType}'><jecs:code listCode="bd.cardtype" value="${mi.jmiMember.cardType}"/></font> ] - <font color=#FF0000><fmt:formatNumber value="${(fn:length(mi.treeIndex)-rootTreeIndex)/3}" pattern="##"/></font> <font color=#FF0000><jecs:locale key="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0?'login.Out':'' }"/></font>[<jecs:locale key="${(not empty mi.jmiMember.checkDate)?('logType.C'):('pd.createTime') }"/>:<fmt:formatDate value="${(not empty mi.jmiMember.checkDate)?(mi.jmiMember.checkDate):(mi.jmiMember.createTime) }" pattern="yyyy-MM-dd"/>];";
		</c:if>
	</c:forEach>
</c:if>

<c:if test="${sessionScope.sessionLogin.userType=='C'}">
	<c:forEach items="${miRecommendRefList}" var="mi" varStatus="status">
	<c:if test="${mi.jmiMember.cardType==0 }">
		<c:set var="cardType" value="#999999"/>
	</c:if>
	<c:if test="${mi.jmiMember.cardType==1 }">
		<c:set var="cardType" value="#009900"/>
	</c:if>
	<c:if test="${mi.jmiMember.cardType==2 }">
		<c:set var="cardType" value="#006666"/>
	</c:if>
	<c:if test="${mi.jmiMember.cardType==3 }">
		<c:set var="cardType" value="#0033CC"/>
	</c:if>
	<c:if test="${mi.jmiMember.cardType==4 }">
		<c:set var="cardType" value="#CC0000"/>
	</c:if>
		<c:if test="${status.count == 1}">
			<c:set var="rootTreeIndex" value="${fn:length(mi.treeIndex)}"/>
			tree.N["0_<c:out value="${mi.userCode}"/>"] = "T:<font color=#0066CC><c:out value="${mi.userCode} - ${mi.jmiMember.petName}"/></font> [ <font color='${cardType}'><jecs:code listCode="bd.cardtype" value="${mi.jmiMember.cardType}"/></font> ] <font color=#FF0000><jecs:locale key="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0?'login.Out':'' }"/></font>[<jecs:locale key="${(not empty mi.jmiMember.checkDate)?('logType.C'):('pd.createTime') }"/>:<fmt:formatDate value="${(not empty mi.jmiMember.checkDate)?(mi.jmiMember.checkDate):(mi.jmiMember.createTime) }" pattern="yyyy-MM-dd"/>];";
		</c:if>
		<c:if test="${status.count != 1}">
			<c:if test="${mi.userCode=='haiwaixian'}">
			tree.N["<c:out value="${mi.jmiMember.recommendNo}"/>_<c:out value="${mi.userCode}"/>"] = "T:<img src=images/newIcons/block_16.gif /> - <jecs:locale key="miMember.haiwaixian"/>;";
			</c:if>
			<c:if test="${mi.userCode!='haiwaixian'}">
			tree.N["<c:out value="${mi.jmiMember.recommendNo}"/>_<c:out value="${mi.userCode}"/>"] = "T:<font color=#0066CC><c:out value="${mi.userCode} - ${mi.jmiMember.petName}"/></font> [ <font color='${cardType}'><jecs:code listCode="bd.cardtype" value="${mi.jmiMember.cardType}"/></font> ] - <font color=#FF0000><fmt:formatNumber value="${(fn:length(mi.treeIndex)-rootTreeIndex)/3}" pattern="##"/></font> <font color=#FF0000><jecs:locale key="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0?'login.Out':'' }"/></font>[<jecs:locale key="${(not empty mi.jmiMember.checkDate)?('logType.C'):('pd.createTime') }"/>:<fmt:formatDate value="${(not empty mi.jmiMember.checkDate)?(mi.jmiMember.checkDate):(mi.jmiMember.createTime) }" pattern="yyyy-MM-dd"/>];";
			</c:if>
		</c:if>
	</c:forEach>
</c:if>
</c:if>
document.getElementById("kkk").innerHTML=tree.toString();
</script>
