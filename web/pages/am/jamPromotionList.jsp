<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jamPromotionList.title"/></title>
<content tag="heading"><jecs:locale key="jamPromotionList.heading"/></content>
<meta name="menu" content="JamPromotionMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJamPromotion">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJamPromotion.html"/>?strAction=addJamPromotion'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>


<form action="" method="get" name="miMemberForm" id="miMemberForm">

	<div class="searchBar" style="margin-left:10px;margin-bottom:10px;">
		
	
	
			<jecs:power powerCode="addJamPromotion">
			<button name="search" class="btn btn_ins" type="button" onclick="location.href='editJamPromotion.html?strAction=addJamPromotion'"><jecs:locale key="member.new.num"/></button>
			</jecs:power>
	</div>

</form>

<ec:table 
	tableId="jamPromotionListTable"
	items="jamPromotionList"
	var="jamPromotion"
	action="${pageContext.request.contextPath}/jamPromotions.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
	    			<ec:column property="pmName" title="jamPromotion.pmName" />
	    			<ec:column property="pmWay" title="jamPromotion.pmWay" >
	    					<jecs:code listCode="jampromotion.pmway" value="${jamPromotion.pmWay }"/>
	    			</ec:column>
	    			<ec:column property="pmType" title="customerRecord.type" >
	    					<jecs:code listCode="jampromotion.pmtype" value="${jamPromotion.pmType }"/>
	    			</ec:column>
	    			<ec:column property="startTime" title="common.startTime" >
	    				<fmt:formatDate value="${jamPromotion.startTime }" pattern="yyyy-MM-dd"/>
	    			</ec:column>
	    			<ec:column property="endTime" title="customerFollow.endTime" >
	    				<fmt:formatDate value="${jamPromotion.endTime }" pattern="yyyy-MM-dd"/>
	    			</ec:column>
	    			<ec:column property="status" title="customerFollow.state" >
	    					<jecs:code listCode="jampromotion.status" value="${jamPromotion.status }"/>
	    			</ec:column>
	    			<ec:column property="targetGroup" title="jamPromotion.targetGroup" />
	    			<ec:column property="proposer" title="jamPromotion.proposer" />
					<ec:column property="1" title="title.operation" sortable="false" width="100" >
					
						<img src="images/newIcons/pencil_16.gif" onclick="window.location.href='editJamPromotion.html?id=${jamPromotion.id}&strAction=editJamPromotion';" alt="<jecs:locale key="button.edit"/>" style="cursor:pointer"/>
						
					
					</ec:column>
				</ec:row>

</ec:table>	
