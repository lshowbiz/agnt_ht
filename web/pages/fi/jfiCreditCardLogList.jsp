<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiCreditCardLogList.title"/></title>
<content tag="heading"><jecs:locale key="jfiCreditCardLogList.heading"/></content>
<meta name="menu" content="JfiCreditCardLogMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJfiCreditCardLog">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJfiCreditCardLog.html"/>?strAction=addJfiCreditCardLog'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jfiCreditCardLogListTable"
	items="jfiCreditCardLogList"
	var="jfiCreditCardLog"
	action="${pageContext.request.contextPath}/jfiCreditCardLogs.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJfiCreditCardLog('${jfiCreditCardLog.logId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="companyCode" title="jfiCreditCardLog.companyCode" />
    			<ec:column property="userCode" title="jfiCreditCardLog.userCode" />
    			<ec:column property="firstName" title="jfiCreditCardLog.firstName" />
    			<ec:column property="lastName" title="jfiCreditCardLog.lastName" />
    			<ec:column property="cardNumber" title="jfiCreditCardLog.cardNumber" />
    			<ec:column property="expireDate" title="jfiCreditCardLog.expireDate" />
    			<ec:column property="payAmount" title="jfiCreditCardLog.payAmount" />
    			<ec:column property="payTime" title="jfiCreditCardLog.payTime" />
    			<ec:column property="payResult" title="jfiCreditCardLog.payResult" />
    			<ec:column property="memberOrderNo" title="jfiCreditCardLog.memberOrderNo" />
    			<ec:column property="payCause" title="jfiCreditCardLog.payCause" />
    			<ec:column property="returnData" title="jfiCreditCardLog.returnData" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiCreditCardLog(logId){
   		<jecs:power powerCode="editJfiCreditCardLog">
					window.location="editJfiCreditCardLog.html?strAction=editJfiCreditCardLog&logId="+logId;
			</jecs:power>
		}

</script>
