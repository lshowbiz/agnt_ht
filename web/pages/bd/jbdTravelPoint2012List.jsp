<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<title><jecs:locale key="jbdTravelPoint2012List.title"/></title>
<content tag="heading"><jecs:locale key="jbdTravelPoint2012List.heading"/></content>
<meta name="menu" content="JbdTravelPoint2012Menu"/>


<form action="" method="get" name="bdSendRecordForm1" id="bdSendRecordForm1">
<div class="searchBar">
<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
		<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />
</div>

</form>
<ec:table 
	tableId="jbdTravelPoint2012ListTable"
	items="jbdTravelPoint2012List"
	var="jbdTravelPoint2012"
	action="${pageContext.request.contextPath}/jbdTravelPoint2012s.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="passStar" title="活动考核期间内最高奖衔" >
    				<jecs:code listCode="pass.star.zero" value="${jbdTravelPoint2012.passStar }"/>
    			</ec:column>
    			<ec:column property="total" title="fiCoinLog.coin" />
				</ec:row>

</ec:table>	

