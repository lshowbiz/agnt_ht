<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="fiBcoinBalanceList.title"/></title>
<content tag="heading"><jecs:locale key="fiBcoinBalanceList.heading"/></content>
<meta name="menu" content="FiBcoinBalanceMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addFiBcoinBalance">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editFiBcoinBalance.html"/>?strAction=addFiBcoinBalance'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="fiBcoinBalanceListTable"
	items="fiBcoinBalanceList"
	var="fiBcoinBalance"
	action="${pageContext.request.contextPath}/fiBcoinBalances.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/icons/editIcon.gif'/>" 
								onclick="javascript:editFiBcoinBalance('${fiBcoinBalance.userCode}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="balance" title="fiBcoinBalance.balance" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editFiBcoinBalance(userCode){
   		<jecs:power powerCode="editFiBcoinBalance">
					window.location="editFiBcoinBalance.html?strAction=editFiBcoinBalance&userCode="+userCode;
			</jecs:power>
		}

</script>
