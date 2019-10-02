<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script>
<title><jecs:locale key="bdSendOperaterSearchList.title"/></title>
<content tag="heading"><jecs:locale key="bdSendOperaterSearchList.heading"/></content>
<meta name="menu" content="BdSendRecordMenu"/>
<form action="bdSendOperaterStatisticReport.html" method="get" name="bdSendOperaterStatisticForm1" id="bdSendOperaterStatisticForm1">
<input type="hidden" name="strAction" value="${param.strAction }"/>
<div class="searchBar">

<jecs:locale key="bdBounsDeduct.wweek"/>	<jecs:title key="busi.common.ex"/>

<input name="wweek" type="text" value="${param.wweek }" size="6"/>		
<input name="search" type="submit" class="button" onclick="loading('<jecs:locale key="button.loading"/>');" value="<jecs:locale key="operation.button.search"/>"/>		

</div>




</form>
<div id="loading">
<ec:table 
	tableId="bdSendOperaterStatisticListTable"
	items="bdSendOperaterStatisticList"
	var="bdSendOperaterStatistic"
	action="${pageContext.request.contextPath}/bdSendOperaterStatisticReport.html"
	width="100%"
	showPagination="false"
	rowsDisplayed="20" sortable="false" imagePath="./images/extremetable/*.gif" >

				<ec:row>
    			<ec:column property="bd_type" title="bdSendRecord.registerStatus" >
    				<jecs:code listCode="bd.send.status" value="${bdSendOperaterStatistic['bd_type'] }" />
    			</ec:column>
    		
    			
    			<ec:column property="qty" title="report.bdSend.Statistic.count" styleClass="numberColumn" >
 			
    			</ec:column>
    			
    			<ec:column property="sum_money" title="bdSendRecord.remittanceMoney" styleClass="numberColumn" >
     				<fmt:parseNumber value="${bdSendOperaterStatistic['sum_money'] }" pattern="###,###,##0.00" var="varNumber"></fmt:parseNumber>
    				<fmt:formatNumber pattern="###,###,##0.00">${varNumber}</fmt:formatNumber>   				
    			</ec:column>
    			
    		
    			
    	
    			
    					
				</ec:row>

</ec:table>	
</div>


<script type="text/javascript">

   function editBdSendRecord(recordId){
   		<jecs:power powerCode="editBdSendRecord">
					window.location="editBdSendRecord.html?strAction=editBdSendRecord&recordId="+recordId;
			</jecs:power>
		}

</script>
