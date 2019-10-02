<%@ include file="/common/taglibs.jsp"%>

<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiChinapnrLogList.title" />
</title>
<content tag="heading">
<jecs:locale key="jfiChinapnrLogList.heading" />
</content>
<meta name="menu" content="JfiChinapnrLogMenu" />
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script>
<script type="text/javascript"
	src="./scripts/calendar/calendar-setup.js"> </script>
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>



<form action="jfiChinapnrLog.html" method="get" name="searchForm"
	id="searchForm">
	<div class="searchBar">
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;会员编号：
			<input name="userCode" type="text" size="10"
				value="${param.userCode }" />
		</div>
		<div class="new_searchBar">
			&nbsp;&nbsp;&nbsp;商户号：
			<input name="merchantId" type="text" size="10"
				value="${param.merchantId}" />
		</div>
		<div class="new_searchBar">
			汇付处理号：
			<input name="payorderid" type="text" size="10"
				value="${param.payorderid}" />
		</div>
		<div class="new_searchBar">
			是否进存折：
			<jecs:list name="inc" listCode="yesno" value="${param.inc}"
				defaultValue="" showBlankLine="true" />
		</div>
		<div class="new_searchBar">
			交易日期：
			<input name="startCreateTime" id="startCreateTime" type="text" 
					value="${param.startCreateTime }" style="cursor: pointer;width:75px;"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				-
			<input name="endCreateTime" id="endCreateTime" type="text" 
					value="${param.endCreateTime }" style="cursor: pointer;width:75px;"
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


<ec:table tableId="jfiChinapnrLogListTable" items="jfiChinapnrLogList"
	var="jfiChinapnrLog"
	action="${pageContext.request.contextPath}/jfiChinapnrLog.html"
	width="100%" retrieveRowsCallback="limit" autoIncludeParameters="true"
	rowsDisplayed="20" sortable="true"
	imagePath="./images/extremetable/*.gif">
	<ec:exportXls fileName="jfiChinapnrLog.xls" />
	<ec:row>
		<ec:column property="userCode" title="会员编号" escapeAutoFormat="true" />
		<ec:column property="merchantid" title="商户号" escapeAutoFormat="true" />
		<ec:column property="merorderid" title="订单编号" escapeAutoFormat="true" />
		<ec:column property="payorderid" title="汇付处理号" escapeAutoFormat="true" />
		<ec:column property="amountsum" title="商品总金额"
			styleClass="numberColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<fmt:formatNumber value="${jfiChinapnrLog.amountsum}" type="number"
				pattern="###,###,##0.00" />
		</ec:column>
		<ec:column property="inc" title="是否进存折" styleClass="centerColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<jecs:code listCode="yesno" value="${jfiChinapnrLog.inc}" />
		</ec:column>
		<ec:column property="createTime" title="交易日期"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<fmt:formatDate value="${jfiChinapnrLog.createTime }"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</ec:column>
		<ec:column property="returnMsg" title="验签状态" styleClass="centerColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<jecs:code listCode="returnmsg" value="${jfiChinapnrLog.returnMsg}" />
		</ec:column>
		<ec:column property="dataType" title="数据来源" styleClass="centerColumn"
			cell="com.joymain.jecs.util.ectable.EcTableCell">
			<jecs:code listCode="fibankbook.datatype"
				value="${jfiChinapnrLog.dataType}" />
		</ec:column>
	</ec:row>

</ec:table>

<script type="text/javascript">

   function editJfiChinapnrLog(logId){
   		<jecs:power powerCode="editJfiChinapnrLog">
					window.location="editJfiChinapnrLog.html?strAction=editJfiChinapnrLog&logId="+logId;
			</jecs:power>
		}

</script>
