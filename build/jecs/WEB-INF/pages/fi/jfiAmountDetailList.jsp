<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiAmountDetailList.title"/></title>
<content tag="heading"><jecs:locale key="jfiAmountDetailList.heading"/></content>
<meta name="menu" content="JfiAmountDetailMenu"/>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script type="text/javascript" src="./js/jquery-1.4.2.min.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/calendar-setup.js"> </script> 
<script type="text/javascript" src="./scripts/calendar/lang.jsp"> </script> 

<form name="frm" id="frm"
	action="<c:url value='/jfiAmountDetails.html'/>">
	<div id="titleBar">
		<input type="hidden" name="quotaId" id="quotaId" value="${param.quotaId }"/>
		<c:choose>
			<c:when test="${quotaId!=null && quotaId!='' }">
				<button name="exportExcel" class="btn btn_ins" type="button" value="exportExcel" onclick="location.href='<c:url value="/jfiQuotas.html"/>'">
					<jecs:locale key="operation.button.return"/>
				</button>
			</c:when>
			<c:otherwise>
				<jecs:title key="bdBounsDeduct.wweek" />
			<input name="validityPeriod" id="validityPeriod"
				value="<c:out value='${param.validityPeriod}'/>" cssClass="text medium" />
				
			<jecs:title key="fiBillAccount.AccountCode" />
			<input name="billAccountCode" id="billAccountCode"
				value="<c:out value='${param.billAccountCode}'/>" cssClass="text medium" />
				
			<jecs:title key="miMember.memberNo" />
			<input name="userCode" id="userCode"
				value="<c:out value='${param.userCode}'/>" cssClass="text medium" />
			<br/>
			<jecs:title key="pd.createTime"/>
					<input name="createTimeStart" size='11' id="createTimeStart"  value="${param.createTimeStart}">
		    	<img src="./images/calendar/calendar7.gif" id="img_startTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createTimeStart", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_startTime", 
					singleClick    :    true
					}); 
				</script>
				- <input name="createTimeEnd" size='11'  id="createTimeEnd"  value="${param.createTimeEnd}">
		    	<img src="./images/calendar/calendar7.gif" id="img_endTime" style="cursor: pointer;" title="<jecs:locale key="Calendar.TT.SEL_DATE"/>"/> 
				<script type="text/javascript"> 
					Calendar.setup({
					inputField     :    "createTimeEnd", 
					ifFormat       :    "%Y-%m-%d",  
					button         :    "img_endTime", 
					singleClick    :    true
					}); 
				</script>
				<input type="submit" class="button"
								value="<jecs:locale  key='operation.button.search'/>" />
			</c:otherwise>
		</c:choose>
		<c:out value="${buttons}" escapeXml="false"/>
	</div>
</form>
	
<ec:table 
	tableId="jfiAmountDetailListTable"
	items="jfiAmountDetailList"
	var="jfiAmountDetail"
	action="${pageContext.request.contextPath}/jfiAmountDetails.html"
	width="100%"
	retrieveRowsCallback="limit"
	autoIncludeParameters="true"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:exportXls fileName="jfi99billLogs.xls" />
				<ec:row >
					<ec:column property="jfiQuota.validityPeriod" title="bdBounsDeduct.wweek" />
	    			<ec:column property="jfiQuota.fiBillAccount.billAccountCode" title="fiBillAccount.AccountCode" />
	    			<ec:column property="userCode" title="miMember.memberNo" />
	    			<ec:column property="memberOrderNo" title="poMemberOrder.memberOrderNo" />
	    			<ec:column property="money" title="fiBankbookJournal.money" />
	    			<ec:column property="createTime" title="sysOperationLog.operateTime" format="yyyy-MM-dd HH:mm:ss" cell="date"/>
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiAmountDetail(id){
   		<jecs:power powerCode="editJfiAmountDetail">
					window.location="editJfiAmountDetail.html?strAction=editJfiAmountDetail&id="+id;
			</jecs:power>
		}

</script>
