<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>


<title><jecs:locale key="jbdVentureFundListList.title"/></title>
<content tag="heading"><jecs:locale key="jbdVentureFundListList.heading"/></content>
<meta name="menu" content="JbdVentureFundListMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdVentureFundList">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdVentureFundList.html"/>?strAction=addJbdVentureFundList'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>


<form action="" method="get" name="miMemberForm" id="miMemberForm">

<input type="hidden" name="strAction" value="jbdVentureFundLists"/>

	<div class="searchBar">
		
			<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
			<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />
		
		
		<jecs:power powerCode="addJbdVentureFundList">
		<input name="search" class="button" type="button" onclick="location.href='editJbdVentureFundList.html?strAction=addJbdVentureFundList'" value="<jecs:locale key="member.new.num"/>" />
		</jecs:power>
	</div>


</form>



<ec:table 
	tableId="jbdVentureFundListListTable"
	items="jbdVentureFundListList"
	var="jbdVentureFundList"
	action="${pageContext.request.contextPath}/jbdVentureFundLists.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="wweek" title="bdBounsDeduct.wweek" />
    			<ec:column property="recommendNo" title="bdBonusRankingReport.recommandPerson" />
    			<ec:column property="orderNo" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="orderType" title="pd.orderType" >
    				<jecs:code listCode="po.all.order_type" value="${jbdVentureFundList.orderType}"/>
    			</ec:column>
    			<ec:column property="pvAmt" title="report.inPv" />
    			<ec:column property="percentage" title="billAccount.persent" >
					<c:if test="${not empty jbdVentureFundList.percentage }">
   						<fmt:formatNumber value="${jbdVentureFundList.percentage*100 }" type="number" pattern="###,###,###.##"/>%
   					</c:if>
    			</ec:column>
    			<ec:column property="createWeek" title="创建期别" />
    			<ec:column property="remark" title="busi.common.remark" />
    			
					<ec:column property="1" title="title.operation" sortable="false" width="100" >
					<c:if test="${jbdVentureFundList.status==1 && jbdVentureFundList.calcStatus==0 }">
					<a href="jbdVentureFundLists.html?id=${jbdVentureFundList.id}&strAction=deleteJbdVentureFundList" onclick="return window.confirm('<jecs:locale key="amMessage.confirmdelete"/>');"><img border="0" src="images/icons/w.gif" alt="<jecs:locale key="operation.button.delete"/>" /></a>
					</c:if>
					
					</ec:column>
				</ec:row>

</ec:table>	
