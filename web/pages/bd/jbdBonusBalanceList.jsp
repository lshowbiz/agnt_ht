<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdBonusBalanceList.title"/></title>
<content tag="heading"><jecs:locale key="jbdBonusBalanceList.heading"/></content>
<meta name="menu" content="JbdBonusBalanceMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdBonusBalance">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdBonusBalance.html"/>?strAction=addJbdBonusBalance'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jbdBonusBalanceListTable"
	items="jbdBonusBalanceList"
	var="jbdBonusBalance"
	action="${pageContext.request.contextPath}/jbdBonusBalances.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editJbdBonusBalance('${jbdBonusBalance.userCode}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="companyCode" title="jbdBonusBalance.companyCode" />
    			<ec:column property="name" title="jbdBonusBalance.name" />
    			<ec:column property="cardType" title="jbdBonusBalance.cardType" />
    			<ec:column property="bank" title="jbdBonusBalance.bank" />
    			<ec:column property="bankaddress" title="jbdBonusBalance.bankaddress" />
    			<ec:column property="bankbook" title="jbdBonusBalance.bankbook" />
    			<ec:column property="bankcard" title="jbdBonusBalance.bankcard" />
    			<ec:column property="bonusBalance" title="jbdBonusBalance.bonusBalance" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdBonusBalance(userCode){
   		<jecs:power powerCode="editJbdBonusBalance">
					window.location="editJbdBonusBalance.html?strAction=editJbdBonusBalance&userCode="+userCode;
			</jecs:power>
		}

</script>
