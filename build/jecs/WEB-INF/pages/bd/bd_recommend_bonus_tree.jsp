<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="bdPeriodList.title" /></title>
<content tag="heading">
<fmt:message key="bdPeriodList.heading" />
</content>
<meta name="menu" content="BdSendRecordMenu" />

<link rel="stylesheet" href="styles/tree_grid.css"/>
<script src="./scripts/validate.js"> </script> 
<script src="scripts/jquerymobile/jquery-1.7.1.min.js"></script>
<script src="scripts/jQTreeTable/jQTreeTable.js"></script>

<script type="text/javascript">
$(function(){
    var map = [
			<c:forEach items="${bdSendRecords}" var="bdSendRecordVar" varStatus="bdSendRecordVarStatus">
			<c:if test="${bdSendRecordVarStatus.index>0}">,</c:if>${bdSendRecordVar.parentId}
			</c:forEach>
               ];
    var options = {
    	    openImg: "images/jQTreeTable/tv-collapsable.gif", 
    	    shutImg: "images/jQTreeTable/tv-expandable.gif", 
    	    leafImg: "images/jQTreeTable/tv-item.gif", 
    	    lastOpenImg: "images/jQTreeTable/tv-collapsable-last.gif", 
    	    lastShutImg: "images/jQTreeTable/tv-expandable-last.gif", 
    	    lastLeafImg: "images/jQTreeTable/tv-item-last.gif", 
    	    vertLineImg: "images/jQTreeTable/vertline.gif", 
    	    blankImg: "images/jQTreeTable/blank.gif", 
    	    collapse: true, 
    	    column: 0, 
    	    striped: true, 
    	    highlight: true, 
    	    onselect: function(target){window.status = "You clicked "+target.html();}
    };
    $("#treetable").jqTreeTable(map, options);
});
</script>

<form method="get" action="bd_recommend_bonus_tree.html" onsubmit="return validateOthers(this)" id="bdPeriodForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:label key="bdBounsDeduct.wweek" />	
			<input type="text" name="formatedWeek" id="formatedWeek" value="${param.formatedWeek}"
				title="<jecs:locale key="label.example"/>200806"/>
		</div>
		
		<c:if test="${sessionScope.sessionLogin.isMember==false}">
			<div class="new_searchBar">
				<jecs:label key="miMember.memberNo" />
				<input type="text" name="memberNo" value="${param.memberNo }"/>
			</div>
			<div class="new_searchBar">
				<jecs:label key="bdBonus.tree.level" />
				<input type="text" name="showLevel" value="${param.showLevel }" size="5"/>
			</div>
		</c:if>
		<div class="new_searchBar" style="margin-left: 65px">
			<jecs:power powerCode="bdBonusTreeQuery">	
				<button type="submit" name="submit" class="btn btn_sele">
					<jecs:locale key="operation.button.search"/>
				</button>
				<%-- <input type="submit" name="submit" value="<jecs:locale key="operation.button.search"/>" class="button"/>--%>
			</jecs:power>
			<input type="hidden" name="strAction" value="bdRecommendBonusTreeQuery"/>
				
		</div>
		
	</div>

<c:if test="${sessionScope.sessionLogin.isMember==true}">
<jecs:locale key="bdBonus.tree.organize.recommend"/>: <a href="bd_recommend_bonus_tree.html?strAction=bdRecommendBonusTreeQuery&formatedWeek=${param.formatedWeek }&memberNo=${sessionScope.sessionLogin.userCode }">${sessionScope.sessionLogin.jmiMember.petName }</a>
<c:if test="${not empty upMiRecommends}">
	<c:forEach items="${upMiRecommends}" var="upMiRecommendRefVar">
		/ <a href="bd_recommend_bonus_tree.html?strAction=bdRecommendBonusTreeQuery&formatedWeek=${param.formatedWeek }&memberNo=${upMiRecommendRefVar.jmiMember.userCode }">
		
	<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			${upMiRecommendRefVar.jmiMember.petName }
	</c:if>
	<c:if test="${sessionScope.sessionLogin.userType!='C'}">
			${upMiRecommendRefVar.jmiMember.petName }
	</c:if>
		</a>
	</c:forEach>
	
</c:if>
<br>
<jecs:locale key="bdBonus.tree.tips"/> <img src="images/bonus_tree/next_5.png" border="0" align="absmiddle"/>
</c:if>
<table id="tablemain" class="tree_grid" width="100%">
	<thead>
	 <c:if test="${param.formatedWeek <201905}">
		<tr>
			<th><jecs:locale key="bdNetWorkCostReport.memberCH"/></th>
			<th><jecs:locale key="bdCalculatingSubDetail.cardType"/></th>
			<th><jecs:locale key="bdMemberBounsCalcSell.level"/></th>
			<th><jecs:locale key="miMember.recommendCount"/></th>
			<%-- <th><jecs:locale key="bday.first_pv"/></th> --%>
			<th><jecs:locale key="bday.month_recommend_cypv"/></th>
			<th><jecs:locale key="bday.month_recommend_cxpv"/></th>
			<th><jecs:locale key="bdday.pending_recommend_pv1"/></th>
			<th><jecs:locale key="bdday.pending_recommend_pv2"/></th>
			<%--<th><jecs:locale key="bdBonus.tree.groupPv"/></th>--%>
			
			<%-- <th><jecs:locale key="bdday.pending_pv"/></th> --%>
			<th><jecs:locale key="jbdMemberLinkCalcHist.passStar"/></th>
				<c:if test="${param.formatedWeek<201037}">
			<th><jecs:locale key="jbdMemberLinkCalcHist.passGrade"/></th>
			</c:if>
			
			
			<%-- <c:if test="${doubleView=='1' }">
				<th><jecs:locale key="bdBonus.double_pass_star"/></th>
				<th><jecs:locale key="bdBonus.month_double_area_pv"/></th>
			</c:if> --%>
			
			<th><jecs:locale key="customerRecord.type"/></th>
			<%-- <th><jecs:locale key="bdday.pending_recommend_pv"/></th> --%>
			
			<c:if test="${sessionScope.sessionLogin.isMember==true}">
			<th>&nbsp;</th>
			</c:if>
		</tr>
	 </c:if>
	 
	  <c:if test="${empty param.formatedWeek || param.formatedWeek >=201905 }">
		<tr>
			<th><jecs:locale key="bdNetWorkCostReport.memberCH"/></th>
			<th><jecs:locale key="bdCalculatingSubDetail.cardType"/></th>
			<th><jecs:locale key="bdMemberBounsCalcSell.level"/></th>
			<th><jecs:locale key="miMember.recommendCount"/></th>
			<th>本旬整组重消业绩</th>
			<th>本旬推荐网络新增业绩</th>
			<th>当月个人重消业绩</th>
			
			<th><jecs:locale key="jbdMemberLinkCalcHist.passStar"/></th>
			
			<th><jecs:locale key="customerRecord.type"/></th>
			
			<c:if test="${sessionScope.sessionLogin.isMember==true}">
			<th>&nbsp;</th>
			</c:if>
		</tr>
	 </c:if>
	</thead>
	<tbody id="treetable">
		<c:forEach items="${bdSendRecords}" var="bdSendRecordVar" varStatus="bdSendRecordVarStatus">
		<c:if test="${param.formatedWeek <201905}">
			<tr>
				<td>${bdSendRecordVar.user_code } - 
					<c:if test="${sessionScope.sessionLogin.userType=='C'}">
							${bdSendRecordVar.pet_name }
					</c:if>
					<c:if test="${sessionScope.sessionLogin.userType!='C'}">
							${bdSendRecordVar.pet_name }
					</c:if>
				
				
				</td>
				<td><jecs:code listCode="bd.cardtype" value="${bdSendRecordVar.card_type }"/></td>
				<td>
					<c:if test="${bdSendRecordVar.user_code==miMember.userCode}">&nbsp;</c:if>
					<c:if test="${bdSendRecordVar.user_code!=miMember.userCode}">${bdSendRecordVar.layer_id-miMember.jmiRecommendRef.layerId }</c:if>
				</td>
				<td ><img src="images/bonus_tree/tree_star.gif" border="0"/> ${bdSendRecordVar.recommend_num }</td>
				
				<%-- <td align="right"><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${bdSendRecordVar.first_pv }</fmt:formatNumber></td>
				 --%>
				<td align="right"><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${bdSendRecordVar.month_recommend_cypv }</fmt:formatNumber></td>
				<td align="right"><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${bdSendRecordVar.month_recommend_cxpv }</fmt:formatNumber></td>
				
				
				<td align="right"><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${bdSendRecordVar.month_recommend_pv }</fmt:formatNumber></td>
				<td align="right"><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${bdSendRecordVar.month_consumer_pv }</fmt:formatNumber></td>
				<%--<td align="right"><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${bdSendRecordVar.week_group_pv }</fmt:formatNumber></td>--%>

							
				<%-- <td align="right"><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${bdSendRecordVar.pending_pv }</fmt:formatNumber></td> --%>
				<td><jecs:code listCode="pass.star" value="${bdSendRecordVar.pass_star}"/></td>
				<c:if test="${bdSendRecordVar.w_week<201037}">
				<td><jecs:code listCode="pass.grade" value="${bdSendRecordVar.pass_grade}"/></td>
				</c:if>
				
				
			<%-- <c:if test="${doubleView=='1' }">
				<td align="right"><jecs:code listCode="pass.star" value="${bdSendRecordVar.double_pass_star }"/></td>
				<td align="right"><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${bdSendRecordVar.month_double_area_pv }</fmt:formatNumber></td>
			</c:if> --%>
				
				<td><jecs:code listCode="isstore" value="${bdSendRecordVar.isstore}"/></td>
				<%-- <td align="right"><img src="images/bonus_tree/tree_person_pv.gif" border="0"/><fmt:formatNumber pattern="###,##0.00">${bdSendRecordVar.pending_recommend_pv }</fmt:formatNumber></td> --%>
				
				
				<c:if test="${sessionScope.sessionLogin.isMember==true}">
				<td><a href="bd_recommend_bonus_tree.html?strAction=bdRecommendBonusTreeQuery&formatedWeek=${param.formatedWeek }&memberNo=${bdSendRecordVar.user_code }"><img src="images/bonus_tree/next_5.png" border="0"/></a></td>
				</c:if>
			</tr>
		</c:if>
			
		<c:if test="${param.formatedWeek >=201905}">
			<tr>
				<td>${bdSendRecordVar.user_code } - 
					<c:if test="${sessionScope.sessionLogin.userType=='C'}">
							${bdSendRecordVar.pet_name }
					</c:if>
					<c:if test="${sessionScope.sessionLogin.userType!='C'}">
							${bdSendRecordVar.pet_name }
					</c:if>
				</td>
				<td><jecs:code listCode="bd.cardtype" value="${bdSendRecordVar.card_type }"/></td>
				<td>
					<c:if test="${bdSendRecordVar.user_code==miMember.userCode}">&nbsp;</c:if>
					<c:if test="${bdSendRecordVar.user_code!=miMember.userCode}">${bdSendRecordVar.layer_id-miMember.jmiRecommendRef.layerId }</c:if>
				</td>
				<td ><img src="images/bonus_tree/tree_star.gif" border="0"/> ${bdSendRecordVar.recommend_num }</td>
				
				<td align="right"><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${bdSendRecordVar.month_recommend_cxpv }</fmt:formatNumber></td>
				<td align="right"><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${bdSendRecordVar.month_recommend_pv }</fmt:formatNumber></td>
				
				
				<td align="right"><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${bdSendRecordVar.month_consumer_pv1 }</fmt:formatNumber></td>

							
				<td><jecs:code listCode="pass.star" value="${bdSendRecordVar.pass_star}"/></td>
				<td><jecs:code listCode="isstore" value="${bdSendRecordVar.isstore}"/></td>
				<c:if test="${sessionScope.sessionLogin.isMember==true}">
				<td><a href="bd_recommend_bonus_tree.html?strAction=bdRecommendBonusTreeQuery&formatedWeek=${param.formatedWeek }&memberNo=${bdSendRecordVar.user_code }"><img src="images/bonus_tree/next_5.png" border="0"/></a></td>
				</c:if>
			</tr>
		</c:if>
			
		</c:forEach>
	</tbody>	
</table>
	
</form>

<script type="text/javascript">
function validateOthers(theForm){
	if(theForm.formatedWeek.value=="" || theForm.formatedWeek.value.length!=6 || !isUnsignedInteger(theForm.formatedWeek.value)){
		alert("<jecs:locale key="week.isError"/>");
		theForm.formatedWeek.focus();
		return false;
	}
	<c:if test="${sessionScope.sessionLogin.isMember==false}">
	if(theForm.memberNo.value==""){
		alert("<jecs:locale key="bdBonus.tree.memberNo.required"/>");
		theForm.memberNo.focus();
		return false;
	}
	if(theForm.showLevel.value=="" || !isUnsignedInteger(theForm.showLevel.value)){
		alert("<jecs:locale key="bdSendRecord.showLevel.required"/>");
		theForm.showLevel.focus();
		return false;
	}
	</c:if>
	
	return true;
}
</script>