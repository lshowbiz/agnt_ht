<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdTravelPointAllList.title" /></title>
<content tag="heading">
<jecs:locale key="jbdTravelPointAllList.heading" />
</content>
<meta name="menu" content="JbdTravelPointAllMenu" />
<script type="text/javascript">
function exportSub(){
	var wmonth = document.getElementsByName('wmonth')[0].value;
	if(wmonth==null || wmonth=='' || wmonth.length!=6){
		alert('<jecs:locale key="cmonth.input"/>');
		return false;
	}
	var obj_text = document.getElementById('exportFlag');
	obj_text.value="export";
	document.getElementById('jbdTravelPointAllForm1').action="jbdTravelPointAlls.html";
	document.getElementById('jbdTravelPointAllForm1').submit();
	obj_text.value="";
}
</script>
<c:set var="buttons">
	<!-- 
		<jecs:power powerCode="addJbdTravelPointAll">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdTravelPointAll.html"/>?strAction=addJbdTravelPointAll'"
			        value="<jecs:locale key="button.edit"/>"/>
		</jecs:power>
		 -->
</c:set>
<form action="" method="get" name="jbdTravelPointAllForm1"
	id="jbdTravelPointAllForm1">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="miMember.memberNo" /><input name="userCode" type="text" style="width: 165px;" value="${param.userCode}" size="10" />
			<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
		<div class="new_searchBar">
			<jecs:title key="jbdTravelPoint.month" />
			<input name="wmonth" type="text" value="${param.wmonth}" size="6" maxlength="10"
			onkeyup="value=value.replace(/[^\d.]/g,'')"  style="width: 120px;"/>
			<button name="export" class="btn btn_ins" type="button" onclick="exportSub();">
				<jecs:locale key="toolbar.text.xls"/>
			</button>
			<input id="exportFlag" name="exportFlag" type="hidden" value="" />
		</div>
	</div>
</form>
<ec:table tableId="jbdTravelPointAllListTable"
	items="jbdTravelPointAllList" var="jbdTravelPointAll"
	action="${pageContext.request.contextPath}/jbdTravelPointAlls.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:row>
		<ec:column property="comp_id.userCode" title="miMember.memberNo" />
		<ec:column property="comp_id.fyear" title="jbdTravelPointAll.fyear" />
		<ec:column property="passStar" title="jbdTravelPointAll.passStar">
			<jecs:code listCode="pass.star.zero"
				value="${jbdTravelPointAll.passStar }" />
		</ec:column>
		<ec:column property="total" title="fiCoinLog.coin" />


	</ec:row>

</ec:table>

<script type="text/javascript">

</script>
