<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiChanjetLogList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiChanjetLogList.heading" />
</content>
<meta name="menu" content="JfiChanjetLogMenu" />
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>

<form action="jfiChanjetLogs.html" method="get" name="searchForm"
	id="searchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;会员编号：
			<input name="userCode" type="text" size="10"
				value="${param.userCode }" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商户号：
			<input name="merchantId" type="text" size="10"
				value="${param.merchantId}" />
		</div>
		<div class="new_searchBar">
			畅捷处理号：
			<input name="detailId" type="text" size="10" value="${param.detailId}" />
		</div>
		<div class="new_searchBar">
			银行订单号：
			<input name="bankDealId" type="text" size="10"
				value="${param.bankDealId}" />
		</div>
		<div class="new_searchBar">
			是否进存折：
			<jecs:list name="inc" listCode="yesno" value="${param.inc}"
				defaultValue="" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;交易日期：
			<input name="startCreateTime" id="startCreateTime" type="text" 
					value="${param.startCreateTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endCreateTime" id="endCreateTime" type="text" 
					value="${param.endCreateTime }" style="cursor: pointer;width:70px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;数据来源：
			<jecs:list name="dataType" listCode="fibankbook.datatype"
				value="${param.dataType}" defaultValue="" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			<button name="search" class="btn btn_sele" type="submit">
				<jecs:locale key="operation.button.search"/>
			</button>
		</div>
	</div>
</form>

<ec:table tableId="jfiChanjetLogListTable" items="jfiChanjetLogList"
	var="jfiChanjetLog"
	action="${pageContext.request.contextPath}/jfiChanjetLogs.html"
	width="100%" retrieveRowsCallback="limit" rowsDisplayed="20"
	sortable="true" imagePath="./images/extremetable/*.gif">
	<ec:exportXls fileName="jfiChanjetLogs.xls" />
	<ec:row>


		<ec:column property="userCode" title="会员编号" escapeAutoFormat="true" />
		<ec:column property="merchantId" title="商户号" escapeAutoFormat="true" />
		<ec:column property="orderId" title="订单编号" escapeAutoFormat="true" />
		<ec:column property="detailId" title="畅捷处理号" escapeAutoFormat="true" />
		<ec:column property="bankDealId" title="银行订单号" escapeAutoFormat="true" />
		<ec:column property="orderAmount" title="商品总金额"
			styleClass="numberColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<fmt:formatNumber value="${jfiChanjetLog.orderAmount * 0.01}"
				type="number" pattern="###,###,##0.00" />
		</ec:column>
		<ec:column property="amount" title="交易金额" styleClass="numberColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<fmt:formatNumber value="${jfiChanjetLog.amount * 0.01}"
				type="number" pattern="###,###,##0.00" />
		</ec:column>
		<ec:column property="inc" title="是否进存折" styleClass="centerColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<jecs:code listCode="yesno" value="${jfiChanjetLog.inc}" />
		</ec:column>
		<ec:column property="createTime" title="交易日期"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<fmt:formatDate value="${jfiChanjetLog.createTime }"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</ec:column>
		<ec:column property="returnMsg" title="验签状态" styleClass="centerColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<jecs:code listCode="returnmsg" value="${jfiChanjetLog.returnMsg}" />
		</ec:column>
		<ec:column property="dataType" title="数据来源" styleClass="centerColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<jecs:code listCode="fibankbook.datatype"
				value="${jfiChanjetLog.dataType}" />
		</ec:column>



	</ec:row>

</ec:table>

<script type="text/javascript">

   function editJfiChanjetLog(logId){
   		<jecs:power powerCode="editJfiChanjetLog">
					window.location="editJfiChanjetLog.html?strAction=editJfiChanjetLog&logId="+logId;
			</jecs:power>
		}

</script>
