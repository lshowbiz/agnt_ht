<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdSendRecordHistList.title"/></title>
<content tag="heading"><jecs:locale key="jbdSendRecordHistList.heading"/></content>
<meta name="menu" content="JbdSendRecordHistMenu"/>

<c:set var="buttons">
		<jecs:power powerCode="addJbdSendRecordHist">
			    <input type="button" class="button" style="margin-right: 5px"
			        onclick="location.href='<c:url value="/editJbdSendRecordHist.html"/>?strAction=addJbdSendRecordHist'"
			        value="<jecs:locale key="button.add"/>"/>
		</jecs:power>
</c:set>
<div id="titleBar">
<c:out value="${buttons}" escapeXml="false"/>
</div>
<ec:table 
	tableId="jbdSendRecordHistListTable"
	items="jbdSendRecordHistList"
	var="jbdSendRecordHist"
	action="${pageContext.request.contextPath}/jbdSendRecordHists.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sort./images/extremetableecs/images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editJbdSendRecordHist('${jbdSendRecordHist.id}')"
								style="cursor: pointer;" title="<wecs:locale key="button.edit"/>"/> 
					</ec:column>
    			<ec:column property="wyear" title="jbdSendRecordHist.wyear" />
    			<ec:column property="wmonth" title="jbdSendRecordHist.wmonth" />
    			<ec:column property="wweek" title="jbdSendRecordHist.wweek" />
    			<ec:column property="companyCode" title="jbdSendRecordHist.companyCode" />
    			<ec:column property="userCode" title="jbdSendRecordHist.userCode" />
    			<ec:column property="recommendNo" title="jbdSendRecordHist.recommendNo" />
    			<ec:column property="linkNo" title="jbdSendRecordHist.linkNo" />
    			<ec:column property="name" title="jbdSendRecordHist.name" />
    			<ec:column property="patName" title="jbdSendRecordHist.patName" />
    			<ec:column property="cardType" title="jbdSendRecordHist.cardType" />
    			<ec:column property="bank" title="jbdSendRecordHist.bank" />
    			<ec:column property="bankaddress" title="jbdSendRecordHist.bankaddress" />
    			<ec:column property="bankbook" title="jbdSendRecordHist.bankbook" />
    			<ec:column property="bankcard" title="jbdSendRecordHist.bankcard" />
    			<ec:column property="exitDate" title="jbdSendRecordHist.exitDate" />
    			<ec:column property="sendLateCause" title="jbdSendRecordHist.sendLateCause" />
    			<ec:column property="sendLateRemark" title="jbdSendRecordHist.sendLateRemark" />
    			<ec:column property="remittanceMoney" title="jbdSendRecordHist.remittanceMoney" />
    			<ec:column property="remittanceBNum" title="jbdSendRecordHist.remittanceBNum" />
    			<ec:column property="operaterCode" title="jbdSendRecordHist.operaterCode" />
    			<ec:column property="operaterTime" title="jbdSendRecordHist.operaterTime" />
    			<ec:column property="registerStatus" title="jbdSendRecordHist.registerStatus" />
    			<ec:column property="reissueStatus" title="jbdSendRecordHist.reissueStatus" />
    			<ec:column property="supplyTime" title="jbdSendRecordHist.supplyTime" />
    			<ec:column property="sendTrace" title="jbdSendRecordHist.sendTrace" />
    			<ec:column property="sendRemark" title="jbdSendRecordHist.sendRemark" />
    			<ec:column property="sendMoney" title="jbdSendRecordHist.sendMoney" />
    			<ec:column property="totalMoney" title="jbdSendRecordHist.totalMoney" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdSendRecordHist(id){
   		<jecs:power powerCode="editJbdSendRecordHist">
					window.location="editJbdSendRecordHist.html?strAction=editJbdSendRecordHist&id="+id;
			</jecs:power>
		}

</script>
