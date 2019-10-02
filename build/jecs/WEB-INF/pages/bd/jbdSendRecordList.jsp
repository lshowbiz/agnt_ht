<%@ include file="/common/taglibs.jsp"%>

<title><jecs:locale key="jbdSendRecordList.title"/></title>
<content tag="heading"><jecs:locale key="jbdSendRecordList.heading"/></content>
<meta name="menu" content="JbdSendRecordMenu"/>


<form action="" method="get" name="bdSendRecordForm1" id="bdSendRecordForm1">
<c:if test="${sessionScope.sessionLogin.userType=='C'}">
<div class="searchBar">
		<jecs:title key="miMember.memberNo"/>
			<input name="userCode" type="text" value="${param.userCode }" size="12"/>
		<jecs:title key="bdCalculatingSubDetail.name"/>
			<input name="name" type="text" value="${param.name}" size="12"/>
		<jecs:locale key="bdBounsDeduct.wweek"/><jecs:title key="busi.common.ex"/>
			<input name="wweek" type="text" value="${param.wweek }" size="8"/>
		<jecs:title key="bdSendRecord.cardType"/>
			<jecs:list name="cardType" listCode="bd.cardtype" value="${param.cardType}" defaultValue="" showBlankLine="true" />
<input name="search" type="submit" class="button" onclick="loading('<jecs:locale key="button.loading"/>');" value="<jecs:locale key="operation.button.search"/>"/>
<%--<table width="100%" border="0">
	<tr>

	
		<th>
		</th>
		
		<th>
		</th>
		
		<th>
		</th>

		<th>
		</th>
		<th>
		<jecs:locale key="operation.button.search"/>
		</th>
		<th>&nbsp;</th>
	</tr>
	<tr>

		<td>
		</td>
		<td>
		</td>
		<td>
		</td>

		<td>
		</td>
		<td>
			
		</td>
		<td width="100%">&nbsp;</td>
	</tr>
	
</table>--%>
</div>

</c:if>
</form>






<ec:table 
	tableId="jbdSendRecordListTable"
	items="jbdSendRecordList"
	var="jbdSendRecord"
	action="${pageContext.request.contextPath}/jbdSendRecords.html"
	width="100%"
	retrieveRowsCallback="limit"
	rowsDisplayed="20" sortable="true" imagePath="./images/extremetable/*.gif">
				<ec:row >
				<ec:column property="edit" title="title.operation" sortable="false" viewsAllowed="html">
							<img src="<c:url value='/images/newIcons/pencil_16.gif'/>" 
								onclick="javascript:editJbdSendRecord('${jbdSendRecord.id}')"
								style="cursor: pointer;" title="<jecs:locale key="button.edit"/>"/> 
					</ec:column>
					
    			<ec:column property="userCode" title="jbdSendRecord.userCode" />
    			<ec:column property="recommendNo" title="jbdSendRecord.recommendNo" />
    			<ec:column property="linkNo" title="jbdSendRecord.linkNo" />
    			<ec:column property="name" title="jbdSendRecord.name" />
    			<ec:column property="patName" title="jbdSendRecord.patName" />
    			<ec:column property="cardType" title="jbdSendRecord.cardType" />
    			<ec:column property="bank" title="jbdSendRecord.bank" />
    			<ec:column property="bankaddress" title="jbdSendRecord.bankaddress" />
    			<ec:column property="bankbook" title="jbdSendRecord.bankbook" />
    			<ec:column property="bankcard" title="jbdSendRecord.bankcard" />
    			<ec:column property="exitDate" title="jbdSendRecord.exitDate" />
    			<ec:column property="sendLateCause" title="jbdSendRecord.sendLateCause" />
    			<ec:column property="sendLateRemark" title="jbdSendRecord.sendLateRemark" />
    			<ec:column property="remittanceMoney" title="jbdSendRecord.remittanceMoney" />
    			<ec:column property="remittanceBNum" title="jbdSendRecord.remittanceBNum" />
    			<ec:column property="operaterCode" title="jbdSendRecord.operaterCode" />
    			<ec:column property="operaterTime" title="jbdSendRecord.operaterTime" />
    			<ec:column property="registerStatus" title="jbdSendRecord.registerStatus" />
    			<ec:column property="reissueStatus" title="jbdSendRecord.reissueStatus" />
    			<ec:column property="supplyTime" title="jbdSendRecord.supplyTime" />
    			<ec:column property="sendTrace" title="jbdSendRecord.sendTrace" />
    			<ec:column property="sendRemark" title="jbdSendRecord.sendRemark" />
    			<ec:column property="sendMoney" title="jbdSendRecord.sendMoney" />
    			<ec:column property="totalMoney" title="jbdSendRecord.totalMoney" />
				</ec:row>

</ec:table>	

<script type="text/javascript">

   function editJbdSendRecord(id){
   		<jecs:power powerCode="editJbdSendRecord">
					window.location="editJbdSendRecord.html?strAction=editJbdSendRecord&id="+id;
			</jecs:power>
		}

</script>
