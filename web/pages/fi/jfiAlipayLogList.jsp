<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiAlipayLogList.title"/></title>
<content tag="heading"><jecs:locale key="jfiAlipayLogList.heading"/></content>
<meta name="menu" content="JfiAlipayLogMenu"/>


<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form action="jfiAlipayLogs.html" method="get" name="searchForm" id="searchForm">
<div class="searchBar">
<jecs:title key="miMember.memberNo"/>
<input name="userCode" type="text" size="10" value="${param.userCode }"/>
<jecs:title key="jfi99billLog.dealId"/>
<input name="tradeNo" type="text" size="10" value="${param.tradeNo }"/>
<jecs:title key="jfi99billLog.bankDealId"/>
<input name="bankSeqNo" type="text" size="10" value="${param.bankSeqNo }"/>
<jecs:title key="jfi99billLog.inc"/>
<jecs:list name="inc" listCode="yesno" value="${param.inc}" defaultValue="" showBlankLine="true"/>
<jecs:title key="comm.busi.deal.transaction.date"/>
	    <input name="startCreateTime" id="startCreateTime" type="text" value="${param.startCreateTime }" size="15" class="readonly" readonly/>
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
<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.search"/>" />
</div>
</form>

<ec:table 
	tableId="jfiAlipayLogListTable"
	items="jfiAlipayLogList"
	var="jfiAlipayLog"
	action="${pageContext.request.contextPath}/jfiAlipayLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="userCode" title="miMember.memberNo" />
    			<ec:column property="outTradeNo" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="tradeNo" title="jfi99billLog.dealId" />
    			<ec:column property="bankSeqNo" title="jfi99billLog.bankDealId" />
    			<ec:column property="totalFee" title="poAssistMemberOrder.productAmount" styleClass="numberColumn">
    				<fmt:formatNumber value="${jfiAlipayLog.totalFee}" type="number" pattern="###,###,##0.00"/>
    			</ec:column>
    			<ec:column property="inc" title="jfi99billLog.inc" styleClass="centerColumn">
    				<jecs:code listCode="yesno" value="${jfiAlipayLog.inc}"/>
    			</ec:column>
    			<ec:column property="returnMsg" title="jfi99billLog.returnMsg" styleClass="centerColumn">
    				<jecs:code listCode="returnmsg.alipay" value="${jfiAlipayLog.returnMsg}"/>
    			</ec:column>
    			<ec:column property="createTime" title="comm.busi.deal.transaction.date">
    				<fmt:formatDate value="${jfiAlipayLog.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    			</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiAlipayLog(logId){
   		<jecs:power powerCode="editJfiAlipayLog">
					window.location="editJfiAlipayLog.html?strAction=editJfiAlipayLog&logId="+logId;
			</jecs:power>
		}

</script>
