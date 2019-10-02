<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="miUpKpvList.title"/></title>
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
		<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode }" size="12"/>	
 	</div>
 	<div class="new_searchBar">
 		<jecs:title key="bdBounsDeduct.wweek"/>
 		<%-- <jecs:locale key="busi.common.ex"/>--%>
		<input name="wweek" type="text" value="${param.wweek }" size="8"/>	
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
<div>
<img src=images/cardType/0.gif alt=<jecs:code listCode="bd.cardtype" value="0"/> /> 
<jecs:code listCode="bd.cardtype" value="0"/>;

<img src=images/cardType/1.gif alt=<jecs:code listCode="bd.cardtype" value="1"/> /> 
<jecs:code listCode="bd.cardtype" value="1"/>;

<img src=images/cardType/2.gif alt=<jecs:code listCode="bd.cardtype" value="2"/> /> 
<jecs:code listCode="bd.cardtype" value="2"/>;

<img src=images/cardType/3.gif alt=<jecs:code listCode="bd.cardtype" value="3"/> /> 
<jecs:code listCode="bd.cardtype" value="3"/>;

<img src=images/cardType/4.gif alt=<jecs:code listCode="bd.cardtype" value="4"/> /> 
<jecs:code listCode="bd.cardtype" value="4"/>;

</div>
<div id="kkk"></div>

<script language="javaScript">localMsg ='<jecs:locale key="miMember.localMsg"/>';</script>
	<script language="javaScript" src="scripts/MzTreeViewRef.js"></script>
	<script language="javascript" type="text/javascript">
		loading();
		window.tree = new MzTreeView("tree");
		tree.setIconPath("images/treeimages/");

			<c:forEach items="${bdMemberBounsCalcs}" var="var" varStatus="status">
				<c:if test="${status.count == 1}">
					tree.N["0_<c:out value="${var['user_code']}"/>"] = "T:<c:out value="${var['user_code']}-"/><font color=#FF0000>${status.count}</font>;";
				</c:if>
				<c:if test="${status.count != 1}">				
tree.N["<c:out value="${var['link_no']}"/>_<c:out value="${var['user_code']}"/>"] = "T:<c:out value="${var['user_code']}-${var['pet_name']}"/>-[<img src=images/cardType/${var['card_type']}.gif alt=<jecs:code listCode="bd.cardtype" value="${var['card_type']}"/> />]-<font color=#FF0000><c:out value="${status.count}"/></font> <c:if test="${not empty var['keep_pv'] }">[<jecs:locale key="bdBonusStatReport.company.keey.pv"/>:<c:out value="${var['keep_pv']}"/>]</c:if>;";
				</c:if>

					
					
			
			</c:forEach>

		document.getElementById("kkk").innerHTML=tree.toString();
		tree.expandAll();
	</script>
</form>
