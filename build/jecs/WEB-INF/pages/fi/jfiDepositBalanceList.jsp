<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jfiDepositBalanceList.title"/></title>
<content tag="heading"><jecs:locale key="jfiDepositBalanceList.heading"/></content>
<meta name="menu" content="JfiDepositBalanceMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJfiDepositBalance">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJfiDepositBalance.html"/>?strAction=addJfiDepositBalance'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jfiDepositBalanceListTable"
	items="jfiDepositBalanceList"
	var="jfiDepositBalance"
	action="${pageContext.request.contextPath}/jfiDepositBalances.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="/jecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editJfiDepositBalance('${jfiDepositBalance.fdbId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="userCode" title="jfiDepositBalance.userCode" />
    			<ec:column property="balance" title="jfiDepositBalance.balance" />
    			<ec:column property="depositType" title="jfiDepositBalance.depositType" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJfiDepositBalance(fdbId){
   		<jecs:power powerCode="editJfiDepositBalance">
					window.location="editJfiDepositBalance.html?strAction=editJfiDepositBalance&fdbId="+fdbId;
			</jecs:power>
		}

</script>
