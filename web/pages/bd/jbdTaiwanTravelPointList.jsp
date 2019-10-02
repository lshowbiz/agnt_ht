<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdTaiwanTravelPointList.title"/></title>
<content tag="heading"><jecs:locale key="jbdTaiwanTravelPointList.heading"/></content>
<meta name="menu" content="JbdTaiwanTravelPointMenu"/>


<form action="" method="get" name="miMemberForm" id="miMemberForm">

	<div class="searchBar">
		
		<c:if test="${sessionScope.sessionLogin.userType=='C'}">
			<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
			<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />
		</c:if>
	
	</div>

</form>

<ec:table 
	tableId="jbdTaiwanTravelPointListTable"
	items="jbdTaiwanTravelPointList"
	var="jbdTaiwanTravelPoint"
	action="${pageContext.request.contextPath}/jbdTaiwanTravelPoints.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				
	    		
	    		
	    		<ec:column property="userCode" title="miMember.memberNo" />
	    		
				<c:if test="${sessionScope.sessionLogin.userType=='C'}">
	    			<ec:column property="userName" title="bdCalculatingSubDetail.name" />
	    			<ec:column property="phone" title="miMember.phone" />
    			</c:if>
    			
    			<ec:column property="passStar1" title="jbdTaiwanTravelPoint.passStar1" />
    			<ec:column property="passStar2" title="jbdTaiwanTravelPoint.passStar2" />
    			<ec:column property="passStar3" title="jbdTaiwanTravelPoint.passStar3" />
    			<ec:column property="passStar4" title="jbdTaiwanTravelPoint.passStar4" />
    			<ec:column property="recommendZuanshi" title="jbdTaiwanTravelPoint.recommendZuanshi" />
    			<ec:column property="recommendShenghuoguan" title="jbdTaiwanTravelPoint.recommendShenghuoguan" />
    			<ec:column property="shenghuoguanCheck" title="jbdTaiwanTravelPoint.shenghuoguanCheck" />
    			<ec:column property="total" title="jbdTaiwanTravelPoint.total" />
    			
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdTaiwanTravelPoint(id){
   		<jecs:power powerCode="editJbdTaiwanTravelPoint">
					window.location="editJbdTaiwanTravelPoint.html?strAction=editJbdTaiwanTravelPoint&id="+id;
			</jecs:power>
		}

</script>
