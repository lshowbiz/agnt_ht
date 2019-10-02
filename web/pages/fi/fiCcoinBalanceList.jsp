<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiCcoinBalanceList.title"/></title>
<content tag="heading"><jecs:locale key="fiCcoinBalanceList.heading"/></content>
<meta name="menu" content="FiCcoinBalanceMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addFiCcoinBalance">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editFiCcoinBalance.html"/>?strAction=addFiCcoinBalance'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="fiCcoinBalanceListTable"
	items="fiCcoinBalanceList"
	var="fiCcoinBalance"
	action="${pageContext.request.contextPath}/fiCcoinBalances.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editFiCcoinBalance('${fiCcoinBalance.userCode}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="balance" title="fiCcoinBalance.balance" />
    			<ec:column property="lastJournalId" title="fiCcoinBalance.lastJournalId" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editFiCcoinBalance(userCode){
   		<jecs:power powerCode="editFiCcoinBalance">
					window.location="editFiCcoinBalance.html?strAction=editFiCcoinBalance&userCode="+userCode;
			</jecs:power>
		}

</script>
