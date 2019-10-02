<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>

<title><jecs:locale key="jbdTravelPointList.title" />
</title>
<content tag="heading">
<jecs:locale key="jbdTravelPointList.heading" />
</content>
<meta name="menu" content="JbdTravelPointMenu" />
<script type="text/javascript">
function exportSub(){
	var obj_text = document.getElementById('exportFlag');
	obj_text.value="export";
	document.getElementById('bdSendRecordForm1').action="jbdTravelPoints.html";
	document.getElementById('bdSendRecordForm1').submit();
	obj_text.value="";
}
</script>
<form action="" method="get" name="bdSendRecordForm1"
	id="bdSendRecordForm1">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" type="text" value="${param.userCode}" size="10" />
		</div>
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
		<div class="new_searchBar">
			财月:
			<input name="wmonth" type="text" value="${param.wmonth}" size="10" />
			<button name="export" class="btn btn_long" type="button" onclick="exportSub();">
				<jecs:locale key="toolbar.text.xls"/>
			</button>
		</div>
		<input id="exportFlag" name="exportFlag" type="hidden" value="" />
	</div>

</form>
<ec:table tableId="jbdTravelPointListTable" items="jbdTravelPointList"
	var="jbdTravelPoint"
	action="${pageContext.request.contextPath}/jbdTravelPoints.html"
	width="100%" autoIncludeParameters="true" retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true"
	imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="userCode" title="miMember.memberNo" />
		<ec:column property="passStar" title="活动考核期间内最高奖衔">
			<jecs:code listCode="pass.star.zero"
				value="${jbdTravelPoint.passStar }" />
		</ec:column>
		<ec:column property="total" title="fiCoinLog.coin" />
	</ec:row>

</ec:table>
