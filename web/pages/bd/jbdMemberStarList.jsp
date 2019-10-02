<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdUserCardListList.title"/></title>
<content tag="heading"><jecs:locale key="jbdUserCardListList.heading"/></content>
<meta name="menu" content="JbdUserCardListMenu"/>

<form action="" method="get" name="bdPvSubDetailFrom" id="bdPvSubDetailFrom">
<div class="searchBar">
	<div class="new_searchBar">	
		<jecs:label key="miMember.memberNo"/>	
		<input name="userCode" type="text" value="${param.userCode }" size="10"/>
		<%--<jecs:locale key="bdCalculatingSubDetail.name"/>	
		<input name="userName" type="text" value="${param.userName }" size="10"/>--%>
	</div>
	<div class="new_searchBar">
		<jecs:label key="bdBounsDeduct.wweek"/>	
		<input name="wweek" type="text" value="${param.wweek }" size="8"/>
	</div>	
	<div class="new_searchBar">
		<button name="search" type="submit" class="btn btn_sele">
			<jecs:locale key="operation.button.search"/>
		</button>
		<%-- 
		<input name="search" type="submit" class="button" value="<jecs:locale key="operation.button.search"/>"/>
		--%>
	</div>	
</div>

</form>


<ec:table 
	tableId="JbdMemberStarListTable"
	items="jbdMemberStars"
	var="jbdMemberStar"
	action="${pageContext.request.contextPath}/jbdMemberStars.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="wweek" title="bdBounsDeduct.wweek" >
    				<jecs:weekFormat week="${jbdMemberStar.wweek}" weekType="w" />
    			</ec:column>
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="name" title="bdCalculatingSubDetail.name" />
    			<ec:column property="honorStar" title="jbdMemberLinkCalcHist.honorStar" >
    			 <jecs:code listCode="honor.star.zero" value="${jbdMemberStar.honorStar}"/>
    			</ec:column>
    			<ec:column property="passStar" title="jbdMemberLinkCalcHist.passStar" >
    			<jecs:code listCode="pass.star.zero" value="${jbdMemberStar.passStar}"/>
    			</ec:column>
    			
    			<ec:column property="qualifyStar" title="jbdMemberLinkCalcHist.qualifyStar" >
    			<jecs:code listCode="qualify.star.zero" value="${jbdMemberStar.qualifyStar}"/>
    			</ec:column>
    			
    			<ec:column property="-1" title="jbdMemberLinkCalcHist.qualifyRemark" >
    				<span title="${jbdMemberStar.qualifyRemark}">${jbdMemberStar.qualifyRemark}</span>
    			</ec:column>
    			
    			
    			
				</ec:row>

</ec:table>	

