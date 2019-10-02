<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiPayLogList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiPayLogList.heading" />
</content>
<meta name="menu" content="JfiPayLogMenu" />
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="jfiPayLogs.html" method="get" name="searchForm"
	id="searchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			<jecs:title key="foundationOrder.userCode"/>
			<input name="userCode" type="text" size="10"
				value="${param.userCode }" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiBillAccount.AccountCode"/>
			<input name="merchantId" type="text" size="10"
				value="${param.merchantId}" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="jfiBillAccount.payPlatform"/>
			<jecs:list name="payType" listCode="paycompany"
				value="${param.payType}" defaultValue="" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="jfiHiTrustLog.inc"/>
			<jecs:list name="inc" listCode="yesno" value="${param.inc}"
				defaultValue="" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<jecs:title key="fiTransferAccount.dealDate"/>
			<input name="startCreateTime" id="startCreateTime" type="text" 
					value="${param.startCreateTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endCreateTime" id="endCreateTime" type="text" 
					value="${param.endCreateTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			<jecs:title key="fibankbook.dataType"/>
			<jecs:list name="dataType" listCode="fibankbook.datatype"
				value="${param.dataType}" defaultValue="" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>
<ec:table tableId="jfiPayLogListTable" items="jfiPayLogList"
	var="jfiPayLog"
	action="${pageContext.request.contextPath}/jfiPayLogs.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	autoIncludeParameters="true" sortable="true"
	imagePath="./images/extremetable/*.gif">
	<ec:exportXls fileName="jfiPayLogs.xls" />
	<ec:row>
		<ec:column property="userCode" title="会员编号" />
		<ec:column property="merchantId" title="商户号" />
		<ec:column property="orderId" title="订单编号" />
		<ec:column property="orderAmount" title="商品总金额"
			styleClass="numberColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<fmt:formatNumber value="${jfiPayLog.orderAmount }" type="number"
				pattern="###,###,##0.00" />
		</ec:column>
		<ec:column property="payType" title="支付平台" styleClass="centerColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<jecs:code listCode="paycompany" value="${jfiPayLog.payType}" />
		</ec:column>
		<ec:column property="dealId" title="支付平台处理号" />
		<ec:column property="inc" title="是否进存折" styleClass="centerColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<jecs:code listCode="yesno" value="${jfiPayLog.inc}" />
		</ec:column>
		<ec:column property="createTime" title="交易日期" />
		<ec:column property="returnMsg" title="验签状态" styleClass="centerColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<jecs:code listCode="returnmsg" value="${jfiPayLog.returnMsg}" />
		</ec:column>
		<ec:column property="dataType" title="数据来源" styleClass="centerColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<jecs:code listCode="fibankbook.datatype"
				value="${jfiPayLog.dataType}" />
		</ec:column>
	</ec:row>
</ec:table>

<script type="text/javascript">
	function editJfiPayLog(logId) {
		<jecs:power powerCode="editJfiPayLog">
		window.location = "editJfiPayLog.html?strAction=editJfiPayLog&logId="
				+ logId;
		</jecs:power>
	}
</script>
