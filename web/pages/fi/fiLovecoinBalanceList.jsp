<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiLovecoinBalanceList.title"/></title>
<content tag="heading"><jecs:locale key="fiLovecoinBalanceList.heading"/></content>
<meta name="menu" content="FiLovecoinBalanceMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

 
<form action="fiLovecoinBalances.html" method="get" name="fiLovecoinBalanceSearchForm" id="fiLovecoinBalanceSearchForm">
<div class="searchBar">
<!-- 
<jecs:title key="pd.agentormember"/>
<input name="userCode" type="text" value="${param.userCode }" size="14"/>
 -->
<div class="new_searchBar" style="margin-left:20px;">
<button name="search" class="btn btn_sele" style="width:auto;" type="submit"><jecs:locale key="operation.button.do.search"/></button>
</div>
<!--  
<input name="search" class="button" type="submit" value="<jecs:locale key="operation.button.do.search"/>"/>
-->
</div>
</form>


<form action="fiLovecoinBalances.html" method="get" name="fiLovecoinBalanceForm" id="fiLovecoinBalanceForm">
<ec:table 
	tableId="fiLovecoinBalanceListTable"
	items="fiLovecoinBalanceList"
	var="fiLovecoinBalance"
	autoIncludeParameters="true"
	retrieveRowsCallback="limit"
	form="fiLovecoinBalanceForm"
	action="${pageContext.request.contextPath}/fiLovecoinBalances.html"
	width="100%"
	showPagination="true"
	sortable="true" imagePath="./images/extremetable/*.gif" >
	<ec:exportXls fileName="loveCoinsReport.xls"/>
	<ec:row highlightRow="false">
		<ec:column property="user_code" title="pd.agentormember" >
		${fiLovecoinBalance.user_code }
		</ec:column>
		<ec:column property="user_name" title="pd.agentormembername" >
		${fiLovecoinBalance.user_name }
		</ec:column>
		<ec:column property="balance" title="bdBounsDeduct.remainMoney" styleClass="numberColumn" width="100" calc="total" calcTitle="report.allTotal" format="###,##0.00">
			<c:if test="${not empty fiLovecoinBalance.balance}"><fmt:formatNumber pattern="###,##0.00" value="${fiLovecoinBalance.balance}"/></c:if>
			<c:if test="${empty fiLovecoinBalance.balance}">0.00</c:if>
		</ec:column>

	</ec:row>
</ec:table>	
</form>



<%--

<ec:table 
	tableId="fiLovecoinBalanceListTable"
	items="fiLovecoinBalanceList"
	var="fiLovecoinBalance"
	action="${pageContext.request.contextPath}/fiLovecoinBalances.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				
    			<ec:column property="userCode" title="pd.agentormember" />
    			<ec:column property="balance" title="bdBounsDeduct.remainMoney" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editFiLovecoinBalance(userCode){
   		<jecs:power powerCode="editFiLovecoinBalance">
					window.location="editFiLovecoinBalance.html?strAction=editFiLovecoinBalance&userCode="+userCode;
			</jecs:power>
		}

</script>
--%>