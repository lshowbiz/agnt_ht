<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE HTML>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1"> 
		<meta name="decorator" content="mobile"/>
	</head>
	<style>
	
element.style {
    font-size: 16px;
    text-align: left;
}
	</style>
	
<script>
function loading(){
	var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>';
	str += '&nbsp;&nbsp;<jecs:locale key="button.loading"/>';
	document.getElementById("kkk").innerHTML=str;
}
</script>
<img style="display:none" src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>

<form action="miRecommendRefs.html" method="get" name="miMemberForm" id="miMemberForm" onsubmit='document.getElementById("search").disabled = true;'  data-ajax="false" >


<table width="100%">




    <tr>
	    <th>
		   	<jecs:locale key="miMember.memberNo"  />
	    </th>
	    <td >
	   		<input name="memberNo"  size="10" type="text" value="${empty param.memberNo ? memberNo : param.memberNo }"/>
	    </td>
	 </tr>

    <tr>
	    <th>
		  <jecs:locale key="miMember.layerId"  />
	    </th>
	    <td >
	   		<select name="layerId">
				<c:forEach items='${layerList}' var='item'>
				  <option value="${item}" ${item==param.layerId?"selected=selected":"" }>${item}</option>
				</c:forEach>
				</select>
	    </td>
	 </tr>
	
				
	<tr><td colspan="2" >

	<fieldset class="ui-grid-a">
			<div class="ui-block-a">	<input   name="search" id="search" type="submit" onclick='loading();' class="button" value="<jecs:locale key="operation.button.search"/>"/></div>
			
			<div class="ui-block-b"><input   type="button"  name="cancel" onclick="window.history.back()" value="<jecs:locale key="operation.button.return"/>" />  </div>	
</fieldset>
				
				
	
	</td></tr>
	

</table>





</form>
<div style="text-align: left;"><jecs:locale key="miRecommendRef.total"/>:${empty miMemberCount?0:miMemberCount }</div>
<div id="kkk" style="text-align: left;letter-spacing:-1px;"></div>
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
			tree.N["0_<c:out value="${mi.userCode}"/>"] = "T:<font color=#0066CC><c:out value="${mi.userCode} - ${mi.jmiMember.petName}"/></font> [ <font color='${cardType}'><jecs:code listCode="bd.cardtype" value="${mi.jmiMember.cardType}"/></font> ];";
		</c:if>
		<c:if test="${status.count != 1}">
			tree.N["<c:out value="${mi.jmiMember.recommendNo}"/>_<c:out value="${mi.userCode}"/>"] = "T:<font color=#0066CC><c:out value="${mi.userCode} - ${mi.jmiMember.petName}"/></font> [ <font color='${cardType}'><jecs:code listCode="bd.cardtype" value="${mi.jmiMember.cardType}"/></font> ] - <font color=#FF0000><fmt:formatNumber value="${(fn:length(mi.treeIndex)-rootTreeIndex)/3}" pattern="##"/></font>;";
		</c:if>
	</c:forEach>
</c:if>

</c:if>
document.getElementById("kkk").innerHTML=tree.toString();
</script>
</html>
