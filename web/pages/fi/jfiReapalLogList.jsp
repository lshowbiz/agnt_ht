<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiReapalLogList.title"/></title>
<content tag="heading"><jecs:locale key="jfiReapalLogList.heading"/></content>
<meta name="menu" content="JfiReapalLogMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<c:set var="buttons">
		<jecs:power powerCode="addJfiReapalLog">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJfiReapalLog.html"/>?strAction=addJfiReapalLog'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<form action="jfiReapalLogs.html" method="get" name="searchForm" id="searchForm">
<div class="searchBar">


会员编号：<input name="userCode" type="text" size="10" value="${param.userCode }"/>

商户号：<input name="merchantId" type="text" size="10" value="${param.merchantId}"/>

融宝处理号：<input name="detailId" type="text" size="10" value="${param.detailId}"/>

银行订单号：<input name="bankDealId" type="text" size="10" value="${param.bankDealId}"/>

是否进存折：<jecs:list name="inc" listCode="yesno" value="${param.inc}" defaultValue="" showBlankLine="true"/>

交易日期：<input name="startCreateTime" id="startCreateTime" type="text" value="${param.startCreateTime }" size="15" class="readonly" readonly/>
	    <img src="./images/calendar/calendar7.gif" id="img_startDealDate" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "startCreateTime", 
			ifFormat       :    "%Y-%m-%d %H:%M:00",  
			button         :    "img_startDealDate", 
			showsTime	   :	true,
			singleClick    :    true
			}); 
		</script>
	     - 
	     <input name="endCreateTime" id="endCreateTime" type="text" value="${param.endCreateTime }" size="15" class="readonly" readonly/>
	     <img src="./images/calendar/calendar7.gif" id="img_endDealDate" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
		<script type="text/javascript"> 
			Calendar.setup({
			inputField     :    "endCreateTime", 
			ifFormat       :    "%Y-%m-%d %H:%M:00",  
			button         :    "img_endDealDate", 
			showsTime	   :	true,
			singleClick    :    true
			}); 
		</script>
		

数据来源：<jecs:list name="dataType" listCode="fibankbook.datatype" value="${param.dataType}" defaultValue="" showBlankLine="true"/>

<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>" />
</div>
</form>
</div>
<ec:table 
	tableId="jfiReapalLogListTable"
	items="jfiReapalLogList"
	var="jfiReapalLog"
	action="${pageContext.request.contextPath}/jfiReapalLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="userCode" title="会员编号" />
    			<ec:column property="merchantId" title="商户号" />
    			<ec:column property="orderId" title="订单编号" />
    			<ec:column property="detailId" title="融宝处理号"  escapeAutoFormat="true"/>
    			<ec:column property="bankDealId" title="银行处理流水" />
    			<ec:column property="orderAmount" title="商品总金额" styleClass="numberColumn" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<fmt:formatNumber value="${jfiReapalLog.orderAmount}" type="number" pattern="###,###,##0.00"/>
    			</ec:column>
    			<ec:column property="amount" title="交易金额" styleClass="numberColumn" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<fmt:formatNumber value="${jfiReapalLog.amount}" type="number" pattern="###,###,##0.00"/>
    			</ec:column>
    			<ec:column property="inc" title="是否进存折" styleClass="centerColumn" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="yesno" value="${jfiReapalLog.inc}"/>
    			</ec:column>
    			<ec:column property="createTime" title="交易日期" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<fmt:formatDate value="${jfiYeepayLog.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    			</ec:column>
    			<ec:column property="returnMsg" title="验签状态" styleClass="centerColumn" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="returnmsg" value="${jfiReapalLog.returnMsg}"/>
    			</ec:column>
    			<ec:column property="dataType" title="数据来源" styleClass="centerColumn" cell="com.joymain.jecs.util.ectable.EcTableCell">
    				<jecs:code listCode="fibankbook.datatype" value="${jfiReapalLog.dataType}"/>
    			</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiReapalLog(logId){
   		<jecs:power powerCode="editJfiReapalLog">
					window.location="editJfiReapalLog.html?strAction=editJfiReapalLog&logId="+logId;
			</jecs:power>
		}

</script>