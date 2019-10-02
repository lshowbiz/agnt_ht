<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiHiTrustLogList.title"/></title>
<content tag="heading"><jecs:locale key="jfiHiTrustLogList.heading"/></content>
<meta name="menu" content="JfiHiTrustLogMenu"/>


<form action="jfiHiTrustLogs.html" method="get" name="searchForm" id="searchForm">
<div class="searchBar">
<jecs:title key="poMemberOrder.memberOrderNo"/>
<input name="orderNo" type="text" size="10" value="${param.orderNo }"/>
<jecs:title key="jfi99billLog.bankDealId"/>
<input name="authRrn" type="text" size="10" value="${param.authRrn }"/>
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
	tableId="jfiHiTrustLogListTable"
	items="jfiHiTrustLogList"
	var="jfiHiTrustLog"
	action="${pageContext.request.contextPath}/jfiHiTrustLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
    			<ec:column property="orderNo" title="poMemberOrder.memberOrderNo" />
    			<ec:column property="authRrn" title="jfi99billLog.bankDealId" />
    			<ec:column property="captureAmount" title="poAssistMemberOrder.productAmount">
    				<fmt:formatNumber value="${jfiHiTrustLog.captureAmount * 0.01}" type="number" pattern="###,###,##0.00"/>
    			</ec:column>
    			<ec:column property="inc" title="jfiHiTrustLog.inc" styleClass="centerColumn">
    				<jecs:code listCode="yesno" value="${jfiHiTrustLog.inc}"/>
    			</ec:column>
    			<ec:column property="returnMsg" title="jfi99billLog.returnMsg" styleClass="centerColumn">
    				<jecs:code listCode="returnmsg" value="${jfiHiTrustLog.returnMsg}"/>
    			</ec:column>
    			<ec:column property="createTime" title="comm.busi.deal.transaction.date">
    				<fmt:formatDate value="${jfiHiTrustLog.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    			</ec:column>
    			<ec:column property="pageType" title="jfiHiTrustLog.pageType" styleClass="centerColumn">
    				<jecs:code listCode="pagetype" value="${jfiHiTrustLog.pageType}"/>
    			</ec:column>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiHiTrustLog(logId){
   		<jecs:power powerCode="editJfiHiTrustLog">
					window.location="editJfiHiTrustLog.html?strAction=editJfiHiTrustLog&logId="+logId;
			</jecs:power>
		}

</script>
