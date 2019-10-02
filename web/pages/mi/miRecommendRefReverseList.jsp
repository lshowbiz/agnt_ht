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

<form action="" method="post" name="miMemberForm" id="miMemberForm">

<div class="searchBar">
	<div class="new_searchBar">
		<jecs:label key="miMember.memberNo"/>
		<input name="memberNo" size="10" type="text" value="${param.memberNo }"/>
	</div>
	<div class="new_searchBar">
		<button name="search" type="submit" onclick='loading();' class="btn btn_sele">
			<jecs:locale key="operation.button.search"/>
		</button>
	<%-- 
		<input name="search" type="submit" onclick='loading();' class="button" value="<jecs:locale key="operation.button.search"/>"/>
	--%>
	</div>
</div>

<div id="kkk"></div>
<script language="javaScript">localMsg ='<jecs:locale key="miMember.localMsg"/>';</script>
	<script language="javaScript" src="scripts/MzTreeViewRef.js"></script>
	<script language="javascript" type="text/javascript">
		loading();
		window.tree = new MzTreeView("tree");
		tree.setIconPath("images/treeimages/");
		<c:if test="${not empty miRecommendRefForm}">
			<c:forEach items="${miRecommendRefList}" var="mi" varStatus="status">
			
			var syt='';
			var sytName ='${mi.sytName}';
			var reUserName ='${mi.reUserName}';
			var rankName ='${mi.rankName}';
			//"[ ${mi.sytName} - ${mi.reUserName} - ${mi.rankName} ];"
			if(''!=sytName && null!=sytName){
				syt += sytName;
			}
			if(''!=reUserName && null!=reUserName){
				syt += ' - ' + reUserName;
			}
			if(''!=rankName && null!=rankName){
				syt += ' - ' + rankName;
			}
			
			if(''!=syt && null!=syt){
				syt = ' [' + syt + ']';
			}
			
			<c:if test="${sessionScope.sessionLogin.userType!='C'}">
				<c:if test="${status.count == 1}">
					tree.N["0_<c:out value="${mi.treeIndex}"/>"] = "T:<font color=#0066CC><c:out value="${mi.userCode} - ${mi.jmiMember.petName}"/></font> [ <font color='${cardType}'>${mi.jmiMember.cardType}</font> ]  <font color=#FF0000><jecs:locale key="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0?'login.Out':'' }"/></font> <jecs:code listCode="store.type" value="${mi.jmiMember.isstore }" />"+syt+" ;";
				</c:if>
				<c:if test="${status.count != 1}">
					<c:if test="${empty treeIndex}">
						<c:set var="treeIndex" value="${fn:substring(mi.treeIndex,0,fn:length(mi.treeIndex)-3)}"/>
					</c:if>
					<c:if test="${sessionScope.sessionLogin.companyCode!='AA'}">
						<c:if test="${sessionScope.sessionLogin.companyCode==mi.jmiMember.companyCode}">
							tree.N["<c:out value="${treeIndex}"/>_<c:out value="${mi.treeIndex}"/>"] = "T:<font color=#0066CC><c:out value="${mi.userCode} - ${mi.jmiMember.petName}"/></font> [ <font color='${cardType}'>${mi.jmiMember.cardType}</font> ] - <font color=#FF0000><fmt:formatNumber value="${(fn:length(mi.treeIndex)-rootTreeIndex)/3-1}" pattern="##"/></font>  <font color=#FF0000><jecs:locale key="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0?'login.Out':'' }"/></font> <jecs:code listCode="store.type" value="${mi.jmiMember.isstore }" />"+syt+" ;";
							<c:set var="treeIndex" value="${null}"/>
						</c:if>
					</c:if>
					<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
						tree.N["<c:out value="${fn:substring(mi.treeIndex,0,fn:length(mi.treeIndex)-3)}"/>_<c:out value="${mi.treeIndex}"/>"] = "T:<font color=#0066CC><c:out value="${mi.userCode} - ${mi.jmiMember.petName}"/></font> [ <font color='${cardType}'>${mi.jmiMember.cardType}</font> ] - <font color=#FF0000><fmt:formatNumber value="${(fn:length(mi.treeIndex)-rootTreeIndex)/3-1}" pattern="##"/></font>  <font color=#FF0000><jecs:locale key="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0?'login.Out':'' }"/></font> <jecs:code listCode="store.type" value="${mi.jmiMember.isstore }" />"+syt+" ;";
					</c:if>
				</c:if>
			</c:if>
			<c:if test="${sessionScope.sessionLogin.userType=='C'}">
				var state;
				<c:choose>
					<c:when test="${mi.jmiMember.sysUser.state=='0' }">
						state= "<jecs:locale key="member.status.limit0"/>"+syt;
					</c:when>
					<c:when test="${mi.jmiMember.sysUser.state=='1' }">
						state= "<jecs:locale key="member.status.limit1"/>"+syt;
					</c:when>
				</c:choose>
			
				<c:if test="${status.count == 1}">
					tree.N["0_<c:out value="${mi.treeIndex}"/>"] = "T:<font color=#0066CC><c:out value="${mi.userCode} - ${mi.jmiMember.miUserName} - ${mi.jmiMember.petName}"/></font> [ <font color='${cardType}'>${mi.jmiMember.cardType}</font> ]  [ <jecs:code listCode="member.level" value="${mi.jmiMember.memberLevel}"/> ] <font color=#FF0000><jecs:locale key="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0?'login.Out':'' }"/><fmt:formatDate value="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0 ? mi.jmiMember.exitDate : '' }" pattern="yyyy-MM-dd"/></font> <jecs:code listCode="store.type" value="${mi.jmiMember.isstore }" /><font color=#FF0000><jecs:locale key="${mi.jmiMember.active=='1' ? 'is.active' : '' }"/></font><jecs:code listCode="memberno.zcw" value="${mi.jmiMember.userCode}"/> <jecs:code listCode="membertype" value="${fn:trim(mi.jmiMember.memberType)}"/> "+state+";";
				</c:if>
				<c:if test="${status.count != 1}">
					<c:if test="${empty treeIndex}">
						<c:set var="treeIndex" value="${fn:substring(mi.treeIndex,0,fn:length(mi.treeIndex)-3)}"/>
					</c:if>
					<c:if test="${sessionScope.sessionLogin.companyCode!='AA'}">
						<c:if test="${sessionScope.sessionLogin.companyCode==mi.jmiMember.companyCode}">
							tree.N["<c:out value="${treeIndex}"/>_<c:out value="${mi.treeIndex}"/>"] = "T:<font color=#0066CC><c:out value="${mi.userCode} - ${mi.jmiMember.miUserName} - ${mi.jmiMember.petName}"/></font> [ <font color='${cardType}'>${mi.jmiMember.cardType}</font> ] [ <jecs:code listCode="member.level" value="${mi.jmiMember.memberLevel}"/> ] - <font color=#FF0000><fmt:formatNumber value="${(fn:length(mi.treeIndex)-rootTreeIndex)/3-1}" pattern="##"/></font>  <font color=#FF0000><jecs:locale key="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0?'login.Out':'' }"/><fmt:formatDate value="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0 ? mi.jmiMember.exitDate : '' }" pattern="yyyy-MM-dd"/></font> <jecs:code listCode="store.type" value="${mi.jmiMember.isstore }" /><font color=#FF0000><jecs:locale key="${mi.jmiMember.active=='1' ? 'is.active' : '' }"/></font><jecs:code listCode="memberno.zcw" value="${mi.jmiMember.userCode}"/> <jecs:code listCode="membertype" value="${fn:trim(mi.jmiMember.memberType)}"/> "+state+";";
							<c:set var="treeIndex" value="${null}"/>
						</c:if>
					</c:if>
					<c:if test="${sessionScope.sessionLogin.companyCode=='AA'}">
						tree.N["<c:out value="${fn:substring(mi.treeIndex,0,fn:length(mi.treeIndex)-3)}"/>_<c:out value="${mi.treeIndex}"/>"] = "T:<font color=#0066CC><c:out value="${mi.userCode} - ${mi.jmiMember.miUserName} - ${mi.jmiMember.petName}"/></font> [ <font color='${cardType}'>${mi.jmiMember.cardType}</font> ] [ <jecs:code listCode="member.level" value="${mi.jmiMember.memberLevel}"/> ] - <font color=#FF0000><fmt:formatNumber value="${(fn:length(mi.treeIndex)-rootTreeIndex)/3-1}" pattern="##"/></font>  <font color=#FF0000><jecs:locale key="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0?'login.Out':'' }"/><fmt:formatDate value="${(not empty mi.jmiMember.exitDate)&&mi.jmiMember.sysUser.state==0 ? mi.jmiMember.exitDate : '' }" pattern="yyyy-MM-dd"/></font> <jecs:code listCode="store.type" value="${mi.jmiMember.isstore }" /><font color=#FF0000><jecs:locale key="${mi.jmiMember.active=='1' ? 'is.active' : '' }"/></font><jecs:code listCode="memberno.zcw" value="${mi.jmiMember.userCode}"/> <jecs:code listCode="membertype" value="${fn:trim(mi.jmiMember.memberType)}"/> "+state+";";
					</c:if>
				</c:if>
			</c:if>
			</c:forEach>
		</c:if>
		document.getElementById("kkk").innerHTML=tree.toString();
		tree.expandAll();
	</script>
</form>
