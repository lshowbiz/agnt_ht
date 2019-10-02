<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<title><jecs:locale key="jbdTravelPoint2014List.title"/></title>
<content tag="heading"><jecs:locale key="jbdTravelPoint2014List.heading"/></content>
<meta name="menu" content="JbdTravelPoint2014Menu"/>


<form action="" method="get" name="bdSendRecordForm1" id="bdSendRecordForm1">
<div class="searchBar">
<jecs:title key="miMember.memberNo"/>
		<input name="userCode" type="text" value="${param.userCode}" size="10"/>	
		<input type="submit" class="button" name="cancel" value="<jecs:locale key="operation.button.search"/>" />
</div>

</form>
<ec:table 
	tableId="jbdTravelPoint2014ListTable"
	items="jbdTravelPoint2014List"
	var="jbdTravelPoint2014"
	action="${pageContext.request.contextPath}/jbdTravelPoint2014s.html"
	width="100%"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="passStar" title="活动考核期间内最高奖衔" >
    				<jecs:code listCode="pass.star.zero" value="${jbdTravelPoint2014.passStar }"/>
    			</ec:column>
    			<ec:column property="total" title="fiCoinLog.coin" />
				</ec:row>

</ec:table>	

