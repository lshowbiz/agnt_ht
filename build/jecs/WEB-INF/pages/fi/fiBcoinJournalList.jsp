<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBcoinJournalList.title"/></title>
<content tag="heading"><jecs:locale key="fiBcoinJournalList.heading"/></content>
<meta name="menu" content="FiBcoinJournalMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addFiBcoinJournal">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editFiBcoinJournal.html"/>?strAction=addFiBcoinJournal'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="fiBcoinJournalListTable"
	items="fiBcoinJournalList"
	var="fiBcoinJournal"
	action="${pageContext.request.contextPath}/fiBcoinJournals.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editFiBcoinJournal('${fiBcoinJournal.journalId}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="userCode" title="fiBcoinJournal.userCode" />
    			<ec:column property="serial" title="fiBcoinJournal.serial" />
    			<ec:column property="dealType" title="fiBcoinJournal.dealType" />
    			<ec:column property="dealDate" title="fiBcoinJournal.dealDate" />
    			<ec:column property="coin" title="fiBcoinJournal.coin" />
    			<ec:column property="notes" title="fiBcoinJournal.notes" />
    			<ec:column property="balance" title="fiBcoinJournal.balance" />
    			<ec:column property="remark" title="fiBcoinJournal.remark" />
    			<ec:column property="createrCode" title="fiBcoinJournal.createrCode" />
    			<ec:column property="createrName" title="fiBcoinJournal.createrName" />
    			<ec:column property="createTime" title="fiBcoinJournal.createTime" />
    			<ec:column property="moneyType" title="fiBcoinJournal.moneyType" />
    			<ec:column property="uniqueCode" title="fiBcoinJournal.uniqueCode" />
    			<ec:column property="appId" title="fiBcoinJournal.appId" />

				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editFiBcoinJournal(journalId){
   		<jecs:power powerCode="editFiBcoinJournal">
					window.location="editFiBcoinJournal.html?strAction=editFiBcoinJournal&journalId="+journalId;
			</jecs:power>
		}

</script>
